package hw4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main class to run the synonym solver program. It loads training files, builds
 * semantic descriptors, and calculates accuracy based on command line
 * arguments.
 * 
 * 
 * First argument: synonyms (questions) file Rest arguments: training files
 */
public class SemanticSimilarity {

	/**
	 * Main method to execute the program.
	 * 
	 * @param args command line arguments: (synonyms file, training files)
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Not enough arguments");
			return;
		}
		String synonymsFile = args[0];
		List<String> trainingFiles = new ArrayList<>();

		for (int i = 1; i < args.length; i++) {
			trainingFiles.add(args[i]);
		}
		try {
			List<List<String>> sentences = TextProcessor.getSentenceListsFromFile(trainingFiles);
			Map<String, Map<String, Integer>> semanticDescriptors = SemanticDescriptorBuilder
					.buildSemanticDescriptors(sentences);
			double accuracy = SynonymSolver.runSimilarityTest(synonymsFile, semanticDescriptors);
			System.out.printf("Accuracy: %.2f%%\n", accuracy);
		} catch (IOException e) {
			System.out.println("Error reading files: ");
		}
	}
}
