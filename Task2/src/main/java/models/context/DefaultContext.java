package models.context;

import exceptions.context.NoSuchContextElementException;

import java.util.HashMap;
import java.util.Map;

public class DefaultContext implements Context{
    private final Map<String, Object> ctx;
    private DefaultContext(DefaultContextBuilder builder) {
        this.ctx = builder.ctx;
    }
    public static class DefaultContextBuilder {
        private final Map<String, Object> ctx = new HashMap<>();
        public DefaultContextBuilder with(String key, Object value) {
            ctx.put(key, value);
            return this;
        }
        public DefaultContext build() {
            return new DefaultContext(this);
        }
    }
    @Override
    public Object getContextElement(String name) {
        Object contextElement = ctx.get(name);
        if (contextElement == null)
            throw new NoSuchContextElementException(name);
        return contextElement;
    }
}
