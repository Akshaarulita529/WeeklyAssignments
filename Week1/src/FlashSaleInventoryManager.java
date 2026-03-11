import java.util.*;

class FlashSaleInventoryManager {

    HashMap<String, Integer> stock = new HashMap<>();
    HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    synchronized String purchaseItem(String productId, int userId) {
        int available = stock.getOrDefault(productId, 0);
        if (available > 0) {
            stock.put(productId, available - 1);
            return "Success, remaining " + (available - 1);
        }
        waitingList.putIfAbsent(productId, new LinkedList<>());
        waitingList.get(productId).add(userId);
        return "Added to waiting list";
    }

    int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public static void main(String[] args) {
        FlashSaleInventoryManager manager = new FlashSaleInventoryManager();
        manager.stock.put("IPHONE15_256GB", 100);

        System.out.println(manager.checkStock("IPHONE15_256GB"));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 67890));
    }
}