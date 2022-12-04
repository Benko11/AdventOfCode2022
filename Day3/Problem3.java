package Day3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem3 {
    public static String[] halve(String rucksack) throws Exception {
        final String[] halves = new String[2];    
        final int rucksackLength = rucksack.length();
        if (rucksackLength % 2 != 0) {
            throw new Exception("Rucksack is invalid");
        }
        
        halves[0] = rucksack.substring(0, rucksackLength / 2);
        halves[1] = rucksack.substring(rucksackLength / 2);
        return halves;
    }

    public static Set<Character> findCommonItems(String half1, String half2) {
        Set<Character> half1Set = half1.chars().mapToObj(item -> (char) item).collect(Collectors.toSet());
        Set<Character> half2Set = half2.chars().mapToObj(item -> (char) item).collect(Collectors.toSet());
        half1Set.retainAll(half2Set);

        return half1Set;
    }

    public static Integer rate(Character item) {
        if (item >= 'a' && item <= 'z') {
            return item - 96;
        }

        if (item >= 'A' && item <= 'Z') {
            return item - 38; 
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        Integer score = 0;
        try {
            File file = new File("list3.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] halves = halve(line);
                score += findCommonItems(halves[0], halves[1]).stream().map(item -> rate(item)).reduce(0, (acc, item) -> acc + item);
            }

            System.out.println(score);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
