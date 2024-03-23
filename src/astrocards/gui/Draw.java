package astrocards.gui;

import astrocards.logic.Gameplay;
import astrocards.model.Player;
import astrocards.system.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * Klasa odpowiadajaca za rysowanie wszystkiego na ekranie
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Draw extends JPanel {

    private Game d_game;
    private MenuA d_menu;
    private Multiplayer d_multi;
    private Player d_player1;
    private Player d_player2;
    private Gameplay d_gameplay;
    private Settings d_set;
    private Maps d_map;

    private Image background;
    private Image bohater1;
    private Image bohater2;
    private Image bohater3;
    private Image bohater4;
    private Font menuFont;

    private Score d_score;

    private final Font d_font = new Font("Consolas", Font.PLAIN, 20);

    public Draw(Game game, MenuA menu, Multiplayer multi, Player player1, Player player2, Gameplay gameplay, Score score, Settings set, Maps map) throws IOException {
        this.d_game = game;
        this.d_menu = menu;
        this.d_multi = multi;
        this.d_player1 = player1;
        this.d_player2 = player2;
        this.d_gameplay = gameplay;
        this.d_score = score;
        this.d_set = set;
        this.d_map = map;
        this.setLayout(null);
        this.background = ImageIO.read(new File("rsc\\BackGrounds\\1.png"));
        this.bohater1 = ImageIO.read(new File("rsc\\Bohater1.png"));
        this.bohater2 = ImageIO.read(new File("rsc\\Bohater2.png"));
        this.bohater3 = ImageIO.read(new File("rsc\\Bohater3.png"));
        this.bohater4 = ImageIO.read(new File("rsc\\Bohater4.png"));
        this.menuFont = new Font("Press Start 2P", Font.PLAIN, 120);
        initializeComponents();
        this.menuFont = new Font("Press Start 2P", Font.PLAIN, 120);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(d_font);
        g.setColor(Color.white);
        this.setBackground(Color.black);
        Graphics2D g2 = (Graphics2D) g;

        try {
            this.DrawMenu(g, g2);
            this.DrawMultiplayer(g, g2);
            this.DrawGameplay(g, g2);
            this.DrawScore(g, g2);
            this.DrawSetting(g, g2);
            this.DrawMap(g,g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void DrawMenu(Graphics g, Graphics2D g2) {
        this.d_menu.setVisible();
        if (this.d_game.getScene() == 1) {
            g.drawImage(this.background, 0, 0, null);

            g.setColor(Color.decode("#FFFFFF"));
            g.setFont(this.menuFont);
            g.drawString("ASTROCARDS", 100, 200);
            g.setFont(new Font("Press Start 2P", Font.PLAIN, 10));
            g.drawString("@ OSKAR ANDRZEJEWSKI & TOMASZ BOGDAN", 50, 700);
            g.drawImage(this.bohater1, 500, 350, null);
            g.drawImage(this.bohater2, 650, 400, null);
            g.drawImage(this.bohater3, 800, 350, null);
            g.drawImage(this.bohater4, 950, 400, null);


        }
    }

    private void initializeComponents() {
        this.add(this.d_menu.getStart());
        this.add(this.d_menu.getMulti());
        this.add(this.d_menu.getOption());
        this.add(this.d_menu.getExit());
        this.add(this.d_multi.getButtonConnect());
        this.add(this.d_multi.getButtonReload());
        this.add(this.d_multi.getButtonChooseHero());
        this.add(this.d_multi.getButtonBack());
        this.add(this.d_multi.getButtonHero1());
        this.add(this.d_multi.getButtonHero2());
        this.add(this.d_multi.getButtonHero3());
        this.add(this.d_multi.getButtonHero4());
        this.add(this.d_multi.getTextFieldIp());
        this.add(this.d_multi.getTextFieldPort());
        this.add(this.d_multi.getTextFieldNick());
    }
    public void DrawMap(Graphics g, Graphics2D g2) throws IOException{
        this.d_map.setVisible();
        this.background = ImageIO.read(new File("rsc\\BackGrounds\\bakc.png"));
        if(this.d_game.getScene() == 2){
            g.drawImage(this.background, 0, 0, null);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(2f));
            g2.drawRect(599,99,701,401);
            g.drawImage(this.d_map.getMap(), 600, 100, null);
            g.drawImage(this.d_map.getPlanet(),130,50,null);
            this.add(this.d_map.getButtonBack());
            this.add(this.d_map.getButtonRight());
            this.add(this.d_map.getButtonLeft());
        }
    }
    public void DrawMultiplayer(Graphics g, Graphics2D g2) throws IOException {
        this.background = ImageIO.read(new File("rsc\\BackGrounds\\2.png"));
        this.d_multi.setVisible();
        if (this.d_game.getScene() == 5) {
            g.drawImage(this.background, 0, 0, null);

            initializeComponents();

            if (this.d_game.getIsConnected()) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.white);
            }
            g2.setStroke(new BasicStroke(2f));
            g2.drawRect(600, 400, 600, 300);
            g.setFont(new Font("Press Start 2P", Font.PLAIN, 45));
            g.drawString("INFORMATION", 680, 450);
            g.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
            g.drawString("IP  : " + this.d_multi.getIp(), 605, 500);
            g.drawString("PORT: " + this.d_multi.getPort(), 605, 530);
            g.setColor(Color.white);
            g.drawString("Player 1: " + this.d_player1.getNick(), 605, 560);
            g.drawString("Player 2: " + this.d_player2.getNick(), 605, 590);
            g.drawString("Hero 1 Status: " + this.d_player1.getHeroInt(), 605, 620);
            g.drawString("Hero 2 Status: " + this.d_player2.getHeroInt(), 605, 650);
        }
    }


    public void DrawGameplay(Graphics g, Graphics2D g2) throws IOException {
        this.background = ImageIO.read(new File("rsc\\BackGrounds\\3.png"));
        if (this.d_game.getScene() == 6) {
            Image trucizna = ImageIO.read(new File("rsc\\poison.png"));
            Image shield = ImageIO.read(new File("rsc\\shield.png"));

            g.drawImage(this.d_map.getBackgound(), 0, 0, null);
            this.add(this.d_gameplay.getButtonEndTurn());
            this.add(this.d_gameplay.getButtonWinner());
            this.add(this.d_gameplay.getButtonLoser());



            g.setFont(new Font("Press Start 2P", Font.PLAIN, 10));

            if(this.d_game.getColor()){
                g2.setColor(Color.red);
            }else{
                g.setColor(Color.decode("#928e80"));
            }

            g.drawString("HEALT: " + this.d_player1.getHp(), 50, 50);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRect(50,55,402, 12);
            g2.setStroke(new BasicStroke(9f));
            g2.drawRect(55,60,d_player1.getHp()*4,3);

            if(this.d_game.getColor()){
                g2.setColor(Color.yellow);
            }else{
                g.setColor(Color.decode("#928e80"));
            }
            g.drawString("ENERGY: " + this.d_player1.getMana(), 50, 100);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRect(50,105,402, 12);
            g2.setStroke(new BasicStroke(9f));
            g2.drawRect(55,110,d_player1.getMana()*4,3);

            if(this.d_game.getColor()){
                g2.setColor(Color.cyan);
            }else{
                g.setColor(Color.decode("#928e80"));
            }
            g.drawString("      -" + this.d_gameplay.getManaCost(), 150, 100);
            g.drawString("SHIELD: " + this.d_player1.getShield(), 50, 150);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRect(50,155,402, 12);
            g2.setStroke(new BasicStroke(9f));
            g2.drawRect(55,160,d_player1.getShield()*4,3);

            if(this.d_game.getColor()){
                g2.setColor(Color.green);
            }else{
                g.setColor(Color.decode("#928e80"));
            }
            g.drawString("POISON: " + this.d_player1.getTrucizna(), 50, 200);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRect(50,205,402, 12);
            g2.setStroke(new BasicStroke(9f));
            g2.drawRect(55,210,d_player1.getTrucizna()*4,3);

            if(this.d_game.getColor()){
                g2.setColor(Color.red);
            }else{
                g.setColor(Color.decode("#928e80"));
            }
            g.drawString("HEALT: " + this.d_player2.getHp(), 1200, 50);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRect(950,55,402, 12);
            g2.setStroke(new BasicStroke(9f));
            g2.drawRect(955,60,d_player2.getHp()*4,3);

            if(this.d_game.getColor()){
                g2.setColor(Color.yellow);
            }else{
                g.setColor(Color.decode("#928e80"));
            }
            g.drawString("ENERGY: " + this.d_player2.getMana(), 1200, 100);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRect(950,105,402, 12);
            g2.setStroke(new BasicStroke(9f));
            g2.drawRect(955,110,d_player2.getMana()*4,3);

            if(this.d_game.getColor()){
                g2.setColor(Color.cyan);
            }else{
                g.setColor(Color.decode("#928e80"));
            }
            g.drawString("SHIELD: " + this.d_player2.getShield(), 1200, 150);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRect(950,155,402, 12);
            g2.setStroke(new BasicStroke(9f));
            g2.drawRect(955,160,d_player2.getTrucizna()*4,3);

            if(this.d_game.getColor()){
                g2.setColor(Color.green);
            }else{
                g.setColor(Color.decode("#928e80"));
            }
            g.drawString("POISON: " + this.d_player2.getTrucizna(), 1200, 200);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRect(950,205,402, 12);
            g2.setStroke(new BasicStroke(9f));
            g2.drawRect(955,210,d_player2.getTrucizna()*4,3);

            g.setColor(Color.decode("#928e80"));
            g.drawImage(this.d_gameplay.getPlayerCard1(), 100, 550, null);
            g.drawImage(this.d_gameplay.getPlayerCard2(), 250, 550, null);
            g.drawImage(this.d_gameplay.getPlayerCard3(), 400, 550, null);
            g.drawImage(this.d_gameplay.getPlayerCard4(), 550, 550, null);
            g.drawImage(this.d_gameplay.getPlayerCard5(), 700, 550, null);
            g.drawImage(this.d_gameplay.getPlayerCard6(), 850, 550, null);
            g.drawImage(this.d_gameplay.getPlayerCard7(), 1000, 550, null);
            g.drawImage(this.d_gameplay.getPlayerCard8(), 1150, 550, null);


            g.setFont(new Font("Press Start 2P", Font.PLAIN, 15));
            g.setColor(Color.white);
            g.drawString("ROUND: " + this.d_gameplay.getTura(),530,50);


            if(this.d_player1.getHeroInt() == 1){
                g.drawImage(bohater1,50,240,null);
            }
            if(this.d_player1.getHeroInt() == 2){
                g.drawImage(bohater2,50,240,null);
            }
            if(this.d_player1.getHeroInt() == 3){
                g.drawImage(bohater3,50,240,null);
            }
            if(this.d_player1.getHeroInt() == 4){
                g.drawImage(bohater4,50,240,null);
            }
            if(this.d_player2.getHeroInt() == 1){
                g.drawImage(bohater1,1100,240,null);
            }
            if(this.d_player2.getHeroInt() == 2){
                g.drawImage(bohater2,1100,240,null);
            }
            if(this.d_player2.getHeroInt() == 3){
                g.drawImage(bohater3,1100,240,null);
            }
            if(this.d_player2.getHeroInt() == 4){
                g.drawImage(bohater4,1100,240,null);
            }
            if(this.d_player1.getShield() > 0){
                g.drawImage(shield,50,450,null);
            }
            if(this.d_player2.getShield() > 0){
                g.drawImage(shield,1100,450,null);
            }
            if(this.d_player1.getTrucizna() > 0){
                g.drawImage(trucizna,250,450,null);
            }
            if(this.d_player2.getTrucizna() > 0){
                g.drawImage(trucizna,1200,450,null);
            }



            //g.drawImage(this.d_gameplay.getOponentCard1(), 400, 0, null);
            //g.drawImage(this.d_gameplay.getOponentCard2(), 480, 0, null);
            //g.drawImage(this.d_gameplay.getOponentCard3(), 560, 0, null);
            //g.drawImage(this.d_gameplay.getOponentCard4(), 640, 0, null);
            //g.drawImage(this.d_gameplay.getOponentCard5(), 720, 0, null);
            //g.drawImage(this.d_gameplay.getOponentCard6(), 800, 0, null);
            //g.drawImage(this.d_gameplay.getOponentCard7(), 880, 0, null);
            //g.drawImage(this.d_gameplay.getOponentCard8(), 960, 0, null);

            //g.drawString(Integer.toString(this.d_gameplay.getTura()), 600, 400);
            //g.drawString("My id: " + this.d_player1.getID(), 600, 450);


            g.setColor(Color.green);
            g2.setColor(Color.green);
            g2.setStroke(new BasicStroke(4f));

            if (this.d_gameplay.getClickCard1()) {
                //g.drawString("Kliknieto karte 1", 500, 400);
                g2.drawRect(173, 523, 10, 10);
            }
            if (this.d_gameplay.getClickCard2()) {
                //g.drawString("Kliknieto karte 2", 500, 400);
                g2.drawRect(323, 523, 10, 10);
            }
            if (this.d_gameplay.getClickCard3()) {
                //g.drawString("Kliknieto karte 3", 500, 400);
                g2.drawRect(473, 523, 10, 10);
            }
            if (this.d_gameplay.getClickCard4()) {
                //g.drawString("Kliknieto karte 4", 500, 400);
                g2.drawRect(623, 523, 10, 10);
            }
            if (this.d_gameplay.getClickCard5()) {
                //g.drawString("Kliknieto karte 5", 500, 400);
                g2.drawRect(773, 523, 10, 10);
            }
            if (this.d_gameplay.getClickCard6()) {
                //g.drawString("Kliknieto karte 6", 500, 400);
                g2.drawRect(923, 523, 10, 10);
            }
            if (this.d_gameplay.getClickCard7()) {
                //g.drawString("Kliknieto karte 7", 500, 400);
                g2.drawRect(1073, 523, 10, 10);
            }
            if (this.d_gameplay.getClickCard8()) {
                //g.drawString("Kliknieto karte 8", 500, 400);
                g2.drawRect(1223, 523, 10, 10);
            }
            g2.setStroke(new BasicStroke(1f));
            g.setColor(Color.green);
            g.drawRect(this.d_gameplay.getPlayerCardCoord().x, this.d_gameplay.getPlayerCardCoord().y, 15, 15);
            //g.setColor(Color.red);
            //g.drawRect(this.d_gameplay.getOponentCardCoord().x, this.d_gameplay.getOponentCardCoord().y, 15, 15);

            g.setColor(Color.white);
            if (this.d_gameplay.CheckTurn()) {
                //g.drawImage(this.d_gameplay.getEndTurn(), 1000, 300, null);
            }
        }
    }

    public void DrawScore(Graphics g, Graphics2D g2) throws IOException {
        this.background = ImageIO.read(new File("rsc\\BackGrounds\\4.png"));
        this.d_score.SetVisible();
        if (this.d_game.getScene() == 7) {
            g.drawImage(this.background,0,0,null);
            if (this.d_score.getReady()) {
                //this.add(this.d_score.getScrollPane());
                this.add(this.d_score.getTable());
                //this.add(this.d_score.getPanel());

            }
            this.add(this.d_score.getNext());
            this.add(this.d_score.getExit());
            this.add(this.d_score.getReload());
            this.add(this.d_score.getButtonHero1());
            this.add(this.d_score.getButtonHero2());
            this.add(this.d_score.getButtonHero3());
            this.add(this.d_score.getButtonHero4());
            this.add(this.d_score.getButtonScore());

            g2.setStroke(new BasicStroke(2f));
            g2.drawRect(600, 400, 600, 300);
            g.setFont(new Font("Press Start 2P", Font.PLAIN, 40));
            g.drawString("INFORMATION", 680, 450);
            g.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
            g.setColor(Color.decode("#928e80"));
            g.drawString("Player 1: " + this.d_player1.getNick(), 605, 560);
            g.drawString("Player 2: " + this.d_player2.getNick(), 605, 590);
            g.drawString("Hero 1 Status: " + this.d_player1.getHeroInt(), 605, 620);
            g.drawString("Hero 2 Status: " + this.d_player2.getHeroInt(), 605, 650);

        }
    }
    public void DrawSetting(Graphics g, Graphics2D g2) throws IOException {
        this.d_set.setVisible();
        if(this.d_game.getScene() == 8){
            this.add(this.d_set.getButtonTheme());
            this.add(this.d_set.getButtonColor());
            this.add(this.d_set.getButtonBack());
            g.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
            if(this.d_game.getColor()){
                g.setColor(Color.red);
                g.drawString("HEALT" , 500, 240);
                g.setColor(Color.yellow);
                g.drawString("ENERGY" , 650, 240);
                g.setColor(Color.cyan);
                g.drawString("SHIELD" , 800, 240);
                g.setColor(Color.green);
                g.drawString("POISON" , 950, 240);
            }else{
                g.setColor(Color.decode("#928e80"));
                g.drawString("HEALT" , 500, 240);

                g.drawString("ENERGY" , 650, 240);

                g.drawString("SHIELD" , 800, 240);

                g.drawString("POISON" , 950, 240);

            }

            g.setColor(Color.decode("#431723"));
            g2.setStroke(new BasicStroke(4f));
            g2.drawRect(520,320,20,20);

            g.setColor(Color.decode("#928e80"));
            g2.setStroke(new BasicStroke(4f));
            g2.drawRect(620,320,20,20);

            g.setColor(Color.decode("#4a2480"));
            g2.setStroke(new BasicStroke(4f));
            g2.drawRect(720,320,20,20);

            g.setColor(Color.decode("#c53a9d"));
            g2.setStroke(new BasicStroke(4f));
            g2.drawRect(820,320,20,20);

            g.setColor(Color.decode("#203671"));
            g2.setStroke(new BasicStroke(4f));
            g2.drawRect(920,320,20,20);

            g.setColor(Color.decode("#5fc75d"));
            g2.setStroke(new BasicStroke(4f));
            g2.drawRect(1020,320,20,20);

            g.setColor(Color.white);
            g2.setStroke(new BasicStroke(2f));
            g2.drawRect(480,300,610,60);
            g.setColor(Color.white);
            g2.setStroke(new BasicStroke(2f));
            g2.drawRect(480,200,610,60);
            g.setColor(Color.green);
            if(this.d_set.getTheme() == 1){
                g2.drawRect(480,300,200,60);
            }
            if(this.d_set.getTheme() == 2){
                g2.drawRect(675,300,210,60);
            }
            if(this.d_set.getTheme() == 3){
                g2.drawRect(885,300,205,60);
            }
        }
    }
}

