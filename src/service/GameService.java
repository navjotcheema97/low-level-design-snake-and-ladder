package service;

import models.Dice;
import models.Ladder;
import models.Player;
import models.Snake;
import strategies.DiceRollStrategies;
import strategies.WinningStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO
//Create separate board model that will store ladders and snakes and size
//Score of player should be part of service
//Multiple contructors to accept optional requirements


public class GameService {
    int size;
    private List<Player> players;
    private Map<Integer, Snake> idToSnake;
    private Map<Integer, Ladder> idToLadder;
    private Player currentPlayer;
    private DiceRollStrategies diceRollStrategies;
    private WinningStrategy winningStrategy;


    public GameService(int size,
                       List<Player> players,
                       List<Snake> snakes,
                       DiceRollStrategies diceRollStrategies,
                       WinningStrategy winningStrategy,
                       List<Ladder> ladders) {
        this.size = size;
        this.players = players;
        this.idToSnake = new HashMap<>();
        this.idToLadder = new HashMap<>();
        this.diceRollStrategies = diceRollStrategies ;
        this.winningStrategy = winningStrategy;
        for(Snake snake: snakes){
            idToSnake.put(snake.getStart(), snake);
        }
        for(Ladder ladder: ladders){
            idToLadder.put(ladder.getStart(), ladder);
        }
    }

    private int processDiceScore(int number){
        int newPosition = this.currentPlayer.getScore() + number;
        if(newPosition> size){
            return this.currentPlayer.getScore();
        }
        while(true) {
            if (idToSnake.containsKey(newPosition)) {
                Snake snake = idToSnake.get(newPosition);
                newPosition = snake.getEnd();
            }
            else if(idToLadder.containsKey(newPosition)){
                Ladder ladder = idToLadder.get(newPosition);
                newPosition = ladder.getEnd();
            }else{
                break;
            }
        }
        return newPosition;
    }

    public void run(){
        int currentIndex = 0;
        while(true){
            this.currentPlayer = players.get(currentIndex);
            int number = this.diceRollStrategies.roll();
            int prevPosition = this.currentPlayer.getScore();
            int newPosition = this.processDiceScore(number);
            this.currentPlayer.setScore(newPosition);
            if(this.winningStrategy.isWin(this.players, this.currentPlayer, this.size)){
                //TODO print in winning strategy and it should return end game
                System.out.println(currentPlayer.getName() + " wins the game");
                break;
            }else{
                System.out.println(
                        currentPlayer.getName() + " rolled a " + number +
                                " and moved from " + prevPosition + " to " + newPosition);
            }
            currentIndex++;
            if(currentIndex == players.size()){
                currentIndex = 0;
            }
        }
    }
}
