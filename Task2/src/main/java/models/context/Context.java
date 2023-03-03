package models.context;

public interface Context {
    <T> ContextElement<T> getContextElement(String name, Class<T> cls);
}
