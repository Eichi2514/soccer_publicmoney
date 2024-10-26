package org.soccer.publicmoney.repository;

import org.soccer.publicmoney.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByMonth_YearAndMonth_MonthNumber(int year, int monthNumber); // 연도와 월로 사용자 찾기
}