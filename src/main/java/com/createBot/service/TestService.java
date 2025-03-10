package com.createBot.service;


import com.createBot.repository.TestRepository;
import com.createBot.repository.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;
//    private final QuestionRepository questionRepository;
//    public List<Question> getQuestionsByTestId(int testId) {
//        // Предполагается, что связь test и question через промежуточную таблицу настроена
//        return questionRepository.findQuestionsByTestId(testId);
//    }
//
    public List<Test> getTests() {
        return testRepository.findAll();
    }



}
