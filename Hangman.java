// GAME 3 --> Hangman.

import java.util.Scanner;

class Hangman {
    static GameZone gz;

    static String[] easyWords = {"happy", "garden", "simple", "smile", "height"};
    static String[] mediumWords = {"travel", "piano", "towel", "breeze"};
    static String[] hardWords = {"chocolate", "mysterious", "syndrome", "promise"};

    static String[] hints = {
            "Expressing joy or contentment",
            "A cultivated area with plants and flowers",
            "Not complex; easy to grasp",
            "A pleased, kind, or amused facial expression",
            "It goes up but never ever comes down",

            "Embarking on a journey or excursion",
            "A musical instrument with black and white keys",
            "The more it dries it gets wetter",
            "A gentle and pleasant movement of the air",

            "A sweet delicacy derived from cacao seeds",
            "Full of secrets and difficult to understand",
            "A characteristic set of symptoms indicating a particular social condition",
            "It can be broken but never held"
    };

    static String wordToGuess;
    static char[] guessedLetters;
    static int attemptsLeft;
    static int difficultyChoice;

    void main(String[] args) {
        difficultyChoice = getDifficultyChoice();
        selectRandomWord(difficultyChoice);
        initializeGame();

        while (attemptsLeft > 0 && !isWordGuessed()) {
            displayGameStatus();
            char guessedLetter = getGuessFromPlayer();
            checkLetter(guessedLetter);
        }

        displayGameResult();
    }

    static int getDifficultyChoice() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println(gz.ANSI_YELLOW+ "Choose the difficulty level:");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard" +gz.ANSI_RESET);
            System.out.print(gz.ANSI_GREEN+"Enter your choice (1-3): " +gz.ANSI_RESET);
            choice = sc.nextInt();

            if (choice < 1 || choice > 3) {
                System.out.println(gz.ANSI_RED+ "Invalid choice. Please choose again." +gz.ANSI_RESET);
            }
        } while (choice < 1 || choice > 3);

        return choice;
    }

    static void selectRandomWord(int difficultyChoice) {
        String[] selectedWords;

        switch (difficultyChoice) {
            case 1:
                selectedWords = easyWords;
                break;
            case 2:
                selectedWords = mediumWords;
                break;
            case 3:
                selectedWords = hardWords;
                break;
            default:
                selectedWords = easyWords;
        }

        int randomIndex = (int) (Math.random() * selectedWords.length);
        wordToGuess = selectedWords[randomIndex];
    }

    static void initializeGame() {
        guessedLetters = new char[wordToGuess.length()];
        attemptsLeft = wordToGuess.length();

        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
    }

    static void displayGameStatus() {
        System.out.println(gz.ANSI_CYAN+"\nWord: " + String.valueOf(guessedLetters) +gz.ANSI_RESET);
        System.out.println(gz.ANSI_BLUE+"Hint: " + hints[getWordIndex()] +gz.ANSI_RESET);
        System.out.println(gz.ANSI_LIGHT_PURPLE+ "Attempts left: " + attemptsLeft +gz.ANSI_RESET);
    }

    static int getWordIndex() {
        for (int i = 0; i < hints.length; i++) {
            if (wordToGuess.equals(getWord(i))) {
                return i;
            }
        }
        return -1; // i can return any int number here as this case will never occur but it is mandatory for syntax of return type method.
    }

    static String getWord(int index) {
        switch (index) {
            case 0: return "happy";
            case 1: return "garden";
            case 2: return "simple";
            case 3: return "smile";
            case 4: return "height";

            case 5: return "travel";
            case 6: return "piano";
            case 7: return "towel";
            case 8: return "breeze";

            case 9: return "chocolate";
            case 10: return "mysterious";
            case 11: return "syndrome";
            case 12: return "promise";

            default: return "";
        }
    }

    static char getGuessFromPlayer() {
        Scanner sc = new Scanner(System.in);
        System.out.print(gz.ANSI_YELLOW+ "Enter a letter: " +gz.ANSI_RESET);
        String enterLetter = sc.next();
        return Character.toLowerCase(enterLetter.charAt(0));
    }

    static void checkLetter(char guessedLetter) {
        boolean letterAlreadyGuessed = false;

        for (char letter : guessedLetters) {
            if (letter == guessedLetter) {
                letterAlreadyGuessed = true;
                System.out.println(gz.ANSI_RED+ "You already guessed this letter. Enter a different letter." +gz.ANSI_RESET);
                break;
            }
        }

        if (!letterAlreadyGuessed) {
            boolean letterGuessed = false;

            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guessedLetter) {
                    guessedLetters[i] = guessedLetter;
                    letterGuessed = true;
                }
            }

            if (letterGuessed) {
                System.out.println(gz.ANSI_GREEN+ "Good guess!" +gz.ANSI_RESET);
            }
            else {
                attemptsLeft--;
                System.out.println(gz.ANSI_RED+ "Incorrect guess. Try again!" +gz.ANSI_RESET);
            }
        }
    }

    static boolean isWordGuessed() {
        for (char letter : guessedLetters) {
            if (letter == '_') {
                return false;
            }
        }
        return true;
    }

    static void displayGameResult() {
        if (isWordGuessed()) {
            System.out.println(gz.ANSI_GREEN+ "\nCongratulations! You guessed the word: " + wordToGuess +gz.ANSI_RESET);
            switch (difficultyChoice) {
                case 1:
                    gz.tokensEarned = 7;  // Easy level
                    break;
                case 2:
                    gz.tokensEarned = 10;  // Medium level
                    break;
                case 3:
                    gz.tokensEarned = 15; // Hard level
                    break;
            }
        }
        else {
            System.out.println(gz.ANSI_RED+ "\nSorry, you ran out of attempts. The word was: " + wordToGuess +gz.ANSI_RESET);
            gz.tokensEarned = 0;
        }
    }
}
