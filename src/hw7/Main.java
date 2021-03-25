package hw7;

import hw7.tests.FirstTest;
import hw7.tests.ThirdTest;

public class Main {
    public static void main(String[] args) {
        Tester.start(FirstTest.class);
        Tester.start("hw7.tests.SecondTest");
        Tester.start(ThirdTest.class);
    }
}
