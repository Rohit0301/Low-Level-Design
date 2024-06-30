import java.util.HashMap;

public class SnakeAndLadderBoard {
    private final int size;
    private final HashMap<Integer, Integer> snakes;
    private final HashMap<Integer, Integer> ladders;

    public SnakeAndLadderBoard(int size,HashMap<Integer, Integer> snakes,HashMap<Integer, Integer> ladders) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public int getSize() {
        return size;
    }

    private int getSnake(int start){
        if(snakes.containsKey(start)){
            return snakes.get(start);
        }
        return -1;
    }

    private int getLadder(int start){
        if(ladders.containsKey(start)){
            return ladders.get(start);
        }
        return -1;
    }

    public int getNextPosition(Player player, int position){
        int prevPostion = player.getPosition();
        String name = player.getName();
        int diceNumber = position - prevPostion;
        int snakePosition = getSnake(position);
        int ladderPosition = getLadder(position);
        if(snakePosition!=-1){
            System.out.println(name+" rolled the dice got "+ diceNumber +" Snake bit at position: "+ position+ " move from " + prevPostion +" to "+ snakePosition+" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return snakePosition;
        }

        if(ladderPosition!=-1){
            System.out.println(name+" rolled the dice got "+ diceNumber +" Ladder encounter at position: "+ position+ " move from " + prevPostion +" to "+ ladderPosition +" /////////////////////////////");
            return ladderPosition;
        }
          System.out.println(name+" rolled the dice got "+ diceNumber +" move from "+prevPostion +" to "+ position);
        return position;
    }

}