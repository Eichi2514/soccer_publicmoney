package org.soccer.publicmoney.controller;

import org.soccer.publicmoney.entity.Users;
import org.soccer.publicmoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @GetMapping("/home/main")
    public String listUsers(Model model) {
        model.addAttribute("userss", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("users", new Users());
        return "user-form";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("users") Users users) {
        userService.saveUser(users);
        return "redirect:/users/list";
    }

    @PostMapping("/toggle")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> toggleUserField(@RequestParam Long userId, @RequestParam String field) {
        boolean isMoneyChanged = false;
        boolean isParticipationChanged = false;

        // 사용자 조회
        Users users = userService.findById(userId);
        if ("isMoney".equals(field)) {
            users.setMoney(!users.isMoney());
            isMoneyChanged = users.isMoney();
        } else if ("isParticipation".equals(field)) {
            users.setParticipation(!users.isParticipation());
            isParticipationChanged = users.isParticipation();
        }

        // 사용자 저장
        userService.saveUser(users);

        Map<String, Boolean> response = new HashMap<>();
        response.put("isMoney", isMoneyChanged);
        response.put("isParticipation", isParticipationChanged);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/month/{year}/{monthNumber}")
    public String listUsersByMonth(@PathVariable int year, @PathVariable int monthNumber, Model model) {
        // 로직 구현
        List<Users> usersByMonth = userService.getUsersByMonth(year, monthNumber);
        model.addAttribute("userss", usersByMonth);
        model.addAttribute("year", year);
        model.addAttribute("month", monthNumber);
        return "user-month-list"; // Thymeleaf 템플릿 이름
    }
}