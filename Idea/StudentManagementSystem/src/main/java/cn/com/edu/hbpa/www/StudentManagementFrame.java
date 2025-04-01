package cn.com.edu.hbpa.www;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementFrame extends JFrame implements ActionListener {
    private JTextArea displayArea;
    private JTextField snoField, nameField, birthdayField, departmentField;
    private JButton addButton, deleteButton, updateButton, queryButton;
    private File dataFile = new File("students.txt");

    public StudentManagementFrame() {
        setTitle("学生信息管理系统");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("学号:"));
        snoField = new JTextField();
        inputPanel.add(snoField);

        inputPanel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("出生日期:"));
        birthdayField = new JTextField();
        inputPanel.add(birthdayField);

        inputPanel.add(new JLabel("院系:"));
        departmentField = new JTextField();
        inputPanel.add(departmentField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("添加");
        addButton.addActionListener(this);
        deleteButton = new JButton("删除");
        deleteButton.addActionListener(this);
        updateButton = new JButton("修改");
        updateButton.addActionListener(this);
        queryButton = new JButton("查询");
        queryButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(queryButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        loadData(); // 加载数据
        setVisible(true);
    }

    private void loadData() {
        displayArea.setText("");
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                displayArea.append(line + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "数据文件加载失败：" + e.getMessage());
        }
    }

    private void saveData(List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (String record : data) {
                bw.write(record);
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "数据文件保存失败：" + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sno = snoField.getText().trim();
        String name = nameField.getText().trim();
        String birthday = birthdayField.getText().trim();
        String department = departmentField.getText().trim();

        try {
            List<String> data = new ArrayList<>();
            if (dataFile.exists()) {
                data = new ArrayList<>(java.nio.file.Files.readAllLines(dataFile.toPath()));
            }

            if (e.getSource() == addButton) {
                data.add(String.format("%s,%s,%s,%s", sno, name, birthday, department));
                saveData(data);
                JOptionPane.showMessageDialog(this, "学生添加成功！");
            } else if (e.getSource() == deleteButton) {
                data.removeIf(record -> record.startsWith(sno + ","));
                saveData(data);
                JOptionPane.showMessageDialog(this, "学生删除成功！");
            } else if (e.getSource() == updateButton) {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).startsWith(sno + ",")) {
                        data.set(i, String.format("%s,%s,%s,%s", sno, name, birthday, department));
                        break;
                    }
                }
                saveData(data);
                JOptionPane.showMessageDialog(this, "学生信息修改成功！");
            } else if (e.getSource() == queryButton) {
                displayArea.setText("");
                for (String record : data) {
                    if (record.startsWith(sno + ",") || name.isEmpty() || record.contains(name)) {
                        displayArea.append(record + "\n");
                    }
                }
                return;
            }

            loadData(); // 更新显示
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "操作失败：" + ex.getMessage());
        }
    }
}
