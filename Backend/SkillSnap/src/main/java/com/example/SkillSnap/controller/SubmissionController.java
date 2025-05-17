package com.example.SkillSnap.controller;

import com.example.SkillSnap.model.Submission;
import com.example.SkillSnap.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@CrossOrigin(origins = "*")
public class SubmissionController {
    
    @Autowired
    private SubmissionService submissionService;
    
    @PostMapping("/submit/{problemId}")
    public ResponseEntity<Submission> submitSolution(
            @PathVariable Long problemId,
            @RequestParam String language,
            @RequestBody String sourceCode) {
        try {
            Submission submission = submissionService.submitSolution(problemId, sourceCode, language);
            return ResponseEntity.ok(submission);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/problem/{problemId}")
    public List<Submission> getSubmissionsByProblem(@PathVariable Long problemId) {
        return submissionService.getSubmissionsByProblem(problemId);
    }
    
    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }
}