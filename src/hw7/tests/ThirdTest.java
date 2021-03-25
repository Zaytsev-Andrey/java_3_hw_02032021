package hw7.tests;

import hw7.annotations.AfterSuite;
import hw7.annotations.BeforeSuite;
import hw7.annotations.Test;

public class ThirdTest {
    @BeforeSuite
    public void before1() {
        System.out.printf("Class \"%s\": before1 running%n", this.getClass().getSimpleName());
    }

    @BeforeSuite
    public void before2() {
        System.out.printf("Class \"%s\": before2 running%n", this.getClass().getSimpleName());
    }

    @Test
    public void method1() {
        System.out.printf("Class \"%s\": method1 successful%n", this.getClass().getSimpleName());
    }

    @Test
    public void method2() {
        System.out.printf("Class \"%s\": method2 successful%n", this.getClass().getSimpleName());
    }

    @Test
    public void method3() {
        System.out.printf("Class \"%s\": method3 successful%n", this.getClass().getSimpleName());
    }
}
