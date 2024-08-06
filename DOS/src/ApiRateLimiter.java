    import DOS.Request;

import java.time.Instant;

public class ApiRateLimiter {
    int windowInterval ;
    int maxRequests;

    long windowIndex;
    long servedRequests;
    public ApiRateLimiter(int windowInterval, int maxRequests) {
        this.windowInterval = windowInterval;
        this.maxRequests = maxRequests;
        windowIndex = -1;
        servedRequests =0;
    }


    public boolean isAllowed(Request request) {
        // 2 minuts 120 seconds/ 300 -> belong 0 window
        // 5 miutes 10 seconsd -> 300+10/300- >1
        long currentWindowIndex =  (Instant.now().getEpochSecond()/windowInterval);

        if(currentWindowIndex==windowIndex){
            if(servedRequests >=maxRequests)
                return  false;
            else{
                servedRequests++;
                return  true;

            }
        }
        windowIndex = currentWindowIndex;
        servedRequests = 1;


        return  true;
    }
}
