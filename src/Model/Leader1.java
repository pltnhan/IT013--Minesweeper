package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leader1{
    private Map<Integer, Integer> scores;

    public Leader1() {
        scores = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        if (!scores.containsKey(playerId)) {
            scores.put(playerId, 0);
        }
        scores.put(playerId, scores.get(playerId) + score);
    }

    public int top(int K) {
        List<Integer> values = new ArrayList<Integer>(scores.values());
        Collections.sort(values, Collections.reverseOrder());
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += values.get(i);
        }
        return sum;
    }

    public void reset(int playerId) {

        scores.put(playerId, 0);
    }

}
