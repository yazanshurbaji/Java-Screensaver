import java.awt.Graphics2D;

/*
 * Square shape subclass.
 */
public class SquareShape extends Shape {

    public SquareShape(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x, y, size, size);
    }
}