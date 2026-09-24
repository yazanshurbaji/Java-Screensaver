import java.awt.Graphics2D;
import java.awt.Polygon;

/*
 * Triangle shape subclass.
 */
public class TriangleShape extends Shape {

    public TriangleShape(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2) {
        int[] xPoints = {x + size / 2, x, x + size};
        int[] yPoints = {y, y + size, y + size};

        Polygon triangle = new Polygon(xPoints, yPoints, 3);

        g2.setColor(color);
        g2.fillPolygon(triangle);
    }
}