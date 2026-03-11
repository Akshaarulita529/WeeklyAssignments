import java.util.*;

class PlagiarismDetectionSystem {

    HashMap<String, Set<String>> ngramIndex = new HashMap<>();
    int n = 5; // 5-grams

    void addDocument(String docId, String text) {
        List<String> ngrams = generateNgrams(text);
        for (String gram : ngrams) {
            ngramIndex.putIfAbsent(gram, new HashSet<>());
            ngramIndex.get(gram).add(docId);
        }
    }

    List<String> generateNgrams(String text) {
        String[] words = text.split(" ");
        List<String> grams = new ArrayList<>();
        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) sb.append(words[i + j]).append(" ");
            grams.add(sb.toString().trim());
        }
        return grams;
    }

    void analyzeDocument(String docId, String text) {
        List<String> ngrams = generateNgrams(text);
        Map<String, Integer> similarity = new HashMap<>();
        for (String gram : ngrams) {
            if (ngramIndex.containsKey(gram)) {
                for (String id : ngramIndex.get(gram)) {
                    similarity.put(id, similarity.getOrDefault(id, 0) + 1);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : similarity.entrySet()) {
            double percent = entry.getValue() * 100.0 / ngrams.size();
            System.out.println("Similarity with " + entry.getKey() + ": " + percent + "%");
        }
    }

    public static void main(String[] args) {
        PlagiarismDetectionSystem detector = new PlagiarismDetectionSystem();
        detector.addDocument("essay1", "this is a sample essay for testing plagiarism detection");
        detector.analyzeDocument("essay2", "this is a sample essay written for plagiarism testing");
    }
}