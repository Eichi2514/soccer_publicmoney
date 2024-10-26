package org.soccer.publicmoney.service;

import org.soccer.publicmoney.entity.Users;
import org.soccer.publicmoney.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public void saveUser(Users user) {
        usersRepository.save(user);
    }

    public Users findById(Long userId) {
        Optional<Users> optionalUser = usersRepository.findById(userId);
        return optionalUser.orElse(null); // 사용자가 없으면 null 반환
    }

    public List<Users> getUsersByMonth(int year, int monthNumber) {
        return usersRepository.findByMonth_YearAndMonth_MonthNumber(year, monthNumber); // 연도와 월로 사용자 조회
    }
}