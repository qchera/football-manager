package com.testask.footballmanager.service;

import com.testask.footballmanager.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDTO);

    List<PlayerDTO> getAllPlayers();

    PlayerDTO getPlayerById(Long id);

    PlayerDTO getPlayerByName(String name);

    List<PlayerDTO> getPlayersByTeamId(Long teamId);

    PlayerDTO updatePlayer(PlayerDTO playerDTO);

    PlayerDTO updatePlayer(PlayerDTO playerDTO, boolean isTransfer);

    void deletePlayerById(Long id);
}
