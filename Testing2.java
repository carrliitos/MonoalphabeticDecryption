import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class Testing2 {
	public static final String filepath = "./sample2.txt";

	public static void main(String[] args) {
		int chars[] = readFile();
		if(chars == null) return;

		for(int i = 0; i < 26; i++) {
			char letter = (char)('A' + i);
			System.out.println(letter + ": " + chars[i]);
		}
	}

	public static int[] readFile() {
		try {
			File file = new File(filepath);
			Scanner reader = new Scanner(file, "UTF-8");

			int ret[] = new int[26];
			int letterCount = 0;

			while(reader.hasNextLine()) {
				String line = reader.nextLine().toUpperCase();
				for(int i = 0; i < line.length(); i++) {
					int letter = (int)(line.charAt(i) - 'A');
					if(letter > -1 && letter < ret.length){
						ret[letter]++;
					}
				}
				letterCount += line.length();
			}
			System.out.println("Total characters = " + letterCount);
			reader.close();
			return ret;
		}catch(FileNotFoundException e) {
			System.out.println("Cannot find file.");
			return null;
		}
	}
}
