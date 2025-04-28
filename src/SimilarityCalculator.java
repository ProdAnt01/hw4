package hw4;

import java.util.Map;

/**
 * This class is responsible for calculating similarity between two vectors
 * 
 * @author Prodromos Antoniou
 * @since 19/04/2025
 * @version 1.0
 */
public class SimilarityCalculator {
	/**
	 * Method to calculate the norm length of a vector
	 * 
	 * @param vec the semantic descriptor vector
	 * @return the norm of the vector
	 */
	private static double calculateNorm(Map<String, Integer> vec) {
		double sumOfSquares = 0.0;
		for (int value : vec.values()) {
			sumOfSquares += value * value;
		}
		return Math.sqrt(sumOfSquares);
	}

	/**
	 * Calculates the cosine similarity between two vectors
	 * 
	 * @param vec1 first semantic descriptor of the word
	 * @param vec2 second semantic descriptor of the word
	 * @return cosine similarity
	 */
	public static double cosineSimilarity(Map<String, Integer> vec1, Map<String, Integer> vec2) {
		double dotProduct = 0.0;
		for (String key : vec1.keySet()) {
			if (vec2.containsKey(key)) {
				dotProduct += vec1.get(key) * vec2.get(key);
			}
		}
		double normVec1 = calculateNorm(vec1);
		double normVec2 = calculateNorm(vec2);

		if (normVec1 == 0 || normVec2 == 0) {
			return -1.0;
		}
		return dotProduct / (normVec1 * normVec2);
	}
}
