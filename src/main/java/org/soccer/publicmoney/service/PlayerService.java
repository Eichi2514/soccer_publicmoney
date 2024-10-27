package org.soccer.publicmoney.service;

import org.soccer.publicmoney.entity.Player; // Users에서 Player로 변경
import org.soccer.publicmoney.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService { // UserService에서 PlayerService로 변경

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() { // Users에서 Player로 변경
        return playerRepository.findAll();
    }

    public void savePlayer(Player player) { // Users에서 Player로 변경
        playerRepository.save(player);
    }

    public Player findById(Long playerId) { // Users에서 Player로 변경
        Optional<Player> optionalPlayer = playerRepository.findById(playerId); // Users에서 Player로 변경
        return optionalPlayer.orElse(null); // 사용자가 없으면 null 반환
    }

    public List<Player> findByCdarYearAndCdarMonth(int year, int month) { // Users에서 Player로 변경
        return playerRepository.findByCdar_YearAndCdar_Month(year, month);
    }
}
