import java.util.Scanner;

public class Game_logic {
        static  Scanner sc = new Scanner(System.in);
        static Player player;
        public static boolean isRunning;

        public static String[] encounters = {"Battle", "Battle", "Battle", "Rest", "Rest", "Rest"};

        public static String[] enemies = {"Ogre", "Goblin", "Assasin", "Troll"};

        public static int place = 0, act = 1;
        public static String[] places = {"Throne Room", "Main Hall", "Secret Garden"};

        public static int readInt(String prompt, int userChoices){
            int input;
            do{
                System.out.println(prompt);
                try {
                    input = Integer.parseInt(sc.next());
                }catch(Exception e){
                    input = -1;
                    System.out.println("Please enter an integer!");
                }

            }while( input < 1 || input > userChoices);
                return input;
        }

        public static void clearConsole(){
            for(int i = 0; i < 100; i++)
                System.out.println();
        }
        public static void printSeparator(int n){
            for(int i = 0; i < n; i++)
                System.out.print("-");
            System.out.println( );
    }
    public static void printHeading(String title){
            printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }
    public static void AnythingtoContinue(){
        System.out.println("\nEnter something to continue the game");
        sc.next();
    }

     public static void StartGame(){
            boolean nameSet = false;
            String name;
            clearConsole();
            printSeparator(40);
            printSeparator(30);
         System.out.println("WELCOME TO THE DUNGEON");
         printSeparator(40);
         printSeparator(30);
         AnythingtoContinue();

         do{
             clearConsole();
             printHeading("What is your name friend?");
             name = sc.next();
            clearConsole();
            printHeading("Your name is" + " " + name + " " + ".\n Is that correct?");
             System.out.println("(1) Yes!");
             System.out.println("(2) NO! I want to change the name!");
             int input =  readInt("->", 2);
             if(input == 1){
                 nameSet = true;
             }
         }while(!nameSet);

         Story.printIntro();

         player = new Player(name);
         isRunning = true;
         gameLoop();

     }
     public static void checkAct(){
     if(player.XP >= 10 && act ==1){
             act = 2;
             place = 1;
             Story.printIntro();
             player.chooseTrait();
             Story.Part1();
             enemies[0]= " Evil Mercenary!";
             enemies[1]= " Goblin";
             enemies[2]= " Ogre";
             enemies[3]= " Wolf Pack";
             enemies[4]= " Elemental";
             encounters[0] = "Battle";
             encounters[1] = "Battle";
             encounters[2] = "Battle";
             encounters[3] = "Shop";
             encounters[4] = "Rest";
             player.HP = player.maxHP;
     }

    }

    public static  void randomEncounter(){
            int encounter = (int) (Math.random()* encounters.length);
            if(encounters[encounter].equals("Battle")){
                  randomBattle();
            }else if(encounters[encounter].equals("Shop")){
                  shop();
        }else if(encounters[encounter].equals("Rest")){
                   Rest();
        }
    }
     public static void  continueJourney(){
            checkAct();
            if (act!=4){
                randomEncounter();
            }
        }

    public static void characterInfo() {
        printHeading("CHARACTER INFO");
        System.out.println(player.name );
        System.out.println( "HP:"+ " " + player.HP + "/" + player.maxHP+
                        "\nXP:" + " " + player.XP + "\tGold" + " " + player.gold);
        printSeparator(20);
        System.out.println("# Potions :" + " " + player.potions);
        printSeparator(20);



        if(player.numAtcUpgrades > 0){
            System.out.println("Offensive trait:"  + player.atkUpGrades[player.numAtcUpgrades - 1]);
            printSeparator(20);
        }
        if(player.numDefUpgrades > 0){
            System.out.println("Defensive trait:" + player.defUpGrades[player.numDefUpgrades - 1]);
        }
        AnythingtoContinue();
    }


    public static void shop(){
            clearConsole();
            printHeading("You meet a mysterious stranger\n He offers you something");
            int price = (int)(Math.random() * (10 + player.potions * 3) + 10 + player.potions);
        System.out.println("Magic potion costs:" + " " + price);
        printSeparator(20);
        System.out.println("Do you want to buy one?\n(1) Yes\n(2) No");
        int input = readInt("- >", 2);
        if(input == 1){
            clearConsole();
            if(player.gold >= price){
                printHeading("You bought a magic potion for:" + " " + price + " " + "gold");
                player.potions ++;
                player.gold -= price;
            }else
                System.out.println("You do not have enough money!");
             AnythingtoContinue();
        }
    }

    public static void Rest(){
            clearConsole();
            if(player.restlife >= 1) {
                printHeading("Do you want to take a rest? (" + player.restlife + ") rest(s) left");
                System.out.println("(1) Yes\n(2) No");
                int input = readInt(" ->", 2);
                if (input == 1) {
                    clearConsole();

                    if (player.HP < player.maxHP) {
                        int HPrestored = (int) (Math.random() * (player.XP / 4 + 1) + 10);
                        player.HP = HPrestored;

                        if (player.HP > player.maxHP)
                            player.HP = player.maxHP;
                        System.out.println("You took a rest and restored health for:" + HPrestored + " " + "points");
                        player.restlife --;
                    }
                    else
                        System.out.println("You do not need a rest!");

                    AnythingtoContinue();

                }
            }

        }


            public static void randomBattle(){
            clearConsole();
            printHeading("You encountered an evil minded creature! You have to fight!");
            AnythingtoContinue();
            battle(new Enemy(enemies[(int)(Math.random() * enemies.length)], player.XP));
    }
    public static void battle(Enemy enemy){
            while(true){
                clearConsole();
                printHeading(enemy.name +"\nHP:" + enemy.HP + "/" + enemy.maxHP );
                printHeading(player.name +"\nHP:" + player.HP + "/" + player.maxHP );
                System.out.println("Choose an action:");
                printSeparator(20);
                System.out.println("(1) Fight\n(2) Use potion\n(3) Run away");
                int input = readInt("->", 3);
                if(input ==1){
                    int dmg = player.attack() - enemy.defence();
                    int dmgtook = enemy.attack() - player.defence();
                    if(dmgtook < 0){
                        dmg -= dmgtook/2;
                        dmgtook = 0;
                    }
                    if(dmg < 0)
                        dmg = 0;
                        player.HP -= dmgtook;
                        enemy.HP -= dmg;
                        clearConsole();
                        printHeading("BATTLE");
                        System.out.println("You dealt" + " " + dmg + "damage to the " + " " + enemy.name + ".");
                        printSeparator(20);
                        System.out.println("The" + " " + enemy.name + "dealt" + " " + dmgtook + " " + "to you!");
                        AnythingtoContinue();

                    if(player.HP <= 0){
                         playerDied();
                         break;
                    } else if(enemy.HP <= 0){
                        clearConsole();
                        printHeading("You defeated  the" +" " + enemy.name + "!");
                        player.XP += enemy.XP;
                        System.out.println("You earned" + " " + enemy.XP + "XP!");

                        boolean addRest = (Math.random()*5 + 1 <= 2.250);
                        int goldEarned = (int) (Math.random() * enemy.XP);
                        if(addRest){
                            player.restlife ++;
                            System.out.println("You earned a chance to get additional rest");
                        }
                        if(goldEarned > 0){
                            player.gold += goldEarned;
                            System.out.println("You collected" + " " + goldEarned + " " + "gold!");
                        }


                        AnythingtoContinue();
                        break;
                    }
                    else if (input ==2){
                        clearConsole();
                        if(player.potions > 0 && player.HP < player.maxHP ){
                                printHeading("Do you want to take a potion? ("+ player.potions + " left.)");
                            System.out.println("(1) Yes\n(2) No");
                            input= readInt("->", 2);
                            if(input == 1){
                                player.HP = player.maxHP;
                                clearConsole();
                                printHeading("You drank a magic potion! You full of power again!");
                                AnythingtoContinue();
                            }
                            if(input ==2){
                                AnythingtoContinue();
                            }
                        }else{
                            System.out.println("You dont need any potions!");
                            AnythingtoContinue();
                        }


                    }
                    else{
                        clearConsole();
                        if(Math.random() * 10  + 1 <= 3.5){
                            printHeading("You ran away from the" + " " + enemy.name);
                            AnythingtoContinue();
                            break;
                        }
                        else
                            {
                                printHeading("You did not manage to escape!");
                                   dmgtook -= enemy.attack();
                                System.out.println("In you hurre, you took " + dmgtook + "damage!");
                                AnythingtoContinue();
                                if(player.HP <= 0){
                                    playerDied();
                                }

                        }

                    }
                }
            }
    }

    public static void printMenu() {
         clearConsole();
         printHeading(places[place]);
        System.out.println("What would you like to do?");
        printSeparator(20);
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Character info");
        System.out.println("(3) Exit Game ");

    }

    public static void finalBattle(){
           battle(new Enemy("THE DEMON",90));
               isRunning = false;

    }



    public static void playerDied(){
            printHeading("YOU DIED!!!");
        System.out.println("Thank you for playing!");
        isRunning = false;
    }
     public static void gameLoop(){
            while(isRunning){
                printMenu();
                int input = readInt("->", 3);
                if(input == 1){
                    continueJourney();
                }
                else if(input == 2){
                    characterInfo();
                }
                else if(
                    isRunning = false);
            }

     }
}
