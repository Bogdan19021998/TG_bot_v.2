package incomingdata;

import com.pengrad.telegrambot.model.Update;

public abstract class IncomingFile implements IncomingData {

    @Override
    public abstract  boolean isCorrectData(Update update);

    public abstract Object getFile( Update update );
}
