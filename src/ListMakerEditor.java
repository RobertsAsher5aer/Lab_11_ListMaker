import java.util.ArrayList;
import java.util.Scanner;

public class ListMakerEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        boolean keepRunning = true;

        while (keepRunning) {
            printList(list);
            printMenu();
            String command = SafeInput.getRegExString(scanner, "Enter a command", "[AaDdIiPpQq]").toUpperCase();

            switch (command) {
                case "A":
                    addItem(scanner, list);
                    break;
                case "D":
                    deleteItem(scanner, list);
                    break;
                case "I":
                    insertItem(scanner, list);
                    break;
                case "P":
                    printList(list);
                    break;
                case "Q":
                    keepRunning = !SafeInput.getYNConfirm(scanner, "Are you sure you want to quit?");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }

        System.out.println("Exiting program...");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("P - Print (display) the list");
        System.out.println("Q - Quit the program");
    }

    private static void addItem(Scanner scanner, ArrayList<String> list) {
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the item to add");
        list.add(item);
        System.out.println("Item added to the list.");
    }

    private static void deleteItem(Scanner scanner, ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }
        int index = SafeInput.getRangedInt(scanner, "Enter the item number to delete", 1, list.size()) - 1;
        list.remove(index);
        System.out.println("Item deleted from the list.");
    }

    private static void insertItem(Scanner scanner, ArrayList<String> list) {
        int index = SafeInput.getRangedInt(scanner, "Enter the position number to insert the item at", 1, list.size() + 1) - 1;
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the item to insert");
        list.add(index, item);
        System.out.println("Item inserted into the list.");
    }

    private static void printList(ArrayList<String> list) {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }
}