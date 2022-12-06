package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem6 {
    public static boolean repeatedCharacters(String sequence) {
        Set<Character> set = sequence.chars().mapToObj(item -> (char) item).collect(Collectors.toSet());
        return sequence.length() > set.size();
    }

    public static int uniqueSequenceEnd(String line, int size) {
        int spot = -1;
        
        for (int i = 0; i < line.length() - size; i++) {
            if (!repeatedCharacters(line.substring(i, i + size))) {
                return i + size;
            }
        }

        return spot;
    }

    public static void main(String[] args) {
        try {
            File file = new File("./Day6/list6.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(uniqueSequenceEnd(line, 4));
                System.out.println(uniqueSequenceEnd(line, 14));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}