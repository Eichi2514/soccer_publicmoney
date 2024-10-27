package org.soccer.publicmoney.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 사용자 이름

    @ManyToOne(fetch = FetchType.LAZY) // LAZY 로딩
    @JoinColumn(name = "cdar_id") // Cdar 엔티티와의 관계를 나타냄
    private Cdar cdar; // 해당 사용자의 연도 및 월 정보

    private boolean money; // 공금 납부 여부 (true: 납부, false: 미납)
    private boolean participation; // 참여 여부 (true: 참여, false: 비참여)
}
