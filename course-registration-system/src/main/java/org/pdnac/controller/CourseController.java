package org.pdnac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CourseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Show all courses
    @GetMapping("/courses")
    public String listCourses(Model model) {
        String sql = "SELECT * FROM courses";
        model.addAttribute("courses", jdbcTemplate.queryForList(sql));
        return "courses";
    }

    // Register a student to a course
    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) {
            return "redirect:/login";
        }

        // Fetch student ID from email
        String studentQuery = "SELECT student_id FROM students WHERE email = ?";
        Integer studentId = jdbcTemplate.queryForObject(studentQuery, Integer.class, email);

        // Insert into registrations
        String insertSql = "INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql, studentId, courseId, LocalDate.now());

        return "redirect:/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
}