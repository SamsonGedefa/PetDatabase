/**
 * Author Samson Gedafa
 * Assignment: CSC 422 – Assignment #1 – Part 2
 * Date: 05/18/2022
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Pet Class
 */
public class Pet {

    private String name;

    private int age;

    static ArrayList<Pet> pets;


    /**
     * Pet Constructor
     *
     * @param name
     * @param age
     */
    public Pet(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    /**
     * Pet name getter
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Pet name Setter
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Pet age getter.
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Pet age setter
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        String s = "";
        s = String.format("%5s%10s%10s%5d%5s\n", "|", name, "|", age, "|");
        return s;
    }


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        pets = new ArrayList<>();
        int option;
        do {
            System.out.println("What would like to do?\n" + "\t1) View all pets\n" + "\t2) Add more pets\n"
                    + "\t3) Exit program");
            System.out.print("Your choice: ");
            option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1:
                    viewAllPets();
                    break;
                case 2:
                    addMorePets(scan);
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        } while (option != 3);

        scan.close();
    }

    /**
     * View all pets.
     */
    private static void viewAllPets() {

        System.out.println("+---------------------------------------+");
        System.out.printf("|%5s%5s%10s%10s%5s%5s\n", "ID", "|", "NAME", "|", "AGE", "|");
        System.out.println("+---------------------------------------+");
        int i = 0;
        for (Pet pet : pets) {

            System.out.printf("|%5d%5s", i, pet.toString());

            i++;
        }
        System.out.println("+---------------------------------------+");
        System.out.println((i) + " rows in set.");
    }

    /**
     * Adds the more pets.
     *
     * @param scan the scan
     */
    private static void addMorePets(Scanner scan) {
        int count = 0;
        String petString = "";
        do {

            System.out.print("add pet (name, age): ");
            petString = scan.nextLine();
            if (petString.equalsIgnoreCase("done")) {

                break;
            }
            String name = petString.split("\\s+")[0];
            int age = Integer.parseInt(petString.split("\\s+")[1]);

            pets.add(new Pet(name, age));
            count++;

        } while (!petString.equalsIgnoreCase("done"));
        System.out.println(count + " pets added.");
    }


}
