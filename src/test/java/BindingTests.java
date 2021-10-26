import me.nort3x.binding.Binding;
import me.nort3x.binding.ListenAble;
import me.nort3x.binding.NotificationListener;
import org.junit.jupiter.api.Test;

public class BindingTests {
    @Test
    void shouldReactToTwoSource(){
        Binding b = new Binding();
        ListenAble l1 = new ListenAble();
        ListenAble l2 = new ListenAble();
        b.listenTo(l1);
        b.listenTo(l2);

        b.addListener(new NotificationListener() {
            @Override
            public void invalidated(ListenAble sender) {
                System.out.println("invalidated from:"+sender.toString());
            }

            @Override
            public void changed(ListenAble sender) {
                System.out.println("changed from:"+sender.toString());
            }
        });

        l1.notifyChanged();
        l2.notifyChanged();
        l1.invalidate();
        l2.invalidate();
    }
}
