import javax.swing.*;
import java.awt.*;

/**
 * Created by Torab on 08-Mar-16.
 */
public class FirstStageComplete {

    public static void popUpWindow(){


        JFrame jf = new JFrame();
        jf.setSize(600,200);
        jf.setLocation(350,200);
        jf.setTitle("Congratulation :D ");

        JLabel label;
        label = new JLabel("YOU HAVE SUCCESSFULLY COMPLETED THE FIRST STAGE ");
        label.setFont(new Font("Serif", Font.BOLD, 16));

        jf.add(label);
        jf.getDefaultCloseOperation();
        jf.setVisible(true);

    }
}
