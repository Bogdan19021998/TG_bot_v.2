package status;

import com.company.Bot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import update.BigUpdate;

public abstract class Algoritm {

    public static final int COMPLETE = 1;
    public static final int NEXT_ALGORITM = 2;
    public static final int NOT_COMPLETE = 3;
    public static final int UNKNOWN_DATA = 4;
    public static final int ROLL_BACK = 5;


    public final static String  ROLL_BACK_MSG = "/start";
    private final int rollbackIndexStatus;

    public Algoritm(int rollbackIndexStatus) {
        this.rollbackIndexStatus = rollbackIndexStatus;
    }

    protected boolean checkRollBack( BigUpdate bigUpdate)
    {
        Message mess = bigUpdate.getUpdate().message();
        if( mess != null )
        {
            if( mess.text().trim().toLowerCase().contentEquals( Algoritm.ROLL_BACK_MSG ) )
            {
                return true;
            }
        }
        return false;
    }

    public int getRollbackIndexStatus() {
        return rollbackIndexStatus;
    }

    public abstract int work(Bot bot , BigUpdate bigUpdate);
}
