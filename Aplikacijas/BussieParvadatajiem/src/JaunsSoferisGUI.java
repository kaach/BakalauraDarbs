

import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.1.4
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class JaunsSoferisGUI extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JButton okBtn;
    private JButton backBtn;
    private JLabel lbl1;
    private JLabel lbl2;
    private JTextField pass;
    Statement statement = null;
    Connection conn = null;

    public JaunsSoferisGUI(Connection conn) {
        this.setLayout(null);
        this.setBounds(50, 50, 400, 200);
        this.conn = conn;

        mainPanel = new JPanel(null);
        mainPanel.setBounds(0, 0, 380, 125);
        this.add(mainPanel);

        lbl1 = new JLabel("JAUNA ŠOFERA PIEVIENOŠANAS FORMA");
        lbl1.setBounds(5, 5, 370, 30);
        mainPanel.add(lbl1);

        lbl2 = new JLabel("Ievadiet jaunā šofera paroli");
        lbl2.setBounds(5, 40, 370, 30);
        mainPanel.add(lbl2);

        pass = new JTextField();
        pass.setBounds(5, 75, 200, 30);
        mainPanel.add(pass);

        okBtn = new JButton("PIEVIENOT JAUNU ŠOFERI");
        okBtn.setBounds(5, 125, 180, 30);
        okBtn.addActionListener(this);
        this.add(okBtn);

        backBtn = new JButton("ATPAKAĻ UZ SĀKUMU");
        backBtn.setBounds(195, 125, 180, 30);
        backBtn.addActionListener(this);
        this.add(backBtn);


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();
        if (o.equals(okBtn)) {
            this.setVisible(false);
            try {
                String sql = "INSERT INTO Soferis (parole) VALUES ('" + pass.getText() + "')";
                statement = conn.createStatement();
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Pievienots ieraksts");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (o.equals(backBtn)) {
            this.setVisible(false);
        }
    }
}
