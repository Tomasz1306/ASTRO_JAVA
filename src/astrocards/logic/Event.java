package astrocards.logic;

import astrocards.gui.Draw;
import astrocards.system.Game;
import astrocards.system.MenuA;
import astrocards.system.Multiplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Klasa odpowiadajaca za obsluge wydarzen zwiazanych z oblsuga myszki
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Event extends MouseAdapter{

    private Game e_game;
    private Draw e_draw;
    private Gameplay e_gameplay;
    private MenuA e_menu;
    private Multiplayer e_multi;

    private Point e_mousePosition;
    public Event(Game game, Draw draw, Gameplay gameplay, MenuA menu, Multiplayer multi){
        this.e_game = game;
        this.e_draw = draw;
        this.e_gameplay = gameplay;
        this.e_menu = menu;
        this.e_multi = multi;
        this.e_mousePosition = new Point();
    }
    public void mouseClicked(MouseEvent e){
        if(this.e_game.getScene() == 6){
            this.setMousePosition(e);
            this.e_gameplay.Update(true, this.e_mousePosition.x, this.e_mousePosition.y);
            this.e_draw.repaint();
        }
    }
    public void mouseMoved(MouseEvent e){
        if(this.e_game.getScene() == 6){
            this.setMousePosition(e);
            this.e_gameplay.Update(false, this.e_mousePosition.x, this.e_mousePosition.y);
            this.e_draw.repaint();
        }
    }


    public void setMousePosition(MouseEvent e){
        if(this.e_game.getScene() == 6){
            PointerInfo a;
            a = MouseInfo.getPointerInfo();
            Point p = new Point(a.getLocation());
            SwingUtilities.convertPointFromScreen(p, e.getComponent());
            this.e_mousePosition.x=(int) p.getX();
            this.e_mousePosition.y=(int) p.getY();
            //this.e_mousePosition.x -= 7;
            //this.e_mousePosition.y -= 28;
        }
    }
}

