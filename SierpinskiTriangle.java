import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


public class SierpinskiTriangle extends JPanel {
    private int steps;

    public SierpinskiTriangle(int steps) {
        this.steps = steps;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point p1 = new Point(getWidth() / 2, 0);
        Point p2 = new Point(0, getHeight());
        Point p3 = new Point(getWidth(), getHeight());

        drawTriangle(g, p1, p2, p3, steps);
    }

    private void drawTriangle(Graphics g, Point p1, Point p2, Point p3, int steps) {
        if (steps == 0) {
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
            g.drawLine(p2.x, p2.y, p3.x, p3.y);
            g.drawLine(p3.x, p3.y, p1.x, p1.y);
        } else {
            Point p4 = midpoint(p1, p2);
            Point p5 = midpoint(p2, p3);
            Point p6 = midpoint(p3, p1);

            drawTriangle(g, p1, p4, p6, steps - 1);
            drawTriangle(g, p4, p2, p5, steps - 1);
            drawTriangle(g, p6, p5, p3, steps - 1);
        }
    }

    private Point midpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of steps: ");
        int steps = input.nextInt();

        JFrame frame = new JFrame("Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SierpinskiTriangle(steps));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(600, 600);
    }
}
