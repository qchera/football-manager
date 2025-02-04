package com.testask.footballmanager.service.impl;

import com.testask.footballmanager.exception.TeamNotFoundException;
import com.testask.footballmanager.mapper.TeamMapper;
import com.testask.footballmanager.model.Team;
import com.testask.footballmanager.model.dto.TeamDTO;
import com.testask.footballmanager.repository.TeamRepository;
import com.testask.footballmanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;

    private final TeamMapper teamMapper;

    @Transactional
    @Override
    public TeamDTO createTeam(TeamDTO teamDTO) {

        Team team = teamMapper.toTeam(teamDTO);

        validateTeam(team);

        if (repository.findByName(team.getName()).isPresent()) {
            throw new IllegalArgumentException("Team with name '" + team.getName() + "' already exists");
        }

        // set proper team to each player to not throw any exception
        team.getPlayers().forEach(player -> player.setTeam(team));

        Team savedTeam = repository.save(team);

        return teamMapper.toTeamDTO(savedTeam);
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return teamMapper.toTeamDTOList(repository.findAll());
    }

    @Override
    public TeamDTO getTeamById(Long id) {

        Team team = repository.findById(id).orElseThrow(
                () -> new TeamNotFoundException("Team with id '" + id + "' not found"));

        return teamMapper.toTeamDTO(team);
    }

    @Override
    public TeamDTO getTeamByName(String name) {

        Team team = repository.findByName(name).orElseThrow(
                () -> new TeamNotFoundException("Team with name '" + name + "' not found"));

        return teamMapper.toTeamDTO(team);
    }

    @Transactional
    @Override
    public TeamDTO updateTeam(TeamDTO teamDTO) {

        // crude way to ignore players changes
        // alternative is just to set this club's name to each player, but then this method will update not only team but players as well !SRP
        teamDTO.setPlayers(null);

        Team team = teamMapper.toTeam(teamDTO);

        validateTeam(team);

        repository.findById(team.getId()).orElseThrow(
                () -> new TeamNotFoundException("Team with id '" + team.getId() + "' not found"));

        Team updatedTeam = repository.save(team);

        return teamMapper.toTeamDTO(updatedTeam);
    }

    @Transactional
    @Override
    public void deleteTeam(Long id) {
        repository.findById(id).orElseThrow(
                () -> new TeamNotFoundException("Team with id '" + id + "' not found"));

        repository.deleteById(id);
    }

    private void validateTeam(Team team) {

        if (team.getCommission() < 0 || team.getCommission() > 10) {
            throw new IllegalArgumentException("Commission can't be negative or bigger than 10%");
        }
    }
}
