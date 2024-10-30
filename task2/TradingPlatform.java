package task2;
import java.util.HashMap;
import java.util.Scanner;

class Stock {
    private String name;
    private String ticker;
    private double price;

    public Stock(String name, String ticker, double price) {
        this.name = name;
        this.ticker = ticker;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Market {
    private HashMap<String, Stock> stocks = new HashMap<>();

    public Market() {
        stocks.put("AAPL", new Stock("Apple Inc.", "AAPL", 150.00));
        stocks.put("GOOGL", new Stock("Alphabet Inc.", "GOOGL", 2800.00));
        stocks.put("AMZN", new Stock("Amazon.com Inc.", "AMZN", 3400.00));
    }

    public Stock getStock(String ticker) {
        return stocks.get(ticker);
    }

    public void displayMarketData() {
        System.out.println("\nMarket Data:");
        for (Stock stock : stocks.values()) {
            System.out.printf("%s (%s): $%.2f\n", stock.getName(), stock.getTicker(), stock.getPrice());
        }
    }
}

class Portfolio {
    private HashMap<String, Integer> holdings = new HashMap<>();
    private double cashBalance;

    public Portfolio(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void buyStock(Stock stock, int quantity) {
        double totalCost = stock.getPrice() * quantity;
        if (cashBalance >= totalCost) {
            holdings.put(stock.getTicker(), holdings.getOrDefault(stock.getTicker(), 0) + quantity);
            cashBalance -= totalCost;
            System.out.printf("Bought %d shares of %s at $%.2f each. Total cost: $%.2f\n", quantity, stock.getTicker(), stock.getPrice(), totalCost);
        } else {
            System.out.println("Insufficient balance to buy shares.");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        if (holdings.getOrDefault(stock.getTicker(), 0) >= quantity) {
            double totalRevenue = stock.getPrice() * quantity;
            holdings.put(stock.getTicker(), holdings.get(stock.getTicker()) - quantity);
            cashBalance += totalRevenue;
            System.out.printf("Sold %d shares of %s at $%.2f each. Total revenue: $%.2f\n", quantity, stock.getTicker(), stock.getPrice(), totalRevenue);
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void displayPortfolio(Market market) {
        System.out.println("\nPortfolio:");
        double totalValue = cashBalance;
        for (String ticker : holdings.keySet()) {
            Stock stock = market.getStock(ticker);
            int quantity = holdings.get(ticker);
            double value = stock.getPrice() * quantity;
            totalValue += value;
            System.out.printf("%s: %d shares at $%.2f each, Value: $%.2f\n", ticker, quantity, stock.getPrice(), value);
        }
        System.out.printf("Cash Balance: $%.2f\n", cashBalance);
        System.out.printf("Total Portfolio Value: $%.2f\n", totalValue);
    }
}

public class TradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Market market = new Market();
        Portfolio portfolio = new Portfolio(10000.00); 

        while (true) {
            System.out.println("\nWelcome to the Stock Trading Platform!");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    market.displayMarketData();
                    break;

                case 2:
                    System.out.print("Enter ticker symbol to buy: ");
                    String buyTicker = scanner.next();
                    Stock buyStock = market.getStock(buyTicker.toUpperCase());

                    if (buyStock != null) {
                        System.out.print("Enter quantity to buy: ");
                        int buyQuantity = scanner.nextInt();
                        portfolio.buyStock(buyStock, buyQuantity);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter ticker symbol to sell: ");
                    String sellTicker = scanner.next();
                    Stock sellStock = market.getStock(sellTicker.toUpperCase());

                    if (sellStock != null) {
                        System.out.print("Enter quantity to sell: ");
                        int sellQuantity = scanner.nextInt();
                        portfolio.sellStock(sellStock, sellQuantity);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;

                case 4:
                    portfolio.displayPortfolio(market);
                    break;

                case 5:
                    System.out.println("Exiting platform. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

