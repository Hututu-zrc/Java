package cn.com.edu.hbpa.www;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;  // 导入 java.util 包（包括 List）
import java.util.List;

// 如果 InfoInsertJPanel 在同一包下，不需要额外导入；否则，请根据实际路径修改导入语句
import cn.com.edu.hbpa.www.InfoInsertJPanel;  // 导入 InfoInsertJPanel 类

public class JMenuTest extends MouseAdapter implements ActionListener {
    private static JFrame mainFrame;
    private InfoInsertJPanel insertJPanel = new InfoInsertJPanel();  // 创建 InfoInsertJPanel 的实例

    public static void main(String[] args) {
        mainFrame = new JFrame("学生信息管理系统");
        mainFrame.setLocation(400, 200);
        mainFrame.setSize(800, 600);
        Container contentPane = mainFrame.getContentPane();
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);     // 流布局管理器对齐方式为左对齐
        contentPane.setLayout(layout);                                        // 设置容器为流布局管理器

        JMenuBar jmenuBar = new JMenuBar();     // 创建菜单栏

        JMenu J1 = new JMenu("文件(F)");             // 创建菜单
        JMenuItem I1 = new JMenuItem("连接");
        JMenuItem I3 = new JMenuItem("退出");
        J1.add(I1);
        J1.add(I3);

        JMenu J2 = new JMenu("数据操作(O)");
        JMenuItem Delete = new JMenuItem("删除");
        Delete.addActionListener(new JMenuTest());
        J2.add(Delete);

        jmenuBar.add(J1);
        jmenuBar.add(J2);

        contentPane.add(jmenuBar);
        mainFrame.setVisible(true);         // 显示
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("退出")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("删除")) {
            deleteStudentBySno();
        }
    }

    // 删除学生
    private void deleteStudentBySno() {
        String sno = JOptionPane.showInputDialog(mainFrame, "请输入学号删除学生:");
        if (sno != null && !sno.trim().isEmpty()) {
            try {
                File file = new File("students.txt");
                List<String> lines = new ArrayList<>(); // 使用 java.util.List
                boolean found = false;

                // 读取文件内容
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.startsWith(sno + ",")) {
                            lines.add(line);  // 将不包含学号的行添加到列表
                        } else {
                            found = true;  // 找到学生记录
                        }
                    }
                }

                if (found) {
                    // 删除学生信息后，将剩余内容重新写回文件
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        for (String line : lines) {
                            writer.write(line);
                            writer.newLine();
                        }
                    }
                    JOptionPane.showMessageDialog(mainFrame, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "未找到学号为 " + sno + " 的学生", "提示", JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mainFrame, "删除失败，发生错误！", "错误", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}
