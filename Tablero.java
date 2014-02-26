package tetris;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import javax.swing.JPanel;

public class Tablero {
    private Color[][] mi_tablero;

    public Tablero() {
        mi_tablero = new Color[25][12];
    }

    public boolean permite_izq(Pieza p) {
        if(p.posX / 20 > 0) {
            //if(permite_abajo(p) == true) {
                for(int i=0;i<p.la_pieza.length;i++) {
                    if(mi_tablero[p.posY / 20 + i][p.posX / 20 - 1] != null && p.la_pieza[i][0] == true) {
                        return false;
                    }
                }

                return true;
            /*} else {
                return false;
            }*/
        } else {
            return false;
        }
    }

    public boolean permite_dcha(Pieza p) {
        if(p.posX / 20 < 12 - p.la_pieza[0].length) {
            //if(permite_abajo(p) == true) {
                for(int i=0;i<p.la_pieza.length;i++) {
                    if(mi_tablero[p.posY / 20 + i][p.posX / 20 + p.la_pieza[0].length] != null && p.la_pieza[i][p.la_pieza[0].length-1] == true) {
                        return false;
                    }
                }

                return true;
            /*} else {
                return false;
            }*/
        } else {
            return false;
        }
    }

    public boolean permite_abajo(Pieza p) {
        if(p.posY / 20 < 24 - p.la_pieza.length) {
            for(int i=0;i<p.la_pieza.length;i++) {
                for(int k=0;k<p.la_pieza[0].length;k++) {
                    if(mi_tablero[p.posY / 20 + i + 1][p.posX / 20 + k] != null && p.la_pieza[i][k] == true) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public void dejar_pieza(Pieza p, JPanel mainPanel) {
        for(int i=0;i<p.la_pieza.length;i++) {
            for(int k=0;k<p.la_pieza[0].length;k++) {
                if(p.la_pieza[i][k] == true) {
                    mi_tablero[p.posY / 20 + i][p.posX / 20 + k] = p.color_de_la_pieza;

                    Label cuadro = new Label();
                    cuadro.setPreferredSize(new Dimension(19,19));
                    cuadro.setSize(new Dimension(19,19));
                    cuadro.setLocation(p.posX + k*20, p.posY + i*20);
                    cuadro.setText("");
                    cuadro.setBackground(p.color_de_la_pieza);
                    mainPanel.add(cuadro);
                }
            }
        }

        mainPanel.remove(p);
    }

    public void hacer_tetris(JPanel mainPanel) {
        boolean hay_tetris;
        
        for(int i=24;i>=0;i--) {
            hay_tetris = true;
            
            for(int k=0;k<12;k++) {
                if(mi_tablero[i][k] == null) {
                    hay_tetris = false;
                }
            }

            if(hay_tetris == true) {
                eliminar_fila(i, mainPanel);
                bajar_filas(i, mainPanel);
            }
        }
    }

    public void eliminar_fila(int fila, JPanel mainPanel) {
        for(int i=0;i<12;i++) {
            try {
                mainPanel.remove(mainPanel.getComponentAt(i*20, fila*20));
                mi_tablero[fila][i] = null;
            } catch(Exception ex) { }
        }
    }

    public void bajar_filas(int fila, JPanel mainPanel) {
        Component c;
        
        for(int i=fila-1;i>=0;i--) {
            for(int k=0;k<12;k++) {
                try {
                    c = mainPanel.getComponentAt(k*20, i*20);
                    if(c instanceof Label) {
                        c.setLocation(c.getLocation().x, c.getLocation().y + 20);
                    }

                    mi_tablero[i+1][k] = mi_tablero[i][k];
                } catch(Exception ex) { }
            }
        }
    }
}
