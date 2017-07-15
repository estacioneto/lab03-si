package br.edu.ufcg.lebflix.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by estacio on 13/07/17.
 */
@Entity
@Table(name = "TB_SERIES")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series implements Serializable {

    private static final long serialVersionUID = 4302522548712194679L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "IMDB_ID", updatable = false, unique = true)
    private String imdbID;

    @Column(name = "RATE")
    private Double rate;

    @Column(name = "SEASON")
    private String season;

    @Column(name = "EPISODE")
    private String episode;

    @NotNull
    @Column(name = "ON_PROFILE")
    private Boolean onProfile;

    @NotNull
    @Column(name = "ON_WATCHLIST")
    private Boolean onWatchlist;

    @NotNull
    @Column(name = "ID_USER")
    private Long idUser;

    public Series() {}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public Boolean getOnProfile() {
        return onProfile;
    }

    public void setOnProfile(Boolean onProfile) {
        this.onProfile = onProfile;
    }

    public Boolean getOnWatchlist() {
        return onWatchlist;
    }

    public void setOnWatchlist(Boolean onWatchlist) {
        this.onWatchlist = onWatchlist;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
