import java.util.Random;
import java.util.Scanner;

public class num {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0; 
        String playAgain;


        do {
            int numberToGuess = random.nextInt(100) + 1; 
            int attemptsLeft = 5; 
            boolean guessedCorrectly = false;

            System.out.println("\nI have picked a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts to guess it!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println(" Correct! You guessed the number.");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println(" Too low! Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You ran out of attempts! The number was: " + numberToGuess);
            }

            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().toLowerCase();

        } while (playAgain.equals("yes"));

        System.out.println("\n Game Over! Your final score: " + score);
        scanner.close();
    }

    @Override
    public String toString() {
        return "num []";
    }
}