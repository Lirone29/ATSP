package ATSP;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Individual {
    int Gene;
    Vector<Integer> genotype;
    double fitnessValue;
    double genotypeValue;
    Matrix allelesValues;
    Random generator;

    public Individual(){
        generator = new Random();
        fitnessValue = 0;
        genotypeValue = 0;
        allelesValues = new Matrix();
    }

    public Individual(int numberOfGenes){
        generator = new Random();
        genotype = new Vector<>();
        fitnessValue = 0;
        genotypeValue = 0;
        allelesValues = new Matrix();

        for (int i = 0; i < numberOfGenes; ++i)
            genotype.add(i);

        mutateTotal();
        updateFitness();
    }
    public Individual(Vector<Integer> genotype){
        generator = new Random();
        genotype = new Vector<>();
        fitnessValue = 0;
        genotypeValue = 0;
        allelesValues = new Matrix();

        born(genotype);
        updateFitness();

    }

    void born(Vector<Integer> genotype){
        this.genotype = genotype;

    }

    void die(){
        genotype.clear();
    }

    Vector<Integer> getGenotype(){
        return this.genotype;
    }

    void mutate(MutationType mutationType, int numberOfMutations)
    {
        if(mutationType.equals(MutationType.SWAP_MUT))
            this.mutateSwap(1);
        if(mutationType.equals(MutationType.SCRAM_MUT))
            this.mutateSwap(1);

    }

    void mutateTotal(){
        Collections.shuffle(genotype);
        updateFitness();
    }

    void mutateSwap(int numberOfSwaps)
    {
        int x=0,y=0;
        for(int i = 0 ; i < numberOfSwaps; i++){
            while (x==y) {
                x = generator.nextInt(genotype.size());
                y = generator.nextInt(genotype.size());
            }
            Collections.swap(genotype, x, y);

       }
        updateFitness();
    }

    void mutateScramble(int numberOfScrambles){
        int firstIndex = 0, lastIndex = 0;
        for(int i = 0 ; i < numberOfScrambles; i++){
            while (firstIndex == lastIndex){
                firstIndex = generator.nextInt(genotype.size());
                lastIndex = generator.nextInt(genotype.size());
            }
            if(firstIndex>lastIndex){
                int tmp = firstIndex;
                firstIndex = lastIndex;
                lastIndex = tmp;
            }

            Vector<Integer> tmpVector = new Vector<>(genotype.subList(firstIndex,lastIndex));
            Collections.shuffle(tmpVector);
            int iteratorOfTmp = 0;

            for(int j = firstIndex ; j <= lastIndex ; j++){
                genotype.set(j, tmpVector.get(iteratorOfTmp));
                iteratorOfTmp++;
            }

        }
        updateFitness();
    }

    void updateFitness(){
        fitnessValue = 1.0 / getGenotypeValue();
    }
    double getGenotypeValue(){
        int i;
        genotypeValue = 0;
        for (i = 0; i < genotype.size() - 1; ++i) {
            genotypeValue += allelesValues.getValue(genotype.elementAt(i),genotype.elementAt(i+1));
        }
        genotypeValue += allelesValues.getValue(genotype.elementAt(i),genotype.elementAt(0));
        return genotypeValue;
    }

    double getFitnessValue(){
        return fitnessValue;
    }

    void crossoverPMX(Individual otherParent, Individual tmpChild){

        Random random = new Random();
        int smallerCrossPoint, biggerCrossPoint;
        Vector<Integer> child = new Vector<>();
        Vector<Integer> parentA = genotype;
        Vector<Integer> parentB = otherParent.getGenotype();
        int genotypeSize = genotype.size();
        //Vector<Integer>iterator iteratorVector;
        int tmpCounter;
        int tmpNumber;

        smallerCrossPoint = random.nextInt(genotypeSize-1);
        biggerCrossPoint = random.nextInt(genotypeSize-1);
        child.setSize(genotypeSize);

        if(smallerCrossPoint>biggerCrossPoint)
        {
            int tmp = smallerCrossPoint;
            smallerCrossPoint = biggerCrossPoint;
            biggerCrossPoint = tmp;
        }

        for(int i = 0; i < genotypeSize; i++) {
            child.set(i,parentB.get(i));
        }

        Vector tmpParent  = new Vector();
        for(int i = smallerCrossPoint; i < biggerCrossPoint; i++){
            child.set(i,parentB.get(i));
        }

        tmpChild = new Individual(child);
        //tutaj może dać return tmpChild - taka metoda byłąby lepsza
    }

    void printDebugInfo(){

    }
}
