import java.awt.Graphics2D;

/*
 * Circle shape subclass.
 */
public class CircleShape extends Shape {

    public CircleShape(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval(x, y, size, size);
    }
}