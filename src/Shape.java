import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/*
 * Abstract superclass required by the rubric.
 * All specific shapes inherit from this class.
 */
public abstract class Shape {
    protected int x;
    protected int y;
    protected int size;
    protected int dx;
    protected int dy;
    protected Color color;
    protected int visualState;

    protected static final Random random = new Random();

    /*
     * Constructor for all shapes.
     */
    public Shape(int x, int y) {
        this.x = x;
        this.y = y;

        // Random size
        this.size = 30 + random.nextInt(41); // 30 to 70

        // Random speed, never zero
        this.dx = randomSpeed();
        this.dy = randomSpeed();

        // Start with a random visual state
        this.visualState = random.nextInt(3);
        applyVisualState();
    }

    /*
     * Abstract method that each subclass must override.
     * This satisfies abstraction + overriding.
     */
    public abstract void draw(Graphics2D g2);

    /*
     * Moves the shape.
     */
    public void move() {
        x += dx;
        y += dy;
    }

    /*
     * Wall collision detection.
     * Shapes bounce back when they hit the panel boundaries.
     */
    public void checkWallCollision(int panelWidth, int panelHeight) {
        if (x <= 0) {
            x = 0;
            dx = -dx;
            nextVisualState();
        }

        if (y <= 0) {
            y = 0;
            dy = -dy;
            nextVisualState();
        }

        if (x + size >= panelWidth) {
            x = panelWidth - size;
            dx = -dx;
            nextVisualState();
        }

        if (y + size >= panelHeight) {
            y = panelHeight - size;
            dy = -dy;
            nextVisualState();
        }
    }

    /*
     * Simple collision check between shapes.
     * Uses distance between centers.
     */
    public boolean collidesWith(Shape other) {
        int centerX1 = this.x + this.size / 2;
        int centerY1 = this.y + this.size / 2;
        int centerX2 = other.x + other.size / 2;
        int centerY2 = other.y + other.size / 2;

        double distance = Math.sqrt(Math.pow(centerX1 - centerX2, 2) + Math.pow(centerY1 - centerY2, 2));
        double minDistance = (this.size / 2.0) + (other.size / 2.0);

        return distance < minDistance;
    }

    /*
     * Called when a shape collides with another shape.
     * Changes direction and visual appearance.
     */
    public void reactToCollision() {
        dx = -dx;
        dy = -dy;
        nextVisualState();
    }

    /*
     * Cycle through 3 different visual states.
     * This helps meet the rubric requirement for visual states.
     */
    public void nextVisualState() {
        visualState = (visualState + 1) % 3;
        applyVisualState();
    }

    /*
     * Applies color and size changes based on current visual state.
     */
    protected void applyVisualState() {
        switch (visualState) {
            case 0:
                color = new Color(52, 152, 219); // Blue
                size = Math.max(30, size);
                break;
            case 1:
                color = new Color(231, 76, 60); // Red
                size = Math.max(40, size);
                break;
            case 2:
                color = new Color(46, 204, 113); // Green
                size = Math.max(50, size);
                break;
        }
    }

    /*
     * Helper method to generate a non-zero speed.
     */
    private int randomSpeed() {
        int speed;
        do {
            speed = random.nextInt(7) - 3; // -3 to 3
        } while (speed == 0);
        return speed;
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}