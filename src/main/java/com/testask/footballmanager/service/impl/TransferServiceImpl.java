package com.testask.footballmanager.service.impl;

import com.testask.footballmanager.exception.IllegalTransferException;
import com.testask.footballmanager.exception.TeamNotSolventException;
import com.testask.footballmanager.mapper.PlayerMapper;
import com.testask.footballmanager.mapper.TeamMapper;
import com.testask.footballmanager.model.Player;
import com.testask.footballmanager.model.Team;
import com.testask.footballmanager.model.dto.PlayerDTO;
import com.testask.footballmanager.model.dto.TeamDTO;
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

    private final PlayerMapper playerMapper;

    private final TeamMapper teamMapper;

    @Override
    public boolean transferPlayer(Long playerId, Long teamId) {

        PlayerDTO playerDTO = playerService.getPlayerById(playerId);
        TeamDTO teamDTO = teamService.getTeamById(teamId);

        if (playerDTO.getTeam().equals(teamDTO.getName())) {
            throw new IllegalTransferException("Can't transfer player '" + playerDTO.getName() +
                                                       "' to team '" + teamDTO.getName() + "'. He is already in this team");
        }

        return makeTransfer(playerMapper.toPlayer(playerDTO), teamMapper.toTeam(teamDTO));
    }

    @Override
    public boolean transferPlayer(String playerName, String teamName) {

        PlayerDTO playerDTO = playerService.getPlayerByName(playerName);
        TeamDTO teamDTO = teamService.getTeamByName(teamName);

        if (playerDTO.getTeam().equals(teamDTO.getName())) {
            throw new IllegalTransferException("Can't transfer player '" + playerName +
                                                       "' to team '" + teamName + "'. He is already in this team");
        }

        return makeTransfer(playerMapper.toPlayer(playerDTO), teamMapper.toTeam(teamDTO));
    }

    private boolean makeTransfer(Player player, Team newTeam) {

        Double transferSum = calculateTransferSum(player);

        if (!checkSolvency(transferSum, newTeam)) {
            throw new TeamNotSolventException("Team '" + newTeam.getName() + "' can't afford to buy player '"
                                                      + player.getName() +  "' for " + transferSum);
        }

        Team oldTeam = player.getTeam();

        oldTeam.transferPlayer(player, newTeam, transferSum);

        PlayerDTO newPlayer = playerService.updatePlayer(playerMapper.toPlayerDTO(player), true);
        teamService.updateTeam(teamMapper.toTeamDTO(newTeam));

        return newPlayer.getTeam().equals(newTeam.getName());
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
