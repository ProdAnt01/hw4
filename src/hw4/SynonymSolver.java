package hw4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for solving synonym questions, depending on the
 * semantic similarity It also lowers the words and finds the best choice
 * 
 * @author Prodromos Antoniou
 * @since 19/04/2025
 * @version 1.0
 */
public class SynonymSolver extends TextProcessor {
	/**
	 * Finds the most similar word from the list of choices based on semantic
	 * 
	 * @param word                target word
	 * @param choices             list of choices
	 * @param semanticDescriptors map of semantic descriptors for words
	 * @return the choice that is most similar to the target word
	 */
	public static String mostSimilarWord(String word, List<String> choices,
			Map<String, Map<String, Integer>> semanticDescriptors) {
		word = word.toLowerCase();
		Map<String, Integer> wordDescriptor = semanticDescriptors.get(word);
		if (wordDescriptor == null) {
			return choices.get(0);
		}

		double maxSimilarity = -1;
		String bestChoice = choices.get(0);
		for (String choice : choices) {
			choice = lowercaseWord(choice);
			Map<String, Integer> choiceDescriptor = semanticDescriptors.get(choice);
			if (choiceDescriptor != null) {
				double similarity = SimilarityCalculator.cosineSimilarity(wordDescriptor, choiceDescriptor);
				if (similarity > maxSimilarity) {
					maxSimilarity = similarity;
					bestChoice = choice;
				}
			}
		}
		return bestChoice;

	}

	/**
	 * Runs a synonym similarity test given a file of questions Each line in the
	 * file contains a question word, the correct answer, and three choices. Results
	 * are printed to console and saved to a file.
	 * 
	 * @param filename            path to the file containing test questions
	 * @param semanticDescriptors map of semantic descriptors for words
	 * @return percentage of correctly guessed answers
	 * @throws IOException if file reading or writing fails
	 */
	public static double runSimilarityTest(String filename, Map<String, Map<String, Integer>> semanticDescriptors)
			throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		int correct = 0;
		int total = 0;
		List<String> outputs = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(" ");
			for (int i = 0; i < parts.length; i++) {
				parts[i] = lowercaseWord(parts[i]);
			}
			if (parts.length < 3) {
				continue;
			}
			String question = parts[0];
			String answer = parts[1];
			List<String> choices = Arrays.asList(Arrays.copyOfRange(parts, 2, parts.length));
			String guessed = mostSimilarWord(question, choices, semanticDescriptors);
			if (guessed.equals(answer)) {
				correct++;
				System.out.println(question + " | Guessed : " + guessed + " | Correct : " + answer + "= correct");
			} else
				System.out.println(question + " | Guessed : " + guessed + " | Correct : " + answer + "= false");
			outputs.add(question + " | Guessed : " + guessed + " | Correct : " + answer);
			total++;
		}
		reader.close();

		BufferedWriter writer = new BufferedWriter(new FileWriter("output_results.txt"));
		for (String output : outputs) {
			writer.write(output);
			writer.newLine();
		}
		writer.close();
		if (total > 0)
			return ((double) correct / total) * 100;
		return 0;
	}

}
