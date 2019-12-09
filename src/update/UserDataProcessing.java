package update;

import com.company.Bot;
import com.pengrad.telegrambot.model.Update;
import status.Status;
import status.StatusManager;

public class UserDataProcessing {
    private Bot bot;
    private BigUpdate bigUpdate;
    private StatusManager statusManager;
    private int statusID;

    public UserDataProcessing(Bot bot, Update update, StatusManager statusManager) {
        this.bot = bot;
        this.bigUpdate = new BigUpdate( update );
        this.statusManager = statusManager;

        statusID = -1;

    }

        // устанавливаем текущий статус пользователя
    public void setStatus()
    {
            // ищем ID в БД.
        statusID = UsersDB.getStatus( bigUpdate.getUserID() );

        if( statusID == -1 )
        {
            UsersDB.addUser( bigUpdate.getUserID() );
                // проверь обновился ли статус

            statusID = 0;
        }
    }

    public int workWithStatus()
    {
            // ищем подходящий алгоритм
        Status status = statusManager.getStatus( this.statusID );

        if( status != null )
        {
                // если нашли алгоритм по статусу

            int resultStatus = status.work( bot, bigUpdate );

            switch ( resultStatus )
            {
                case Status.COMPLETE :
                {
                    UsersDB.incrStatus( bigUpdate.getUserID() );
                    break;
                }
                case Status.NOT_COMPLETE:
                {
                    return Status.NOT_COMPLETE;
                }
                case Status.NEXT_STATUS:
                {
                    UsersDB.incrStatus( bigUpdate.getUserID() );
                    return Status.NEXT_STATUS;
                }
                case Status.ROLL_BACK:
                {
                    UsersDB.rollBackStatus( bigUpdate.getUserID(), status.getRollbackStatusID() );

                    return Status.ROLL_BACK;
                }
            }
        }
        return Status.UNKNOWN_DATA;
    }

    public BigUpdate getBigUpdate() {
        return bigUpdate;
    }
}
