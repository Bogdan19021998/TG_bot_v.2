package update;

import com.pengrad.telegrambot.model.Update;
import update.BigUpdate;

public class WorkUser {
    private BigUpdate bigUpdate;

    public WorkUser(Update update) {
        this.bigUpdate = new BigUpdate( update );

        bigUpdate.printStatusData();

        System.out.println("User id : " + bigUpdate.getUserID() );
    }

    public void workWithID()
    {
            // ищем ID в БД.
            // тестируем
        int status = UsersDB.getStatus( bigUpdate.getUserID() );

            // ищем алгоритм для этого статуса

    }

    private void createNewUser()
    {

    }
}
