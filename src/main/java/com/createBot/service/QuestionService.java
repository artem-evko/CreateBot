package com.createBot.service;


import com.createBot.repository.QuestionRepositoryCustom;
import com.createBot.repository.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepositoryCustom questionRepositoryCustom;
    private final Map<Long, List<Question>> userQuestionCache = new ConcurrentHashMap<>();

    public List<Question> getQuestionsForUser(Long chatId, Long testId) {
        return userQuestionCache.computeIfAbsent(chatId, id -> generateRandomQuestionList(testId));
    }
    public List<Question> getQuestionsForUser(Long chatId) {
        List<Question> questions = userQuestionCache.get(chatId);
        if (questions == null) {
            throw new IllegalStateException("Список вопросов для данного пользователя еще не создан.");
        }
        return questions;
    }
    private List<Question> generateRandomQuestionList(Long testId) {
        List<Question> allQuestions = questionRepositoryCustom.findQuestionsByTestId(testId);
        Collections.shuffle(allQuestions); // Перемешиваем вопросы
        return allQuestions.stream().limit(7).collect(Collectors.toList()); // Верните нужное количество вопросов, если нужно ограничение
    }


    public void clearCache(Long chatId) {
        userQuestionCache.remove(chatId);
    }
}
