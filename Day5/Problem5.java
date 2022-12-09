package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem5 {
    public static void parseFile(Scanner scanner) {
        List<String> cratesSchema = new ArrayList<>();
        List<String> instructions = new ArrayList<>();

        Boolean addingCrates = true;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.trim().equalsIgnoreCase("")) {
                addingCrates = false;
                continue;
            }

            if (addingCrates) {
                cratesSchema.add(line);
                continue;
            }
            
            instructions.add(line);
        }

        List<List<Character>> crates = parseCrates(cratesSchema);
        List<List<Integer>> moves = parseMoves(instructions);

        for (var movement: moves) {
            crates = moveNew(crates, movement);
        }

        System.out.println(topCrates(crates));
    }

    public static List<List<Character>> parseCrates(List<String> crates) {
        List<String> placements = crates.stream().filter(item -> crates.indexOf(item) < crates.size() - 1).toList();

        List<List<Character>> rows = new ArrayList<>();
        placements.stream().map(Problem5::parseRow).forEach(rows::add);
        List<List<Character>> columns = transpose(rows);

        return columns;
    }

    public static List<List<Character>> transpose(List<List<Character>> rows) {
        List<List<Character>> columns = new ArrayList<>();
        for (int i = 0; i < rows.get(0).size(); i++) {
            List<Character> column = new ArrayList<>();
            for (var item: rows) {
                column.add(item.get(i));
            }

            columns.add(column);
        }

        return columns;
    }

    public static List<Character> parseRow(String placement) {
        List<Character> placementList = new ArrayList<>();
        placement.chars().forEach(item -> placementList.add((char) item));
        List<Character> necessities = IntStream.range(1, placementList.size()).filter(n -> n % 4 == 1).mapToObj(placementList::get).toList();

        return necessities.stream().map(item -> item == ' ' ? null : item).toList();
    }

    public static List<List<Integer>> parseMoves(List<String> instructions) {
        Pattern pattern = Pattern.compile("move ([0-9]*) from ([0-9]*) to ([0-9]*)");
        List<List<Integer>> numberMoves = new ArrayList<>();

        instructions.stream().map(item -> {
            Matcher matcher = pattern.matcher(item);
            matcher.matches();

            return Arrays.asList(new Integer[]{
                Integer.valueOf(matcher.group(1)),
                Integer.valueOf(matcher.group(2)),
                Integer.valueOf(matcher.group(3))
            });
        }).forEach(numberMoves::add);

        return numberMoves;
    }

    public static List<List<Character>> move(List<List<Character>> crates, List<Integer> moves) {
        int howMany = moves.get(0);
        int from = moves.get(1) - 1;
        int to = moves.get(2) - 1;

        for (int i = 0; i < howMany; i++) {
            Character value = top(crates.get(from));
            int fromIndex = topIndex(crates.get(from)) + 1;
            int toIndex = topIndex(crates.get(to));

            crates.get(from).set(fromIndex, null);

            if (toIndex != -1) {
                crates.get(to).set(toIndex, value);
            } else {
                crates.get(to).add(0, value);
            }
        }

        return crates;
    }

    public static List<List<Character>> moveNew(List<List<Character>> crates, List<Integer> moves) {
        int howMany = moves.get(0);
        int from = moves.get(1) - 1;
        int to = moves.get(2) - 1;

        var values = new ArrayList<Character>();
        for (int i = 0; i < howMany; i++) {
            int fromIndex = topIndex(crates.get(from));
            System.out.println(fromIndex);            
            values.add(0, crates.get(from).get(fromIndex));
            crates.get(from).set(fromIndex, null);
        }

        System.out.println(values);
        for (var v: values) {
            int toIndex = topIndex(crates.get(to)) + 1;
            if (toIndex >= crates.get(to).size()) {
                crates.get(to).add(0, v);
            } else {
                crates.get(to).set(toIndex, v);
            }
        }

        System.out.println(crates);

        return crates;
    }

    public static Character top(List<Character> reversedColumn) {
        List<Character> list =  reversedColumn.stream()
                                    .filter(item -> item != null)
                                    .toList();
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    public static int topIndex(List<Character> reversedColumn) {
        int index = -1;
        for (int i = 0; i < reversedColumn.size(); i++) {
            if (reversedColumn.get(i) == null) {
                index = i;
            }
        }

        return index;
    }

    public static String topCrates(List<List<Character>> crates) {
        List<Character> tops = new ArrayList<>();
        crates.stream().map(Problem5::top).filter(item -> item != null).forEach(tops::add);

        return tops.stream().map(Object::toString).collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        try {
            File file = new File("./Day5/list5-test.txt");
            Scanner scanner = new Scanner(file);

            parseFile(scanner);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }    
}
