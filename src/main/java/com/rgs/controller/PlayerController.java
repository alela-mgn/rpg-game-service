package com.rgs.controller;

import com.rgs.dto.PlayerSearchDto;
import com.rgs.entity.Player;
import com.rgs.service.PlayerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers(@ModelAttribute PlayerSearchDto searchDto) {
        return playerService.getAllPlayers(searchDto);
    }

    @GetMapping("/count")
    public Integer getCountPlayer(@ModelAttribute PlayerSearchDto searchDto) {
        return playerService.getPlayersCount(searchDto).size();
    }

    @GetMapping("/{id}")
    public Player getByIdPlayer(@PathVariable("id") Long playerId) {
        return playerService.getById(playerId);
    }

    @DeleteMapping("/{id}")
    public void deleteByIdPlayer(@PathVariable("id") Long id) {
        playerService.delete(id);
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.create(player);
    }

    @PostMapping("/{id}")
    public Player updatePlayer(@PathVariable("id") Long id, @RequestBody Player updatePlayer) {
        return playerService.update(id, updatePlayer);
    }
}
