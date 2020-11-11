package team13.pulsbes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team13.pulsbes.services.StudentService;


@RestController
@RequestMapping("/API/students")

public class StudentController {

    @Autowired
    StudentService ts;



}
