package com.company;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.BaseResponse;
import status.ButtonManager;
import status.Status;
import status.StatusManager;
import update.BigUpdate;
import update.UserDataProcessing;
import update.UsersDB;

import java.util.List;

public class Bot extends TelegramBot {

    private final GetUpdates getUpdates;
    private final StatusManager statusManager;
    private final ButtonManager buttonManager;
    private final UsersDB userDB;


    public Bot(String botToken , StatusManager statusManager) {
        super(botToken);
        this.statusManager = statusManager;
        this.buttonManager = new ButtonManager();
        this.userDB = new UsersDB();


            // шаблон для получения входящих сообщений
        getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

            // создаю слушатель для получения входящих сообщений
        setUpdatesListener(update -> {
            System.out.println("Server take new update data.");

                // обрабатываю ОДНО входящее сообщение
            int statusUpdated = processAllUpdates();

            return UpdatesListener.CONFIRMED_UPDATES_ALL ;
        });
    }


    private int processAllUpdates( )
    {
        System.out.println("Process updates");

        List<Update> updates = execute(getUpdates).updates();

        if( updates != null || updates.size() != 0)
        {
            System.out.println("COUNT updates : " + updates.size() );

            // получаем первое сообщение с Telegram.
            Update update = updates.get(0);

            return processUpdate( new BigUpdate( update ));


        }else{
            // ложный вызов, список входящих сообщений в БОТ - пустой.
            System.out.println("ERROR : processUpdates.");
            new Exception().printStackTrace();
        }

        // подтверждаю, что список пустой
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public int processUpdate( BigUpdate bigUpdate )
    {
        // обьект для работы с update
        UserDataProcessing userDataProcessing = new UserDataProcessing( this, bigUpdate  );
        // смотрю/устанавливаю статус в БД
        userDataProcessing.setStatus();
        // нахожу необходимый алгоритм для этого статуса
        int resultStatus = userDataProcessing.workWithStatus();

        if( resultStatus == Status.NEXT_STATUS || resultStatus == Status.ROLL_BACK)
        {
            processUpdate( userDataProcessing.getBigUpdate().cloneWithOutUpdate() );
        }
        // если алгорит сработал успешно, то этот update больше не появится в TG.
        return  userDataProcessing.getBigUpdate().getUpdateID();
    }
        // отправляет сообщения
    public BaseResponse sendMessage(BaseRequest request )
    {

        BaseResponse responseMessage = execute( request );

        return responseMessage;
    }


    public StatusManager getStatusManager() {
        return statusManager;
    }

    public ButtonManager getButtonManager() {
        return buttonManager;
    }

    public UsersDB getUserDB() {
        return userDB;
    }
}


