package opcional1;

import jaco.mp3.player.MP3Player;
import java.awt.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class peleador2 extends Thread {

    int coorx, coory;
    boolean golpe_Puno = false, golpe_Patada = false, finaliza = false;
    static int dir;
    int vida = 10;

    ImageIcon pj = null;

    ImageIcon pow = new ImageIcon(getClass().getResource("/Imagenes/powsn.png"));
    ImageIcon noVidaImg = new ImageIcon(getClass().getResource("/Imagenes/cora_vacio.png"));
    ImageIcon fin = new ImageIcon(getClass().getResource("/Imagenes/gameover.png"));
    ImageIcon v1 = new ImageIcon(getClass().getResource("/Imagenes/cora.png"));
    ImageIcon v2 = new ImageIcon(getClass().getResource("/Imagenes/cora.png"));
    ImageIcon v3 = new ImageIcon(getClass().getResource("/Imagenes/cora.png"));
    
    
    public String golpe2 = "D:\\Github\\Opcional1\\src\\sound\\golpe2.mp3";
    MP3Player musicGolpe2 = new MP3Player(new File(golpe2));
    
    public String fallo = "D:\\Github\\Opcional1\\src\\sound\\fallo.mp3";
    MP3Player musicFallo = new MP3Player(new File(fallo));
    

    public peleador2(int x, int y, int direccion) {
        coorx = x;
        coory = y;
        dir = direccion;
    }

    public void actualizaVida(int golpe) {
        vida = vida - golpe;
    }

    public void mueve(int avanza) {
        this.dir = avanza;
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
            coorx = coorx - 30;
        }
    }

    public void paint(Graphics g) {
        
        if (vida > 0) {
            pj = new ImageIcon(getClass().getResource("/Imagenes/peleador2_2.png"));
        } else {
            pj = null;
        }

        validar();
        switch (dir) {
            case 1:
                pj = new ImageIcon(getClass().getResource("/Imagenes/peleador2_2.png"));
                coorx = coorx + 20;
                dir = 0;
                break;

            case 2:
                coorx = coorx - 20;
                pj = new ImageIcon(getClass().getResource("/Imagenes/peleador2_2.png"));
                dir = 0;
                break;

            case 3:
                coorx = coorx;
                musicFallo.play();
                pj = new ImageIcon(getClass().getResource("/Imagenes/puno2_2.png"));
                golpe_Puno = true;
                dir = 0;
                break;

            case 4:
                coorx = coorx;
                musicFallo.play();
                pj = new ImageIcon(getClass().getResource("/Imagenes/patada2_2.png"));
                golpe_Patada = true;
                dir = 0;
                break;

            case 5:
                coorx = coorx + 100;
                musicGolpe2.play();
                pj = new ImageIcon(getClass().getResource("/Imagenes/caido2_2.png"));
                g.drawImage(pow.getImage(), 40, 400, null);
                if (vida == 7) {                    
                    v1 = new ImageIcon(getClass().getResource("/Imagenes/cora_vacio.png"));
                } else if (vida == 5) {                    
                    v2 = new ImageIcon(getClass().getResource("/Imagenes/cora_vacio.png"));
                } else if (vida <1) {                    
                    v3 = new ImageIcon(getClass().getResource("/Imagenes/cora_vacio.png"));
                    finaliza = true;
                }
                vida--;
                dir = 0;
                break;
        }

        if (finaliza == false) {
            g.drawImage(v1.getImage(), 750, 50, null);
            g.drawImage(v2.getImage(), 700, 50, null);
            g.drawImage(v3.getImage(), 650, 50, null);
            try {
                g.drawImage(pj.getImage(), coorx, coory, null);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(peleador.class.getName()).log(Level.SEVERE, null, ex);
            }
            coory = 300;
            golpe_Patada = false;
            golpe_Puno = false;
        } else System.out.println("Juego Finalizado: Ganador Jugador 2"); 


    }
}
