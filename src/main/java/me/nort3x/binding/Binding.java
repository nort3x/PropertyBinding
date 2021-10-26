package me.nort3x.binding;


import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Binding extends ListenAble implements NotificationListener{

    public void listenTo(ListenAble listenAble){
        listenAble.addListener(this);
    }

    @Override
    public void invalidated(ListenAble sender) {
        this.invalidate();
    }

    @Override
    public void changed(ListenAble sender) {
        this.notifyChanged();
    }
}
