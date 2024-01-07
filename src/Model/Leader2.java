package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Leader2 {

    private Map<Integer, Integer> scores;

    public Leader2() {
        scores = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        if (!scores.containsKey(playerId)) {
            scores.put(playerId, 0);
        }
        scores.put(playerId, scores.get(playerId) + score);
    }

    public List<Map.Entry<Integer, Integer>> top(int K) {
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : scores.entrySet()) { // O(N)
            minHeap.offer(entry);
            if (minHeap.size() > K) {
                minHeap.poll(); // extract min because we are interested in Top elements
                                // O(logK)
            }
        } // Overall time complexity to execute this foreach loop: O(NlogK)

        List<Map.Entry<Integer, Integer>> topEntries = new ArrayList<>(minHeap);
        Collections.reverse(topEntries); // Reversing the list to get entries in descending order of scores
        return topEntries;

        // Time complexity: O(K) + O(NlogK) = O(NlogK)
    }

    public void reset(int playerId) { 
        scores.put(playerId, 0); 
    }

    /*
     * Space Complexity: O(N + K), where N = total number of players.
     * We are keeping scores of all the players in the map.
     * O(K) is used by the heap in top(K) method.
     */
}
