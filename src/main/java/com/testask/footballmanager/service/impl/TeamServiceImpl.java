package com.testask.footballmanager.service.impl;

import com.testask.footballmanager.exception.TeamNotFoundException;
import com.testask.footballmanager.model.Team;
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

    @Transactional
    @Override
    public void createTeam(Team team) {

        validateTeam(team);

        if (repository.findByName(team.getName()).isPresent()) {
            throw new IllegalArgumentException("Team with name '" + team.getName() + "' already exists");
        }

        repository.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return repository.findAll();
    }

    @Override
    public Team getTeamById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new TeamNotFoundException("Team with id '" + id + "' not found")
        );
    }

    @Override
    public Team getTeamByName(String name) {
        return repository.findByName(name).orElseThrow(
                () -> new TeamNotFoundException("Team with name '" + name + "' not found")
        );
    }

    @Transactional
    @Override
    public void updateTeam(Team team) {

        validateTeam(team);

        repository.findById(team.getId()).orElseThrow(
                () -> new TeamNotFoundException("Team with id '" + team.getId() + "' not found"));

        repository.save(team);
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
