package testEnum;

import com.pengrad.telegrambot.model.Update;
import incomingdata.IncomingCallback;
import incomingdata.IncomingText;
import outgoingdata.OutgoingMessage;

public enum EnumProcessStatus implements MethodWork {
    SEND_HELLO(1) {

        @Override
        public int work(Update update) {

            return 0;
        };
    };

//    CHECK_IS_PRESSED_BUT_START(2) {
//        @Override
//        public int work(Update update) {
//            if( incomingText.isCorrectData( update ) ){
//                if( incomingText.getText( update ).contentEquals("SomeText ") ){
//                    return 1;
//                }
//            }
//            return -1;
//        }
//    };





    public static void setValues( String [] arrayMsgs )
    {
        EnumProcessStatus[] arrayEnum = EnumProcessStatus.values();
        if( arrayEnum != null )
        {
            for (int i = 0; i < arrayEnum.length ; i++ )
            {
                arrayEnum[i].setMsg( arrayMsgs[i] );
            }
        }
    }

//    private static final IncomingText incomingText = new IncomingText();
//
//    private static final IncomingCallback incomingCallback = new IncomingCallback();
//
//    private static final OutgoingMessage outgoingMessage = new OutgoingMessage();


    private String msg;

    private int status_id;

    EnumProcessStatus(int status_id) {
        this.status_id = status_id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public  String getMsg() {
        return msg;
    }
}
