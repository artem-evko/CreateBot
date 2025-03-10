package com.createBot.repository;

import com.createBot.repository.entity.TgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TgUser,Long> {

    Optional<TgUser> findByTelegramId(Long telegramId);
}
