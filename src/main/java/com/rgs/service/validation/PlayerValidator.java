package com.rgs.service.validation;

import com.rgs.entity.Player;
import com.rgs.exception.PlayerValidationException;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class PlayerValidator {

    public void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new PlayerValidationException("Некорректный id");
        }
    }

    public void validateName(String name) {
        if (name == null || name.length() > 12 || name.length() == 0) {
            throw new PlayerValidationException("Имя должно находится в диапазоне от 1 до 12 символов");
        }
    }

    public void validateTitle(String title) {
        if (title == null || title.length() > 30) {
            throw new PlayerValidationException("Название должно находиться в диапазоне от 1 до 30 символов");
        }
    }

    public void validateExperience(Integer experience) {
        if (experience == null || experience > 10_000_000 || experience <= 0) {
            throw new PlayerValidationException("Опыт должен находиться в диапазоне от 0 до 10_000_000 очков");
        }
    }

    public void validateBirthday(Date date) {
        if (date.before(new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime())
                || date.after(new GregorianCalendar(3000, Calendar.DECEMBER, 31).getTime())) {
            throw new PlayerValidationException("Дата должна находиться между временным промежутком 2000 и 3000 годом");
        }
    }

    public void validatePlayer(Player player) {
        validateName(player.getName());
        validateExperience(player.getExperience());
        validateTitle(player.getTitle());
        validateBirthday(player.getBirthday());
    }
}