package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Statistic {

    @JsonIgnore
    private final String password = "!ImSuperMANager!";
    private long currentIncome;
    private int numberOfAvailableSeats;
    private long numberOfPurchasedTickets;

    public String getPassword() {
        return password;
    }

    public void setCurrentIncome() {
        this.currentIncome = currentIncome;
    }

    public long getCurrentIncome() {
        return currentIncome;
    }

    public void setNumberOfAvailableSeats() {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfPurchasedTickets() {
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public long getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public Statistic() {
        super();
    }

    public Statistic(long currentIncome, int numberOfAvailableSeats, long numberOfPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }
}
