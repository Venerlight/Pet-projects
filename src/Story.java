public class Story {

    public static void printIntro() {
        Game_logic.clearConsole();
        Game_logic.printSeparator(30);
        System.out.println("BEGGINING");
        Game_logic.printSeparator(30);
        System.out.println("You are a brave knight, and after a hard fight during enemy siege you tried to hide in a Dungeon.");
        System.out.println("Choose a trait carefully!");
        Game_logic.AnythingtoContinue();

    }

    public static void Part1(){
        Game_logic.clearConsole();
        Game_logic.printSeparator(30);
        System.out.println("PART 1");
        Game_logic.printSeparator(30);
        System.out.println( Game_logic.player.name + "," + " keep fight and do not even think to surrender! Keep going!");
        Game_logic.AnythingtoContinue();
    }
}
