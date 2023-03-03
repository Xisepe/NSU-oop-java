package models.context;

import exceptions.context.NoSuchContextElementException;

import java.util.HashMap;
import java.util.Map;

public class DefaultContext implements Context{
    private final Map<String, ContextElement<?>> ctx;
    private DefaultContext(DefaultContextBuilder builder) {
        this.ctx = builder.ctx;
    }
    private static class DefaultContextBuilder {
        private final Map<String, ContextElement<?>> ctx = new HashMap<>();
        DefaultContextBuilder with(String key, ContextElement<?> value) {
            ctx.put(key, value);
            return this;
        }
        DefaultContext build() {
            return new DefaultContext(this);
        }
    }
    @Override
    public <T> ContextElement<T> getContextElement(String name, Class<T> cls) {
        ContextElement<?> contextElement = ctx.get(name);
        if (contextElement == null)
            throw new NoSuchContextElementException(name);
        if (cls.isAssignableFrom(contextElement.getElement().getClass()))
            throw new NoSuchContextElementException(name);
        return (ContextElement<T>) contextElement;
    }
}
