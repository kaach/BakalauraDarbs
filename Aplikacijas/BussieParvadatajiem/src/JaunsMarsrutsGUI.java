import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.3.4
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class JaunsMarsrutsGUI extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton ok;
    private JButton back;
    private JLabel virsraksts, l1, l2, l3, l4;
    private JTextField no, lidz, cikos, lidzCikiem;
    private Statement statement;
    private Connection conn = null;

    public JaunsMarsrutsGUI(Connection conn) {
        this.conn = conn;
        this.setLayout(null);
        this.setBounds(50, 50, 400, 200);

        panel = new JPanel(null);
        panel.setBounds(0, 0, this.getWidth() - 10, 3 * this.getHeight() / 5);
        this.add(panel);

        virsraksts = new JLabel("JAUNA MARŠRUTA PIEVIENOŠANA");
        l1 = new JLabel("Vieta, no kurienes atiet maršruts:");
        l2 = new JLabel("Vieta, līdz kurienei ir maršruts:");

        virsraksts.setBounds(0, 0, 2 * panel.getWidth() / 3, panel.getHeight() / 5);
        l1.setBounds(0, panel.getHeight() / 5, 2 * panel.getWidth() / 3, panel.getHeight() / 5);
        l2.setBounds(0, 2 * panel.getHeight() / 5, 2 * panel.getWidth() / 3, panel.getHeight() / 5);

        panel.add(virsraksts);
        panel.add(l1);
        panel.add(l2);

        no = new JTextField();
        lidz = new JTextField();

        no.setBounds((2 * panel.getWidth() / 3) - 15, panel.getHeight() / 5, panel.getWidth() / 3, panel.getHeight() / 5);
        lidz.setBounds((2 * panel.getWidth() / 3) - 15, 2 * panel.getHeight() / 5, panel.getWidth() / 3, panel.getHeight() / 5);

        panel.add(no);
        panel.add(lidz);

        ok = new JButton("JAUNS MARŠRUTS");
        back = new JButton("ATPAKAĻ UZ SĀKUMU");

        ok.setBounds(0, (3 * this.getHeight() / 5) + 5, this.getWidth() / 2 - 3, this.getHeight() / 5 - 5);
        back.setBounds(this.getWidth() / 2 - 3, (3 * this.getHeight() / 5) + 5, this.getWidth() / 2 - 18, this.getHeight() / 5 - 5);

        ok.addActionListener(this);
        back.addActionListener(this);

        this.add(ok);
        this.add(back);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();
        if (o.equals(ok)) {
            this.setVisible(false);
            try {
                String sql = "INSERT INTO Marsruts (nosaukums) VALUES ('" + no.getText() + " - " + lidz.getText() +"')";
                statement = conn.createStatement();
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Pievienots ieraksts");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (o.equals(back)) {
            this.setVisible(false);
        }
    }
}
