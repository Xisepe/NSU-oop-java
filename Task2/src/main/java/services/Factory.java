package services;

public interface Factory<T> {
    void register(String name, Class<T> t);
    T create(String name);
}
