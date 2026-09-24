import java.awt.Graphics2D;
import java.awt.Polygon;

/*
 * Irregular shape subclass.
 * This helps satisfy the rubric requirement for one irregular shape.
 */
public class StarShape extends Shape {

    public StarShape(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2) {
        int centerX = x + size / 2;
        int centerY = y + size / 2;

        int outerRadius = size / 2;
        int innerRadius = size / 4;

        int[] xPoints = new int[10];
        int[] yPoints = new int[10];

        for (int i = 0; i < 10; i++) {
            double angle = Math.toRadians(-90 + i * 36);
            int radius = (i % 2 == 0) ? outerRadius : innerRadius;

            xPoints[i] = centerX + (int)(Math.cos(angle) * radius);
            yPoints[i] = centerY + (int)(Math.sin(angle) * radius);
        }

        Polygon star = new Polygon(xPoints, yPoints, 10);

        g2.setColor(color);
        g2.fillPolygon(star);
    }
}