package me.nort3x.property;

@FunctionalInterface
public interface ObjectPropertyChangeListener<T> {
    void onChange(T newVal, T oldVal, ObjectProperty<T> source);
}
