import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel {

    private final int Divide = 600 / 8;
    private int move = 0;
    private Rectangle2D rec;

    private Image TorreBranca = null;
    private Image CavaloBranco = null;
    private Image BispoBranco = null;
    private Image RainhaBranca = null;
    private Image ReiBranco = null;
    private Image PeaoBranco = null;
    private Image TorrePreta = null;
    private Image CavaloPreto = null;
    private Image BispoPreto = null;
    private Image RainhaPreta = null;
    private Image ReiPreto = null;
    private Image PeaoPreto = null;
    char tabuleir[][] = new char[8][8];

    Tabuleiro(char[][] t) {
        tabuleir = t;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tabuleir[0][0] = 'R';
        tabuleir[0][1] = 'N';
        tabuleir[0][2] = 'B';
        tabuleir[0][3] = 'Q';
        tabuleir[0][4] = 'K';
        tabuleir[0][5] = 'B';
        tabuleir[0][6] = 'N';
        tabuleir[0][7] = 'R';
        tabuleir[7][7] = 'r';
        tabuleir[1][1] = 'P';
        tabuleir[1][0] = 'P';
        tabuleir[1][2] = 'P';
        tabuleir[1][3] = 'P';
        tabuleir[1][4] = 'P';
        tabuleir[1][5] = 'P';
        tabuleir[1][6] = 'P';
        tabuleir[1][7] = 'P';
        tabuleir[7][3] = 'q';
        tabuleir[7][0] = 'r';
        tabuleir[7][1] = 'n';
        tabuleir[7][2] = 'b';
        tabuleir[7][3] = 'q';
        tabuleir[7][4] = 'k';
        tabuleir[7][5] = 'b';
        tabuleir[7][6] = 'n';
        tabuleir[7][7] = 'r';
        for(int i=0; i<8; i++)
            tabuleir[6][i] = 'p';
        
        int iWidth = 600;
        int iHeight = 600;

        TorreBranca = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\wr.gif");
        CavaloBranco = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\\\Desktop\\teste_tabuleiro\\Icons\\wn.gif");
        BispoBranco = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\wb.gif");
        RainhaBranca = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\wq.gif");
        ReiBranco = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\wk.gif");
        PeaoBranco = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\wp.gif");
        TorrePreta = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\br.gif");
        CavaloPreto = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\bn.gif");
        BispoPreto = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\bb.gif");
        RainhaPreta = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\bq.gif");
        ReiPreto = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\bk.gif");
        PeaoPreto = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\teste_tabuleiro\\Icons\\bp.gif");
        //apaga a tela antes de desenhar o tabuleiro com as peças
        g.setColor(Color.WHITE);

        // Desenha o tabuleiro
        for (int i = 0; i < 8; i = i + 2) {
            for (int j = 0; j < 8; j = j + 2) {

                g2.setColor(Color.BLUE);
                rec = new Rectangle2D.Double(j * iWidth / 8, (1 + i) * iWidth / 8, Divide, Divide);
                g2.fill(rec);
                rec = new Rectangle2D.Double((1 + j) * iWidth / 8, i * iWidth / 8, Divide, Divide);
                g2.fill(rec);

            }
        }

        //Atribui as peças
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {

                switch (tabuleir[j][i]) {
                    case 'R':
                        g.drawImage(TorreBranca, 75 * i + 20, 540 - (75 * j + 10), this);
                        break;
                    case 'r':
                        g.drawImage(TorrePreta, 75 * i + 20, 540 - (75 * j + 10), this);
                        break;
                    case 'B':
                        g.drawImage(BispoBranco, 75 * i + 20, 540 - (75 * j + 10), this);
                        break;
                    case 'b':
                        g.drawImage(BispoPreto, 75 * i + 20, 540 - (75 * j + 10), this);
                        break;
                    case 'N':
                        g.drawImage(CavaloBranco, 75 * i + 20, 540 - (75 * j + 10), this);
                        break;
                    case 'n':
                        g.drawImage(CavaloPreto, 75 * i + 20, 540 - (75 * j + 10), this);
                        break;
                    case 'Q':
                        g.drawImage(RainhaBranca, 75 * i + 20, 530 - (75 * j + 5), this);
                        break;
                    case 'q':
                        g.drawImage(RainhaPreta, 75 * i + 20, 530 - (75 * j + 5), this);
                        break;
                    case 'k':
                        g.drawImage(ReiPreto, 75 * i + 20, 530 - (75 * j + 3), this);
                        break;
                    case 'K':
                        g.drawImage(ReiBranco, 75 * i + 20, 530 - (75 * j + 10), this);
                        break;
                    case 'p':
                        g.drawImage(PeaoPreto, 75 * i + 20, 540 - (75 * j + 10), this);
                        break;
                    case 'P':
                        g.drawImage(PeaoBranco, 75 * i + 20, 540 - (75 * j + 10), this);
                        break;
                    default:
                    //desenha nada
                }

            }

        }
        //g.drawImage(BispoPreto, 170,  530, this); //00
    }
}
