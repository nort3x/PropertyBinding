package me.nort3x.binding;

import java.util.function.Consumer;

public interface NotificationListener{
     void invalidated(ListenAble sender);
     void changed(ListenAble sender);

    default void accept(Notification notification){
            switch (notification.t){
                case CHANGED -> changed(notification.fromWho);
                case INVALIDATED -> invalidated(notification.fromWho);
            }
    }
}
