package tetris;

import java.awt.Color;
import java.util.Date;
import java.util.Random;
import javax.swing.JPanel;

public class Pieza extends JPanel {
    Color color_de_la_pieza;
    int forma;
    boolean[][] la_pieza;
    int posX;
    int posY;

    public Pieza() {
        Date d = new Date();
        Random r = new Random(d.getTime());

        switch(r.nextInt(7) + 1) {
            case 1:
                crear_pieza(r.nextInt(7) + 1, Color.CYAN);
                break;
            case 2:
                crear_pieza(r.nextInt(7) + 1, Color.BLUE);
                break;
            case 3:
                crear_pieza(r.nextInt(7) + 1, Color.ORANGE);
                break;
            case 4:
                crear_pieza(r.nextInt(7) + 1, Color.YELLOW);
                break;
            case 5:
                crear_pieza(r.nextInt(7) + 1, Color.GREEN);
                break;
            case 6:
                crear_pieza(r.nextInt(7) + 1, Color.MAGENTA);
                break;
            case 7:
                crear_pieza(r.nextInt(7) + 1, Color.RED);
                break;
        }
        
    }

    public Pieza(int f, Color mi_color) {
        crear_pieza(f, mi_color);
    }

    private void crear_pieza(int f, Color mi_color) {
        color_de_la_pieza = mi_color;
        forma = f;
        switch(forma) {
            //****
            case 1:
                la_pieza = new boolean[1][4];
                for(int i=0;i<4;i++) {
                    la_pieza[0][i] = true;
                }
                break;
            //*--
            //***
            case 2:
                la_pieza = new boolean[2][3];
                la_pieza[0][0] = true;
                for(int i=0;i<3;i++) {
                    la_pieza[1][i] = true;
                }
                break;
            //--*
            //***
            case 3:
                la_pieza = new boolean[2][3];
                la_pieza[0][2] = true;
                for(int i=0;i<3;i++) {
                    la_pieza[1][i] = true;
                }
                break;
            //**
            //**
            case 4:
                la_pieza = new boolean[2][2];
                for(int i=0;i<2;i++) {
                    la_pieza[0][i] = true;
                }
                for(int i=0;i<2;i++) {
                    la_pieza[1][i] = true;
                }
                break;
            //-**
            //**-
            case 5:
                la_pieza = new boolean[2][3];
                for(int i=1;i<3;i++) {
                    la_pieza[0][i] = true;
                }
                for(int i=0;i<2;i++) {
                    la_pieza[1][i] = true;
                }
                break;
            //-*-
            //***
            case 6:
                la_pieza = new boolean[2][3];
                la_pieza[0][1] = true;
                for(int i=0;i<3;i++) {
                    la_pieza[1][i] = true;
                }
                break;
            //**-
            //-**
            case 7:
                la_pieza = new boolean[2][3];
                for(int i=0;i<2;i++) {
                    la_pieza[0][i] = true;
                }
                for(int i=1;i<3;i++) {
                    la_pieza[1][i] = true;
                }
                break;

        }

        this.setLayout(null);
        this.setOpaque(false);
    }

    public void rotar(Tablero tablero) {
        boolean[][] la_nueva_pieza = new boolean[la_pieza[0].length][la_pieza.length];
        for(int i=0;i<la_pieza.length;i++) {
            for(int k=0;k<la_pieza[0].length;k++) {
                la_nueva_pieza[k][i] = la_pieza[la_pieza.length - i - 1][k];
            }
        }

        if(posX / 20 + la_nueva_pieza[0].length > 12) {
            posX = 12 * 20 - la_nueva_pieza[0].length * 20;
        }

        if(posY / 20 + la_nueva_pieza.length > 24) {
            posY = 24 * 20 - la_nueva_pieza.length * 20;
        }

        la_pieza = la_nueva_pieza;
    }
}
