package status;

import com.company.Bot;
import com.company.TableBuilder;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import update.BigUpdate;

public class Algoritm_3 extends AlgoritmMess{

    public final static int STATUS_ID = 3;

    public Algoritm_3(int rollBack, String [] messOne, String [] messTwo) {
        super( rollBack, messOne, messTwo);
    }

    @Override
    public int work(Bot bot , BigUpdate bigUpdate) {

        System.out.println("Algoritm_3 is start.");
        if( checkRollBack( bigUpdate) )
        {
            return Algoritm.ROLL_BACK;
        }

        Update update = bigUpdate.getUpdate();

        Message mess = update.message();


        long chatID = mess.chat().id();
        SendMessage message = new SendMessage(chatID, "sss");

        message.replyMarkup( TableBuilder.createTableSpecial() );

        // отправил сообщение
        BaseResponse response = bot.sendMessage( message );
        System.out.println("Status : " +  response.isOk());
        return ( response.isOk() ) ? Algoritm.NEXT_ALGORITM : Algoritm.NOT_COMPLETE;
    }
}
