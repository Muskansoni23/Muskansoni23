import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Stock {
    private String symbol;
    private double price;
    private int availableShares;

    public Stock(String symbol, double price, int availableShares) {
        this.symbol = symbol;
        this.price = price;
        this.availableShares = availableShares;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableShares() {
        return availableShares;
    }

    public void buyShares(int shares) {
        availableShares -= shares;
    }

    public void sellShares(int shares) {
        availableShares += shares;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();

    public void buyStock(String symbol, int shares) {
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + shares);
    }

    public void sellStock(String symbol, int shares) {
        if (holdings.containsKey(symbol) && holdings.get(symbol) >= shares) {
            holdings.put(symbol, holdings.get(symbol) - shares);
            if (holdings.get(symbol) == 0) {
                holdings.remove(symbol);
            }
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void displayPortfolio() {
        System.out.println("Your Portfolio:");
        if (holdings.isEmpty()) {
            System.out.println("No holdings.");
        } else {
            for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                System.out.println("Stock: " + entry.getKey() + ", Shares: " + entry.getValue());
            }
        }
    }
}

class StockMarket {
    private Map<String, Stock> stocks = new HashMap<>();

    public void addStock(String symbol, double price, int availableShares) {
        stocks.put(symbol, new Stock(symbol, price, availableShares));
    }

    public void displayStocks() {
        System.out.println("Available Stocks:");
        for (Stock stock : stocks.values()) {
            System.out.println("Symbol: " + stock.getSymbol() + ", Price: $" + stock.getPrice() + ", Available Shares: " + stock.getAvailableShares());
        }
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockMarket market = new StockMarket();
        Portfolio portfolio = new Portfolio();

        // Adding some stocks to the market
        market.addStock("AAPL", 150.00, 100);
        market.addStock("GOOGL", 2800.00, 50);
        market.addStock("TSLA", 700.00, 75);

        int choice;

        do {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Available Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    market.displayStocks();
                    break;
                case 2:
                    System.out.print("Enter Stock Symbol to Buy: ");
                    String buySymbol = scanner.next();
                    System.out.print("Enter Number of Shares: ");
                    int buyShares = scanner.nextInt();
                    Stock stockToBuy = market.getStock(buySymbol);
                    if (stockToBuy != null && stockToBuy.getAvailableShares() >= buyShares) {
                        stockToBuy.buyShares(buyShares);
                        portfolio.buyStock(buySymbol, buyShares);
                        System.out.println("Bought " + buyShares + " shares of " + buySymbol);
                    } else {
                        System.out.println("Insufficient shares available or stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Stock Symbol to Sell: ");
                    String sellSymbol = scanner.next();
                    System.out.print("Enter Number of Shares: ");
                    int sellShares = scanner.nextInt();
                    portfolio.sellStock(sellSymbol, sellShares);
                    if (portfolio.holdings.containsKey(sellSymbol)) {
                        Stock stockToSell = market.getStock(sellSymbol);
                        stockToSell.sellShares(sellShares);
                        System.out.println("Sold " + sellShares + " shares of " + sellSymbol);
                    }
                    break;
                case 4:
                    portfolio.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting the platform.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
