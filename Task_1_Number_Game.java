import java.util.Random;
import java.util.Scanner;

public class Task_1_Number_Game {
    private static final int MAX_ATTEMPTS = 7; // Maximum number of attempts
    private static int score = 0; // User's score

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            playRound(scanner);
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        } while (playAgain);

        System.out.println("Thanks for playing! Your final score is: " + score);
        scanner.close();
    }

    private static void playRound(Scanner scanner) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("A new number has been generated between 1 and 100.");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");

        while (attempts < MAX_ATTEMPTS && !hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the number: " + numberToGuess);
                score += (MAX_ATTEMPTS - attempts + 1); // Score based on remaining attempts
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry, you've used all your attempts. The number was: " + numberToGuess);
        }

        System.out.println("Your current score is: " + score);
    }
}
