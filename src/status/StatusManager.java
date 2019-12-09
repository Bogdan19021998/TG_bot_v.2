package status;

import com.company.Bot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import update.BigUpdate;

import java.util.HashMap;

public class StatusManager {

    // выдает необходимый класс с алгоритмом по статусу.

    private HashMap<Integer, Status> mapStatus;


    public StatusManager(HashMap <Integer, DialogMessages> mapDialogMessages ) {
        this.mapStatus = new HashMap<>();

        fillInTheStatusCard( mapDialogMessages );
    }

    private void fillInTheStatusCard(HashMap <Integer, DialogMessages> mapDialogMessages )
    {
        /*
            1)send for user msg with greeting
            2) add button with /start.
        */
        Status statusHello = new StatusMessage(0, mapDialogMessages.get(0) ){
            @Override
            public int work(Bot bot, BigUpdate bigUpdate) {
                Message messageIn = bigUpdate.getUpdate().message();
                if( messageIn != null )
                {

                    // Create out msg with chat_ID and text_msg :  "Привет, я могу помочь тебе найти идеальную ИТ вакансию"
                    SendMessage message = new SendMessage(
                            bigUpdate.getChatID(),
                            getDialogMessages().getOutgoingMessage() );

                    // add button for roll back status.
                    message.replyMarkup( bot.getButtonManager().getRollBackButtonMarkup() );

                    // send msg
                    BaseResponse response = bot.sendMessage( message , bigUpdate);
                    return ( response.isOk() ) ? Status.COMPLETE : Status.NOT_COMPLETE;

                }
                return Status.UNKNOWN_DATA;
            }
        };
        mapStatus.put(0, statusHello );

        /*
            1) check input message by "/start"

         */
        Status statusPressButStart = new StatusMessage(0,mapDialogMessages.get(1)) {
            @Override
            public int work(Bot bot, BigUpdate bigUpdate) {
                Message messageIn = bigUpdate.getUpdate().message();

                if( messageIn != null )
                {
                    String textIn = messageIn.text().trim().toLowerCase();

                    if( textIn.contentEquals( getDialogMessages().getIncomingMessage() ) ) {

                        return Status.NEXT_STATUS ;
                    }
                }
                new Exception( "UNKNOWN_DATA").printStackTrace();
                return Status.UNKNOWN_DATA;
            }
        };
        mapStatus.put(1, statusPressButStart);

        /*
           1) send msg with "To start, write me your name and surname"
         */
        Status statusSendMsgName = new StatusMessage(0, mapDialogMessages.get(2) ) {
            @Override
            public int work(Bot bot, BigUpdate bigUpdate) {
//                if( checkRollBack( bigUpdate) ){ return ROLL_BACK; }
                // create out message
                SendMessage message = new SendMessage(
                        bigUpdate.getChatID(),
                        getDialogMessages().getOutgoingMessage() );

                // send out message
                BaseResponse response = bot.sendMessage( message , bigUpdate);

                return ( response.isOk() ) ? Status.COMPLETE : Status.NOT_COMPLETE;
                }
            };
        mapStatus.put(2, statusSendMsgName );


        /*
            1) Check input string. Must have ( firstname & lastname )
            2) Send msg :
                    2.1 confirmation message
                    2.2 error message
         */
        Status statusName = new StatusMessage( 0,mapDialogMessages.get(3)) {
            @Override
            public int work(Bot bot, BigUpdate bigUpdate) {
//                if( checkRollBack( bigUpdate) ){ return ROLL_BACK; }
                Message messageIn = bigUpdate.getUpdate().message();

                if( messageIn != null )
                {
                    String [] arrayWords = messageIn.text().split(" ");


                        // block for checkling first name and last name
                        // ...
                    boolean isGoodName = ( arrayWords.length >=2 && arrayWords.length  <= 3) ;

                    if( isGoodName )
                    {

                        return Status.NEXT_STATUS;
                    } else
                    {
                        SendMessage messageOut = new SendMessage(
                                bigUpdate.getChatID(),
                                getDialogMessages().getErrorMessage());

                        BaseResponse response = bot.sendMessage( messageOut , bigUpdate);
                            // -> must check response for messageOut

                        return Status.NOT_COMPLETE;
                    }
                }
                new Exception( "UNKNOWN_DATA").printStackTrace();
                return Status.UNKNOWN_DATA;
            }
        };

        mapStatus.put(3, statusName);

        Status statusSpecialSend = new StatusMessage(2, mapDialogMessages.get(4)){

            @Override
            public int work(Bot bot, BigUpdate bigUpdate) {
//                if( checkRollBack( bigUpdate) ){ return ROLL_BACK; }

                    // create out message
                SendMessage message = new SendMessage(
                        bigUpdate.getChatID(),
                        getDialogMessages().getOutgoingMessage() );

                    // create table for special
                message.replyMarkup( bot.getButtonManager().getSpecialButtons() );


                // send out message
                BaseResponse response = bot.sendMessage( message , bigUpdate);

                return ( response.isOk() ) ? Status.COMPLETE : Status.NOT_COMPLETE;
            }
        };

        mapStatus.put(4, statusSpecialSend );

        Status statusListenerSpecialTable = new Status(2) {
            @Override
            public int work(Bot bot, BigUpdate bigUpdate) {
//                if( checkRollBack( bigUpdate) ){ return ROLL_BACK; }

                CallbackQuery callback = bigUpdate.getUpdate().callbackQuery();

                if( callback != null )
                {
                    String textCallBack = callback.data();
                    System.out.println("1 : " + textCallBack);

                    if( !textCallBack.contentEquals("Call_Finish") )
                    {
                            // user pressed other button
                        ButtonManager buttonManager = bot.getButtonManager();
                        InlineKeyboardMarkup inlineKeyMark = callback.message().replyMarkup();
                        InlineKeyboardButton [][] arrayButtons = inlineKeyMark.inlineKeyboard();

                        int [] indexInArray = buttonManager.findButtonWithCallBackName( arrayButtons , textCallBack);

                        if( indexInArray != null ){
                            InlineKeyboardButton buttonOld = arrayButtons[ indexInArray[0] ][ indexInArray[1] ];
                            if( buttonManager.checkButtonOnMarker( buttonOld ) )
                            {
                                    // find marker -> remove marker
                                arrayButtons[ indexInArray[0] ][ indexInArray[1] ] =
                                        buttonManager.removeMarkerWithButton( buttonOld );
                            }else{
                                    // not find marker -> add marker
                                arrayButtons[ indexInArray[0] ][ indexInArray[1] ] =
                                        buttonManager.addMarkerForButton( buttonOld );
                            }

                            EditMessageReplyMarkup messageOut = new EditMessageReplyMarkup(
                                    bigUpdate.getChatID(),
                                    bigUpdate.getMessageID());
                            messageOut.replyMarkup( inlineKeyMark );

                                // send new buttons
                            bot.sendMessage( messageOut , bigUpdate);
                                // create answer fore remove icon ( loading )
                            AnswerCallbackQuery answer = new AnswerCallbackQuery( callback.id() );
                            BaseResponse resp = bot.sendMessage(answer, bigUpdate);
                            System.out.println("Resp remove load : " + resp.isOk() );
                            return Status.NOT_COMPLETE;

                        } else {
                            return Status.UNKNOWN_DATA;
                        }

                    }else{
                            // user pressed "Finish" button
                        return Status.NEXT_STATUS ;
                    }

                }
                new Exception( "UNKNOWN_DATA").printStackTrace();
                return Status.UNKNOWN_DATA;
            }
        };

        mapStatus.put(5, statusListenerSpecialTable);

    }

    public Status getStatus(int statusID )
    {
        Status status = mapStatus.get( statusID );
        if( status == null )
        {
            new Exception("Unknown status !").printStackTrace();
        }
        return status;
    }
}
