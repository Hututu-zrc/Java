import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {
    private static List<User> users = new ArrayList<>();
    private static MenuDatabase menuDatabase = new MenuDatabase();
    private static User currentUser;

    public static void main(String[] args) {
        initializeUsers();
        menuDatabase.loadDishesFromDatabase();
        SwingUtilities.invokeLater(CRUD::createLoginGUI);
    }

    private static void initializeUsers() {
        users.add(new User("admin", "admin123", "管理员"));
        users.add(new User("user", "user123", "普通用户"));
    }

    private static void createLoginGUI() {
        JFrame loginFrame = new JFrame("登录界面");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new GridLayout(5, 2));

        JLabel userLabel = new JLabel("用户名:");
        JTextField userField = new JTextField();
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("登录");
        JLabel messageLabel = new JLabel("");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                currentUser = login(username, password);
                if (currentUser != null) {
                    messageLabel.setText("登录成功!");
                    loginFrame.dispose();
                    createMenuManagementGUI();
                } else {
                    messageLabel.setText("用户名或密码错误!");
                }
            }
        });

        loginFrame.add(userLabel);
        loginFrame.add(userField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(new JLabel(""));
        loginFrame.add(loginButton);
        loginFrame.add(messageLabel);

        loginFrame.setVisible(true);
    }

    private static User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }




    private static void createMenuManagementGUI() {
        JFrame menuFrame = new JFrame("菜品管理系统_朱润财");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(500, 300);
        menuFrame.setLayout(new BorderLayout());

        JButton addButton = new JButton("添加菜品");
        JButton updateButton = new JButton("修改菜品");
        JButton deleteButton = new JButton("删除菜品");
        JButton listButton = new JButton("查看所有菜品");
        JButton saveButton = new JButton("保存菜品信息");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0; // 初始行位置
        gbc.insets = new Insets(10, 10, 10, 10); // 设置按钮之间的间距


        gbc.weighty = 1.0; // 垂直方向权重
        gbc.fill = GridBagConstraints.VERTICAL; // 垂直填充
        gbc.anchor = GridBagConstraints.CENTER; // 水平居中

        buttonPanel.add(addButton, gbc);
        gbc.gridy++; // 移动到下一行
        buttonPanel.add(updateButton, gbc);
        gbc.gridy++;
        buttonPanel.add(deleteButton, gbc);
        gbc.gridy++;
        buttonPanel.add(listButton, gbc);
        gbc.gridy++;

        buttonPanel.add(saveButton, gbc);


        addButton.addActionListener(e -> addDish());
        updateButton.addActionListener(e -> updateDish());
        deleteButton.addActionListener(e -> deleteDish());
        listButton.addActionListener(e -> listDishes());
        saveButton.addActionListener(e -> saveDishes());
        menuFrame.add(buttonPanel, BorderLayout.CENTER);



        menuFrame.setVisible(true);
    }




    private static void addDish() {
        if (!currentUser.getRole().equals("管理员")) {
            JOptionPane.showMessageDialog(null, "您没有权限进行此操作。", "权限错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = JOptionPane.showInputDialog("输入菜品名称:");
        if (name == null) {
            return; // 用户点击取消
        }
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "菜品名称不能为空。", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String priceStr = JOptionPane.showInputDialog("输入菜品价格:");
        if (priceStr == null) {
            return; // 用户点击取消
        }
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "请输入有效的价格。", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String description = JOptionPane.showInputDialog("输入菜品描述:");
        if (description == null) {
            return; // 用户点击取消
        }
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "菜品描述不能为空。", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Dish dish = new Dish(0, name, price, description);
        menuDatabase.addDish(dish);
    }

    private static void updateDish() {
        if (!currentUser.getRole().equals("管理员")) {
            JOptionPane.showMessageDialog(null, "您没有权限进行此操作。", "权限错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idStr = JOptionPane.showInputDialog("输入要修改的菜品ID:");
        if (idStr == null) {
            return; // 用户点击取消
        }
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "请输入有效的ID。", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String name = JOptionPane.showInputDialog("输入新的菜品名称:");
        if (name == null) {
            return; // 用户点击取消
        }
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "菜品名称不能为空。", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String priceStr = JOptionPane.showInputDialog("输入新的菜品价格:");
        if (priceStr == null) {
            return; // 用户点击取消
        }
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "请输入有效的价格。", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String description = JOptionPane.showInputDialog("输入新的菜品描述:");
        if (description == null) {
            return; // 用户点击取消
        }
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "菜品描述不能为空。", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Dish updatedDish = new Dish(id, name, price, description);
        menuDatabase.updateDish(id, updatedDish);
    }

    private static void deleteDish() {
        if (!currentUser.getRole().equals("管理员")) {
            JOptionPane.showMessageDialog(null, "您没有权限进行此操作。", "权限错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idStr = JOptionPane.showInputDialog("输入要删除的菜品ID:");
        if (idStr == null) {
            return; // 用户点击取消
        }
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "请输入有效的ID。", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        menuDatabase.deleteDish(id);
    }

    private static void listDishes() {
        StringBuilder dishesText = new StringBuilder();
        for (Dish dish : menuDatabase.dishes) {
            dishesText.append(dish).append("\n");
        }
        JTextArea dishListArea = new JTextArea(dishesText.toString());
        dishListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(dishListArea);

        JFrame listFrame = new JFrame("菜品列表");
        listFrame.setSize(400, 300);
        listFrame.add(scrollPane);
        listFrame.setVisible(true);
    }

    private static void saveDishes() {
        menuDatabase.saveDishesToDatabase();
        JOptionPane.showMessageDialog(null, "菜品信息已保存。", "保存成功", JOptionPane.INFORMATION_MESSAGE);
    }
}

class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

class Dish {
    private int id;
    private String name;
    private double price;
    private String description;

    public Dish(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dish{id=" + id + ", name='" + name + "', price=" + price + ", description='" + description + "'}";
    }
}

class MenuDatabase {
    List<Dish> dishes = new ArrayList<>();
    private int nextId = 1;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dish";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public void addDish(Dish dish) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO dishes (name, price, description) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, dish.getName());
            stmt.setDouble(2, dish.getPrice());
            stmt.setString(3, dish.getDescription());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                dish = new Dish(rs.getInt(1), dish.getName(), dish.getPrice(), dish.getDescription());
                dishes.add(dish);
                System.out.println("菜品添加成功: " + dish);
            }
        } catch (SQLException e) {
            System.out.println("添加菜品时出错: " + e.getMessage());
        }
    }

    public void updateDish(int id, Dish updatedDish) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE dishes SET name = ?, price = ?, description = ? WHERE id = ?")) {
            stmt.setString(1, updatedDish.getName());
            stmt.setDouble(2, updatedDish.getPrice());
            stmt.setString(3, updatedDish.getDescription());
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                Dish dish = findDishById(id);
                if (dish != null) {
                    dish.setName(updatedDish.getName());
                    dish.setPrice(updatedDish.getPrice());
                    dish.setDescription(updatedDish.getDescription());
                    System.out.println("菜品更新成功: " + dish);
                }
            } else {
                System.out.println("未找到ID为 " + id + " 的菜品。");
            }
        } catch (SQLException e) {
            System.out.println("更新菜品时出错: " + e.getMessage());
        }
    }

    public void deleteDish(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM dishes WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                Dish dish = findDishById(id);
                if (dish != null) {
                    dishes.remove(dish);
                    System.out.println("菜品删除成功: " + dish);
                }
            } else {
                System.out.println("未找到ID为 " + id + " 的菜品。");
            }
        } catch (SQLException e) {
            System.out.println("删除菜品时出错: " + e.getMessage());
        }
    }

    private Dish findDishById(int id) {
        for (Dish dish : dishes) {
            if (dish.getId() == id) {
                return dish;
            }
        }
        return null;
    }

    public void saveDishesToDatabase() {
        // This method is no longer needed since data is directly saved to the database
        System.out.println("菜品信息已保存到数据库中。");
    }

    public void loadDishesFromDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM dishes")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                dishes.add(new Dish(id, name, price, description));
                nextId = Math.max(nextId, id + 1);
            }
            System.out.println("菜品信息已从数据库中加载。");
        } catch (SQLException e) {
            System.out.println("加载菜品信息时出错: " + e.getMessage());
        }
    }
}
