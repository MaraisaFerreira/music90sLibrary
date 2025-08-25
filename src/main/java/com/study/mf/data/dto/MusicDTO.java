package com.study.mf.data.dto;

import java.util.Objects;

public class MusicDTO {

    private Long id;
    private String name;
    private String artist;
    private Integer year;

    public MusicDTO(){}

    public MusicDTO(Long id, String name, String artist, Integer year){
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
        if (!(o instanceof MusicDTO musicDTO)) return false;
        return Objects.equals(getId(), musicDTO.getId()) && Objects.equals(getName(), musicDTO.getName()) && Objects.equals(getArtist(), musicDTO.getArtist()) && Objects.equals(getYear(), musicDTO.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getArtist(), getYear());
    }

    @Override
    public String toString() {
        return "MusicDTO { " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", year=" + year +
                " }";
    }
}
