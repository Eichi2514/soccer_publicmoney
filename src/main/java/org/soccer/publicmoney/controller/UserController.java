package org.soccer.publicmoney.controller;

import org.soccer.publicmoney.entity.Player;
import org.soccer.publicmoney.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/usr")
public class UserController {

    @Autowired
    private PlayerService playerService;

    // 사용자 목록을 보여주는 메서드
    @GetMapping("/home/main")
    public String listUsers(Model model) {
        model.addAttribute("players", playerService.getAllPlayers()); // "userss"에서 "players"로 변경
        return "user-list"; // HTML 파일명
    }

    // 사용자 추가 폼을 보여주는 메서드
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("player", new Player()); // "player" 객체를 모델에 추가
        return "user-form"; // 사용자 추가 폼 HTML 파일
    }

    // 사용자를 저장하는 메서드
    @PostMapping("/new")
    public String saveUser(@ModelAttribute("player") Player player) { // "player"로 변경
        playerService.savePlayer(player);
        return "redirect:/usr/home/main"; // 사용자 목록으로 리디렉션
    }

    // 사용자 필드를 토글하는 메서드
    @PostMapping("/toggle")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> toggleUserField(@RequestParam Long userId, @RequestParam String field) {
        Player player = playerService.findById(userId);
        if (player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }

        switch (field) {
            case "money":
                player.setMoney(!player.isMoney());
                break;
            case "participation":
                player.setParticipation(!player.isParticipation());
                break;
            default:
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid field name"));
        }

        playerService.savePlayer(player);

        Map<String, Object> response = new HashMap<>();
        response.put("money", player.isMoney());
        response.put("participation", player.isParticipation());

        return ResponseEntity.ok(response);
    }

    // 특정 연도와 월에 속한 사용자 목록을 보여주는 메서드
    @GetMapping("/Cdar/{year}/{month}")
    public String listUsersByCdar(@PathVariable int year, @PathVariable int month, Model model) {
        List<Player> playersByCdar = playerService.findByCdarYearAndCdarMonth(year, month);
        model.addAttribute("players", playersByCdar); // "userss"에서 "players"로 변경
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        return "user-Cdar-list"; // Cdar 사용자 목록 HTML 템플릿 파일명
    }
}
