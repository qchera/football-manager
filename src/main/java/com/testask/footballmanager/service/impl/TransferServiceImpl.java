package com.testask.footballmanager.service.impl;

import com.testask.footballmanager.exception.TeamNotSolventException;
import com.testask.footballmanager.model.Player;
import com.testask.footballmanager.model.Team;
import com.testask.footballmanager.service.PlayerService;
import com.testask.footballmanager.service.TeamService;
import com.testask.footballmanager.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final PlayerService playerService;

    private final TeamService teamService;

    @Override
    public boolean transferPlayer(Long playerId, Long teamId) {

        Player player = playerService.getPlayerById(playerId);
        Team team = teamService.getTeamById(teamId);

        return makeTransfer(player, team);
    }

    @Override
    public boolean transferPlayer(String playerName, String teamName) {

        Player player = playerService.getPlayerByName(playerName);
        Team team = teamService.getTeamByName(teamName);

        return makeTransfer(player, team);
    }

    private boolean makeTransfer(Player player, Team newTeam) {

        Double transferSum = calculateTransferSum(player);

        if (!checkSolvency(transferSum, newTeam)) {
            throw new TeamNotSolventException("Team '" + newTeam.getName() + "' can't afford to buy player '"
                                                      + player.getName() +  "' for " + transferSum);
        }

        player.getTeam().transferPlayer(player, newTeam, transferSum);

        return player.getTeam().getName().equals(newTeam.getName());
    }

    private Double calculateTransferSum(Player player) {

        Team currentTeam = player.getTeam();

        double playerPrice = (player.getExperience() * 100000.0) / player.getAge();

        return playerPrice * (1.0 + currentTeam.getCommission() / 100.0);
    }

    private boolean checkSolvency(Double transferSum, Team team) {
        return team.getBalance() >= transferSum;
    }
}
