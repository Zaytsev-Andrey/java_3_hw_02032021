package hw7.tests;

import hw7.annotations.Test;

public class FirstTest {
    @Test()
    public void method1() {
        System.out.printf("Class \"%s\": method1 successful%n", this.getClass().getSimpleName());
    }

    @Test(priority = 1)
    public void method2() {
        System.out.printf("Class \"%s\": method2 successful%n", this.getClass().getSimpleName());
    }

    @Test
    public void method3() {
        System.out.printf("Class \"%s\": method3 successful%n", this.getClass().getSimpleName());
    }
}
