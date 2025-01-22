package dns;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class representing a cache of stored DNS records.
 *
 * @version 1.0
 */
public class DNSCache {

    // TODO: fill me in!

    private static ArrayList<DNSRecord> cache = new ArrayList<>();

    public static void setCache(DNSRecord record) {
        cache.add(record);
    }

    public static void setCache(ArrayList<DNSRecord> records) {
        for (DNSRecord r : records) {
            cache.add(r);
        }
    }


    public static ArrayList<DNSRecord> getRecordsCache(String q_name, String q_type, String q_class) {
        ArrayList<DNSRecord> answer = new ArrayList<>();

        for (DNSRecord c : cache) {
            if (c.getName().equals(q_name) && c.getClassStr().equals(q_class) && c.getTypeStr().equals(q_type)) {
                answer.add(c);
            }
        }
        return answer;
    }

    public static void removeExpiredRecordsCache() {
        Instant current_time = Instant.now();

        if (cache.isEmpty()) {
            return;
        }

        for (DNSRecord c : cache) {
            Duration duration = Duration.between(current_time, c.getTime());
            // System.out.println(c.getTTL());
            if (duration.getSeconds() > c.getTTL()) {
                cache.remove(c);
            }
        }

        return;
    }
}
