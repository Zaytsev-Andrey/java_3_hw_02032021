package hw5;

public class Tunnel extends Stage {
    public Tunnel(SynchronizationManage synManage) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.synManage = synManage;
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                synManage.getTunnelSemaphore().acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                synManage.getTunnelSemaphore().release();

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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
