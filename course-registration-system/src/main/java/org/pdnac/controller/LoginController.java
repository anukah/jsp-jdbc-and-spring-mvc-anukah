package org.pdnac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        String sql = "SELECT COUNT(*) FROM students WHERE email=? AND password=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email, password);

        if (count == 1) {
            session.setAttribute("userEmail", email);
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }


    @GetMapping("/courses")
    public String showCourses(Model model) {
        String sql = "SELECT * FROM courses";
        model.addAttribute("courses", jdbcTemplate.queryForList(sql));
        return "courses";
    }
}