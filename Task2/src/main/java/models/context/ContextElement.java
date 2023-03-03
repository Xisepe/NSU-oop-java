package models.context;

import lombok.Data;
import lombok.NonNull;

@Data
public class ContextElement<T> {
    @NonNull
    private final T element;
}
