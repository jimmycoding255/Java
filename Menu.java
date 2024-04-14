import java.util.HashMap;
import java.util.Scanner;

class Menu {
    private String name;
    private HashMap<String, Double> items;

    public Menu(String name, HashMap<String, Double> items) {
        this.name = name;
        this.items = items;
    }

    public String calculateBill(String[] purchasedItems) {
        double totalPrice = 0.0;
        for (String item : purchasedItems) {
            totalPrice += this.items.get(item);
        }
        return "Your total is $" + totalPrice;
    }

    public static void main(String[] args) {
        // Welcome
        System.out.println("Welcome to Anytime Restaurant!\n");

        // Get the menu
        System.out.println("We have the following menus: brunch, early_bird, dinner, kids\n");
        String menuType = null;
        Scanner scanner = new Scanner(System.in);
        while (menuType == null) {
            // Choose a menu
            System.out.print("Which menu would you like?: ");
            String chosenMenu = scanner.nextLine();
            if (chosenMenu.equals("brunch") || chosenMenu.equals("early_bird") || chosenMenu.equals("dinner") || chosenMenu.equals("kids")) {
                menuType = chosenMenu;
                // Print menu items
                System.out.println("\nHere are the items on the " + menuType + " menu:");
                HashMap<String, Double> menuItems = getMenuItems(menuType);
                for (String key : menuItems.keySet()) {
                    System.out.println(key + " " + menuItems.get(key));
                }
            } else {
                System.out.println("Invalid menu choice. Please try again.");
            }
        }

        // Take the order
        String[] order = new String[0];
        boolean anythingElse = true;
        while (anythingElse) {
            System.out.print("What would you like to order?: ");
            String item = scanner.nextLine();
            HashMap<String, Double> menuItems = getMenuItems(menuType);
            if (menuItems.containsKey(item)) {
                order = addToOrder(order, item);
                System.out.print("Do you want anything else? (yes/no): ");
                String yesNo = scanner.nextLine();
                if (yesNo.equalsIgnoreCase("no")) {
                    anythingElse = false;
                    break;
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
                System.out.print("Do you want anything else? (yes/no): ");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("no")) {
                    anythingElse = false;
                    break;
                }
            }
        }

        // Calculate the order total
        if (order.length > 0) {
            Menu currentMenu = getMenu(menuType);
            String bill = currentMenu.calculateBill(order);
            System.out.println(bill);
        } else {
            System.out.println("\nOkay, so you don't want anything? That's alright.");
        }

        scanner.close();
    }

    private static HashMap<String, Double> getMenuItems(String menuType) {
        switch (menuType) {
            case "brunch":
                return getBrunchMenu();
            case "early_bird":
                return getEarlyBirdMenu();
            case "dinner":
                return getDinnerMenu();
            case "kids":
                return getKidsMenu();
            default:
                return new HashMap<>();
        }
    }

    private static Menu getMenu(String menuType) {
        switch (menuType) {
            case "brunch":
                return new Menu("Brunch", getBrunchMenu());
            case "early_bird":
                return new Menu("Early-bird Dinner", getEarlyBirdMenu());
            case "dinner":
                return new Menu("Dinner", getDinnerMenu());
            case "kids":
                return new Menu("Kids", getKidsMenu());
            default:
                return null;
        }
    }

    private static String[] addToOrder(String[] order, String item) {
        String[] newOrder = new String[order.length + 1];
        for (int i = 0; i < order.length; i++) {
            newOrder[i] = order[i];
        }
        newOrder[order.length] = item;
        return newOrder;
    }

    private static HashMap<String, Double> getBrunchMenu() {
        HashMap<String, Double> brunchMenu = new HashMap<>();
        brunchMenu.put("scrambled eggs", 3.5);
        brunchMenu.put("pancakes", 5.5);
        brunchMenu.put("waffles", 6.0);
        brunchMenu.put("hamburger", 7.0);
        brunchMenu.put("french fries", 3.5);
        brunchMenu.put("coffee", 1.5);
        brunchMenu.put("oatmeal", 4.0);
        brunchMenu.put("green tea", 2.0);
        brunchMenu.put("water", 1.0);
        brunchMenu.put("orange juice", 2.5);
        brunchMenu.put("milk", 2.5);
        brunchMenu.put("soy milk", 2.5);
        return brunchMenu;
    }

    private static HashMap<String, Double> getEarlyBirdMenu() {
        HashMap<String, Double> earlyBirdMenu = new HashMap<>();
        earlyBirdMenu.put("spaghetti", 9.0);
        earlyBirdMenu.put("salad", 5.0);
        earlyBirdMenu.put("cheese pizza", 9.0);
        earlyBirdMenu.put("chicken sandwich", 9.5);
        earlyBirdMenu.put("vegetable soup", 5.5);
        earlyBirdMenu.put("green tea", 2.0);
        earlyBirdMenu.put("water", 1.0);
        earlyBirdMenu.put("soda", 2.5);
        earlyBirdMenu.put("milk", 2.5);
        earlyBirdMenu.put("soy milk", 2.5);
        return earlyBirdMenu;
    }

    private static HashMap<String, Double> getDinnerMenu() {
        HashMap<String, Double> dinnerMenu = new HashMap<>();
        dinnerMenu.put("chicken with eggplant", 15.0);
        dinnerMenu.put("chicken salad", 7.0);
        dinnerMenu.put("cheese pizza", 12.0);
        dinnerMenu.put("chicken noodle soup", 5.5);
        dinnerMenu.put("mashed potatoes", 5.5);
        dinnerMenu.put("coffee", 2.0);
        dinnerMenu.put("green tea", 2.0);
        dinnerMenu.put("water", 1.0);
        dinnerMenu.put("soda", 2.5);
        dinnerMenu.put("milk", 2.5);
        dinnerMenu.put("soy milk", 2.5);
        return dinnerMenu;
    }

    private static HashMap<String, Double> getKidsMenu() {
        HashMap<String, Double> kidsMenu = new HashMap<>();
        kidsMenu.put("chicken nuggets", 4.5);
        kidsMenu.put("meatball sub", 6.0);
        kidsMenu.put("chicken sandwich", 6.0);
        kidsMenu.put("water", 1.0);
        kidsMenu.put("soda", 2.5);
        kidsMenu.put("milk", 2.5);
        kidsMenu.put("soy milk", 2.5);
        return kidsMenu;
    }
}

