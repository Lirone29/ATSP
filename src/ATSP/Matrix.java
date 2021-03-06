package ATSP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Matrix {

    int size;
    Vector<Vector<Double>> matrixVector;

    Matrix(){
        matrixVector = new Vector<Vector<Double>>();
        size = 0;
    }

    public void display(){
        Iterator iterator = matrixVector.iterator();
        for(int i = 0 ; i < size; i++) {
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " \n");
            }
        }
    }

    public void clearMatrix(){
            matrixVector.clear();
    }

    boolean readFromFile(String filename){
        int dimension = 0;
        String[] dim;

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains("DIMENSION")){
                    dim = data.split(" ", 2);
                    dimension = Integer.valueOf(dim[1]);
                    this.setSize(dimension);
                    System.out.println("Dimension is " + dimension);
                }
                if(data.contains("EDGE_WEIGHT_SECTION")) break;
            }
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                dim = Arrays.stream(data.split(" ")).map(String::trim).toArray(String[]::new);
                Vector v = new Vector();

                for (int i = 0; i < dim.length; i++) {
                    String tmp = dim[i];
                    if (tmp.contains(" ")|| tmp.isEmpty()) continue;
                    v.add(tmp);
                    //System.out.println("Element V = " + v.lastElement());
                }
                if(!v.isEmpty()) matrixVector.add(v);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return false;
    }

    //setting up values in matrix - NOT USED
    /*
    void setMatrixVector(Vector<Vector<Double>> tmpMatrixVector){

        matrixVector = tmpMatrixVector;
        //size = matrixVector.size();
    }
    */

    Vector<Vector<Double>> getMatrixVector(){
         return matrixVector;
    }

    double getValue(int indexFirst, int indexSecond){
        return matrixVector.get(indexFirst).get(indexSecond);
    }
    int getSize(){
        return size;
    }

    void setSize(int tmpSize){
        this.size = tmpSize;
    }
}
