import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        String playAgain = "yes";
        int round=1;

        ArrayList<String> summary = new ArrayList<>();

        while (playAgain.equalsIgnoreCase("yes")) {

            int attempts = 0;
            int guess = 0;
            int maxAttempts;

            System.out.println("\n------ Welcome to the Number Guessing Game! ------");
            System.out.println("              ------GAME START------                ");

            System.out.println("Choose Difficulty:");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");

            int upperlimit;
            int choice = sc.nextInt();

            if(choice==1){
                upperlimit=50;
                maxAttempts=10;
            }else if(choice==2){
                upperlimit=100;
                maxAttempts=7;
            }else{
                upperlimit=200;
                maxAttempts=5;  
            }
            int secret = random.nextInt(upperlimit) + 1;

            System.out.println("\n    ====== Round " + round + " ======    ");


            while (guess != secret && attempts< maxAttempts) {
                
                System.out.print("Enter your guess (1-" + upperlimit + "): ");
                guess = sc.nextInt();

                if (guess < 1 || guess > upperlimit) {
                    System.out.println("Invalid guess! Please enter a number between 1 and " + upperlimit + ".");
                    System.out.println("This guess does not count as an attempt.");
                    continue;
                }
                attempts++;
                System.out.println("You entered " + guess);

                if (guess > secret) {
                    System.out.println("Your guess is too high.");
                } else if (guess < secret) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Congratulations! You guessed the number!");
                }


            System.out.println("Attempts: " + attempts);
            System.out.println("Attempts Left: " + (maxAttempts - attempts));
            System.out.println();
        }
        if (guess != secret) {
            System.out.println("You Lost!");
            System.out.println("The correct number was: " + secret);
            summary.add("Round " + round + " - Lost");
        } else {
            summary.add("Round " + round + " - Guessed in " + attempts + " attempts");
        }

        System.out.print("Do you want to play again? (yes/no): ");
        playAgain = sc.next();
        round++;
        System.out.println("---------------------------------------");
    }
    System.out.println("\n    ====== Game Summary ======    ");

    for (String result : summary) {
        System.out.println(result);
    }

    System.out.println("Thanks for playing!");
    sc.close();
    }
}