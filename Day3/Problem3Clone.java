package Day3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem3Clone {
    public static Set<Character> findCommonItems(String part1, String part2, String part3) {
        Set<Character> part1Set = part1.chars().mapToObj(item -> (char) item).collect(Collectors.toSet());
        Set<Character> part2Set = part2.chars().mapToObj(item -> (char) item).collect(Collectors.toSet());
        Set<Character> part3Set = part3.chars().mapToObj(item -> (char) item).collect(Collectors.toSet());
        part1Set.retainAll(part2Set);
        part1Set.retainAll(part3Set);

        return part1Set;
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
                String line1 = scanner.nextLine();
                String line2 = scanner.nextLine();
                String line3 = scanner.nextLine();
                score += findCommonItems(line1, line2, line3).stream().map(item -> rate(item)).reduce(0, (acc, item) -> acc + item);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
