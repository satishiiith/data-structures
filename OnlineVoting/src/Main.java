import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> persons = new ArrayList<>(Arrays.asList(0,1,1,0,0,1,0)),
                timeStamps = new ArrayList<>(Arrays.asList(0, 5, 10, 15, 20, 25, 30));
        OnlineVoting onlineVoting = new OnlineVoting(persons,timeStamps);
    //Input
        //["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
        //[[[0, 1, 1, 0, 0, 1, 0],
        // [0, 5, 10, 15, 20, 25, 30]],
        // [3], [12], [25], [15], [24], [8]]
        //Output
        //[null, 0, 1, 1, 0, 0, 1]



        System.out.println(onlineVoting.getleadingCandidate(3));
        System.out.println(onlineVoting.getleadingCandidate(12));
        System.out.println(onlineVoting.getleadingCandidate(25));
        System.out.println(onlineVoting.getleadingCandidate(15));
        System.out.println(onlineVoting.getleadingCandidate(24));

    }
}