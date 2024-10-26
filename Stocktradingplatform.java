public class Stocktradingplatform {
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

// Stock class representing a stock in the market
class Stock {
    private String symbol;
    private String companyName;
    private double price;
    private int quantity;

    public Stock(String symbol, String companyName, double price, int quantity) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}

// User class representing a trader
class User {
    private String username;
    private String password;
    private ArrayList<Trade> tradeHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.tradeHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void addTrade(Trade trade) {
        tradeHistory.add(trade);
    }

    public ArrayList<Trade> getTradeHistory() {
        return tradeHistory;
    }
}

// Trade class representing a trade made by a user
class Trade {
    private User user;
    private Stock stock;
    private int quantity;
    private double price;
    private Date date;

    public Trade(User user, Stock stock, int quantity, double price) {
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.date = new Date();
    }

    public User getUser() {
        return user;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}

// StockMarket class managing stocks
class StockMarket {
    private ArrayList<Stock> stocks;

    public StockMarket() {
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public Stock getStock(String symbol) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equals(symbol)) {
                return stock;
            }
        }
        return null; // Stock not found
    }

    public ArrayList<Stock> getAllStocks() {
        return stocks;
    }
}

// Main class for the Stock Trading Platform
public class StockTradingPlatform {
    private static StockMarket stockMarket = new StockMarket();
    private static HashMap<String, User> users = new HashMap<>();
    private static User loggedInUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample stocks
        stockMarket.addStock(new Stock("AAPL", "Apple Inc.", 150.0, 100));
        stockMarket.addStock(new Stock("GOOGL", "Alphabet Inc.", 2800.0, 50));

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter username:");
                    String username = scanner.nextLine();
                    System.out.println("Enter password:");
                    String password = scanner.nextLine();
                    users.put(username, new User(username, password));
                    System.out.println("Registration successful.");
                    break;

                case 2:
                    System.out.println("Enter username:");
                    String loginUsername = scanner.nextLine();
                    System.out.println("Enter password:");
                    String loginPassword = scanner.nextLine();
                    User user = users.get(loginUsername);
                    if (user != null && user.validatePassword(loginPassword)) {
                        loggedInUser = user;
                        System.out.println("Login successful.");
                        userMenu(scanner);
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void userMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. View Trade History");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    for (Stock stock : stockMarket.getAllStocks()) {
                        System.out.printf("%s (%s): $%.2f (%d available)%n",
                                stock.getCompanyName(), stock.getSymbol(), stock.getPrice(), stock.getQuantity());
                    }
                    break;

                case 2:
                    System.out.println("Enter stock symbol:");
                    String symbol = scanner.nextLine();
                    Stock stockToBuy = stockMarket.getStock(symbol);
                    if (stockToBuy != null) {
                        System.out.println("Enter quantity:");
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (quantity <= stockToBuy.getQuantity()) {
                            double totalCost = quantity * stockToBuy.getPrice();
                            System.out.printf("Buying %d of %s for $%.2f%n", quantity, symbol, totalCost);
                            stockToBuy.updateQuantity(stockToBuy.getQuantity() - quantity);
                            Trade trade = new Trade(loggedInUser, stockToBuy, quantity, stockToBuy.getPrice());
                            loggedInUser.addTrade(trade);
                        } else {
                            System.out.println("Not enough stock available.");
                        }
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;

                case 3:
                    for (Trade trade : loggedInUser.getTradeHistory()) {
                        System.out.printf("%s traded %d of %s at $%.2f on %s%n",
                                trade.getUser().getUsername(), trade.getQuantity(), trade.getStock().getSymbol(),
                                trade.getPrice(), trade.getDate());
                    }
                    break;

                case 4:
                    loggedInUser = null;
                    System.out.println("Logged out.");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
}  