package com.example.gameserviceapi.services;

import com.example.gameserviceapi.entities.Game;
import com.example.gameserviceapi.repositories.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game saveGame(Game gameRequest) {
        Game gameCreatedInDatabase = this.gameRepository.save(gameRequest);
        return gameCreatedInDatabase;
    }

    public Game getGames(Long id) {
        Game listGames = this.gameRepository.findById(id).orElseThrow();
        return listGames;
    }

    public Game deleteGame(Long id) {
        gameRepository.deleteById(id);
        return null;
    }
}
