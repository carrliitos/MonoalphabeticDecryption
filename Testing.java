import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.List;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Testing {
	public static final Character ALPHABET[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
												'H', 'I', 'J', 'K', 'L', 'M', 'N',
												'O', 'P', 'Q', 'R', 'S', 'T', 'U',
												'V', 'W', 'X', 'Y', 'Z'};

	public static void main(String[] args) {
		try {
			// File filePath = new File("testing.txt");
			// FileReader reader = new FileReader(filePath);
			// int c = 0;
			// char character = ' ';
			// ArrayList<Character> chars = new ArrayList<>();
			// int counter = 0;

			// while((c = reader.read()) != -1) {
			// 	if(Character.isLetter(c)) {
			// 		character = (char) c;
			// 		chars.add(character);
			// 	}
			// }

			// for(int i = 0; i < ALPHABET.length; i++) {
			// 	for(int j = 0; j < chars.size(); j++) {
			// 		if(j == i) {
			// 			System.out.printf("%s", ALPHABET[j]);
			// 		}
			// 	}
			// }

			// System.out.printf("count=%d\n", counter);
			// System.out.println(chars);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("testing.txt")));
			String name;
			Integer val;
			TreeMap<Character, Integer> map = new TreeMap<>();
			while((name = reader.readLine()) != null) {
				for(int i = 0; i < name.length(); i++) {
					// System.out.println("Char at: " + line.charAt(i));
					char c = name.charAt(i);
					val = map.get(c);
					if(val != null) {
						map.put(c, new Integer(val + 1));
					}else {
						map.put(c, 1);
					}
				}
				// print frequency to output
				String key, value;
				for(Map.Entry<Character, Integer> entry : map.entrySet()) {
					key = Character.toString(entry.getKey());
					value = String.valueOf(entry.getValue());
					System.out.println(key + ": " + value);
				}
			}

			reader.close();
		}catch(FileNotFoundException f) {
			System.out.printf("File not found\n");
			return;
		}catch(IOException e) {
			System.out.println("IO error\n");
			return;
		}
	}
}