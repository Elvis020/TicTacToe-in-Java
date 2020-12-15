import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {
		char[][] gameBoard = {
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
		};
		printGameBoard(gameBoard);
		
		System.out.println("====Welcome to Tic Tac Toe ====");
		System.out.println();
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your position from (1-9): ");
			int playerPos = sc.nextInt();
			while(playerPosition.contains(playerPos) || cpuPosition.contains(playerPos)) {
				System.out.println("Sorry, position already taken. Choose another one");
				playerPos = sc.nextInt();
			}
			
			placePiece(gameBoard, playerPos, "player");
			String result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			Random rand  = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			placePiece(gameBoard, cpuPos, "cpu");
			while(playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			printGameBoard(gameBoard);
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
		}
	}
	
	
	public static void placePiece(char[][] gameBoard, int pos, String User) {
		char symbol = ' ';
		if(User.equals("player")) {
			symbol = 'X';
			playerPosition.add(pos);
		} else if(User.equals("cpu")) {
			symbol = 'O';
			cpuPosition.add(pos);
		}
		switch(pos) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		default:
			break;
		}			
	}
	
	
	public static void printGameBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char el : row) {
				System.out.print(el);
			}
			System.out.println();
		}
	};
	
	
	public static String checkWinner() {
		List topRow = Arrays.asList(1,2,3);
		List middleRow = Arrays.asList(4,5,6);
		List bottomRow = Arrays.asList(7,8,9);
		List leftCol= Arrays.asList(1,4,7);
		List rightCol= Arrays.asList(3,6,9);
		List middleCol= Arrays.asList(2,5,8);
		List diag1 = Arrays.asList(1,5,9);
		List diag2 = Arrays.asList(3,5,7);
		List<List> winning = new ArrayList<List>();
		
		winning.add(diag2);
		winning.add(diag1);
		winning.add(middleCol);
		winning.add(rightCol);
		winning.add(leftCol);
		winning.add(bottomRow);
		winning.add(middleRow);
		winning.add(topRow);
		
		
		
		for(List l : winning) {
			if(playerPosition.containsAll(l)) {
				return "Congratulations you won";
			} else if(cpuPosition.containsAll(l)) {
				return "CPU won, better luck next time";
			} else if (playerPosition.size() + cpuPosition.size() == 9) {
				return "CAT";
			}
		}
		return "";
	}
}
