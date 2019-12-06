package status;

import com.company.Bot;
import com.company.TableBuilder;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import update.BigUpdate;
import update.UsersDB;

public class Algoritm_2 extends AlgoritmMess{

    public final static int STATUS_ID = 2;

    public Algoritm_2(int rollBack, String [] messOne, String [] messTwo) {
        super( rollBack, messOne, messTwo);
    }

    @Override
    public int work(Bot bot , BigUpdate bigUpdate) {

        if( checkRollBack( bigUpdate) )
        {
            return Algoritm.ROLL_BACK;
        }
        Update update = bigUpdate.getUpdate();

        Message mess = update.message();

        if( mess != null )
        {
            String textIn = mess.text().trim().toLowerCase();


            long chatID = mess.chat().id();
            SendMessage message = new SendMessage(chatID, arrayMessage[0] + " - > " + textIn);
            message.replyMarkup( TableBuilder.createButton( Algoritm.ROLL_BACK_MSG));

            // отправил сообщение
            BaseResponse response = bot.sendMessage( message );

            return ( response.isOk() ) ? Algoritm.NEXT_ALGORITM : Algoritm.NOT_COMPLETE;


        }
        new Exception( "UNKNOWN_DATA").printStackTrace();
        return Algoritm.UNKNOWN_DATA;
    }
}
