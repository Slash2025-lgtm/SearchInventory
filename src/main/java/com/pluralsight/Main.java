package com.pluralsight;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            ArrayList<Product> invList = getInventory();
            invList.sort(Comparator.comparing(Product::getId));
            System.out.println("=== Menu ===");
            System.out.println("[1] List all products\n" + "[2] Lookup a product by its id\n" + "[3] Find all products within a price range\n" + "[4] Add a new product\n" + "[5] Quit the application");
            int selected = keyboard.nextInt();

            if (selected == 1) {
                int i = 0;
                System.out.println("=== Products ===");
                while (i < invList.size()) {
                    Product product = invList.get(i);
                    System.out.printf("Product ID: %s \nProduct Name: %s\nProduct Price: $%.2f\nDepartment %s\n", product.getId(), product.getName(), product.getPrice(), product.getDepartment());
                    System.out.println();
                    i++;
                }
            } else if (selected == 2) {
                int i = 0;
                System.out.println("=== Product ID's ===");
                while (i < invList.size()) {
                    Product product = invList.get(i);
                    System.out.printf("Product ID: %d\n", product.getId());
                    System.out.println();
                    i++;
                }
            }  else if (selected == 4) {
                try {
                    FileWriter fileWriter = new FileWriter("src/main/resources/Inventory.csv");
                    BufferedWriter bufWriter = new BufferedWriter(fileWriter);

                    System.out.println("Enter in the Product ID: ");
                    String productId = keyboard.next();

                    System.out.println("Enter in the Product Name: ");
                    String productName = keyboard.next();

                    System.out.println("Enter in the Product Price: ");
                    String productPrice = keyboard.next();

                    System.out.println("Enter in the Product Department");
                    String productDepartment = keyboard.next();

                    System.out.println(productName + " Has been added to the Inventory");
                    bufWriter.write("\n" + productId + "|" + productName + "|" + productPrice + "|" + productDepartment);
                    bufWriter.close();

                } catch (IOException e) {
                    e.getMessage();
                }
            } else if (selected == 5) {
                exit = true;
            }
        }
    }

    public static ArrayList<Product> getInventory() {
        ArrayList<Product> invList = new ArrayList<Product>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/Inventory.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] infoList = input.split("[|]");
                String id = infoList[0];
                String name = infoList[1];
                double price = Double.parseDouble(infoList[2]);
                String department = infoList[3];
                invList.add(new Product(id, name, price, department));
            }

            bufReader.close();
        } catch (IOException e) {
            e.getMessage();
        }
        return invList;
    }
}
