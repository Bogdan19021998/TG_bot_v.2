package elements;

import com.pengrad.telegrambot.model.request.Keyboard;

import java.io.Serializable;

public class MyButton extends Keyboard implements Serializable {

    private static final long serialVersionUID = 0L;
    private boolean request_contact;
    private boolean request_location;
    private String text;
    private String callback_data;


}
