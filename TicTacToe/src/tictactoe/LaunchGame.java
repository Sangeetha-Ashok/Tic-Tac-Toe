package tictactoe;
import java.util.Random;
import java.util.Scanner;

class TicTacToe {
	static char[][] board;
	
	public TicTacToe()
	{
		board = new char[3][3];
		initBoard();
	}
	void initBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j] = ' ';
			}
		}
	}
	static void dispBoard()
	{
		System.out.println("-------------");
		for(int i=0;i<board.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
		
	}
	static void placeMark(int row, int column, char mark)
	{
		if(row >=0 && row <=2 && column >=0 && column <=2)
		{
			board[row][column] = mark;
		}
		else 
		{
			System.out.println("Invalid Position");
		}
	}
	static boolean checkColumnWin()
	{
		for(int j=0;j<=2;j++)
		{
			if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j] )
			{
				return true;
			}
		}
		return false;
	}
	static boolean checkRowWin()
	{
		for(int i=0;i<=2;i++)
		{
			if(board[i][0]!=' ' &&board[i][0]==board[i][1] && board[i][1]==board[i][2])
			{
				return true;
			}
		}
		return false;
	}
	static boolean checkDiagWin() 
	{
		if(board[0][0]!=' ' &&board[0][0]==board[1][1] && board[1][1]==board[2][2] || board[0][2]!=' ' &&board[0][2]==board[1][1] && board[1][1]==board[2][0])
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	static boolean chechDraw()
	{
		for(int i=0;i<=2;i++)
		{
			for(int j=0;j<=2;j++)
			{
				if(board[i][j]==' ')
				{
					return false;
				}
			}
		}
		return true;
	}
}

abstract class Player
{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row, int column)
	{
		if(row>=0 && row<=2 && column>=0 && column<=2)
		{
			if(TicTacToe.board[row][column]== ' ')
			{
				return true;
			}
		}
		return false;
	}
}
class HumanPlayer extends Player
{
	
	HumanPlayer(String name, char mark)
	{
		this.name = name;
		this.mark = mark;
	}
	void makeMove()
	{
		Scanner scan = new Scanner(System.in);
		int row;
		int column;
		do
		{
		System.out.println("Enter the row and column");
		row=scan.nextInt();
		column=scan.nextInt(); 
		}  while(!isValidMove(row,column));
		TicTacToe.placeMark(row, column, mark);
	}
	
}
class AIPlayer extends Player
{
	
	AIPlayer(String name, char mark)
	{
		this.name = name;
		this.mark = mark;
	}
	void makeMove()
	{
		Scanner scan = new Scanner(System.in);
		int row;
		int column;
		do
		{
			Random r= new Random(); 
			row=r.nextInt(3);
			column=r.nextInt(3);
		}  while(!isValidMove(row,column));
		TicTacToe.placeMark(row, column, mark);
	}
	
}
public class LaunchGame {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		TicTacToe t = new TicTacToe();
		System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Select game mode:");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs AI");
        
        
		int choice =scan.nextInt();

        Player p1 = new HumanPlayer("Player 1", 'X');
        Player p2;

        if (choice == 1) {
            p2 = new HumanPlayer("Player 2", 'O');
        } else {
            p2 = new AIPlayer("Computer", 'O');
        }

        Player cp = p1;

        while (true) {
            System.out.println(cp.name + "'s turn");
            cp.makeMove();
            TicTacToe.dispBoard();

            if (TicTacToe.checkColumnWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin()) {
                System.out.println(cp.name + " has won!");
                break;
            } else if (TicTacToe.chechDraw()) {
                System.out.println("Game is a draw!");
                break;
            } else {
                cp = (cp == p1) ? p2 : p1;
            }
        }
    }
}