package status;

import com.company.Bot;
import com.company.TableBuilder;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import update.BigUpdate;
import update.UsersDB;

public class Algoritm_1 extends AlgoritmMess{

    public final static int STATUS_ID = 1;

    public Algoritm_1(int rollBack, String [] messOne, String [] messTwo) {
        super( rollBack, messOne, messTwo);
    }

    @Override
    public int work(Bot bot , BigUpdate bigUpdate) {


        Update update = bigUpdate.getUpdate();
        Message mess = update.message();

        if( mess != null )
        {
            String textIn = mess.text().trim().toLowerCase();

            if( textIn.contentEquals(Algoritm.ROLL_BACK_MSG ) )
            {
                    // Пользоавтель отправил /start
                System.out.println("Пользователь нажал " + Algoritm.ROLL_BACK_MSG );

                long chatID = mess.chat().id();
                SendMessage message = new SendMessage(chatID, arrayMessage[0] );
                message.replyMarkup( TableBuilder.createButton( Algoritm.ROLL_BACK_MSG));

                // отправил сообщение
                BaseResponse response = bot.sendMessage( message );

                return ( response.isOk() ) ? Algoritm.COMPLETE : Algoritm.NOT_COMPLETE;
            }
        }
        new Exception( "UNKNOWN_DATA").printStackTrace();
        return Algoritm.UNKNOWN_DATA;
    }
}
