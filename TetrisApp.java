/*
 * TetrisApp.java
 */

package tetris;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.Timer;
import org.jdesktop.application.SingleFrameApplication;

public class TetrisApp extends SingleFrameApplication {
    TetrisView ventana;
    Tablero tablero;
    Pieza pieza_actual;
    Timer timer;
    boolean jugando = true;

    @Override protected void startup() {
        //Crea la ventana
        ventana = new TetrisView(this);

        //Creamos el tablero
        tablero = new Tablero();

        //Creamos la primera pieza que va a caer (actual)
        pieza_actual = new Pieza();
        pieza_actual.posX = 100;
        pieza_actual.posY = 0;
        pieza_actual = ventana.dibujar_pieza(pieza_actual);
        
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pieza_actual.setLocation(pieza_actual.posX, pieza_actual.posY);
                if(tablero.permite_abajo(pieza_actual) == true) {
                    pieza_actual.posY = pieza_actual.posY + 20;
                } else {
                    tablero.dejar_pieza(pieza_actual, ventana.mainPanel);
                    tablero.hacer_tetris(ventana.mainPanel);
                    pieza_actual = new Pieza();
                    pieza_actual.posX = 100;
                    pieza_actual.posY = 0;
                    pieza_actual = ventana.dibujar_pieza(pieza_actual);
                    if(tablero.permite_abajo(pieza_actual) == false) {
                        jugando = false;
                        timer.stop();
                        Component c[] = ventana.mainPanel.getComponents();
                        for(int i=0;i<c.length;i++) {
                            if(c[i] instanceof Label && c[i].getLocation().y < 142) {
                                ventana.mainPanel.remove(c[i]);
                            }
                        }
                        ventana.mainPanel.remove(pieza_actual);
                        ventana.perdido.setVisible(true);
                    }
                }
            }
        });
        timer.start();

        ventana.getFrame().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(jugando == true) {
                    if(evt.getKeyCode() == evt.VK_UP) {
                        pieza_actual.rotar(tablero);
                        ventana.dibujar_pieza(pieza_actual);
                    } else if(evt.getKeyCode() == evt.VK_LEFT) {
                        if(tablero.permite_izq(pieza_actual)) {
                            pieza_actual.posX = pieza_actual.posX - 20;
                            ventana.dibujar_pieza(pieza_actual);
                        }
                    } else if(evt.getKeyCode() == evt.VK_RIGHT) {
                        if(tablero.permite_dcha(pieza_actual)) {
                            pieza_actual.posX = pieza_actual.posX + 20;
                            ventana.dibujar_pieza(pieza_actual);
                        }
                    }
                }
            }
        });

        //Muestra la ventana
        show(ventana);
    }
    
    public static void main(String[] args) {
        launch(TetrisApp.class, args);
    }
}
