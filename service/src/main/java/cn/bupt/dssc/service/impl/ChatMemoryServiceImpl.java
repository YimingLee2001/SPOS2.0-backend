package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.common.exception.BadRequestException;
import cn.bupt.dssc.domain.po.DatabaseChatMemory;
import cn.bupt.dssc.repository.DatabaseChatMemoryRepository;
import cn.bupt.dssc.service.IChatMemoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMemoryServiceImpl implements IChatMemoryService {

    private final DatabaseChatMemoryRepository chatMemoryRepository;

    @Override
    public DatabaseChatMemory save(DatabaseChatMemory chatMemory) {
        return chatMemoryRepository.save(chatMemory);
    }

    @Override
    public DatabaseChatMemory findById(String id) {
        Optional<DatabaseChatMemory> optionalDatabaseChatMemory = chatMemoryRepository.findById(id);
        if (!optionalDatabaseChatMemory.isPresent()) {
            return null;
        }

        return optionalDatabaseChatMemory.get();
    }

    @Override
    public DatabaseChatMemory update(DatabaseChatMemory chatMemory) {
        return chatMemoryRepository.save(chatMemory);
    }

    @Override
    public void deleteById(String id) {
        chatMemoryRepository.deleteById(id);
    }

    @Override
    public List<DatabaseChatMemory> findByUserId(String userId) {
        return chatMemoryRepository.findByUserId(userId);
    }

    @Override
    public List<DatabaseChatMemory> findAll() {
        return chatMemoryRepository.findAll();
    }
}
