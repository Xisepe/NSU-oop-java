package service.loader;

public interface ResourceLoader<T> {
    T load(String name);
}
