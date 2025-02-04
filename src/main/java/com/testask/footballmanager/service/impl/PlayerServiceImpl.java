package com.testask.footballmanager.service.impl;

import com.testask.footballmanager.exception.IllegalTransferException;
import com.testask.footballmanager.exception.PlayerNotFoundException;
import com.testask.footballmanager.mapper.PlayerMapper;
import com.testask.footballmanager.model.Player;
import com.testask.footballmanager.model.dto.PlayerDTO;
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

    private final PlayerMapper playerMapper;

    @Transactional
    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {

        Player player = playerMapper.toPlayer(playerDTO);

        validatePlayer(player);

        if (repository.findByName(player.getName()).isPresent()) {
            throw new IllegalArgumentException("Player with name '" + player.getName() + "' already exists");
        }

        Player savedPlayer = repository.save(player);

        return playerMapper.toPlayerDTO(savedPlayer);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerMapper.toPlayerDTOList(repository.findAll());
    }

    @Override
    public PlayerDTO getPlayerById(Long id) {

        Player player = repository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException("Player with id '" + id + "' not found"));

        return playerMapper.toPlayerDTO(player);
    }

    @Override
    public PlayerDTO getPlayerByName(String name) {

        Player player = repository.findByName(name).orElseThrow(
                () -> new PlayerNotFoundException("Player with name '" + name + "' not found"));

        return playerMapper.toPlayerDTO(player);
    }

    @Override
    public List<PlayerDTO> getPlayersByTeamId(Long teamId) {
        return playerMapper.toPlayerDTOList(repository.findByTeamId(teamId));
    }

    @Transactional
    @Override
    public PlayerDTO updatePlayer(PlayerDTO playerDTO) {

        Player player = playerMapper.toPlayer(playerDTO);

        validatePlayer(player);

        Player currentPlayer = repository.findById(player.getId()).orElseThrow(() -> new PlayerNotFoundException("Player with id '" + player.getId() + "' not found"));

        if (!player.getTeam().getName().equals(currentPlayer.getTeam().getName())) {
            throw new IllegalTransferException("If you want to change team of some player, make a transfer");
        }

        Player updatedPlayer = repository.save(player);

        return playerMapper.toPlayerDTO(updatedPlayer);
    }

    @Transactional
    @Override
    public PlayerDTO updatePlayer(PlayerDTO playerDTO, boolean isTransfer) {

        if (!isTransfer)
            return updatePlayer(playerDTO);

        Player player = playerMapper.toPlayer(playerDTO);

        validatePlayer(player);

        Player updatedPlayer = repository.save(player);

        return playerMapper.toPlayerDTO(updatedPlayer);
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
