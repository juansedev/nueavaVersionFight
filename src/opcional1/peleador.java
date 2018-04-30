package opcional1;

import jaco.mp3.player.MP3Player;
import java.awt.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class peleador extends Thread {

    int coorx, coory;
    boolean golpe_Puno = false, golpe_Patada = false, finaliza = false;
    static int dir;
    int vida = 10;

    ImageIcon pj = null;
    ImageIcon pow = new ImageIcon(getClass().getResource("/Imagenes/powsn.png"));
    ImageIcon fin = new ImageIcon(getClass().getResource("/Imagenes/gameover.png"));
    ImageIcon v1 = new ImageIcon(getClass().getResource("/Imagenes/cora.png"));
    ImageIcon v2 = new ImageIcon(getClass().getResource("/Imagenes/cora.png"));
    ImageIcon v3 = new ImageIcon(getClass().getResource("/Imagenes/cora.png"));
    
    
    public String golpe1 = "D:\\Github\\Opcional1\\src\\sound\\golpe1.mp3";
    
    public String fallo = "D:\\Github\\Opcional1\\src\\sound\\fallo.mp3";
    MP3Player musicFallo = new MP3Player(new File(fallo));   
    MP3Player musicGolpe1 = new MP3Player(new File(golpe1));    
       
    public peleador(int x, int y, int direccion) {
        coorx = x;
        coory = y;
        dir = direccion;
    }

    public void mueve(int avanza) {
        this.dir = avanza;
    }

    public void actualizaVida(int golpe) {
        vida = vida - golpe;
    }

    public int getX() {
        return coorx;
    }

    public int getVida() {
        return vida;
    }

    public boolean getGolpePuno() {
        return golpe_Puno;
    }

    public boolean getGolpePatada() {
        return golpe_Patada;
    }

    public void validar() {
        if (coorx <= 60) {
            coorx = coorx + 20;
        } else if (coorx >= 800) {
            coorx = coorx - 20;
        }
    }

    public void paint(Graphics g) {

        if (vida > 0) {
            pj = new ImageIcon(getClass().getResource("/Imagenes/peleador1_1.png"));

        } else {
            pj = null;
        }
        validar();
        switch (dir) {
            case 1:
                pj = new ImageIcon(getClass().getResource("/Imagenes/peleador1_1.png"));
                coorx = coorx + 20;
                dir = 0;
                break;

            case 2:
                pj = new ImageIcon(getClass().getResource("/Imagenes/peleador1_1.png"));
                coorx = coorx - 20;
                dir = 0;
                break;

            case 3:
                coorx = coorx;
                coory = coory - 30;
                pj = new ImageIcon(getClass().getResource("/Imagenes/puno_1.png"));
                musicFallo.play();
                golpe_Puno = true;
                 /*{
                    try {
                   
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(peleador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }*/

                dir = 0;
                break;

            case 4:
                coorx = coorx;
                musicFallo.play();
                golpe_Patada = true;
                pj = new ImageIcon(getClass().getResource("/Imagenes/patada_1.png"));
                 /*{
                    try {
                        pj = new ImageIcon(getClass().getResource("/Imagenes/patada_1.png"));
                        Thread.sleep(200);

                    } catch (InterruptedException ex) {                
                    }
                }*/
                
                dir = 0;
                break;

            case 5:
                coorx = coorx - 100;
                musicGolpe1.play();
                pj = new ImageIcon(getClass().getResource("/Imagenes/caido_1.png"));
                g.drawImage(pow.getImage(), 750, 400, null);
                if (vida == 7) {                    
                    v1 = new ImageIcon(getClass().getResource("/Imagenes/cora_vacio.png"));
                } else if (vida== 5) {                    
                    v2 = new ImageIcon(getClass().getResource("/Imagenes/cora_vacio.png"));
                } else if (vida < 1) {                    
                    v3 = new ImageIcon(getClass().getResource("/Imagenes/cora_vacio.png"));
                    finaliza=true;
                }
                vida--;
                dir = 0;
                break;
        }

        if (finaliza == false) {
            g.drawImage(v1.getImage(), 100, 50, null);
            g.drawImage(v2.getImage(), 150, 50, null);
            g.drawImage(v3.getImage(), 200, 50, null);

            try {
                g.drawImage(pj.getImage(), coorx, coory, null);
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(peleador.class.getName()).log(Level.SEVERE, null, ex);
            }
            golpe_Patada = false;
            golpe_Puno = false;
            coory = 300;
        } else System.out.println("Juego Finalizado: Ganador Jugador 2");
        /*else {
            g.drawImage(fin.getImage(), 0, 0, null);
        }*/

    }
}
