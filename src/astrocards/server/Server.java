package astrocards.server;


import astrocards.database.DataBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Klasa Server, ktory odpowiada za komunikacje pomiedzy klientami i logike podczas rozgrywki
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class Server {
    private ServerSocket serverSocket;
    private ClientThread client1Thread;
    private ClientThread client2Thread;
    private DataBase baza;



    private String Player1Information[];
    private String Player2Information[];

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {

        try {
            this.baza = new DataBase();

            System.out.println("Uruchomiono server");
            this.baza.dropSeq();
            this.baza.DropTable();
            this.baza.createSeq();
            this.baza.CreateTable();
            this.baza.InsertIntoHeros();
            this.baza.InsertIntoKarty();

            serverSocket = new ServerSocket(2222);
            Socket clientSocket1 = null;
            Socket clientSocket2 = null;
            this.Player1Information = new String[10];
            this.Player2Information = new String[10];
            System.out.println("Server started. Waiting for connections...");
            while (true) {
                System.out.println(serverSocket);
                if (clientSocket1 == null) {
                    clientSocket1 = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket1.getInetAddress().getHostAddress());
                } else {
                    clientSocket2 = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket2.getInetAddress().getHostAddress());
                }

                System.out.println(clientSocket1);
                System.out.println(clientSocket2);

                if (clientSocket1 != null && clientSocket2 != null) {
                    if (client1Thread == null) {
                        System.out.println("Thread 1  start");
                        this.client1Thread = new ClientThread(clientSocket1, clientSocket2, this, "player1", Player1Information, Player2Information, baza);
                        this.client1Thread.start();
                    }
                    if (client2Thread == null) {
                        System.out.println("Thread 2  start");
                        this.client2Thread = new ClientThread(clientSocket2, clientSocket1, this, "player2", Player2Information, Player1Information, baza);
                        this.client2Thread.start();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    public static class ClientThread extends Thread {
        private Socket mySocket;
        private Socket enemySocket;
        private Server server;
        private PrintWriter myOut;
        private PrintWriter enemyOut;
        private BufferedReader myIn;
        private BufferedReader enemyIn;
        private String playerName;


        private DataBase baza;

        private String myInformation[];
        private String enemyInformation[];
        private int tura;
        private int tura2;
        private int mana;


        public ClientThread(Socket socket1, Socket socket2, Server server, String playerName, String inf1[], String inf2[], DataBase baza) {
            this.mySocket = socket1;
            this.enemySocket = socket2;
            this.server = server;
            this.playerName = playerName;
            this.tura = 1;
            this.tura2 = 1;
            this.mana = 1;
            this.baza = baza;

            this.myInformation = inf1;

            this.enemyInformation = inf2;
        }

        @Override
        public void run() {

            try {
                myOut = new PrintWriter(mySocket.getOutputStream(), true);
                enemyOut = new PrintWriter(enemySocket.getOutputStream(), true);
                myIn = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
                enemyIn = new BufferedReader(new InputStreamReader(enemySocket.getInputStream()));

                String[] dane;
                String wiadomosc = null;
                wiadomosc = myIn.readLine();

                dane = wiadomosc.split(",");
                this.myInformation[0] = dane[0];
                this.myInformation[1] = dane[1];
                this.myInformation[2] = "10";
                this.myInformation[3] = "1";
                this.myInformation[4] = "0";
                this.myInformation[5] = "0";
                this.myInformation[6] = "0";
                this.myInformation[7] = "1";

                if (this.playerName.equals("player1")) {
                    this.baza.InsertIntoGracz(1, dane[0], Integer.parseInt(dane[1]));
                } else {
                    this.baza.InsertIntoGracz(2, dane[0], Integer.parseInt(dane[1]));
                }

                System.out.println(this.playerName + " : " + this.myInformation[0] + " : " + this.myInformation[1]);

                while (true) {

                    String inputLine = myIn.readLine();
                    if (inputLine != null) {
                        if (inputLine.equals("Dane2")) {
                            this.myOut.println("Dane2");
                            this.myOut.println(this.myInformation[0] + "," + this.myInformation[1] + "," + this.enemyInformation[0] + "," + this.enemyInformation[1]);

                        }
                        if(inputLine.equals("SetHero")){

                            String data;
                            data = this.myIn.readLine();
                            System.out.println("odebrano setHero: " + data);
                            this.myInformation[1] = data;
                        }
                        if(inputLine.equals("Score")){
                            System.out.println("Odebrano prosbe Score od " + this.playerName);
                            String dane11="brak", dane12="brak";
                            String dane21="brak", dane22="brak";
                            if(this.playerName.equals("player1")){
                                dane11="brak";
                                dane12="brak";
                                while(dane11.equals("brak")){
                                    dane11 = this.baza.SelectWyniki11Name();
                                    System.out.println(" Player1 Oczekiwanie na dane1");
                                }
                                while(dane12.equals("brak")){
                                    dane12 = this.baza.SelectWyniki12Name();
                                    System.out.println(" Player1 Oczekiwanie na dane2");
                                }
                                this.myOut.println("Dane Score 1");
                                this.myOut.println(dane11);
                                System.out.println("Wyslano Wyniki do " + this.playerName + " : " + dane11);
                                this.myOut.println("Dane Score 2");
                                this.myOut.println(dane12);
                                System.out.println("Wyslano Wyniki do " + this.playerName + " : " + dane12);
                            }
                            if(this.playerName.equals("player2")){
                                dane21="brak";
                                dane22="brak";
                                while(dane21.equals("brak")){
                                    dane21 = this.baza.SelectWyniki21Name();
                                    System.out.println(" Player2 Oczekiwanie na dane1");
                                }
                                while(dane22.equals("brak")){
                                    dane22 = this.baza.SelectWyniki22Name();
                                    System.out.println(" Player2 Oczekiwanie na dane2");
                                }
                                this.myOut.println("Dane Score 1");
                                this.myOut.println(dane21);
                                System.out.println("Wyslano Wyniki do " + this.playerName + " : " + dane21);
                                this.myOut.println("Dane Score 2");
                                this.myOut.println(dane22);
                                System.out.println("Wyslano Wyniki do " + this.playerName + " : " + dane22);
                            }


                        }

                        if (inputLine.equals("Zaznaczono karte 1")) {
                            enemyOut.println("Zaznaczono karte 1");
                        }
                        if (inputLine.equals("Zaznaczono karte 2")) {
                            enemyOut.println("Zaznaczono karte 2");
                        }
                        if (inputLine.equals("Zaznaczono karte 3")) {
                            enemyOut.println("Zaznaczono karte 3");
                        }
                        if (inputLine.equals("Zaznaczono karte 4")) {
                            enemyOut.println("Zaznaczono karte 4");
                        }
                        if (inputLine.equals("Zaznaczono karte 5")) {
                            enemyOut.println("Zaznaczono karte 5");
                        }
                        if (inputLine.equals("Zaznaczono karte 6")) {
                            enemyOut.println("Zaznaczono karte 6");
                        }
                        if (inputLine.equals("Zaznaczono karte 7")) {
                            enemyOut.println("Zaznaczono karte 7");
                        }
                        if (inputLine.equals("Zaznaczono karte 8")) {
                            enemyOut.println("Zaznaczono karte 8");
                        }
                        if (inputLine.equals("Dane")) {
                            myOut.println("Reload");
                            myOut.println(this.myInformation[0] + "," + this.myInformation[1] + "," + this.enemyInformation[0] + "," + this.enemyInformation[1]);
                            System.out.println(this.myInformation[0] + "," + this.myInformation[1] + "," + this.enemyInformation[0] + "," + this.enemyInformation[1]);
                        }
                        if (inputLine.equals("Zakoncz ture")) {
                            String noweDane = myIn.readLine();
                            String tablicaDanych[] = noweDane.split(",");
                            /*
                            [0] - nick
                            [1] - hero
                            [2] - hp
                            [3] - mana
                            [4] - shield
                            [5] trucizna

                            [6]  - Karta 1
                            [7]  - Karta 2
                            [8]  - Karta 3
                            [9]  - Karta 4
                            [10] - Karta 5
                            [11] - Karta 6
                            [12] - Karta 7
                            [13] - Karta 8
                             */


                            this.tura = Integer.parseInt(this.myInformation[7]);
                            this.tura2 = Integer.parseInt(this.enemyInformation[7]);
                            this.tura += 1;
                            this.myInformation[7] = Integer.toString(this.tura);
                            this.enemyInformation[7] = Integer.toString(this.tura);

                            int x = 0, y = 0, z = 0;
                            x = Integer.parseInt(this.myInformation[2]);
                            y = Integer.parseInt(this.myInformation[5]);
                            if (y != 0) {
                                x -= y;
                                y--;
                            }
                            this.myInformation[2] = Integer.toString(x);
                            this.myInformation[5] = Integer.toString(y);

                            this.myInformation[2] = tablicaDanych[0];
                            this.myInformation[3] = tablicaDanych[1];
                            this.myInformation[4] = tablicaDanych[2];
                            this.myInformation[5] = tablicaDanych[3];
                            int obrazenia = Integer.parseInt(tablicaDanych[4]);
                            int trucizna = Integer.parseInt(tablicaDanych[5]);
                            int noweHp = Integer.parseInt(this.enemyInformation[2]);
                            int nowaTrucizna = Integer.parseInt(this.enemyInformation[5]);
                            int nowyShield = Integer.parseInt(this.enemyInformation[4]);
                            this.enemyInformation[5] = Integer.toString(nowaTrucizna + trucizna);

                            for (int i = 0; i < (obrazenia + Integer.parseInt(enemyInformation[5])); i++) {
                                if (nowyShield != 0) {
                                    nowyShield -= 1;
                                } else {
                                    noweHp -= 1;
                                }
                            }
                            int trucizna_1 = Integer.parseInt(this.enemyInformation[5]);
                            if(trucizna_1 > 0 ){
                                trucizna_1 = trucizna_1-1;
                            }
                            this.enemyInformation[5] = Integer.toString(trucizna_1);
                            this.mana += 1;
                            int mana2 = Integer.parseInt(this.myInformation[3]);
                            this.myInformation[3] = Integer.toString(this.mana + mana2);

                            this.enemyInformation[4] = Integer.toString(nowyShield);
                            this.enemyInformation[2] = Integer.toString(noweHp);

                            if(this.playerName.equals("player1")){
                                this.baza.InsertIntoTura(this.tura, 1, 2, Integer.parseInt(this.enemyInformation[2]));
                            }else{
                                this.baza.InsertIntoTura(this.tura, 1, 2, Integer.parseInt(this.enemyInformation[2]));
                            }
                            if(tablicaDanych[6].equals("true")){
                                String id_bohater = this.myInformation[1] + "1";
                                System.out.println("Karty: " + id_bohater);
                                this.baza.InsertIntoZagraneKarty(this.tura, Integer.parseInt(id_bohater));
                            }
                            if(tablicaDanych[7].equals("true")){
                                String id_bohater = this.myInformation[1] + "2";
                                this.baza.InsertIntoZagraneKarty(this.tura, Integer.parseInt(id_bohater));
                            }
                            if(tablicaDanych[8].equals("true")){
                                String id_bohater = this.myInformation[1] + "3";
                                this.baza.InsertIntoZagraneKarty(this.tura, Integer.parseInt(id_bohater));
                            }
                            if(tablicaDanych[9].equals("true")){
                                String id_bohater = this.myInformation[1] + "4";
                                this.baza.InsertIntoZagraneKarty(this.tura, Integer.parseInt(id_bohater));
                            }
                            if(tablicaDanych[10].equals("true")){
                                String id_bohater = this.myInformation[1] + "5";
                                this.baza.InsertIntoZagraneKarty(this.tura, Integer.parseInt(id_bohater));
                            }
                            if(tablicaDanych[11].equals("true")){
                                String id_bohater = this.myInformation[1] + "6";
                                this.baza.InsertIntoZagraneKarty(this.tura, Integer.parseInt(id_bohater));
                            }
                            if(tablicaDanych[12].equals("true")){
                                String id_bohater = this.myInformation[1] + "7";
                                this.baza.InsertIntoZagraneKarty(this.tura, Integer.parseInt(id_bohater));
                            }
                            if(tablicaDanych[13].equals("true")){
                                String id_bohater = this.myInformation[1] + "8";
                                this.baza.InsertIntoZagraneKarty(this.tura, Integer.parseInt(id_bohater));
                            }
                            this.myOut.println("My Information");
                            this.myOut.println(this.myInformation[2] + "," + this.myInformation[3] + "," +
                                    this.myInformation[4] + "," + this.myInformation[5] + "," +
                                    this.enemyInformation[2] + "," + this.enemyInformation[3] + "," +
                                    this.enemyInformation[4] + "," + this.enemyInformation[5] + "," + this.myInformation[7]);

                            this.enemyOut.println("My Information");
                            this.enemyOut.println(this.enemyInformation[2] + "," + this.enemyInformation[3] + "," +
                                    this.enemyInformation[4] + "," + this.enemyInformation[5] + "," +
                                    this.myInformation[2] + "," + this.myInformation[3] + "," +
                                    this.myInformation[4] + "," + this.myInformation[5] + "," + this.enemyInformation[7]);
                            int myHp = Integer.parseInt(this.myInformation[2]);
                            int enemyHp = Integer.parseInt(this.enemyInformation[2]);
                            if(myHp <= 0){
                                this.myInformation[2] = "10";
                                this.myInformation[3] = "1";
                                this.myInformation[4] = "0";
                                this.myInformation[5] = "0";
                                this.myInformation[6] = "0";
                                this.myInformation[7] = "1";
                                this.enemyInformation[2] = "10";
                                this.enemyInformation[3] = "1";
                                this.enemyInformation[4] = "0";
                                this.enemyInformation[5] = "0";
                                this.enemyInformation[6] = "0";
                                this.enemyInformation[7] = "1";
                                if(this.playerName.equals("player2")){
                                    this.baza.InsertIntoWynik(1);
                                }else{
                                    this.baza.InsertIntoWynik(2);
                                }
                                this.myOut.println("Przegrana");
                                this.enemyOut.println("Wygrana");
                                this.tura = 1;
                                this.tura2 = 1;
                            }
                            if(enemyHp <= 0){
                                this.myInformation[2] = "10";
                                this.myInformation[3] = "1";
                                this.myInformation[4] = "0";
                                this.myInformation[5] = "0";
                                this.myInformation[6] = "0";
                                this.myInformation[7] = "1";
                                this.enemyInformation[2] = "10";
                                this.enemyInformation[3] = "1";
                                this.enemyInformation[4] = "0";
                                this.enemyInformation[5] = "0";
                                this.enemyInformation[6] = "0";
                                this.enemyInformation[7] = "1";
                                if(this.playerName.equals("player1")){
                                    this.baza.InsertIntoWynik(1);

                                }else{
                                    this.baza.InsertIntoWynik(2);

                                }
                                this.myOut.println("Wygrana");
                                this.enemyOut.println("Przegrana");
                                this.tura = 1;
                                this.tura2 = 1;

                            }
                        }
                        /*
                                [0] - nick
                                [1] - hero
                                [2] - hp
                                [3] - mana
                                [4] - shield
                                [5] trucizna

                                [6]  - Karta 1
                                [7]  - Karta 2
                                [8]  - Karta 3
                                [9]  - Karta 4
                                [10] - Karta 5
                                [11] - Karta 6
                                [12] - Karta 7
                                [13] - Karta 8

                         */
                        if (inputLine.equals("Get player id")) {
                            if (this.playerName.equals("player1")) {
                                myOut.println("1");
                            } else if (this.playerName.equals("player2")) {
                                myOut.println("2");
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println(playerName + " disconnected.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    mySocket.close();
                    enemySocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (this == server.client1Thread) {
                    server.client1Thread = null;
                } else if (this == server.client2Thread) {
                    server.client2Thread = null;
                }
            }
        }
    }
}
