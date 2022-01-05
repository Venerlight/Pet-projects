public class Enemy extends Character{

    int playerXP;
    public  Enemy(String name, int playerXP){
        super(name,(int) (Math.random() * playerXP + playerXP/3 + 5),(int) (Math.random() * playerXP + playerXP/3 + 5),(int) (Math.random() * playerXP/4 * 2) + 1);
        this.playerXP = playerXP;
    }
    @Override
    public int attack() {
        return (int) (Math.random() * (playerXP/4 + 1)+ XP/4 + 3);
    }

    @Override
    public int defence() {
     return (int) (Math.random() * (playerXP/4 + 1)+ XP/4 + 3);

    }
}
