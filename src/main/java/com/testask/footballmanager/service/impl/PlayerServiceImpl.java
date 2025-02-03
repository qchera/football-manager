package com.testask.footballmanager.service.impl;

import com.testask.footballmanager.exception.PlayerNotFoundException;
import com.testask.footballmanager.model.Player;
import com.testask.footballmanager.repository.PlayerRepository;
import com.testask.footballmanager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;

    @Transactional
    @Override
    public void createPlayer(Player player) {

        validatePlayer(player);

        if (repository.findByName(player.getName()).isPresent()) {
            throw new IllegalArgumentException("Player with name '" + player.getName() + "' already exists");
        }
        repository.save(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    @Override
    public Player getPlayerById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException("Player with id '" + id + "' not found"));
    }

    @Override
    public Player getPlayerByName(String name) {
        return repository.findByName(name).orElseThrow(
                () -> new PlayerNotFoundException("Player with name '" + name + "' not found"));
    }

    @Override
    public List<Player> getPlayersByTeamId(Long teamId) {
        return repository.findByTeamId(teamId);
    }

    @Transactional
    @Override
    public void updatePlayer(Player player) {

        validatePlayer(player);

        repository.findById(player.getId()).orElseThrow(() -> new PlayerNotFoundException("Player with id '" + player.getId() + "' not found"));

        repository.save(player);
    }

    @Transactional
    @Override
    public void deletePlayerById(Long id) {
        repository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player with id '" + id + "' not found"));

        repository.deleteById(id);
    }

    private void validatePlayer(Player player) {

        if (player.getAge() > 70 || player.getAge() < 16) {
            throw new IllegalArgumentException("Player can't be younger than 16 or older than 70 years old");
        }
        if (player.getExperience() < 0) {
            throw new IllegalArgumentException("Player's experience can't be negative");
        }
    }
}
