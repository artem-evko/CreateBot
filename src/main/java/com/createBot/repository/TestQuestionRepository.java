package com.createBot.repository;


import com.createBot.repository.entity.Question;
import com.createBot.repository.entity.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion,Long> {
    TestQuestion getTestQuestionByQuestion(Question question);
}
