package service.loader;

public interface ResourceManager<T> extends ResourceLoader<T>{
    void save(T t, String filename);
}
