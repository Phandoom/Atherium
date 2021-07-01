import java.sql.SQLException;
import java.util.Scanner;

import DB.IRepositoryPlayer;
import DB.RepositoryPlayerDB;
import models.*;
import models.Enums.classes;
import models.Enums.races;
import models.window.Game;


public class mainProgram {
    static Scanner reader = new Scanner(System.in);


    public static void main(String[] args) {
        String loginOrRegister;
        boolean ingame = false;
        IRepositoryPlayer rep;
        try{
        rep = new RepositoryPlayerDB();
        rep.open();


        System.out.print("Would you like to login or to register? ");
        loginOrRegister = reader.next();
        //Überprüfen, ob der Nutzer sich registrieren, oder sich einloggen will
        if (loginOrRegister.equals("register")){
            register p2 = new register("Lars1", "Schätzle1234", races.dwarf,classes.knight);
            if (rep.createNewPlayer(p2)){
                System.out.println("Spieler wurde erstellt!");
                ingame = true;
            }
            else {
                System.out.println("Spieler wurde nicht erstellet!");
                ingame = false;
            }
        }
        else if (loginOrRegister.equals("login")){
            register l = new register();
            System.out.print("Please type in your username: ");
            l.setName(reader.next());
            System.out.print("Please type in your password: ");
            l.setPassword(reader.next());
            //l.setUserId();
            if (rep.login(l)){
                System.out.println("You are logged in");
                ingame = true;
            }
            else {
                System.out.println("You are not logged in");
                ingame = false;
            }
        }
        else if (loginOrRegister.equals("test")){
            ingame = true;
        }
        else {
            ingame = true;
        }


        //getUserById
        /*System.out.println("\n\nSpieler ausgeben:\n");
        register p1 = rep.getPlayerById("Buri_Minnos123");
        if (p1 == null){
            System.out.println("Spieler nicht vorhanden");
        }
        else {
            System.out.println("Spieler vorhanden");
            System.out.println(p1);
        }
        */

        rep.close();
        }
        catch (ClassNotFoundException e) {
        System.out.println("MySQL Treiber konnte nicht geladen werden!");
        }
        catch (SQLException e) {
            System.out.println("Datenbankfehler!" + e.getMessage());
        }

        if (ingame){
            Game.main();
        }
    }

}
