package com.rgs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "player")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "race")
    @Enumerated(EnumType.STRING)
    private Race race;

    @Column(name = "profession")
    @Enumerated(EnumType.STRING)
    private Profession profession;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "banned")
    private Boolean banned;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "level")
    private Integer level;

    @Column(name = "untilNextLevel")
    private Integer untilNextLevel;

    public Player() {

    }

    public Player(Long id, String name, String title, Race race, Profession profession, Date birthday, Boolean banned, Integer experience, Integer level, Integer untilNextLevel) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public void calculateLevel(Integer experience) {
        int currentLevel = ((int) Math.sqrt(2500 + 200 * experience) - 50) / 100;
        int expUntil = 50 * (currentLevel + 1) * (currentLevel + 2) - experience;
        this.level = currentLevel;
        this.untilNextLevel = expUntil;
    }

    public void ignoringIdLevelUntil() {
        this.id = null;
        this.level = null;
        this.untilNextLevel = null;
    }

    public void updateChecked(Player updatePlayer) {
        updatePlayer.id = null;
        updatePlayer.level = null;
        updatePlayer.untilNextLevel = null;
        if (updatePlayer.name != null) {
            this.name = updatePlayer.name;
        }
        if (updatePlayer.title != null) {
            this.title = updatePlayer.title;
        }
        if (updatePlayer.race != null) {
            this.race = updatePlayer.race;
        }
        if (updatePlayer.profession != null) {
            this.profession = updatePlayer.profession;
        }
        if (updatePlayer.birthday != null) {
            this.birthday = updatePlayer.birthday;
        }
        if (updatePlayer.banned != null) {
            this.banned = updatePlayer.banned;
        }
        if (updatePlayer.experience != null) {
            this.experience = updatePlayer.experience;
            calculateLevel(this.experience);
        }
    }

    public boolean isCheckedBody() {
        return this.name != null
                && this.title != null
                && this.race != null
                && this.profession != null
                && this.birthday != null
                && this.experience != null;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", birthday=" + birthday +
                ", banned=" + banned +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                '}';
    }
}
