import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import DOS.Request;


public class SlidingWindowApiRateLimiter {
    int windowInterval;
    int maxRequests;

    Deque<Long> timeStampLog ;

    public SlidingWindowApiRateLimiter(int windowInterval, int maxRequests) {
        this.windowInterval = windowInterval;
        this.maxRequests = maxRequests;
        timeStampLog = new ArrayDeque<>();

    }

    boolean isAllowed(Request request){
        // find nearest ts > currentWindowStart
        long now = Instant.now().getEpochSecond();
        long currentWindowStart = now - windowInterval;
        while(!timeStampLog.isEmpty() && timeStampLog.getFirst() < currentWindowStart){
            timeStampLog.peekFirst();
        }
        // 5 minutes -> 10 requests
        // [0, 1,2,3,4,2]
        //[to, t1, t2, t2, t3,t3, t3, t4, t4]
        if(timeStampLog.size()<maxRequests){
            timeStampLog.add(now);
            return true;
        }

        return false;
    }
}
