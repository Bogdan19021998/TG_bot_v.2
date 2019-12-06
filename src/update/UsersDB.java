package update;

import java.util.HashMap;

public class UsersDB {

    private static HashMap<Integer, Integer> mapUsers;

    public static final int BOGDAN_ID = 1062233435;

    {
        mapUsers = new HashMap<>();
        addUser( BOGDAN_ID );
    }

    public static void addUser( int userID )
    {
        mapUsers.put(userID, 0);
    }

    public static int getStatus( int userID)
    {
        Integer status = mapUsers.get( userID );
        if( status == null )
        {
            addUser( userID );
                // Добавь проверку добавлен ли пользователт в БД.

            status = 0;
        }

        return status;
    }
}
