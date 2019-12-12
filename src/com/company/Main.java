package com.company;


import status.DialogMessages;
import status.StatusManager;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        String token = "1032578818:AAEcsGFQ46oLcenajdG7vUB_jPyuazzdqIk";
        StatusManager statusManager = new StatusManager( createDialogs() );
        Bot bot = new Bot( token , statusManager);

//        IncomingData incomingMessage = new IncomingCallback();

//        CallbackQuery callBack = incomingMessage.getData( new Update() );

    }

    public static HashMap<Integer, DialogMessages> createDialogs()
    {
        HashMap <Integer, DialogMessages> mapDialogs = new HashMap<>();

        mapDialogs.put(0, new DialogMessages(
                null,
                "Привет, я могу помочь тебе найти идеальную ИТ вакансию",
                null));
        mapDialogs.put(1, new DialogMessages(
                "/start",
                null,
                "Чтобы начать работать с ботом нажмите кнопку : /start"));
        mapDialogs.put(2, new DialogMessages(
                null,
                "Для начала напиши мне свое имя и фамилию",
                null));
        mapDialogs.put(3, new DialogMessages(
                null,
                null,
                "Я не могу распознать введенную тобой информацию как имя с фамилией." +
                        " В этих данных не может быть чисел, ссылок и прочего. Только текст, " +
                        "состоящий из двух или трех отдельно написанных слов. Попробуй еще раз.\n"));
        mapDialogs.put(4, new DialogMessages(
                null,
        "Превосходно, теперь укажи свою специализацию нажав на " +
                "соответствующие кнопки. Ты можешь выбрать несколько, но не больше пяти. " +
                "По окончанию нажми на кнопку Завершить.\n",
                null));
        mapDialogs.put(5, new DialogMessages(
                null,
                "Круто, ты выбрал специализацию: Java. Можешь выбрать еще " +
                        "несколько или завершить этот этап.",
                null));

        return mapDialogs;
    }
}
