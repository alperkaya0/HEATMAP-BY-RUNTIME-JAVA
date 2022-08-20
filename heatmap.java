import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class heatmap {
    public static void main(String[] args) throws IOException {
        String filename = "examplecode.java";
        readcode(filename);
        Runtime.getRuntime().exec("java "+"2"+filename);
        colorcode(filename);
    }
    public static void colorcode(String filename) throws FileNotFoundException {
        File file = new File("output.txt");
        String str = "";
        Scanner sc = new Scanner(file);
    
        while (sc.hasNextLine())
            str += sc.nextLine().trim();
        sc.close();
        String[] arr = str.split(",");
        ArrayList<Long> times = new ArrayList<>();
        for (int i = 0; i+1 < arr.length; i++) {times.add(Long.parseLong(arr[i+1])-Long.parseLong(arr[i]));}

        file = new File(filename);
        ArrayList<String> codes = new ArrayList<>();
        sc = new Scanner(file);
    
        while (sc.hasNextLine())
            codes.add(sc.nextLine().trim());
        sc.close();
        double avg = 0;
        for (int i = 0; i < times.size(); ++i) avg += times.get(i);
        avg /= times.size();
        int k = 0;
        System.out.println(times);
        for (int i = 0; i < codes.size(); ++i) {
            if (codes.get(i).indexOf("public")==-1 && !(codes.get(i).equals("}")) && codes.get(i).indexOf("ArrayList<Long> arr = new")==-1) {
                //this is where coloring occures
                if (times.get(k) < avg) {
                    codes.set(i, BLUE_BACKGROUND + codes.get(i) + RESET);
                    ++k;
                } else if (times.get(k) > avg) {
                    codes.set(i, RED_BACKGROUND  + codes.get(i) + RESET);
                    ++k;
                } else {
                    codes.set(i,GREEN_BACKGROUND + codes.get(i) + RESET);
                    ++k;
                }
            }
        }
        for (String string : codes) {
            System.out.println(string);
        }
    }
    public static void readcode(String filename) throws IOException {
        // pass the path to the file as a parameter
        File file = new File(filename);
        ArrayList<String> arr = new ArrayList<>();
        Scanner sc = new Scanner(file);
    
        while (sc.hasNextLine())
            arr.add(sc.nextLine().trim());
        sc.close();
        arr.add(2, "ArrayList<Long> arr = new ArrayList<>();");
        for (int i = 0; i < arr.size(); ++i) {
            if (arr.get(i).indexOf("public")==-1 && !(arr.get(i).equals("}")) && arr.get(i).indexOf("ArrayList<Long> arr = new")==-1) {
                arr.add(i, "arr.add(System.currentTimeMillis());");
                ++i;
            }
        }
        int pointer = arr.size()-1;
        while (pointer > 0) {
            if (arr.get(pointer).indexOf("public")!=-1 || (arr.get(pointer).equals("}"))) {
                --pointer;
            } else break;
        }
        arr.add(pointer+1, "arr.add(System.currentTimeMillis());\nString str =\"\";\nfor (Long el:arr) str += el+(el!=arr.get(arr.size()-1)?\",\":\"\");\nBufferedWriter writer = new BufferedWriter(new FileWriter(\"output.txt\"));\nwriter.write(str);\nwriter.close();");
        arr.add(0, "import java.io.BufferedWriter;\nimport java.io.File;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.util.ArrayList;\nimport java.util.Scanner;");

        String str = "";
        for (String string : arr) {
            str += string+"\n";
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("2"+filename));
        writer.write(str);
        writer.close();
    }
    // Reset
    public static final String RESET = "\033[0m";
    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
}