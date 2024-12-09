import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;
        int rounds = 0;
        boolean playAgain;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            rounds++;
            int secretNumber = random.nextInt(100) + 1;
            int maxAttempts = 7;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n--- Round " + rounds + " ---");
            System.out.println("Guess the number (1 to 100). You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == secretNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    totalScore += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                    guessedCorrectly = true;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all attempts. The correct number was " + secretNumber + ".");
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("\nGame Over! You played " + rounds + " rounds.");
        System.out.println("Your total score is: " + totalScore);
        scanner.close();
    }
}
