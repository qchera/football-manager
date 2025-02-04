package com.testask.footballmanager.controller;

import com.testask.footballmanager.model.dto.TeamDTO;
import com.testask.footballmanager.service.TeamService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable @Min(value = 1,message = "Team id can't be smaller than 1") Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<TeamDTO> getTeamByName(@RequestParam String name) {
        return ResponseEntity.ok(teamService.getTeamByName(name));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamDTO teamDTO) {
        teamDTO.setId(id);
        return ResponseEntity.ok(teamService.updateTeam(teamDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable @Min(value = 1,message = "Team id can't be smaller than 1") Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
