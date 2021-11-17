import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Μέσα απο αυτή την κλάσση καλούμε όλες τις λειτουρίες του παιχνιδιού
 */
public class Rules {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Αυτή η μέθοδος διαλέγει τυχαία ενα φύλλο απο το 1 εως το 11
     */
    public static int drawACard(){
        Random random = new Random();
        int card = random.nextInt(11)+1;
        return card;
    }

    /**
     * Αυτή η μέθοδος βλέπει αν είναι καινούργιο παιχνίδι ή τρέχον
     */
    public static void setOrContinues(Player player, Dealer dealer) {
        if (player.getWins() == 0 && dealer.getWins() == 0) {
            initializePlayer(player);
            initializeDealer(dealer);
        } else {
            restartTheGame(dealer, player);
        }
        hitOrStay(player, dealer);
    }

    /**
     * Αυτή η μέθοδος αρχικοποιεί τον Dealer
     */
    public static void initializeDealer(Dealer dealer){
        dealer.setName("dealer");
        dealer.setWins(0);
        List<Integer> dealersCards = new ArrayList<>();
        for (int i = 0 ; i < 2 ; i++){
            dealersCards.add(Rules.drawACard());
        }
        dealer.setCards(dealersCards);
        int total = 0;
        for (Integer card : dealersCards){
            total += card;
        }
        dealer.setScore(total);
        System.out.println("the " + dealer.getName() + " has a " + dealersCards.get(0) + " showing and a hidden card\n");
    }

    /**
     * Αυτή η μέθοδος αρχικοποιεί τον Player
     */
    public static void initializePlayer(Player player){
        System.out.println("What is your name?");
        String answer = scanner.nextLine();
        player.setName(answer);
        player.setWins(0);

        List<Integer> playersCards = new ArrayList<>();
        for (int i = 0 ; i < 2 ; i++){
            playersCards.add(Rules.drawACard());
        }
        player.setCards(playersCards);
        int total = 0;
        for (Integer card : playersCards){
            total += card;
        }
        player.setScore(total);
        System.out.println("\n" + player.getName() + " has a " + player.getCards().get(0) + " and " +
                player.getCards().get(1) + "\n" + player.getName() + "'s total is : " + total + "\n\n");
    }

    /**
     * Αυτή η μέθοδος κάνει reset κάποια πεδία του Player και του Dealer
     */
    public static void restartTheGame(Dealer dealer, Player player){
        player.setScore(0);
        player.setCards(new ArrayList<>());
        player.setLost(false);

        dealer.setScore(0);
        dealer.setCards(new ArrayList<>());
    }


    /**
     * Αυτή η μέθοδος επιτρέπει στον Player να ζητήσει ένα φύλλο ή να μείνει
     */
    public static void hitOrStay(Player player, Dealer dealer){
        List<Integer> cards = player.getCards();
        boolean cont = true;
        while (cont) {
            System.out.println("Would you like to 'Hit' or 'Stay'");
            String choice = scanner.nextLine();
            if (choice.equals("Hit") || choice.equals("HIT") || choice.equals("hit")){
                cards.add(drawACard());
                if (checkIfBurned(cards, player, dealer)){
                    cont = false;
                }
            }
            if (choice.equals("Stay") || choice.equals("STAY") || choice.equals("stay")){
                cont = false;
                checker(cards);
                dealersTurn(dealer, player);
            }
        }
    }


    /**
     * Αυτή η μέθοδος τσεκάρει αν το άθρισμα των φύλλων του Player έχει λιγότερο ή περισσότερο απο το 21
     */
    public static boolean checkIfBurned(List<Integer> cards, Player player, Dealer dealer){
        int total = 0;
        System.out.print("You drew : ");
        for (Integer i : cards){
            System.out.print(i + " ");
            total += i;
        }
        if (total >= 22){
            player.setLost(true);
            System.out.println("You are over 21");
            dealer.setWins(dealer.getWins()+1);
            System.out.println(player.getName() + " lost, " + dealer.getName() +" won !");
            playAgain(dealer, player);
            return true;
        } else {
            System.out.println("\nYou total is " + total);
            return false;
        }
    }

    public static int checker(List<Integer> cards){
        int total = 0;
        for (Integer i : cards){
            total += i;
        }
        return total;
    }

    /**
     * Σε αυτή την μέθοδο το πρόγραμμα αποφασίζει αν θα πρέπει ο Dealer να τραβήξει φύλλο
     */
    private static void dealersTurn(Dealer dealer, Player player) {
        List<Integer> cards = dealer.getCards();
        System.out.print("Dealer drew : ");
        for (Integer i : cards){
            System.out.print(i + " ");
        }
        while (checker(dealer.getCards()) < 18){
            System.out.println("\nDealer want to hit!");
            int drewCard = drawACard();
            cards.add(drewCard);
            System.out.println(drewCard + " ");
        }

        int players = checker(player.getCards());
        int dealers = checker(dealer.getCards());

        outcome(dealer, player, players, dealers);

        playAgain(dealer, player);
    }

    /**
     * Αυτή η μέθοδος ρωτάει τον Player αν θέλει έναν ακόμη παιχνίδι
     */
    private static void playAgain(Dealer dealer, Player player) {
        while (true){
            System.out.println("Wanna play again ?\n'Yes'/'No'");
            String answer = scanner.nextLine();
            if (answer.equals("Yes") || answer.equals("yes") || answer.equals("YES")) {
                setOrContinues(player, dealer);
            }
            if (answer.equals("No") || answer.equals("no") || answer.equals("NO")){
                System.out.println("Goodbye !");
                System.exit(0);
                break;
            }
        }
    }

    /**
     * Αυτή η μέθοδος ελέγχει τον νικητή
     */
    private static void outcome(Dealer dealer, Player player, int players, int dealers) {
        if (dealers > 21){
            System.out.println(dealer.getName() + " lost, " + player.getName() +" won !");
            player.setWins(player.getWins()+1);
        } else if (players == dealers || dealers > players){
            System.out.println(player.getName() + " lost, " + dealer.getName() +" won !");
            dealer.setWins(dealer.getWins()+1);
        } else {
            System.out.println(dealer.getName() + " lost, " + player.getName() +" won !");
            player.setWins(player.getWins()+1);
        }
    }
}
