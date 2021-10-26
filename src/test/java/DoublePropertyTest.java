import me.nort3x.property.DoubleProperty;
import me.nort3x.property.ObjectProperty;
import me.nort3x.property.ObjectPropertyChangeListener;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;

public class DoublePropertyTest {
    @Test
    void should() {
        DoubleProperty d = new DoubleProperty();
        DoubleProperty d2 = new DoubleProperty();
        DoubleProperty d3 = new DoubleProperty();

        d3.add(d2).add(d);

        d3.addOnChangeListener(new ObjectPropertyChangeListener<Double>() {
            @Override
            public void onChange(Double newVal, Double oldVal, ObjectProperty<Double> source) {
                System.out.println("new:" + newVal + " old:" + oldVal);
            }
        });

        d2.setValue(2d);
        d.setValue(3d);

    }


    @Test
    void shouldBeLikeSinAddCos() throws InterruptedException {
        DoubleProperty d = new DoubleProperty(0);
        DoubleProperty d2 = new DoubleProperty(0);
        DoubleProperty d3 = new DoubleProperty(0);


        CountDownLatch cl = new CountDownLatch(2);

        new Thread(() -> {
            cl.countDown();
            try {
                cl.await();

                int i = 1;
                while (true) {
                    d.setValue(Math.sin(i++ / 1000d));
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            cl.countDown();
            try {
                cl.await();

                int i = 1;
                while (true) {
                    d2.setValue(Math.cos(i++ / 1000d));
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        d3.andThen((x, y) -> x + Math.pow(y, 2), d).andThen((x, y) -> x + Math.pow(y, 2), d2);  // d3 = d^2 + d2^2

        d3.addOnChangeListener(new ObjectPropertyChangeListener<Double>() {
            @Override
            public void onChange(Double newVal, Double oldVal, ObjectProperty<Double> source) {
                System.out.println("new:" + newVal + " old:" + oldVal);
            }
        });

        synchronized (this) {
            this.wait(2000);
        }

    }
}
