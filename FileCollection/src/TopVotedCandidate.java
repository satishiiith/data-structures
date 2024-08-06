import java.util.HashMap;
import java.util.Map;

public class TopVotedCandidate {

    // need datstructure to search these keys in order . get should take log(n)
    //insert sholud take log(n)
    // balanced binary search tree

    // and we need a map
    int[] times;
    Map<Integer,Map<Integer,Integer>> timeFrequency = new HashMap<>();
    Map<Integer , Integer>  timeLeadMap = new HashMap<>();

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        for (int i = 0; i < times.length; i++) {



        }


    }

    public int q(int t) {
        return 0;
    }
}
