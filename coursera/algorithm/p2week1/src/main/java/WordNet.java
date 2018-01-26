import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordNet {

    private Digraph digraph;
    private Map<Integer, Entry> map;
    private Set<String> nouns;

    private static class Entry {

        private Integer id;
        private List<String> synset;
        private String gloss;

        public Entry(String string) {
            String[] splitedString;

            if (string == null) {
                throw new IllegalArgumentException("Argument shouldn't be null!");
            }

            splitedString = string.split(",", 3);
            if (splitedString.length != 3) {
                throw new IllegalArgumentException("Argument shouldn't be null!");
            }

            this.id = Integer.valueOf(splitedString[0]);
            this.synset = Arrays.asList(splitedString[1].split("\\s"));
            this.gloss = splitedString[2];
        }
    }

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        loadSynsets(synsets);
        digraph = new Digraph(map.size());
        Map<Integer, List<Integer>> synsetToHypernyms = loadHypernyms(hypernyms);
        addEdges(synsetToHypernyms);
    }


    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Argument shouldn't be null!");
        }
        return nouns.contains(word);
    }

    private int[] idByNoun(String noun) {
        return new int[] {};
    }

    private String nounById(int id) {
        return "";
    }


    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Arguments shouldn't be null and should be nouns!");
        }

        int idA = 1;
        int idB = 2;
        int dist[][] = new int[2][map.size()];
        int sap = calc(idA, idB, dist);

        return dist[0][sap] + dist[1][sap];
    }


    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Arguments shouldn't be null and should be nouns!");
        }

        int idA = 1;
        int idB = 2;
        int dist[][] = new int[2][map.size()];
        int sap = calc(idA, idB, dist);

        return "";
    }

    private void loadSynsets(String filename) {
        map = new HashMap<>();
        nouns = new HashSet<>();
        if (filename == null) {
            throw new IllegalArgumentException("Synsets' source filename shouldn't be null!");
        }

        In in = new In(filename);
        while (!in.isEmpty()) {
            Entry entry = new Entry(in.readLine());
            map.put(entry.id, entry);
            nouns.addAll(entry.synset);
        }
        in.close();
    }

    private Map<Integer, List<Integer>> loadHypernyms(String filename) {
        Map<Integer, List<Integer>> synsetToHypernyms = new HashMap<>();

        if (filename == null) {
            throw new IllegalArgumentException("Hypernyms' source filename shouldn't be null!");
        }

        In in = new In(filename);
        while (!in.isEmpty()) {
            String[] splitedString = in.readLine().split(",");
            Integer synsetId = Integer.valueOf(splitedString[0]);
            List<Integer> hypernyms = synsetToHypernyms.containsKey(synsetId) ? synsetToHypernyms.get(synsetId) : new ArrayList<Integer>();

            for (int i = 1; i < splitedString.length; i++) {
                hypernyms.add(Integer.valueOf(splitedString[i]));
            }
            synsetToHypernyms.put(synsetId, hypernyms);
        }
        in.close();

        return synsetToHypernyms;
    }

    private void addEdges(Map<Integer, List<Integer>> synsetToHypernyms) {
        for (Map.Entry<Integer, List<Integer>> entry : synsetToHypernyms.entrySet()) {
            for (Integer hypernym : entry.getValue()) {
                digraph.addEdge(entry.getKey(), hypernym);
            }
        }
    }

    private int calc(int idA, int idB, int[][] dist) {
        Queue<Integer> queue = new Queue<Integer>();
        int minDistance, sap;

        dist[0][idA] = 1;
        dist[1][idB] = 1;
        sap = -1;
        minDistance = map.size() + 1;

        queue.enqueue(idA);
        while (!queue.isEmpty()) {
            int currentVertice = queue.dequeue();

            for (int v : digraph.adj(currentVertice)) {
                if (dist[0][v] == 0) {
                    dist[0][v] = dist[0][currentVertice] + 1;
                    queue.enqueue(v);
                }
            }
        }

        queue.enqueue(idB);
        while (!queue.isEmpty()) {
            int currentVertice = queue.dequeue();
            if (dist[0][currentVertice] > 0) {
                int distance = dist[0][currentVertice] + dist[1][currentVertice] - 2;

                if (minDistance > distance) {
                    sap = currentVertice;
                    minDistance = distance;
                }
            }

            for (int v : digraph.adj(currentVertice)) {
                if (dist[1][v] == 0) {
                    dist[1][v] = dist[1][currentVertice] + 1;
                    queue.enqueue(v);
                }
            }
        }

        return sap;
    }


    // do unit testing of this class
    public static void main(String[] args) {
        System.out.println("");
    }
}