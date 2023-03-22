package com.kgc.chatbot.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kgc.chatbot.jpa.entity.ClientLog;

@Repository
public interface ClientLogRepository extends JpaRepository<ClientLog, Long>{

}
