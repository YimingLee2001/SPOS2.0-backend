package cn.bupt.dssc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@MapperScan(basePackages = "cn.bupt.dssc.mapper")
@EnableMongoRepositories(basePackages = "cn.bupt.dssc.repository")
public class SPOSApplication {
    public static void main(String[] args) {
        SpringApplication.run(SPOSApplication.class, args);
    }
}
