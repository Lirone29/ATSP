package ATSP;

import java.util.Vector;

public class Individual {
    int Gene;
    Vector<Integer> genotype;
    double fitnessValue;
    double genotypeValue;
    Matrix allelesValues;

    public Individual(){

    }

    public Individual(int number){

    }
    public Individual(Vector<Integer> genotype){

    }

    void born(Vector<Integer> genotype){

    }
    void die(){

    }

    Vector<Integer> getGenotype(){
        return this.genotype;
    }

    void mutate(MutationType mutationType, int numberOfMutations){

    }

    void mutateTotal(){

    }

    void mutateSwap(int numberOfSwaps){

    }

    void mutateScramble(int numberOfScrambles){

    }

    double getGenotypeValue(){
        return genotypeValue;
    }

    double getFitnessValue(){
        return fitnessValue;
    }

    void updateFitnessValue(){

    }

    void crossoverPMX(Individual otherParent, Individual child){

    }

    void printDebugInfo(){

    }
}
