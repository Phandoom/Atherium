import java.util.Scanner;



public class GameField {

    static char[][] gamfield_1 = new char[20][60];
    static int hp;
    static String aWaffe;
    static String aFäikeit;



    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        hp = 100;
        aWaffe = "schwert";
        aFäikeit = "Heilung - e";
        char action;
        int zeile = 1;
        int spalte = 1;
        boolean programmbenden = false;


        do {
            if (hp == 0){
                gamfield_01_belegen();
                gamfield_1[10][25] = 'G';
                gamfield_1[10][26] = 'A';
                gamfield_1[10][27] = 'M';
                gamfield_1[10][28] = 'E';
                gamfield_1[10][29] = ' ';
                gamfield_1[10][30] = 'O';
                gamfield_1[10][31] = 'V';
                gamfield_1[10][32] = 'E';
                gamfield_1[10][33] = 'R';
                gamfield_01_out();
                programmbenden = true;
            }
            else {
                gamfield_01_belegen();
                gamfield_1[zeile][spalte] = 'P';
                gamfield_01_out();
                System.out.println("Wählen sie ihre aktion");
                action = reader.next().toLowerCase().charAt(0);
                if (action == 'w') {
                    zeile--;
                } else if (action == 'a') {
                    spalte--;
                } else if (action == 's') {
                    zeile++;
                } else if (action == 'd') {
                    spalte++;
                } else if (action == 'x') {
                    System.out.println("Programm wirt beendeet");
                    programmbenden = true;
                }
                else  if (action == 'g'){
                    hp = hp - 10;
                }
                else if (action == 'e'){
                    if (hp<100){
                        System.out.println("Heilung 10");
                        hp = hp + 10;
                        if (hp>=90){
                            hp=100;
                        }
                    }

                }
                else {
                    System.out.println("Falsche Taste");
                }
                wipe();
            }
        }while (programmbenden != true);




    }
    public static void gamfield_01_belegen (){
        for (int zeile = 0; zeile < 20; zeile++){
            // alle Spalten durchlaufen
            for (int spalte =0; spalte <60; spalte++){
                // startwert jedem Feld zuweisen
                gamfield_1[zeile][spalte] = ' ';
            }
        }
        for (int spalte =0; spalte <60; spalte++){
            // startwert jedem Feld zuweisen
            gamfield_1[0][spalte] = '#';
        }
        for (int zeile = 0; zeile < 20; zeile++) {
            gamfield_1[zeile][0] = '#';
        }
        for (int zeile = 0; zeile < 20; zeile++) {
            gamfield_1[zeile][59] = '#';
        }
        for (int spalte =0; spalte <60; spalte++){
            // startwert jedem Feld zuweisen
            gamfield_1[19][spalte] = '#';
        }



    }
    public static void wipe (){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    public static void gamfield_01_out (){
        for (int zeile = 0; zeile < 20; zeile++){
            // alle Spalten durchlaufen
            for (int spalte =0; spalte < 60; spalte++){
                System.out.printf("%2c", gamfield_1[zeile][spalte]);
            }
            System.out.println();
        }
        String slive = null;
        if (hp >= 100){
            slive = " ##########";
        }
        else if (hp >= 90){
            slive = " ######### ";
        }
        else if (hp >= 80){
            slive = " ########  ";
        }
        else if (hp >= 70){
            slive = " #######   ";
        }
        else if (hp >= 60){
            slive = " ######    ";
        }
        else if (hp >= 50){
            slive = " #####     ";
        }
        else if (hp >= 40){
            slive = " ####      ";
        }
        else if (hp >= 30){
            slive = " ###       ";
        }
        else if (hp >= 20){
            slive = " ##        ";
        }
        else if (hp >= 10){
            slive = " #         ";
        }
        else if (hp >= 0){
            slive = "           ";
        }


        System.out.println(slive + "                          "+ " Ausgerüstete Waffe: " + aWaffe + "                          " + " Aktive Fähikeit: " + aFäikeit);

    }

}


