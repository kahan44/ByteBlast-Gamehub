// Runs Full GameHub.

import java.util.Scanner;

class GameZone {
    static String ANSI_YELLOW = "\u001B[33m";
    static String ANSI_RED = "\u001B[31m";
    static String ANSI_CYAN = "\u001B[36m";
    static String ANSI_GREEN = "\u001B[32m";
    static String ANSI_BLUE = "\u001B[34m";
    static String ANSI_LIGHT_PURPLE = "\u001B[95m";
    static String ANSI_BOLD = "\u001b[1m";
    static String ANSI_UNDERLINE = "\u001b[4m";
    static String ANSI_BLINK = "\u001b[5m";
    static String ANSI_RESET = "\u001B[0m";

    static int tokensToPurchase;
    static int totalTokensPurchased;
    static int userTokens = 0;
    static int rupeesPerToken = 10;
    static int tokensDeducted;
    static int tokensEarned;
    static int totalTokensWon;
    static int tokensToRedeem;
    static int rupeesEarned;
    static int paymentMethod;
    static int gameChoice;
    static double updatedRupees;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Rules r = new Rules();

        r.GameZoneRules();
        
        while (true) {
            System.out.println(ANSI_RED+"------------- MAIN MENU -------------"+ANSI_RESET);
            System.out.println(ANSI_CYAN+"1. Purchase Tokens");
            System.out.println("2. Go to Games Station");
            System.out.println("3. Show Token Balance");
            System.out.println("4. Redeem Tokens and Exit Application"+ANSI_RESET);
            System.out.println(ANSI_RED+"-------------------------------------"+ANSI_RED);
            System.out.print(ANSI_GREEN+"Enter your choice (1-4): "+ANSI_RESET);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    purchaseTokens();
                    break;
                case 2:
                    playGames();
                    break;
                case 3: 
                    System.out.println(ANSI_YELLOW+"\nTotal Tokens Available: "+userTokens+"\n"+ANSI_RESET);
                    break;
                case 4:
                    redeemAndGenerateBill();
                    System.exit(0);
                    break;
                default:
                    System.out.println(ANSI_RED+"Invalid choice. Try again.\n"+ANSI_RESET);
            }
        }
    }

    static void purchaseTokens() {
        PaymentMethods pm = new PaymentMethods();

        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_YELLOW+"\nEnter the number of tokens to purchase: "+ANSI_RESET);
        tokensToPurchase = sc.nextInt();

        if (tokensToPurchase > 0) {
            int totalCost = tokensToPurchase * rupeesPerToken;
            System.out.println(ANSI_YELLOW+"Total Cost: " + totalCost + " rupees");
            System.out.println("\nEnter 1 to confirm payment through UPI.");
            System.out.println("Enter 2 to confirm payment through Debit/Credit Card: "+ANSI_RESET);
            System.out.print(ANSI_GREEN+"Enter Your Choice (1-2): "+ANSI_RESET);
            paymentMethod = sc.nextInt();

            switch (paymentMethod) {
                case 1:
                    pm.confirmPaymentByUPI(tokensToPurchase, totalCost);
                    break;
                case 2:
                    pm.confirmPaymentByCard(tokensToPurchase, totalCost);
                    break;
                default:
                    System.out.println(ANSI_RED+"Invalid payment method. Purchase canceled.\n"+ANSI_RESET);
            }
        }
        else {
            System.out.println(ANSI_RED+"Invalid number of tokens. Purchase canceled.\n"+ANSI_RESET);
        }
    }

    static void playGames() {
        Scanner sc = new Scanner(System.in);
        
        boolean backToMenu = true;
        
        while(backToMenu){
            System.out.println(ANSI_RED+"\n------------- GAMES STATION -------------"+ANSI_RESET);
            System.out.println(ANSI_CYAN+"1. Tic-Tac-Toe        (Pay -> 5 Tokens)");
            System.out.println("2. Memory Game        (Pay -> 5 Tokens)");
            System.out.println("3. Hangman            (Pay -> 5 Tokens)");
            System.out.println("4. Dungeon Rush       (Pay -> 10 Tokens)");
            System.out.println("5. Exit to Main Menu."+ANSI_RESET);
            System.out.println(ANSI_RED+"-----------------------------------------"+ANSI_RESET);
            System.out.print(ANSI_GREEN+"Enter Your Choice (1-5): "+ANSI_RESET);
            gameChoice = sc.nextInt();
            switch (gameChoice) {
                
                case 1:
                    if(userTokens>=5){
                        playTicTacToe();
                    }
                    else{
                        System.out.println(ANSI_RED+"Insufficient tokens to play. Purchase more tokens."+ANSI_RESET);
                    }
                    break;
                case 2:
                    if(userTokens>=5){
                        playMemoryGame();
                    }
                    else{
                        System.out.println(ANSI_RED+"Insufficient tokens to play. Purchase more tokens."+ANSI_RESET);
                    }
                    break;
                case 3:
                    if(userTokens>=5){
                        playHangman();
                    }
                    else{
                        System.out.println(ANSI_RED+"Insufficient tokens to play. Purchase more tokens."+ANSI_RESET);
                    }
                    break;
                case 4:
                    if(userTokens>=10){
                        playDungeonRush();
                    }
                    else{
                        System.out.println(ANSI_RED+"Insufficient tokens to play. Purchase more tokens."+ANSI_RESET);
                    }
                    break;
                case 5:
                    backToMenu=false;
                    System.out.println();
                    return;
                default:
                    System.out.println(ANSI_RED+"Invalid choice. Try again."+ANSI_RESET);
            }
        }
    }

    
    static void playTicTacToe() {
        Rules r =  new Rules();
        TicTacToe ticTacToe = new TicTacToe();
        deductTokens(5);
        r.showRules();
        ticTacToe.main(null);
        winTokens(tokensEarned);
    }

    static void playMemoryGame() {
        Rules r = new Rules();
        MemoryGame memoryGame = new MemoryGame();
        deductTokens(5);
        r.showRules();
        memoryGame.main(null);
        winTokens(tokensEarned);
    }

    static void playHangman() {
        Rules r = new Rules();
        Hangman hangman = new Hangman();
        deductTokens(5);
        r.showRules();
        hangman.main(null);
        winTokens(tokensEarned);
    }

    static void playDungeonRush() {
        Rules r = new Rules();
        DungeonRush dr = new DungeonRush();
        deductTokens(10);
        r.showRules();
        dr.main(null);
        winTokens(tokensEarned); 
    }

    static void deductTokens(int tokensToDeduct) {
        if (userTokens >= tokensToDeduct) {
            userTokens -= tokensToDeduct;
            tokensDeducted += tokensToDeduct;
            System.out.println(ANSI_YELLOW+"\nTokens deducted: " + tokensToDeduct);
            System.out.println("Remaining Tokens: " + userTokens+ANSI_RESET);
        }
    }

    static void winTokens(int tokensToWin) {
        totalTokensWon += tokensToWin;
        userTokens += tokensToWin;
        System.out.println(ANSI_YELLOW+"\nYou won " + tokensToWin + " tokens.");
        System.out.println("Total Tokens remaining: " + userTokens+ANSI_YELLOW);
    }

    static void redeemAndGenerateBill() {
        TokenBill bill = new TokenBill();
        if (userTokens > 0) {
            System.out.println(ANSI_RED+"\nRedeeming remaining tokens...");
            rupeesEarned = userTokens * rupeesPerToken;
            updatedRupees = rupeesEarned - (0.20 * rupeesEarned);
            System.out.println("Tokens available: " + userTokens);
            System.out.println("Rupees earned: " + updatedRupees +ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED+"No tokens to redeem."+ANSI_RESET);
        }
        bill.generateBill();
    }
}
