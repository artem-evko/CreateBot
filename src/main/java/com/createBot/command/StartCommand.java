package com.createBot.command;


import com.createBot.keyboard.StartKeyboard;
import com.createBot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.createBot.command.CommandName.START;


@Component
@RequiredArgsConstructor
public class StartCommand extends AbstractCommand{
    private final UserService userService;
    public final static String START_MESSAGE="Привет. Я JavaTestBot. Я помогу тебе изучить Java. " +
            " Напиши команду /help, чтобы узнать, что я умею.";

    @Override
    public SendMessage buildResponse(Update update) {
        SendMessage sendMessage = new SendMessage();
        // Получение chatId
        Long chatId;
        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else {
            throw new IllegalArgumentException("Update does not contain a valid message or callback query.");
        }

        userService.getOrCreateUser(chatId, update.getMessage().getFrom().getUserName());

        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(START_MESSAGE);
        InlineKeyboardMarkup markupInline = StartKeyboard.GetStartKeyboard();
        sendMessage.setReplyMarkup(markupInline);

        return sendMessage;
    }

    @Override
    public String getCommandIdentifier() {
        return START.getCommandName();
    }
}
