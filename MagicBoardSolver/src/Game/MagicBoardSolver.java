package Game;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class MagicBoardSolver {
    
    //these  are the constraints on the size of the board
    private final static int MAXIMUMSIZE = 20;
    private final static int MINIMUMSIZE = 5;
    
    //this method returns a 2-d array of BoardBox objects.
    private static BoardBox[][] createMagicBoard(int size){

        BoardBox [][] magicBoard = new BoardBox[size][size];
        Random rand=new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                magicBoard[i][j] = new BoardBox(rand.nextInt(size-1)+1,i,j);
            }
        }
        int x;
        int y;
        do {
             x = rand.nextInt(size);
             y = rand.nextInt(size);
        }
       while ((x==0&&y==0)||(x==size-1&&y==size-1)||(x==0&&y==size-1)||(x==size-1&&y==0));
        
        magicBoard[x][y].setValue(0);
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                System.out.print("["+magicBoard[i][j].getValue()+"]");
            }
            System.out.println();
        }

        return magicBoard;
    }
    
    //These 4 mehtods return a boolean representing whether or not a certain direction is visitable from
    //the current cell 
    private static boolean isWestVisitable(BoardBox[][]magicBoard,int i , int j){
        if(j-magicBoard[i][j].getValue()>=0){
            return true;
        }
        return false;
    }
    private static boolean isEastVisitable(BoardBox[][] magicBoard,int i , int j){
        if(j+magicBoard[i][j].getValue()<magicBoard.length){
            return true;
        }
        return false;
    }
    private static boolean isSouthVisitable(BoardBox[][] magicBoard,int i , int j){
        if(i+magicBoard[i][j].getValue()<magicBoard.length){
            return true;
        }
        return false;
    }
    private static boolean isNorthVisitable(BoardBox[][]magicBoard,int i , int j){
        if(i-magicBoard[i][j].getValue()>=0){
            return true;
        }
        return false;
    }
    
    // This method recursively parses the Board to see if it is solvable from a certain 
    //certain cell
    private static boolean recursivelySolve(BoardBox[][] magicBoard,int i, int j){

        //If we are at the goal cell, return true
        if(magicBoard[i][j].getValue()==0){
            return true;
        }
        
        if (isWestVisitable(magicBoard,i,j)&& !magicBoard[i][j].getIsWestVisited()){
            magicBoard[i][j].setWestVisited(true);
            //If the goal cell is in the west, return true
            if(recursivelySolve(magicBoard,i,j-magicBoard[i][j].getValue())){
                return true;
            }
        }
        if (isEastVisitable(magicBoard,i,j)&&  !magicBoard[i][j].getIsEastVisited()){
            magicBoard[i][j].setEastVisited(true);
            //If the goal cell is in the east, return true
            if(recursivelySolve(magicBoard,i,j+magicBoard[i][j].getValue())){
                return true;
            }
            
        }
        if (isSouthVisitable(magicBoard,i,j)&&  !magicBoard[i][j].getIsSouthVisited()){
            magicBoard[i][j].setSouthVisited(true);
            //If the goal cell is in the south, return true
           if(recursivelySolve(magicBoard,i+magicBoard[i][j].getValue(),j)){
               return true;
           }
        }
        if (isNorthVisitable(magicBoard,i,j)&&  !magicBoard[i][j].getIsNorthVisited()){
            magicBoard[i][j].setNorthVisited(true);
           //If the goal cell is in the north, return true
            if(recursivelySolve(magicBoard,i-magicBoard[i][j].getValue(),j)){
                return true;
            }
        }
        
        return false;  // we only reach this statement if the goal cell was found nowhere
    }
    
    //This method iteratively parses the Board to see if it solvable from a certain
    //BoardBox object. This is performed with a Stack and using depth-first search.
    private static boolean iterativelySolve(BoardBox[][] magicBoard,int i, int j){
        Stack<BoardBox> stack = new Stack<>();
        stack.push(magicBoard[i][j]);

        while (stack.empty() == false) {

            BoardBox current = stack.pop();;
            if(current.getValue()==0){
                return true;
            }

            if(isWestVisitable(magicBoard,current.pos_i,current.pos_j)&& !magicBoard[current.pos_i][current.pos_j].getIsWestVisited()){
                magicBoard[current.pos_i][current.pos_j].setWestVisited(true);
                stack.push(current);
                stack.push(magicBoard[current.pos_i][current.pos_j-magicBoard[current.pos_i][current.pos_j].getValue()]);
            }
            if (isEastVisitable(magicBoard,current.pos_i,current.pos_j)&&  !magicBoard[current.pos_i][current.pos_j].getIsEastVisited()){
                magicBoard[current.pos_i][current.pos_j].setEastVisited(true);
                stack.push(current);
                stack.push(magicBoard[current.pos_i][current.pos_j+magicBoard[current.pos_i][current.pos_j].getValue()]);
            }
            if (isNorthVisitable(magicBoard,current.pos_i,current.pos_j)&&  !magicBoard[current.pos_i][current.pos_j].getIsNorthVisited()){
                magicBoard[current.pos_i][current.pos_j].setNorthVisited(true);
                stack.push(current);
                stack.push(magicBoard[current.pos_i-magicBoard[current.pos_i][current.pos_j].getValue()][current.pos_j]);
            }
            if(isSouthVisitable(magicBoard,current.pos_i,current.pos_j)&&  !magicBoard[current.pos_i][current.pos_j].getIsSouthVisited()){
                magicBoard[current.pos_i][current.pos_j].setSouthVisited(true);
                stack.push(current);
                stack.push(magicBoard[current.pos_i+magicBoard[current.pos_i][current.pos_j].getValue()][current.pos_j]);
            }

        }
        
        return false; // once the stack is empty, we can safely say that all possible paths were searched with no results.
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int size=0;
        int i=0;
        int j=0;
        int choice;

        System.out.println("***********************************");
        System.out.println("***********************************");
        System.out.println("***********************************");
        System.out.println("Welcome to the Magic Board Solver !");

        try{
            System.out.println("First, what size would you want the board to be ? Choose a size between 5 and 20 inclusive:");
             size = s.nextInt();
            while (size > MAXIMUMSIZE || size < MINIMUMSIZE){
                System.out.println("Sorry the size is not valid. Choose a size between 5 and 20 inclusive: ");
                size = s.nextInt();
            }
            System.out.println("This is your game board :");
            BoardBox[][] magicBoard = createMagicBoard(size);
            System.out.println("How nice ! Knowing this, from which corner would you like to start?");
            System.out.println("For the upper left corner type 1, upper right type 2, lower left type 3 and lower right type 4:");
            choice = s.nextInt();
            while (choice > 4 || choice < 1){
                System.out.println("Sorry the input is not valid. Choose a number between 1 and 4 inclusive: ");
                choice = s.nextInt();
            }
            switch(choice){
                case 1:
                i=0;
                j=0;
                break;

                case 2:
                i=0;
                j=size-1;
                break;

                case 3:
                i=size-1;
                j=0;
                break;

                case 4:
                i=size-1;
                j=size-1;
                break;
            }
           
            System.out.println("Perfect, would you like to solve the board recursively or iteratively ?");
            System.out.println("Type in 0 for recursive or 1 for iterative: ");
            int method = s.nextInt();
            while (method!=0 && method !=1){
                System.out.println("Sorry the input is not valid. Choose either 0 or 1: ");
                choice = s.nextInt();
            }
            switch(method){
                case 0:
                System.out.println("[RECURSIVELY] The board is solvable from the upper left corner: "+recursivelySolve(magicBoard, i, j));
                break;

                case 1:
                System.out.println("[ITERATIVELY] The board is solvable from the upper left corner: "+iterativelySolve(magicBoard, i, j));
                break;
            }
            
            System.out.println("So fun! Thank you for playing the Magic Board Solver !");
            System.out.println("******************************************************");
            System.out.println("******************************************************");
            System.out.println("******************************************************");
    
            s.close();
        }
        catch(InputMismatchException e)
        {
            System.out.println("Please respect the requested input. Program will exit");
            System.out.println("*******************************************************");
        
        }

    }
}
