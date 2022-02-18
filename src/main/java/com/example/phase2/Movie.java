package com.example.phase2;

public class Movie extends Media{

    private String rating;

    public Movie(){

    }
    // instead of repeating  title and copies every time, I used inheritance, and passed the title and copies to the super class


    // fx: code
    public Movie(String mediaCode,String title, int numberOfCopies, String rating) {
        super(mediaCode,title, numberOfCopies);
        this.rating = rating;
    }

    @Override
    public String toString() {
        return super.toString() +
                " rating=" + rating + " ";
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getInfo(){

        return "Movie" +"$"+ super.getMediaCode() + "$" + super.getTitle() +"$"+ super.getNumberOfCopies() + "$" +this.getRating();
    }

}
