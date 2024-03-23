package astrocards.system;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
/**
 * Klasa odpowiadajaca za wybor mapy
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Maps implements ActionListener {

    private Game m_game;

    private JButton m_buttonBack;
    private JButton m_buttonRight;
    private JButton m_buttonLeft;

    private Image planet1;
    private Image planet2;
    private Image planet3;

    private Image planet;

    private int numer;

    private Image map1;
    private Image map2;
    private Image map3;

    private Image map;

    private Image background1;
    private Image background2;
    private Image background3;

    private Image background;

    private String color1;
    private String color2;

    Maps(Game game) throws IOException {
        this.m_game = game;
        this.color1 = this.m_game.getColor1();
        this.color2 = this.m_game.getColor2();
        this.InitializeButton();
        this.ButtonListenerMaps();
        this.planet1 = ImageIO.read(new File("rsc\\Maps\\1.png"));
        this.planet2 = ImageIO.read(new File("rsc\\Maps\\2.png"));
        this.planet3 = ImageIO.read(new File("rsc\\Maps\\3.png"));
        this.map1 = ImageIO.read(new File("rsc\\Maps\\11.png"));
        this.map2 = ImageIO.read(new File("rsc\\Maps\\22.png"));
        this.map3 = ImageIO.read(new File("rsc\\Maps\\33.png"));
        this.background1 = ImageIO.read(new File("rsc\\BackGrounds\\11.png"));
        this.background2 = ImageIO.read(new File("rsc\\BackGrounds\\22.png"));
        this.background3 = ImageIO.read(new File("rsc\\BackGrounds\\33.png"));
        this.numer = 1;
        this.map = this.map1;
        this.planet = this.planet1;
        this.background = this.background1;



    }

    public Image getMap(){
        return this.map;
    }
    public Image getPlanet(){
        return this.planet;
    }
    public Image getBackgound(){
        return this.background;
    }
    public void InitializeButton(){

        this.m_buttonBack = new JButton("BACK");
        this.m_buttonBack.setBounds(100,600,280,60);
        this.m_buttonBack.setBackground(Color.decode(this.m_game.getColor1()));
        this.m_buttonBack.setForeground(Color.white);
        this.m_buttonBack.setVisible(false);
        this.m_buttonBack.setUI(new BasicButtonUI());
        this.m_buttonBack.setBorder(BorderFactory.createLineBorder(Color.decode(this.m_game.getColor2())));
        this.m_buttonBack.setBorderPainted(true);
        this.m_buttonBack.setFont(new Font("Press Start 2P", Font.PLAIN, 50));
        this.m_buttonBack.addActionListener(this);

        this.m_buttonRight = new JButton(">");
        this.m_buttonRight.setBounds(450,150,60,60);
        this.m_buttonRight.setBackground(Color.decode(this.m_game.getColor1()));
        this.m_buttonRight.setForeground(Color.white);
        this.m_buttonRight.setVisible(false);
        this.m_buttonRight.setUI(new BasicButtonUI());
        this.m_buttonRight.setBorder(BorderFactory.createLineBorder(Color.decode(this.m_game.getColor2())));
        this.m_buttonRight.setBorderPainted(true);
        this.m_buttonRight.setFont(new Font("Press Start 2P", Font.PLAIN, 50));
        this.m_buttonRight.addActionListener(this);

        this.m_buttonLeft = new JButton("<");
        this.m_buttonLeft.setBounds(50,150,60,60);
        this.m_buttonLeft.setBackground(Color.decode(this.m_game.getColor1()));
        this.m_buttonLeft.setForeground(Color.white);
        this.m_buttonLeft.setVisible(false);
        this.m_buttonLeft.setUI(new BasicButtonUI());
        this.m_buttonLeft.setBorder(BorderFactory.createLineBorder(Color.decode(this.m_game.getColor2())));
        this.m_buttonLeft.setBorderPainted(true);
        this.m_buttonLeft.setFont(new Font("Press Start 2P", Font.PLAIN, 50));
        this.m_buttonLeft.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.m_buttonBack){
            this.m_game.setScene(1);
            this.m_game.RePaint();
        }
        if(e.getSource() == this.m_buttonRight){
            if(this.numer == 3) {
                this.numer = 1;
            }else{
                this.numer++;
            }
            if(this.numer == 1){
                this.map = this.map1;
                this.planet = this.planet1;
                this.background = this.background1;
            }
            if(this.numer == 2){
                this.map = this.map2;
                this.planet = this.planet2;
                this.background = this.background2;
            }
            if(this.numer == 3){
                this.map = this.map3;
                this.planet = this.planet3;
                this.background = this.background3;
            }
            this.m_game.RePaint();
        }
        if(e.getSource() == this.m_buttonLeft){
            if(this.numer == 1){
                this.numer = 3;
            }else{
                this.numer--;
            }
            if(this.numer == 1){
                this.map = this.map1;
                this.planet = this.planet1;
                this.background = this.background1;
            }
            if(this.numer == 2){
                this.map = this.map2;
                this.planet = this.planet2;
                this.background = this.background2;
            }
            if(this.numer == 3){
                this.map = this.map3;
                this.planet = this.planet3;
                this.background = this.background3;
            }
            this.m_game.RePaint();
        }
    }
    public void setVisible(){
        if(this.m_game.getScene() == 2){
            this.m_buttonBack.setVisible(true);
            this.m_buttonRight.setVisible(true);
            this.m_buttonLeft.setVisible(true);
        }else{
            this.m_buttonBack.setVisible(false);
            this.m_buttonRight.setVisible(false);
            this.m_buttonLeft.setVisible(false);
        }
    }
    public JButton getButtonBack(){
        return this.m_buttonBack;
    }
    public JButton getButtonRight(){
        return this.m_buttonRight;
    }
    public JButton getButtonLeft(){
        return this.m_buttonLeft;
    }
    public void ButtonListenerMaps() {
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
                m_buttonBack.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonBack.setBackground(Color.decode(color2));
            }
        });
        this.m_buttonRight.addMouseListener(new MouseListener() {
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
                m_buttonRight.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonRight.setBackground(Color.decode(color2));
            }
        });
        this.m_buttonLeft.addMouseListener(new MouseListener() {
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
                m_buttonLeft.setBackground(Color.decode(color1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonLeft.setBackground(Color.decode(color2));
            }
        });
    }

}
