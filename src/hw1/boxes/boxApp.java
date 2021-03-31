package hw1.boxes;

public class boxApp {
    public static void main(String[] args) {
        System.out.println("Задание 3");
        // 1-я коробка из 6 яблок
        Box<Apple> firstBox = createAppleBox(6);
        // 2-я коробка из 10 яблок
        Box<Apple> secondBox = createAppleBox(10);
        // 3-я коробка из 4 апельсинов
        Box<Orange> thirdBox = createOrangeBox(4);
        // 4-я пустая коробка
        Box<Orange> fourthBox = new Box<>();

        showWeightOfBox(firstBox, secondBox, thirdBox, fourthBox);

        System.out.printf("Коробка № 1 равна коробке № 2? - %b\n", firstBox.compare(secondBox));
        System.out.printf("Коробка № 1 равна коробке № 3? - %b\n", firstBox.compare(thirdBox));
        System.out.printf("Коробка № 2 равна коробке № 3? - %b\n", secondBox.compare(thirdBox));
        System.out.println("");

        System.out.println("Переместим содержимое коробки № 1 в коробку № 2");
        firstBox.moveOtherBox(secondBox);

        showWeightOfBox(firstBox, secondBox);
    }

    private static Box<Apple> createAppleBox(int count) {
        Box<Apple> appleBox = new Box<>();

        for (int i = 0; i < count; i++) {
            appleBox.add(new Apple());
        }

        return appleBox;
    }

    private static Box<Orange> createOrangeBox(int count) {
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < count; i++) {
            orangeBox.add(new Orange());
        }

        return orangeBox;
    }

    private static void showWeightOfBox(Box<?>... boxes) {
        for (int i = 0; i < boxes.length; i++) {
            System.out.printf("Вес коробки № %d: %.3f\n", i + 1, boxes[i].getWeight());
        }

        System.out.println("");
    }
}
