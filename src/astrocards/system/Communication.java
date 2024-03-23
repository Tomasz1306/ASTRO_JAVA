package astrocards.system;

import astrocards.gui.Draw;
import astrocards.logic.Gameplay;
import astrocards.model.Hero;
import astrocards.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Klasa sluzaca do komunikacji pomiedzy klientem a serverem, glownie odbiera komunikaty wyslane przez serwer i odpowiednio na nie reaguje
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Communication {

    private Game c_game;
    private Multiplayer c_multi;
    private Draw c_draw;
    private Player c_player1;
    private Player c_player2;
    private Gameplay c_gameplay;
    private Score c_score;
    private Hero c_hero1;
    private Hero c_hero2;

    private Socket c_socket;
    private PrintWriter out;
    private BufferedReader in;

    private boolean getID;


    Communication(Game game, Multiplayer multi, Draw draw, Player player1, Player player2, Gameplay gameplay, Score score, Hero hero1, Hero hero2) {
        this.c_game = game;
        this.c_multi = multi;
        this.c_draw = draw;
        this.c_player1 = player1;
        this.c_player2 = player2;
        this.c_gameplay = gameplay;
        this.c_score = score;
        this.c_hero1 = hero1;
        this.c_hero2 = hero2;

        this.getID = true;

        this.Com();
    }

    public void setGetId(boolean x) {
        this.getID = x;
    }

    public void Com() {
        new Thread(() -> {
            System.out.println("Uruchomiono komunikacje pomiedzy klientem a serwerem");

            while (true) {
                try {
                    Thread.sleep(10);
                    this.obsluga();
                } catch (IOException | InterruptedException e) {
                    //throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public PrintWriter getOut() {
        return this.out;
    }

    public void obsluga() throws IOException {
        if (c_game.getConnect()) {
            c_socket = new Socket(c_multi.getIp(), c_multi.getPort());
            c_game.setSocket(c_socket);
            out = new PrintWriter(c_socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(c_socket.getInputStream()));

            String wiadomosc = c_player1.getNick() + "," + c_player1.getHeroInt();
            c_game.setIsConnected(true);
            out.println(wiadomosc);
            c_game.setConnect(false);

            if (getID && c_socket != null) {
                out.println("Get player id");
                String input = in.readLine();
                c_player1.setID(Integer.parseInt(input));

                c_gameplay.setYourTurn(c_player1.getID() == 1);
                c_gameplay.setButtonEndTurn(c_player1.getID() == 1);

                getID = false;
            }

            if (c_socket == null) {
                c_game.setConnect(true);
            }
        }

        if (in != null && out != null) {
            String input = in.readLine();

            if (input != null) {
                switch (input) {
                    case "Dane" -> processDane();
                    case "Dane Score 1" -> processDaneScore1();
                    case "Dane Score 2" -> processDaneScore2();
                    case "Zaznaczono karte 1", "Zaznaczono karte 2", "Zaznaczono karte 3", "Zaznaczono karte 4",
                            "Zaznaczono karte 5", "Zaznaczono karte 6", "Zaznaczono karte 7", "Zaznaczono karte 8" ->
                            processZaznaczonoKarte(input);
                    case "Odznaczono karte" ->
                            c_gameplay.setOponentCardCoord(-100, -100);
                    case "My Information" -> processMyInformation();
                    case "Wygrana" -> {
                        c_gameplay.setButtonEndTurn(false);
                        c_gameplay.setButtonWinner(true);
                        c_score.GetScore();
                    }
                    case "Przegrana" -> {
                        c_gameplay.setButtonEndTurn(false);
                        c_gameplay.setG_buttonLoser(true);
                        c_score.GetScore();
                    }
                    case "Dane2" -> processDane2();
                    case "Reload" -> processReload();
                }
            }
        }
    }

    private void processDane() throws IOException {
        String dane = in.readLine();
        String[] names = dane.split(",");

        if (names.length != 0) {
            assignPlayerData(names);
        }

        c_game.RePaint();
    }

    private void processDaneScore1() throws IOException {
        String input = in.readLine();
        String[] wynikID = input.split(",");
        List<String> wynikIDList = Arrays.stream(wynikID)
                .collect(Collectors.toList());
        c_score.setWynikID(wynikIDList.toArray(new String[0]));
        c_score.setPobrano1(true);
    }

    private void processDaneScore2() throws IOException {
        String input = in.readLine();
        String[] graczID = input.split(",");
        List<String> graczIDList = Arrays.stream(graczID)
                .collect(Collectors.toList());
        c_score.setGraczID(graczIDList.toArray(new String[0]));
        c_score.setPobrano2(true);
    }

    private void processZaznaczonoKarte(String input) {
        c_gameplay.setOponentCardCoord(430 + (80 * (input.charAt(input.length() - 1) - '1')), 110);
    }

    private void processMyInformation() throws IOException {
        String newInput = in.readLine();
        String[] dane2 = newInput.split(",");
        c_player1.setHp(Integer.parseInt(dane2[0]));
        c_player1.setMana(Integer.parseInt(dane2[1]));
        c_player1.setShield(Integer.parseInt(dane2[2]));
        c_player1.setTrucizna(Integer.parseInt(dane2[3]));
        c_player2.setHp(Integer.parseInt(dane2[4]));
        c_player2.setMana(Integer.parseInt(dane2[5]));
        c_player2.setShield(Integer.parseInt(dane2[6]));
        c_player2.setTrucizna(Integer.parseInt(dane2[7]));
        c_gameplay.setTura(Integer.parseInt(dane2[8]));

        if (c_gameplay.getTura() % 2 == (c_player1.getID() - 1)) {
            c_gameplay.setYourTurn(false);
            c_gameplay.setNothing();
            c_gameplay.setButtonEndTurn(false);
            System.out.println("Its not your turn");
        } else {
            c_gameplay.setButtonEndTurn(true);
            c_gameplay.setYourTurn(true);
            c_gameplay.setRed();
            System.out.println("Its your turn");
        }
    }

    private void processDane2() throws IOException {
        c_score.setLicznik(c_score.getLicznik() + 1);
        String dane = in.readLine();
        String[] names = dane.split(",");
        if (names.length != 0) {
            assignPlayerData(names);
            c_hero1.ChangeHero(c_player1.getHeroInt());
            c_hero2.ChangeHero(c_player2.getHeroInt());
            c_game.setOponentCard(c_player2.getHeroInt());
            c_game.RePaint();
        }
    }

    private void processReload() throws IOException {
        String dane = in.readLine();
        String[] names = dane.split(",");
        if (names.length != 0) {
            assignPlayerData(names);
        }
    }

    private void assignPlayerData(String[] names) {
        c_player1.setNick(names[0]);
        c_player1.setHeroInt(Integer.parseInt(names[1]));
        c_player2.setNick(names[2]);
        c_player2.setHeroInt(Integer.parseInt(names[3]));
    }

}
