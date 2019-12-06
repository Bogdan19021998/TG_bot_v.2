package update;

import com.company.Bot;
import com.pengrad.telegrambot.model.Update;
import status.Algoritm;
import status.AlgoritmManager;
import update.BigUpdate;

public class WorkUser {
    private Bot bot;
    private BigUpdate bigUpdate;
    private int status;

    public WorkUser(Bot bot, Update update) {
        this.bot = bot;

        this.bigUpdate = new BigUpdate( update );

//        bigUpdate.printStatusData();
        status = -1;

        System.out.println("User id : " + bigUpdate.getUserID() );
    }

        // устанавливаем текущий статус пользователя
    public void setStatus()
    {
            // ищем ID в БД.
        status = UsersDB.getStatus( bigUpdate.getUserID() );
        if( status == -1 )
        {
            UsersDB.addUser( bigUpdate.getUserID() );
                // проверь обновился ли статус
            status = 0;
        }
    }

    public int workWithAlgoritm()
    {
            // ищем подходящий алгоритм
        Algoritm algoritm = AlgoritmManager.getAlgoritmByStatus(status);


        if( algoritm != null )
        {
                // если нашли алгоритм по статусу

            int resultAlgoritm = algoritm.work( bot, bigUpdate );

            if( resultAlgoritm == Algoritm.COMPLETE )
            {
                    // алгоритм сработал успешно
                UsersDB.incrStatus( bigUpdate.getUserID() );
            }else
            if ( resultAlgoritm == Algoritm.NEXT_ALGORITM){

                UsersDB.incrStatus( bigUpdate.getUserID() );

                setStatus();

                return workWithAlgoritm();
            }else
            if( resultAlgoritm == Algoritm.ROLL_BACK )
            {
                    // пользователь хочет откатить статус

                    // откатываем статус в БД.
                UsersDB.rollBackStatus( bigUpdate.getUserID(), algoritm.getRollbackIndexStatus() );


                setStatus();
//

                return workWithAlgoritm();
            }
        }
        return Algoritm.UNKNOWN_DATA;
    }

    public BigUpdate getBigUpdate() {
        return bigUpdate;
    }
}
