package status;

import com.company.Bot;
import com.company.TableBuilder;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import update.BigUpdate;

public class Algoritm_4 extends AlgoritmMess{

    public final static int STATUS_ID = 4;

    public Algoritm_4(int rollBack, String [] messOne, String [] messTwo) {
        super( rollBack, messOne, messTwo);
    }

    @Override
    public int work(Bot bot , BigUpdate bigUpdate) {

        if( checkRollBack( bigUpdate) )
        {
            return Algoritm.ROLL_BACK;
        }

        Update update = bigUpdate.getUpdate();

        CallbackQuery callbackQuery = update.callbackQuery();

        if( callbackQuery != null )
        {
            Message messageIn = callbackQuery.message();
            String textIn = messageIn.text();

            long chatID = messageIn.chat().id();
            SendMessage messageOut = new SendMessage(chatID, "");

            System.out.println("CAllback TExt : " + textIn);
            System.out.println("  ");
            if( textIn.contentEquals(arrayMessage[0]) )
            {
                System.out.println("User press on EXIT Button.");

                messageOut.replyMarkup( TableBuilder.createButton( Algoritm.ROLL_BACK_MSG) );
                return Algoritm.COMPLETE;

            }else{
                System.out.println("User press on OTHER Button.");
            }
            return Algoritm.NOT_COMPLETE;
        }
        new Exception( "UNKNOWN_DATA").printStackTrace();
        return Algoritm.UNKNOWN_DATA;
    }
}
