package astrocards.system;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Klasa odpowiadajaca za ustawienia w grze
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Settings implements ActionListener {

    private Game s_game;
    private JButton s_buttonColor;
    private JButton s_buttonTheme;
    private JButton s_buttonBack;

    private int theme;

    Settings(Game game){
        this.s_game = game;
        this.InitializeSettings();
        this.ButtonListenerMenu();
        this.theme = 1;
    }

    public void InitializeSettings(){
        this.s_buttonColor = new JButton("COLORS");
        this.s_buttonColor.setBounds(100, 200, 280, 60);
        this.s_buttonColor.setBackground(Color.decode("#431723"));
        this.s_buttonColor.setForeground(Color.white);
        this.s_buttonColor.setVisible(true);
        this.s_buttonColor.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.s_buttonColor.setBorderPainted(true);
        this.s_buttonColor.setUI(new BasicButtonUI());
        this.s_buttonColor.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.s_buttonColor.addActionListener(this);

        this.s_buttonTheme = new JButton("THEME");
        this.s_buttonTheme.setBounds(100, 300, 280, 60);
        this.s_buttonTheme.setBackground(Color.decode("#431723"));
        this.s_buttonTheme.setForeground(Color.white);
        this.s_buttonTheme.setVisible(true);
        this.s_buttonTheme.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.s_buttonTheme.setBorderPainted(true);
        this.s_buttonTheme.setUI(new BasicButtonUI());
        this.s_buttonTheme.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.s_buttonTheme.addActionListener(this);

        this.s_buttonBack = new JButton("BACK");
        this.s_buttonBack.setBounds(100, 400, 280, 60);
        this.s_buttonBack.setBackground(Color.decode("#431723"));
        this.s_buttonBack.setForeground(Color.white);
        this.s_buttonBack.setVisible(true);
        this.s_buttonBack.setBorder(BorderFactory.createLineBorder(Color.decode("#928e80")));
        this.s_buttonBack.setBorderPainted(true);
        this.s_buttonBack.setUI(new BasicButtonUI());
        this.s_buttonBack.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.s_buttonBack.addActionListener(this);
    }

    public JButton getButtonColor(){
        return this.s_buttonColor;
    }
    public JButton getButtonBack(){
        return this.s_buttonBack;
    }
    public JButton getButtonTheme(){
        return this.s_buttonTheme;
    }
    public int getTheme(){
        return this.theme;
    }
    public void setVisible(){
        if (this.s_game.getScene() == 8) {
            this.s_buttonColor.setVisible(true);
            this.s_buttonBack.setVisible(true);
            this.s_buttonTheme.setVisible(true);
        } else {
            this.s_buttonColor.setVisible(false);
            this.s_buttonBack.setVisible(false);
            this.s_buttonTheme.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.s_game.getScene() == 8){
            if(e.getSource() == this.s_buttonColor){
                if(this.s_game.getColor()){
                    this.s_game.setColor(false);
                }else{
                    this.s_game.setColor(true);
                }
                this.s_game.RePaint();
            }
            if(e.getSource() == this.s_buttonBack){
                this.s_game.setScene(1);
                this.s_game.RePaint();
            }
            if(e.getSource() == this.s_buttonTheme){
                if(this.theme == 3){
                    this.theme = 1;
                }else{
                    this.theme++;
                }
                if (this.theme == 1) {
                    this.s_game.setColor1("#431723");
                    this.s_game.setColor2("#928e80");
                    this.s_buttonTheme.setBackground(Color.decode(this.s_game.getColor1()));
                    this.s_buttonBack.setBackground(Color.decode(this.s_game.getColor1()));
                    this.s_buttonColor.setBackground(Color.decode(this.s_game.getColor1()));
                }
                if (this.theme == 2) {
                    this.s_game.setColor1("#4a2480");
                    this.s_game.setColor2("#c53a9d");
                    this.s_buttonTheme.setBackground(Color.decode(this.s_game.getColor1()));
                    this.s_buttonBack.setBackground(Color.decode(this.s_game.getColor1()));
                    this.s_buttonColor.setBackground(Color.decode(this.s_game.getColor1()));
                }
                if (this.theme == 3) {
                    this.s_game.setColor1("#203671");
                    this.s_game.setColor2("#5fc75d");
                    this.s_buttonTheme.setBackground(Color.decode(this.s_game.getColor1()));
                    this.s_buttonBack.setBackground(Color.decode(this.s_game.getColor1()));
                    this.s_buttonColor.setBackground(Color.decode(this.s_game.getColor1()));
                }
                this.s_game.RePaint();
            }

        }
    }
    public void ButtonListenerMenu() {
        this.s_buttonColor.addMouseListener(new MouseListener() {
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
                s_buttonColor.setBackground(Color.decode(s_game.getColor2()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonColor.setBackground(Color.decode(s_game.getColor1()));
            }
        });
        this.s_buttonBack.addMouseListener(new MouseListener() {
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
                s_buttonBack.setBackground(Color.decode(s_game.getColor2()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonBack.setBackground(Color.decode(s_game.getColor1()));
            }
        });
        this.s_buttonTheme.addMouseListener(new MouseListener() {
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
                s_buttonTheme.setBackground(Color.decode(s_game.getColor2()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                s_buttonTheme.setBackground(Color.decode(s_game.getColor1()));
            }
        });
    }
}

