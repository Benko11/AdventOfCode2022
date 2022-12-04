package Day1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Integer sum = 0;

        List<Integer> consumers = new ArrayList<>();

        try {
            var fileObj = new File("list1.txt");
            var reader = new Scanner(fileObj);
            while (reader.hasNextLine()) {
                var line = reader.nextLine();
                if (line.trim().isEmpty()) {
                    consumers.add(sum);
                    sum = 0;
                    continue;
                }

                sum += Integer.valueOf(line);
            }
            
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }

        Collections.sort(consumers, Collections.reverseOrder());
        System.out.println(consumers.get(0) + consumers.get(1) + consumers.get(2));
    }
}