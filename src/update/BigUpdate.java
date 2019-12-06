package update;

import com.pengrad.telegrambot.model.Update;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BigUpdate {

    private Update update;
    private int userID;

    public BigUpdate(Update update) {
        this.update = update;

        setUserID();
    }

    private void setUserID()
    {
        userID = -1;
        if( update.message() != null ) {
            userID = update.message().from().id();
        }else
        if( update.callbackQuery() != null ) {
            userID = update.callbackQuery().from().id();
        }
    }

    public int getUserID() {
        return userID;
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