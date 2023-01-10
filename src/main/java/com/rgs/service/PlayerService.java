package com.rgs.service;

import com.rgs.dto.PlayerSearchDto;
import com.rgs.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers(PlayerSearchDto searchDto);

    List<Player> getPlayersCount(PlayerSearchDto searchDto);

    Player getById(Long id);

    void delete(Long id);

    Player create(Player player);

    Player update(Long id, Player player);

}
