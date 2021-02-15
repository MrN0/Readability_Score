package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // check that the path to the file is specified
        if (args.length != 1) {
            System.out.println("WARNING: Wrong path to the text file.");
            return;
        }

        // read text from file to string
        String text;
        try {
            text = readFileAsString(args[0]);
        } catch (IOException e) {
            System.out.printf("ERROR: Could not read text file %s.%n", args[0]);
            return;
        }

        // create a readability index object
        ReadabilityIndex index = new ReadabilityIndex(text);
        // print text statistics (characters, words, sentences, etc.)
        System.out.println(index.textStatistics());
        // ask the user for desired readability Index
        String input = getTypeOfScoreToCalculate();
        // print result
        printScore(index, input);
    }

    /**
     * This method reads the file as a string
     *
     * @param filePath      the {@link String} file path
     * @return              the {@link String} with text from a file
     * @throws IOException  if an I/O error occurs reading from the stream
     */
    private static String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * This method asks the user for the desired type of score to calculate
     *
     * @return      the {@link String} type of score
     */
    private static String getTypeOfScoreToCalculate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");

        String input = scanner.nextLine();
        while (!input.matches("ARI|FK|SMOG|CL|all")) {
            System.out.println("Wrong input. Try agan:");
            input = scanner.nextLine();
        }
        
        scanner.close();
        return input;
    }

    /**
     * This method prints the desired type of score
     *
     * @param index         the {@link ReadabilityIndex} object that performs calculations
     * @param scoreType     the {@link String} type of score
     */
    private static void printScore(ReadabilityIndex index, String scoreType) {
        switch (scoreType) {
            case "ARI":
                System.out.println(index.getARI());
                break;
            case "FK":
                System.out.println(index.getFK());
                break;
            case "SMOG":
                System.out.println(index.getSMOG());
                break;
            case "CL":
                System.out.println(index.getCL());
                break;
            case "all":
                System.out.println(index.getARI());
                System.out.println(index.getFK());
                System.out.println(index.getSMOG());
                System.out.println(index.getCL());
                break;
            default:
                break;
        }
    }
}
