package cn.bupt.dssc.repository;

import cn.bupt.dssc.domain.po.DatabaseChatMemory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DatabaseChatMemoryRepository extends MongoRepository<DatabaseChatMemory, String> {

    List<DatabaseChatMemory> findByUserId(String userId);
}
