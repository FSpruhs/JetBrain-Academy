package Project2;

public class Player {
    private String playerName;
    private BattleField playerBattlefield;
    private BattleField playerFogOfWarBattlefield;
    private boolean winner = false;

    public Player(String playerName) {
        this.playerName = playerName;
        this.playerBattlefield = new BattleField();
        this.playerFogOfWarBattlefield = new BattleField();
    }

    public BattleField getPlayerBattlefield() {
        return playerBattlefield;
    }

    public BattleField getPlayerFogOfWarBattlefield() {
        return playerFogOfWarBattlefield;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isWinner() {
        return winner;
    }
}

