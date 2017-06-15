import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.6.6
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class StartGUI extends JFrame implements ActionListener{
    private JTextField textField1;
    private JPanel panel1;
    private JButton saktButton;
    private Connection conn;
    private Statement statement=null;
    private int reissId = 0;
    public static int delay = 30000;
    private Calendar cal = null;
    int h,m,s, i=0;
    String hh,mm,ss,date="";
    double lat, lon;
    public double[] latList = {57.179837,57.183047,57.185047};
    public double[] longList = {22.639228,22.630130,22.620517};


    public StartGUI(Connection connection){
        this.setBounds(0,0,150,80);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        saktButton.addActionListener(this);
        this.add(panel1);
        panel1.add(textField1);
        panel1.add(saktButton);
        conn = connection;


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();
        if(o.equals(saktButton)){
            reissId=Integer.parseInt(textField1.getText());
            date = createTime();
            String query = "INSERT INTO Punkts (reiss_id, latitude, longitude, laiks) VALUES ('"+reissId+"',57.176325, 22.646781,'"+date+"')";
            try {
                statement = conn.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                date = createTime();
                lat=latList[i];
                lon=longList[i];
                i++;
                String query = "INSERT INTO Punkts (reiss_id, latitude, longitude, laiks) VALUES ('"+reissId+"',"+lat+","+lon+",'"+date+"')";
                try {
                    statement = conn.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }


                }
            };
            new javax.swing.Timer(delay, taskPerformer).start();
        }
    }

    public String createTime(){
        cal = Calendar.getInstance();
        h=cal.get(Calendar.HOUR_OF_DAY);
        m=cal.get(Calendar.MINUTE);
        s=cal.get(Calendar.SECOND);
        if(h<10)hh="0"+h;
        else hh=""+h;
        if(m<10)mm="0"+m;
        else mm=""+m;
        if(s<10)ss="0"+s;
        else ss=""+s;
        String time = hh+":"+mm+":"+ss;
        return time;
    }
}
