import java.util.*;

public class OnlineVoting {
    List<Integer> candidates;
    List<Integer> votingTimeStamps;

    Map<Integer, Integer> timeStampLeadingCandidateMap; 

    Map<Integer, Integer> candidateCumulativeFrequencyMap; 

    int leadingCandidate;


    //[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
    //Output
    // 5 -1 1
    // 10 -1 2
    //15 -2-2
    //

    public OnlineVoting(List<Integer> candidates,List<Integer> votingTimeStamps ) {
        this.candidates = candidates;
        leadingCandidate = -1;
        this.votingTimeStamps = votingTimeStamps;
        candidateCumulativeFrequencyMap = new HashMap<>();
        timeStampLeadingCandidateMap = new HashMap<>();

        for (int i = 0; i < candidates.size(); i++) {
            int candidate = candidates.get(i);
            int votes = candidateCumulativeFrequencyMap.getOrDefault(candidate,0)+1;
            candidateCumulativeFrequencyMap.put(candidate,votes);

            if(votes>= candidateCumulativeFrequencyMap.getOrDefault(leadingCandidate,0)) {
                leadingCandidate = candidate;
                timeStampLeadingCandidateMap.put(votingTimeStamps.get(i), candidate);
            }
            else {
                timeStampLeadingCandidateMap.put(votingTimeStamps.get(i), leadingCandidate);
            }


            }



        }

        //need map to persist cumulative frequency, need it for every candidate

    public int getleadingCandidate(int timeStamp) {
        //while retrieving get nearestTimestamp which belogns voting timestamps < timeStamp

        //need it in order ,we need sortedArray
        int index = Collections.binarySearch(votingTimeStamps, timeStamp);
        int nearestTimestamp;
        if (index < 0){ // (-(insertion point) - 1).{ -n-1 ->n+1-2
            index = -1*index-2;
            nearestTimestamp = votingTimeStamps.get(index);

        }
        else
            nearestTimestamp = votingTimeStamps.get(index);

        return  timeStampLeadingCandidateMap.get(nearestTimestamp);


    }


}
