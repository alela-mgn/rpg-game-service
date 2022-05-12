package com.game.service;

import com.game.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> getAll();

    List<Player> getCountPlayers();

    Player getById(Long id);

    void delete(Long id);

    void create(Player player);

    void update(Player player);

    Boolean checkedCreateAndUpdatePlayer(Player player);

    Boolean checkId(Long id);

}
