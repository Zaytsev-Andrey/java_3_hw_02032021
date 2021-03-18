package hw5;

public class Road extends Stage {
    public Road(int length, SynchronizationManage synManage) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.synManage = synManage;
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);

            // Если это заключительный этап, тогда вывод сообщения и добавление объекта в очередь финишировавших
            // выполняем в синхронизированном блоке кода
            if (isFinishStage) {
                synManage.getFinishLock().lock();
                System.out.println(c.getName() + " закончил этап: " + description);
                synManage.putFinisher(c);
                synManage.getFinishLock().unlock();
            } else {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
