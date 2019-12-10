package status;

import com.company.Bot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import update.BigUpdate;

public abstract class Status {

    public static final int COMPLETE = 1;
    public static final int NEXT_STATUS = 2;
    public static final int NOT_COMPLETE = 3;
    public static final int UNKNOWN_DATA = 4;
    public static final int ROLL_BACK = 5;

    public final static String  MSG_ROLL_BACK = "/start";

    private final int rollbackID;

    public Status(int rollbackID) {
        this.rollbackID = rollbackID;
    }

    public int getRollbackStatusID() {
        return rollbackID;
    }

    protected boolean checkRollBack(BigUpdate bigUpdate)
    {
        if( bigUpdate.getUpdate() != null) {
            Message mess = bigUpdate.getUpdate().message();
            if (mess != null) {
                if (mess.text().trim().toLowerCase().contentEquals(Status.MSG_ROLL_BACK)) {
                    return true;
                }
            }
        }
        return false;
    }


    public abstract int work(Bot bot , BigUpdate bigUpdate);
}
