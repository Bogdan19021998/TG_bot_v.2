package status;

import com.company.Bot;
import com.company.TableBuilder;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import update.BigUpdate;

public class Algoritm_0 extends AlgoritmMess{

    public final static int STATUS_ID = 0;

    public Algoritm_0(int rollBack, String [] messOne, String [] messTwo) {
        super( rollBack, messOne, messTwo);
    }

    @Override
    public int work(Bot bot , BigUpdate bigUpdate) {

        Update update = bigUpdate.getUpdate();
        Message mess = update.message();

        if( mess != null )
        {
            String textIn = mess.text().trim().toLowerCase();


                // Пользоавтель отправил /start
                // Формируем ответ
                long chatID = mess.chat().id();

                    // создал сообщение с текстом : Привет, я могу помочь тебе найти идеальную ИТ вакансию
                SendMessage message = new SendMessage(chatID, arrayMessage[0] );

                    // добавил кнопку
                message.replyMarkup(TableBuilder.createButton( Algoritm.ROLL_BACK_MSG ));

                    // отправил сообщение
                BaseResponse response = bot.sendMessage( message );


            return ( response.isOk() ) ? Algoritm.COMPLETE : Algoritm.NOT_COMPLETE;

        }
        return Algoritm.UNKNOWN_DATA;
    }
}
