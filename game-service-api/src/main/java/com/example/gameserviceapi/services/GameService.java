package com.example.gameserviceapi.services;

import com.example.gameserviceapi.commons.constants.TopicConstants;
import com.example.gameserviceapi.entities.Game;
import com.example.gameserviceapi.repositories.GameRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final StreamBridge streamBridge;

    public GameService(GameRepository gameRepository, StreamBridge streamBridge) {
        this.gameRepository = gameRepository;
        this.streamBridge = streamBridge;
    }

    public Game saveGame(String userId, Game gameRequest) {
        gameRequest.setUserId(Integer.parseInt(userId));
        return Optional.of(gameRequest)
                .map(gameRepository::save)
                .map(this::sendGameEvent)
                .orElseThrow(() -> new GameException(HttpStatus.BAD_REQUEST, "Error saving game"));
    }

    private Game sendGameEvent(Game game) {
        Optional.of(game)
                .map(givenGame -> this.streamBridge.send(TopicConstants.GAME_CREATED_TOPIC, game))
                .map(bool -> game);
        return game;
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
