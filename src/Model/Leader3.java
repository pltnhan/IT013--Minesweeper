package Model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Leaderboard class keeps track of scores of players in a game,
 * providing functions to add scores, retrieve the top scores, and reset a
 * player's score.
 */
public class Leader3 {
    // A HashMap to store the playerId and their corresponding scores.
    private final Map<Integer, Integer> playerScores = new HashMap<>();

    // A TreeMap to maintain sorted scores (in descending order) along with their
    // frequency.
    private final TreeMap<Integer, Integer> sortedScores = new TreeMap<>(java.util.Collections.reverseOrder());

    public void addScore(int playerId, int score) {
        // Merge the new score into the existing one or put if absent, then get the
        // updated score
        int updatedScore = playerScores.merge(playerId, score, Integer::sum);

        // If the score is an update (not the first score), reduce the frequency of the
        // old score
        if (updatedScore != score) {
            sortedScores.merge(updatedScore - score, -1, Integer::sum);
        }

        // Add or update the frequency of the new score
        sortedScores.merge(updatedScore, 1, Integer::sum);
    }

    public List<Map.Entry<Integer, Integer>> top(int K) {
        int remaining = K;
        List<Map.Entry<Integer, Integer>> topEntries = new ArrayList<>();

        // In-order traversal over the scores in the TreeMap
        for (Map.Entry<Integer, Integer> entry : sortedScores.entrySet()) { // amortized complexity of getting next node
                                                                            // in an inorder traversal
            // in red-black tree is O(1). TreeMap is implemented using Red-Black tree which
            // is a balanced binary search tree.
            int score = entry.getKey(); // getting key from entry is O(1)
            int numberOfPlayers = entry.getValue(); // getting value from an entry is O(1)
            int playersToAdd = Math.min(remaining, numberOfPlayers);

            for (int i = 0; i < playersToAdd; i++) {
                topEntries.add(new AbstractMap.SimpleEntry<>(score, playersToAdd));
            }

            remaining -= playersToAdd;
            if (remaining <= 0) {
                break;
            }
        } // Overall time complexity of this loop: O(K)

        return topEntries;
        // Overall time complexity of top(K) method is O(K)
    }

    public void reset(int playerId) {
        // Remove the player's score
        int score = playerScores.remove(playerId);

        // Decrement the frequency of the player's score and remove it if it reaches
        // zero
        if (score != 0 && sortedScores.merge(score, -1, Integer::sum) == 0) {
            sortedScores.remove(score);
        }
    }
}
