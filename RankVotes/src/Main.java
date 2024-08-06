import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> rankLists = new ArrayList<>(Arrays.asList("ABC", "ACB", "BAC", "BAC", "BAC"));
        RankVotes rankVotes = new RankVotes(rankLists);
        System.out.println(rankVotes.getOrder());
    }

    private static class RankVotes {
        List<String> rankLists; // ["ABC", "ACB", "BAC", "BAC", "BAC"]

        // TeamRanks{team=A, rankList=[2, 3, 0]}
        //TeamRanks{team=B, rankList=[3, 1, 1]}
        // TeamRanks{team=C, rankList=[0, 1, 4]}
        List<TeamRanks> teamRanksList;


        public RankVotes(List<String> rankLists) {
            this.rankLists = rankLists;
            this.teamRanksList = new ArrayList<>();
        }

        public String getOrder() {
            Map<Character, int[]> teamRanksMap = new HashMap<>();

            for (String rankList : rankLists) {
                int numTeams = rankList.length();
                for (int j = 0; j < numTeams; j++) {
                    char team = rankList.charAt(j);
                    int[] rankOrder = teamRanksMap.getOrDefault(team, new int[numTeams]);
                    rankOrder[j] = rankOrder[j] + 1;
                    teamRanksMap.put(team, rankOrder);
                }
            }

            this.teamRanksList = new ArrayList<>();

            for (Character team : teamRanksMap.keySet()) {
                TeamRanks teamRanks = new TeamRanks(team, teamRanksMap.get(team));
                teamRanksList.add(teamRanks);
            }

            Collections.sort(teamRanksList, new Comparator<TeamRanks>() {
                @Override
                public int compare(TeamRanks o1, TeamRanks o2) {
                    // TeamRanks{team=A, rankList=[2, 3, 0]}
                    //TeamRanks{team=B, rankList=[3, 1, 1]}
                    // TeamRanks{team=C, rankList=[0, 1, 4]}
                    //sort descending order interms of rankFrequency

                    int[] rankList1 = o1.getRankList();
                    int[] rankList2 = o2.getRankList();
                    for (int i = 0; i < rankList1.length; i++) {
                        if (rankList1[i] > rankList2[i])
                            return 1;
                        else if (rankList1[i] < rankList2[i])
                            return -1;
                    }
                    return Character.compare(o1.getTeam(), o2.getTeam());
                }
            });

            StringBuilder result = new StringBuilder();
            for (TeamRanks teamRanks : teamRanksList) {
                char team = teamRanks.getTeam();
                result.append(team);
            }

            return result.toString();
        }
    }

    private static class TeamRanks {
        private final char team;
        private final int[] rankList;

        public TeamRanks(char team, int[] rankList) {
            this.team = team;
            this.rankList = rankList;
        }

        public char getTeam() {
            return team;
        }

        public int[] getRankList() {
            return rankList;
        }
    }
}
