package status;


public abstract class StatusMessage extends Status {

    private final DialogMessages dialogMessages;

    public StatusMessage(int rollbackID, DialogMessages dialogMessages) {
        super(rollbackID);
        this.dialogMessages = dialogMessages;
    }

    public DialogMessages getDialogMessages() {
        return dialogMessages;
    }
}
