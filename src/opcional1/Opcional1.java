package opcional1;


import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import jaco.mp3.player.MP3Player;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

/**
 * @author Prueba 2.0
 */
public class Opcional1 extends JFrame {

    public String song = "D:\\Github\\Opcional1\\src\\sound\\musica1.mp3";
    public String round = "D:\\Github\\Opcional1\\src\\sound\\round.mp3";
    public String one = "D:\\Github\\Opcional1\\src\\sound\\1.mp3";
    public String golpe1 = "D:\\Github\\Opcional1\\src\\sound\\golpe1.mp3";
    public String golpe2 = "D:\\Github\\Opcional1\\src\\sound\\golpe2.mp3";
    public String fallo = "D:\\Github\\Opcional1\\src\\sound\\fallo.mp3";
    public String end = "D:\\Github\\Opcional1\\src\\sound\\fin.mp3";
    public String fight1 = "D:\\Github\\Opcional1\\src\\sound\\fight.mp3";

    MP3Player musicaFondo = new MP3Player(new File(song));
    MP3Player musicaRound = new MP3Player(new File(round));
    MP3Player musicOne = new MP3Player(new File(one));
    MP3Player musicGolpe1 = new MP3Player(new File(golpe1));
    MP3Player musicGolpe2 = new MP3Player(new File(golpe2));
    MP3Player musicFallo = new MP3Player(new File(fallo));
    MP3Player musicFin = new MP3Player(new File(end));
    MP3Player musicFight = new MP3Player(new File(fight1));

    //private static final long serialVersionUID = 1L;
    peleador p1 = new peleador(300, 300, 0);
    peleador2 hilo2 = new peleador2(550, 300, 0);
    JLabel vida;
    JPanel panel;

    boolean finalizar = false;
    int inicia = 1, fin = 1;

    ImageIcon gameOver = new ImageIcon(getClass().getResource("/Imagenes/gameover.png"));
    ImageIcon fondo = new ImageIcon(getClass().getResource("/Imagenes/fondo1.png"));
    ImageIcon fight = new ImageIcon(getClass().getResource("/Imagenes/fight.jpg"));

    public Opcional1() {
        setSize(920, 530);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AccionKeyboard kD = new AccionKeyboard();
        addKeyListener(kD);
        p1.start();
        hilo2.start();
        musicaFondo.play();
    }

    public void paint(Graphics h) {

        int j2Vida = hilo2.getVida();
        int j1Vida = p1.getVida();

        //h.setColor(Color.white);
        //h.fillRect(0, 0, getWidth(), getHeight());
        if (j1Vida > 0 && j2Vida > 0) {
            finalizar = false;
        } else {
            finalizar = true;
        }

        if (inicia == 1) {
            inicia++;
            try {
                h.drawImage(fight.getImage(), 200, 100, null);
                musicaRound.play();
                Thread.sleep(1000);
                musicOne.play();
                Thread.sleep(1000);
                musicFight.play();
            } catch (InterruptedException ex) {
            }
            System.out.println("inicia" + inicia);
        } else {
            System.out.println("inicia" + inicia);
        }

        if (!finalizar) {
            h.drawImage(fondo.getImage(), 0, 0, null);
            p1.paint(h);
            hilo2.paint(h);
        } else {
            h.drawImage(gameOver.getImage(), 0, 0, null);
            musicaFondo.stop();
            if (fin == 1) {
                musicFin.play();
                fin++;
            } else {
                h.drawImage(gameOver.getImage(), 0, 0, null);

            }
        }
    }

    public static void main(String[] args) {
        Opcional1 nuevo = new Opcional1();
        while (true) {
            nuevo.repaint();
            nuevo.golpea();
        }
    }

    class AccionKeyboard extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                aux(e);
            } catch (LetraInvalida ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    public void aux(KeyEvent e) throws LetraInvalida {
        int teclado = e.getKeyCode();
        try {
            switch (teclado) {
                case KeyEvent.VK_D:
                    System.out.println("le dio a la D");
                    p1.mueve(1);
                    repaint();
                    break;
                case KeyEvent.VK_A:
                    System.out.println("le dio a la A");
                    p1.mueve(2);
                    repaint();
                    break;
                case KeyEvent.VK_W:
                    System.out.println("le dio a la W");
                    p1.mueve(3);
                    repaint();
                    break;
                case KeyEvent.VK_S:
                    System.out.println("le dio a la S");
                    p1.mueve(4);
                    repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("le dio a la DERECHA");
                    hilo2.mueve(1);
                    repaint();
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("le dio a la IZQUIERDA");
                    hilo2.mueve(2);
                    System.out.println(teclado);
                    repaint();
                    break;
                case KeyEvent.VK_UP:
                    System.out.println("le dio a la ARRIGA");
                    hilo2.mueve(3);
                    repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("le dio a la ABAJO");
                    hilo2.mueve(4);
                    repaint();
                    break;
                default:
                //throw new LetraInvalida();
            }
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Opcional1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void golpea() {
        int j1X = p1.getX();
        int j2X = hilo2.getX();
        int distancia = Math.abs(j1X - j2X);

        boolean j1Puno = p1.getGolpePuno();
        boolean j1Patada = p1.getGolpePatada();
        boolean j2Puno = hilo2.getGolpePuno();
        boolean j2Patada = hilo2.getGolpePatada();

        if (distancia <= 110 && j1Puno == true) {
            hilo2.mueve(5);
        } else if (distancia <= 110 && j1Patada == true) {
            hilo2.mueve(5);
        } else if (distancia <= 110 && j2Puno == true) {
            p1.mueve(5);
        } else if (distancia <= 110 && j2Patada == true) {
            p1.mueve(5);
        }
    }

    public class LetraInvalida extends Exception {

        public String getMessage() {
            return "letra invalida";
        }
    }
}
