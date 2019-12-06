package status;

public abstract class AlgoritmMess extends Algoritm{

    protected final String [] arrayMessage;
    protected final String [] arrayErrorMessage;

    public AlgoritmMess( int rollBack, String [] messOne, String [] messTwo) {
        super(rollBack);
        this.arrayMessage = messOne;
        this.arrayErrorMessage = messTwo;
    }

    public String[] getArrayMessage() {
        return arrayMessage;
    }

    public String[] getArrayErrorMessage() {
        return arrayErrorMessage;
    }
}
