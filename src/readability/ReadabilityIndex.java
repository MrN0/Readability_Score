package readability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains methods that calculate the readability of the text using the following indexes:
 * <ul>
 *     <li>Automated readability index (ARI)</li>
 *     <li>Flesch–Kincaid readability tests (FK)</li>
 *     <li>Simple Measure of Gobbledygook (SMOG)</li>
 *     <li>Coleman–Liau index (CL)</li>
 * </ul>
 */
public class ReadabilityIndex {
    // text
    private final String text;
    // the number of characters in the text
    private final int characters;
    // the number of words in the text
    private final int words;
    // the number of sentences in the text
    private final int sentences;
    // the number of syllables in the text
    private int syllables;
    // the number of polysyllables in the text
    private int polysyllables;

    /**
     * Constructs this object
     *
     * @param text      the {@link String} text
     */
    public ReadabilityIndex(String text) {
        this.text = text;
        this.characters = countCharacters(text);
        this.words = countWords(text);
        this.sentences = countSentences(text);
        this.syllables = 0;
        this.polysyllables = 0;
        countSyllables(text);
    }

    /**
     * This method calculates the automated readability index (ARI)
     *
     * @return  the {@link String} result of ARI
     */
    public String getARI() {
        double index = 4.71 * ((double) characters / words) + 0.5 * ((double) words / sentences) - 21.43;
        return String.format("Automated Readability Index: %.2f (about %s-year-olds).%n",
                index, getAgeByScore((int) Math.ceil(index), true));
    }

    /**
     * This method calculates Flesch–Kincaid readability tests (FK)
     *
     * @return  the {@link String} result of FK
     */
    public String getFK() {
        double index = 0.39 * ((double) words / sentences) + 11.8 * ((double) syllables / words) - 15.59;
        return String.format("Flesch–Kincaid readability tests: %.2f (about %s-year-olds).%n",
                index, getAgeByScore((int) Math.ceil(index), true));
    }

    /**
     * This method calculates Simple Measure of Gobbledygook (SMOG)
     *
     * @return  the {@link String} result of SMOG
     */
    public String getSMOG() {
        double index = 1.043 * Math.sqrt(polysyllables * (30D / sentences)) + 3.1291;
        return String.format("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).%n",
                index, getAgeByScore((int) Math.ceil(index), true));
    }

    /**
     * This method calculates Coleman–Liau index (CL)
     *
     * @return  the {@link String} result of CL
     */
    public String getCL() {
        double l = (double) characters / words * 100;
        double s = (double) sentences / words * 100;
        double index = 0.0588 * l - 0.296 * s - 15.8;
        return String.format("Coleman–Liau index: %.2f (about %s-year-olds).%n",
                index, getAgeByScore((int) Math.ceil(index), false));
    }

    /**
     * This method returns a string with text statistics:
     * <ul>
     *     <li>text itself</li>
     *     <li>number of words</li>
     *     <li>number of sentences</li>
     *     <li>number of characters</li>
     *     <li>number of syllables</li>
     *     <li>number of polysyllables</li>
     * </ul>
     *
     * @return  the {@link String} with text statistics
     */
    public String textStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("The text is:").append("\n");
        sb.append(text).append("\n\n");
        sb.append("Words: ").append(words).append("\n");
        sb.append("Sentences: ").append(sentences).append("\n");
        sb.append("Characters: ").append(characters).append("\n");
        sb.append("Syllables: ").append(syllables).append("\n");
        sb.append("Polysyllables: ").append(polysyllables).append("\n");
        return sb.toString();
    }

    /**
     * This method counts the number of characters in a given text
     *
     * @param text  the {@link String} given text
     * @return      the number of characters
     */
    private int countCharacters(String text) {
        return  text.replaceAll("[ ]|\\n|\\t","").split("").length;
    }

    /**
     * This method counts the number of words in a given text
     *
     * @param text  the {@link String} given text
     * @return      the number of words
     */
    private int countWords(String text) {
        return text.split("\\s").length;
    }

    /**
     * This method counts the sentences in a given text
     *
     * @param text  the {@link String} given text
     * @return      the number of sentences
     */
    private int countSentences(String text) {
        return  text.split("[.!?]").length;
    }

    /**
     * This method counts the number of syllables and polysyllables in a given text
     *
     * @param text  the {@link String} given text
     */
    private void countSyllables(String text) {
        String[] wordsArray = text.replaceAll("e?[,.!?]|e\\b", "").split("\\s");

        Pattern VOWELS_PATTERN = Pattern.compile("(?i)[aeiouy]+");

        for (String word : wordsArray) {
            Matcher countSyllablesMatcher = VOWELS_PATTERN.matcher(word);

            int split = 0;
            while (countSyllablesMatcher.find()) {
                split++;
            }

            if (split > 2) {
                polysyllables++;
            }
            syllables += split;
        }
    }

    /**
     * This method returns the appropriate age for the given score
     *
     * @param score         readability index score
     * @param range         range of years (true - minimal range, false maximal range)
     * @return              the {@link String} appropriate age for a given score
     */
    private static String getAgeByScore(int score, boolean range) {
        switch (score) {
            case 1:
                return range ? "5" : "6";
            case 2:
                return range ? "6" : "7";
            case 3:
                return range ? "7" : "9";
            case 4:
                return range ? "9" : "10";
            case 5:
                return range ? "10" : "11";
            case 6:
                return range ? "11" : "12";
            case 7:
                return range ? "12" : "13";
            case 8:
                return range ? "13" : "14";
            case 9:
                return range ? "14" : "15";
            case 10:
                return range ? "15" : "16";
            case 11:
                return range ? "16" : "17";
            case 12:
                return range ? "17" : "18";
            case 13:
                return range ? "18" : "24";
            default:
                return "24+";
        }
    }
}
