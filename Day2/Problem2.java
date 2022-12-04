import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem2 {
    public static int findScore(char left, char right) {
        int score = 0;
        if (right == 'X') {
            score += 1;

            if (left == 'A') score += 3;
            if (left == 'C') score += 6;
            return score;
        }

        if (right == 'Y') {
            score += 2;

            if (left == 'A') score += 6;
            if (left == 'B') score += 3;
            return score;
        }

        if (right == 'Z') {
            score += 3;

            if (left == 'B') score += 6;
            if (left == 'C') score += 3;

            return score;
        }

        return 0;
    }

    public static int findActualScore(char left, char right) {
        int score = 0;

        if (right == 'X') {
            if (left == 'A') score += 3;
            if (left == 'B') score += 1;
            if (left == 'C') score += 2;

            return score;
        }

        if (right == 'Y') {
            if (left == 'A') score += 1;
            if (left == 'B') score += 2;
            if (left == 'C') score += 3;
            score += 3;

            return score;
        }

        if (right == 'Z') {
            if (left == 'A') score += 2;
            if (left == 'B') score += 3
            if (left == 'C') score += 1;
            score += 6;

            return score;
        }

        return 0;
    }

    public static void main(String[] args) {
        long score = 0;
        try {
            File file = new File("list2.txt");
            Scanner reader =  new Scanner(file);
            while (reader.hasNextLine()) {
                var line = reader.nextLine();
                var values = line.split(" ");
                var scoreToAdd = Long.valueOf(findActualScore(values[0].charAt(0), values[1].charAt(0)));
                score += scoreToAdd;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(score);
    }
}
