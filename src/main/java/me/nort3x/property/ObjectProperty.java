package me.nort3x.property;

import me.nort3x.binding.Binding;
import me.nort3x.binding.ListenAble;
import me.nort3x.binding.NotificationListener;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ObjectProperty<T> extends Binding {
    Function<T,T> piper = Function.identity();
    T currentValue = null;
    T lastValue = null;
    private Supplier<T> valueSource;


    public ObjectProperty(Supplier<T> valueSource) {
        this.valueSource = valueSource;
        changed(this);
    }

    @Override
    public void invalidated(ListenAble sender) {
        super.invalidate();
    }

    @Override
    public void changed(ListenAble sender) {
        processNewValue();
        super.notifyChanged();
    }

    public T getValue(){
        return currentValue;
    }

    public void setValue(T t){
        valueSource = () -> t;
        changed(this);
    }

    public ObjectProperty<T> andThen(BiFunction<T,T,T> op,ObjectProperty<T> objectProperty){
        listenTo(objectProperty);
        this.andThen(new Function<T, T>() {
            @Override
            public T apply(T t) {
                return op.apply(t, objectProperty.getValue());
            }
        });
    return this;
    }

    public void addOnChangeListener(ObjectPropertyChangeListener<T> objectPropertyChangeListener){
        this.addListener(new NotificationListener() {
            @Override
            public void invalidated(ListenAble sender) {

            }

            @Override
            public void changed(ListenAble sender) {
                objectPropertyChangeListener.onChange(currentValue,lastValue,ObjectProperty.this);
            }
        });
    }

    private void processNewValue() {
        lastValue = currentValue;
        currentValue = piper.apply(valueSource.get());
    }

    public ObjectProperty<T> andThen(Function<T,T> piper){
        this.piper = this.piper.andThen(piper);
        return this;
    }

    public void replacePiper(Function<T,T> piper){
        this.piper = piper;
    }
}
