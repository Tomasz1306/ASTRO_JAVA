package astrocards.logic;

import astrocards.model.Player;
import astrocards.system.Game;
import astrocards.system.Score;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
/**
 * Klasa odpowiadajaca za logike w trakcie rozgrywki jednakze obliczenia i glowna logika zajmuje sie server
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Gameplay implements ActionListener {

    private Game g_game;
    private Player g_player;
    private Player g_player2;
    private Score g_score;

    private Socket g_socket;
    private PrintWriter out2;
    private BufferedReader in2;

    private Image g_endTurnGreen;
    private Image g_endTurnRed;
    private Image g_nothing;
    private Image g_endTurn;
    private boolean g_mouseCard1;
    private boolean g_mouseCard2;
    private boolean g_mouseCard3;
    private boolean g_mouseCard4;
    private boolean g_mouseCard5;
    private boolean g_mouseCard6;
    private boolean g_mouseCard7;
    private boolean g_mouseCard8;
    private boolean g_clickCard1;
    private boolean g_clickCard2;
    private boolean g_clickCard3;
    private boolean g_clickCard4;
    private boolean g_clickCard5;
    private boolean g_clickCard6;
    private boolean g_clickCard7;
    private boolean g_clickCard8;

    private Image g_oponentCard1;
    private Image g_oponentCard2;
    private Image g_oponentCard3;
    private Image g_oponentCard4;
    private Image g_oponentCard5;
    private Image g_oponentCard6;
    private Image g_oponentCard7;
    private Image g_oponentCard8;


    private Image g_emptyCard;

    private Point g_oponentCard;
    private Point g_playerCard;

    private int g_tura;

    private int g_manaCost;

    private boolean g_yourTurn;

    private JButton g_buttonEndTurn;
    private JButton g_buttonWinner;
    private JButton g_buttonLoser;


    public Gameplay(Game game, Player player, Player player2, Score score) throws IOException {
        this.g_game = game;
        this.g_player = player;
        this.g_player2 = player2;
        this.g_score = score;
        this.InitializeButton();
        this.g_oponentCard = new Point();
        this.g_playerCard = new Point();
        this.g_endTurnGreen = ImageIO.read(new File("rsc\\Gameplay\\EndTurnGreen.png"));
        this.g_endTurnRed = ImageIO.read(new File("rsc\\Gameplay\\EndTurnRed.png"));
        this.g_nothing = ImageIO.read(new File("rsc\\Gameplay\\space.png"));
        this.g_endTurn = g_endTurnRed;
        this.g_tura = 1;
        this.g_manaCost = 0;

    }
    public void Reset(){
        this.g_tura = 1;
        this.g_manaCost = 0;
    }

    public Image getPlayerCard1() {
        return this.g_player.getCard1small();
    }

    public Image getPlayerCard2() {
        return this.g_player.getCard2small();
    }

    public Image getPlayerCard3() {
        return this.g_player.getCard3small();
    }

    public Image getPlayerCard4() {
        return this.g_player.getCard4small();
    }

    public Image getPlayerCard5() {
        return this.g_player.getCard5small();
    }

    public Image getPlayerCard6() {
        return this.g_player.getCard6small();
    }

    public Image getPlayerCard7() {
        return this.g_player.getCard7small();
    }

    public Image getPlayerCard8() {
        return this.g_player.getCard8small();
    }

    public boolean getMouseCard1() {
        return this.g_mouseCard1;
    }

    public boolean getMouseCard2() {
        return this.g_mouseCard2;
    }

    public boolean getMouseCard3() {
        return this.g_mouseCard3;
    }

    public boolean getMouseCard4() {
        return this.g_mouseCard4;
    }

    public boolean getMouseCard5() {
        return this.g_mouseCard5;
    }

    public boolean getMouseCard6() {
        return this.g_mouseCard6;
    }

    public boolean getMouseCard7() {
        return this.g_mouseCard7;
    }

    public boolean getMouseCard8() {
        return this.g_mouseCard8;
    }

    public boolean getClickCard1() {
        return this.g_clickCard1;
    }

    public boolean getClickCard2() {
        return this.g_clickCard2;
    }

    public boolean getClickCard3() {
        return this.g_clickCard3;
    }

    public boolean getClickCard4() {
        return this.g_clickCard4;
    }

    public boolean getClickCard5() {
        return this.g_clickCard5;
    }

    public boolean getClickCard6() {
        return this.g_clickCard6;
    }

    public boolean getClickCard7() {
        return this.g_clickCard7;
    }

    public boolean getClickCard8() {
        return this.g_clickCard8;
    }

    public Image getOponentCard1() {
        return this.g_oponentCard1;
    }

    public Image getOponentCard2() {
        return this.g_oponentCard2;
    }

    public Image getOponentCard3() {
        return this.g_oponentCard3;
    }

    public Image getOponentCard4() {
        return this.g_oponentCard4;
    }

    public Image getOponentCard5() {
        return this.g_oponentCard5;
    }

    public Image getOponentCard6() {
        return this.g_oponentCard6;
    }

    public Image getOponentCard7() {
        return this.g_oponentCard7;
    }

    public Image getOponentCard8() {
        return this.g_oponentCard8;
    }

    public Image getEndTurn() {
        return this.g_endTurn;
    }

    public Point getPlayerCardCoord() {
        return this.g_playerCard;
    }

    public Point getOponentCardCoord() {
        return this.g_oponentCard;
    }


    public boolean CheckTurn() {
        return this.g_yourTurn;
    }
    public void setYourTurn(boolean x){ this.g_yourTurn = x;}

    public int getManaCost() {
        return this.g_manaCost;
    }


    public void setOponentCardCoord(int x, int y){this.g_oponentCard.x = x; this.g_oponentCard.y = y;}

    public void setButtonEndTurn(boolean  x){this.g_buttonEndTurn.setVisible(x);}
    public void setButtonWinner(boolean x){this.g_buttonWinner.setVisible(x);}
    public void setG_buttonLoser(boolean x){this.g_buttonLoser.setVisible(x);}

    public void setTura(int x){this.g_tura = x; }
    public int getTura() {
        return this.g_tura;
    }
    public void setNothing(){ this.g_endTurn = this.g_nothing;}
    public void setRed(){this.g_endTurn = this.g_endTurnRed;}

    public void InitializeButton() {
        this.g_buttonEndTurn = new JButton("END TURN");
        this.g_buttonEndTurn.setBounds(660, 25, 250, 35);
        this.g_buttonEndTurn.setBackground(Color.decode("#431723"));
        this.g_buttonEndTurn.setForeground(Color.decode("#928e80"));
        this.g_buttonEndTurn.setVisible(false);
        this.g_buttonEndTurn.setFont(new Font("Press Start 2P", Font.PLAIN, 15));
        this.g_buttonEndTurn.addActionListener(this);

        this.g_buttonWinner = new JButton("WINNER");
        this.g_buttonWinner.setBounds(500, 300, 400, 100);
        this.g_buttonWinner.setBackground(Color.black);
        this.g_buttonWinner.setForeground(Color.white);
        this.g_buttonWinner.setVisible(false);
        this.g_buttonWinner.setFont(new Font("Press Start 2P", Font.PLAIN, 30));
        this.g_buttonWinner.addActionListener(this);

        this.g_buttonLoser = new JButton("LOSER");
        this.g_buttonLoser.setBounds(500, 300, 400, 100);
        this.g_buttonLoser.setBackground(Color.black);
        this.g_buttonLoser.setForeground(Color.white);
        this.g_buttonLoser.setVisible(false);
        this.g_buttonLoser.setFont(new Font("Press Start 2P", Font.PLAIN, 30));
        this.g_buttonLoser.addActionListener(this);


    }

    public void setOponentCard(int hero) throws IOException {
        if (hero == 1) {
            this.g_oponentCard1 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
            this.g_oponentCard2 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
            this.g_oponentCard3 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
            this.g_oponentCard4 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
            this.g_oponentCard5 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
            this.g_oponentCard6 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
            this.g_oponentCard7 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
            this.g_oponentCard8 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
        }
        if (hero == 2) {
            this.g_oponentCard1 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
            this.g_oponentCard2 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
            this.g_oponentCard3 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
            this.g_oponentCard4 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
            this.g_oponentCard5 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
            this.g_oponentCard6 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
            this.g_oponentCard7 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
            this.g_oponentCard8 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
        }
        if (hero == 3) {
            this.g_oponentCard1 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
            this.g_oponentCard2 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
            this.g_oponentCard3 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
            this.g_oponentCard4 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
            this.g_oponentCard5 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
            this.g_oponentCard6 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
            this.g_oponentCard7 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
            this.g_oponentCard8 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
        }
        if (hero == 4) {
            this.g_oponentCard1 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
            this.g_oponentCard2 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
            this.g_oponentCard3 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
            this.g_oponentCard4 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
            this.g_oponentCard5 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
            this.g_oponentCard6 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
            this.g_oponentCard7 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
            this.g_oponentCard8 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
        }
    }
    public JButton getButtonEndTurn() {
        return this.g_buttonEndTurn;
    }
    public JButton getButtonWinner(){
        return this.g_buttonWinner;
    }
    public JButton getButtonLoser(){
        return this.g_buttonLoser;
    }

    public void InitializeCard() {
        try {
            this.g_socket = this.g_game.getSocket();
            this.out2 = new PrintWriter(this.g_socket.getOutputStream(), true);
            this.in2 = new BufferedReader(new InputStreamReader(this.g_socket.getInputStream()));

            if (this.g_player2.getHeroInt() == 1) {
                this.g_oponentCard1 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
                this.g_oponentCard2 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
                this.g_oponentCard3 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
                this.g_oponentCard4 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
                this.g_oponentCard5 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
                this.g_oponentCard6 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
                this.g_oponentCard7 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
                this.g_oponentCard8 = ImageIO.read(new File("rsc\\Gameplay\\1.png"));
            }
            if (this.g_player2.getHeroInt() == 2) {
                this.g_oponentCard1 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
                this.g_oponentCard2 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
                this.g_oponentCard3 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
                this.g_oponentCard4 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
                this.g_oponentCard5 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
                this.g_oponentCard6 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
                this.g_oponentCard7 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
                this.g_oponentCard8 = ImageIO.read(new File("rsc\\Gameplay\\2.png"));
            }
            if (this.g_player2.getHeroInt() == 3) {
                this.g_oponentCard1 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
                this.g_oponentCard2 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
                this.g_oponentCard3 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
                this.g_oponentCard4 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
                this.g_oponentCard5 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
                this.g_oponentCard6 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
                this.g_oponentCard7 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
                this.g_oponentCard8 = ImageIO.read(new File("rsc\\Gameplay\\3.png"));
            }
            if (this.g_player2.getHeroInt() == 4) {
                this.g_oponentCard1 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
                this.g_oponentCard2 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
                this.g_oponentCard3 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
                this.g_oponentCard4 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
                this.g_oponentCard5 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
                this.g_oponentCard6 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
                this.g_oponentCard7 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
                this.g_oponentCard8 = ImageIO.read(new File("rsc\\Gameplay\\4.png"));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void Update(boolean mouseClicked, int x, int y) {
        int obrazenia = 0;
        int trucizna = 0;
        if (x >= 100 && x <= (100 + 145) && y >= 550 && y <= (550 + 208)) {
            this.g_playerCard.x = 170;
            this.g_playerCard.y = 520;
            if (this.g_mouseCard1 == false) {
                this.out2.println("Zaznaczono karte 1");
                this.g_mouseCard1 = true;
            }
            if (mouseClicked) {
                if (this.g_clickCard1 == false) {
                    this.g_clickCard1 = true;
                    if (this.g_player.getHeroInt() == 1) {
                        //Zadaj 6 obrazen
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        //zadaj 2 obrazen i naloz 2 trucizny
                        //koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 3) {
                        //zadaj 4 obrazen
                        //koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        //zadaj 4 obrazen
                        //koszt many 1
                        this.g_manaCost += 1;
                    }
                } else {
                    if (this.g_player.getHeroInt() == 1) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 3) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        this.g_manaCost -= 1;
                    }
                    this.g_clickCard1 = false;
                }
            }
        } else if (x >= 250 && x <= (250 + 145) && y >= 550 && y <= (550 + 208)) {
            this.g_playerCard.x = 320;
            this.g_playerCard.y = 520;
            if (this.g_mouseCard2 == false) {
                this.out2.println("Zaznaczono karte 2");
                this.g_mouseCard2 = true;
            }
            if (mouseClicked) {
                if (this.g_clickCard2 == false) {
                    this.g_clickCard2 = true;
                    if (this.g_player.getHeroInt() == 1) {
                        //Otrzymaj 4 ptk tarczy
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        //Otrzymaj 4 ptk tarczy
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 3) {
                        //Otrzymaj 6 ptk tarczy
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        //Otrzymaj 4 ptk tarczy
                        //Koszt many 1
                        this.g_manaCost += 1;
                    }
                } else {
                    if (this.g_player.getHeroInt() == 1) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 3) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        this.g_manaCost -= 1;
                    }
                    this.g_clickCard2 = false;
                }
            }
        } else if (x >= 400 && x <= (400 + 145) && y >= 550 && y <= (550 + 208)) {
            this.g_playerCard.x = 470;
            this.g_playerCard.y = 520;
            if (this.g_mouseCard3 == false) {
                this.out2.println("Zaznaczono karte 3");
                this.g_mouseCard3 = true;
            }
            if (mouseClicked) {
                if (this.g_clickCard3 == false) {
                    this.g_clickCard3 = true;
                    if (this.g_player.getHeroInt() == 1) {
                        //Otrzymaj 8 ptk tarczy
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        //Zadaj 5 pkt trucizny
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 3) {
                        //Otrzymaj 6 ptk tarczy
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        //Podwojenie obrazen w kolejnej turze
                        //Koszt many 2
                        this.g_manaCost += 2;
                    }
                } else {
                    if (this.g_player.getHeroInt() == 1) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 3) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        this.g_manaCost -= 2;
                    }
                    this.g_clickCard3 = false;
                }
            }
        } else if (x >= 550 && x <= (550 + 145) && y >= 550 && y <= (550 + 208)) {
            this.g_playerCard.x = 620;
            this.g_playerCard.y = 520;
            if (this.g_mouseCard4 == false) {
                this.out2.println("Zaznaczono karte 4");
                this.g_mouseCard4 = true;
            }
            if (mouseClicked) {
                if (this.g_clickCard4 == false) {
                    this.g_clickCard4 = true;
                    if (this.g_player.getHeroInt() == 1) {
                        //Zwieksz zadane obrazenia o 1
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        //Podwoj trucizne w tej turze i usun w nastepnej
                        //Koszt many 1
                        this.g_manaCost += 2;
                    } else if (this.g_player.getHeroInt() == 3) {
                        //Otrzymaj 1 ptk tarczy
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        //Zyskaj karte w kolejnej turze i 1 ptk energi
                        //Koszt many 1
                        this.g_manaCost += 1;
                    }
                } else {
                    if (this.g_player.getHeroInt() == 1) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        this.g_manaCost -= 2;
                    } else if (this.g_player.getHeroInt() == 3) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        this.g_manaCost -= 1;
                    }
                    this.g_clickCard4 = false;
                }
            }
        } else if (x >= 700 && x <= (700 + 145) && y >= 550 && y <= (550 + 208)) {
            this.g_playerCard.x = 770;
            this.g_playerCard.y = 520;
            if (this.g_mouseCard5 == false) {
                this.out2.println("Zaznaczono karte 6");
                this.g_mouseCard5 = true;
            }
            if (mouseClicked) {
                if (this.g_clickCard5 == false) {
                    this.g_clickCard5 = true;
                    if (this.g_player.getHeroInt() == 1) {
                        //Zwieksz zadane obrazenia o 1
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        //Podwoj trucizne w tej turze i usun w nastepnej
                        //Koszt many 1
                        this.g_manaCost += 2;
                    } else if (this.g_player.getHeroInt() == 3) {
                        //Otrzymaj 1 ptk tarczy
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        //Zyskaj karte w kolejnej turze i 1 ptk energi
                        //Koszt many 1
                        this.g_manaCost += 1;
                    }

                } else {
                    if (this.g_player.getHeroInt() == 1) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        this.g_manaCost -= 2;
                    } else if (this.g_player.getHeroInt() == 3) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        this.g_manaCost -= 1;
                    }
                    this.g_clickCard5 = false;

                }
            }
        } else if (x >= 850 && x <= (850 + 145) && y >= 550 && y <= (550 + 208)) {
            this.g_playerCard.x = 920;
            this.g_playerCard.y = 520;
            if (this.g_mouseCard6 == false) {
                this.out2.println("Zaznaczono karte 6");
                this.g_mouseCard6 = true;
            }
            if (mouseClicked) {
                if (this.g_clickCard6 == false) {
                    this.g_clickCard6 = true;
                    if (this.g_player.getHeroInt() == 1) {
                        //Zadaj 5 ptk obrazen obrazenia sa podwojone przeciwko tarczom
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        //Zamien 50% trucizny przeciwnika na swoja tarcze
                        //Koszt many 1
                        this.g_manaCost += 2;
                    } else if (this.g_player.getHeroInt() == 3) {
                        //Usun cala swoja tarcze i zadaj obrazenia rowne 50% tarczy
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        //Zadaj 9 ptk obrazen
                        //Koszt many 2
                        this.g_manaCost += 2;
                    }

                } else {
                    if (this.g_player.getHeroInt() == 1) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 3) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        this.g_manaCost -= 2;
                    }
                    this.g_clickCard6 = false;
                }
            }
        } else if (x >= 1000 && x <= (1000 + 145) && y >= 550 && y <= (550 + 208)) {
            this.g_playerCard.x = 1070;
            this.g_playerCard.y = 520;
            if (this.g_mouseCard7 == false) {
                this.g_mouseCard7 = true;
                this.out2.println("Zaznaczono karte 7");
            }
            if (mouseClicked) {
                if (this.g_clickCard7 == false) {
                    this.g_clickCard7 = true;
                    if (this.g_player.getHeroInt() == 1) {
                        //Otrzymaj 6 ptk obrazen i zadaj 24 ptk obrazen
                        //Koszt many 2
                        this.g_manaCost += 2;
                    } else if (this.g_player.getHeroInt() == 2) {
                        //Trucizna ktora nakladasz przez karty jest wieksza o 1
                        //Koszt many 1
                        this.g_manaCost += 2;
                    } else if (this.g_player.getHeroInt() == 3) {
                        //Zadaj 15 obrazen
                        //Koszt many 2
                        this.g_manaCost += 2;
                    } else if (this.g_player.getHeroInt() == 4) {
                        //W kolejnej turze zyskaj 2 do celnosci
                        //Koszt many 1
                        this.g_manaCost += 1;
                    }
                } else {
                    if (this.g_player.getHeroInt() == 1) {
                        this.g_manaCost -= 2;
                    } else if (this.g_player.getHeroInt() == 2) {
                        this.g_manaCost -= 2;
                    } else if (this.g_player.getHeroInt() == 3) {
                        this.g_manaCost -= 2;
                    } else if (this.g_player.getHeroInt() == 4) {
                        this.g_manaCost -= 1;
                    }
                    this.g_clickCard7 = false;
                }
            }
        } else if (x >= 1150 && x <= (1150 + 145) && y >= 550 && y <= (550 + 208)) {
            this.g_playerCard.x = 1220;
            this.g_playerCard.y = 520;
            if (this.g_mouseCard8 == false) {
                this.g_mouseCard8 = true;
                this.out2.println("Zaznaczono karte 8");
            }
            if (mouseClicked) {
                if (this.g_clickCard8 == false) {
                    this.g_clickCard8 = true;
                    if (this.g_player.getHeroInt() == 1) {
                        //Atak zada 2x wiecej obrazen
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        //Przez kolejne 3 rundy nakladaj na przeciwnika 3 ptk trucizny
                        //Koszt many 1
                        this.g_manaCost += 2;
                    } else if (this.g_player.getHeroInt() == 3) {
                        //Zmniejsz obrazenia zadawane przez przeciwnika w nastepnej turze
                        //Koszt many 1
                        this.g_manaCost += 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        //Znisz tarcze i ktory gracz bedzie mial wiecej tarczy zada 50% (od tarczy) obrazen
                        //Koszt many 1
                        this.g_manaCost += 1;
                    }
                } else {
                    if (this.g_player.getHeroInt() == 1) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 2) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 3) {
                        this.g_manaCost -= 1;
                    } else if (this.g_player.getHeroInt() == 4) {
                        this.g_manaCost -= 1;
                    }
                    this.g_clickCard8 = false;
                }

            }
        }else {
            this.g_playerCard.x = -100;
            this.g_playerCard.y = -100;
            if (this.g_yourTurn) {
                this.g_endTurn = this.g_endTurnRed;
            }
            if (this.g_mouseCard1) {
                //this.o1 = true;
                this.out2.println("Odznaczono karte");
                //this.g_clickCard1 = false;
                this.g_mouseCard1 = false;
            }
            if (this.g_mouseCard2) {
                //this.o2 = true;
                this.out2.println("Odznaczono karte");
                //this.g_clickCard2 = false;
                this.g_mouseCard2 = false;
            }
            if (this.g_mouseCard3) {
                //this.o3 = true;
                this.out2.println("Odznaczono karte");
                //this.g_clickCard3 = false;
                this.g_mouseCard3 = false;
            }
            if (this.g_mouseCard4) {
                //this.o4 = true;
                this.out2.println("Odznaczono karte");
                //this.g_clickCard4 = false;
                this.g_mouseCard4 = false;
            }
            if (this.g_mouseCard5) {
                //this.o5 = true;
                this.out2.println("Odznaczono karte");
                //this.g_clickCard5 = false;
                this.g_mouseCard5 = false;
            }
            if (this.g_mouseCard6) {
                //this.o6 = true;
                this.out2.println("Odznaczono karte");
                //this.g_clickCard6 = false;
                this.g_mouseCard6 = false;
            }
            if (this.g_mouseCard7) {
                //this.o7 = true;
                this.out2.println("Odznaczono karte");
                //this.g_clickCard7 = false;
                this.g_mouseCard7 = false;
            }
            if (this.g_mouseCard8) {
                //this.o8 = true;
                this.out2.println("Odznaczono karte");
                //this.g_clickCard8 = false;
                this.g_mouseCard8 = false;
            }
        }
        this.g_game.RePaint();
    }

    public void actionPerformed(ActionEvent e) {

        int obrazenia = 0;
        int trucizna = 0;
        if (e.getSource() == this.g_buttonEndTurn && (this.g_player.getMana() >= this.g_manaCost)) {

            this.g_player.setMana(this.g_player.getMana() - this.g_manaCost);

            if (this.g_clickCard1) {
                if (this.g_player.getHeroInt() == 1) {
                    //Zadaj 6 obrazen
                    //Koszt many 1
                    obrazenia += 6 + this.g_player.getCelnosc();
                    //System.out.println("Dodano 6 obrazen");
                } else if (this.g_player.getHeroInt() == 2) {
                    //zadaj 2 obrazen i naloz 2 trucizny
                    //koszt many 1
                    obrazenia += 2 + this.g_player.getCelnosc();
                    trucizna += 2 + this.g_player.getWzmocnienie_trucizna();
                } else if (this.g_player.getHeroInt() == 3) {
                    //zadaj 4 obrazen
                    //koszt many 1
                    obrazenia += 4 + this.g_player.getCelnosc();
                } else if (this.g_player.getHeroInt() == 4) {
                    //zadaj 4 obrazen
                    //koszt many 1
                    obrazenia += 4 + this.g_player.getCelnosc();
                }
            }
            if (this.g_clickCard2) {
                if (this.g_player.getHeroInt() == 1) {
                    //Otrzymaj 4 ptk tarczy
                    //Koszt many 1
                    this.g_player.setShield(this.g_player.getShield() + 4 + this.g_player.getWzmocnienie());
                } else if (this.g_player.getHeroInt() == 2) {
                    //Otrzymaj 4 ptk tarczy
                    //Koszt many 1
                    this.g_player.setShield(this.g_player.getShield() + 4 + this.g_player.getWzmocnienie());
                } else if (this.g_player.getHeroInt() == 3) {
                    //Otrzymaj 6 ptk tarczy
                    //Koszt many 1
                    this.g_player.setShield(this.g_player.getShield() + 6 + this.g_player.getWzmocnienie());
                } else if (this.g_player.getHeroInt() == 4) {
                    //Otrzymaj 4 ptk tarczy
                    //Koszt many 1
                    this.g_player.setShield(this.g_player.getShield() + 4 + this.g_player.getWzmocnienie());
                }
            }
            if (this.g_clickCard3) {
                if (this.g_player.getHeroInt() == 1) {
                    //Zadaj  8 ptk obrazen
                    //Koszt many 1
                    obrazenia += 8 + this.g_player.getCelnosc();
                } else if (this.g_player.getHeroInt() == 2) {
                    //Zadaj 5 pkt trucizny
                    //Koszt many 1
                    trucizna += 5 + this.g_player.getWzmocnienie_trucizna();
                } else if (this.g_player.getHeroInt() == 3) {
                    //Otrzymaj 6 ptk tarczy
                    //Koszt many 1
                    this.g_player.setShield(this.g_player.getShield() + 6 + this.g_player.getWzmocnienie());
                } else if (this.g_player.getHeroInt() == 4) {
                    //Zadaj 7 pkt obrazen, 2x obrazenia i 2 obrazen sobie jesli masz mniej niz 25 pkt hp
                    //Koszt many 2
                    obrazenia += 7 + this.g_player.getCelnosc();
                    if (this.g_player.getHp() < 25) {
                        obrazenia += 7 + this.g_player.getCelnosc();
                        this.g_player.setHp(this.g_player.getHp() - 2);
                    }
                }
            }
            if (this.g_clickCard4) {
                if (this.g_player.getHeroInt() == 1) {
                    //Zwieksz zadane obrazenia o 1
                    //Koszt many 1
                    this.g_player.setCelnosc(this.g_player.getCelnosc() + 1);
                } else if (this.g_player.getHeroInt() == 2) {
                    //Zwieksz trucizne przeciwnika o 50%
                    //Koszt many 1
                    trucizna += (this.g_player2.getTrucizna() / 2);
                } else if (this.g_player.getHeroInt() == 3) {
                    //Otrzymaj 1 ptk wzmocnienia tarczy
                    //Koszt many 1
                    this.g_player.setWzmocnienie(this.g_player.getWzmocnienie() + 1);
                } else if (this.g_player.getHeroInt() == 4) {
                    //Ulecz 10 pkt hp
                    //Koszt many 1
                    this.g_player.setHp(this.g_player.getHp() + 10);
                }
            }
            if (this.g_clickCard5) {
                if (this.g_player.getHeroInt() == 1) {
                    //Straz 10% hp zadaj 2x tyle obrazen
                    //Koszt many 1
                    obrazenia += (this.g_player.getHp() / 5);
                    this.g_player.setHp(this.g_player.getHp() - (this.g_player.getHp() / 10));
                } else if (this.g_player.getHeroInt() == 2) {
                    //Aktywuj trucizne przeciwnika
                    //Koszt many 1
                    trucizna -= 1;
                    obrazenia += this.g_player2.getTrucizna();
                } else if (this.g_player.getHeroInt() == 3) {
                    //Otrzymaj 4 tarczy 2 razy
                    //Koszt many 1
                    this.g_player.setShield((this.g_player.getShield() + 4 + this.g_player.getWzmocnienie()) * 2);
                } else if (this.g_player.getHeroInt() == 4) {
                    //Zyskaj 3ptk tarczy zwiekszane poprzez efekty zwiekszjajace obrazenia
                    //Koszt many 1
                    this.g_player.setShield(this.g_player.getShield() + 3 + this.g_player.getCelnosc());
                }
            }
            if (this.g_clickCard6) {
                if (this.g_player.getHeroInt() == 1) {
                    //Zadaj 5 ptk obrazen obrazenia sa podwojone jesli przeciwnik ma tarcze
                    //Koszt many 1
                    if (this.g_player2.getShield() > 0)
                        obrazenia += ((5 + this.g_player.getCelnosc()) * 2);
                    else
                        obrazenia += (5 + this.g_player.getCelnosc());
                } else if (this.g_player.getHeroInt() == 2) {
                    //Zamien 50% trucizny przeciwnika na swoja tarcze
                    //Koszt many 1
                    this.g_player.setShield(this.g_player.getShield() + (this.g_player2.getTrucizna() / 2));
                    trucizna = trucizna - this.g_player2.getTrucizna() / 2;
                } else if (this.g_player.getHeroInt() == 3) {
                    //Usun cala swoja tarcze i zadaj obrazenia rowne 50% tarczy
                    //Koszt many 1
                    obrazenia = (this.g_player.getShield() / 2);
                    this.g_player.setShield(0);
                } else if (this.g_player.getHeroInt() == 4) {
                    //Zadaj 9 ptk obrazen
                    //Koszt many 2
                    obrazenia += 9 + this.g_player.getCelnosc();
                }
            }
            if (this.g_clickCard7) {
                if (this.g_player.getHeroInt() == 1) {
                    //Otrzymaj 6 ptk obrazen i zadaj 24 ptk obrazen
                    //Koszt many 2
                    this.g_player.setHp(this.g_player.getHp() - 6);
                    obrazenia += 24 + this.g_player.getCelnosc();
                } else if (this.g_player.getHeroInt() == 2) {
                    //Trucizna ktora nakladasz przez karty jest wieksza o 1
                    //Koszt many 1
                    this.g_player.setWzmocnienie_trucizna(this.g_player.getWzmocnienie_trucizna() + 1);
                } else if (this.g_player.getHeroInt() == 3) {
                    //Zadaj 15 obrazen
                    //Koszt many 2
                    obrazenia += 15 + this.g_player.getCelnosc();
                } else if (this.g_player.getHeroInt() == 4) {
                    //W zyskaj 2 celnosci, usun swoja tacze
                    //Koszt many 1
                    this.g_player.setCelnosc(this.g_player.getCelnosc() + 2);
                    this.g_player.setShield(0);
                }
            }
            if (this.g_mouseCard8) {
                this.out2.println("Zaznaczono karte 8");
                if (this.g_player.getHeroInt() == 1) {
                    //Atak zada 2x wiecej obrazen
                    //Koszt many 1
                    obrazenia *= 2;
                } else if (this.g_player.getHeroInt() == 2) {
                    //Naluz 3 razy 3 trucizny na przeciwnika
                    //Koszt many 2
                    trucizna += ((3 + this.g_player.getWzmocnienie_trucizna()) * 3);
                } else if (this.g_player.getHeroInt() == 3) {
                    //Zmniejsz swoje obrazenia o 25%, zyskaj 10 pkt tarczy
                    //Koszt many 1
                    this.g_player.setShield(this.g_player.getShield() + 10 + this.g_player.getWzmocnienie());
                    obrazenia = obrazenia - (obrazenia / 4);
                } else if (this.g_player.getHeroInt() == 4) {
                    //Znisz tarcze i ktory gracz bedzie mial wiecej tarczy zada 50% (od tarczy) obrazen
                    //Koszt many 1
                    if (this.g_player.getShield() >= this.g_player2.getShield()) {
                        obrazenia = (this.g_player.getShield() / 2);
                        this.g_player.setShield(0);
                        this.g_player2.setShield(0);
                    } else {
                        obrazenia = (this.g_player2.getShield() / 2);
                        this.g_player.setShield(0);
                        this.g_player2.setShield(0);
                    }
                }
            }

            this.out2.println("Zakoncz ture");
            this.out2.println(this.g_player.getHp() + "," + this.g_player.getMana() + "," + this.g_player.getShield() + "," +
                    this.g_player.getTrucizna() + "," + obrazenia + "," + trucizna + "," +
                    this.g_clickCard1 + "," + this.g_clickCard2 + "," + this.g_clickCard3 + "," + this.g_clickCard4 + "," +
                    this.g_clickCard5 + "," + this.g_clickCard6 + "," + this.g_clickCard7 + "," + this.g_clickCard8);

            obrazenia = 0;
            trucizna = 0;
            if (this.g_clickCard1) {
                this.g_clickCard1 = false;
            }
            if (this.g_clickCard2) {
                this.g_clickCard2 = false;
            }
            if (this.g_clickCard3) {
                this.g_clickCard3 = false;
            }
            if (this.g_clickCard4) {
                this.g_clickCard4 = false;
            }
            if (this.g_clickCard5) {
                this.g_clickCard5 = false;
            }
            if (this.g_clickCard6) {
                this.g_clickCard6 = false;
            }
            if (this.g_clickCard7) {
                this.g_clickCard7 = false;
            }
            if (this.g_clickCard8) {
                this.g_clickCard8 = false;
            }
            this.g_manaCost = 0;
        }
        if (e.getSource() == this.g_buttonWinner){
            this.g_game.setScene(7);
            this.g_buttonWinner.setVisible(false);
        }
        if(e.getSource() == this.g_buttonLoser){
            this.g_game.setScene(7);
            this.g_buttonLoser.setVisible(false);
        }
        this.g_game.RePaint();
    }
}

