package elements;

public abstract class OutMessage  {
    private Object message;

    public OutMessage(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }
}
