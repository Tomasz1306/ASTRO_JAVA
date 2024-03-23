package astrocards.system;

import astrocards.gui.Draw;
import astrocards.logic.Event;
import astrocards.logic.Gameplay;
import astrocards.model.Hero;
import astrocards.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Klasa Game, glowna klasa ktora sluzy do utworzenia wszystkich niezbednych instancji
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */

public class Game extends JFrame{

    private Socket g_socket;
    private int g_scene;
    private String Color1;
    private String Color2;
    private boolean color;


    private int g_port;
    private String g_ip;

    private Font g_font;

    private Draw g_draw;
    private Event g_event;
    private MenuA g_menu;
    private Multiplayer g_multi;
    private Player g_player;
    private Player g_player2;
    private Hero g_hero;
    private Hero g_hero2;
    private Gameplay g_gameplay;
    private Score g_score;
    private Communication g_com;
    private Settings g_set;
    private Maps g_map;

    public boolean s_isConnected;

    private boolean ButtonConnect;
    private boolean ButtonReload;

    private int g_myHero = 0;
    private int  g_enemyHero = 0;
    public Game() throws IOException, FontFormatException {

        this.g_scene = 1;
        this.Color1 = "#431723";
        this.Color2 = "#928e80";
        this.InitializeWindow();
        this.InitializeClasses();
        this.ButtonConnect = false;
        this.ButtonReload = false;
        this.g_font = Font.createFont(Font.TRUETYPE_FONT, new File("rsc\\Font\\PressStart2P-Regular.ttf"));

        this.color = false;
        this.s_isConnected = false;

        this.add(g_draw);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.black);
        this.addMouseMotionListener( g_event);
        this.addMouseListener( g_event);
    }

    public void InitializeWindow(){
        this.setSize(1400, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        this.setVisible(true);
    }
    public void InitializeClasses() throws IOException {

        this.g_map = new Maps(this);
        this.g_hero = new Hero(this);
        this.g_hero2 = new Hero(this);
        this.g_player = new Player(this, this.g_hero);
        this.g_player2 = new Player(this, this.g_hero2);
        this.g_score = new Score(this, this.g_player,this.g_player2, this.g_hero, this.g_hero2);
        this.g_gameplay = new Gameplay(this,this.g_player, this.g_player2, this.g_score);
        this.g_multi = new Multiplayer(this,this.g_player, this.g_hero, this.g_player2, this.g_hero2, this.g_gameplay);
        this.g_set = new Settings(this);
        this.g_menu = new MenuA(this);
        this.g_draw = new Draw(this,this.g_menu,this.g_multi,this.g_player,this.g_player2,this.g_gameplay, this.g_score,this.g_set,this.g_map);
        this.g_event = new Event(this,this.g_draw,this.g_gameplay,this.g_menu, this.g_multi);
        this.g_com = new Communication(this,this.g_multi,this.g_draw, this.g_player, this.g_player2,this.g_gameplay,this.g_score,this.g_hero,this.g_hero2);


    }

    public boolean getIsConnected(){
        return this.s_isConnected;
    }
    public void setIsConnected(boolean x){
        this.s_isConnected = x;
    }
    public String getColor1(){
        return this.Color1;
    }
    public String getColor2(){
        return this.Color2;
    }
    public void setColor1(String x){
        this.Color1 = x;
    }
    public void setColor2(String x){
        this.Color2 = x;
    }
    public boolean getColor(){
        return this.color;
    }
    public void setColor(boolean x){
        this.color = x;
    }

    public PrintWriter getOut(){return this.g_com.getOut();}
    public Font getFont(){
        return this.g_font;
    }
    public int getScene(){
        return this.g_scene;
    }
    public void setScene(int scene){
        this.g_scene = scene;
    }
    public void RePaint(){
        this.g_draw.repaint();
    }
    public Socket getSocket(){
        return this.g_socket;
    }
    public void setEnemyHero(int hero){
        this.g_enemyHero = hero;
    }
    public int getEnemyHero(){
        return this.g_enemyHero;
    }
    public void setMyHero(int hero) { this.g_myHero = hero;}
    public int getMyHero() { return this.g_myHero; }
    public void setSocket(Socket socket){ this.g_socket = socket; }

    public int getPort(){ return this.g_port; }
    public String getIp(){ return this.g_ip; }
    public void setPort(int port) { this.g_port = port; }
    public void setIp(String ip){ this.g_ip = ip; }
    public void ResetGameplay(){this.g_gameplay.Reset();}
    public void setOponentCard(int hero) throws IOException {this.g_gameplay.setOponentCard(hero);}
    public void setTuraGameplay(boolean x){this.g_gameplay.setYourTurn(x);}
    public void setEndTurnVisible(boolean x){this.g_gameplay.setButtonEndTurn(x);}

    public boolean getConnect(){return  this.ButtonConnect;}
    public boolean getReload(){return this.ButtonReload; }
    public void setConnect(boolean x){this.ButtonConnect = x;}
    public void setReload(boolean x){this.ButtonReload = x;}
    public void setIdTrue(boolean x){this.g_com.setGetId(x);}
    public void addPanel(JPanel panel){this.add(panel);}
}
