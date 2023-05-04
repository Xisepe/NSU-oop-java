package model.sound;

public interface Sound {
    void play();
    void stop();
    void setLoop(boolean loop);
    void setVolume(int percent);
}
