package cn.bupt.dssc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "cn.bupt.dssc.mapper")
@SpringBootApplication
public class SPOSApplication {
    public static void main(String[] args) {
        SpringApplication.run(SPOSApplication.class, args);
    }
}
