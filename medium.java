import java.util.*;

public class CardOrganizer {
    public static void main(String[] args) {
        Map<String, Set<String>> cardCollection = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Card\n2. Display Cards of a Symbol\n3. Display All Cards\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Card Symbol (e.g., Hearts, Spades): ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter Card Value (e.g., Ace, 2, King): ");
                    String value = scanner.nextLine();
                    cardCollection.computeIfAbsent(symbol, k -> new HashSet<>()).add(value);
                    break;
                case 2:
                    System.out.print("Enter Symbol to Display: ");
                    symbol = scanner.nextLine();
                    System.out.println("Cards: " + cardCollection.getOrDefault(symbol, Collections.emptySet()));
                    break;
                case 3:
                    for (Map.Entry<String, Set<String>> entry : cardCollection.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
