package org.soccer.publicmoney.repository;

import org.soccer.publicmoney.entity.Player; // Users에서 Player로 변경
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> { // Users에서 PlayerRepository로 변경
    List<Player> findByCdar_YearAndCdar_Month(int year, int month); // 연도와 월로 사용자 찾기
}
