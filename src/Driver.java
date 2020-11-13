import javax.swing.*;
import java.awt.*;

public class Driver extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater( new Runnable() {
            public void run() {
                try {
                    admin_control_panel frame = admin_control_panel.getInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
