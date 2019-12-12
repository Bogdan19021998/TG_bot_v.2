package outgoingdata;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

public abstract class OutgoingChangedMessage implements OutgoingData{

    public abstract boolean changeUserMessage( long chatId, int messageId, String textMessage );

    public abstract boolean changeUserMessage( long chatId, int messageId, String textMessage, InlineKeyboardMarkup markup );
}
