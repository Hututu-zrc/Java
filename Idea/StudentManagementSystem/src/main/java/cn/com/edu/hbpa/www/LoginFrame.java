package cn.com.edu.hbpa.www;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("登录 - 学生信息管理系统");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("用户名:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("密码:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("登录");
        loginButton.addActionListener(this);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // 验证用户名和密码
        if (username.equals("admin") && password.equals("123456")) {
            JOptionPane.showMessageDialog(this, "登录成功！");
            dispose();
            SwingUtilities.invokeLater(() -> {
                new StudentManagementFrame(); // 打开学生管理界面
            });
        } else {
            JOptionPane.showMessageDialog(this, "用户名或密码错误！");
        }
    }
}
