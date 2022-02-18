/**
 * !
 * name: Faris Abufarha
 * ID: 1200546
 * Sec:7
 *
 *
 */

package com.example.phase2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

/*
    public static MediaRentalManager mediaRentalManager = new MediaRentalManager();


    public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);


        readFromFiles();


        int choice;
        System.out.println("WELCOME TO THE PROGRAM!");

        do {
            System.out.println("Press 1 to add a customer");
            System.out.println("Press 2 to add a media");
            System.out.println("Press 3 to add to cart ");
            System.out.println("Press 4 to remove from cart ");
            System.out.println("Press 5 to process request 1");
            System.out.println("Press 6 to process request 2");

            System.out.println("Press 7 to add a return media ");
            System.out.println("Press 8 to search from media  ");
            System.out.println("Press -1 to quit ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> testAddingCustomers();
                case 2 -> testAddingMedia();
                case 3 -> testingAddingToCart();
                case 4 -> testingRemovingFromCart();
                case 5 -> testProcessingRequestsOne();
                case 6 -> testProcessingRequestsTwo();
                case 7 -> testReturnMedia();
                case 8 -> testSearchMedia();
                case -1 -> System.out.println("quiting...");
                default -> throw new IllegalArgumentException("PLEASE ENTER RIGHT INPUT");
            }


        } while (choice != -1);
        System.out.println("THANK YOU FOR USING OUR PROGRAM! :)");


    }

    public static void testAddingCustomers() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("input number of customers you want to add the data base");
        int numberOfCustomers = scanner.nextInt();
        String name, address, plan;
        for (int i = 0; i < numberOfCustomers; i++) {
            System.out.println("input Name, address, and the plan of the customer");
            name = scanner.next();
            address = scanner.next();
            plan =  scanner.next();
            Customer customer = new Customer(name, address, plan);
            MediaRentalManager.customerArrayList.add(customer);

        }
        System.out.println("After Adding customers:");
        System.out.println("*********************************************");
        System.out.println(mediaRentalManager.getAllCustomersInfo());
        System.out.println("*********************************************");
        //add to file
    }

    public static void testAddingMedia() throws FileNotFoundException {
        PrintWriter mediaWriter = new PrintWriter("media.txt");

        Scanner scanner = new Scanner(System.in);

        System.out.println("input number of medias you want to add to the data base");
        int numberOfMedia = scanner.nextInt();
        String name, address, plan;
        String title, rating;
        String artist, songs;
        double weight;
        int copies;
        int choice;
        for (int i = 0; i < numberOfMedia; i++) {

            System.out.println("Press 1 if it's a Movie");
            System.out.println("Press 2 if it's an Album");
            System.out.println("Press 3 if it's a Game");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("input title, number of copies, and rating of the movie ");
                    title = scanner.next();
                    copies = scanner.nextInt();
                    rating = scanner.next();
                    Movie movie = new Movie(title, copies, rating);
                    MediaRentalManager.mediaArrayList.add(movie);
                    mediaWriter.println("Movie$" + title + "$" + copies + "$" + rating);
                }
                case 2 -> {
                    System.out.println("input title, number of copies, artist name, and songs of the album ");
                    title = scanner.next();
                    copies = scanner.nextInt();
                    artist = scanner.next();
                    songs = scanner.next();
                    Album album = new Album(title, copies, artist, songs);
                    MediaRentalManager.mediaArrayList.add(album);
                    mediaWriter.println("Album$" + title + "$" + copies + "$" + artist + "$" + songs);
                }
                case 3 -> {
                    System.out.println("input title, number of copies, and rating of the game ");
                    title = scanner.next();
                    copies = scanner.nextInt();
                    weight = scanner.nextDouble();
                    Game game = new Game(title, copies, weight);
                    MediaRentalManager.mediaArrayList.add(game);
                    mediaWriter.println("Album$" + title + "$" + copies + "$" + weight);
                }
                default -> throw new IllegalArgumentException("wrong input");
            }

        }
        System.out.println("*********************************************");
        System.out.println("After adding media:");
        System.out.println(mediaRentalManager.getAllMediaInfo());
        System.out.println("*********************************************");
        // write in file

    }

    public static void testingAddingToCart() throws FileNotFoundException {
        PrintWriter cartWriter = new PrintWriter("cart.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.println("input how many materials you want to add to the cart");
        int numOfMedia = scanner.nextInt();
        Customer tmp = new Customer();
        for (int i = 0; i < numOfMedia; i++) {
            System.out.println("input customer name, and media you want to add to his cart");

            String name = scanner.next();
            String mediaTitle = scanner.next();
            for (int j = 0; j < MediaRentalManager.customerArrayList.size(); j++) {
                if (MediaRentalManager.customerArrayList.get(j).getName().equalsIgnoreCase(name)){
                    tmp = MediaRentalManager.customerArrayList.get(j);
                    break;
                }
            }
            MediaRentalManager mediaRentalManager = new MediaRentalManager();
            mediaRentalManager.addToCart(name, mediaTitle);
            cartWriter.print(name+"$");
            for (int z = 0; z < tmp.cart.size(); z++){

                cartWriter.append(tmp.cart.get(z)+":");
            }
            cartWriter.println();
        }

    }

    public static void testingRemovingFromCart() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("input customer name, and media you want to remove from his cart");

        String name = scanner.next();
        String mediaTitle = scanner.next();
        mediaRentalManager.removeFromCart(name, mediaTitle);

        // write in cart

    }

    public static void testProcessingRequestsOne() throws FileNotFoundException {
        PrintWriter rentedWriter = new PrintWriter("rented.txt");
        String x = mediaRentalManager.processRequests();
        System.out.println(mediaRentalManager.processRequests());
        rentedWriter.println(x);

    }

    public static void testProcessingRequestsTwo() {
        mediaRentalManager.processRequests();

    }

    public static void testReturnMedia() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input customer name, and media you want to return from his cart");
        String name = scanner.next();
        String mediaTitle = scanner.next();

        mediaRentalManager.returnMedia(name, mediaTitle);
        //write in recieved

    }

    public static void testSearchMedia() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> searchMediaContent = new ArrayList<>();

        System.out.println("input: title, rating, artist, and songs you're looking for");
        String title, rating, artist, songs;
        title = scanner.next();
        rating = scanner.next();
        artist = scanner.next();
        songs = scanner.nextLine();
        searchMediaContent = mediaRentalManager.searchMedia(title, rating, artist, songs);
        System.out.println("*********************************************");
        for (String x : searchMediaContent) {
            System.out.println(x);
        }
        System.out.println("*********************************************");
    }

    private static void readFromFiles() {

        File customersFile = new File("customer.txt");
        File mediaFile = new File("media.txt");
        File cartFile = new File("cart.txt");
        File rentedFile = new File("rented.txt");
        String line;
        if (customersFile.exists()) {
            try {
                Scanner fileScanner = new Scanner(customersFile);
                while (fileScanner.hasNextLine()) {

                    line = fileScanner.nextLine();
                    String[] customerStrings = line.split("\\$");
                    mediaRentalManager.addCustomer(customerStrings[0], customerStrings[1], customerStrings[2]);
                }
                fileScanner.close();
                fileScanner = new Scanner(mediaFile);
                while (fileScanner.hasNextLine()) {
                    line = fileScanner.nextLine();
                    String[] mediaString = line.split("\\$");
                    String mediaType = mediaString[0];
                    if (mediaType.equalsIgnoreCase("Movie")) {          //Double.parseDouble( mediaString[3])
                        mediaRentalManager.addMovie(mediaString[1], Integer.parseInt(mediaString[2]), mediaString[3]);
                    } else if (mediaType.equalsIgnoreCase("Album")) {
                        mediaRentalManager.addAlbum(mediaString[1], Integer.parseInt(mediaString[2]), mediaString[3], mediaString[4]);
                    } else if (mediaType.equalsIgnoreCase("Game")) {
                        mediaRentalManager.addGame(mediaString[1], Integer.parseInt(mediaString[2]), Double.parseDouble(mediaString[3]));


                    }
                    fileScanner.close();

                    fileScanner = new Scanner(cartFile);
                    String customerName;
                    while (fileScanner.hasNextLine()) {
                        line = fileScanner.nextLine();
                        String[] cartString = line.split("\\$");
                        customerName = cartString[0];
                        String[] mediaArray = cartString[1].split(":");
                        for (int i = 0; i < MediaRentalManager.customerArrayList.size(); i++) {
                            for (int k = 0; k < mediaArray.length; k++) {

                                if (MediaRentalManager.customerArrayList.get(i).getName().equalsIgnoreCase(customerName)) {
                                    for (int j = 0; j < mediaRentalManager.mediaArrayList.size(); j++) {
                                        if (MediaRentalManager.mediaArrayList.get(j).getTitle().equalsIgnoreCase(mediaArray[k])){

                                            MediaRentalManager.customerArrayList.get(i).cart.add(mediaArray[k]);
                                        }

                                    }

                                }
                            }
                        }
                                //B

                    }


                    fileScanner.close();

                    fileScanner = new Scanner(rentedFile);
                    while (fileScanner.hasNextLine()) {
                        String[] rentedString = fileScanner.nextLine().split("\\$");
                        String [] rentedMediaArray = rentedString[1].split(":");

                        customerName = rentedString[0];

                        for (int i = 0; i < MediaRentalManager.mediaArrayList.size(); i++) {
                            for (int k = 0; k < rentedMediaArray.length; k++) {

                                if (MediaRentalManager.customerArrayList.get(i).getName().equalsIgnoreCase(customerName)) {
                                    for (int j = 0; j < mediaRentalManager.mediaArrayList.size(); j++) {
                                        if (MediaRentalManager.mediaArrayList.get(j).getTitle().equalsIgnoreCase(rentedMediaArray[k])){

                                            MediaRentalManager.customerArrayList.get(i).cart.add(rentedMediaArray[k]);
                                        }

                                    }

                                }
                            }
                        }
                                //B

                    }



                    }
                    fileScanner.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

 */


}
