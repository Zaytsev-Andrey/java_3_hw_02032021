package hw7;

import hw7.annotations.AfterSuite;
import hw7.annotations.BeforeSuite;
import hw7.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Tester {
    private static <T> T createInstance(Class<T> tClass) {
        T instance;

        try {
            instance = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            String errMsg = String.format("Class %s: instance not created%n", tClass.getSimpleName());
            throw new RuntimeException(errMsg);
        }

        return instance;
    }

    private static <T> void invoke(Method method, T obj) {
        try {
            method.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.printf("Class %s: method %s failed%n", obj.getClass().getSimpleName(), method.getName());
        }
    }

    private static <T> void test(Class<T> testClass) {
        Method[] methods = testClass.getDeclaredMethods();

        Method[] beforeMethods = Arrays.stream(methods)
                .filter(method -> method.getAnnotation(BeforeSuite.class) != null)
                .toArray(Method[]::new);

        Method[] afterMethods = Arrays.stream(methods)
                .filter(method -> method.getAnnotation(AfterSuite.class) != null)
                .toArray(Method[]::new);

        if (beforeMethods.length > 1 || afterMethods.length > 1) {
            String errMsg = String.format("Class \"%s\" contains more than one method BeforeSuite or AfterSuite%n",
                    testClass.getSimpleName());
            throw new RuntimeException(errMsg);
        }

        T obj = createInstance(testClass);

        if (beforeMethods.length == 1) {
            invoke(beforeMethods[0], obj);
        }

        Method[] testMethods = Arrays.stream(methods)
                .filter(method -> method.getAnnotation(Test.class) != null)
                .sorted(Comparator.comparing(o -> o.getAnnotation(Test.class).priority(), Comparator.reverseOrder()))
                .toArray(Method[]::new);

        for (Method m : testMethods) {
            invoke(m, obj);
        }

        if (afterMethods.length == 1) {
            invoke(afterMethods[0], obj);
        }
    }

    public static <T> void start(Class<T> cl) {
        try {
            test(cl);
        } catch (RuntimeException e) {
            System.err.print(e.getMessage());
        }
    }

    public static void start(String className) {
        try {
            test(Class.forName(className));
        } catch (ClassNotFoundException e) {
            String errorMsg = String.format("Class \"%s\" cannot be located%n", className);
            System.err.print(errorMsg);
        } catch (RuntimeException e) {
            System.err.print(e.getMessage());
        }
    }
}
