package org.soccer.publicmoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "org.soccer.publicmoney.entity") // 엔티티 클래스가 위치한 패키지
public class PublicMoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicMoneyApplication.class, args);
    }

}
