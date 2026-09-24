import javax.swing.JFrame;

/*
 * This class creates the main application window.
 */
public class ScreensaverFrame extends JFrame {

    public ScreensaverFrame() {
        setTitle("Assignment 4 - Screensaver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null); // Center the window on screen
        add(new ScreensaverPanel());
        setVisible(true);
    }
}