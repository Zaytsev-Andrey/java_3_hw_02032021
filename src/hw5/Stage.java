package hw5;

public abstract class Stage {
    protected int length;
    protected String description;
    protected SynchronizationManage synManage;
    protected boolean isFinishStage;

    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
