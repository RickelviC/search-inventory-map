package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class SearchInventoryMap {
    private static final String CVS_File = "inventory.csv";


    public static HashMap<Integer, Product> inventory =
            new HashMap<Integer, Product>();

    public static void main(String[] args) {
        // this method loads product objects into inventory
        loadInventory();

        for (Product product : inventory.values()) {
            System.out.println(product);
        }

        Scanner scanner = new Scanner(System.in);



        while (true){
            System.out.print("What item # are you interested in? ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Product matchedProduct = inventory.get(id);

            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
            } else {
                System.out.printf("We carry %s and the price is $%.2f",
                        matchedProduct.getName(), matchedProduct.getPrice());
            }
            System.out.println();
            System.out.println("Do you want to Stop?(y to quit) ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")){
                return;
            }
        }
    }

    private static void loadInventory(){

        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(CVS_File));

            while ((line = reader.readLine()) != null) {

                String[] divider = line.split("\\|");
                int id = Integer.parseInt(divider[0]);
                String name = divider[1];
                double price = Double.parseDouble(divider[2]);

                Product product = new Product(id,name,price);
                inventory.put(id,product);
            }

        } catch (Exception ex) {
            System.err.println("Something went wrong");
        }

    }

}
