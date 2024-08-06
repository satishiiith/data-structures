import DOS.Request;

import java.util.HashMap;
import java.util.Map;

public class PerCustomerApiRateLimter {

    int windowInterval;
    int maxRequests;
    Map<String, ApiRateLimiter> customerRateLimiterMap;

    public PerCustomerApiRateLimter(int windowInterval, int maxRequests) {
        this.windowInterval = windowInterval;
        this.maxRequests = maxRequests;
        customerRateLimiterMap = new HashMap<>();
    }

    public boolean isAllowed(Request requst) {
        String clientId = requst.getClientId();
        if(customerRateLimiterMap.containsKey(clientId)){
            ApiRateLimiter apiRateLimiter = customerRateLimiterMap.get(clientId);
            return apiRateLimiter.isAllowed(requst);
        }
        else{
            ApiRateLimiter apiRateLimiter = new ApiRateLimiter(windowInterval, maxRequests);
            customerRateLimiterMap.put(clientId,apiRateLimiter);
            return apiRateLimiter.isAllowed(requst);
        }



    }
}
