package com.createBot.keyboard;


import com.createBot.repository.entity.Test;
import com.createBot.service.TestService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


public class TestSelectionKeyboard {

    public static InlineKeyboardMarkup createTestSelectionKeyboard(TestService testService){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<Test> tests=testService.getTests();
        for (Test test : tests) {
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(test.getName()); // Предполагается, что у теста есть метод getName()
            button.setCallbackData("/test " + test.getId()); // Предполагается, что у теста есть метод getId()
            rowInline.add(button);
            rowsInline.add(rowInline);
        }

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
