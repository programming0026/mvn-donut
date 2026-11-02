package com.juniverse.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Donut extends JPanel implements ActionListener {

    private double angleA = 0;
    private double angleB = 0;
    private final Timer timer;

    /** Crea el panel e inicia la animación (un fotograma cada 30 ms). */
    public Donut() {
        timer = new Timer(30, this);
        timer.start();
    }

    /** Dibuja el fotograma actual. */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderDonut((Graphics2D) g);
    }

    /**
     * Recorre la superficie del toro (ángulos theta y phi), la rota, la proyecta a 2D y pinta cada punto
     * con un gris según su iluminación.
     * @param g2d contexto gráfico
     */
    private void renderDonut(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        double R1 = 1;  // radio menor
        double R2 = 2;  // radio mayor
        double K2 = 5;  // distancia del observador
        double K1 = getWidth() * K2 * 3 / (8 * (R1 + R2));  // escala

        int width = getWidth();
        int height = getHeight();
 
        double[] zBuffer = new double[width * height];
        double cosA = Math.cos(angleA), sinA = Math.sin(angleA);
        double cosB = Math.cos(angleB), sinB = Math.sin(angleB);

        for (double theta = 0; theta < 2 * Math.PI; theta += 0.07) {
            for (double phi = 0; phi < 2 * Math.PI; phi += 0.02) {
                double cosTheta = Math.cos(theta), sinTheta = Math.sin(theta);
                double cosPhi = Math.cos(phi), sinPhi = Math.sin(phi);

                double circleX = R2 + R1 * cosTheta;
                double circleY = R1 * sinTheta;

                double x = circleX * (cosB * cosPhi + sinA * sinB * sinPhi) - circleY * cosA * sinB;
                double y = circleX * (sinB * cosPhi - sinA * cosB * sinPhi) + circleY * cosA * cosB;
                double z = K2 + cosA * circleX * sinPhi + circleY * sinA;
                double ooz = 1 / z;

                int xp = (int) (getWidth() / 2 + K1 * ooz * x);
                int yp = (int) (getHeight() / 2 - K1 * ooz * y);

                if (xp < 0 || xp >= width - 1 || yp < 0 || yp >= height - 1 || ooz <= zBuffer[yp * width + xp]) {
                    continue;
                }
                zBuffer[yp * width + xp] = ooz;


                float luminance = (float) ((cosPhi * cosTheta * sinB - cosA * cosTheta * sinPhi - sinA * sinTheta + cosB * (cosA * sinTheta - cosTheta * sinA * sinPhi)));
                luminance = Math.max(0, Math.min(1, (float) ((luminance + 1.5) / 3.0)));

                Color color = new Color(luminance, luminance, luminance);
                g2d.setColor(color);
                g2d.fillRect(xp, yp, 2, 2);
            }
        }
    }

    /** Avanza los ángulos de rotación y vuelve a dibujar. */
    @Override
    public void actionPerformed(ActionEvent e) {
        angleA += 0.03;
        angleB += 0.02;
        repaint();
    }

}
