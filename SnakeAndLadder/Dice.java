
import java.util.Random;



class Dice{
    Random random;
    public Dice(){
        random = new Random();
    }
    public int rollDice(){
        return random.nextInt(6) + 1;
    }
}