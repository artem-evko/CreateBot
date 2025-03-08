package com.createBot.service;


import com.createBot.repository.TestResultRepository;
import com.createBot.repository.entity.Test;
import com.createBot.repository.entity.TestResult;
import com.createBot.repository.entity.TgUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestResultService {
    private final TestResultRepository testResultRepository;
    public TestResult saveTestResult(TgUser user, Test test, int score) {
        TestResult result = new TestResult();
        result.setUser(user);
        result.setTest(test);
        result.setScore(score);
        result.setCompletedAt(LocalDateTime.now());
        return testResultRepository.save(result);

    }
    public List<TestResult> getResultsByUser(TgUser user) {
        return testResultRepository.findByUser(user);
    }
}
