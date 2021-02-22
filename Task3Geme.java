import java.util.Random;
import java.util.Scanner;

public class Task3Geme {
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 5;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    public static int  scoreUser = 0;
    public static int  scoreIa = 0;
    public static int  scoreFull = 0;
    public static void main(String[] args) {

        initMap();
        printMap();

        while (scoreIa+scoreFull+scoreUser < 4) {

                humanTurn();
                printMap();
                if (checkWin(DOT_X)) {
                    scoreUser++;
                    printGameScore("Победил человек партия №", (scoreIa+scoreFull+scoreUser), scoreUser,  scoreIa);
                    initMap();
                    printMap();
                    continue;
                }
                if (isMapFull()) {
                    scoreFull++;
                    printGameScore("Ничья", (scoreIa+scoreFull+scoreUser), scoreUser,  scoreIa);
                    initMap();
                    printMap();
                    continue;
                }
                aiTurn();
                printMap();
                if (checkWin(DOT_O)) {
                    scoreIa++;
                    printGameScore("Победил Искуственный Интеллект", (scoreIa+scoreFull+scoreUser), scoreUser,  scoreIa);
                    initMap();
                    printMap();
                    continue;
                }
                if (isMapFull()) {
                    printGameScore("Ничья", (scoreIa+scoreFull+scoreUser), scoreUser,  scoreIa);
                    initMap();
                    printMap();
                    scoreFull++;
                    continue;
                }
        }
        printGameScore("Игра окончна.", (scoreIa+scoreFull+scoreUser), scoreUser,  scoreIa);
    }

    public static void printGameScore(String name, int party, int scoreUser, int scoreIa){

        System.out.println(name + " " + "Сыграно: " + party +" "+ "из 4");
        System.out.println("Счет: " +scoreUser +  " - человек, " +  scoreIa + " - ИИ" );

    }

    public static boolean checkWin(char symb) {
        for(int i=0, j = 0; i<map.length; i++){
            if(map[j][i] == symb && map[j+1][i] == symb && map[j+2][i]== symb &&
                    map[j+3][i]== symb && map[j+4][i] == symb) return true;
            if(map[i][j] == symb && map[i][j+1] == symb && map[i][j+2]  == symb &&
                    map[i][j+3] == symb && map[i][j+4]== symb) return true;
        }
        if(map[0][0] == symb && map[1][1] == symb && map[2][2]  == symb &&
                map[3][3] == symb && map[4][4]== symb) return true;
        if(map[0][4] == symb && map[1][3] == symb && map[2][2]  == symb &&
                map[3][1] == symb && map[4][0]== symb) return true;

        return false;
    }


    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    // Искусвенный интелект - проверка выиграшной кобинации бота(ia) в один ход
    public static char winningBotStrategy(char symbAi,   char symbNull){

        for(int i=0, j = 0; i<map.length; i++){
            // "." 0 0 0 0 (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] ==  symbAi && map[j+2][i]==  symbAi &&
                    map[j+3][i]==  symbAi && map[j+4][i] ==  symbAi) return map[j][i] = DOT_O;
            if(map[i][j] ==  symbNull  && map[i][j+1] == symbAi && map[i][j+2]  == symbAi &&
                    map[i][j+3] == symbAi && map[i][j+4]== symbAi) return  map[i][j] = DOT_O;
            // 0 "." 0 0 0 (по горизонтали и вертикали)
            if(map[j][i] == symbAi && map[j+1][i] == symbNull  && map[j+2][i]== symbAi &&
                    map[j+3][i]== symbAi && map[j+4][i] ==symbAi) return  map[j+1][i] = DOT_O;
            if(map[i][j] ==symbAi && map[i][j+1] ==  symbNull  && map[i][j+2]  == symbAi &&
                    map[i][j+3] == symbAi && map[i][j+4]== symbAi) return  map[i][j+1] = DOT_O;
            // 0 0"." 0 0 (по горизонтали и вертикали)
            if(map[j][i] ==symbAi && map[j+1][i] == symbAi && map[j+2][i]==  symbNull  &&
                    map[j+3][i]== symbAi && map[j+4][i] ==symbAi) return  map[j+2][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbAi && map[i][j+2]  == symbNull  &&
                    map[i][j+3] == symbAi && map[i][j+4]== symbAi) return  map[i][j+2] = DOT_O;
            // 0 0 0"." 0 (по горизонтали и вертикали)
            if(map[j][i] == symbAi && map[j+1][i] == symbAi && map[j+2][i]== symbAi &&
                    map[j+3][i]==  symbNull  && map[j+4][i] == symbAi) return  map[j+3][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbAi && map[i][j+2]  == symbAi &&
                    map[i][j+3] ==  symbNull  && map[i][j+4]== symbAi) return  map[i][j+3] = DOT_O;
            // 0 0 0 0 "." (по горизонтали и вертикали)
            if(map[j][i] == symbAi && map[j+1][i] == symbAi && map[j+2][i]== symbAi &&
                    map[j+3][i]== symbAi && map[j+4][i] ==  symbNull ) return  map[j+4][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbAi && map[i][j+2]  == symbAi &&
                    map[i][j+3] == symbAi && map[i][j+4]==  symbNull ) return  map[i][j+4] = DOT_O;

        }

        // "." 0 0 0 0 (по диагоналям)
        if(map[0][0] == symbNull && map[1][1] == symbAi && map[2][2]  == symbAi &&
                map[3][3] ==symbAi && map[4][4]== symbAi) return  map[0][0] = DOT_O;
        if(map[0][4] == symbNull && map[1][3] == symbAi && map[2][2]  == symbAi &&
                map[3][1] ==symbAi && map[4][0]==symbAi) return map[0][4] = DOT_O;
        // 0 "." 0 0 0   по диагоналям
        if(map[0][0] == symbAi && map[1][1] == symbNull && map[2][2]  == symbAi &&
                map[3][3] ==symbAi && map[4][4]== symbAi) return  map[1][1] = DOT_O;
        if(map[0][4] == symbAi && map[1][3] == symbNull && map[2][2]  == symbAi &&
                map[3][1] ==symbAi && map[4][0]==symbAi) return  map[1][3] = DOT_O;
        // 0 0 "." 0 0  по диагоналям
        if(map[0][0] == symbAi && map[1][1] == symbAi && map[2][2]  == symbNull &&
                map[3][3] ==symbAi && map[4][4]== symbAi) return  map[2][2] = DOT_O;
        if(map[0][4] == symbAi && map[1][3] == symbAi && map[2][2]  == symbNull &&
                map[3][1] ==symbAi && map[4][0]==symbAi) return  map[2][2] = DOT_O;
        // 0 0 0 "." 0  по диагоналям
        if(map[0][0] == symbAi && map[1][1] == symbAi && map[2][2]  == symbAi &&
                map[3][3] ==symbNull && map[4][4]== symbAi) return  map[3][3] = DOT_O;
        if(map[0][4] == symbAi && map[1][3] == symbAi && map[2][2]  == symbAi &&
                map[3][1] ==symbNull && map[4][0]==symbAi) return  map[3][1] = DOT_O;
        // 0 0 0 0 "."    по диагоналям
        if(map[0][0] == symbAi && map[1][1] == symbAi && map[2][2]  == symbAi &&
                map[3][3] ==symbAi && map[4][4]== symbNull) return  map[4][4] = DOT_O;
        if(map[0][4] == symbAi && map[1][3] == symbAi && map[2][2]  == symbAi &&
                map[3][1] ==symbAi && map[4][0]==symbNull) return  map[4][0] = DOT_O;

        return 0;

    }
    // Блокировка выиграшной компбинации соперника
    public static char strategyBlockingProgress (char symbUser, char symbNull){
        for(int i=0, j = 0; i<map.length; i++){
            // "." X x x x (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] ==  symbUser && map[j+2][i]==  symbUser &&
                    map[j+3][i]==  symbUser && map[j+4][i] ==  symbUser) return  map[j][i] = DOT_O;
            if(map[i][j] ==  symbNull  && map[i][j+1] == symbUser && map[i][j+2]  == symbUser &&
                    map[i][j+3] == symbUser && map[i][j+4]== symbUser) return  map[i][j] = DOT_O;
            // x "." X x x (по горизонтали и вертикали)
            if(map[j][i] == symbUser && map[j+1][i] == symbNull  && map[j+2][i]== symbUser &&
                    map[j+3][i]== symbUser && map[j+4][i] ==symbUser) return  map[j+1][i] = DOT_O;
            if(map[i][j] ==symbUser && map[i][j+1] ==  symbNull  && map[i][j+2]  == symbUser &&
                    map[i][j+3] == symbUser && map[i][j+4]== symbUser) return  map[i][j+1] = DOT_O;
            // x x "." X x (по горизонтали и вертикали)
            if(map[j][i] ==symbUser && map[j+1][i] == symbUser && map[j+2][i]==  symbNull  &&
                    map[j+3][i]== symbUser && map[j+4][i] ==symbUser) return  map[j+2][i] = DOT_O;
            if(map[i][j] == symbUser && map[i][j+1] == symbUser && map[i][j+2]  == symbNull  &&
                    map[i][j+3] == symbUser && map[i][j+4]== symbUser) return  map[i][j+2] = DOT_O;
            // x x x "." X (по горизонтали и вертикали)
            if(map[j][i] == symbUser && map[j+1][i] == symbUser && map[j+2][i]== symbUser &&
                    map[j+3][i]==  symbNull  && map[j+4][i] == symbUser) return  map[j+3][i] = DOT_O;
            if(map[i][j] == symbUser && map[i][j+1] == symbUser && map[i][j+2]  == symbUser &&
                    map[i][j+3] ==  symbNull  && map[i][j+4]== symbUser) return  map[i][j+3] = DOT_O;
            // x x x x "." (по горизонтали и вертикали)
            if(map[j][i] == symbUser && map[j+1][i] == symbUser && map[j+2][i]== symbUser &&
                    map[j+3][i]== symbUser && map[j+4][i] ==  symbNull ) return  map[j+4][i] = DOT_O;
            if(map[i][j] == symbUser && map[i][j+1] == symbUser && map[i][j+2]  == symbUser &&
                    map[i][j+3] == symbUser && map[i][j+4]==  symbNull ) return  map[i][j+4] = DOT_O;
        }

        // "." 0 0 0 0 (по диагоналям)
        if(map[0][0] == symbNull && map[1][1] == symbUser && map[2][2]  == symbUser &&
                map[3][3] ==symbUser && map[4][4]== symbUser) return  map[0][0] = DOT_O;
        if(map[0][4] == symbNull && map[1][3] == symbUser && map[2][2]  == symbUser &&
                map[3][1] ==symbUser && map[4][0]==symbUser) return map[0][4] = DOT_O;
        // 0 "." 0 0 0   (по диагоналям)
        if(map[0][0] == symbUser && map[1][1] == symbNull && map[2][2]  == symbUser &&
                map[3][3] ==symbUser && map[4][4]== symbUser) return  map[1][1] = DOT_O;
        if(map[0][4] == symbUser && map[1][3] == symbNull && map[2][2]  == symbUser &&
                map[3][1] ==symbUser && map[4][0]==symbUser) return  map[1][3] = DOT_O;
        // 0 0 "." 0 0   (по диагоналям)
        if(map[0][0] == symbUser && map[1][1] == symbUser && map[2][2]  == symbNull &&
                map[3][3] ==symbUser && map[4][4]== symbUser) return  map[2][2] = DOT_O;
        if(map[0][4] == symbUser && map[1][3] == symbUser && map[2][2]  == symbNull &&
                map[3][1] ==symbUser && map[4][0]==symbUser) return  map[2][2] = DOT_O;
        // 0 0 0 "." 0   (по диагоналям)
        if(map[0][0] == symbUser && map[1][1] == symbUser && map[2][2]  == symbUser &&
                map[3][3] ==symbNull && map[4][4]== symbUser) return  map[3][3] = DOT_O;
        if(map[0][4] == symbUser && map[1][3] == symbUser && map[2][2]  == symbUser &&
                map[3][1] ==symbNull && map[4][0]==symbUser) return  map[3][1] = DOT_O;
        // 0 0 0 0 "."    (по диагоналям)
        if(map[0][0] == symbUser && map[1][1] == symbUser && map[2][2]  == symbUser &&
                map[3][3] ==symbUser && map[4][4]== symbNull) return  map[4][4] = DOT_O;
        if(map[0][4] == symbUser && map[1][3] == symbUser && map[2][2]  == symbUser &&
                map[3][1] ==symbUser && map[4][0]==symbNull) return  map[4][0] = DOT_O;

        return 0;
    }

    // стратегия игры бота - победа в два хода
    public static char twoMoveStrategy(char symbAi, char symbNull ){
        for(int i=0, j = 0; i<map.length; i++){
            // "." "." 0 0 0 (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] ==  symbNull && map[j+2][i]==  symbAi &&
                    map[j+3][i]==  symbAi && map[j+4][i] ==  symbAi) return  map[j][i] = DOT_O;
            if(map[i][j] ==  symbNull  && map[i][j+1] ==symbNull && map[i][j+2]  == symbAi &&
                    map[i][j+3] == symbAi && map[i][j+4]== symbAi) return  map[i][j] = DOT_O;
            // 0 "." "." 0 0 (по горизонтали и вертикали)
            if(map[j][i] == symbAi && map[j+1][i] == symbNull  && map[j+2][i]== symbNull &&
                    map[j+3][i]== symbAi && map[j+4][i] ==symbAi) return  map[j+1][i] = DOT_O;
            if(map[i][j] ==symbAi && map[i][j+1] ==  symbNull  && map[i][j+2]  == symbNull &&
                    map[i][j+3] == symbAi && map[i][j+4]== symbAi) return  map[i][j+1] = DOT_O;
            // 0 0  "." "." 0 (по горизонтали и вертикали)
            if(map[j][i] ==symbAi && map[j+1][i] == symbAi && map[j+2][i]==  symbNull  &&
                    map[j+3][i]== symbNull && map[j+4][i] ==symbAi) return  map[j+2][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbAi && map[i][j+2]  == symbNull  &&
                    map[i][j+3] == symbNull && map[i][j+4]== symbAi) return  map[i][j+2] = DOT_O;
            //  0 0 0  "." "." (по горизонтали и вертикали)
            if(map[j][i] == symbAi && map[j+1][i] == symbAi && map[j+2][i]== symbAi &&
                    map[j+3][i]==  symbNull  && map[j+4][i] == symbNull) return  map[j+3][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbAi && map[i][j+2]  == symbAi &&
                    map[i][j+3] ==  symbNull  && map[i][j+4]== symbNull) return  map[i][j+3] = DOT_O;
            //  "." 0 0 0 "." (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] == symbAi && map[j+2][i]== symbAi &&
                    map[j+3][i]== symbAi && map[j+4][i] ==  symbNull ) return  map[j+4][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbAi && map[i][j+2]  == symbAi &&
                    map[i][j+3] == symbAi && map[i][j+4]==  symbNull ) return  map[i][j+4] = DOT_O;
            //  "." 0 "." 0 0 (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] == symbAi && map[j+2][i]== symbNull &&
                    map[j+3][i]== symbAi && map[j+4][i] ==  symbAi ) return  map[j+2][i] = DOT_O;
            if(map[i][j] == symbNull && map[i][j+1] == symbAi && map[i][j+2]  == symbNull &&
                    map[i][j+3] == symbAi && map[i][j+4]==  symbAi ) return  map[i][j+2] = DOT_O;
            //  0 "." 0 "." 0 (по горизонтали и вертикали)
            if(map[j][i] ==  symbAi && map[j+1][i] == symbNull && map[j+2][i]== symbAi &&
                    map[j+3][i]== symbNull && map[j+4][i] ==  symbAi ) return  map[j+1][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbNull && map[i][j+2]  == symbAi &&
                    map[i][j+3] == symbNull && map[i][j+4]==  symbAi ) return  map[i][j+1] = DOT_O;
            //  0  0 "." 0 "." (по горизонтали и вертикали)
            if(map[j][i] ==  symbAi && map[j+1][i] == symbAi && map[j+2][i]== symbNull &&
                    map[j+3][i]== symbAi && map[j+4][i] ==  symbNull ) return  map[j+4][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbAi && map[i][j+2]  ==symbNull &&
                    map[i][j+3] == symbAi && map[i][j+4]==  symbNull ) return  map[i][j+4] = DOT_O;
            //    0 "." 0 0"." (по горизонтали и вертикали)
            if(map[j][i] ==  symbAi && map[j+1][i] == symbNull && map[j+2][i]== symbAi &&
                    map[j+3][i]== symbAi && map[j+4][i] ==  symbNull ) return  map[j+4][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbNull && map[i][j+2]  ==symbAi &&
                    map[i][j+3] == symbAi && map[i][j+4]==  symbNull ) return  map[i][j+4] = DOT_O;
            //  "." 0 0"."0 (по горизонтали и вертикали)
            if(map[j][i] ==  symbNull && map[j+1][i] == symbAi && map[j+2][i]== symbAi &&
                    map[j+3][i]== symbNull && map[j+4][i] ==  symbAi ) return  map[j][i] = DOT_O;
            if(map[i][j] == symbNull && map[i][j+1] == symbAi && map[i][j+2]  ==symbAi &&
                    map[i][j+3] == symbNull && map[i][j+4]==  symbAi ) return  map[i][j] = DOT_O;

        }
        return 0;
    }
    // стратегия игры бота - победа в 3 хода
    public static char threeMoveStrategy (char symbAi,   char symbNull ){
        for(int i=0, j = 0; i<map.length; i++){
            // "." "." "." 0 0 (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] ==  symbNull && map[j+2][i]==   symbNull &&
                    map[j+3][i]==  symbAi && map[j+4][i] ==  symbAi) return  map[j][i] = DOT_O;
            if(map[i][j] ==  symbNull  && map[i][j+1] ==symbNull && map[i][j+2]  ==  symbNull &&
                    map[i][j+3] == symbAi && map[i][j+4]== symbAi) return  map[i][j] = DOT_O;
            // 0 "." "." "." 0 (по горизонтали и вертикали)
            if(map[j][i] == symbAi && map[j+1][i] == symbNull  && map[j+2][i]== symbNull &&
                    map[j+3][i]== symbNull && map[j+4][i] ==symbAi) return  map[j+1][i] = DOT_O;
            if(map[i][j] ==symbAi && map[i][j+1] ==  symbNull  && map[i][j+2]  == symbNull &&
                    map[i][j+3] == symbNull && map[i][j+4]== symbAi) return  map[i][j+1] = DOT_O;
            // 0 0  "." "." "." (по горизонтали и вертикали)
            if(map[j][i] ==symbAi && map[j+1][i] == symbAi && map[j+2][i]==  symbNull  &&
                    map[j+3][i]== symbNull && map[j+4][i] ==symbNull) return  map[j+2][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbAi && map[i][j+2]  == symbNull  &&
                    map[i][j+3] == symbNull && map[i][j+4]== symbNull) return  map[i][j+2] = DOT_O;
            //  "." 0 0  "." "." (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] == symbAi && map[j+2][i]== symbAi &&
                    map[j+3][i]==  symbNull  && map[j+4][i] == symbNull) return  map[j+3][i] = DOT_O;
            if(map[i][j] == symbNull  && map[i][j+1] == symbAi && map[i][j+2]  == symbAi &&
                    map[i][j+3] ==  symbNull  && map[i][j+4]== symbNull) return  map[i][j+3] = DOT_O;
            //  "." "." 0 0 "." (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] == symbNull && map[j+2][i]== symbAi &&
                    map[j+3][i]== symbAi && map[j+4][i] ==  symbNull ) return  map[j+4][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] ==symbNull && map[i][j+2]  == symbAi &&
                    map[i][j+3] == symbAi && map[i][j+4]==  symbNull ) return  map[i][j+4] = DOT_O;
            //  "." 0 "." "." 0 (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] == symbAi && map[j+2][i]== symbNull &&
                    map[j+3][i]== symbNull && map[j+4][i] ==  symbAi ) return  map[j+2][i] = DOT_O;
            if(map[i][j] == symbNull && map[i][j+1] == symbAi && map[i][j+2]  == symbNull &&
                    map[i][j+3] == symbNull && map[i][j+4]==  symbAi ) return  map[i][j+2] = DOT_O;
            //  "." "." 0 "." 0 (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] == symbNull && map[j+2][i]== symbAi &&
                    map[j+3][i]== symbNull && map[j+4][i] ==  symbAi ) return  map[j][i] = DOT_O;
            if(map[i][j] == symbNull && map[i][j+1] == symbNull && map[i][j+2]  == symbAi  &&
                    map[i][j+3] == symbNull && map[i][j+4]==  symbAi ) return  map[i][j] = DOT_O;
            //  0 "." "." 0 "." (по горизонтали и вертикали)
            if(map[j][i] == symbAi && map[j+1][i] ==symbNull && map[j+2][i]== symbNull &&
                    map[j+3][i]== symbAi && map[j+4][i] ==  symbNull) return  map[j+1][i] = DOT_O;
            if(map[i][j] == symbAi && map[i][j+1] == symbNull && map[i][j+2]  == symbNull  &&
                    map[i][j+3] == symbAi && map[i][j+4]==  symbNull ) return  map[i][j+1] = DOT_O;
            //   "." 0 "." 0 "." (по горизонтали и вертикали)
            if(map[j][i] == symbNull && map[j+1][i] ==symbAi && map[j+2][i]== symbNull &&
                    map[j+3][i]== symbAi && map[j+4][i] ==  symbNull) return  map[j][i] = DOT_O;
            if(map[i][j] == symbNull && map[i][j+1] == symbAi && map[i][j+2]  == symbNull  &&
                    map[i][j+3] == symbAi && map[i][j+4]==  symbNull ) return  map[i][j] = DOT_O;
        }
        return 0;
    }
    // генерация случайного хода бота
    public static void randomMove (){
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
         map[y][x] = DOT_O;
    }

    public static void aiTurn() {
        if(winningBotStrategy(DOT_O, DOT_EMPTY) == 0){
            if(strategyBlockingProgress (DOT_X,DOT_EMPTY)==0){
                if(twoMoveStrategy(DOT_O, DOT_EMPTY)==0){
                    if(threeMoveStrategy(DOT_O, DOT_EMPTY)==0){
                        randomMove();
                    }
                }
            }
        }
        System.out.println("Искуственный Интеллект выполнил ход");
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < DOTS_TO_WIN; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < DOTS_TO_WIN; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
