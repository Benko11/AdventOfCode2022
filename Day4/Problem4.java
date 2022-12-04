package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem4 {
    public static Integer[] parseLine(String line) {
        Pattern pattern = Pattern.compile("([0-9]*)-([0-9]*),([0-9]*)-([0-9]*)");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            return null;
        }

        Integer[] parsedNumbers = {
            Integer.valueOf(matcher.group(1)),
            Integer.valueOf(matcher.group(2)),
            Integer.valueOf(matcher.group(3)),
            Integer.valueOf(matcher.group(4))
        };

        return parsedNumbers;
    }

    public static boolean isIncluded(Integer[] parsedNumbers) {
        final Integer a = parsedNumbers[0];
        final Integer b = parsedNumbers[1];
        final Integer c = parsedNumbers[2];
        final Integer d = parsedNumbers[3];

        return (a <= c && b >= d) || (c <= a && d >= b);
    }

    public static boolean isIncludedAtAll(Integer[] parsedNumbers) {
        final Integer a = parsedNumbers[0];
        final Integer b = parsedNumbers[1];
        final Integer c = parsedNumbers[2];
        final Integer d = parsedNumbers[3];

        // 2 11
        // 12 16
        // 

        return !(a > d || b < c);
    }

    public static void main(String[] args) {
        int score = 0;

        try {
            File file = new File("./Day4/list4.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                final Integer[] values = parseLine(line);
                score += isIncludedAtAll(values) ? 1 : 0;
            }
            scanner.close();
            System.out.println(score);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
