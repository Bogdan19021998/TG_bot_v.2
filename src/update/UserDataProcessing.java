package update;

import com.company.Bot;
import com.pengrad.telegrambot.model.Update;
import status.Status;
import status.StatusManager;

public class UserDataProcessing {
    private Bot bot;
    private BigUpdate bigUpdate;
    private StatusManager statusManager;
    private UsersDB usersDB;
    private int statusID;

    public UserDataProcessing(Bot bot, BigUpdate bigUpdate) {
        this.bot = bot;
        this.bigUpdate = bigUpdate;
        this.statusManager = bot.getStatusManager();
        this.usersDB = bot.getUserDB();

        statusID = -1;

    }

        // устанавливаем текущий статус пользователя
    public void setStatus()
    {
            // ищем ID в БД.
        statusID = usersDB.getStatus( bigUpdate.getUserID() );

        if( statusID == -1 )
        {
            usersDB.addUser( bigUpdate.getUserID() );
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
                    usersDB.incrStatus( bigUpdate.getUserID() );
                    break;
                }
                case Status.NOT_COMPLETE:
                {
                    return Status.NOT_COMPLETE;
                }
                case Status.NEXT_STATUS:
                {
                    usersDB.incrStatus( bigUpdate.getUserID() );
                    return Status.NEXT_STATUS;
                }
                case Status.ROLL_BACK:
                {
                    usersDB.rollBackStatus( bigUpdate.getUserID(), status.getRollbackStatusID() );

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
