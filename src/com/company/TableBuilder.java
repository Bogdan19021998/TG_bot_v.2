package com.company;

import com.pengrad.telegrambot.model.request.*;

import java.util.ArrayList;

public class TableBuilder
{
    // класс для создания меню с кнопками

    public static InlineKeyboardMarkup  createTableSpecial()
    {
        InlineKeyboardButton[][] arrSpecial = new InlineKeyboardButton[][]
                {
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
                                    new InlineKeyboardButton("Call_Scala").callbackData("Call_Scala"),

                                },
                        new InlineKeyboardButton[]
                                {
                                      new InlineKeyboardButton("Finish").callbackData("Call_Finish")
                                }
                    };


        return new InlineKeyboardMarkup( arrSpecial );
    }

    public static ReplyKeyboardMarkup  createTableSpecialKeyboard()
    {
        KeyboardButton[][] arrSpecial = new KeyboardButton[][]
                {
                        new KeyboardButton[]
                                {
                                        new KeyboardButton(".NET"),
                                        new KeyboardButton("Front-End / JS")
                                },
                        new KeyboardButton[]
                                {
                                        new KeyboardButton("Android"),
                                        new KeyboardButton("Node.js")
                                },
                        new KeyboardButton[]
                                {
                                        new KeyboardButton("C / C++"),
                                        new KeyboardButton("PHP")
                                },
                        new KeyboardButton[]
                                {
                                        new KeyboardButton("Goaling"),
                                        new KeyboardButton("Python"),
                                },
                        new KeyboardButton[]
                                {
                                        new KeyboardButton("iOS"),
                                        new KeyboardButton("Ruby / Rails"),
                                },
                        new KeyboardButton[]
                                {
                                        new KeyboardButton("Java"),
                                        new KeyboardButton("Call_Scala")

                                },
                        new KeyboardButton[]
                                {
                                        new KeyboardButton("Finish")
                                }
                };


        return new ReplyKeyboardMarkup( arrSpecial );
    }
}
