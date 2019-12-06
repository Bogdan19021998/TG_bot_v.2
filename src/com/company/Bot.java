package com.company;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.BaseResponse;
import update.BigUpdate;
import elements.OutMessage;
import elements.Table;
import elements.Text;
import update.WorkUser;

import java.util.List;
import java.util.TreeMap;

public class Bot extends TelegramBot {

    private GetUpdates getUpdates;

    private TreeMap<String, OutMessage> mapMesseges;

    public Bot(String botToken, TreeMap<String, OutMessage> mapMesseges) {
        super(botToken);

        this.mapMesseges = mapMesseges;

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


    private int processUpdates( )
    {
        System.out.println("Process updates");

        List<Update> updates = execute(getUpdates).updates();

        if( updates != null )
        {
                System.out.println("COUNT updates : " + updates.size() );
                    // получаем первое сообщение с Telegram.
                Update update = updates.get(0);

                    // обрабатываем входящее сообщение и формируем исходящее сообщение
                BaseRequest baseRequest = createAnswer(update);

                if( baseRequest != null ) {
                    // отправляем сообщение
                    BaseResponse outReqest = execute(baseRequest);

                    // получилось ли отправить сообщение?
                    boolean isOk = outReqest.isOk();

                    System.out.println("Status sended message : " + outReqest.isOk());
                    // формируем статус отправки сообщения
                    if ( !isOk) {
                        // пользовтельское сообщение не обработано ( ответ не был доставлен !)
                        // ( будет повторная обработка этого сообщения )
                        return UpdatesListener.CONFIRMED_UPDATES_NONE;
                    }
                }

                // пользовательское сообщение успешно обработано.
                // больше оно не прийдет в БОТ.
                return update.updateId();

        }else{
                // ложный вызов, список входящих сообщений в БОТ - пустой.
            System.out.println("ERROR : processUpdates.");
            new Exception().printStackTrace();
        }
            // подтверждаю, что список пустой
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
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

            WorkUser workUser = new WorkUser( update );

            return update.updateId();

            /*
            // обрабатываем входящее сообщение и формируем исходящее сообщение
            BaseRequest baseRequest = createAnswer(update);

            if( baseRequest != null ) {
                // отправляем сообщение
                BaseResponse outReqest = execute(baseRequest);

                // получилось ли отправить сообщение?
                boolean isOk = outReqest.isOk();

                System.out.println("Status sended message : " + outReqest.isOk());
                // формируем статус отправки сообщения
                if ( !isOk) {
                    // пользовтельское сообщение не обработано ( ответ не был доставлен !)
                    // ( будет повторная обработка этого сообщения )
                    return UpdatesListener.CONFIRMED_UPDATES_NONE;
                }
            }

            // пользовательское сообщение успешно обработано.
            // больше оно не прийдет в БОТ.
            return update.updateId();
            */


        }else{
            // ложный вызов, список входящих сообщений в БОТ - пустой.
            System.out.println("ERROR : processUpdates.");
            new Exception().printStackTrace();
        }
        // подтверждаю, что список пустой
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private BaseRequest createAnswer( Update update )
    {
        new BigUpdate( update ).printStatusData();
        Message mess = update.message();

        String textUser = mess.text().toLowerCase().trim();
        long shatID = mess.chat().id();

        OutMessage answer = mapMesseges.get( textUser );

        BaseRequest baseRequest = null;

        if( answer == null ){
            answer = new Text("I don't now this command!");
        }

        if( answer instanceof Text )
        {
                // создаем сообщение с текстом
            baseRequest = new SendMessage(shatID, (String)answer.getMessage() );
        }else
        if( answer instanceof Table){
                // создаем сообщение с таблице


            baseRequest = new SendMessage(shatID, "ff").replyMarkup(  (InlineKeyboardMarkup)answer.getMessage() );

        }

        return baseRequest;
    }

    private Keyboard createButton ()
    {
            // создаю кнопку с маштабированием для экрана.
        return new ReplyKeyboardMarkup( new KeyboardButton[]{new KeyboardButton( "/start" )} ).resizeKeyboard(true);
    }
}


