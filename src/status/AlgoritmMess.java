package status;

public abstract class AlgoritmMess extends Algoritm{

    protected final String [] arrayMessage;
    protected final String [] arrayErrorMessage;

    public AlgoritmMess(String [] messOne, String [] messTwo) {
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
