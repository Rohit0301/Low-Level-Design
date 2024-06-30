
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class GameService{
    Dice dice;
    Deque<Player> players;
    SnakeAndLadderBoard board;

    public GameService(int size, HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders){
        players = new ArrayDeque<>();
        dice  = new Dice();
        board = new SnakeAndLadderBoard(size, snakes, ladders);
    }

    public void setPlayer(String name){
        players.add(new Player(name));
    }

    public void startGame(){
        while(true){
            Player currPlayer = players.removeFirst();
            int diceNumber = dice.rollDice();
            int playerNextPosition = currPlayer.getPosition() + diceNumber;

            if(playerNextPosition > 100) {
                System.out.println(currPlayer.getName()+" cannot move got number "+ diceNumber+ " current position "+ currPlayer.getPosition());
                players.addLast(currPlayer);
                continue;
            }

            playerNextPosition = board.getNextPosition(currPlayer, playerNextPosition);
            if(playerNextPosition == 100){
                System.out.println("Player " + currPlayer.getName() + " won the game wwwwwwwwwwwwwwwwwwwwwwwwwwww");
                if(players.size() == 1) break;
                continue;
            }
            currPlayer.setPosition(playerNextPosition);
            if(diceNumber == 6){
                System.err.println("player " + currPlayer.getName() + " got one more chance");
                players.addFirst(currPlayer);
            }
            else
                players.addLast(currPlayer);
            

        }
    }
}