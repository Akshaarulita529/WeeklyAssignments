import java.util.*;

class WebsiteAnalyticsDashboard {

    HashMap<String, Integer> pageViews = new HashMap<>();
    HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    HashMap<String, Integer> trafficSources = new HashMap<>();

    void processEvent(String url, String userId, String source) {
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);
        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    void getDashboard() {
        System.out.println("Top Pages:");
        pageViews.entrySet().stream()
                .sorted((a,b) -> b.getValue() - a.getValue())
                .limit(10)
                .forEach(e -> {
                    int unique = uniqueVisitors.getOrDefault(e.getKey(), new HashSet<>()).size();
                    System.out.println(e.getKey() + " - " + e.getValue() + " views (" + unique + " unique)");
                });

        System.out.println("\nTraffic Sources:");
        int total = trafficSources.values().stream().mapToInt(i->i).sum();
        for (Map.Entry<String, Integer> entry : trafficSources.entrySet()) {
            double percent = total==0 ? 0 : entry.getValue()*100.0/total;
            System.out.println(entry.getKey() + ": " + String.format("%.1f", percent) + "%");
        }
    }

    public static void main(String[] args) {
        WebsiteAnalyticsDashboard dashboard = new WebsiteAnalyticsDashboard();
        dashboard.processEvent("/article/breaking-news","user123","Google");
        dashboard.processEvent("/article/breaking-news","user456","Facebook");
        dashboard.processEvent("/sports/championship","user789","Direct");
        dashboard.getDashboard();
    }
}