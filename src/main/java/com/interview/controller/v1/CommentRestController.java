package com.interview.controller.v1;

import com.interview.dto.CommentDto;
import com.interview.exception.NotFoundException;
import com.interview.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentDto> like(@PathVariable Long id) throws NotFoundException {
        return null;
    }

    @PostMapping("/{questionId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentDto> commentAdd(@PathVariable Long questionId, @RequestParam String text, Model model) {
        return new ResponseEntity<>(commentService.save(questionId, text), HttpStatus.OK);
    }
}
