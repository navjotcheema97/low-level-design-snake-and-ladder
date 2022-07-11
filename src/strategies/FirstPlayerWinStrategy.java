package strategies;

import models.Player;

import java.util.List;

public class FirstPlayerWinStrategy implements WinningStrategy {
    @Override
    public boolean isWin(List<Player> players, Player currentPlayer, int winningScore) {
        return currentPlayer.getScore() == winningScore;
    }
}
