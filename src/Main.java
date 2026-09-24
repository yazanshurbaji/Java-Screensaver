import javax.swing.SwingUtilities;

/*
 * Main class to start the program.
 * This launches the GUI safely using SwingUtilities.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ScreensaverFrame();
        });
    }
}