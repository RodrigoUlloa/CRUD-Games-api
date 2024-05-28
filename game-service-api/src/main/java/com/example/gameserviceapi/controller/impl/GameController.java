package com.example.gameserviceapi.controller.impl;

import com.example.gameserviceapi.controller.GameApi;
import com.example.gameserviceapi.entities.Game;
import com.example.gameserviceapi.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class GameController implements GameApi {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Game> SaveGame(@RequestHeader("userIdRequest") String userId, @RequestBody Game game) {
        Game gameCreated = this.gameService.saveGame(userId, game);
        return ResponseEntity.ok(gameCreated);
    }
    @GetMapping
    public ResponseEntity<Game> GetGame(@RequestParam long id) {
        Game getList = this.gameService.getGames(id);
        return  ResponseEntity.ok(getList);
    }
    @PutMapping
    public ResponseEntity<Game> UpdateGame(@RequestHeader("userIdRequest") String userId, @RequestBody Game game) {
        Game gameUpdated = this.gameService.saveGame(userId, game);
        return ResponseEntity.ok(gameUpdated);
    }
    @DeleteMapping
    public ResponseEntity<Void> DeleteGame(@RequestParam long id) {
        this.gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
