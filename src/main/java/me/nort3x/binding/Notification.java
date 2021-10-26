package me.nort3x.binding;

public class Notification {
    public enum Type{
        INVALIDATED,
        CHANGED
    }
    Type t;
    ListenAble fromWho;

    public Notification(Type t, ListenAble fromWho) {
        this.t = t;
        this.fromWho = fromWho;
    }

}
