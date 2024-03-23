package astrocards.model;
import astrocards.system.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * Klasa Bohater, w ktorej sa zawarte wszystkie informacje na temat danego bohatera
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Hero {
    private BufferedImage h_card1small;
    private BufferedImage h_card2small;
    private BufferedImage h_card3small;
    private BufferedImage h_card4small;
    private BufferedImage h_card5small;
    private BufferedImage h_card6small;
    private BufferedImage h_card7small;
    private BufferedImage h_card8small;

    private Game h_game;

    public Hero(Game game) throws IOException {
        this.h_game = game;

    }

    public Image getCard1small() {
        return this.h_card1small;
    }

    public Image getCard2small() {
        return this.h_card2small;
    }

    public Image getCard3small() {
        return this.h_card3small;
    }

    public Image getCard4small() {
        return this.h_card4small;
    }

    public Image getCard5small() {
        return this.h_card5small;
    }

    public Image getCard6small() {
        return this.h_card6small;
    }

    public Image getCard7small() {
        return this.h_card7small;
    }

    public Image getCard8small() {
        return this.h_card8small;
    }

    public void ChangeHero(int hero){
        try {
            if (hero == 1) {

                this.h_card1small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta1.png"));
                this.h_card2small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta2.png"));
                this.h_card3small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta3.png"));
                this.h_card4small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta4.png"));
                this.h_card5small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta5.png"));
                this.h_card6small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta6.png"));
                this.h_card7small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta7.png"));
                this.h_card8small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta8.png"));

            }
            if (hero == 2) {

                this.h_card1small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta1.png"));
                this.h_card2small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta2.png"));
                this.h_card3small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta3.png"));
                this.h_card4small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta4.png"));
                this.h_card5small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta5.png"));
                this.h_card6small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta6.png"));
                this.h_card7small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta7.png"));
                this.h_card8small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta8.png"));
            }
            if (hero == 3) {

                this.h_card1small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta1.png"));
                this.h_card2small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta2.png"));
                this.h_card3small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta3.png"));
                this.h_card4small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta4.png"));
                this.h_card5small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta5.png"));
                this.h_card6small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta6.png"));
                this.h_card7small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta7.png"));
                this.h_card8small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta8.png"));
            }
            if (hero == 4) {

                this.h_card1small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta1.png"));
                this.h_card2small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta2.png"));
                this.h_card3small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta3.png"));
                this.h_card4small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta4.png"));
                this.h_card5small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta5.png"));
                this.h_card6small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta6.png"));
                this.h_card7small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta7.png"));
                this.h_card8small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta8.png"));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void InitializeCard(int hero) {
        try {
            if (hero == 1) {

                this.h_card1small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta1.png"));
                this.h_card2small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta2.png"));
                this.h_card3small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta3.png"));
                this.h_card4small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta4.png"));
                this.h_card5small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta5.png"));
                this.h_card6small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta6.png"));
                this.h_card7small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta7.png"));
                this.h_card8small = ImageIO.read(new File("rsc\\Hero\\1\\Bohater1Karta8.png"));

            }
            if (hero == 2) {

                this.h_card1small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta1.png"));
                this.h_card2small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta2.png"));
                this.h_card3small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta3.png"));
                this.h_card4small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta4.png"));
                this.h_card5small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta5.png"));
                this.h_card6small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta6.png"));
                this.h_card7small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta7.png"));
                this.h_card8small = ImageIO.read(new File("rsc\\Hero\\2\\Bohater2Karta8.png"));
            }
            if (hero == 3) {

                this.h_card1small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta1.png"));
                this.h_card2small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta2.png"));
                this.h_card3small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta3.png"));
                this.h_card4small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta4.png"));
                this.h_card5small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta5.png"));
                this.h_card6small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta6.png"));
                this.h_card7small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta7.png"));
                this.h_card8small = ImageIO.read(new File("rsc\\Hero\\3\\Bohater3Karta8.png"));
            }
            if (hero == 4) {

                this.h_card1small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta1.png"));
                this.h_card2small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta2.png"));
                this.h_card3small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta3.png"));
                this.h_card4small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta4.png"));
                this.h_card5small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta5.png"));
                this.h_card6small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta6.png"));
                this.h_card7small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta7.png"));
                this.h_card8small = ImageIO.read(new File("rsc\\Hero\\4\\Bohater4Karta8.png"));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

