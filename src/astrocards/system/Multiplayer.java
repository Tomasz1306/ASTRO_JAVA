package astrocards.system;

import astrocards.logic.Gameplay;
import astrocards.model.Hero;
import astrocards.model.Player;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Klasa Multiplayer tworzy menu multiplayer, w ktorym gracze lacza sie z serwerem
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Multiplayer implements ActionListener {

    private Game m_game;
    private Player m_player;
    private Player m_player2;
    private Hero m_hero;
    private Hero m_hero2;
    private Gameplay m_gameplay;

    private Socket m_socket;
    private JButton m_buttonConnect;
    private JButton m_buttonReload;
    private JButton m_buttonNext;
    private JButton m_buttonBack;
    private JButton m_buttonHero1;
    private JButton m_buttonHero2;
    private JButton m_buttonHero3;
    private JButton m_buttonHero4;
    private JTextField m_TextFieldIp;
    private JTextField m_TextFieldPort;
    private JTextField m_TextFieldNick;
    private JLabel m_nick1label;
    private JLabel m_nick2label;
    private JLabel m_hero1label;
    private JLabel m_hero2label;


    private String m_ip;
    private String m_nick;
    private int m_heroInt1 = 0;
    private int m_heroInt2 = 0;
    private String m_info1;
    private String m_info2;
    private int m_port;
    private boolean m_connected;

    private PrintWriter out;
    private BufferedReader in;

    Multiplayer(Game game, Player player,Hero hero,Player player2, Hero hero2, Gameplay gameplay) {
        this.m_game = game;
        this.m_player = player;
        this.m_player2 = player2;
        this.m_hero = hero;
        this.m_hero2 = hero2;
        this.m_gameplay = gameplay;
        this.m_socket = this.m_game.getSocket();
        this.m_connected = false;
        this.InitializeComponents();
        this.ButtonListenerMulti();
    }

    public void InitializeComponents(){
        this.m_buttonConnect = new JButton("CONNECT");
        this.m_buttonConnect.setBounds(100,100,280,60);
        this.m_buttonConnect.setBackground(Color.decode("#431723"));
        this.m_buttonConnect.setForeground(Color.white);
        this.m_buttonConnect.setVisible(false);
        this.m_buttonConnect.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.m_buttonConnect.setBorderPainted(true);
        this.m_buttonConnect.setUI(new BasicButtonUI());
        this.m_buttonConnect.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_buttonConnect.addActionListener(this);

        this.m_buttonReload = new JButton("RELOAD");
        this.m_buttonReload.setBounds(100,200,280,60);
        this.m_buttonReload.setBackground(Color.decode("#431723"));
        this.m_buttonReload.setForeground(Color.white);
        this.m_buttonReload.setVisible(false);
        this.m_buttonReload.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.m_buttonReload.setBorderPainted(true);
        this.m_buttonReload.setUI(new BasicButtonUI());
        this.m_buttonReload.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_buttonReload.addActionListener(this);

        this.m_buttonNext = new JButton("START");
        this.m_buttonNext.setBounds(650,100,500,125);
        this.m_buttonNext.setBackground(Color.decode("#431723"));
        this.m_buttonNext.setForeground(Color.white);
        this.m_buttonNext.setVisible(false);
        this.m_buttonNext.setUI(new BasicButtonUI());
        this.m_buttonNext.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.m_buttonNext.setBorderPainted(true);
        this.m_buttonNext.setFont(new Font("Press Start 2P", Font.PLAIN, 50));
        this.m_buttonNext.addActionListener(this);

        this.m_buttonBack = new JButton("BACK");
        this.m_buttonBack.setBounds(100,300,280,60);
        this.m_buttonBack.setBackground(Color.decode("#431723"));
        this.m_buttonBack.setForeground(Color.white);
        this.m_buttonBack.setVisible(false);
        this.m_buttonBack.setUI(new BasicButtonUI());
        this.m_buttonBack.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.m_buttonBack.setBorderPainted(true);
        this.m_buttonBack.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_buttonBack.addActionListener(this);

        this.m_buttonHero1 = new JButton("HERO 1");
        this.m_buttonHero1.setBounds(100,600,100,100);
        this.m_buttonHero1.setBackground(Color.decode("#431723"));
        this.m_buttonHero1.setForeground(Color.white);
        this.m_buttonHero1.setVisible(false);
        this.m_buttonHero1.setUI(new BasicButtonUI());
        this.m_buttonHero1.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.m_buttonHero1.setBorderPainted(true);
        this.m_buttonHero1.setFont(new Font("Press Start 2P", Font.PLAIN, 10));
        this.m_buttonHero1.addActionListener(this);

        this.m_buttonHero2 = new JButton("HERO 2");
        this.m_buttonHero2.setBounds(200,600,100,100);
        this.m_buttonHero2.setBackground(Color.decode("#431723"));
        this.m_buttonHero2.setForeground(Color.white);
        this.m_buttonHero2.setVisible(false);
        this.m_buttonHero2.setUI(new BasicButtonUI());
        this.m_buttonHero2.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.m_buttonHero2.setBorderPainted(true);
        this.m_buttonHero2.setFont(new Font("Press Start 2P", Font.PLAIN, 10));
        this.m_buttonHero2.addActionListener(this);

        this.m_buttonHero3 = new JButton("HERO 3");
        this.m_buttonHero3.setBounds(300,600,100,100);
        this.m_buttonHero3.setBackground(Color.decode("#431723"));
        this.m_buttonHero3.setForeground(Color.white);
        this.m_buttonHero3.setVisible(false);
        this.m_buttonHero3.setUI(new BasicButtonUI());
        this.m_buttonHero3.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.m_buttonHero3.setBorderPainted(true);
        this.m_buttonHero3.setFont(new Font("Press Start 2P", Font.PLAIN, 10));
        this.m_buttonHero3.addActionListener(this);

        this.m_buttonHero4 = new JButton("HERO 4");
        this.m_buttonHero4.setBounds(400,600,100,100);
        this.m_buttonHero4.setBackground(Color.decode("#431723"));
        this.m_buttonHero4.setForeground(Color.white);
        this.m_buttonHero4.setVisible(false);
        this.m_buttonHero4.setUI(new BasicButtonUI());
        this.m_buttonHero4.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.m_buttonHero4.setBorderPainted(true);
        this.m_buttonHero4.setFont(new Font("Press Start 2P", Font.PLAIN, 10));
        this.m_buttonHero4.addActionListener(this);

        this.m_TextFieldIp = new JTextField("localhost");
        this.m_TextFieldIp.setBounds(600,100,280,60);
        this.m_TextFieldIp.setBackground(Color.decode("#431723"));
        this.m_TextFieldIp.setForeground(Color.decode("#928e80"));
        this.m_TextFieldIp.setEditable(true);
        this.m_TextFieldIp.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.m_TextFieldIp.setUI(new BasicTextFieldUI());
        this.m_TextFieldIp.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_TextFieldIp.addActionListener(this);

        this.m_TextFieldPort = new JTextField("2222");
        this.m_TextFieldPort.setBounds(600,200,280,60);
        this.m_TextFieldPort.setBackground(Color.decode("#431723"));
        this.m_TextFieldPort.setForeground(Color.decode("#928e80"));
        this.m_TextFieldPort.setVisible(false);
        this.m_TextFieldPort.setUI(new BasicTextFieldUI());
        this.m_TextFieldPort.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_TextFieldPort.addActionListener(this);
        this.m_TextFieldPort.setFocusable(true);

        this.m_TextFieldNick = new JTextField("NICK");
        this.m_TextFieldNick.setBounds(600,300,280,60);
        this.m_TextFieldNick.setBackground(Color.decode("#431723"));
        this.m_TextFieldNick.setForeground(Color.decode("#928e80"));
        this.m_TextFieldNick.setVisible(false);
        this.m_TextFieldNick.setUI(new BasicTextFieldUI());
        this.m_TextFieldNick.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_TextFieldNick.addActionListener(this);
        this.m_TextFieldNick.setFocusable(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.m_buttonConnect){
            this.m_ip = this.m_TextFieldIp.getText();
            this.m_port = Integer.parseInt(this.m_TextFieldPort.getText());
            this.m_player.setNick(this.m_TextFieldNick.getText());
            if((this.m_heroInt1 != 0) && (this.m_connected == false)){
                this.m_game.setConnect(true);
                this.m_buttonConnect.setVisible(false);
                this.m_TextFieldNick.setVisible(false);
                this.m_TextFieldPort.setVisible(false);
                this.m_TextFieldIp.setVisible(false);
                this.m_connected = true;
            }
            this.m_game.RePaint();
        }
        if(e.getSource() == this.m_buttonReload){
            if(this.m_connected == true){
                this.out = this.m_game.getOut();
                this.out.println("Dane");
                this.m_game.RePaint();
            }
        }
        if(e.getSource() == this.m_buttonNext){
            if(this.m_port == 2222 && this.m_player.getHeroInt() != 0 && this.m_connected && this.m_player2.getHeroInt() != 0){

                this.m_player.InitializePlayer(this.m_hero,this.m_nick, this.m_heroInt1);
                this.m_player2.InitializePlayer(this.m_hero2, this.m_info2, this.m_heroInt2);
                this.m_gameplay.InitializeCard();

                this.m_game.setScene(6);
            }
            this.m_game.RePaint();
        }
        if(e.getSource() == this.m_buttonBack){
            this.m_game.setScene(1);
            this.m_game.RePaint();
        }
        if(e.getSource() == this.m_buttonHero1){
            this.m_heroInt1 = 1;
            this.m_player.setHeroInt(1);
            this.m_buttonHero1.setBackground(Color.decode("#928e80"));
            this.m_game.RePaint();
        }else{
            this.m_buttonHero1.setBackground(Color.decode("#431723"));
        }
        if(e.getSource() == this.m_buttonHero2){
            this.m_heroInt1 = 2;
            this.m_player.setHeroInt(2);
            this.m_buttonHero2.setBackground(Color.decode("#928e80"));
            this.m_game.RePaint();
        }else{
            this.m_buttonHero2.setBackground(Color.decode("#431723"));
        }
        if(e.getSource() == this.m_buttonHero3){
            this.m_heroInt1 = 3;
            this.m_player.setHeroInt(3);
            this.m_buttonHero3.setBackground(Color.decode("#928e80"));
            this.m_game.RePaint();
        }else{
            this.m_buttonHero3.setBackground(Color.decode("#431723"));
        }
        if(e.getSource() == this.m_buttonHero4){
            this.m_heroInt1 = 4;
            this.m_player.setHeroInt(4);
            this.m_buttonHero4.setBackground(Color.decode("#928e80"));
            this.m_game.RePaint();
        }else{
            this.m_buttonHero4.setBackground(Color.decode("#431723"));
        }
    }
    public JButton getButtonConnect(){
        return this.m_buttonConnect;
    }
    public JButton getButtonReload(){
        return this.m_buttonReload;
    }
    public JButton getButtonChooseHero(){ return this.m_buttonNext; }
    public JButton getButtonBack(){ return this.m_buttonBack ;}
    public JButton getButtonHero1(){return this.m_buttonHero1;}
    public JButton getButtonHero2(){return this.m_buttonHero2;}
    public JButton getButtonHero3(){return this.m_buttonHero3;}
    public JButton getButtonHero4(){return this.m_buttonHero4;}
    public JTextField getTextFieldIp(){
        return this.m_TextFieldIp;
    }
    public JTextField getTextFieldPort(){
        return this.m_TextFieldPort;
    }
    public JTextField getTextFieldNick(){
        return this.m_TextFieldNick;
    }
    public void setVisible(){
        if(this.m_game.getScene() == 5){
            if(this.m_connected==false){
                this.m_TextFieldIp.setVisible(true);
                this.m_TextFieldPort.setVisible(true);
                this.m_TextFieldNick.setVisible(true);
                this.m_buttonConnect.setVisible(true);
            }
            if(this.m_player2.getNick() != null){
                this.m_buttonNext.setVisible(true);
            }
            this.m_buttonReload.setVisible(true);
            this.m_buttonBack.setVisible(true);
            this.m_buttonHero1.setVisible(true);
            this.m_buttonHero2.setVisible(true);
            this.m_buttonHero3.setVisible(true);
            this.m_buttonHero4.setVisible(true);
        }else{
            this.m_buttonConnect.setVisible(false);
            this.m_buttonReload.setVisible(false);
            this.m_buttonBack.setVisible(false);
            this.m_buttonNext.setVisible(false);
            this.m_buttonHero1.setVisible(false);
            this.m_buttonHero2.setVisible(false);
            this.m_buttonHero3.setVisible(false);
            this.m_buttonHero4.setVisible(false);
            this.m_TextFieldIp.setVisible(false);
            this.m_TextFieldPort.setVisible(false);
            this.m_TextFieldNick.setVisible(false);
        }
    }

    public void ButtonListenerMulti(){
        this.m_buttonConnect.addMouseListener(new MouseListener() {
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
                m_buttonConnect.setBackground(Color.decode("#46464f"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonConnect.setBackground(Color.decode("#431723"));
            }
        });

        this.m_buttonReload.addMouseListener(new MouseListener() {
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
                m_buttonReload.setBackground(Color.decode("#46464f"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonReload.setBackground(Color.decode("#431723"));
            }
        });

        this.m_buttonBack.addMouseListener(new MouseListener() {
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
                m_buttonBack.setBackground(Color.decode("#46464f"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonBack.setBackground(Color.decode("#431723"));
            }
        });

        this.m_buttonNext.addMouseListener(new MouseListener() {
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
                m_buttonNext.setBackground(Color.decode("#46464f"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonNext.setBackground(Color.decode("#431723"));
            }
        });
        this.m_buttonHero1.addMouseListener(new MouseListener() {
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
                m_buttonHero1.setBackground(Color.decode("#46464f"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonHero1.setBackground(Color.decode("#431723"));
            }
        });

        this.m_buttonHero2.addMouseListener(new MouseListener() {
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
                m_buttonHero2.setBackground(Color.decode("#46464f"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonHero2.setBackground(Color.decode("#431723"));
            }
        });

        this.m_buttonHero3.addMouseListener(new MouseListener() {
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
                m_buttonHero3.setBackground(Color.decode("#46464f"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonHero3.setBackground(Color.decode("#431723"));
            }
        });

        this.m_buttonHero4.addMouseListener(new MouseListener() {
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
                m_buttonHero4.setBackground(Color.decode("#46464f"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonHero4.setBackground(Color.decode("#431723"));
            }
        });
    }
    public String getIp(){
        return this.m_ip;
    }
    public int getPort(){
        return this.m_port;
    }
}

