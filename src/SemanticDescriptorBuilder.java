package hw4;

import java.util.*;

/**
 * This SemanticDescriptorBuilder class is responsible to build semantic
 * descriptors for words base on their occurence
 * 
 * @author Prodromos Antoniou
 * @since 19/04/2025
 * @version 1.0
 */
public class SemanticDescriptorBuilder {
	/**
	 * Builds semantic descriptors from a list of sentences. Each word maps to a
	 * descriptor showing how many times it occurs with other words in the same
	 * sentences.
	 * 
	 * @param sentences list of sentences
	 * @return a map where each word maps to its semantic descriptor
	 */
	public static Map<String, Map<String, Integer>> buildSemanticDescriptors(List<List<String>> sentences) {
		Map<String, Map<String, Integer>> semanticDescriptors = new HashMap<>();
		for (List<String> sentence : sentences) {
			Set<String> uniqueWords = new HashSet<>(sentence);
			for (String word : uniqueWords) {
				if (!semanticDescriptors.containsKey(word)) {
					semanticDescriptors.put(word, new HashMap<>());
				}
				Map<String, Integer> descriptor = semanticDescriptors.get(word);
				for (String word2 : uniqueWords) {
					if (!word.equals(word2)) {
						int count = 0;
						if (descriptor.containsKey(word2)) {
							count = descriptor.get(word2);
						}
						descriptor.put(word2, count + 1);
					}
				}
			}

		}
		return semanticDescriptors;
	}

}
