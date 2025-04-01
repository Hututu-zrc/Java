package cn.com.edu.hbpa.www;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class InfoInsertJPanel extends JPanel implements ActionListener {
    // 声明标签
    private JLabel JLsno = new JLabel("学    号");
    private JLabel JLname = new JLabel("姓    名");
    private JLabel JLsex = new JLabel("性    别");
    private JLabel JLbirthday = new JLabel("出生日期");
    private JLabel JLdepartment = new JLabel("院系名称");

    // 声明文本框
    private JTextField JTFsno = new JTextField();
    private JTextField JTFname = new JTextField(10);
    private JTextField JTFbirthday = new JTextField(10);

    // 声明单选按钮
    private JRadioButton JRB1 = new JRadioButton("男", true);
    private JRadioButton JRB2 = new JRadioButton("女");
    private ButtonGroup BGsex = new ButtonGroup(); // 声明两个单选按钮加入的ButtonGroup

    // 声明下拉列表
    String[] depts = {"信息安全", "计算机科学", "刑事技术", "治安管理", "法律", "涉外警务"};
    private JComboBox JCBdepartment = new JComboBox(depts);

    // 声明按钮
    private JButton JBsubmit = new JButton("插入");
    private JButton JBreset = new JButton("重置");
    private JButton JBSave = new JButton("保存");  // 新增保存按钮

    // 声明面板
    private JPanel JPLabels = new JPanel();
    private JPanel JPsex = new JPanel();
    private JPanel JPinputArea = new JPanel();
    private JPanel JPechoArea = new JPanel();
    private JPanel JPbuttons = new JPanel();

    // 构造方法
    public InfoInsertJPanel() {
        // 建立性别面板
        JPsex.setLayout(new GridLayout(1, 2));
        JPsex.add(JRB1);
        JPsex.add(JRB2);
        BGsex.add(JRB1);
        BGsex.add(JRB2);

        // 建立放置静态文本的面板，并且设置大小
        JPLabels.setLayout(new GridLayout(5, 1));
        JLsno.setHorizontalAlignment(SwingConstants.CENTER);    // 设置文本对齐方式
        JLname.setHorizontalAlignment(SwingConstants.CENTER);
        JLsex.setHorizontalAlignment(SwingConstants.CENTER);
        JLbirthday.setHorizontalAlignment(SwingConstants.CENTER);
        JLdepartment.setHorizontalAlignment(SwingConstants.CENTER);
        JPLabels.add(JLsno);
        JPLabels.add(JLname);
        JPLabels.add(JLsex);
        JPLabels.add(JLbirthday);
        JPLabels.add(JLdepartment);

        // 建立放置输入文本框的面板
        JPinputArea.setLayout(new GridLayout(5, 1));
        JPinputArea.add(JTFsno);
        JPinputArea.add(JTFname);
        JPinputArea.add(JPsex);
        JPinputArea.add(JTFbirthday);
        JPinputArea.add(JCBdepartment);

        // 建立组合放置静态文本和文本框面板
        JPechoArea.setLayout(new GridLayout(1, 2));
        JPechoArea.add(JPLabels);
        JPechoArea.add(JPinputArea);

        // 设置按钮面板
        JPbuttons.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPbuttons.add(JBsubmit);
        JPbuttons.add(JBreset);
        JPbuttons.add(JBSave);  // 添加保存按钮

        // 把面板放置到框架中
        this.setLayout(new BorderLayout());
        this.add(JPechoArea, BorderLayout.CENTER);
        this.add(JPbuttons, BorderLayout.SOUTH);

        // 设置监听
        JBsubmit.addActionListener(this);
        JBreset.addActionListener(this);
        JBSave.addActionListener(this);  // 设置保存按钮监听
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("插入")) {
            // 将所输入的数据插入到数据库或文件
            System.out.println("write");
        } else if (command.equals("重置")) {
            // 重置所有组件
            reset();
        } else if (command.equals("保存")) {
            // 保存信息到 students.txt
            saveStudentInfo();
        }
    }

    // 保存学生信息到 students.txt
    private void saveStudentInfo() {
        String sno = JTFsno.getText();
        String name = JTFname.getText();
        String birthday = JTFbirthday.getText();
        String department = (String) JCBdepartment.getSelectedItem();
        String sex = JRB1.isSelected() ? "男" : "女";

        // 检查是否输入完整
        if (sno.isEmpty() || name.isEmpty() || birthday.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请完整填写学生信息", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 将信息写入到文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
            writer.write(sno + "," + name + "," + birthday + "," + department + "," + sex);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "信息保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            reset();  // 保存成功后重置
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "保存失败，发生错误！", "错误", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // 清空所有文本框
    private void reset() {
        JTFsno.setText("");
        JTFname.setText("");
        JTFbirthday.setText("");
        JRB1.setSelected(true);
        JCBdepartment.setSelectedIndex(0);
    }
}
