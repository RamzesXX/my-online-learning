import java.util.Arrays;

public class SentimentAlalysis {

    public static final int NO_OPINION = 0;
    public static final int POSITIVE_OPINION = +1;
    public static final int NEGATIVE_OPINION = -1;

    // Tip: labeled continue can be used when iterating featureSet + convert review to lower-case
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords,
                                          String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length]; // output

        review = review.toLowerCase().replaceAll("\\s+", " ");

        for (int i = 0; i < featureSet.length; i++) {
            for (String feature : featureSet[i]) {
                featureOpinions[i] = getOpinionOnFeature(review.toLowerCase(), feature, posOpinionWords,
                        negOpinionWords);
                if (featureOpinions[i] != NO_OPINION) {
                    break;
                }
            }
        }

        return featureOpinions;
    }

    // First invoke checkForWasPhrasePattern and
    // if it cannot find an opinion only then invoke checkForOpinionFirstPattern
    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords,
                                           String[] negOpinionWords) {
        int opinion = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        if (opinion == NO_OPINION) {
            opinion = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
        }

        return opinion;
    }

    private static String extractNextWord(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) < 'a' || string.charAt(i) > 'z') {
                return string.substring(0, i);
            }
        }

        return string;
    }

    private static String extractPrevWord(String string) {
        for (int i = string.length() - 1; i >= 0; i--) {
            if (string.charAt(i) < 'a' || string.charAt(i) > 'z') {
                return string.substring(i + 1);
            }
        }

        return string;
    }

    private static boolean opinionExists(String[] opinionsWords, String opinionsWord) {
        for (String word : opinionsWords) {
            if (opinionsWord.equals(word)) {
                return true;
            }
        }

        return false;
    }

    // Tip: Look at String API doc. Methods like indexOf, length, substring(beginIndex), startsWith can come into play
    // Return 1 if positive opinion found, -1 for negative opinion, 0 for no opinion
    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords,
                                                String[] negOpinionWords) {
        int index = -1;
        String pattern = feature + " was ";

        while ((index = review.indexOf(pattern, index)) > -1) {
            index += pattern.length();
            String opinionWord = extractNextWord(review.substring(index));
            if (!opinionWord.isEmpty()) {
                if (opinionExists(posOpinionWords, opinionWord)) {
                    return POSITIVE_OPINION;
                }
                if (opinionExists(negOpinionWords, opinionWord)) {
                    return NEGATIVE_OPINION;
                }
            }
        }

        return NO_OPINION;
    }

    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        // Extract sentences as feature might appear multiple times.
        // split() takes a regular expression and "." is a special character
        // for regular expression. So, escape it to make it work!!
        int index = 0;
        String pattern = " " + feature;

        while ((index = review.indexOf(pattern, index)) > -1) {
            String opinionWord = extractPrevWord(review.substring(0, index));
            index += pattern.length();
            if (!opinionWord.isEmpty()) {
                if (opinionExists(posOpinionWords, opinionWord)) {
                    return POSITIVE_OPINION;
                }
                if (opinionExists(negOpinionWords, opinionWord)) {
                    return NEGATIVE_OPINION;
                }
            }

        }

        return NO_OPINION;
    }

    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";

        //String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";

        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}};
        String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome",
                "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}