import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class WordNetTest {
    private WordNet wordNetSimple;
    private WordNet wordNet;
    private WordNet wordNet1;
    private WordNet wordNet2;

    @Before
    public void setUp() throws Exception {
        wordNetSimple = new WordNet("src/test/resources/synsets.txt", "src/test/resources/hypernyms.txt");
        wordNet = new WordNet("src/test/resources/wordnet/synsets.txt", "src/test/resources/wordnet/hypernyms.txt");
        wordNet1 = new WordNet("src/test/resources/synsets1.txt", "src/test/resources/hypernyms1.txt");
        wordNet2 = new WordNet("src/test/resources/synsets2.txt", "src/test/resources/hypernyms2.txt");
    }

    @Test
    public void nouns() throws Exception {
        Set<String> nouns = new HashSet<>();

        assertNotEquals(nouns, wordNetSimple.nouns());
        nouns.add("a");
        nouns.add("b");
        nouns.add("c");
        assertEquals(nouns, wordNetSimple.nouns());
    }

    @Test
    public void isNoun() throws Exception {
        assertTrue(wordNetSimple.isNoun("a"));
        assertTrue(wordNetSimple.isNoun("b"));
        assertFalse(wordNetSimple.isNoun("d"));
    }

    @Test
    public void calc() throws Exception {
        assertEquals(4, wordNet1.calc(2,7, true));
        assertEquals(5, wordNet1.calc(2,11, true));
        assertEquals(4, wordNet1.calc(3,11, true));
        assertEquals(1, wordNet1.calc(12,10, true));
        assertEquals(0, wordNet1.calc(12,12, true));

        assertEquals(2, wordNet2.calc(1,5, true));

    }

    @Test
    public void sap() throws Exception {

    }
}