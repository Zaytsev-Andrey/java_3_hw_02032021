package hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"a", "b", "c", "d", "e"};

        int firstIndex = 1;
        int secondIndex = 4;

        System.out.printf("Исходный массив типа %s: %s\n", intArray.getClass().getSimpleName(), Arrays.toString(intArray));
        swapElements(intArray, firstIndex, secondIndex);
        System.out.printf("Поменяны местами элемент %s с элементом %s: %s\n", firstIndex, secondIndex, Arrays.toString(intArray));

        System.out.printf("Исходный массив типа %s: %s\n", strArray.getClass().getSimpleName(), Arrays.toString(strArray));
        swapElements(strArray, firstIndex, secondIndex);
        System.out.printf("Поменяны местами элемент %s с элементом %s: %s\n", firstIndex, secondIndex, Arrays.toString(strArray));


        System.out.println();
        System.out.println("Задание 2");
        System.out.printf("ArrayList из массива типа %s: [ ", intArray.getClass().getSimpleName());
        asArrayList(intArray).forEach(e -> System.out.printf("{%s}:%s ", e.getClass().getSimpleName(), e));
        System.out.println("]");
        System.out.printf("ArrayList из массива типа %s: [ ", strArray.getClass().getSimpleName());
        asArrayList(strArray).forEach(e -> System.out.printf("{%s}:%s ", e.getClass().getSimpleName(), e));
        System.out.println("]");
    }

    /**
     * Меняет местами элемент с индексом firstIndex с элементом с индексом secondIndex для массива любого ссылочного типа
     * @param array массив любого ссылочного типа
     * @param firstIndex индекс первого элемента
     * @param secondIndex индекс второго элемента
     * @param <T> любой ссылочный тип
     */
    private static <T> void swapElements(T[] array, int firstIndex, int secondIndex) {
        if (firstIndex >= 0 && firstIndex < array.length && secondIndex >=0 && secondIndex < array.length) {
            T temp = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = temp;
        } else {
            throw new IllegalArgumentException("Элемент(ы) с переданными индексами не существуют");
        }
    }

    /**
     * Преобразует массив в ArrayList
     * @param array исходный массив
     * @param <E> тип элементов массива и ArrayList
     * @return ArrayList<E>
     */
    private static <E> ArrayList<E> asArrayList(E[] array) {
        return Arrays.stream(array).collect(Collectors.toCollection(ArrayList::new));
    }
}
