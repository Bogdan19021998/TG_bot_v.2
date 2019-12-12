package incomingdata;

import com.pengrad.telegrambot.model.Update;

public abstract class IncomingText implements IncomingData {

    @Override
    public abstract boolean isCorrectData(Update update);

    public abstract String getText(Update update );
}
