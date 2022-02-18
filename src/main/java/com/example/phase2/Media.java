/*
 *   Author: Faris Abufarha
 *   ID: 1200546
 *
 *
 *   Note: i use Linux, so if u face any problem, put it in consideration please
 */


package com.example.phase2;

import java.util.Objects;
import java.util.SplittableRandom;

public abstract class Media implements Comparable<Media>{
    //in order to @Override comparable method, i implemented the Comparable interface

    //fx update:
    private String mediaCode = "";
    private String title="";
    private int numberOfCopies=0;
    public Media(){

    }

    // fx: code
    public Media(String mediaCode, String title, int numberOfCopies) {
        this.mediaCode = mediaCode;
        this.title = title;
        setNumberOfCopies(numberOfCopies);
    }

    public String getTitle() {
        return title;
    }


    public int getNumberOfCopies() {
        return numberOfCopies;
    }


    public void setNumberOfCopies(int numberOfCopies) {
        if (numberOfCopies >= 0) {
            this.numberOfCopies = numberOfCopies;
        } else {

            throw new IllegalArgumentException(" number of copies must be >= 0");

        }
    }

    @Override
    public String toString() {
        return
                "title='" + title + '\'' +
                ", numberOfCopies=" + numberOfCopies ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Media media = (Media) o;
        return title.equals(media.title);
    }

    // compare to used to make some sorting in future steps, sorting by title
    @Override
    public int compareTo(Media media) {

        return this.getTitle().compareTo(media.getTitle());

    }
    public abstract String getInfo();


    public String getMediaCode() {
        return mediaCode;
    }

    public void setMediaCode(String mediaCode) {
        this.mediaCode = mediaCode;
    }
}
