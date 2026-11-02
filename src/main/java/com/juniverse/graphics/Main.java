package com.juniverse.graphics;

import javax.swing.JFrame;

/**
 * Abre la ventana con el donut animado.
 *
 * @author ajuar
 */
public class Main {

    /**
     * Punto de entrada.
     * @param args no se usan
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Donut 3D con Java 2D");
        Donut panel = new Donut();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
