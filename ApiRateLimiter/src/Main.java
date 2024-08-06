import DOS.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        ApiRateLimiter apiRateLimiter = new ApiRateLimiter(60, 10);
        Request request = new Request(1, "abc");

        Map<String, Integer> clientRequestFrequency =
                Map.of("apiKey1", 5 ,"apiKey2", 10, "apiKey3", 12);
        /*
        for(String clientId : clientRequestFrequency.keySet()){
            int freq = clientRequestFrequency.get(clientId);
            for (int i = 0; i < freq; i++) {
                Request requst = new Request(i, clientId);
                boolean isAllowed = apiRateLimiter.isAllowed(requst);

                if (isAllowed)
                    System.out.printf("Request %d allowed for clientId %s%n", i, clientId);
                else
                    System.out.printf("Request %d is not allowed for clientId %s%n", i, clientId);

            }
        }



        PerCustomerApiRateLimter perCustomerApiRateLimter = new PerCustomerApiRateLimter(60,10);
        for(String clientId : clientRequestFrequency.keySet()){
            int freq = clientRequestFrequency.get(clientId);
            for (int i = 0; i < freq; i++) {
                Request requst = new Request(i, clientId);
                boolean isAllowed = perCustomerApiRateLimter.isAllowed(requst);

                if (isAllowed)
                    System.out.printf("Request %d allowed for clientId %s%n", i, clientId);
                else
                    System.out.printf("Request %d is not allowed for clientId %s%n", i, clientId);

            }
        }

         */

        SlidingWindowApiRateLimiter slidingWindowApiRateLimiter = new SlidingWindowApiRateLimiter(30, 10);

        // [0, 1,2,3,4,2]
        //[to, t1, t2, t2, t3,t3, t3, t4, t4]

        List<Integer> requests = new ArrayList<>(Arrays.asList(0,1,2,3,4,2));

        for(int i=0;i<30;i++){
            Request request1 = new Request(i,"apiKey" );
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int j = 0; j < i; j++) {
                boolean isAllowed = slidingWindowApiRateLimiter.isAllowed(request1);
                if(isAllowed){
                    System.out.printf("Request allowed at time %d\n", i);
                }
                else{
                    System.out.printf("Request is not allowed at time %d\n", i);

                }

            }

        }



    }
}