package model.state;

import lombok.Data;

@Data
public class Score {
    private int points;
    public void addPoints(int points) {
        this.points += points;
    }
}
