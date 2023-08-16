import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static int overlapping_Count = 0;
    static AtomicInteger number = new AtomicInteger(0);
    public static void Day1(){
        List <Integer> resultList = new ArrayList();
        HashMap <String, Integer> caloriesList = new LinkedHashMap<>();
        String keyPrefix = "Elf-";
        int sum = 0;
        int maxNumber = Integer.MIN_VALUE;
        try {
            File file = new File("C:\\Users\\keaga\\IdeaProjects\\Adventodcode\\list");
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()) {
                String token = myReader.nextLine();
                if(token.equals("")) {
                    resultList.add(sum);
                    sum = 0;
                } else {
                    sum += Integer.parseInt(token);
                }
            }
            myReader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
            fileNotFoundException.printStackTrace();
        } finally {
            for (int i=0; i < resultList.size(); i++){
                //System.out.println(resultList.get(i));
                caloriesList.put(keyPrefix + (i+1), resultList.get(i));
            }

            for (String i: caloriesList.keySet()) {
                //System.out.println("Key: " + i + " Value: " + caloriesList.get(i));
                if(caloriesList.get(i) > maxNumber){
                    maxNumber = caloriesList.get(i);
                }
            }

            resultList.sort(Collections.reverseOrder());
            int first, second, third;
            first = resultList.get(0);
            second = resultList.get(1);
            third = resultList.get(2);

            for (Map.Entry<String, Integer> e : caloriesList.entrySet()) {
                if(e.getValue().equals(maxNumber)) {
                    System.out.println("\nThe Elf with the highest calorie is " + e.getKey() + " with a calorie of " + e.getValue());
                }
            }

            for (Map.Entry<String, Integer> e : caloriesList.entrySet()) {
                if(e.getValue().equals(first) || e.getValue().equals(second) || e.getValue().equals(third)) {
                    System.out.println("\nThe Elf with the highest calorie is " + e.getKey() + " with a calorie of " + e.getValue());
                }
            }

            System.out.println("\nThe top three elves are carrying a total of " + (first+second+third) + " calories");
        }
    }
    public static void Day2(int strategy){
        File file = new File("C:\\Users\\keaga\\IdeaProjects\\Adventodcode\\rock_paper_scissor");
        List<String> opponentsMoves = new ArrayList<>();
        List<String> myMoves = new ArrayList<>();
        List<String> outcome = new ArrayList<>();
        int points = 0;
        int loosingPoints, drawPoints, winningPoints;
        loosingPoints = 0;
        drawPoints = 3;
        winningPoints = 6;
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String token = myReader.nextLine();
                opponentsMoves.add(String.valueOf(token.charAt(0)).replaceAll("A", "Rock").replaceAll("B", "Paper").replaceAll("C", "Scissors"));
                myMoves.add(String.valueOf(token.charAt(2)).replaceAll("X", "Rock").replaceAll("Y", "Paper").replaceAll("Z", "Scissors"));
                outcome.add(String.valueOf(token.charAt(2)).replaceAll("X", "Loose").replaceAll("Y", "Draw").replaceAll("Z", "Win"));
                //System.out.println(token);
            }
            myReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        } finally {
            for (int i = 0; i < myMoves.size(); i++) {
                System.out.println("Round " + (i+1));
                if(strategy == 1){
                switch(myMoves.get(i)) {
                    case "Rock":
                        switch (opponentsMoves.get(i)) {
                            case "Rock":
                                points += (1 + drawPoints);
                                break;
                            case "Paper":
                                points += (1 + loosingPoints);
                                break;
                            case "Scissors":
                                points += (1 + winningPoints);
                                break;
                        }
                        System.out.println("You got " + points + " points.");
                        break;
                    case "Paper":
                        switch (opponentsMoves.get(i)) {
                            case "Rock":
                                points += (2 + winningPoints);
                                break;
                            case "Paper":
                                points += (2 + drawPoints);
                                break;
                            case "Scissors":
                                points += (2 + loosingPoints);
                                break;
                        }
                        System.out.println("You got " + points + " points.");
                        break;
                    case "Scissors":
                        switch (opponentsMoves.get(i)) {
                            case "Rock":
                                points += (3 + loosingPoints);
                                break;
                            case "Paper":
                                points += (3 + winningPoints);
                                break;
                            case "Scissors":
                                points += (3 + drawPoints);
                                break;
                        }
                        System.out.println("You got " + points + " points.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                    }
                } else {
                    switch (outcome.get(i)) {
                        case "Loose":
                            switch (opponentsMoves.get(i)) {
                                case "Rock":
                                    points += (3 + loosingPoints);
                                    break;
                                case "Paper":
                                    points += (1 + loosingPoints);
                                    break;
                                case "Scissors":
                                    points += (2 + loosingPoints);
                                    break;
                            }
                            System.out.println("You got " + points + " points.");
                            break;
                        case "Draw":
                            switch (opponentsMoves.get(i)) {
                                case "Rock":
                                    points += (1 + drawPoints);
                                    break;
                                case "Paper":
                                    points += (2 + drawPoints);
                                    break;
                                case "Scissors":
                                    points += (3 + drawPoints);
                                    break;
                            }
                            System.out.println("You got " + points + " points.");
                            break;
                        case "Win":
                            switch (opponentsMoves.get(i)) {
                                case "Rock":
                                    points += (2 + winningPoints);
                                    break;
                                case "Paper":
                                    points += (3 + winningPoints);
                                    break;
                                case "Scissors":
                                    points += (1 + winningPoints);
                                    break;
                            }
                            System.out.println("You got " + points + " points.");
                            break;
                        default:
                            System.out.println("Invalid Outcome.");
                    }
                }
            }
        }
    }
    public static void Day3(int part){
        List <Character> firstCompartment = new ArrayList<>();
        List <Character> secondCompartment = new ArrayList<>();
        File file = new File("C:\\Users\\keaga\\IdeaProjects\\Adventodcode\\rugsacks");
        //File file = new File("C:\\Users\\keaga\\IdeaProjects\\Adventodcode\\example");
        int length_of_items, sum = 0;
        List<Character> alphabetResult = new ArrayList<>();
        String uppercase_Alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toUpperCase(Locale.ROOT);
        String lowercase_Alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase(Locale.ROOT);
        HashMap<Character, Integer> alphabetsValueTable = new LinkedHashMap<>();

        //Part 2
        List<String> group_Rucksack = new ArrayList<>();
        HashSet<List<Character>> finalResult = new LinkedHashSet<>();
        HashMap<Character, Long> duplicate_Characters_Table = new LinkedHashMap<>();
        int count = 0;

        try{
            //region Alphabet HashTable
            alphabetTable(uppercase_Alphabets, lowercase_Alphabets, alphabetsValueTable);
            //endregion
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()) {
                String token = myReader.nextLine();
                if (part == 1) {
                    length_of_items = token.length() / 2;
                    splitTokensIntoTwoParts(firstCompartment, secondCompartment, length_of_items, token);
                    check_If_Characters_Repeat(firstCompartment, secondCompartment, alphabetResult, token);
                } else if(part == 2) {
                    group_Rucksack.add(token.chars().mapToObj(item -> (char) item).distinct().map(String::valueOf).collect(Collectors.joining()));
                    for (String s : group_Rucksack) {
                        List<Character> result = s.chars().mapToObj(item -> (char) item).collect(Collectors.toList());
                        finalResult.add(result);
                    }
                }
            }
            //group_Rucksack.forEach(i -> System.out.println(i.chars().mapToObj(item -> (char) item).collect(Collectors.toList())));
            myReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
            ex.printStackTrace();
        } finally {
            if(part == 1) {
                calculateSumOfRepeatingCharacters(sum, alphabetResult, alphabetsValueTable);
            } else if(part == 2){
                for (int i = 0; i < finalResult.size(); i++) {
                    //theMatrix = (result.stream().collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum)));
                    for (Character character : new ArrayList<>(finalResult).get(i)) {
                        if (!duplicate_Characters_Table.containsKey(character)) {
                            duplicate_Characters_Table.put(character, 1L);
                        } else {
                            duplicate_Characters_Table.put(character, duplicate_Characters_Table.get(character) + 1);
                        }
                    }
                    count++;
                    if(count%3 == 0) {
                        for(Map.Entry<Character, Long> answer: duplicate_Characters_Table.entrySet()) {
                            if(answer.getValue().equals(3L)){
                                System.out.println("The only item that appears in all three rug sacks is " + answer.getKey());
                                alphabetResult.add(answer.getKey());
                            }
                        }
                        System.out.println(duplicate_Characters_Table);
                        duplicate_Characters_Table.clear();
                    }
                }
                calculateSumOfRepeatingCharacters(sum, alphabetResult, alphabetsValueTable);
            }
        }
    }
    public static void Day4(int part){
        List<Integer> first_Section = new ArrayList<>();
        List<Integer> second_Section = new ArrayList<>();

        File file = new File("C:\\Users\\keaga\\IdeaProjects\\Adventodcode\\day4_input");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                int start, end, size, count = 0, inner_Overlapping_Count = 0;
                List<Integer> intersection = null;
                String token = myReader.nextLine();
                //System.out.println(token);
                List <Integer> newToken= Arrays.stream(token.split("[-,]")).map(Integer::valueOf).collect(Collectors.toList());
                for (int i = 0; i < newToken.size(); i++) {
                    if(count != (newToken.size()-1)) {
                        start = newToken.get(i);
                        end = newToken.get(i+1);
                        count++;
                        if(count == 1) {
                            first_Section = getRangeOfList(start, end);
                        } else if(count == 3){
                            second_Section = getRangeOfList(start, end);
                        }
                    } else {
                        break;
                    }
                }
                System.out.println("The first pair of elves was assigned section " + first_Section);
                System.out.println("The second pair of elves was assigned section " + second_Section);
                if(new HashSet<>(first_Section).containsAll(second_Section) || new HashSet<>(second_Section).containsAll(first_Section)){
                    overlapping_Count += 1;
                    inner_Overlapping_Count += 1;

                }
                System.out.println("There are " + inner_Overlapping_Count + " such pairs.");
                intersection = first_Section.stream().filter(second_Section::contains).collect(Collectors.toList());
                if(!intersection.isEmpty()) {
                    System.out.println("The intersecting numbers are " + intersection +"\n");
                    number.getAndIncrement();
                } else {
                    System.out.println("There are no intersecting numbers \n");
                }
            }
            System.out.println("----------------------------------RESULT-------------------------------------");
            System.out.println("There are total of " + overlapping_Count + " such pairs.");
            System.out.println("the number of overlapping assignment pairs is " + number);
            System.out.println("-----------------------------------------------------------------------------");
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day5() {
        File file = new File("C:\\Users\\keaga\\IdeaProjects\\Adventodcode\\day5_input");
        List <Character> characters;
        Map<String, String> matrix = new LinkedHashMap<>();
        int gapCounter = 0;
        List<String> entries = new ArrayList<>();
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String token = myReader.nextLine();
                System.out.println(token);
                String replace = token.replace("[", "").replace("]", "").replace(" ", "");
                int counter = (int) replace.chars().mapToObj(item -> (char) item).count();
                int gaps = (int) Math.ceil(token.length()/4.0);
                characters = token.chars().mapToObj(item -> (char) item).collect(Collectors.toList());
                if(!token.contains("1") && !token.contains("move") && counter!=0) {
                    overlapping_Count = counter;
                    for (int i = 1; i < characters.size(); i+=4) {
                        if (gapCounter >= gaps) {
                            gapCounter = 0;
                        }
                        gapCounter++;
                        if(Character.isSpaceChar(characters.get(i))){
                            matrix.put(String.valueOf(gapCounter), "-");
                        } else if (matrix.containsKey(String.valueOf(gapCounter))) {
                            matrix.put(String.valueOf(gapCounter), matrix.get(String.valueOf(gapCounter)).concat("," + characters.get(i)));
                        } else {
                            matrix.put(String.valueOf(gapCounter), characters.get(i).toString());
                        }
                    }
                } else if(token.contains("move")) {
                    Queue<String> crate_1;
                    Stack<String> crate_2;
                    Stack<String> crate_1_Stack;
                    List<String> crate1_Chars = new ArrayList<>();
                    List<String> crate2_Chars = new ArrayList<>();
                    String item, column1, column2;
                    List<String> values = Arrays.stream(token.replace(" ", "").split("[move from to]")).filter(number -> !number.equals("")).collect(Collectors.toList());
                    item = values.get(0);
                    column1 = values.get(1);
                    column2 = values.get(2);
                    for(Map.Entry<String, String> variable : matrix.entrySet()){
                        //System.out.println("Key: " + variable.getKey() + " Value: " + variable.getValue());
                        if(variable.getKey().equals(column1)) {
                            crate1_Chars = Arrays.stream(variable.getValue().split(",")).filter(item1 -> !item1.contains("-")).collect(Collectors.toList());
                        } else if (variable.getKey().equals(column2)) {
                            crate2_Chars = Arrays.stream(variable.getValue().split(",")).filter(item2 -> !item2.contains("-")).collect(Collectors.toList());
                        }
                    }
                    crate_1 = new LinkedBlockingQueue<>(crate1_Chars);
                    crate_2 = new Stack<>();
                    crate_2.addAll(crate2_Chars);
                    crate_1_Stack = new Stack<>();
                    crate_1_Stack.addAll(crate1_Chars);
                    int l = Integer.parseInt(item);
                    do {
                        String item_Moved;
                        if(item.equals("1")) {
                            item_Moved = crate_1.poll();
                            if (crate_2.contains("")) {
                                crate_2.clear();
                                crate_2.add(0, item_Moved);
                            } else {
                                crate_2.add(0, item_Moved);
                            }
                            matrix.put(column1, crate_1.toString().replace("[", "").replace("]", "").replace(" ", ""));
                        } else {
                            item_Moved = crate_1_Stack.remove(l-1);
                            if(crate_2.contains("")){
                                crate_2.clear();
                                crate_2.add(0, item_Moved);
                            } else {
                                crate_2.add(0, item_Moved);
                            }
                            matrix.put(column1, crate_1_Stack.toString().replace("[", "").replace("]", "").replace(" ", ""));
                        }
                        matrix.put(column2, crate_2.toString().replace("[", "").replace("]", "").replace(" ", ""));
                        l--;
                    } while(l!=0);
                    if(item.equals("1")) {
                        System.out.println(crate_1);
                        System.out.println(crate_2);
                    } else {
                        System.out.println(crate_1_Stack);
                        System.out.println(crate_2);
                    }
                }
                //System.out.println(overlapping_Count);
            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        } finally {
            for (Map.Entry<String, String> result: matrix.entrySet()) {
                System.out.println("Row No." + result.getKey() +" has the following values: " + result.getValue());
                entries.add(Arrays.stream(result.getValue().split(",")).filter(i -> i.indexOf(i)== 0).collect(Collectors.toList()).stream().findFirst().get());
                //System.out.println("The order of the crates is " + Arrays.stream(result.getValue().split(",")).filter(i -> i.indexOf(i)== 0).collect(Collectors.toList()).stream().findFirst());;
            }
            System.out.println("\nThe order of the crates is " + entries.stream().map(String::valueOf).collect(Collectors.joining()));
        }
    }
    public static void Day6(){
        File file = new File("C:\\Users\\keaga\\IdeaProjects\\Adventodcode\\day6_input");
        List<Character> token_Characters;
        List<List<Character>> result = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();
        int position = 0;
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String token = myReader.nextLine();
                System.out.println(token);
                token_Characters = token.chars().mapToObj(item -> (char) item).collect(Collectors.toList());
                for (int i = 0; i < token_Characters.size(); i++){
                    int counter = 0;
                    List<Character> characters = new ArrayList<>();
                    if(i <= token_Characters.size()-14) {
                        for(int num = 0; num < 14; num++) {
                            characters.add(token_Characters.get(i + num));
                        }
                    }
                    for (int j = 0; j < characters.size()-1; j++) {
                        if(counter == 1){
                            break;
                        }

                        for (int k = j+1; k < characters.size(); k++) {
                            if (characters.get(j).equals(characters.get(k))) {
                                counter++;
                                break;
                            } else if (j == 12 && k == 13 && counter==0) {
                                result.add(characters);
                                result2.add(i+14);
                                break;
                            }
                        }
                    }
                }
                System.out.println("The first sequence is " + result.get(0) + " at position " + result2.get(0));
                result.clear();
                result2.clear();
            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day7Useless(){
        File file = new File("D:\\IntelliJ Projects\\Adventodcode\\day7_input");

        String fileName;
        String currentDirectory = "";

        HashSet <String> total_Number_Of_Directories = new LinkedHashSet<>();

        Map<String, Entry> file_List = new LinkedHashMap<>();
        try{
            String change_Directory = "$ cd";
            String list_Directory = "$ ls";
            String move_Up_Directory = "$ cd ..";

            //Regex Expression
            String change_Directory_Regex = "[\\$\\scd\\s[A-Za-z]]";
            String move_Up_Directory_Regex = "[\\$\\scd\\s\\.\\.]";
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()) {
                String token = myReader.nextLine();
                System.out.println(token);
                if (token.equals(move_Up_Directory)) {
                    currentDirectory = Paths.get(currentDirectory).getParent().toString().replace("\\", "/");
                } else if (token.contains(change_Directory)) {
                    String finalToken = token;
                    List<String> directory_Value = total_Number_Of_Directories.stream().filter(val -> val.endsWith("/" + finalToken.replace(change_Directory, "").trim())).collect(Collectors.toList());
                    if(currentDirectory.isEmpty()){
                        currentDirectory = token.replace(change_Directory, "").trim().concat("root");
                        total_Number_Of_Directories.add(currentDirectory);
                    } else {
                        if(directory_Value.isEmpty()) {
                            System.out.println("Gotchya");
                        } else {
                            String finalCurrentDirectory = currentDirectory;
                            //Optional<String> more_Than_One_Directory= directory_Value.stream().filter(v -> v.contains(finalCurrentDirectory)).findFirst();
                            Optional<String> more_Than_One_Directory= directory_Value.stream().filter(val -> val.equals(finalCurrentDirectory.concat("/" + finalToken.replace(change_Directory, "").trim()))).findFirst();
                            if(more_Than_One_Directory.isPresent()) {
                                currentDirectory = more_Than_One_Directory.get();
                            }
                        }
                    }
                } else if (token.contains(list_Directory)) {
                    do {
                        if(myReader.hasNext()) {
                            token = myReader.nextLine();
                        } else {
                            break;
                        }
                        fileName = Arrays.stream(token.split(" ")).toList().get(1);
                        String file_Size = Arrays.stream(token.split(" ")).toList().get(0);
                        String fileDirectory;
                        if(currentDirectory.equals("/")) {
                            fileDirectory = currentDirectory.concat(fileName);
                        } else {
                            fileDirectory = currentDirectory.concat("/"+ fileName);
                        }
                        if (token.contains("dir")) {
                            String value = token.replace("dir", "").trim();
                            total_Number_Of_Directories.add(currentDirectory.concat("/" + value));
                        } else if (file_Size.matches("^\\d+$") && fileName.contains(".")) {
                            if (file_List.containsKey(fileDirectory)) {
                                file_List.put(fileDirectory, new Entry(true, Integer.parseInt(file_Size)));
                            } else {
                                file_List.putIfAbsent(fileDirectory, new Entry(true, Integer.parseInt(file_Size)));
                            }
                        } else if (file_Size.matches("^\\d+$") && !fileName.contains(".")) {
                            if (file_List.containsKey(fileName)) {
                                file_List.put(fileDirectory, new Entry(true, Integer.parseInt(file_Size)));
                            } else {
                                file_List.putIfAbsent(fileDirectory , new Entry(true, Integer.parseInt(file_Size)));
                            }
                        }
                    } while(!myReader.hasNext(move_Up_Directory_Regex) || !myReader.hasNext(change_Directory_Regex));
                }
            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        } finally {
            System.out.println(file_List);
            Map<String, String> answer = new LinkedHashMap<>();
            Map<String, Entry> finalKeys = file_List.entrySet().stream().filter(val -> val.getValue().size <= 100000).collect(Collectors.toMap(stringEntryEntry -> stringEntryEntry.getKey(), stringEntryEntry -> stringEntryEntry.getValue(), (o1,o2) -> o1, LinkedHashMap::new));
            int totalSum;
            for (String directory: total_Number_Of_Directories) {
                totalSum = 0;
                for (String path : file_List.keySet()) {
                    int totalSize = getTotalSize(path, file_List);
                    if (path.contains(directory.replace("\\", "/"))) {
                        totalSum += totalSize;
                    }
                }
                answer.put(directory, String.valueOf(totalSum));
            }

            // Calculate the total sizes of all directories
            int total = 0;
            for (Map.Entry<String, String> result : answer.entrySet()) {

                if (Integer.parseInt(result.getValue()) <= 100000 && Integer.parseInt(result.getValue()) > 0) {
                    total += Integer.parseInt(result.getValue());
                    System.out.println("Directory: " + result.getKey() + ", size is "  + result.getValue());
                }

            }
            System.out.println("The sum of the total sizes of directories with at most 100,000 is: " + total);
        }
    }
    public static void Day7() {
        File file = new File("D:\\IntelliJ Projects\\Adventodcode\\day7_input");
        DirectoryEntry rootDirectory = new DirectoryEntry(null , "/");
        DirectoryEntry currentDirectory = rootDirectory;

        List<DirectoryEntry> allDirectories = new ArrayList<>();
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String token = myReader.nextLine();
                String[] linearr = token.split(" ");
                if("$".equals(linearr[0])) {
                    if("cd".equals(linearr[1])){
                        if("/".equals(linearr[2])) {
                            currentDirectory = rootDirectory;
                        }else if("..".equals(linearr[2])) {
                            currentDirectory = currentDirectory.getParent();
                        } else {
                            currentDirectory = currentDirectory.getDir(linearr[2]);
                        }
                    }
                } else if ("dir".equals(linearr[0])) {
                    DirectoryEntry de = new DirectoryEntry(currentDirectory, linearr[1]);
                    currentDirectory.addFile(de);
                    allDirectories.add(de);
                } else {
                    currentDirectory.addFile(new FileEntry(linearr[1], Long.parseLong(linearr[0])));
                }
                System.out.println(token);
            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        } finally {
            long count = 0;
            for (DirectoryEntry de: allDirectories) {
                long size = de.size();
                if(size <= 100000) {
                    count += size;
                }
            }
            System.out.println("The sum of the directories at most 100000 is " + count);

            long size_Of_Root = rootDirectory.size();
            long space_Left = 70000000 - size_Of_Root;
            long space_To_Remove = 30000000 - space_Left;

            List <DirectoryEntry> canditates = new ArrayList<>();
            for (DirectoryEntry de: allDirectories) {
                long size = de.size();
                if(size > space_To_Remove) {
                    canditates.add(de);
                }
            }

            canditates.add(rootDirectory);

            canditates.sort((a, b) -> (int) (a.size() - b.size()));
            System.out.println(canditates.get(0).size());
        }
    }
    public static void Day8(){
        File file = new File("D:\\IntelliJ Projects\\Adventodcode\\day8_input.txt");
        List<String> rows = new ArrayList<>();
        int row = 0;
        int column = 0;
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String token = myReader.nextLine();
                rows.add(token);
                row = rows.size();
                column = token.length();
                //System.out.println(token);
            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        } finally {
            int[][] grid = new int[row][column];
            String[] columns;
            long number_Of_Visible_Trees = 0;
            List<Integer> treeScore = new ArrayList<>();
            for (int i = 0; i < rows.size(); i++) {
                columns = rows.get(i).split("");
                for (int j = 0; j < columns.length; j++) {
                    grid[i][j] = Integer.parseInt(columns[j]);
                }
            }
            number_Of_Visible_Trees = (rows.size() * 2L) + ((column-2) * 2L);
            boolean isVisible = false;
            Map <Integer, Integer> indexOfValue;
            Map<Integer, String> visibleTrees = new LinkedHashMap<>();
            List <Integer> answer = new ArrayList<>();

            for (int i = 1; i < row-1; i++) {
                for(int j = 1; j < column-1; j++) {
                    Map<String, List <Integer>> directions = new LinkedHashMap<>();
                    List<Integer> downValues = new ArrayList<>();
                    List<Integer> leftValues = new ArrayList<>();
                    List<Integer> topValues = new ArrayList<>();
                    List<Integer> rightValues = new ArrayList<>();
                    for (int k = j-1; k >= 0 ; k--) {
                        leftValues.add(grid[i][k]);
                        directions.put("left", leftValues);
                    }

                    for (int k = i-1; k >= 0; k--) {
                        topValues.add(grid[k][j]);
                        directions.put("top", topValues);
                    }

                    for (int k = j+1; k < column; k++) {
                        rightValues.add(grid[i][k]);
                        directions.put("right", rightValues);
                    }

                    for (int k = i+1; k < column; k++) {
                        downValues.add(grid[k][j]);
                        directions.put("down", downValues);
                    }


                    for (Map.Entry<String, List<Integer>> direction: directions.entrySet()) {
                       for (int k = 0; k < direction.getValue().size(); k++) {
                           //int[] newValues = direction.getValue().toArray();
                           //indexOfValue = direction.getValue().stream().collect(Collectors.toUnmodifiableMap(val -> direction.getValue().indexOf(val), val -> val, (o1,o2) -> o1));
                           indexOfValue = new TreeMap<>(direction.getValue().stream().collect(Collectors.toUnmodifiableMap(val -> number.getAndIncrement(), Function.identity(), (o1,o2) -> o2 - o1)));
                           number.set(0);
                           Optional<Integer> sumTotal;
                           int finalI = i;
                           int finalJ = j;
                           direction.getValue().sort((o1, o2) -> o2 - o1);
                           if(grid[i][j] > direction.getValue().get(k)) {
                               //sumTotal = indexOfValue.entrySet().stream().filter(item -> item.getValue().equals(direction.getValue().get(0))).map(Map.Entry::getKey).findFirst();
                               isVisible = true;
                               List<Integer> visibleValues = indexOfValue.entrySet().stream().filter(item -> grid[finalI][finalJ] > item.getValue()).map(Map.Entry::getKey).toList();
                               number.set(0);
                               if(visibleValues.size() == direction.getValue().size()){
                                   answer.add(direction.getValue().size());
                               }
                               //sumTotal.ifPresent(integer -> answer.add(integer + 1));
                               if(visibleTrees.containsKey(grid[i][j])) {
                                   visibleTrees.put(grid[i][j], visibleTrees.get(grid[i][j]).concat("," + direction.getKey()));
                               } else {
                                   visibleTrees.putIfAbsent(grid[i][j], direction.getKey());
                               }
                               break;
                           } else if (grid[i][j] <= direction.getValue().get(k)) {
                               sumTotal = indexOfValue.entrySet().stream().filter(item -> grid[finalI][finalJ] <= item.getValue()).map(Map.Entry::getKey).findFirst();
                               sumTotal.ifPresent(integer -> answer.add(integer + 1));
                               break;
                           }
                           indexOfValue.clear();
                       }
                   }
                   if(visibleTrees.get(grid[i][j]) != null) {
                       System.out.println("Number " + grid[i][j] + " is visible from " + visibleTrees.get(grid[i][j]).replace(",", " & "));
                       number_Of_Visible_Trees++;
                   } else {
                       System.out.println("Number " + grid[i][j] + " is not visible from any direction");
                   }
                    System.out.println("Scenic score: " + answer.stream().reduce(1, (subTotal, element) -> subTotal * element));
                    treeScore.add(answer.stream().reduce(1, (subTotal, element) -> subTotal * element));
                    visibleTrees.clear();
                    answer.clear();
                }
            }
            System.out.println("A total of " + number_Of_Visible_Trees + " trees are visible in this arrangement.");
            treeScore.sort((o1,o2) -> o2-o1);
            System.out.println("The ideal spot for the house has a score of " + treeScore.get(0));
        }
    }
    public static void Day9(){
        File file = new File("D:\\IntelliJ Projects\\Adventodcode\\day9_input.txt");
        Map<String, Integer> gridSize = new LinkedHashMap<>();
        AtomicInteger rows = new AtomicInteger();
        AtomicInteger columns = new AtomicInteger();
        Optional<Integer> rowValue;
        Optional<Integer> columnValue;
        List<String> commands = new ArrayList<>();
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String token = myReader.nextLine();
                commands.add(token);
                gridMap(token, gridSize);
                rowValue = gridSize.entrySet().stream().filter(key -> key.getKey().equals("U") || key.getKey().equals("D")).map(Map.Entry::getValue).max(Integer::compare);
                columnValue = gridSize.entrySet().stream().filter(key -> key.getKey().equals("L") || key.getKey().equals("R")).map(Map.Entry::getValue).max(Integer::compare);
                rowValue.ifPresent(rows::set);
                columnValue.ifPresent(columns::set);
                //System.out.println(token);
            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        } finally {
            rows.set(1000);
            columns.set(1000);
            Map<String, String> positionsVisited = new LinkedHashMap<>();
            ArrayList<ArrayList<LinkedBlockingQueue<String>>> grid = createGridInitialState(rows, columns);
            for (String command: commands) {
                int moves = Integer.parseInt(command.split(" ")[1]);
                int count = 0;
                int row, column;
                List<Integer> startingPosition;
                switch (command.split(" ")[0]) {
                    case "R" -> {
                        startingPosition = getKnotPosition(positionsVisited, rows, "H", 1);
                        row = startingPosition.get(0);
                        column = startingPosition.get(1);
                        int headColumn = 0, tailColumn, tailRow;
                        for (int col = 0; col < moves; col++) {
                            String knotValue = grid.get(row).get(column).poll();
                            if (knotValue != null) {
                                headColumn = column + 1;
                                requeueElements(grid.get(row).get(headColumn), knotValue);
                            }
                            getPositionOfKnot(positionsVisited, knotValue, row, headColumn);

                            if (!checkTailPosition(getKnotPosition(positionsVisited, rows, "H", 1), getKnotPosition(positionsVisited, rows, "T", 1))) {
                                startingPosition = getKnotPosition(positionsVisited, rows, "T", 1);
                                tailRow = startingPosition.get(0);
                                tailColumn = startingPosition.get(1);
                                knotValue = grid.get(tailRow).get(tailColumn).poll();
                                if (knotValue != null) {
                                    startingPosition = getKnotPosition(positionsVisited, rows, "H", 2);
                                    tailRow = startingPosition.get(0);
                                    tailColumn = startingPosition.get(1);
                                    requeueElements(grid.get(tailRow).get(tailColumn), knotValue);
                                }
                                getPositionOfKnot(positionsVisited, knotValue, row, tailColumn);
                            }
                            column++;
                            count++;
                        }
                    }
                    case "L" -> {
                        startingPosition = getKnotPosition(positionsVisited, rows, "H", 1);
                        row = startingPosition.get(0);
                        column = startingPosition.get(1);
                        int headColumn = 0, tailColumn = 0, tailRow;
                        for (int col = 0; col < moves; col++) {
                            String knotValue = grid.get(row).get(column).poll();
                            if (knotValue != null) {
                                headColumn = column - 1;
                                requeueElements(grid.get(row).get(headColumn), knotValue);
                            }
                            getPositionOfKnot(positionsVisited, knotValue, row, headColumn);

                            if (!checkTailPosition(getKnotPosition(positionsVisited, rows, "H", 1), getKnotPosition(positionsVisited, rows, "T", 1))) {
                                startingPosition = getKnotPosition(positionsVisited, rows, "T", 1);
                                tailRow = startingPosition.get(0);
                                tailColumn = startingPosition.get(1);
                                knotValue = grid.get(tailRow).get(tailColumn).poll();
                                if (knotValue != null) {
                                    startingPosition = getKnotPosition(positionsVisited, rows, "H", 2);
                                    tailRow = startingPosition.get(0);
                                    tailColumn = startingPosition.get(1);
                                    requeueElements(grid.get(startingPosition.get(0)).get(startingPosition.get(1)), knotValue);
                                }
                                getPositionOfKnot(positionsVisited, knotValue, tailRow, tailColumn);
                            }
                            column--;
                            count++;
                        }
                    }
                    case "U" -> {
                        startingPosition = getKnotPosition(positionsVisited, rows, "H", 1);
                        row = startingPosition.get(0);
                        column = startingPosition.get(1);
                        int headRow = 0, tailColumn, tailRow;
                        for (int r = 0; r < moves; r++) {
                            String knotValue = grid.get(row).get(column).poll();
                            if (knotValue != null) {
                                headRow = row - 1;
                                requeueElements(grid.get(headRow).get(column), knotValue);
                            }
                            getPositionOfKnot(positionsVisited, knotValue, headRow, column);

                            if (!checkTailPosition(getKnotPosition(positionsVisited, rows, "H", 1), getKnotPosition(positionsVisited, rows, "T", 1))) {
                                startingPosition = getKnotPosition(positionsVisited, rows, "T", 1);
                                tailRow = startingPosition.get(0);
                                tailColumn = startingPosition.get(1);
                                knotValue = grid.get(tailRow).get(tailColumn).poll();
                                if (knotValue != null) {
                                    startingPosition = getKnotPosition(positionsVisited, rows, "H", 2);
                                    tailRow = startingPosition.get(0);
                                    tailColumn = startingPosition.get(1);
                                    requeueElements(grid.get(tailRow).get(tailColumn), knotValue);
                                }
                                getPositionOfKnot(positionsVisited, knotValue, tailRow, tailColumn);
                            }
                            row--;
                            count++;
                        }
                    }
                    case "D" -> {
                        startingPosition = getKnotPosition(positionsVisited, rows, "H", 1);
                        row = startingPosition.get(0);
                        column = startingPosition.get(1);
                        int headRow = 0, tailColumn, tailRow;
                        for (int col = 0; col < moves; col++) {
                            String knotValue = grid.get(row).get(column).poll();
                            if (knotValue != null) {
                                headRow = row + 1;
                                requeueElements(grid.get(headRow).get(column), knotValue);
                            }
                            getPositionOfKnot(positionsVisited, knotValue, headRow, column);

                            if (!checkTailPosition(getKnotPosition(positionsVisited, rows, "H", 1), getKnotPosition(positionsVisited, rows, "T", 1))) {
                                startingPosition = getKnotPosition(positionsVisited, rows, "T", 1);
                                tailRow = startingPosition.get(0);
                                tailColumn = startingPosition.get(1);
                                knotValue = grid.get(tailRow).get(tailColumn).poll();
                                if (knotValue != null) {
                                    startingPosition = getKnotPosition(positionsVisited, rows, "H", 2);
                                    tailRow = startingPosition.get(0);
                                    tailColumn = startingPosition.get(1);
                                    requeueElements(grid.get(tailRow).get(tailColumn), knotValue);
                                }
                                getPositionOfKnot(positionsVisited, knotValue, tailRow, tailColumn);
                            }
                            row++;
                            count++;
                        }
                    }
                }
            }
            /*printGridValues(rows, grid);
            System.out.println(positionsVisited.get("T"));
            printFinalMap(positionsVisited, grid);*/
            int part1_answer = positionOfTailsVisitedAtLeastOnce(positionsVisited.get("T"));
            System.out.printf("There are %s positions the tail visited at least once %n", part1_answer);
        }
    }

    private static int positionOfTailsVisitedAtLeastOnce(String tailPositionsVisited) {
        return Arrays.stream(tailPositionsVisited.split(", ")).collect(Collectors.toSet()).size();
    }

    private static Boolean checkTailPosition(List<Integer> positionOfHead, List<Integer> positionOfTail) {
        int headRow = 0, headColumn = 0, tailRow = 0, tailColumn = 0;
        boolean isAdjacent = false;
        Map<String, String> headAdjacentValues = new LinkedHashMap<>();
        headRow = positionOfHead.get(0);
        headColumn = positionOfHead.get(1);
        tailRow = positionOfTail.get(0);
        tailColumn = positionOfTail.get(1);

        List<String> adjacentSides = List.of("Top", "Bottom", "Left", "Right", "Top Right", "Top Left", "Bottom Right", "Bottom Left", "Centre");

        for (String adjacentSide : adjacentSides) {
            switch (adjacentSide) {
                case "Top" -> headAdjacentValues.put(adjacentSide, "(" + (headRow - 1) + "," + headColumn + ")");
                case "Bottom" -> headAdjacentValues.put(adjacentSide, "(" + (headRow + 1) + "," + headColumn + ")");
                case "Left" -> headAdjacentValues.put(adjacentSide, "(" + (headRow) + "," + (headColumn - 1) + ")");
                case "Right" -> headAdjacentValues.put(adjacentSide, "(" + (headRow) + "," + (headColumn + 1) + ")");
                case "Top Left" ->
                        headAdjacentValues.put(adjacentSide, "(" + (headRow - 1) + "," + (headColumn - 1) + ")");
                case "Top Right" ->
                        headAdjacentValues.put(adjacentSide, "(" + (headRow - 1) + "," + (headColumn + 1) + ")");
                case "Bottom Left" ->
                        headAdjacentValues.put(adjacentSide, "(" + (headRow + 1) + "," + (headColumn - 1) + ")");
                case "Bottom Right" ->
                        headAdjacentValues.put(adjacentSide, "(" + (headRow + 1) + "," + (headColumn + 1) + ")");
                default -> headAdjacentValues.put(adjacentSide, "(" + headRow + "," + headColumn + ")");
            }
        }
        for (Map.Entry<String, String> adjacentValuesCheck: headAdjacentValues.entrySet()) {
            if (adjacentValuesCheck.getValue().equals("(" + tailRow + "," + tailColumn + ")")) {
                isAdjacent = true;
                break;
            }
        }

        return isAdjacent;
    }

    private static LinkedBlockingQueue<String> requeueElements(LinkedBlockingQueue<String> queue, String elementsToBeAdded) {
        Stack<String> elementToBeDequeued = new Stack<>();
        if(queue.isEmpty()) {
            queue.offer(elementsToBeAdded);
        } else {
            queue.drainTo(elementToBeDequeued);
            queue.offer(elementsToBeAdded);
            for (int i = 0; i < elementToBeDequeued.size(); i++) {
                queue.offer(elementToBeDequeued.pop());
            }
        }
        return queue;
    }

    private static void getPositionOfKnot(Map<String, String> positionsVisited, String knotValue,int row, int col) {
        if(positionsVisited.containsKey(knotValue)) {
            positionsVisited.put(knotValue, positionsVisited.get(knotValue).concat(", (" + row + ","+ col +")"));
        } else {
            positionsVisited.putIfAbsent(knotValue, "(" + row +"," + col + ")");
        }
    }

    private static List<Integer> getKnotPosition(Map<String, String> positionsVisited, AtomicInteger noOfRows, String knotsPart, int lastPositionIndex) {
        String[] currentHeadPosition;
        int row;
        int column;
        List<Integer> startingPosition = new ArrayList<>();
        if(positionsVisited.get(knotsPart) == null){
            row = noOfRows.get()/2;
            column = noOfRows.get()/2;
            positionsVisited.put(knotsPart, "(" + row + "," + column + ")");
        } else {
            currentHeadPosition = positionsVisited.get(knotsPart).split(", ")[positionsVisited.get(knotsPart).split(", ").length - lastPositionIndex].replaceAll("[()]", "").split(",");
            row = Integer.parseInt(currentHeadPosition[0]);
            column = Integer.parseInt(currentHeadPosition[1]);
        }
        startingPosition.add(row);
        startingPosition.add(column);
        return startingPosition;
    }

    private static void printGridValues(AtomicInteger rows, ArrayList<ArrayList<LinkedBlockingQueue<String>>> grid) {
        for (int i = 0; i < rows.get()+1; i++) {
            Integer startVertex = i;
            String endVertex = grid.get(i).toString();
            System.out.printf("Vertex %d is connected to vertex %s%n", startVertex, endVertex);
        }
    }

    private static void printFinalMap(Map<String, String> positionNavigationGrid, ArrayList<ArrayList<LinkedBlockingQueue<String>>> grid) {
        int row, column = 0;
        positionNavigationGrid.entrySet().stream().filter(val -> val.getKey().equals("T")).map(points -> Arrays.stream(points.getValue().split(", ")).map(p -> p.replaceAll("[()]", "").split(",")).map(r -> grid.get(Integer.parseInt(r[0])).get(Integer.parseInt(r[1])).add("#")).collect(Collectors.toList())).collect(Collectors.toList());
        printGridValues(new AtomicInteger(4), grid);
    }

    private static ArrayList<ArrayList<LinkedBlockingQueue<String>>> createGridInitialState(AtomicInteger row, AtomicInteger column) {
        LinkedBlockingQueue<String> points = new LinkedBlockingQueue<>();
        ArrayList<ArrayList<LinkedBlockingQueue<String>>> grid = new ArrayList<>(row.get()+1);

        points.offer("H");
        points.offer("T");
        points.offer("s");

        for (int i = 0; i < row.get(); i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < column.get(); j++) {
                if(i == row.get()/2 && j == column.get()/2) {
                    grid.get(row.get()/2).add(points);
                } else {
                    grid.get(i).add(new LinkedBlockingQueue<>());
                }
            }
        }
        return grid;
    }

    private static void gridMap(String token, Map<String, Integer> gridSize) {
        String[] input = token.split(" ");
        if(gridSize.containsKey(input[0])){
            if(gridSize.get(input[0]) < Integer.parseInt(input[1])) {
                gridSize.put(input[0], Integer.parseInt(input[1]));
            }
        } else {
            gridSize.putIfAbsent(input[0], Integer.parseInt(input[1]));
        }
    }

    public static void Day10(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day11(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day12(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day13(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day14(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day15(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day16(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day17(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day18(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day19(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day20(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day21(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day22(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day23(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day24(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }
    public static void Day25(){
        File file = new File("");
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){

            }
            myReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
            ex.printStackTrace();
        }
    }

    // Represents the file or directory
    static class Entry {
        boolean isFile;
        int size;

        public Entry(boolean isFile, int size) {
            this.isFile = isFile;
            this.size = size;
        }
    }

    // Function to calculate the total size of a directory and its subdirectories recursively
    public static int getTotalSize(String path, Map<String, Main.Entry> fileSystem) {
        if (!fileSystem.containsKey(path)) {
            return 0;
        }

        Main.Entry entry = fileSystem.get(path);
        if (entry.isFile) {
            return entry.size;
        }

        int totalSize = 0;
        for (String subPath : fileSystem.keySet()) {
            if (subPath.startsWith(path + "/")) {
                totalSize += getTotalSize(subPath, fileSystem);
            }
        }
        return totalSize;
    }

    public static List<Integer> getRangeOfList(int start, int end){
        return IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
    public static void calculateSumOfRepeatingCharacters(int sum, List<Character> alphabetResult, HashMap<Character, Integer> alphabetsValueTable) {
        for (Character character : alphabetResult) {
            sum += alphabetsValueTable.get(character);
        }
        System.out.println("The sum is " + sum);
    }

    public static void splitTokensIntoTwoParts(List<Character> firstCompartment, List<Character> secondCompartment, int length_of_items, String token) {
        for (int i = 0; i < length_of_items; i++) {
            if (!firstCompartment.contains(token.charAt(i))) {
                firstCompartment.add(token.charAt(i));
            }
        }
        for (int j = length_of_items; j < token.length(); j++) {
            if (!secondCompartment.contains(token.charAt(j))) {
                secondCompartment.add(token.charAt(j));
            }
        }
    }

    private static void check_If_Characters_Repeat(List<Character> firstCompartment, List<Character> secondCompartment, List<Character> alphabetResult, String token) {
        for (Character result: firstCompartment) {
            for (Character result2: secondCompartment){
                if(result2.equals(result)){
                    alphabetResult.add(result);
                    System.out.println("The character that repeats is " + result+ " for token " + token);
                }
            }
        }
        firstCompartment.clear();
        secondCompartment.clear();
    }

    private static void alphabetTable(String uppercase_Alphabets, String lowercase_Alphabets, HashMap<Character, Integer> alphabetsValueTable) {
        for (int z = 0; z < lowercase_Alphabets.length(); z++) {
            if(Character.isLowerCase(lowercase_Alphabets.charAt(z))) {
                alphabetsValueTable.put(lowercase_Alphabets.charAt(z), z + 1);
            }
        }

        for (int z = 0; z < uppercase_Alphabets.length(); z++) {
            if(Character.isUpperCase(uppercase_Alphabets.charAt(z))) {
                alphabetsValueTable.put(uppercase_Alphabets.charAt(z), z + 27);
            }
        }
    }
    public static void main(String[] args) {
        //Day1();
        /*Enter 1/2 for the strategy*/
        //Day2(2);
        //Day3(2);
        //Day4(1);
        //Day5();
        //Day6();
        //Day7();
        //Day8();
        Day9();
    }
}
