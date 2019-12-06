package com.company;

import elements.OutMessage;
import elements.Table;
import elements.Text;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        String token = "1032578818:AAEcsGFQ46oLcenajdG7vUB_jPyuazzdqIk";

        TreeMap<String, OutMessage> mapMessages = new TreeMap();
        mapMessages.put( "salo"     , new Text("sila"));
        mapMessages.put( "hello"    , new Text("Hi!"));
        mapMessages.put( "sport"    , new Text("mogila :D"));
        mapMessages.put( "table1"   , new Table( TableBuilder.createTableSpecial()));





        Bot bot = new Bot( token , mapMessages);

//        BotV2 botV2 = new BotV2( token );

    }
}
