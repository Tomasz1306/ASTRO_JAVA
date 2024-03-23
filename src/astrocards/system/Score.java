package astrocards.system;

import astrocards.model.Hero;
import astrocards.model.Player;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Klasa odpowiadajaca za utworzenie menu po rozgrywce, w ktorej mamy dostep do zwycieztw i mozliwosc rozpoczecia nowej rozgrywki
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Score implements ActionListener {

    private int licznik;
    private JTable s_wyniki;
    private JScrollPane s_scrollPane;
    private JPanel s_panel;

    private Player s_player;
    private Player s_player2;

    private Hero s_hero1;
    private Hero s_hero2;

    private int s_heroInt1;
    private int s_heroInt2;


    private String GraczID[];
    private String WynikID[];
    private String dane[][];

    private boolean pobrano1;
    private boolean pobrano2;

    Game s_game;
    private Socket s_socket;
    private PrintWriter out3;
    private BufferedReader in;
    private boolean s_ready;

    private JButton s_buttonNext;
    private JButton s_buttonExit;
    private JButton s_buttonHero1;
    private JButton s_buttonHero2;
    private JButton s_buttonHero3;
    private JButton s_buttonHero4;
    private JButton s_buttonReload;
    private JButton s_buttonGetScore;

    private String color1;
    private String color2;

    Score(Game game, Player player, Player player2, Hero hero1, Hero hero2) {

        this.s_game = game;
        this.s_player = player;
        this.s_player2 = player2;
        this.s_ready = false;
        this.s_hero1 = hero1;
        this.s_hero2 = hero2;
        this.s_panel = new JPanel(new BorderLayout());
        this.s_wyniki = new JTable();
        this.licznik = 0;
        this.pobrano1 = false;
        this.pobrano2 = false;

        this.color1 = this.s_game.getColor1();
        this.color2 = this.s_game.getColor2();

        this.InitializeButton();
        this.ButtonListenerMulti();
    }

    public void GetScore() throws IOException {
        this.s_socket = this.s_game.getSocket();
        this.out3 = new PrintWriter(this.s_socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.s_socket.getInputStream()));

    }

    public boolean getReady() {
        return this.s_ready;
    }

    public JTable getTable() {
        return this.s_wyniki;
    }

    public JPanel getPanel() {
        return this.s_panel;
    }

    public JScrollPane getScrollPane() {
        return this.s_scrollPane;
    }

    public JButton getNext() {
        return this.s_buttonNext;
    }

    public JButton getExit() {
        return this.s_buttonExit;
    }

    public JButton getReload() {
        return this.s_buttonReload;
    }

    public JButton getButtonScore(){return this.s_buttonGetScore;}

    public JButton getButtonHero1() {
        return this.s_buttonHero1;
    }

    public JButton getButtonHero2() {
        return this.s_buttonHero2;
    }

    public JButton getButtonHero3() {
        return this.s_buttonHero3;
    }

    public JButton getButtonHero4() {
        return this.s_buttonHero4;
    }

    public void setGraczID(String tab[]){this.GraczID = tab;}
    public void setWynikID(String tab[]){this.WynikID = tab;}

    public void setPobrano1(boolean x){this.pobrano1 = x;}
    public void setPobrano2(boolean x){this.pobrano2 = x;}

    public int getLicznik(){return this.licznik;}
    public void setLicznik(int x){this.licznik = x;}

    public void setWYniki(JTable x){this.s_wyniki = x;}
    public void InitializeButton() {

        this.s_buttonNext = new JButton("START");
        this.s_buttonNext.setBounds(100, 100, 280, 60);
        this.s_buttonNext.setBackground(Color.decode(this.s_game.getColor1()));
        this.s_buttonNext.setForeground(Color.white);
        this.s_buttonNext.setVisible(false);
        this.s_buttonNext.setBorder(BorderFactory.createLineBorder(Color.decode(this.s_game.getColor2())));
        this.s_buttonNext.setBorderPainted(true);
        this.s_buttonNext.setUI(new BasicButtonUI());
        this.s_buttonNext.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.s_buttonNext.addActionListener(this);

        this.s_buttonGetScore = new JButton("GET SCORE");
        this.s_buttonGetScore.setBounds(100, 400, 280, 60);
        this.s_buttonGetScore.setBackground(Color.decode(this.s_game.getColor1()));
        this.s_buttonGetScore.setForeground(Color.white);
        this.s_buttonGetScore.setVisible(false);
        this.s_buttonGetScore.setBorder(BorderFactory.createLineBorder(Color.decode(this.s_game.getColor2())));
        this.s_buttonGetScore.setBorderPainted(true);
        this.s_buttonGetScore.setUI(new BasicButtonUI());
        this.s_buttonGetScore.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.s_buttonGetScore.addActionListener(this);

        this.s_buttonExit = new JButton("EXIT");
        this.s_buttonExit.setBounds(100, 200, 280, 60);
        this.s_buttonExit.setBackground(Color.decode(this.s_game.getColor1()));
        this.s_buttonExit.setForeground(Color.white);
        this.s_buttonExit.setVisible(true);
        this.s_buttonExit.setBorder(BorderFactory.createLineBorder(Color.decode(this.s_game.getColor2())));
        this.s_buttonExit.setBorderPainted(true);
        this.s_buttonExit.setUI(new BasicButtonUI());
        this.s_buttonExit.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.s_buttonExit.addActionListener(this);

        this.s_buttonHero1 = new JButton("HERO 1");
        this.s_buttonHero1.setBounds(100, 600, 100, 100);
        this.s_buttonHero1.setBackground(Color.decode(this.s_game.getColor1()));
        this.s_buttonHero1.setForeground(Color.white);
        this.s_buttonHero1.setVisible(false);
        this.s_buttonHero1.setBorder(BorderFactory.createLineBorder(Color.decode(this.s_game.getColor2())));
        this.s_buttonHero1.setBorderPainted(true);
        this.s_buttonHero1.setUI(new BasicButtonUI());
        this.s_buttonHero1.setFont(new Font("Press Start 2P", Font.PLAIN, 10));
        this.s_buttonHero1.addActionListener(this);

        this.s_buttonHero2 = new JButton("HERO 2");
        this.s_buttonHero2.setBounds(200, 600, 100, 100);
        this.s_buttonHero2.setBackground(Color.decode(this.s_game.getColor1()));
        this.s_buttonHero2.setForeground(Color.white);
        this.s_buttonHero2.setVisible(false);
        this.s_buttonHero2.setBorder(BorderFactory.createLineBorder(Color.decode(this.s_game.getColor2())));
        this.s_buttonHero2.setBorderPainted(true);
        this.s_buttonHero2.setUI(new BasicButtonUI());
        this.s_buttonHero2.setFont(new Font("Press Start 2P", Font.PLAIN, 10));
        this.s_buttonHero2.addActionListener(this);

        this.s_buttonHero3 = new JButton("HERO 3");
        this.s_buttonHero3.setBounds(300, 600, 100, 100);
        this.s_buttonHero3.setBackground(Color.decode(this.s_game.getColor1()));
        this.s_buttonHero3.setForeground(Color.white);
        this.s_buttonHero3.setVisible(false);
        this.s_buttonHero3.setBorder(BorderFactory.createLineBorder(Color.decode(this.s_game.getColor2())));
        this.s_buttonHero3.setBorderPainted(true);
        this.s_buttonHero3.setUI(new BasicButtonUI());
        this.s_buttonHero3.setFont(new Font("Press Start 2P", Font.PLAIN, 10));
        this.s_buttonHero3.addActionListener(this);

        this.s_buttonHero4 = new JButton("HERO 4");
        this.s_buttonHero4.setBounds(400, 600, 100, 100);
        this.s_buttonHero4.setBackground(Color.decode(this.s_game.getColor1()));
        this.s_buttonHero4.setForeground(Color.white);
        this.s_buttonHero4.setVisible(false);
        this.s_buttonHero4.setBorder(BorderFactory.createLineBorder(Color.decode(this.s_game.getColor2())));
        this.s_buttonHero4.setBorderPainted(true);
        this.s_buttonHero4.setUI(new BasicButtonUI());
        this.s_buttonHero4.setFont(new Font("Press Start 2P", Font.PLAIN, 10));
        this.s_buttonHero4.addActionListener(this);

        this.s_buttonReload = new JButton("RELOAD");
        this.s_buttonReload.setBounds(100, 300, 280, 60);
        this.s_buttonReload.setBackground(Color.decode(this.s_game.getColor1()));
        this.s_buttonReload.setForeground(Color.white);
        this.s_buttonReload.setVisible(false);
        this.s_buttonReload.setBorder(BorderFactory.createLineBorder(Color.decode(this.s_game.getColor2())));
        this.s_buttonReload.setBorderPainted(true);
        this.s_buttonReload.setUI(new BasicButtonUI());
        this.s_buttonReload.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.s_buttonReload.addActionListener(this);
    }

    public void SetVisible() {
        if (this.s_game.getScene() == 7) {
            this.s_buttonNext.setVisible(true);
            this.s_buttonExit.setVisible(true);
            this.s_buttonHero1.setVisible(true);
            this.s_buttonHero2.setVisible(true);
            this.s_buttonHero3.setVisible(true);
            this.s_buttonHero4.setVisible(true);
            this.s_buttonReload.setVisible(true);
            this.s_panel.setVisible(true);
            this.s_buttonGetScore.setVisible(true);
            //this.s_wyniki.setVisible(true);
        } else {
            this.s_buttonNext.setVisible(false);
            this.s_buttonExit.setVisible(false);
            this.s_buttonHero1.setVisible(false);
            this.s_buttonHero2.setVisible(false);
            this.s_buttonHero3.setVisible(false);
            this.s_buttonHero4.setVisible(false);
            this.s_buttonReload.setVisible(false);
            this.s_panel.setVisible(false);
            this.s_buttonGetScore.setVisible(false);
            //this.s_wyniki.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.s_buttonNext) {
            if (this.s_player.getHeroInt() != 0) {
                this.s_player2.setHero(this.s_hero2);
                this.s_player.setHp(10);
                this.s_player.setMana(1);
                this.s_player.setShield(0);

                this.s_player2.setHp(10);
                this.s_player2.setMana(1);
                this.s_player2.setShield(0);
                this.s_game.ResetGameplay();

                this.s_wyniki.setVisible(false);
                if(this.s_player.getID() == 1){
                    this.s_game.setTuraGameplay(true);
                    this.s_game.setEndTurnVisible(true);
                }
                //this.s_game.setIdTrue(true);
                this.s_game.setScene(6);
                this.s_game.RePaint();
            }
        }
        if (e.getSource() == this.s_buttonReload) {
            this.out3.println("Dane2");
            this.s_game.RePaint();
        }
        if (e.getSource() == this.s_buttonExit) {
            this.s_game.dispatchEvent(new WindowEvent(this.s_game, WindowEvent.WINDOW_CLOSING));
        }
        if (e.getSource() == this.s_buttonHero1) {
            this.s_player.setHeroInt(1);
            this.SetHero();
            this.s_game.RePaint();
        }
        if (e.getSource() == this.s_buttonHero2) {
            this.s_player.setHeroInt(2);
            this.SetHero();
            this.s_game.RePaint();
        }
        if (e.getSource() == this.s_buttonHero3) {
            this.s_player.setHeroInt(3);
            this.SetHero();
            this.s_game.RePaint();
        }
        if (e.getSource() == this.s_buttonHero4) {
            this.s_player.setHeroInt(4);
            this.SetHero();
            this.s_game.RePaint();
        }
        if(e.getSource() == this.s_buttonGetScore){
            this.out3.println("Score");
            while(true){
                if(this.pobrano1 == true && this.pobrano2 == true){
                    this.pobrano1 = false;
                    this.pobrano2 = false;
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

            String dane2[][] = new String[this.WynikID.length][2];

            this.dane = dane2;
            System.out.println("Dlugosc WynikID: " + this.WynikID.length + " Wartosc WynikID[0]: " + this.WynikID[0]);
            System.out.println("Dlugosc GraczID: " + this.GraczID.length + " Wartosc GraczID[0]: " + this.GraczID[0]);

            DefaultTableModel model = new DefaultTableModel();
            model.setRowCount(0);
            this.s_wyniki = new JTable(model);

            model.addColumn("WynikId");
            model.addColumn("GraczId");

            for (int i = 0; i < this.WynikID.length; i++) {
                this.dane[i][0] = this.WynikID[i];
                this.dane[i][1] = this.GraczID[i];
                System.out.println("Wynik: " + this.dane[i][0] + " Gracz: " + this.dane[i][1]);
                model.insertRow(0, new Object[] {this.WynikID[i],this.GraczID[i]});
            }

            for(int i = 0; i<this.s_wyniki.getRowCount();i++){
                this.s_wyniki.setRowHeight(i,30);
            }
            model.fireTableDataChanged();
            this.s_wyniki = new JTable(model);

            for(int i = 0; i<this.s_wyniki.getRowCount();i++){
                this.s_wyniki.setRowHeight(i,30);
            }

            this.s_wyniki.setBounds(600, 100, 400, 250);
            this.s_wyniki.setForeground(Color.decode("#FFFFFF"));
            this.s_wyniki.setShowGrid(true);
            this.s_wyniki.getColumnModel().getColumn(0).setWidth(200);
            this.s_wyniki.getColumnModel().getColumn(1).setWidth(200);
            Font font = new Font("Press Start 2P", Font.PLAIN, 25);

            this.s_wyniki.setOpaque(false);
            ((DefaultTableCellRenderer)this.s_wyniki.getDefaultRenderer(Object.class)).setOpaque(false);
            this.s_wyniki.setFont(font);
            this.s_wyniki.setGridColor(Color.WHITE);
            this.s_wyniki.setVisible(true);
            this.s_game.RePaint();
            this.s_ready = true;
        }
    }

    public void SetHero() {
        this.out3.println("SetHero");
        this.out3.println(this.s_player.getHeroInt());
    }
    public void ButtonListenerMulti(){

        this.s_buttonReload.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                s_buttonReload.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonReload.setBackground(Color.decode(color2));
            }
        });

        this.s_buttonExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                s_buttonExit.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonExit.setBackground(Color.decode(color2));
            }
        });

        this.s_buttonNext.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                s_buttonNext.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonNext.setBackground(Color.decode(color2));
            }
        });
        this.s_buttonHero1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                s_buttonHero1.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonHero1.setBackground(Color.decode(color2));
            }
        });

        this.s_buttonHero2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                s_buttonHero2.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonHero2.setBackground(Color.decode(color2));
            }
        });

        this.s_buttonHero3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                s_buttonHero3.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonHero3.setBackground(Color.decode(color2));
            }
        });

        this.s_buttonHero4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                s_buttonHero4.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonHero4.setBackground(Color.decode(color2));
            }
        });
        this.s_buttonGetScore.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                s_buttonGetScore.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonGetScore.setBackground(Color.decode(color2));
            }
        });
    }

}

