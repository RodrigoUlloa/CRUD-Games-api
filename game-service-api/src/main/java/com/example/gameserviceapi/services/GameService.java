package com.example.gameserviceapi.services;

import com.example.gameserviceapi.entities.Game;
import com.example.gameserviceapi.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game saveGame(String userId, Game gameRequest) {
        gameRequest.setUserId(Integer.parseInt(userId));
        return Optional.of(gameRequest)
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error saving game"));
    }

    public Game updateGame(Game gameRequest){
        return Optional.of(gameRequest)
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error updating game"));
    }

    public Game getGames(Long id) {
        return Optional.of(id)
                .map(this::getErrorFindingGame)
                .orElseThrow(()-> new RuntimeException("Error finding game "));
    }

    private Game getErrorFindingGame(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Error finding game"));
    }

    public Game deleteGame(Long id) {
        return Optional.of(id)
                .map(this::deleteGame)
                .orElseThrow(() -> new RuntimeException("Error deleting game"));
    }
}
