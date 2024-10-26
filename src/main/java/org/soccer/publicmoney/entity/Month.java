package org.soccer.publicmoney.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID를 Long으로 변경

    private int year; // 연도 추가
    private int monthNumber; // 월 번호 추가

    @ManyToOne(fetch = FetchType.LAZY)
    private Month month;

    @OneToMany(mappedBy = "month", fetch = FetchType.LAZY)
    private List<Users> users;

    private boolean money;
    private boolean participation;
}
