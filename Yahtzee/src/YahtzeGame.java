import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class YahtzeGame {
	File HighScoreTable;
	SingleLinkedList players;  //SLL for players name and score
	SingleLinkedList player1;  //SLL for player 1
	SingleLinkedList player2;  //SLL for player 2
	Player player_1, player_2;
	boolean flag;
	int counter1, counter2, counter3, counter4, counter5, counter6;
	int turn;
	
	YahtzeGame() throws IOException {
		HighScoreTable = new File("HighScoreTable.txt");
		players = new SingleLinkedList();
		player1 = new SingleLinkedList();
		player2 = new SingleLinkedList();
		player_1 = new Player(" ",0);
		player_2 = new Player(" ",0);
		counter1 = 0; counter2 = 0; counter3 = 0; counter4 = 0; counter5 = 0; counter6 = 0;
		turn = 1;
		
		fileOperations();
		start();
	}
	
	void start() throws IOException {
		Scanner s1 = new Scanner(System.in);
		System.out.print("Please enter your name ");
		player_1.setName(s1.nextLine());  //to take name of the player1
		System.out.println();
		
		Scanner s2 = new Scanner(System.in);
		System.out.print("Please enter your name ");
		player_2.setName(s2.nextLine());  //to take name of the player2
		
		//randomize 3 number for each player and write their SLL class
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 3; j++) {
				int randomNumber = rollingDice(1, 6);
				player1.AddNumber(randomNumber);
			}
			for(int j = 0; j < 3; j++) {
				int randomNumber = rollingDice(1, 6);
				player2.AddNumber(randomNumber);
			}
			//to write screen
			System.out.println();
			System.out.println("Turn: " + turn);
			System.out.println("---SLL---");
		    System.out.print(player_1.getName() + " ");
			player1.printListNumber();
			System.out.println("     Score: " + player_1.getScore());
			System.out.print(player_2.getName() + " ");
			player2.printListNumber();
			System.out.println("     Score: " + player_2.getScore());
			System.out.println();
			
			countNumbers(player1);
			calculateScore(player1);
			//to use counters again
			counter1 = 0; counter2 = 0; counter3 = 0; counter4 = 0; counter5 = 0; counter6 = 0;
			countNumbers(player2);
			calculateScore(player2);
			//to use counters again
			counter1 = 0; counter2 = 0; counter3 = 0; counter4 = 0; counter5 = 0; counter6 = 0;
			
			//to write screen
			System.out.println("--new SLL--");
		    System.out.print(player_1.getName() + " ");
		    player1.printListNumber();
		    System.out.println("     Score: " + player_1.getScore());
		    System.out.print(player_2.getName() + " ");
		    player2.printListNumber();
		    System.out.println("     Score: " + player_2.getScore());
		    System.out.println();
		    System.out.println();
			turn++;	
		}
		//to find the winner
		if((int)player_1.getScore() < (int)player_2.getScore()) {
			System.out.println("Winner is " + player_2.getName());
			addHighScoreTable(player_2);
		}
		else if((int)player_1.getScore() >= (int)player_2.getScore()) {
			System.out.println("Winner is " + player_1.getName());
			addHighScoreTable(player_1);
		}
		highScoreTableFile();
		System.out.println();
		//to print high score table
		players.printListPlayer();   
	}
	
	void fileOperations() {
		// file operations for high score table
			
		try {
			Scanner in = new Scanner(HighScoreTable);
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String[] values = line.split(" ");
				Player newPlayer = new Player(values[0],Integer.parseInt(values[1]));
				if (line != null) {
					players.sortedAdd(newPlayer);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//to create a random number
	int rollingDice(int min, int max) {
		 int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);  
			return random_int;
	}
	//to calculate how many times a number repeats for all numbers 
	void countNumbers(SingleLinkedList player) {
		  if (player.head != null) {
			//to prevent head from being lost
			Node temp = player.head;
			//to access all numbers
			while (temp != null) {
				 switch((int)temp.getData()) {
				 case 1:
					 counter1++;
					 break;
				 case 2:
					 counter2++;
					 break;
				 case 3:
					 counter3++;
					 break;
				 case 4:
					 counter4++;
					 break;
				 case 5:
					 counter5++;
					 break;
				 case 6:
					 counter6++;
					 break;
				 }
				 temp = temp.getLink ();
			 }
		 }
	}
	
	void calculateScore(SingleLinkedList player) {
		if (player.head != null) {
			flag = false;
			Boolean isDelete = false;
			//to check does SLL have 1,2,3,4,5,6 at the same time
			if(counter1 > 0 && counter2 > 0 && counter3 > 0 && counter4 > 0 &&
					counter5 > 0 && counter6 > 0 ) {
				flag = true;
				if(player == player1) {
					player_1.setScore((int)player_1.getScore() + 30);
				}
				else {
					player_2.setScore((int)player_2.getScore() + 30);
				}
			}
			//to check is a number repeat more than 3 times or not
			if(counter1 >= 4) {
				//to delete repeating numbers
				deleteNumbers(1,flag,player);
				isDelete = true;
			}
			if(counter2 >= 4) {
				deleteNumbers(2,flag,player);
				isDelete = true;
			}
			if(counter3 >= 4) {
				deleteNumbers(3,flag,player);
				isDelete = true;
			}
			if(counter4 >= 4) {
				deleteNumbers(4,flag,player);
				isDelete = true;
			}
			if(counter5 >= 4) {
				deleteNumbers(5,flag,player);
				isDelete = true;
			}
			if(counter6 >= 4) {
				deleteNumbers(6,flag,player);
				isDelete = true;
			}
			//to delete numbers if only have 1,2,3,4,5,6
			if(counter1 < 4 && counter2 < 4 && counter3 < 4 && counter4 < 4 &&
				counter5 < 4 && counter6 < 4 && flag && !isDelete) {
				if(player.head != null) {
					if(player == player1) {
						for(int i = 1; i <= 6; i++) {
							player1.deleteNumber(i);
							}	
						}
					else {
						for(int i = 1; i <= 6; i++) {
							player2.deleteNumber(i);
						}
					}
				}
			}
		}
	}
	//to find the numbers which will be delete and delete them.
	void deleteNumbers(int number, boolean flag, SingleLinkedList player) {
		//if SLL have 1,2,3,4,5,6 and a number which repeats more than 3 times
		if(flag) {
			//to find the player
			if(player == player1) {
				//to increase score
				player_1.setScore((int)player_1.getScore() + 10);
				if(player.head != null) {
					for(int i = 1; i <= 6; i++) {
						
						if(i == number) {
							
							player1.deleteNumber(i);
							player1.deleteNumber(i);
							player1.deleteNumber(i);
							player1.deleteNumber(i);
						}
						else {
							player1.deleteNumber(i);
						}	
					}
				}
			}
			else {
				player_2.setScore((int)player_2.getScore() + 10);
				if(player.head != null) {
					for(int i = 1; i <= 6; i++) {
						if(i == number) {
							player2.deleteNumber(i);
							player2.deleteNumber(i);
							player2.deleteNumber(i);
							player2.deleteNumber(i);
						}
						else {
							player2.deleteNumber(i);
						}	
					}
				}
			}
			flag = false;
		}
		//if SLL have only a number which repeats more than 3 times
		else {
			if(player == player1) {
				player_1.setScore((int)player_1.getScore() + 10);
				if(player.head != null) {
					for(int i = 1; i <= 4; i++) {
						player1.deleteNumber(number);
					}
				}
			}
			else {
				player_2.setScore((int)player_2.getScore() + 10);
				if(player.head != null) {
					for(int i = 1; i <= 4; i++) {
						player2.deleteNumber(number);
					}
				}
			}
		}
	}
	//to add winner to SLL 
	void addHighScoreTable(Player player) {
		Boolean flag = players.deleteLastPlayer(player);
		if(flag)  players.sortedAdd(player);
	}
	//to add winner to high score table file
	public void highScoreTableFile() throws IOException {  
		if (!HighScoreTable.exists()) {    
			HighScoreTable.createNewFile();   
        }
		String newline = System.lineSeparator();
        FileWriter fileWriter = new FileWriter(HighScoreTable, false);   //it helps to write all high score table again and again.   
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        Node temp = players.head;
        while(temp != null){
        	Player player = (Player) temp.getData();
        	bWriter.write(player.getName() + " " + player.getScore() + newline);
        	temp = temp.getLink();
        }          
        bWriter.close(); 
	}	
}
