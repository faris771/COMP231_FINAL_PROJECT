/*
 *   Author: Faris Abufarha
 *   ID: 1200546
 *
 *
 *   Note: i use Linux, so if u face any problem, put it in consideration please
 */


package com.example.phase2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalInt {

    // to ArrayLists represent 2 databases one for customers, the other for media
    //  the lists has been put static because these lists are for the whole class not for one object

    static ArrayList<Customer> customerArrayList = new ArrayList<>();
    static ArrayList<Media> mediaArrayList = new ArrayList<>();
    static int planLimit = 5;

    public MediaRentalManager(){

    }

    @Override
    public void addCustomer(String id,String name,String mobileNumber,String address,String plan){
        Customer customer = new Customer(id,name,mobileNumber,address,plan);
        customerArrayList.add(customer);

    }

    @Override
    public void addMovie(String mediaCode,String title, int copiesAvailable, String rating) {
        Movie movie = new Movie(mediaCode,title,copiesAvailable,rating);
        mediaArrayList.add(movie);

    }

    @Override
    public void addGame(String mediaCode,String title, int copiesAvailable, double weight) {
        Game game = new Game(mediaCode,title, copiesAvailable,weight);
        mediaArrayList.add(game);


    }

    @Override
    public void addAlbum(String mediaCode,String title, int copiesAvailable, String artist, String songs) {
        Album album = new Album(mediaCode,title,copiesAvailable,artist,songs );

        mediaArrayList.add(album);


    }

    @Override
    public void setLimitedPlanLimit(int value) {

        planLimit = value;

    }

    @Override
    public String getAllCustomersInfo() {
        String info = "";
        Collections.sort(customerArrayList);
        for (Customer x: customerArrayList) {
            info += x.toString()+"\n";
        }

        return info;
    }

    @Override
    public String getAllMediaInfo() {
        String info = "";
        Collections.sort(mediaArrayList);
        for (int i=0;i< mediaArrayList.size();i++) {
            info += mediaArrayList.get(i).toString() + "\n";
        }

        return info;
    }



    @Override
    public boolean addToCart(String id, String mediaCode) {
        boolean flag = false;
        int index = -33;
        for (int i = 0; i < customerArrayList.size(); i++) {
            if (customerArrayList.get(i).getId().equals(id)) {

                index = i;
                break;
            }
        }
        if  (index != -33) {
            for (int i = 0; i < mediaArrayList.size(); i++) {
                if (mediaArrayList.get(i).getMediaCode().equalsIgnoreCase(mediaCode)) {

                    for (int j =0 ;j<customerArrayList.get(index).cart.size();j++) {

                        if (customerArrayList.get(index).cart.get(j).equals(mediaCode)) {   // if already exists in his cart

                            return false;
                        }
                    }
                    customerArrayList.get(index).cart.add(mediaCode);


                    return true;



                }
            }
        }
        else if (index == -33){
            return false;
        }
        System.out.println(index +"index");

        return flag;
    }


    @Override
    public boolean removeFromCart(String customerName, String mediaTitle) {
        boolean flag = false;
        for (int i=0;i< customerArrayList.size();i++){
            if (!customerArrayList.get(i).getName().equals(customerName)){
                continue;
            }
            for (int j=0;j<customerArrayList.get(i).cart.size();j++) {

                if (customerArrayList.get(i).cart.get(j).equals(mediaTitle)) {

                    //                            get(j).setTitle("")
                    customerArrayList.get(i).cart.remove(mediaTitle);
                    flag = true;

                }


            }


        }
        return flag;
    }

    @Override
    public String processRequests() {


        String processRequest = "";
        String mediaCode;
        boolean flag = false;
        Collections.sort(customerArrayList);
        for (Customer x : customerArrayList){

            int z =0;
            String mediaTitle="";
            if (x.getPlan().equalsIgnoreCase("UNLIMITED")){

               for (int i=0;i<x.cart.size();i++) {

                   for (int j=0;j<mediaArrayList.size();j++){

                       mediaCode = mediaArrayList.get(j).getMediaCode();
                       if (mediaCode.equals(x.cart.get(i)) ){
                           if ( mediaArrayList.get(j).getNumberOfCopies()>0) {


                               x.rentedList.add(mediaCode);
                               x.cart.remove(mediaCode);
                               i--;

                               int copies = mediaArrayList.get(j).getNumberOfCopies();
                               copies--;
                               mediaArrayList.get(j).setNumberOfCopies(copies);
                               processRequest += "Sending " + mediaCode + " to " + x.getName() + "\n";
                               break;


                           }
                       }

                   }

                }
            }


            else if (x.getPlan().equalsIgnoreCase("LIMITED")){

                for (int i=0;i<x.cart.size();i++) {

                    for (int j=0;j<mediaArrayList.size();j++){

                        mediaCode = mediaArrayList.get(j).getMediaCode();
                       if (mediaCode.equals(x.cart.get(i)) ){
                            if ( mediaArrayList.get(j).getNumberOfCopies()>0) {

                                if (x.rentedList.size() < planLimit) {
                                    //System.out.println("Test");
                                    x.rentedList.add(mediaCode);
                                    x.cart.remove(mediaCode);
                                    i--;
                                    processRequest += "Sending " + mediaCode + " to " + x.getName() + "\n";
                                    int copies = mediaArrayList.get(j).getNumberOfCopies();
                                    copies--;
                                    mediaArrayList.get(j).setNumberOfCopies(copies);
                                    break;

                                }
                            }


                        }


                    }

                }


            }
        }
        return processRequest;
    }

    @Override
    public boolean returnMedia(String customerId, String mediaCode) {
        boolean alert = false;
        int temp;
        boolean mediaExists = false;
        String mediaTitle = "";
        int mediaIndex = -1;
        for (int i=0;i< customerArrayList.size();i++) {

            if (customerArrayList.get(i).getId().equals(customerId)) {
                // i : for each student, j for the items in media lists
                for (int j = 0; j < mediaArrayList.size(); j++) {

                    if (mediaArrayList.get(j).getMediaCode().equalsIgnoreCase(mediaCode)) {
                        mediaExists = true;
                        mediaTitle = mediaArrayList.get(j).getTitle();
                        mediaIndex = j;
                        break;

                    }
                }
                if (!mediaExists) {
                    alert = true;// WILL RETURN FALSE, AND SO IN FX WE WILL SHOW ERROR MESSAGE
                    break;
                }
                // checks if it exists in the rented list for customer 'i'
                // j stands for rented items
                else {

                    for (int j = 0; j < customerArrayList.get(i).rentedList.size(); j++) {
                        if (customerArrayList.get(i).rentedList.get(j).equalsIgnoreCase(mediaTitle)) {

                            System.out.println("inner -1");
                            if (mediaIndex != -1) {
                                customerArrayList.get(i).rentedList.remove(mediaTitle);
                                int minus = mediaArrayList.get(mediaIndex).getNumberOfCopies();
                                minus--;
                                mediaArrayList.get(mediaIndex).setNumberOfCopies(minus);


                                System.out.println("test 2");
                            }
                            break;
                        }

                    }


                }


            }


        }

        return alert;
    }

    @Override
    public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
        ArrayList <String> titles = new ArrayList<>();
        // exception: media must have title

        if (title==null && rating ==null && artist ==null  && songs ==null){
            for (Media x: mediaArrayList){

                titles.add(x.getTitle());

            }
        }
        else {
            boolean titleState, ratingState, artistState, songsState;
            titleState = false;
            ratingState = false;
            artistState = false;
            songsState = false;


            for (Media z : mediaArrayList) {



                if (z instanceof Movie){
                    // what matters here is title and rating
                    titleState  =    title == null || z.getTitle().equalsIgnoreCase(title);
                    ratingState = rating==null  || ((Movie) z).getRating().equalsIgnoreCase(rating);
                    artistState = artist == null;
                    songsState = songs ==null;

                }

                else if (z instanceof Game){
                    // what matters in the parameter is the title
                    titleState = title==null || z.getTitle().equalsIgnoreCase(title);
                    artistState = artist == null;
                    songsState = songs ==null;
                    ratingState = rating==null;

                }
                else if (z instanceof Album){
                    titleState = title==null || z.getTitle().equalsIgnoreCase(title);
                    artistState = artist == null || ((Album) z).getArtist().equalsIgnoreCase(artist);
                    songsState = songs == null || ((Album) z).getSongs().equalsIgnoreCase(songs);
                    ratingState = rating == null;


                }
                titles.add(z.getTitle());

            }

        }
        Collections.sort(titles);
        return titles;
    }


}
