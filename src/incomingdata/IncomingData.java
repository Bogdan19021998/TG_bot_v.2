package incomingdata;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

public interface IncomingData {

    public boolean isCorrectData( Update update );

    public default boolean isRollBack( Update update )
    {
        Message message = update.message();
        if( message != null )
        {
            if( message.text().compareToIgnoreCase("/start" ) == 0 )
            {
                return true;


            }
        }
        return false;
    }

    public default Message getMessage( Update update )
    {

        if( update.message() != null ) {
            return update.message();
        }else
        if( update.callbackQuery() != null ) {
            return update.callbackQuery().message();
        }

        // thrown some Exception.
        return null;
    }

    public default int getUserId( Update update )
    {
        return getMessage( update ).from().id();
    }

    public default long getChatId( Update update ){
        return getMessage( update ).chat().id();
    }

    public default int getMessageId( Update update )
    {
        return getMessage( update ).messageId();
    }
}
