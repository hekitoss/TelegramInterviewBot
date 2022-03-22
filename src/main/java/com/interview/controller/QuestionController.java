package com.interview.controller;

import com.interview.dao.QuestionDao;
import com.interview.dto.QuestionDto;
import com.interview.exception.NotFoundException;
import com.interview.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping()
    public ResponseEntity<QuestionDao> create(@RequestParam String text, @RequestParam String answer) {
        return new ResponseEntity<>(questionService.save(new QuestionDao(text, answer)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<QuestionDto>> getAll() {
        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<QuestionDao> rate(@PathVariable long id, @RequestParam int rate) throws NotFoundException {
        return new ResponseEntity<>(questionService.evaluateById(id, rate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionDao> delete(@PathVariable long id) throws NotFoundException {
        return new ResponseEntity<>(questionService.deleteById(id), HttpStatus.OK);
    }
}
