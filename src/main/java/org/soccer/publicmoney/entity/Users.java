package org.soccer.publicmoney.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 사용자 이름

    @ManyToOne
    @JoinColumn(name = "month_id")
    private Month month; // 해당 사용자의 월 정보

    private boolean money;
    private boolean participation;
}