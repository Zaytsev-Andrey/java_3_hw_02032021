package hw5;

public class Main {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        // Создаем объект синхронизаций
        SynchronizationManage synManage = new SynchronizationManage(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60, synManage), new Tunnel(synManage), new Road(40, synManage));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), synManage);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            // Ждем готовности всех участников
            synManage.getWaitingReady().await();

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            synManage.getWaitingStart().await();

            // Ждем финиша всех участников, первый финишировавший объявляется победителем
            Car winner;
            for (int i = 0; i < CARS_COUNT; i++) {
                winner = synManage.takeFinisher();
                if (i == 0) {
                    System.out.println(winner.getName() + " - WIN");
                }
            }

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
