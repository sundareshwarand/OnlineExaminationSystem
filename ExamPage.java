import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class ExamPage extends JFrame implements ActionListener {

JLabel questionLabel;

JRadioButton r1, r2, r3, r4;
ButtonGroup bg;

JButton nextBtn;

ArrayList<Integer> questionIds = new ArrayList<>();
ArrayList<String> correctAnswers = new ArrayList<>();

int currentQuestion = 0;
int score = 0;

public ExamPage() {

    setTitle("Online Examination System");
    setSize(600, 350);
    setLayout(null);
    setLocationRelativeTo(null);

    questionLabel = new JLabel();
    questionLabel.setBounds(30, 30, 500, 30);
    add(questionLabel);

    r1 = new JRadioButton();
    r1.setBounds(30, 80, 300, 25);

    r2 = new JRadioButton();
    r2.setBounds(30, 110, 300, 25);

    r3 = new JRadioButton();
    r3.setBounds(30, 140, 300, 25);

    r4 = new JRadioButton();
    r4.setBounds(30, 170, 300, 25);

    add(r1);
    add(r2);
    add(r3);
    add(r4);

    bg = new ButtonGroup();
    bg.add(r1);
    bg.add(r2);
    bg.add(r3);
    bg.add(r4);

    nextBtn = new JButton("Next");
    nextBtn.setBounds(220, 230, 100, 30);
    nextBtn.addActionListener(this);
    add(nextBtn);

    loadQuestion();

    setVisible(true);
}

public void loadQuestion() {

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM questions LIMIT 1 OFFSET ?");

        ps.setInt(1, currentQuestion);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            questionLabel.setText(
                    (currentQuestion + 1) + ". " +
                    rs.getString("question"));

            r1.setText(rs.getString("option1"));
            r2.setText(rs.getString("option2"));
            r3.setText(rs.getString("option3"));
            r4.setText(rs.getString("option4"));

            correctAnswers.add(
                    rs.getString("correct_answer"));

            bg.clearSelection();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void actionPerformed(ActionEvent e) {

    String selectedAnswer = "";

    if (r1.isSelected())
        selectedAnswer = r1.getText();

    else if (r2.isSelected())
        selectedAnswer = r2.getText();

    else if (r3.isSelected())
        selectedAnswer = r3.getText();

    else if (r4.isSelected())
        selectedAnswer = r4.getText();

    if (selectedAnswer.equals(
            correctAnswers.get(currentQuestion))) {

        score++;
    }

    currentQuestion++;

    if (currentQuestion < 15) {

        loadQuestion();

        if (currentQuestion == 14) {
            nextBtn.setText("Submit");
        }

    } else {

        JOptionPane.showMessageDialog(this,
                "Exam Completed!\nScore = "
                        + score + "/15");

        new ResultPage(score);

        dispose();
    }
}

}
