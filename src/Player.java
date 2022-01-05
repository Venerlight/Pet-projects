public class Player extends Character{
   public String[] atkUpGrades={"Strengh","Power", "High", "Godlike" };
   public String[] defUpGrades={"Heavy Bones","Stone Skin", "Scale Armor" };
   public int numAtcUpgrades;
   public int numDefUpgrades;

   int gold, restlife, potions;
    public Player (String name){
       super (name, 100,100,0);
       this.numAtcUpgrades = 0;
       this.numDefUpgrades = 0;

       this.gold = 5;
       this.potions = 0;
       this.restlife = 1;
       chooseTrait();
    }

    @Override
    public int attack() {
        return (int) (Math.random() * (XP / 4 + numAtcUpgrades * 3 + 3) + XP/10 + numAtcUpgrades * 2 + numDefUpgrades + 1);
    }

    @Override
    public int defence() {
        return (int) (Math.random() * (XP / 4 + numDefUpgrades *3 + 3) + XP /10 + numDefUpgrades * 2 + numDefUpgrades + 1);
    }

    public void chooseTrait(){
        Game_logic.clearConsole();
        Game_logic.printHeading("Choose a trait");
        System.out.println("(1)" + atkUpGrades[numAtcUpgrades]);
        System.out.println("(2)" + defUpGrades[numDefUpgrades]);
        int input = Game_logic.readInt(" - >", 2);
        Game_logic.clearConsole();
        if(input == 1){
            Game_logic.printHeading("You chose" + " " + atkUpGrades[numAtcUpgrades] + "!");
          numAtcUpgrades++;
            Game_logic.AnythingtoContinue();
        }
        else {
            if (input == 2){
                Game_logic.printHeading("You chose" + " " + defUpGrades[numDefUpgrades] + "!");
                numDefUpgrades++;
            }
            Game_logic.AnythingtoContinue();
        }
    }
}
