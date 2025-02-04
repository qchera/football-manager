package com.testask.footballmanager.controller;

import com.testask.footballmanager.model.dto.PlayerDTO;
import com.testask.footballmanager.service.PlayerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(playerDTO));
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable @Min(value = 1,message = "Player id can't be smaller than 1") Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @GetMapping("/team-id")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeamId(@RequestParam @Min(value = 1,message = "Team id can't be smaller than 1") Long teamId) {
        return ResponseEntity.ok(playerService.getPlayersByTeamId(teamId));
    }

    @GetMapping("/name")
    public ResponseEntity<PlayerDTO> getPlayerByName(@RequestParam String name) {
        return ResponseEntity.ok(playerService.getPlayerByName(name));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerDTO playerDTO) {
        playerDTO.setId(id);
        return ResponseEntity.ok(playerService.updatePlayer(playerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable @Min(value = 1, message = "Player id can't be smaller than 1") Long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }
}
