import java.util.*;

class DNSEntry {
    String ip;
    long expiryTime;

    DNSEntry(String ip, long ttlSeconds) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
    }
}

class DNSCacheSystem {

    HashMap<String, DNSEntry> cache = new HashMap<>();
    int hits = 0, misses = 0;

    String queryUpstream(String domain) {
        return "172.217.14." + (new Random().nextInt(255));
    }

    String resolve(String domain) {
        DNSEntry entry = cache.get(domain);
        if (entry != null && entry.expiryTime > System.currentTimeMillis()) {
            hits++;
            return entry.ip + " (HIT)";
        }
        misses++;
        String ip = queryUpstream(domain);
        cache.put(domain, new DNSEntry(ip, 300));
        return ip + " (MISS, queried upstream)";
    }

    void getCacheStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);
        System.out.println("Cache HIT: " + hits + ", MISS: " + misses + ", Hit Rate: " + hitRate + "%");
    }

    public static void main(String[] args) {
        DNSCacheSystem cache = new DNSCacheSystem();
        System.out.println(cache.resolve("google.com"));
        System.out.println(cache.resolve("google.com"));
        cache.getCacheStats();
    }
}