package com.company;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.BaseResponse;
import status.Algoritm;
import status.AlgoritmManager;
import update.BigUpdate;
import update.WorkUser;

import java.util.List;
import java.util.TreeMap;

public class Bot extends TelegramBot {

    private GetUpdates getUpdates;


    public Bot(String botToken ) {
        super(botToken);


            // шаблон для получения входящих сообщений
        getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

            // создаю слушатель для получения входящих сообщений
        setUpdatesListener(updat -> {
            System.out.println("Server take new update data.");

                // обрабатываю ОДНО входящее сообщение
            int statusUpdated = processUpdates_2();

            return statusUpdated;
        });
    }


    private int processUpdates_2( )
    {
        System.out.println("Process updates");

        List<Update> updates = execute(getUpdates).updates();

        if( updates != null )
        {
            System.out.println("COUNT updates : " + updates.size() );

            // получаем первое сообщение с Telegram.
            Update update = updates.get(0);

                // обьект для работы с update
            WorkUser workUser = new WorkUser( this, update );
                // смотрю/устанавливаю статус в БД
            workUser.setStatus();
                // нахожу необходимый алгоритм для этого статуса
            int result = workUser.workWithAlgoritm();

                // если алгорит сработал успешно, то этот update больше не появится в TG.
            return ( result == Algoritm.COMPLETE ) ? workUser.getBigUpdate().getUpdate().updateId() : UpdatesListener.CONFIRMED_UPDATES_ALL;


        }else{
            // ложный вызов, список входящих сообщений в БОТ - пустой.
            System.out.println("ERROR : processUpdates.");
            new Exception().printStackTrace();
        }

        // подтверждаю, что список пустой
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

        // отправляет сообщения
    public BaseResponse sendMessage( BaseRequest request )
    {
        return execute( request );
    }

}


