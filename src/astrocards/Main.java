package astrocards;

import astrocards.server.Server;
import astrocards.system.Game;

import java.awt.*;
import java.io.IOException;
/**
 * Klasa Main uruchamiajaca 2 klientow
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Main {
    public static void main(String args[]) throws IOException, FontFormatException {
        System.out.println("The game is running");
        Server server = new Server();
        Game game = new Game();
        Game game2 = new Game();
    }
}
