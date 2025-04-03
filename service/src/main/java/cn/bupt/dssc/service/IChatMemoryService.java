package cn.bupt.dssc.service;

import cn.bupt.dssc.domain.po.DatabaseChatMemory;

import java.util.List;

public interface IChatMemoryService {
    DatabaseChatMemory save(DatabaseChatMemory chatMemory);
    DatabaseChatMemory findById(String id);
    DatabaseChatMemory update(DatabaseChatMemory chatMemory);
    void deleteById(String id);
    List<DatabaseChatMemory> findByUserId(String userId);
    List<DatabaseChatMemory> findAll();
}
