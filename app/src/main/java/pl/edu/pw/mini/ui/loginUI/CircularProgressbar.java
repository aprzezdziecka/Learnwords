package pl.edu.pw.mini.ui.loginUI;
import javax.swing.*;
import java.awt.*;
public class CircularProgressbar{

    public static class CircularProgressChart extends JPanel {
        private int correctAnswers;
        private int totalAnswers;

        public CircularProgressChart(int correctAnswers, int totalAnswers) {
            this.correctAnswers = correctAnswers;
            this.totalAnswers = totalAnswers;
            setPreferredSize(new Dimension(200, 200));
            setBackground(new Color(249, 249, 249));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int percentage = (int) ((double) correctAnswers / totalAnswers * 100);

            int diameter = Math.min(getWidth(), getHeight()) - 20;
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(x, y, diameter, diameter);

            g2d.setColor(new Color(255, 235, 141));
            int angle = (int) (360.0 * correctAnswers / totalAnswers);
            g2d.fillArc(x, y, diameter, diameter, 90, -angle);

            int innerDiameter = diameter - 40;
            int innerX = (getWidth() - innerDiameter) / 2;
            int innerY = (getHeight() - innerDiameter) / 2;
            g2d.setColor(new Color(249, 249, 249));
            g2d.fillOval(innerX, innerY, innerDiameter, innerDiameter);

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 20));
            String text = percentage + "%";
            FontMetrics fm = g2d.getFontMetrics();
            int textX = getWidth() / 2 - fm.stringWidth(text) / 2;
            int textY = getHeight() / 2 + fm.getAscent() / 2;
            g2d.drawString(text, textX, textY);
        }

    }

}
