package org.soccer.publicmoney.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cdar") // 테이블 이름 지정
@Data
public class Cdar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"year\"") // 예약어 문제 해결
    private int year; // 연도
    @Column(name = "\"month\"") // 예약어 문제 해결
    private int month; // 월

    @OneToMany(mappedBy = "cdar", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // LAZY 로딩 및 cascade 추가
    private List<Player> players; // 해당 월에 속한 사용자 목록

    // 기본 생성자
    public Cdar() {}

    // 생성자 추가
    public Cdar(int year, int month) {
        this.year = year;
        this.month = month;
    }
}
