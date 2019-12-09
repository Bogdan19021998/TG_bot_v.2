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

import java.util.List;

public class Bot extends TelegramBot {

    private final GetUpdates getUpdates;
    private final StatusManager statusManager;
    private final ButtonManager buttonManager;


    public Bot(String botToken , StatusManager statusManager) {
        super(botToken);
        this.statusManager = statusManager;
        this.buttonManager = new ButtonManager();


            // шаблон для получения входящих сообщений
        getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

            // создаю слушатель для получения входящих сообщений
        setUpdatesListener(update -> {
            System.out.println("Server take new update data.");

                // обрабатываю ОДНО входящее сообщение
            int statusUpdated = processAllUpdates();

            return statusUpdated;
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

            return processUpdate( update );


        }else{
            // ложный вызов, список входящих сообщений в БОТ - пустой.
            System.out.println("ERROR : processUpdates.");
            new Exception().printStackTrace();
        }

        // подтверждаю, что список пустой
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public int processUpdate( Update update )
    {
        // обьект для работы с update
        UserDataProcessing userDataProcessing = new UserDataProcessing( this, update , statusManager );
        // смотрю/устанавливаю статус в БД
        userDataProcessing.setStatus();
        // нахожу необходимый алгоритм для этого статуса
        int resultStatus = userDataProcessing.workWithStatus();

        if( resultStatus == Status.NEXT_STATUS )
        {
            processUpdate( update );
        }
        // если алгорит сработал успешно, то этот update больше не появится в TG.
        return  userDataProcessing.getBigUpdate().getUpdate().updateId();
    }
        // отправляет сообщения
    public BaseResponse sendMessage(BaseRequest request , BigUpdate bigUpdate)
    {
        BaseResponse responseMessage = execute( request );

//        BaseResponse responseButton = execute( new SendMessage(
//                    bigUpdate.getChatID(), " ")
//                    .replyMarkup( buttonManager.getRollBackButtonMarkup() ));
//        System.out.println("Response button : " + responseButton.isOk() );
        return responseMessage;
    }

    public ButtonManager getButtonManager() {
        return buttonManager;
    }
}


