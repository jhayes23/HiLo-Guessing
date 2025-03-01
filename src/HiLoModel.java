import java.util.*;

public class HiLoModel {
    private int target;
    private final Random randominator;
    private final TreeSet<Integer> previousGuesses;

    public HiLoModel() {
        randominator = new Random();
        previousGuesses = new TreeSet<>();
        generateNewNumber();
    }

    public void generateNewNumber() {
        if (previousGuesses != null) {
            previousGuesses.clear();
        }
        target = randominator.nextInt(100) + 1;
        System.out.println("Generated New number: " + target);
    }

    public boolean checkNumber(int number) {
        if (!previousGuesses.contains(number)) {
            previousGuesses.add(number);
            return this.target == number;
        }
        return false;
    }
    public int getTarget() {return target;}

    public boolean previouslyGuessed(int number) {
        return previousGuesses.contains(number);
    }

    public int getNumberOfGuesses() {
        return previousGuesses.size();
    }
    public int getMinNumber() {
        Integer min = 1;
        if(!previousGuesses.isEmpty()){
            min = previousGuesses.floor(target);
        }
        return min != null ? min : 1;

    }
    public int getMaxNumber() {
        Integer max = 100;
        if(!previousGuesses.isEmpty()){
            max = previousGuesses.ceiling(target);
        }
        return max != null ? max : 100;
    }
}
