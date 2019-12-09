package status;

public class DialogMessages {
    private final String incomingMessage;
    private final String outgoingMessage;
    private final String errorMessage;

    public DialogMessages(String incomingMessage, String outgoingMessage, String errorMessage) {
        this.incomingMessage = incomingMessage;
        this.outgoingMessage = outgoingMessage;
        this.errorMessage = errorMessage;
    }

    public String getIncomingMessage() {
        return incomingMessage;
    }

    public String getOutgoingMessage() {
        return outgoingMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
