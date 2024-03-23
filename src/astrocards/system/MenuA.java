package astrocards.system;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
/**
 * Klasa Menu, tworzy menu gry
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class MenuA implements ActionListener {

    private Game m_game;

    public int zmienna;
    private JButton m_buttonStart;
    private JButton m_buttonMutli;
    private JButton m_buttonOption;
    private JButton m_buttonExit;

    private String color1;
    private String color2;

    MenuA(Game game) {

        this.m_game = game;
        this.color1 = this.m_game.getColor1();
        this.color2 = this.m_game.getColor2();
        this.InitializeButtons();
        this.zmienna = 1;
        this.ButtonListenerMenu();

    }

    public void setVisible() {
        if (this.m_game.getScene() == 1) {
            this.m_buttonStart.setVisible(true);
            this.m_buttonMutli.setVisible(true);
            this.m_buttonOption.setVisible(true);
            this.m_buttonExit.setVisible(true);
        } else {
            this.m_buttonStart.setVisible(false);
            this.m_buttonMutli.setVisible(false);
            this.m_buttonOption.setVisible(false);
            this.m_buttonExit.setVisible(false);
        }
    }

    public JButton getStart() {
        return this.m_buttonStart;
    }

    public JButton getMulti() {
        return this.m_buttonMutli;
    }

    public JButton getOption() {
        return this.m_buttonOption;
    }

    public JButton getExit() {
        return this.m_buttonExit;
    }

    public void InitializeButtons() {
        this.m_buttonStart = new JButton("MAPS");
        this.m_buttonStart.setBounds(100, 500, 280, 60);
        this.m_buttonStart.setBackground(Color.decode(color1));
        this.m_buttonStart.setForeground(Color.white);
        this.m_buttonStart.setVisible(true);
        this.m_buttonStart.setBorder(BorderFactory.createLineBorder(Color.decode(color2)));
        this.m_buttonStart.setBorderPainted(false);
        this.m_buttonStart.setUI(new BasicButtonUI());
        this.m_buttonStart.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_buttonStart.addActionListener(this);

        this.m_buttonMutli = new JButton("MULTIPLAYER");
        this.m_buttonMutli.setBounds(100, 300, 280, 60);
        this.m_buttonMutli.setBackground(Color.decode(color1));
        this.m_buttonMutli.setForeground(Color.white);
        this.m_buttonMutli.setVisible(true);
        this.m_buttonMutli.setBorder(BorderFactory.createLineBorder(Color.decode(color2)));
        this.m_buttonMutli.setBorderPainted(false);
        this.m_buttonMutli.setUI(new BasicButtonUI());
        this.m_buttonMutli.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_buttonMutli.addActionListener(this);

        this.m_buttonOption = new JButton("SETTINGS");
        this.m_buttonOption.setBounds(100, 400, 280, 60);
        this.m_buttonOption.setBackground(Color.decode(color1));
        this.m_buttonOption.setForeground(Color.white);
        this.m_buttonOption.setVisible(true);
        this.m_buttonOption.setBorder(BorderFactory.createLineBorder(Color.decode(color2)));
        this.m_buttonOption.setBorderPainted(false);
        this.m_buttonOption.setUI(new BasicButtonUI());
        this.m_buttonOption.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_buttonOption.addActionListener(this);

        this.m_buttonExit = new JButton("EXIT");
        this.m_buttonExit.setBounds(100, 600, 280, 60);
        this.m_buttonExit.setBackground(Color.decode(color1));
        this.m_buttonExit.setForeground(Color.white);
        this.m_buttonExit.setVisible(true);
        this.m_buttonExit.setBorder(BorderFactory.createLineBorder(Color.decode(color2)));
        this.m_buttonExit.setBorderPainted(false);
        this.m_buttonExit.setUI(new BasicButtonUI());
        this.m_buttonExit.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        this.m_buttonExit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.m_buttonStart) {
            this.m_game.setScene(2);
            this.m_game.RePaint();
        }
        if (e.getSource() == this.m_buttonMutli) {
            this.m_game.setScene(5);
            this.m_game.RePaint();

        }
        if (e.getSource() == this.m_buttonOption) {
            this.m_game.setScene(8);
            this.m_game.RePaint();
        }
        if (e.getSource() == this.m_buttonExit) {
            this.m_game.dispatchEvent(new WindowEvent(this.m_game, WindowEvent.WINDOW_CLOSING));
        }
    }

    public void ButtonListenerMenu() {
        this.m_buttonStart.addMouseListener(new MouseListener() {
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
                m_buttonStart.setBackground(Color.decode(m_game.getColor2()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonStart.setBackground(Color.decode(m_game.getColor1()));
            }
        });

        this.m_buttonMutli.addMouseListener(new MouseListener() {
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
                m_buttonMutli.setBackground(Color.decode(m_game.getColor2()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonMutli.setBackground(Color.decode(m_game.getColor1()));
            }
        });

        this.m_buttonOption.addMouseListener(new MouseListener() {
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
                m_buttonOption.setBackground(Color.decode(m_game.getColor2()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonOption.setBackground(Color.decode(m_game.getColor1()));
            }
        });

        this.m_buttonExit.addMouseListener(new MouseListener() {
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
                m_buttonExit.setBackground(Color.decode(m_game.getColor2()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                m_buttonExit.setBackground(Color.decode(m_game.getColor1()));
            }
        });
    }
}
