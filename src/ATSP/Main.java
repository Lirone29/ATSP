package ATSP;

import java.util.Scanner;

public class Main {

    static int choice = -1;
    public static void main(String[] args) {

        Matrix costMatrix = new Matrix();
        costMatrix.readFromFile("tsp10.atsp");
        costMatrix.display();
        //Genetic geneticSolver;
        //geneticSolver = new Genetic();
    }

    public String fileMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println( "Which file you want to open? \n 1: ftv47.atsp \n 2: ftv170.atsp \n 3: rbg403.atsp \n 4: other ");

        switch (choice) {
            case 0:
            {
                return "tsp10.atsp";
            }
            case 1:
            {
                return "ftv47.atsp";
            }
            case 2:{
                return "ftv170.atsp";
            }
            case 3:{
                return "rbg403.atsp";
            }
            case 4 :{
                String name;
                System.out.println("Write name of file: ");
                name = input.nextLine();
                return name;
            }
            default: {
                break;
            }
        }
        return null;
    }
}
