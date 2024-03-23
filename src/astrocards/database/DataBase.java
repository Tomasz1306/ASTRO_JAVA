package astrocards.database;


import java.sql.*;
/**
 * Klasa tworzaca i obslugujaca baze danych
 *
 * @author Tomasz Bogdan i Oskar Andrzejewski
 */
public class DataBase {

    private String url;
    private String name;
    private String password;

    private Statement state;
    private ResultSet res;
    private Connection con;

    private int ilosc;

    private boolean zajete;
    private boolean pozwolenie1;
    private boolean pozwolenie2;

    public DataBase() throws SQLException {
        this.ConnectToDataBase();
    }

    public void ConnectToDataBase() throws SQLException {

        this.url = "jdbc:oracle:thin:@localhost:1521/xe";
        this.name = "system";
        this.password = "system";

        this.con = java.sql.DriverManager.getConnection(url, name, password);
        this.state = con.createStatement();
        this.ilosc = 1;
        this.zajete = false;
        this.pozwolenie1 = false;
        this.pozwolenie2 = false;
    }

    public void CreateTable() throws SQLException {
        this.state.executeQuery("CREATE TABLE Bohater(Id_bohater integer, CONSTRAINT Id_bohater_pk PRIMARY KEY (Id_bohater))");

        this.state.executeQuery("CREATE TABLE Karty(Id_karty integer," +
                "    CONSTRAINT Id_karty_pk PRIMARY KEY (Id_karty)," +
                "    Id_bohater integer," +
                "    CONSTRAINT Id_Bohater_fk_1 FOREIGN KEY (Id_bohater) REFERENCES Bohater(Id_bohater))");
        this.state.executeQuery("CREATE TABLE Gracz(Id_gracz integer," +
                "   CONSTRAINT Id_gracz_pk PRIMARY KEY (Id_gracz)," +
                "   Nick varchar2(100)," +
                "   Id_bohater integer," +
                "   CONSTRAINT Id_Bohater_fk_2 FOREIGN KEY (Id_bohater) REFERENCES Bohater(Id_bohater))");

        this.state.executeQuery("CREATE TABLE Tura(Id_tura integer, CONSTRAINT Id_tura_pk PRIMARY KEY (Id_tura), Id_gracz_1 numeric," +
                "    tura_numer integer," +
                "    CONSTRAINT Id_gracz_fk_1 FOREIGN KEY (Id_gracz_1) REFERENCES Gracz(Id_gracz),Id_gracz_2 integer," +
                "    CONSTRAINT Id_gracz_fk_2 FOREIGN KEY (Id_gracz_2) REFERENCES Gracz(Id_gracz),Zdrowie integer)");

        this.state.executeQuery("CREATE TABLE Zagrane_Karty(Id_zagrane_karty integer," +
                "    CONSTRAINT Id_zagrana_karta_pk PRIMARY KEY (Id_zagrane_karty)," +
                "    Id_tura," +
                "    CONSTRAINT Id_tura_fk FOREIGN KEY (Id_tura) REFERENCES Tura(Id_tura)," +
                "    Id_karty integer," +
                "    CONSTRAINT Id_karty_fk FOREIGN KEY (Id_karty) REFERENCES Karty(Id_karty))");

        this.state.executeQuery("CREATE TABLE Wyniki(Id_wynik integer, CONSTRAINT Id_wynik_pk PRIMARY KEY (Id_wynik)," +
                "   Id_gracz integer, CONSTRAINT  Id_gracz_fk FOREIGN KEY (Id_gracz) REFERENCES  Gracz(Id_gracz))");


    }

    public void createSeq() throws SQLException {
        this.state.executeQuery("Create SEQUENCE SEK1 START WITH 1");
        this.state.executeQuery("Create SEQUENCE SEK2 START WITH 1");
    }
    public void dropSeq() throws  SQLException {
        this.state.executeQuery("DROP SEQUENCE SEK1");
        this.state.executeQuery("DROP SEQUENCE SEK2");
    }
    public void DropTable() throws SQLException {

        this.state.executeQuery("DROP TABLE Zagrane_Karty");
        this.state.executeQuery("DROP TABLE Tura");
        this.state.executeQuery("DROP TABLE Wyniki");
        this.state.executeQuery("DROP TABLE Gracz");
        this.state.executeQuery("DROP TABLE Karty");
        this.state.executeQuery("DROP TABLE Bohater");
    }


    public void InsertIntoHeros() throws SQLException {
        this.state.executeQuery("INSERT INTO Bohater(Id_bohater) VALUES(1)");
        this.state.executeQuery("INSERT INTO Bohater(Id_bohater) VALUES(2)");
        this.state.executeQuery("INSERT INTO Bohater(Id_bohater) VALUES(3)");
        this.state.executeQuery("INSERT INTO Bohater(Id_bohater) VALUES(4)");
    }

    public void InsertIntoKarty() throws SQLException {

        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(11,1)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(12,1)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(13,1)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(14,1)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(15,1)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(16,1)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(17,1)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_Bohater) VALUES(18,1)");

        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(21,2)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(22,2)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(23,2)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(24,2)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(25,2)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(26,2)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(27,2)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(28,2)");

        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(31,3)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(32,3)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(33,3)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(34,3)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(35,3)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(36,3)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(37,3)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(38,3)");

        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(41,4)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(42,4)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(43,4)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(44,4)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(45,4)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(46,4)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(47,4)");
        this.state.executeQuery("INSERT INTO Karty(Id_karty, Id_bohater) VALUES(48,4)");

    }

    public void InsertIntoGracz(int gracz, String nick, int bohater) throws SQLException {
        this.state.executeQuery("INSERT INTO Gracz(Id_Gracz, nick, Id_bohater) VALUES(" + gracz + " ,'"+nick+"'," + bohater + ")");
    }

    public void InsertIntoTura(int tura, int gracz1, int gracz2, int zdrowie) throws SQLException {
        this.res = this.state.executeQuery("SELECT SEK1.NEXTVAL FROM DUAL");
        int id = 0;
        if(this.res.next()){
            id = this.res.getInt(1);
        }
        this.state.executeQuery("INSERT INTO Tura(ID_TURA,tura_numer, ID_GRACZ_1, ID_GRACZ_2, ZDROWIE) VALUES (" + id + ", " + tura + ", " + gracz1 + ", " + gracz2 + ", " + zdrowie + ")");
    }

    public void InsertIntoZagraneKarty(int tura, int karty) throws SQLException {
        String query = "SELECT ID_TURA FROM TURA WHERE tura_numer = " + tura;
        this.res = this.state.executeQuery(query);
        int id_tura = 0;
        if(this.res.next()){
            id_tura = this.res.getInt(1);
        }
        this.res = this.state.executeQuery("SELECT SEK2.NEXTVAL FROM DUAL");
        int id_zagrane_karty = 0;
        if(this.res.next()){
            id_zagrane_karty = this.res.getInt(1);
        }
        this.state.executeQuery("INSERT INTO Zagrane_Karty(id_zagrane_karty ,id_tura, id_karty) VALUES ("+ id_zagrane_karty + "," + id_tura + "," + karty + ")");
    }

    public void InsertIntoWynik(int gracz) throws SQLException {
        this.res = this.state.executeQuery("SELECT COUNT(*) FROM Wyniki");
        int ilosc = 0;
        while(res.next()){
            ilosc++;
            System.out.println("Ilosc wierszy w tabeli wynik: "  + ilosc);
        }

        this.state.executeQuery("INSERT INTO Wyniki(Id_wynik, id_gracz) VALUES (" + this.ilosc + "," + gracz + ")");
        this.ilosc++;
    }

    public String SelectWyniki11Name() throws SQLException {
        if(this.zajete == false){
            System.out.println("Uruchomiono Select 11");
            this.zajete = true;
            this.pozwolenie1 = true;
            this.state.executeQuery("CREATE  TABLE Wyniki1 AS (SELECT ID_WYNIK, NICK FROM WYNIKI JOIN GRACZ ON GRACZ.ID_GRACZ = WYNIKI.ID_GRACZ)");
            this.state.executeQuery("COMMIT");
            String dane[] = new String[this.ilosc-1];
            String DaneString = "";
            int i = 0;
            ResultSet result1 = this.state.executeQuery("SELECT * FROM Wyniki1");

            while(result1.next()){
                dane[i] = result1.getString(1);
                i++;
            }
            for(int j =0; j<dane.length;j++){
                if(j != 0){
                    DaneString += ",";
                }
                DaneString += dane[j];
            }
            if(dane.length == 1){
                DaneString += ",";
            }
            return DaneString;
        }else{
            return "brak";
        }
    }
    public String SelectWyniki12Name() throws SQLException {
        if(this.pozwolenie1 == true){
            System.out.println("Uruchomiono Select 12");
            //this.state.executeQuery("CREATE  TABLE Wyniki1 AS (SELECT * FROM WYNIKI)");
            String dane[] = new String[this.ilosc-1];
            String DaneString = "";
            int i = 0;
            ResultSet result2 = this.state.executeQuery("SELECT * FROM Wyniki1");

            while(result2.next()){
                dane[i] = result2.getString(2);
                i++;
            }
            for(int j =0; j<dane.length;j++){
                if(j != 0){
                    DaneString += ",";
                }
                DaneString += dane[j];
            }
            if(dane.length == 1){
                DaneString += ",";
            }
            this.state.executeQuery("DROP TABLE Wyniki1");
            this.pozwolenie1 = false;
            this.zajete = false;
            return DaneString;
        }else{
            return "brak";
        }
    }



    public String SelectWyniki21Name() throws SQLException {
        if(this.zajete == false){
            System.out.println("Uruchomiono Select 21");
            this.zajete = true;
            this.state.executeQuery("CREATE  TABLE Wyniki2 AS (SELECT ID_WYNIK, NICK FROM WYNIKI JOIN GRACZ ON GRACZ.ID_GRACZ = WYNIKI.ID_GRACZ)");
            this.state.executeQuery("COMMIT");
            String dane[] = new String[this.ilosc-1];
            String DaneString = "";
            int i = 0;
            ResultSet result3 = this.state.executeQuery("SELECT * FROM Wyniki2");

            while(result3.next()){
                dane[i] = result3.getString(1);
                i++;
            }
            for(int j =0; j<dane.length;j++){
                if(j != 0){
                    DaneString += ",";
                }
                DaneString += dane[j];
            }
            if(dane.length == 1){
                DaneString += ",";
            }
            this.pozwolenie2 = true;
            return DaneString;
        }else{
            return "brak";
        }

    }
    public String SelectWyniki22Name() throws SQLException {
        if(this.pozwolenie2 == true){
            System.out.println("Uruchomiono Select 22");
            //this.state.executeQuery("CREATE  TABLE Wyniki2 AS (SELECT * FROM WYNIKI)");
            String dane[] = new String[this.ilosc-1];
            String DaneString = "";
            int i = 0;
            ResultSet result4 = this.state.executeQuery("SELECT * FROM Wyniki2");

            while(result4.next()){
                dane[i] = result4.getString(2);
                i++;
            }
            for(int j =0; j<dane.length;j++){
                if(j != 0){
                    DaneString += ",";
                }
                DaneString += dane[j];
            }
            if(dane.length == 1){
                DaneString += ",";
            }
            this.state.executeQuery("DROP TABLE Wyniki2");
            this.zajete= false;
            this.pozwolenie2 = false;
            return DaneString;
        }else{
            return "brak";
        }

    }
}
