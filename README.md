# Semantic Similarity Solver

## Description

This project is a synonym solver for TOEFL multiple choice questions.
The program learn by reading and processing text file without dictionaries and build semantic descriptors (word occurrence vector). Then it uses cosine similarity to find the most similar word for a given question.

## Details

# TextProcessor.java : Cleans and split the text from training files into sentences and words.
# SemanticDescriptorBuilder.java : Builds co occurence vectors (semanitc descriptors) form training files sentences
# SimilarityCalculator.java : Calculates the cosine similarity between two vectors
# SynonymSolver.java : Chooses the most similar word from given options and prints the results with accuracy percentage.
# SemanticSimilarity.java : Main class for running the project using command lin arguments for tarining files and synonym questions files.

## File structure:

```
src/
  hw4/
    SemanticSimilarity.java
    SemanticDescriptorBuilder.java
    SimilarityCalculator.java
    SynonymSolver.java
    TextProcessor.java
  brown-train-sentences.txt
  pg2600.txt
  pg7178.txt
  SynonymQuestions.txt
  output_results.txt
```

## Compile:

EPL133/Homeworks/src> javac hw4/*.java

## Run:

java hw4.SemanticSimilarity SynonymQuestions.txt brown-train-sentences.txt pg2600.txt pg717

# Team:
Prodromos Antoniou
