package hw1.boxes;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> storage;

    public Box() {
        this.storage = new ArrayList<>();
    }

    public void add(T fruit) {
        storage.add(fruit);
    }

    public float getWeight() {
        return storage.stream()
                .map(Fruit::getWeight)
                .reduce(0.0f, Float::sum);
    }

    public boolean compare(Box<?> otherBox) {
        return Math.abs(this.getWeight() - otherBox.getWeight()) < 0.001f;
    }

    public void moveOtherBox(Box<T> otherBox) {
        while (!storage.isEmpty()) {
            otherBox.add(storage.remove(0));
        }
    }
}
