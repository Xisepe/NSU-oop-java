package factory.wrapper;

import lombok.Data;

@Data
public class Wrapper<T> {
    private T value;
}
