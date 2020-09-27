/* @author Benzon Carlitos Salazar
*	CS455 - Project 1 (Monoalphabetic Cipher Project)
*	This program decrypts a ciphertext using the monoalphabetic cipher using 
*	a Ciphertext-Only attack (COA) with the provided text document.
*	Two tasks are to be done:
*		[1] Assist us in retrieving the key used to encrypt the original message
*		[2] Use the key to decipher the original message as plaintext
*
*	The retrieved key: "UIOYKLXHVBSFWJGNRDPECZMATQ"
*/

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Monoalphabetic {
	public static void main(String[] args) {
		String filepath = "Benzon S.txt";

		System.out.println("Calculating Letter Frequencies...");
		int chars[] = letterFrequency(filepath);
		if(chars == null) return;

		try {
			File f = new File("./letterFrequency.txt");
			FileWriter fWriter = new FileWriter(f);

			fWriter.write("LETTER\t| FREQUENCY\n");
			fWriter.write("--------|----------\n");
			for(int i = 0; i < 26; i++) {
				char letter = (char)('A' + i);
				fWriter.write(letter + "\t\t| " + chars[i] + "\n");
			}
			fWriter.flush();
			fWriter.close();

			System.out.println("Letter frequency calculation finished.");
			System.out.println("File location: " + f);
		}catch(IOException e) {
			System.out.println("IO Error in main() method.");
			e.printStackTrace();
		}

		System.out.println("");
		System.out.println("Decrypting ciphertext...");
		System.out.println("Grab some coffee, this may take a while...");
		decrypt(filepath);
	}

	public static int[] letterFrequency(String filepath) {
		try {
			File file = new File(filepath);
			Scanner reader = new Scanner(file, "UTF-8");

			int ret[] = new int[26];
			int totalLetter = 0;

			while(reader.hasNextLine()) {
				String line = reader.nextLine().toUpperCase();
				for(int i = 0; i < line.length(); i++) {
					int letter = (int)(line.charAt(i) - 'A');
					if(letter > -1 && letter < ret.length){
						ret[letter]++;
					}
				}
			}

			reader.close();
			return ret;
		}catch(FileNotFoundException e) {
			System.out.println("Cannot find file in letterFrequency() method.");
			e.printStackTrace();
			return null;
		}
	}

	public static void decrypt(String filePath) {
		try {
			File file = new File(filePath);
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);

			File f = new File("./plainText.txt");
			FileWriter fWriter = new FileWriter(f);

			int c = 0;
			ArrayList<Character> chars = new ArrayList<>();
			while((c = bReader.read()) != -1) {
				char character = (char) c;
				chars.add(character);
			}
			String cipherText = "";
			for(int i = 0; i < chars.size(); i++) {
				cipherText += chars.get(i);
			}

			String KEY = "UIOYKLXHVBSFWJGNRDPECZMATQ";
			String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

			int i, j;
			for(i = 0; i < cipherText.length(); i++) {
				for(j = 0; j < KEY.length(); j++) {
					if(cipherText.charAt(i) == KEY.charAt(j)) {
						fWriter.write(ALPHABET.charAt(j));
					}
				}
			}

			System.out.println("Decryption finished.");
			System.out.println("Recovered plaintext file location: " + f);

			fReader.close();
			bReader.close();
			fWriter.flush();
			fWriter.close();
		}catch(IOException e) {
			System.out.println("IO Error in decrypt() method");
			e.printStackTrace();
		}
	}
}
