package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player{
    private ArrayList<Integer> emptyCells;
    private String currentState = "";
    public Computer(String symbol, String type) {
        super(symbol, type);
    }

    protected int getCoord(String boardState){
        this.currentState = boardState;
        checkEmptyCells();
        switch (this.getPlayerType()){
            case "easy":
                return easyMove();
            case "medium":
                return mediumMove();
            default:
                return 0;
        }
    }

    /* Returns a random index from the available cells of the board string */
    private void checkEmptyCells(){
        this.emptyCells = new ArrayList<>();
        for (int i = 0; i < this.currentState.length(); i++) {
            if (this.currentState.charAt(i) == '_') {
                this.emptyCells.add(i);
            }
        }
    }
    private int easyMove(){
        Random random = new Random();
        int randomIndex = random.nextInt(this.emptyCells.size());
        return this.emptyCells.get(randomIndex);
    }
    // TODO: Check the board, if it founds that the player have 2 in a row, boolean block becomes true
    /* Boolean blockMove has priority
     * Boolean winningMove comes second */
    private int mediumMove(){
        // Still needs the logic to make a medium move
        //First idea: Use the manager method to check winner and return if the sum is 2 or 3,
        // if the sum is 2 it could be processed here, else if the sum is 3 it will be processed at board
        return 0;
    }
}
