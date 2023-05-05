package model.state.score;

import lombok.Data;

@Data
public class Score {
    private int value;
    public void increment() {
        value++;
    }
    public void increment(int value) {
        this.value+=value;
    }
}
