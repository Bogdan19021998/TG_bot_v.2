package status;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.request.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ButtonManager
{
    // класс для создания меню с кнопками, таблицами

    private String marker;
    private InlineKeyboardMarkup specialButtons;
    private ReplyKeyboardMarkup rollBackButtonMarkup;

    public ButtonManager() {
        createMarker();
        createTableSpecial();
        createRollBackButton();
    }

    public int [] findButtonWithCallBackName(InlineKeyboardButton [][] arrayButtons , String callBackName )
    {
        for (int i = 0; i < arrayButtons.length ; i++ ) {
            for (int j = 0; j < arrayButtons[i].length; j++) {
                if( arrayButtons[i][j].callbackData().contentEquals( callBackName ) ) {
                    return new int []{i,j};
                }
            }
        }
        return null;
    }

    public boolean checkButtonOnMarker(InlineKeyboardButton button )
    {
        return ( button.text().lastIndexOf(marker) != -1 );
    }

    public InlineKeyboardButton removeMarkerWithButton( InlineKeyboardButton button )
    {
        String textBefore = button.text();
        int index = button.text().lastIndexOf( marker );
        String textAfter = ( index != -1 ) ? textBefore.substring(0,index) : textBefore;

        return new InlineKeyboardButton( textAfter ).callbackData( button.callbackData() );
    }

    public InlineKeyboardButton addMarkerForButton(  InlineKeyboardButton button )
    {
        return new InlineKeyboardButton( button.text() + marker )
                .callbackData( button.callbackData() );
    }

    public InlineKeyboardMarkup getSpecialButtons() {
        return specialButtons;
    }

    public ReplyKeyboardMarkup getRollBackButtonMarkup() {
        return rollBackButtonMarkup;
    }

    private void createMarker()
    {
        this.marker = new String(
                new byte[]{(byte)(' '),(byte)0xE2, (byte)0x9C, (byte)0x85},
                StandardCharsets.UTF_8);
    }

    private void createTableSpecial()
    {
        specialButtons = new InlineKeyboardMarkup(
                    new InlineKeyboardButton[]
                        {
                             new InlineKeyboardButton(".NET").callbackData("Call_.NET"),
                             new InlineKeyboardButton("Front-End / JS").callbackData("Call_Front-End/JS"),
                        },
                    new InlineKeyboardButton[]
                        {
                            new InlineKeyboardButton("Android").callbackData("Call_Android"),
                            new InlineKeyboardButton("Node.js").callbackData("Call_Node.js"),
                        },
                    new InlineKeyboardButton[]
                        {
                            new InlineKeyboardButton("C / C++").callbackData("Call_C/C++"),
                            new InlineKeyboardButton("PHP").callbackData("Call_PHP"),
                        },
                    new InlineKeyboardButton[]
                        {
                            new InlineKeyboardButton("Goaling").callbackData("Call_Goaling"),
                            new InlineKeyboardButton("Python").callbackData("Call_Python"),
                        },
                    new InlineKeyboardButton[]
                        {
                           new InlineKeyboardButton("iOS").callbackData("Call_iOS"),
                          new InlineKeyboardButton("Ruby / Rails").callbackData("Call_Ruby/Rails"),
                        },
                    new InlineKeyboardButton[]
                        {
                            new InlineKeyboardButton("Java").callbackData("Call_Java"),
                            new InlineKeyboardButton("Scala").callbackData("Call_Scala"),

                        },
                    new InlineKeyboardButton[]
                        {
                              new InlineKeyboardButton("Finish").callbackData("Call_Finish")
                        });
    }

    private void createRollBackButton()
    {
        rollBackButtonMarkup = new ReplyKeyboardMarkup( new KeyboardButton[]{ new KeyboardButton( "/start" )} ).resizeKeyboard(true);
    }
}
