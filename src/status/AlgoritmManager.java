package status;

import java.util.HashMap;

public class AlgoritmManager {

    // выдает необходимый класс с алгоритмом по статусу.

    private static HashMap <Integer, Algoritm> mapAlgoritm;

    static
    {
        mapAlgoritm = new HashMap<>();

        mapAlgoritm.put(Algoritm_0.STATUS_ID, new Algoritm_0( -1,
                    new String[]{"Привет, я могу помочь тебе найти идеальную ИТ вакансию"}, null) );

        mapAlgoritm.put(Algoritm_1.STATUS_ID, new Algoritm_1( -1,
                new String[]{"Для начала напиши мне свое имя и фамилию "}, null) );

        mapAlgoritm.put(Algoritm_2.STATUS_ID, new Algoritm_2( 1,
                new String[]{"Отлично, я правда не проверял твои данные)"}, null));

        mapAlgoritm.put(Algoritm_3.STATUS_ID, new Algoritm_3( 2,
                new String[]{"Укажи свою специализацию "}, null));

        mapAlgoritm.put(Algoritm_4.STATUS_ID, new Algoritm_4( 3,
                new String[]{"Call_Finish"}, null));

    }

    public static synchronized Algoritm getAlgoritmByStatus( int status )
    {
        Algoritm algoritm = mapAlgoritm.get( status );
        if( algoritm == null )
        {
            new Exception("Unknown algoritm !").printStackTrace();
        }
        return algoritm;
    }
}
