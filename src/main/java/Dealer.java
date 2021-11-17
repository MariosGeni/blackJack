import java.util.List;

/**
 * Ο Dealer έχει τρία πεδία, το όνομα, το score του τρέχοντος παιχνιδιού και πόσα παιχνίδια έχει νικήσει
 */
public class Dealer {

    private String name;
    private List<Integer> cards;
    private int score;
    private int wins;

    public Dealer(String name, int score, int wins) {
        this.name = name;
        this.score = score;
        this.wins = wins;
    }

    public Dealer(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
