package hw4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * This class is responsible for processing text files into lists of sentences
 * and words
 * 
 * @author Prodromos Antoniou
 * @since 19/04/2025
 * @version 1.0
 */
public class TextProcessor {
	/**
	 * Converts a word form camel case to lower case
	 * 
	 * @param word to convert
	 * @return the lowered case word
	 */
	public static String lowercaseWord(String word) {
		StringBuilder lowered = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				lowered.append((char) (c + 32));
			} else
				lowered.append(c);
		}
		return lowered.toString();
	}

	/**
	 * Splits a text into a sentence and the sentence into words
	 * 
	 * @param text the text to process
	 * @return list of sentences where a sentence is a list of words
	 */
	public static List<List<String>> getSentenceLists(String text) {
		List<List<String>> sentences = new ArrayList<>();
		text = text.replace("\n", " ");
		String[] splitSentences = text.split("[.?!]");
		for (String splitSentence : splitSentences) {
			splitSentence = splitSentence.replaceAll("[,;:\"()\\-]", " ");
			String[] words = splitSentence.trim().split(" ");
			List<String> wordList = new ArrayList<>();
			for (String word : words) {
				if (!word.isEmpty()) {
					wordList.add(lowercaseWord(word));
				}
			}
			if (!wordList.isEmpty()) {
				sentences.add(wordList);
			}
		}
		return sentences;
	}

	/**
	 * Reads multiple text files and returns all sentences
	 * 
	 * @param filenames list of files
	 * @return list of sentences where a sentence is a list of words
	 * @throws IOException if a file is not found
	 */
	public static List<List<String>> getSentenceListsFromFile(List<String> filenames) throws IOException {
		List<List<String>> allSentences = new ArrayList<>();
		for (String filename : filenames) {
			String content = readFile(filename);
			List<List<String>> sentences = getSentenceLists(content);
			allSentences.addAll(sentences);
		}
		return allSentences;
	}

	/**
	 * Reads the content of a file into a single string
	 * 
	 * @param filename
	 * @return the files content
	 * @throws IOException if a file is not found
	 */
	public static String readFile(String filename) throws IOException {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line).append(" ");
			}

		}
		return builder.toString();

	}
}
