// GAME 4 --> Dungeon Rush.

import java.util.Random;
import java.util.Scanner;

class DungeonRush {

    static int MAX_LEVEL = 3;
    static Random RANDOM = new Random();
    static Scanner sc = new Scanner(System.in);
    static GameZone gz;

    void main(String[] args) {
        int level = 1;
        boolean gameEnded = false;

        while (level <= MAX_LEVEL && !gameEnded) {
            System.out.println(gz.ANSI_GREEN+"\n--- Level " + level + " ---" +gz.ANSI_RESET);
            gameEnded = exploreLevel(level);
            level++;
        }

        if(gameEnded==true){
            return;
        }       
    }

    static boolean exploreLevel(int level) {
        switch (level) {
            case 1:
                return exploreLevel1();
            case 2:
                return exploreLevel2();
            case 3:
                return exploreLevel3();
        }
        return true;
    }

    static boolean exploreLevel1() {
        System.out.println(gz.ANSI_YELLOW+ "Choose your path: ");
        System.out.println("1. Go left");
        System.out.println("2. Go right" +gz.ANSI_RESET);        
        boolean flag = true;
        while(flag){
            System.out.print(gz.ANSI_GREEN+"Enter your choice (1-2): " +gz.ANSI_RESET);
            int choice = sc.nextInt();
            System.out.println();

            if(choice==1 || choice==2){
                flag = false;
                switch (choice) {
                    case 1:
                        return exploreLeftPathLevel1();
                    case 2:
                        return exploreRightPathLevel1();
                }
            }
            else{
                System.out.println(gz.ANSI_RED+ "Enter Valid Choice." +gz.ANSI_RESET);
                continue;
            }
        }
        return true;
    }

    static boolean exploreLeftPathLevel1() {
        System.out.println(gz.ANSI_BLUE+"You enter a large chamber.");
        System.out.println("In the center, there is an ancient book." +gz.ANSI_RESET);
        System.out.println(gz.ANSI_YELLOW+ "What do you want to do?");
        System.out.println("1. Read the book");
        System.out.println("2. Leave the chamber" +gz.ANSI_RESET);
        boolean flag = true;
        while(flag){
            System.out.print(gz.ANSI_GREEN+ "Enter your choice (1-2): " +gz.ANSI_RESET);
            int bookChoice = sc.nextInt();

            if (bookChoice == 1) {
                flag = false;
                return readBookOutcome();
            }
            else if (bookChoice == 2) {
                flag = false;
                return leaveChamberOutcome();
            }
            else{
                System.out.println(gz.ANSI_RED+ "Enter Valid Choice." +gz.ANSI_RESET);
                continue;
            }
        }
        return true; // Game ended
    }

    static boolean readBookOutcome() {
        if (RANDOM.nextInt(10) < 7) {
            System.out.println(gz.ANSI_YELLOW+ "As you start reading, the room shakes.");
            System.out.println("You gain ancient knowledge and safely exit the chamber." +gz.ANSI_RESET);
            return false;
        }
        else {
            System.out.println(gz.ANSI_RED+ "As you read, the room fills with darkness, and you lose consciousness." +gz.ANSI_RESET);
            endGame(0);
            return true; // Game ended
        }
    }

    static boolean leaveChamberOutcome() {
        if (RANDOM.nextInt(10) < 6) {
            System.out.println(gz.ANSI_YELLOW+ "As you leave the chamber, you hear a roar." +gz.ANSI_RESET);
            System.out.println(gz.ANSI_RED+ "Suddenly, a rockslide blocks your way." +gz.ANSI_RESET);
            endGame(0);
            return true;
        }
        else {
            System.out.println(gz.ANSI_YELLOW+ "You decide to leave the chamber and continue exploring.");
            System.out.println("The cave twists and turns, leading you to a hidden passage." +gz.ANSI_RESET);
            return false;
        }
    }

    static boolean exploreRightPathLevel1() {
        System.out.println(gz.ANSI_BLUE+ "You enter a dark passage." +gz.ANSI_RESET);
        System.out.println(gz.ANSI_YELLOW+ "What do you want to do?");
        System.out.println("1. Continue through the passage");
        System.out.println("2. Retreat and go back" +gz.ANSI_RESET);
        System.out.println();
        boolean flag = true;
        while(flag){
            System.out.print(gz.ANSI_GREEN+ "Enter your choice (1-2): " +gz.ANSI_RESET);
            int darkPassageChoice = sc.nextInt();

            if (darkPassageChoice == 1) {
                flag = false;
                System.out.println(gz.ANSI_BLUE+ "You find a hidden exit leading to a well-lit chamber." +gz.ANSI_RESET);
                return false;
            }
            else if (darkPassageChoice == 2) {
                flag = false;
                if (RANDOM.nextInt(10) < 4) {
                    System.out.println(gz.ANSI_BLUE+ "You decide to retreat and return to the cave entrance." +gz.ANSI_RESET);
                    return false;
                }
                else {
                    System.out.println(gz.ANSI_RED+ "As you exit, the cave entrance collapses." +gz.ANSI_RESET);
                    endGame(0);
                    return true; // Game ended
                }
            }
            else{
                System.out.println(gz.ANSI_RED+ "Enter Valid Choice." +gz.ANSI_RESET);
                continue;
            }
        }
        return true;
    }

    static boolean exploreLevel2() {
        System.out.println(gz.ANSI_BLUE+ "You find yourself in a narrow tunnel with mysterious markings on the walls." +gz.ANSI_RESET);
        System.out.println(gz.ANSI_YELLOW+ "What do you want to do?");
        System.out.println("1. Follow the markings");
        System.out.println("2. Find your way without following the markings");
        System.out.println("3. Enter the secret code on a keypad next to the markings" +gz.ANSI_RESET);
        System.out.println();
        boolean flag = true;
        while(flag){
            System.out.print(gz.ANSI_GREEN+ "Enter your choice (1-3): " +gz.ANSI_RESET);
            int tunnelChoice = sc.nextInt();
            if(tunnelChoice==1 || tunnelChoice==2 || tunnelChoice==3){
                flag = false;
                switch (tunnelChoice) {
                    case 1:
                        return exploreMarkingsOutcome();
                    case 2:
                        return findYourWayOutcome();
                    case 3:
                        return enterSecretCodeOutcome();
                }
            }
            else{
                System.out.println(gz.ANSI_RED+ "Enter Valid choice." +gz.ANSI_RESET);
                continue;
            }
        }
        return true;
    }

    static boolean exploreMarkingsOutcome() {
        if (RANDOM.nextInt(10) < 5) {
            System.out.println(gz.ANSI_BLUE+ "You carefully follow the markings and discover an underground path.");
            System.out.println("You found an exit to the tunnel." +gz.ANSI_RESET);
            return false;
        }
        else {
            System.out.println(gz.ANSI_RED+ "As you follow the markings, they lead you into a dead-end." +gz.ANSI_RESET);
            endGame(1);
            return true; // Game ended
        }
    }

    static boolean findYourWayOutcome() {
        if (RANDOM.nextInt(10) < 6) {
            System.out.println(gz.ANSI_BLUE+ "You decide not to rely on the markings and navigate the maze successfully." +gz.ANSI_RESET);
            return false;
        }
        else {
            System.out.println(gz.ANSI_RED+ "Without the markings, you get lost in the twisting tunnels." +gz.ANSI_RESET);
            endGame(1);
            return true; // Game ended
        }
    }

    static boolean enterSecretCodeOutcome() {
        System.out.println(gz.ANSI_LIGHT_PURPLE+ "Hint: The code is a famous mathematical constant. It represents the ratio of a circle's circumference to its diameter. It is a 3-digit decimal code." +gz.ANSI_RESET);
        System.out.print(gz.ANSI_YELLOW+ "Enter the Secret Code: " +gz.ANSI_RESET);
        double codeEntry = sc.nextDouble();

        if (codeEntry == 3.14) {
            System.out.println(gz.ANSI_BLUE+ "You enter the correct code, and a hidden door opens." +gz.ANSI_RESET);
            return false;
        }
        else {
            System.out.println(gz.ANSI_RED+ "The code is incorrect, and a security alarm goes off." +gz.ANSI_RESET);
            endGame(1);
            return true; // Game ended
        }
    }

    static boolean exploreLevel3() {
        System.out.println(gz.ANSI_BLUE+ "You find yourself in a area with multiple paths.");
        System.out.println("In the center, there's a chest guarded by a mystical force." +gz.ANSI_RESET);
        System.out.println(gz.ANSI_YELLOW+ "What do you want to do?");
        System.out.println("1. Open the chest");
        System.out.println("2. Attempt to solve a riddle" +gz.ANSI_RESET);
        System.out.println();
        boolean flag = true;
        while(flag){
            System.out.print(gz.ANSI_GREEN+ "Enter your choice: " +gz.ANSI_RESET);
            int cavernChoice = sc.nextInt();
            if(cavernChoice==1 || cavernChoice==2){
                switch (cavernChoice) {
                    case 1:
                        return openChestOutcome();
                    case 2:
                        return solveRiddleOutcome();
                }
            }
            else{
                System.out.println(gz.ANSI_RED+ "Enter Valid Choice." +gz.ANSI_RESET);
                continue;
            }
        }
        return true;
    }

    static boolean openChestOutcome() {
        if (RANDOM.nextInt(10) < 3) {
            System.out.println(gz.ANSI_GREEN+ "You open the chest and find a rare artifact. Congratulations You Completed All Levels!" +gz.ANSI_RESET);
            endGame(3);
            return true; // Game ended
        }
        else {
            System.out.println(gz.ANSI_RED+ "As you open the chest, a magical trap is triggered." +gz.ANSI_RESET);
            endGame(2);
            return true; // Game ended
        }
    }

    static String getRandomRiddle() {
        String[] riddles = {
            "The more you take, the more you leave behind. What am I?",
            "I can fly without wings. I can cry without eyes. Wherever I go, darkness follows me. What am I?",
            "What comes once in a minute, twice in a moment, but never in a thousand years?",
            "The person who makes it, sells it. The person who buys it never uses it. What is it?"
        };
        int randomIndex = new Random().nextInt(riddles.length);
        return riddles[randomIndex];
    }

    static boolean isCorrectAnswer(String riddle, String userAnswer) {
        switch (riddle) {
            case "The more you take, the more you leave behind. What am I?":
                return userAnswer.equalsIgnoreCase("footsteps");
            case "I can fly without wings. I can cry without eyes. Wherever I go, darkness follows me. What am I?":
                return userAnswer.equalsIgnoreCase("cloud");
            case "What comes once in a minute, twice in a moment, but never in a thousand years?":
                return userAnswer.equalsIgnoreCase("m");
            case "The person who makes it, sells it. The person who buys it never uses it. What is it?":
                return userAnswer.equalsIgnoreCase("coffin");
            default:
                return false;
        }
    }

    static boolean solveRiddleOutcome() {
        String riddle = getRandomRiddle();
        System.out.println(gz.ANSI_LIGHT_PURPLE+ "Here's a riddle for you:\n" + riddle +gz.ANSI_RESET);
        System.out.print(gz.ANSI_YELLOW+ "Your answer: " +gz.ANSI_RESET);
        String userAnswer = sc.next();

        if (isCorrectAnswer(riddle, userAnswer)) {
            System.out.println(gz.ANSI_GREEN+ "Congratulations! You solved the riddle and Completed all Levels." +gz.ANSI_RESET);
            endGame(3);
            return true; // Game ended
        }
        else {
            System.out.println(gz.ANSI_RED+ "The answer is incorrect. A mysterious force hinders your progress." +gz.ANSI_RESET);
            endGame(2);
            return true; // Game ended
        }
    }

    static void endGame(int level) {
        System.out.println(gz.ANSI_LIGHT_PURPLE+ "\nMax Level Reached: " + level +gz.ANSI_RESET);
        System.out.println(gz.ANSI_RED+ "Game over!" +gz.ANSI_RESET);

        gz = new GameZone();
        if (level == 0)
            gz.tokensEarned = 0;
        else if (level == 1)
            gz.tokensEarned = 15;
        else if (level == 2)
            gz.tokensEarned = 20;
        else if (level == 3)
            gz.tokensEarned = 25;
    }
}
