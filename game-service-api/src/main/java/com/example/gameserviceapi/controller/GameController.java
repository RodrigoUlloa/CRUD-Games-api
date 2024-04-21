package com.example.gameserviceapi.controller;

import com.example.gameserviceapi.entities.Game;
import com.example.gameserviceapi.repositories.GameRepository;
import com.example.gameserviceapi.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")

public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Game> SaveGame(@RequestBody Game game) {
        Game gameCreated = this.gameService.saveGame(game);
        return ResponseEntity.ok(gameCreated);
    }
    @GetMapping
    public ResponseEntity<Game> GetGame(@RequestParam long id) {
        Game getList = this.gameService.getGames(id);
        return  ResponseEntity.ok(getList);
    }

    @PutMapping
    public ResponseEntity<Game> UpdateGame(@RequestBody Game game) {
        Game updateGame = this.gameService.saveGame(game);
        return ResponseEntity.ok(updateGame);
    }
    @DeleteMapping
    public ResponseEntity<Game> DeleteGame(@RequestParam long id) {
        Game deleteGame = this.gameService.deleteGame(id);
        return ResponseEntity.ok(deleteGame);
    }
}
