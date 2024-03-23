package astrocards.model;

import astrocards.system.Game;

import java.awt.*;
/**
 * Klasa Gracz, w ktorej sa zawarte wszystkie informacje na temat danego gracza
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Player {
    private String p_nick;
    private int p_hp;
    private int p_mana;
    private int p_shield;
    private int p_heroInt;

    private int p_trucizna;
    private int p_celnosc;

    private int p_wzmocnienie_trucizna;

    private int p_wzmocnienie;
    private boolean p_deleteTrucizna;

    private Game p_game;
    private Hero p_hero;

    private int p_playerId;

    public Player(Game game, Hero hero){
        this.p_game = game;
        this.p_hero = hero;
    }

    public void setNick(String nick) {
        this.p_nick = nick;
    }
    public void setHp(int hp) {
        this.p_hp = hp;
    }
    public void setMana(int mana) {
        this.p_mana = mana;
    }
    public void setShield(int shield){ this.p_shield = shield; }
    public void setTrucizna(int trucizna) {this.p_trucizna = trucizna; }
    public void setCelnosc(int celnosc) { this.p_celnosc = celnosc; }
    public void setWzmocnienie_trucizna(int wzmocnienie_trucizna) { this.p_wzmocnienie_trucizna = wzmocnienie_trucizna; }
    public void setWzmocnienie(int wzmocnienie) { this.p_wzmocnienie = wzmocnienie; }
    public String getNick() {
        return this.p_nick;
    }
    public int getHp() {
        return this.p_hp;
    }
    public int getMana() {
        return this.p_mana;
    }
    public int getShield(){ return this.p_shield; }
    public int getTrucizna() { return this.p_trucizna; }
    public int getCelnosc() { return this.p_celnosc; }
    public int getWzmocnienie_trucizna() { return this.p_wzmocnienie_trucizna; }
    public int getWzmocnienie() { return this.p_wzmocnienie; }

    public Image getCard1small() { return this.p_hero.getCard1small(); }
    public Image getCard2small() {
        return this.p_hero.getCard2small();
    }
    public Image getCard3small() {
        return this.p_hero.getCard3small();
    }
    public Image getCard4small() {
        return this.p_hero.getCard4small();
    }
    public Image getCard5small() {
        return this.p_hero.getCard5small();
    }
    public Image getCard6small() {
        return this.p_hero.getCard6small();
    }
    public Image getCard7small() {
        return this.p_hero.getCard7small();
    }
    public Image getCard8small() {
        return this.p_hero.getCard8small();
    }
    public void setHero(Hero hero){ this.p_hero = hero; }
    public void setHeroInt(int hero){this.p_heroInt = hero;}
    public int getHeroInt(){return this.p_heroInt;}
    public int getID(){return this.p_playerId;}
    public void setID(int x){this.p_playerId = x;}


    public void InitializePlayer(Hero hero, String nick, int h) {
        this.p_hero = hero;
        this.p_hero.InitializeCard(h);
        this.p_nick = nick;
        this.p_hp = 10;
        this.p_mana = 1;
        this.p_shield = 0;
        this.p_trucizna = 0;
        this.p_celnosc = 0;
        this.p_deleteTrucizna = false;
    }
}

