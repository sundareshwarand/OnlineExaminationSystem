import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterPage extends JFrame implements ActionListener {

    JTextField tfUser, tfName, tfEmail, tfPhone;
    JPasswordField pfPass;
    JButton registerBtn;

    public RegisterPage() {

        setTitle("User Registration");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Username");
        l1.setBounds(50, 30, 100, 25);
        add(l1);

        tfUser = new JTextField();
        tfUser.setBounds(170, 30, 150, 25);
        add(tfUser);

        JLabel l2 = new JLabel("Password");
        l2.setBounds(50, 70, 100, 25);
        add(l2);

        pfPass = new JPasswordField();
        pfPass.setBounds(170, 70, 150, 25);
        add(pfPass);

        JLabel l3 = new JLabel("Name");
        l3.setBounds(50, 110, 100, 25);
        add(l3);

        tfName = new JTextField();
        tfName.setBounds(170, 110, 150, 25);
        add(tfName);

        JLabel l4 = new JLabel("Email");
        l4.setBounds(50, 150, 100, 25);
        add(l4);

        tfEmail = new JTextField();
        tfEmail.setBounds(170, 150, 150, 25);
        add(tfEmail);

        JLabel l5 = new JLabel("Phone");
        l5.setBounds(50, 190, 100, 25);
        add(l5);

        tfPhone = new JTextField();
        tfPhone.setBounds(170, 190, 150, 25);
        add(tfPhone);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(130, 250, 120, 30);
        registerBtn.addActionListener(this);
        add(registerBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username,password,name,email,phone) VALUES(?,?,?,?,?)");

            ps.setString(1, tfUser.getText());
            ps.setString(2, String.valueOf(pfPass.getPassword()));
            ps.setString(3, tfName.getText());
            ps.setString(4, tfEmail.getText());
            ps.setString(5, tfPhone.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Registration Successful!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RegisterPage();
    }
}