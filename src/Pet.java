/**
 * Author Samson Gedafa
 * Assignment: CSC 422 – Assignment #2 – Part 2
 * Date: 05/25/2022
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Pet Class
 */
public class Pet {

    private String name;

    private int age;

    static ArrayList<Pet> pets;

    static int size;

    //    static String FILE_NAME = "petList.txt";
    private static final String FILE_NAME = "petList.txt";


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


    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        pets = new ArrayList<>();
        int option;

        readFile(); // load pet to memory at the start
        do {
            System.out.println("What would like to do?\n" + "\t1) View all pets\n" + "\t2) Add more pets\n"
                    + "\t3) Update an existing pet\n" + "\t4) Remove an existing pet\n" + "\t5) Search pets by name\n"
                    + "\t6) Search pets by age\n" + "\t7) Exit program");

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
                    updateExistingPet(scan);
                    break;
                case 4:
                    removeExistingPet(scan);
                    break;
                case 5:
                    searchByPetName(scan);
                    break;
                case 6:
                    searchByPetAge(scan);
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        } while (option != 5);

        scan.close();
    }

    /**
     * Load file pet list to memory
     *
     * @throws FileNotFoundException
     */
    public static void readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(FILE_NAME));

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" ");
            pets.add(new Pet(parts[0], Integer.parseInt(parts[1])));
        }
        System.out.println();
    }

    /**
     * Write pet to file
     *
     * @throws IOException
     */
    public static void writeToFile() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));

        for (Pet pet : pets) {
            writer.write(pet.getName() + " " + pet.getAge() + System.lineSeparator());
        }

        writer.close();
    }

    /**
     * Display all pets in the database
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
     * Adds more pets to database.
     *
     * @param scan
     */
    private static void addMorePets(Scanner scan) throws IOException {


        String petString = "";
        do {

            if (size >= 5 || pets.size() >= 5) {
                System.out.println("Error: Database is full.\n");
                return;
            }

            System.out.print("add pet (name, age): ");
            petString = scan.nextLine();

            if (petString.equalsIgnoreCase("done")) {
                break;
            }

            if (petString.isEmpty()) {
                System.out.println("Error: Please, make sure you entered both name and age.\n");
                return;
            } else if (petString.split(" ").length != 2) {
                System.out.println("Error: Please, make sure you entered both name and age.\n");
                return;
            } else if (isInteger(petString.split("\\s+")[0]) || !isInteger(petString.split("\\s+")[1])) {
                System.out.println("Error: Invalid name and age.\n");
                return;
            }

            String name = petString.split("\\s+")[0];
            int age = Integer.parseInt(petString.split("\\s+")[1]);


            if (age < 1 || age > 20) {
                System.out.println("Error: Age must be between 0 and 20.\n");
                return;
            }


            pets.add(new Pet(name, age));
            writeToFile();
            size++;

        } while (!petString.equalsIgnoreCase("done"));


        System.out.println(size + " pets added.");
    }


    /**
     * Search by pet name
     *
     * @param scan
     */
    private static void searchByPetName(Scanner scan) {

        System.out.print("Enter name to search: ");
        String name = scan.nextLine();

        System.out.println("+---------------------------------------+");
        System.out.printf("|%5s%5s%10s%10s%5s%5s\n", "ID", "|", "NAME", "|", "AGE", "|");
        System.out.println("+---------------------------------------+");
        int i = 0;
        for (Pet pet : pets) {

            if (pet.getName().equalsIgnoreCase(name)) {
                System.out.printf("|%5d%5s", i, pet.toString());
                i++;
            }
        }
        System.out.println("+---------------------------------------+");
        System.out.println((i) + "rows in set.");

    }

    /**
     * Search by pet age
     *
     * @param scan
     */
    private static void searchByPetAge(Scanner scan) {

        System.out.print("Enter age to search: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.println("+---------------------------------------+");
        System.out.printf("|%5s%5s%10s%10s%5s%5s\n", "ID", "|", "NAME", "|", "AGE", "|");
        System.out.println("+---------------------------------------+");
        int i = 0;
        for (Pet pet : pets) {

            if (pet.getAge() == age) {

                System.out.printf("|%5d%5s", i, pet);
                i++;
            }
        }
        System.out.println("+---------------------------------------+");
        System.out.println((i) + "rows in set.");

    }


    /**
     * Update pet database
     *
     * @param scan
     */
    private static void updateExistingPet(Scanner scan) throws IOException {

        viewAllPets();
        System.out.print("Enter the pet id to update: ");
        int id = scan.nextInt();
        scan.nextLine();

        if (!checkIdInBound(id)) {
            System.out.println("Error: Invalid id.\n");
            return;
        }

        System.out.print("Enter the new name and age: ");
        String petString = scan.nextLine();
        String name = petString.split("\\s+")[0];
        int age = Integer.parseInt(petString.split("\\s+")[1]);
        String oldName = pets.get(id).getName();
        int oldAge = pets.get(id).getAge();
        pets.get(id).setName(name);
        pets.get(id).setAge(age);
        writeToFile();

        System.out.println(oldName + " " + oldAge + " changed to " + name + " " + age);
    }

    /**
     * Removes pet from database
     *
     * @param scan
     */
    private static void removeExistingPet(Scanner scan) throws IOException {

        viewAllPets();
        System.out.print("Enter pet id to remove: ");
        int id = scan.nextInt();
        scan.nextLine();


        if (!checkIdInBound(id)) {
            System.out.println("Error: Invalid id.\n");
            return;
        }

        String name = pets.get(id).getName();
        int age = pets.get(id).getAge();
        pets.remove(id);
        writeToFile();
        size--;
        System.out.println(name + " " + age + " is removed.");
    }

    /**
     * Checks if id is in array range
     *
     * @param id
     * @return
     */
    public static boolean checkIdInBound(int id) {
        return id >= 0 && id < pets.size();
    }

    /**
     * Check if input is digit or not
     *
     * @param name
     * @return
     */
    private static boolean isInteger(String name) {
        try {
            Integer.parseInt(name);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}



