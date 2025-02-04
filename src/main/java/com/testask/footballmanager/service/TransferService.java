package com.testask.footballmanager.service;

public interface TransferService {

    boolean transferPlayer(Long playerId, Long teamId);

    boolean transferPlayer(String playerName, String teamName);
}
