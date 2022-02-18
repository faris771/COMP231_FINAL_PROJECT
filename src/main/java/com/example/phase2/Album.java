package com.example.phase2;

import java.util.ArrayList;

public class Album extends Media  {

    private String artist;
    private String songs;

    public Album(){

    }
    // instead of repeating  title and copies every time, I used inheritance, and passed the title and copies to the super class


    // fx : code, seems like no songs
    public Album(String mediaCode,String title, int numberOfCopies, String artist, String songs) {
        super(mediaCode,title, numberOfCopies);
        this.artist = artist;
        this.songs = songs;
    }

    public String getArtist() {
        return artist;
    }


    public String getSongs() {
        return songs;
    }


    @Override
    public String toString() {
        return super.toString() +
                " artist= " + artist + '\'' +
                ", songs=" + songs+" " ;
    }

    public String getInfo(){
        return "Album" +"$"+ super.getMediaCode() + "$" + super.getTitle() +"$"+ super.getNumberOfCopies() + "$" +this.getArtist() + "$" + this.getSongs();
    }

}
