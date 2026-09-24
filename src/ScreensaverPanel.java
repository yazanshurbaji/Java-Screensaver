import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/*
 * This panel handles:
 * - storing shapes in an ArrayList
 * - drawing all shapes
 * - animation using a Timer
 * - mouse click creation of random shapes
 * - wall collision
 * - shape-to-shape collision
 */
public class ScreensaverPanel extends JPanel implements ActionListener {

    private ArrayList<Shape> shapes;
    private Timer timer;
    private Random random;

    public ScreensaverPanel() {
        shapes = new ArrayList<>();
        random = new Random();

        setBackground(Color.BLACK);

        // Timer used for animation
        timer = new Timer(20, this);
        timer.start();

        /*
         * Add a new random shape whenever the user clicks.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addRandomShape(e.getX(), e.getY());
            }
        });
    }

    /*
     * Creates one of four random shape types.
     */
    private void addRandomShape(int mouseX, int mouseY) {
        int type = random.nextInt(4);

        Shape newShape;

        switch (type) {
            case 0:
                newShape = new CircleShape(mouseX, mouseY);
                break;
            case 1:
                newShape = new SquareShape(mouseX, mouseY);
                break;
            case 2:
                newShape = new TriangleShape(mouseX, mouseY);
                break;
            default:
                newShape = new StarShape(mouseX, mouseY);
                break;
        }

        shapes.add(newShape);
        repaint();
    }

    /*
     * Draw all shapes on the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Smooth drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Shape shape : shapes) {
            shape.draw(g2);
        }
    }

    /*
     * Timer event:
     * - move shapes
     * - check wall collisions
     * - check collisions between shapes
     * - repaint the panel
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Move and check wall collisions
        for (Shape shape : shapes) {
            shape.move();
            shape.checkWallCollision(panelWidth, panelHeight);
        }

        // Check shape-to-shape collisions
        for (int i = 0; i < shapes.size(); i++) {
            for (int j = i + 1; j < shapes.size(); j++) {
                Shape s1 = shapes.get(i);
                Shape s2 = shapes.get(j);

                if (s1.collidesWith(s2)) {
                    s1.reactToCollision();
                    s2.reactToCollision();
                }
            }
        }

        repaint();
    }
}