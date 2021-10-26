package me.nort3x.binding;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ListenAble {

    Set<NotificationListener> listeners = Collections.newSetFromMap(
            Collections.synchronizedMap(
                    new WeakHashMap<>()
            )
    );

    public void addListener(NotificationListener notificationListener) {
        listeners.add(notificationListener);
    }

    public void removeListener(NotificationListener notificationListener) {
        listeners.remove(notificationListener);
    }

    private void notifyListeners(Notification notification) {
        listeners.parallelStream().forEach(notificationListener -> notificationListener.accept(notification));
    }

    public void notifyChanged() {
        notifyListeners(new Notification(Notification.Type.CHANGED, this));
    }

    public void invalidate() {
        notifyListeners(new Notification(Notification.Type.INVALIDATED, this));
        listeners.clear();
    }
}
