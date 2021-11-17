import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        Player player = new Player();
        Dealer dealer = new Dealer();
        /**
         * Αυτο το while προστατευει τον χρηστη ωστε να μην δωσει λαθος τιμες και σκασει το προγραμμα
         */
        while (true) {
            System.out.println("Welcome to the Blackjack table \nWant to play a game ?\n'Yes'/'No'");
            String answer = scanner.nextLine();
            if (answer.equals("Yes") || answer.equals("yes") || answer.equals("YES")){
                Rules.setOrContinues(player, dealer);

            } else if (answer.equals("No") || answer.equals("no") || answer.equals("NO")){
                System.out.println("\nSee you around\n");
                System.exit(0);
                break;
            }

        }


    }


}