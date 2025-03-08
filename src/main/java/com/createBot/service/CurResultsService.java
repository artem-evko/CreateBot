package com.createBot.service;


import com.createBot.repository.CurResultsRepository;
import com.createBot.repository.entity.CurResults;
import com.createBot.repository.entity.TgUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurResultsService {
    private final CurResultsRepository curResultsRepository;

    public void saveOrUpdateResult(TgUser user, Long questionId, Long answerId, boolean isCorrect) {

        CurResults currentResult = curResultsRepository.findByUserAndQuestionId(user, questionId)
                .orElse(new CurResults());

        currentResult.setUser(user);
        currentResult.setQuestionId(questionId);
        currentResult.setAnswerId(answerId);
        currentResult.setCorrect(isCorrect);

        curResultsRepository.save(currentResult);
    }
    public List<Long> findAnswerIdsByUserId(Long userId){
        return curResultsRepository.findAnswerIdsByUserId(userId);
    }

    @Transactional
    public void deleteByUser(TgUser tgUser){
        curResultsRepository.deleteByUser(tgUser);
    }
}
