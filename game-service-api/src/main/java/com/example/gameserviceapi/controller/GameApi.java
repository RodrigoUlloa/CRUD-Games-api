package com.example.gameserviceapi.controller;

import com.example.gameserviceapi.entities.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/games")
public interface GameApi {
    @PostMapping
    ResponseEntity<Game> SaveGame(@RequestHeader("userIdRequest") String userId, @RequestBody Game game) ;
    @GetMapping
    ResponseEntity<Game> GetGame(@RequestParam long id) ;
    @PutMapping
    ResponseEntity<Game> UpdateGame(@RequestHeader("userIdRequest") String userId, @RequestBody Game game) ;
    @DeleteMapping
    ResponseEntity<Void> DeleteGame(@RequestParam long id) ;
}
