package update;

import java.util.HashMap;
import java.util.TreeMap;

public class UsersDB {
    // БД пользователей
    // 1) добавляет пользователя
    // 2) устанавливает дефолтный статус
    // 3) инкриментирует статус
    // 4) откатывает статус до необходимого значения
    // 5) выдает статус пользователя

    private HashMap<Integer, Integer> mapUsers;


    public final int BOGDAN_ID = 1062233435;

    public UsersDB() {
        this.mapUsers = new HashMap<>();
        this.mapUsers.put( BOGDAN_ID, 0 );
    }

    public void addUser(int userID )
    {
        mapUsers.put(userID, 0);
    }

    public int getStatus( int userID)
    {
        System.out.println("User ID  : " + userID);
        Integer status = mapUsers.get( userID );
        return ( status != null ) ? status : -1;
    }

    public boolean incrStatus(  int userID )
    {
        int statusBefore = mapUsers.get( userID );

        int statusUP = statusBefore + 1;

        mapUsers.put( userID, statusUP );

        int statusAfter = mapUsers.get( userID );

        System.out.println("Update status user in DB : ( " + statusBefore + " ) - > ( " + statusAfter + " ) " );
        return false;
    }

    public boolean rollBackStatus( int userID, int status )
    {
        int statusBefore = mapUsers.get( userID );

        mapUsers.put( userID, status );

        int statusAfter = mapUsers.get( userID );

        System.out.println("RollBack status user in DB : ( " + statusBefore + " ) - > ( " + statusAfter + " ) " );
        return false;
    }
}
