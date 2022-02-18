package com.example.phase2;

public class Game extends Media {
    public Game() {

    }

    // instead of repeating  title and copies every time, I used inheritance, and passed the title and copies to the super class
    private double weight = 0;

    public Game(String mediaCode,String title, int numberOfCopies, double weight) {
        super(mediaCode,title, numberOfCopies);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
        }
        else {
            throw new IllegalArgumentException("Weight must be > 0");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                " weight=" + weight+" " ;
    }

    public String getInfo(){
        return "Game" +"$"+ super.getMediaCode() + "$" + super.getTitle() + "$"+  super.getNumberOfCopies() + "$" +this.getWeight();
    }
}
