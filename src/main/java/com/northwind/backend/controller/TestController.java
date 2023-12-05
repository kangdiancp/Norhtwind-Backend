package com.northwind.backend.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "*", maxAge = 3600)
// for Angular Client (withCredentials)
// @CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @PostMapping("/all")
  public String allAccessPost() {
    return "Public Content Post.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('FA') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/fa")
  @PreAuthorize("hasRole('FA')")
  public String moderatorAccess() {
    return "FA Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Async
  public CompletableFuture<String> adminAccess() {
    return CompletableFuture.completedFuture("Admin Board COOL.");
  }
}
