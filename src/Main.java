import models.Ladder;
import models.Player;
import models.Snake;
import service.GameService;
import strategies.DiceRollStrategies;
import strategies.FirstPlayerWinStrategy;
import strategies.SimpleDiceRollStrategy;
import strategies.WinningStrategy;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Player p1 = new Player("1", "Gaurav");
        Player p2 = new Player("2", "Sagar");
        List<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);

        Snake s1 = new Snake("1", 62, 5);
        Snake s2 = new Snake("1", 33, 6);
        Snake s3 = new Snake("1", 49, 9);
        Snake s4 = new Snake("1", 88, 16);
        Snake s5 = new Snake("1", 41, 20);
        List<Snake> snakes = new LinkedList<>();
        snakes.add(s1);
        snakes.add(s2);
        snakes.add(s3);
        snakes.add(s4);
        snakes.add(s5);

        Ladder l1 = new Ladder("1", 2, 37);
        Ladder l2 = new Ladder("1", 27, 46);
        Ladder l3 = new Ladder("1", 10, 32);
        Ladder l4 = new Ladder("1", 51, 68);
        Ladder l5 = new Ladder("1", 61, 79);
        List<Ladder> ladders = new LinkedList<>();
        ladders.add(l1);
        ladders.add(l2);
        ladders.add(l3);
        ladders.add(l4);
        ladders.add(l5);

        WinningStrategy winningStrategy = new FirstPlayerWinStrategy();
        DiceRollStrategies diceRollStrategies = new SimpleDiceRollStrategy();

        GameService gameService = new GameService(100, players, snakes, diceRollStrategies, winningStrategy, ladders);

        gameService.run();
    }

}
