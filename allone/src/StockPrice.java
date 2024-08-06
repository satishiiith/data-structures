import java.util.*;


//Implement the StockPrice class:
//
//StockPrice() Initializes the object with no price records.
//void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
//int current() Returns the latest price of the stock.
//int maximum() Returns the maximum price of the stock.
//int minimum() Returns the minimum price of the stock.

public class StockPrice {

    TreeSet<TimeStampPrice> orderedByPricesSet;
    Map<Integer, Integer> timeStampPriceMap;
    int currentTimestamp;
    // if need to update
    // current Timestamp
    // to get max ? how do you get

    public StockPrice() {

        this.timeStampPriceMap = new HashMap<>();
        this.orderedByPricesSet = new TreeSet<>(Comparator.comparing(TimeStampPrice::getPrice).
                thenComparing(TimeStampPrice::getTimeStamp));
        currentTimestamp = 0;
    }

    public void update(int timeStamp, int price){
        if(timeStampPriceMap.containsKey(timeStamp)){
            orderedByPricesSet.remove(new TimeStampPrice(timeStamp, timeStampPriceMap.get(timeStamp)));
            timeStampPriceMap.remove(timeStamp);
        }
        timeStampPriceMap.put(timeStamp,price);
        orderedByPricesSet.add(new TimeStampPrice(timeStamp,price));
        currentTimestamp = Math.max(timeStamp,currentTimestamp);
    }

    public  int current(){
        return timeStampPriceMap.get(currentTimestamp);



    }


    public  int minimum(){
        return orderedByPricesSet.first().getPrice();
    }

    

    public  int maximum(){
        return orderedByPricesSet.last().getPrice();

    }




    private static  class  TimeStampPrice{
        int timeStamp;
        int price;

        public TimeStampPrice(int timeStamp, int price) {
            this.timeStamp = timeStamp;
            this.price = price;
        }

        public int getTimeStamp() {
            return timeStamp;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TimeStampPrice that = (TimeStampPrice) o;
            return timeStamp == that.timeStamp && price == that.price;
        }

        @Override
        public int hashCode() {
            return Objects.hash(timeStamp, price);
        }
    }

}
