import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerRateLimiter {
    private final long maxBucketSize;
    private final long refillRate;
    private final Map<String, TokenBucket> tokenBuckets;

    public CustomerRateLimiter(long maxBucketSize, long refillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;
        this.tokenBuckets = new ConcurrentHashMap<>();
    }

    private TokenBucket getBucketForCustomer(String customerId) {
        return tokenBuckets.computeIfAbsent(customerId, k -> new TokenBucket(maxBucketSize, refillRate));
    }

    public boolean allowRequest(String customerId, int tokens) {
        TokenBucket bucket = getBucketForCustomer(customerId);
        return bucket.allowRequest(tokens);
    }
}

class TokenBucket {
    private final long maxBucketSize;
    private final long refillRate;
    private double currentBucketSize;
    private long lastRefillTimestamp;

    public TokenBucket(long maxBucketSize, long refillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;
        this.currentBucketSize = maxBucketSize;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest(int tokens) {
        refill();
        if (currentBucketSize >= tokens) {
            currentBucketSize -= tokens;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        double tokensToAdd = (now - lastRefillTimestamp) * refillRate / 1000;
        currentBucketSize = Math.min(currentBucketSize + tokensToAdd, maxBucketSize);
        lastRefillTimestamp = now;
    }
}

