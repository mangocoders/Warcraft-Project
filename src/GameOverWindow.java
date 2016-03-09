import javax.swing.*;
import java.awt.*;

/**
 * Created by Torab on 05-Mar-16.
 */
public class GameOverWindow extends JPanel {


        public static void popUpWindow(){


            JFrame jf = new JFrame();
            jf.setSize(600,200);
            jf.setLocation(350,200);
            jf.setTitle("Dead ");

            JLabel label;
            label = new JLabel("Sorry,You are Dead");
            label.setFont(new Font("Serif", Font.BOLD, 16));

            jf.add(label);
            jf.getDefaultCloseOperation();
            jf.setVisible(true);

        }



}
