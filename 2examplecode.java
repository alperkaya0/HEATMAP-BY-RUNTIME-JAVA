import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class examplecode {
public static void main(String[] args) throws IOException {
ArrayList<Long> arr = new ArrayList<>();
arr.add(System.currentTimeMillis());
System.out.println("Hello World");
arr.add(System.currentTimeMillis());
System.out.println("Hello World");
arr.add(System.currentTimeMillis());
for (int i = 0; i < 1000000000; ++i);
arr.add(System.currentTimeMillis());
int x = 5;
arr.add(System.currentTimeMillis());
System.out.println(x);
arr.add(System.currentTimeMillis());
String str ="";
for (Long el:arr) str += el+(el!=arr.get(arr.size()-1)?",":"");
BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
writer.write(str);
writer.close();
}
}
