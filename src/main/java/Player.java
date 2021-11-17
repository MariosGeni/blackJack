import java.util.List;

/**
 * Ο κάθε παίχτης έχει τέσσερα πεδία, το όνομα, τα φύλλα του, το score του τρέχοντος παιχνιδιού και πόσα παιχνίδια έχει νικήσει
 */
public class Player {

    private String name;
    private List<Integer> cards;
    private int score;
    private boolean lost;
    private int wins;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Player(){}

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

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
