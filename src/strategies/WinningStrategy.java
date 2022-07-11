package strategies;

import models.Player;

import java.util.List;

public interface WinningStrategy {

    boolean isWin(List<Player> players, Player currentPlayer, int winningScore);

}
