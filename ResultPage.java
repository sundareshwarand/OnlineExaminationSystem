<<<<<<< HEAD
import javax.swing.*;
import java.sql.*;

public class ResultPage extends JFrame {

    public ResultPage(int score) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO results(username,score) VALUES(?,?)");

            ps.setString(1, "admin");
            ps.setInt(2, score);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Result");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel resultLabel =
                new JLabel("Your Score : " + score);

        resultLabel.setBounds(80, 60, 150, 30);

        add(resultLabel);

        setVisible(true);
    }
=======
import javax.swing.*;
import java.sql.*;

public class ResultPage extends JFrame {

    public ResultPage(int score) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO results(username,score) VALUES(?,?)");

            ps.setString(1, "admin");
            ps.setInt(2, score);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Result");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel resultLabel =
                new JLabel("Your Score : " + score);

        resultLabel.setBounds(80, 60, 150, 30);

        add(resultLabel);

        setVisible(true);
    }
>>>>>>> c71d8f88e8928821b079a4ad3f6d4646ba467f0b
}