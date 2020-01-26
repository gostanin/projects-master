import java.util.Scanner;

public class Main {

	static boolean solved = false;
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		int size;
		int width;
		char[][] board;
		do
		{
			size = in.nextInt();
			if(size != 0)
			{
				width = in.nextInt();
				board = new char[size][size];
				
				for(int i = 0; i < size; i++)
				{
					for(int j = 0; j < size; j++)
					{
						board[i][j] = in.next().charAt(0);
					}
				}
				solved = false;
				solveSudoku(board, 0, 0, size, width);
				
				if(!solved)
					System.out.println("IMPOSSIBLE!");
			}
		}while(size != 0);
		
		in.close();
		
	}
	
	public static void solveSudoku(char[][] board, int row, int column, int size, int width)
	{
		if(!solved)
		{
			if(row >= size)
			{
				solved = true;
				printBoard(board, size, width);
			}
			else if(column >= size)
			{
				solveSudoku(board, row+1, 0, size, width);
			}
			else if(board[row][column] != '0')
				solveSudoku(board, row, column+1, size, width);
			else
				for(int k = 1; k <= size; k++)
				{
					board[row][column] = Integer.toString(k, 36).toUpperCase().charAt(0);
					if(sudokuFit(board, row, column, size, width))
						solveSudoku(board, row, column+1, size, width);
					
						board[row][column] = '0';
				}
		}
	}
	
	public static boolean sudokuFit(char[][] board, int row, int column, int size, int width)
	{
		int height = size/width;
		int rowi =(row/height)*height;
		int rowbound = rowi+height;
		
		int colj = (column/width)*width;
		int colbound = colj+width;
		
		boolean flag = true;
		
		for(rowi =(row/height)*height; rowi < rowbound && flag; rowi++)
		{
			for(colj = (column/width)*width; colj < colbound && flag; colj++)
			{
				if(board[rowi][colj] == board[row][column] && (rowi != row && colj != column))
					flag = false;
			}
		}
		
		for(int checkij = 0; checkij < size && flag; checkij++)
		{
			if((board[row][checkij] == board[row][column] && checkij != column) || (board[checkij][column] == board[row][column] && checkij != row))
				flag = false;
		}
		return flag;
	}
	
	public static void printBoard(char[][] board, int size, int width)
	{
		if(solved)
		{
			System.out.println("Solved:\n");
			for(int i = 0; i < size; i++)
			{
				if(i % (size/width) == 0 && i > 0)
					System.out.println();
				for(int j = 0; j < size; j++)
				{
					if(j % width == 0 && j > 0)
						System.out.print(" ");
					System.out.print(board[i][j] + " ");
					
				}
				System.out.println();
			}
			System.out.println();
		}
		else
			System.out.print("IMPOSSIBLE!");
	}
}
