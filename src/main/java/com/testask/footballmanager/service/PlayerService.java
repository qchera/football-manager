package com.testask.footballmanager.service;

import com.testask.footballmanager.model.Player;

import java.util.List;

public interface PlayerService {

    void createPlayer(Player player);

    List<Player> getAllPlayers();

    Player getPlayerById(Long id);

    Player getPlayerByName(String name);

    List<Player> getPlayersByTeamId(Long teamId);

    void updatePlayer(Player player);

    void deletePlayerById(Long id);
}
