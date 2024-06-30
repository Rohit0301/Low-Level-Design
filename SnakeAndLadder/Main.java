
import java.util.HashMap;
import java.util.Scanner;

class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Integer> snakes = new HashMap<>();
        HashMap<Integer, Integer> ladders = new HashMap<>();
        // taking snake positios input
        int noOfSnakes = sc.nextInt();
        for(int i =0;i<noOfSnakes;i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            snakes.put(start, end);
        }

        // taking ladder positios input
        int noOfLadders = sc.nextInt();
        for(int i =0;i<noOfLadders;i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            ladders.put(start, end);
        }

        // initiate the game
        GameService game = new GameService(100, snakes, ladders);

        // taking players name input
        int noOfPlayers = sc.nextInt();
        for(int i =0;i<noOfPlayers;i++){
            String name = sc.next();
            game.setPlayer(name);
        }

        game.startGame();
    }
}