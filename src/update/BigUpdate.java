package update;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BigUpdate {
    // класс для получения ID пользователя и отображения типов входящих данных от пользователя (printStatusData () )
    private Update update;

    private int userID;
    private long chatID;
    private int messageID;

    public BigUpdate(Update update) {
        this.update = update;

        setChatAndUSerID();
    }

    private void setChatAndUSerID()
    {
        if( update.message() != null ) {
            Message message = update.message();
            userID = message.from().id();
            chatID = message.chat().id();
            messageID = message.messageId();
        }else
        if( update.callbackQuery() != null ) {
            CallbackQuery callBack = update.callbackQuery();
            userID = callBack.from().id();
            chatID = callBack.message().chat().id();
            messageID = callBack.message().messageId();
        }else{
            new Exception("Unknown data of updata").printStackTrace();
        }
    }

    public int getUserID() {
        return userID;
    }

    public long getChatID() {
        return chatID;
    }

    public int getMessageID() {
        return messageID;
    }

    public Update getUpdate() {
        return update;
    }

    public void printStatusData()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(update.message() != null).append(  " : message" ).append('\n')
            .append(update.editedMessage() != null).append(  " : editedMessage" ).append('\n')
            .append(update.channelPost() != null).append(  " : channelPost" ).append('\n')
            .append(update.editedChannelPost() != null).append(  " : editedChannelPost" ).append('\n')
            .append(update.inlineQuery() != null).append(  " : inlineQuery" ).append('\n')
            .append(update.chosenInlineResult() != null).append(  " : chosenInlineResult" ).append('\n')
            .append(update.callbackQuery() != null).append(  " : callbackQuery" ).append('\n')
            .append(update.shippingQuery() != null).append(  " : shippingQuery" ).append('\n')
            .append(update.preCheckoutQuery() != null).append(  " : preCheckoutQuery" ).append('\n')
            .append(update.poll() != null).append(  " : poll" ).append('\n');

        System.out.println( sb.toString() );
    }
}
