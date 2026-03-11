import java.util.*;

class FinancialTransactionTwoSum {

    List<int[]> findTwoSum(int[] transactions, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();
        for(int i=0;i<transactions.length;i++){
            int complement = target - transactions[i];
            if(map.containsKey(complement)) result.add(new int[]{map.get(complement), i});
            map.put(transactions[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        FinancialTransactionTwoSum detector = new FinancialTransactionTwoSum();
        int[] tx = {500, 300, 200};
        List<int[]> pairs = detector.findTwoSum(tx, 500);
        for(int[] p : pairs) System.out.println("Pair: (" + p[0] + "," + p[1] + ")");
    }
}
