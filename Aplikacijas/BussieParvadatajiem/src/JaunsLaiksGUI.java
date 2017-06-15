import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.10.5
 * Time: 18:34
 * To change this template use File | Settings | File Templates.
 */
public class JaunsLaiksGUI extends JFrame implements ActionListener{
    private JLabel virsraksts2;
    private JLabel virsraksts;
    Connection conn=null;
    JLabel l3,l4;
    JTextField cikos, lidzCikiem;
    JComboBox comboBox;
    private JButton okBtn;
    private JButton backBtn;
    JPanel panel;
    ArrayList<String>ids = new ArrayList<String>();
    Vector<String> nosaukumi = new Vector<String>();

    public JaunsLaiksGUI(Connection conn) throws HeadlessException {
        this.conn = conn;
        this.setLayout(null);
        this.setBounds(50, 50, 400, 200);

        panel = new JPanel(null);
        panel.setBounds(0, 0,400, 120);
        this.add(panel);

        l3 = new JLabel("Laiks, cikos atiet autobuss: (hh:mm:ss) ");
        l4 = new JLabel("Laiks, cikos pienāk autobuss: (hh:mm:ss) ");

        l3.setBounds(0, 3 * panel.getHeight() / 5, 2 * panel.getWidth() / 3, panel.getHeight() / 5);
        l4.setBounds(0, 4 * panel.getHeight() / 5, 2 * panel.getWidth() / 3, panel.getHeight() / 5);

        panel.add(l3);
        panel.add(l4);

        cikos = new JTextField();
        lidzCikiem = new JTextField();

        cikos.setBounds((2 * panel.getWidth() / 3) - 15, 3 * panel.getHeight() / 5, panel.getWidth() / 3, panel.getHeight() / 5);
        lidzCikiem.setBounds((2 * panel.getWidth() / 3) - 15, 4 * panel.getHeight() / 5, panel.getWidth() / 3, panel.getHeight() / 5);

        panel.add(cikos);
        panel.add(lidzCikiem);

        okBtn = new JButton("PIEVIENOT LAIKU");
        okBtn.setBounds(5, 125, 180, 30);
        okBtn.addActionListener(this);
        this.add(okBtn);

        backBtn = new JButton("ATPAKAĻ UZ SĀKUMU");
        backBtn.setBounds(195, 125, 180, 30);
        backBtn.addActionListener(this);
        this.add(backBtn);

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Marsruts;");
            while(rs.next()){
                nosaukumi.add(rs.getObject(2).toString());
                ids.add(rs.getObject(1).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboBox = new JComboBox(nosaukumi);
        comboBox.setBounds(panel.getWidth() / 3, 2 * panel.getHeight() / 5-10, 2 * panel.getWidth() / 3-20, panel.getHeight() / 5);
        panel.add(comboBox);

        virsraksts = new JLabel("JAUNA LAIKA PIEVIENOŠANA");
        virsraksts.setBounds(0, panel.getHeight() / 5-10, 2 * panel.getWidth() / 3, panel.getHeight() / 5);
        panel.add(virsraksts);

        virsraksts2 = new JLabel("Izvēlies maršrutu ");
        virsraksts2.setBounds(0, 2*panel.getHeight() / 5-10, panel.getWidth() / 3, panel.getHeight() / 5);
        panel.add(virsraksts2);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();
        if(o.equals(okBtn)){
            this.setVisible(false);
            String id = "";
            id = ids.get(comboBox.getSelectedIndex());
            try {
                Statement st = conn.createStatement();
                st.executeUpdate("INSERT INTO Laiks VALUES (NULL,'"+id+"','"+cikos.getText()+"','"+lidzCikiem.getText()+"')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(o.equals(backBtn)){
            this.setVisible(false);
        }
    }
}
