import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField tf1;
    JPasswordField pf1;
    JButton loginBtn, registerBtn;

    public LoginPage() {

        setTitle("Online Examination System - Login");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        l1 = new JLabel("Username");
        l1.setBounds(50, 50, 100, 25);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(150, 50, 150, 25);
        add(tf1);

        l2 = new JLabel("Password");
        l2.setBounds(50, 90, 100, 25);
        add(l2);

        pf1 = new JPasswordField();
        pf1.setBounds(150, 90, 150, 25);
        add(pf1);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(70, 150, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(200, 150, 100, 30);
        registerBtn.addActionListener(this);
        add(registerBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {

            String username = tf1.getText();
            String password = String.valueOf(pf1.getPassword());

            try {

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM users WHERE username=? AND password=?");

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    JOptionPane.showMessageDialog(this,
                            "Login Successful!");

                    new ExamPage();
                    dispose();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Invalid Username or Password");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == registerBtn) {

            new RegisterPage();

        }
    }

    public static void main(String[] args) {

        new LoginPage();

    }
}