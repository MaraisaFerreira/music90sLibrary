package com.study.mf.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "musics")
public class Music implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String artist;
    @Column(nullable = false)
    private Integer year;

    public Music(){}

    public Music(Long id, String name, String artist, Integer year){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.year = year;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public Integer getYear(){
        return year;
    }

    public void setYear(Integer year){
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Music music)) return false;
        return Objects.equals(getId(), music.getId()) && Objects.equals(getName(), music.getName()) && Objects.equals(getArtist(), music.getArtist()) && Objects.equals(getYear(), music.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getArtist(), getYear());
    }

    @Override
    public String toString() {
        return "Music { " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", year=" + year +
                " }";
    }
}
