package com.rgs.service.filter;

import com.rgs.entity.Player;
import com.rgs.entity.Profession;
import com.rgs.entity.Race;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PlayerFilter {
    public Specification<Player> filterByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public Specification<Player> filterByTitle(String title) {
        return (root, criteriaQuery, criteriaBuilder) ->
                title == null ? null : criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public Specification<Player> filterByRace(Race race) {
        return (root, criteriaQuery, criteriaBuilder) ->
                race == null ? null : criteriaBuilder.equal(root.get("race"), race);
    }

    public Specification<Player> filterByBirthday(Long after, Long before) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (after == null && before == null) {
                return null;
            } else if (after == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), new Date(before));
            } else if (before == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date(after));
            } else {
                return criteriaBuilder.between(root.get("birthday"), new Date(after), new Date(before));
            }
        };
    }

    public Specification<Player> filterByExperience(Integer minExperience, Integer maxExperience) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (minExperience == null && maxExperience == null) {
                return null;
            } else if (minExperience == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("experience"), maxExperience);
            } else if (maxExperience == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), minExperience);
            } else {
                return criteriaBuilder.between(root.get("experience"), minExperience, maxExperience);
            }
        };
    }

    public Specification<Player> filterByLevel(Integer minLevel, Integer maxLevel) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (minLevel == null && maxLevel == null) {
                return null;
            } else if (minLevel == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("level"), maxLevel);
            } else if (maxLevel == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("level"), minLevel);
            } else {
                return criteriaBuilder.between(root.get("level"), minLevel, maxLevel);
            }
        };
    }

    public Specification<Player> filterByBanned(Boolean banned) {
        return (root, criteriaQuery, criteriaBuilder) ->
                banned == null ? null :
                        banned ? criteriaBuilder.isTrue(root.get("banned")) : criteriaBuilder.isFalse(root.get("banned"));
    }

    public Specification<Player> filterByProfession(Profession profession) {
        return (root, criteriaQuery, criteriaBuilder) ->
                profession == null ? null : criteriaBuilder.equal(root.get("profession"), profession);
    }
}
