package outgoingdata;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;

public interface OutgoingData {
    // текст
    // кнопки

    public default boolean sendRequest( BaseRequest request ){

            // send request ( TelegramBot.execute( request ) )
         new TelegramBot("s").execute(new SendMessage(1,"s")).isOk();
        return false;
    }
}
