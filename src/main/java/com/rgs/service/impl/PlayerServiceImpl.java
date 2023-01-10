package com.rgs.service.impl;

import com.rgs.dto.PlayerSearchDto;
import com.rgs.entity.Player;
import com.rgs.exception.PlayerNotFoundException;
import com.rgs.repository.PlayerRepository;
import com.rgs.service.PlayerService;
import com.rgs.service.filter.PlayerFilter;
import com.rgs.service.validation.PlayerValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Optional.ofNullable;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerValidator playerValidator;
    private final PlayerFilter playerFilter;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerValidator playerValidator, PlayerFilter playerFilter) {
        this.playerRepository = playerRepository;
        this.playerValidator = playerValidator;
        this.playerFilter = playerFilter;
    }

    @Override
    public List<Player> getAllPlayers(PlayerSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPageNumber(), searchDto.getPageSize(), Sort.by(searchDto.getOrder().getFieldName()));
        return playerRepository.findAll(buildSpecification(searchDto), pageable).getContent();
    }

    @Override
    public List<Player> getPlayersCount(PlayerSearchDto searchDto) {
        return playerRepository.findAll(buildSpecification(searchDto));
    }

    @Override
    public Player getById(Long id) {
        playerValidator.validateId(id);
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Not found player"));
    }

    @Override
    public void delete(Long id) {
        playerValidator.validateId(id);
        playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Not found player"));
        playerRepository.deleteById(id);
    }

    @Override
    public Player create(Player player) {
        playerValidator.validatePlayer(player);
        calculateLevel(player);

        return playerRepository.save(player);
    }

    @Override
    public Player update(Long id, Player player) {
        playerValidator.validateId(id);

        Player foundPlayer = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player Not Found"));

        if (hasNullAttribute(player)) {
            return foundPlayer;
        }

        ofNullable(player.getName())
                .map(x -> toAttribute(x, playerValidator::validateName)).ifPresent(foundPlayer::setName);
        ofNullable(player.getBirthday())
                .map(x -> toAttribute(x, playerValidator::validateBirthday)).ifPresent(foundPlayer::setBirthday);
        ofNullable(player.getExperience())
                .map(x -> toAttribute(x, playerValidator::validateExperience)).ifPresent(foundPlayer::setExperience);
        ofNullable(player.getTitle())
                .map(x -> toAttribute(x, playerValidator::validateTitle)).ifPresent(foundPlayer::setTitle);
        ofNullable(player.getProfession()).ifPresent(foundPlayer::setProfession);
        ofNullable(player.getRace()).ifPresent(foundPlayer::setRace);
        ofNullable(player.getBanned()).ifPresent(foundPlayer::setBanned);

        calculateLevel(foundPlayer);

        return playerRepository.save(foundPlayer);
    }

    private <T> T toAttribute(T attribute, Consumer<T> validationSupplier) {
        validationSupplier.accept(attribute);
        return attribute;
    }

    private Specification<Player> buildSpecification(PlayerSearchDto searchDto) {
        return Specification
                .where(playerFilter.filterByName(searchDto.getName()))
                .and(playerFilter.filterByTitle(searchDto.getTitle()))
                .and(playerFilter.filterByRace(searchDto.getRace()))
                .and(playerFilter.filterByProfession(searchDto.getProfession()))
                .and(playerFilter.filterByBirthday(searchDto.getAfter(), searchDto.getBefore()))
                .and(playerFilter.filterByBirthday(searchDto.getAfter(), searchDto.getBefore()))
                .and(playerFilter.filterByBanned(searchDto.getBanned()))
                .and(playerFilter.filterByExperience(searchDto.getMinExperience(), searchDto.getMaxExperience()))
                .and(playerFilter.filterByLevel(searchDto.getMinLevel(), searchDto.getMaxLevel()));
    }

    private void calculateLevel(Player player) {
        int currentLevel = ((int) Math.sqrt(2500 + 200 * player.getExperience()) - 50) / 100;
        int expUntil = 50 * (currentLevel + 1) * (currentLevel + 2) - player.getExperience();
        player.setLevel(currentLevel);
        player.setUntilNextLevel(expUntil);
    }

    private boolean hasNullAttribute(Player player) {
        return player.getName() == null
                && player.getTitle() == null
                && player.getRace() == null
                && player.getProfession() == null
                && player.getBirthday() == null
                && player.getExperience() == null;
    }
}
