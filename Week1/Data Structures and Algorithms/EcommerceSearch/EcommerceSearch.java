import java.util.Scanner;

public class EcommerceSearch {

    public static void main(String[] args) {

        // Sample product data (sorted alphabetically by product name)

        Product[] products = new Product[8];
        products[0] = new Product("P001", "Backpack", "Bags");
        products[1] = new Product("P002", "Desk Lamp", "Furniture");
        products[2] = new Product("P003", "Headphones", "Electronics");
        products[3] = new Product("P004", "Keyboard", "Electronics");
        products[4] = new Product("P005", "Notebook", "Stationery");
        products[5] = new Product("P006", "Water Bottle", "Sports");
        products[6] = new Product("P007", "Wireless Mouse", "Electronics");
        products[7] = new Product("P008", "Yoga Mat", "Sports");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product name to search: ");
        String query = sc.nextLine();

        System.out.println();

        // Linear Search
        System.out.println("--- Linear Search ---");
        int linearCount = 0;
        Product linearResult = null;

        for (int i = 0; i < products.length; i++) {
            linearCount++;
            if (products[i].productName.equalsIgnoreCase(query)) {
                linearResult = products[i];
                break;
            }
        }

        if (linearResult != null) {
            System.out.println("Found: ID: " + linearResult.productId + " | Name: " + linearResult.productName + " | Category: " + linearResult.category);
        } else {
            System.out.println("Product not found.");
        }
        System.out.println("Comparisons Made: " + linearCount);

        System.out.println();

        // Binary Search (array is already sorted alphabetically above)
        System.out.println("--- Binary Search ---");
        int binaryCount = 0;
        Product binaryResult = null;
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryCount++;

            if (products[mid].productName.equalsIgnoreCase(query)) {
                binaryResult = products[mid];
                break;
            } else if (products[mid].productName.compareToIgnoreCase(query) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (binaryResult != null) {
            System.out.println("Found: ID: " + binaryResult.productId + " | Name: " + binaryResult.productName + " | Category: " + binaryResult.category);
        } else {
            System.out.println("Product not found.");
        }
        System.out.println("Comparisons Made: " + binaryCount);

        System.out.println();
        System.out.println("Recommended for E-commerce Platform: Binary Search");
        System.out.println("Reason: Requires fewer comparisons and performs faster on sorted data.");

        sc.close();
    }
}



