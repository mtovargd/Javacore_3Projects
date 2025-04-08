package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player{
    private ArrayList<Integer> emptyCells;
    private String currentState = "";
    private final Manager manager;
    int decidedMove = -1;
    int winMove = -1;

    public Computer(Manager manager, String symbol, String type) {
        super(symbol, type);
        this.manager = manager;
    }

    public int getCoord(String boardState){
        this.currentState = boardState;
        this.checkBoardState();
        switch (this.getPlayerType()){
            case "easy":
                return easyMove();
            case "medium":
                return mediumMove();
            default:
                return easyMove();
        }
    }
/** This method runs every time that the computer is requested with a move
 *  Set the indexes where there are empty cells where a move can be done
 */
    private void checkBoardState(){
        this.emptyCells = new ArrayList<>();
        this.decidedMove = -1;
        this.winMove = -1;
        for (int i = 0; i < this.currentState.length(); i++) {
            if (this.currentState.charAt(i) == '_') {
                this.emptyCells.add(i);
                int cellMove = manager.checkWinner(currentState, i);
                if (cellMove == -2){
                    this.winMove = i;
                } else if (cellMove == 2){
                    this.decidedMove = i;
                }
            }
        }
    }
    /* Returns a random index from the available cells of the board string */
    private int easyMove(){
        Random random = new Random();
        int randomIndex = random.nextInt(this.emptyCells.size());
        return this.emptyCells.get(randomIndex);
    }
    // TODO: Check the board, if it founds that the player have 2 in a row, boolean block becomes true
    private int mediumMove(){
        // First idea: Use the manager method to check winner and return if the sum is 2 or 3
        /* Priority to win move, if there's no win move, block move has priority */
        if (winMove != -1){
            return winMove;
        }else if (decidedMove != -1){
            return decidedMove;
        }
        /* If there is no terminal move returns a random */
        return easyMove();
    }


}
