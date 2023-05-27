package factory.delay;

import factory.wrapper.Wrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Delay extends Wrapper<Integer> {
    private int max;
    private final int min = 0;
}
