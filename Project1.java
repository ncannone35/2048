/**
 * This program runs the game 2048.
 *
 * @Nick Cannone
 * @Project 1
 */
import java.util.*;
public class Project1
{
    private static boolean endGame;//whether or not the game should end
    private static int numMoves;//cumulative number of moves
    public static void main(String[]args)
    {
        int arr[][] = new int [4][4];
        populateBoard(arr);
        while(!getEndGame())//game loop
        {
            makeMove(arr);
            if(isFilled(arr) == true && noMove(arr) == true)//no moves left
            {
                System.out.println("Game Over Loser.");
                break;
            }
            if(has2048(arr))//win game
            {
                setEndGame(true);
                System.out.println("CONGRATULATIONS!!!!!!! YOU'VE WON!!!!!!");
            }
        }
    }

    public static void printBoard(int arr[][])
    {
        for(int i = 0; i < 6; i ++)//prints bottom of border
        {
            System.out.print("-");
            for(int j = 0; j < 6; j ++)
                System.out.print(" ");
        }
        System.out.println();
        for(int i = 0; i < arr.length; i ++)
        {
            for(int j = 0; j < arr[i].length; j ++)
            {
                if(j == 0)//case for the first number 
                {
                    System.out.print("|");//prints left side of border
                    for(int k = 0; k < 6 ; k ++)
                        System.out.print(" ");
                }
                if(arr[i][j] == 0)
                    System.out.print("*");
                else
                    System.out.print(arr[i][j]);//prints the number
                if(j < arr[i].length - 1)//all numbers following the first
                {
                    int dig = numDigits(arr[i][j]);//checks digits
                    for(int k = 0; k < 7 - dig; k ++)//prints spaces
                        System.out.print(" ");
                }
            }
            int dig = numDigits(arr[i][3]);
            for(int k = 0; k < 7 - dig; k ++)//prints right side of border
                System.out.print(" ");
            System.out.print("|");
            System.out.println();
        }
        for(int i = 0; i < 6; i ++)//prints bottom of border
        {
            System.out.print("-");
            for(int j = 0; j < 6; j ++)
                System.out.print(" ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void populateBoard(int arr[][])
    {
        int fill = 0;
        for(int i = 0; i < arr.length; i ++)//fills initial board with 0's that will be outputed as *
        {
            for(int j = 0; j < arr[i].length; j ++)
            {
                arr[i][j] = 0;
            }
        }
        for(int i = 0; i < 2; i ++)//runs loop twice
        {
            int rand = (int)(Math.random() * 10);//finds random number between 0 and 9
            if(rand < 8)//.8 chance for 2
                fill = 2;
            else//.2 chance for 4
                fill = 4;
            int randi = (int)(Math.random() * 4);//finds random i value in 2d array
            int randj = (int) (Math.random() * 4);//finds random j value in 2d array
            while(arr[randi][randj] != 0)
            {
                randi = (int)(Math.random() * 4);//finds random i value in 2d array
                randj = (int) (Math.random() * 4);//finds random j value in 2d array
            }
            arr[randi][randj] += fill;//adds the random value to the random spot in the array
        }
        printBoard(arr);
    }

    public static void placeRandomNum(int arr[][])
    {
        int fill = 0;
        ArrayList <Integer> ivals = new ArrayList <Integer>();//list that will store open i values
        ArrayList <Integer> jvals = new ArrayList <Integer>();//stores open j values.  Lists correspond to one another
        for(int i = 0; i < arr.length; i ++)
        {
            for(int j = 0; j < arr[i].length; j ++)
            {
                if(arr[i][j] == 0)
                {
                    ivals.add(i);
                    jvals.add(j);
                }
            }
        }
        int rand = (int)(Math.random() * 10);//finds random number between 0 and 9
        if(rand < 8)//.8 chance for 2
            fill = 2;
        else//.2 chance for 4
            fill = 4;
        int randindex = ((int)(Math.random() * ivals.size()));//gets a random index in one of the two array lists 
        int randi = ivals.get(randindex);
        int randj = jvals.get(randindex);
        arr[randi][randj] += fill;//adds the random value to the random spot in the array
        printBoard(arr);

    }

    public static void makeMove(int arr[][])
    {
        int copy[][] = new int[4][4];//makes a copy of the array
        for(int i = 0; i < arr.length; i ++)
        {
            for(int j = 0; j < arr[i].length; j ++)
            {
                copy[i][j] = arr[i][j];
            }
        }
        Scanner scan = new Scanner(System.in);
        char confirm;
        String dir = scan.nextLine();
        if(dir.equals("d"))//right
        {
            for(int a = 0; a < 4; a ++)
            {
                int[] row = arr[a];//row array
                for(int b = 0; b < 4; b ++)
                {
                    for(int i = 0; i < row.length - 1; i ++)//goes through all 4 rows
                    {
                        if(row[i+1] == 0)
                        {
                            row[i+1] = row[i];
                            row[i] = 0;                       
                        }
                    }
                }
                for(int i = 3; i > 0; i --)//adds up like adjacent values
                {
                    if(row[i-1] == row[i])
                    {
                        row[i] += row[i-1];
                        row[i-1] = 0;
                        i--;
                    }
                }
                for(int b = 0; b < 4; b ++)
                {
                    for(int i = 0; i < row.length - 1; i ++)//goes through all 4 rows again
                    {
                        if(row[i+1] == 0)
                        {
                            row[i+1] = row[i];
                            row[i] = 0;                       
                        }
                    }
                }
                arr[a] = row;//fills array
            }
            if(equal(arr,copy))//checks if nothing changed
            {
                System.out.println("The key pressed was 'd' which was not a valid move.");
                printBoard(arr);
            }
            else
            {
                System.out.println("The key pressed was 'd' which was a valid move.");
                incNumMoves();
                System.out.println("Number of moves: " + getNumMoves());
                maxValue(arr);
                placeRandomNum(arr);
            }
        }
        else if(dir.equals("a"))//left
        {
            for(int a = 0; a < 4; a ++)
            {
                int[] row = arr[a];
                for(int b = 0; b < 4; b ++)
                {
                    for(int i = row.length - 1; i > 0; i --)//goes through all 4
                    {
                        if(row[i-1] == 0)
                        {
                            row[i-1] = row[i];
                            row[i] = 0;                       
                        }
                    }
                }
                for(int i = 0; i < 3; i ++)
                {
                    if(row[i+1] == row[i])//adds adjacent values
                    {
                        row[i+1] += row[i];
                        row[i] = 0;
                        i ++;
                    }
                }
                for(int b = 0; b < 4; b ++)
                {
                    for(int i = row.length - 1; i > 0; i --)
                    {
                        if(row[i-1] == 0)
                        {
                            row[i-1] = row[i];
                            row[i] = 0;
                        }
                    }
                }
                arr[a] = row;
            }
            if(equal(arr,copy))
            {
                System.out.println("The key pressed was 'a' which was not a valid move.");
                printBoard(arr);
            }
            else
            {
                System.out.println("The key pressed was 'a' which was a valid move.");
                incNumMoves();
                System.out.println("Number of moves: " + getNumMoves());
                maxValue(arr);
                placeRandomNum(arr);
            }
        }

        else if(dir.equals("w"))//up
        {
            int[][] reverse = new int[4][4];//puts columns in a new 2d array as the rows
            for(int i = 0; i < 4; i ++)
            {
                for(int j = 0; j < 4; j++)
                {
                    reverse[i][j] = arr[j][i];
                }
            }
            for(int a = 0; a < 4; a ++)
            {
                int[] col = reverse[a];
                for(int b = 0; b < 4; b ++)//goes through all 4
                {
                    for(int i = 3; i > 0; i --)
                    {
                        if(col[i-1] == 0)
                        {
                            col[i-1] = col[i];
                            col[i] = 0;
                        }
                    }
                }
                for(int i = 0; i < 3; i ++)
                {
                    if(col[i+1] == col[i])//adjacent addition
                    {
                        col[i] += col[i+1];
                        col[i+1] = 0;
                        i ++;
                    }
                }
                for(int b = 0; b < 4; b ++)
                {
                    for(int i = 3; i < 0; i --)
                    {
                        if(col[i-1] == 0)
                        {
                            col[i-1] = col[i];
                            col[i] = 0;
                        }
                    }
                }
                reverse[a] = col;
            }
            for(int i = 0; i < 4; i ++)//fills array with reverse reset
            {
                for(int j = 0; j < 4; j ++)
                {
                    arr[j][i] = reverse[i][j];
                }
            }
            if(equal(arr,copy))
            {
                System.out.println("The key pressed was 'w' which was not a valid move.");
                printBoard(arr);
            }
            else
            {
                System.out.println("The key pressed was 'w' which was a valid move.");
                incNumMoves();
                System.out.println("Number of moves: " + getNumMoves());
                maxValue(arr);
                placeRandomNum(arr);
            }
        }
        else if(dir.equals("s"))//down
        {
            int[][] reverse = new int[4][4];//reverse array similar to previous loop
            for(int i = 0; i < 4; i ++)
            {
                for(int j = 0; j < 4; j++)
                {
                    reverse[i][j] = arr[j][i];
                }
            }
            for(int a = 0; a < 4; a ++)
            {
                int[] col = reverse[a];
                for(int b = 0; b < 4; b ++)
                {
                    for(int i = 0; i < 3; i ++)
                    {
                        if(col[i+1] == 0)
                        {
                            col[i+1] = col[i];
                            col[i] = 0;
                        }
                    }
                }
                for(int i = 3; i > 0; i --)
                {
                    if(col[i-1] == col[i])
                    {
                        col[i-1] += col[i];
                        col[i] = 0;
                        i --;
                    }
                }
                for(int b = 0; b < 4; b ++)
                {
                    for(int i = 0; i < 3; i ++)
                    {
                        if(col[i+1] == 0)
                        {
                            col[i+1] = col[i];
                            col[i] = 0;
                        }
                    }
                }
                reverse[a] = col;
            }
            for(int i = 0; i < 4; i ++)
            {
                for(int j = 0; j < 4; j ++)
                {
                    arr[j][i] = reverse[i][j];
                }
            }
            if(equal(arr,copy))
            {
                System.out.println("The key pressed was 's' which was not a valid move.");
                printBoard(arr);
            }
            else
            {
                System.out.println("The key pressed was 's' which was a valid move.");
                incNumMoves();
                System.out.println("Number of moves: " + getNumMoves());
                maxValue(arr);
                placeRandomNum(arr);
            }
        }

        else if(dir.equals("r"))//reset game
        {
            System.out.println("Are you sure that you wish to restart the game?  Type 'c' to confirm.");
            confirm = scan.next().charAt(0);
            if(confirm == 'c')
            {
                resetNum();//resets number of moves
                populateBoard(arr);//creates a new board
                System.out.println("Number of moves: " + getNumMoves());
                maxValue(arr);
            }
        }
        else if(dir.equals("q"))//quit game method
        {
            System.out.println("Are you sure that you wish to quit the game?  Type 'c' to confirm.");
            confirm = scan.next().charAt(0);
            if(confirm == 'c')
            {
                System.out.println("Game Over.");
                setEndGame(true);
            }
        }
        else//if entered value is not any of the above
        {
            printBoard(arr);
            makeMove(arr);
        }
    }

    public static boolean equal(final int[][] arr1, final int[][] arr2) //checks if 2 2d arrays are equal
    {
        for (int i = 0; i < arr1.length; i++) 
        {
            if (!Arrays.equals(arr1[i], arr2[i])) 
            {
                return false;
            }
        }
        return true;
    }

    public static int numDigits(int num)//calculates the number of digits in a number
    {
        int count = 0;
        while(num != 0)
        {
            num /= 10;//divides by 10
            count++;//adds to digit count
        } 
        if(count == 0)//only happens when the number is 0
            count ++;
        return count;
    }

    public static boolean getEndGame()//getter method for the private endGame value
    {
        return endGame;
    }

    public static void setEndGame(boolean eg)//setter method
    {
        endGame = eg;
    }

    public static int getNumMoves()//getter method for the private numMoves value
    {
        return numMoves;
    }

    public static void incNumMoves()//incrememtns numMoves
    {
        numMoves++;
    }

    public static void resetNum()//resets numMoves
    {
        numMoves = 0;
    }

    public static void maxValue(int arr[][])//finds the max value on the board and prints in a formatted print statement
    {
        int max = 0;
        for(int i = 0; i < arr.length; i ++)
        {
            for(int j = 0; j < arr[i].length; j ++)
            {
                if(arr[i][j] > max)
                    max = arr[i][j];
            }
        }
        System.out.println("Maximum value on board: " + max);
    }

    public static boolean has2048(int arr[][])//checks if a board has 2048
    {
        for(int i = 0; i < arr.length; i ++)
        {
            for(int j = 0; j < arr[i].length; j ++)
            {
                if(arr[i][j] == 2048)
                    return true;
            }
        }
        return false;
    }

    public static boolean isFilled(int arr[][])//checks if a board is filled
    {
        for(int i = 0; i < arr.length; i ++)
        {
            for(int j = 0; j < arr[i].length; j ++)
            {
                if(arr[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    public static boolean noMove(int arr[][])//checks if there is an available move
    {
        int count = 0;
        for(int i = 0; i < 4; i ++)
        {
            for(int j = 0; j < 3; j ++)
            {
                if(arr[i][j] == arr[i][j+1])
                    count ++;
                if(arr[j][i] == arr[j+1][i])
                    count++;
            }
        }
        if(count!=0)
            return true;
        return false;
    }
}

