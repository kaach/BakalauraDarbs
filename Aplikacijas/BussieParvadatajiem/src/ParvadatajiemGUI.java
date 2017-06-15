/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.1.4
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ParvadatajiemGUI extends JFrame implements ActionListener {
    private JPanel panel1;
    private JButton jaunsSoferis;
    private JButton jaunsMarsruts;
    private JButton jaunsLaiks;
    private Connection conn = null;

    public ParvadatajiemGUI(Connection conn) {
        this.setLayout(null);
        this.setBounds(50, 50, 400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1 = new JPanel(null);
        panel1.setBounds(0, 30, 400, 120);
        this.add(panel1);

        jaunsSoferis = new JButton("PIEVIENOT JAUNU ŠOFERI");
        jaunsSoferis.setBounds(10, 10, 360, 30);
        jaunsSoferis.addActionListener(this);
        panel1.add(jaunsSoferis);

        jaunsMarsruts = new JButton("PIEVIENOT JAUNU MARŠRUTU");
        jaunsMarsruts.setBounds(10, 50, 360, 30);
        jaunsMarsruts.addActionListener(this);
        panel1.add(jaunsMarsruts);

        jaunsLaiks = new JButton("PIEVIENOT JAUNU LAIKU MARŠRUTAM");
        jaunsLaiks.setBounds(10, 90, 360, 30);
        jaunsLaiks.addActionListener(this);
        panel1.add(jaunsLaiks);

        this.conn = conn;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();
        if (o.equals(jaunsSoferis)) {
            JaunsSoferisGUI jaunsSofGUI = new JaunsSoferisGUI(conn);
            jaunsSofGUI.setVisible(true);
        }
        if (o.equals(jaunsMarsruts)) {
            JaunsMarsrutsGUI jaunsMarsGUI = new JaunsMarsrutsGUI(conn);
            jaunsMarsGUI.setVisible(true);
        }
        if(o.equals(jaunsLaiks)){
            JaunsLaiksGUI jaunsLaiksGUI= new JaunsLaiksGUI(conn);
            jaunsLaiksGUI.setVisible(true);
        }
    }


}
