package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GameData {
	
	private static final GameData INSTANCE = new GameData();

	private GameClosedTimer gameTimer = new GameClosedTimer();
	
	private File file = new File("savedata");

	private double currentStamina;
	private double totalStamina;
	private String mood;
	private String condition;
	private int tamacoin;
	
	//private ChangeInCurrencyListener listener;

	public GameData() {	
		try {
			this.readSaveData();
			this.deductStaminaDueToTimeClosed();
		}
		catch(FileNotFoundException e) {
			System.out.println("Save file not found!");
		}
	}
	
	/*
	public void refreshCurrency() {
		listener.refreshCurrency();
	}
	
	public void setChanceInCurrencyListener(ChangeInCurrencyListener listener) {
		this.listener = listener;
	}
	*/
	
	private void readSaveData() throws FileNotFoundException {
		Scanner input = new Scanner(this.file);
		
		int lineNumber = 0;
		  
		while(input.hasNext()) {
			if(Character.toString(input.next().charAt(0)).equals("#")) {
				// Do nothing!
			}
			else {
				switch(lineNumber) {
					case 1: this.currentStamina = Double.parseDouble(input.next());
							break;
					case 3: this.totalStamina = Double.parseDouble(input.next());
							break;
					case 5: this.mood = input.next();
							break;
					case 7: this.condition = input.next();
							break;
					case 9: gameTimer.setProgramCloseTime(Long.parseLong(input.next()));
							break;
					case 11: this.tamacoin = Integer.parseInt(input.next());
							break;
					default: break;
				}
			}
			
			lineNumber++;
		}
		
		input.close();
	}
	
	public void writeSaveData() throws FileNotFoundException {
		Scanner input = new Scanner(this.file);
		ArrayList<String> lines = new ArrayList<String>();
		
		// Read the whole file
		while(input.hasNextLine()) {
			// Store data in ArrayList
			lines.add(input.nextLine());
		}
		
		input.close();
		
		PrintWriter output = new PrintWriter(this.file);
		
		// Go through ArrayList to replace data and write on text file
		for (int i = 0; i < lines.size(); i++) {
			if(Character.toString(lines.get(i).charAt(0)).equals("#")) {
				// Do nothing!
			}
			else {
				switch(i) {
					case 1: lines.set(i, Double.toString(this.currentStamina));
							break;
					case 3: lines.set(i, Double.toString(this.totalStamina));
							break;
					case 5: lines.set(i, this.mood);
							break;
					case 7: lines.set(i, this.condition);
							break;
					case 9: lines.set(i, Long.toString(this.gameTimer.getCurrentTime()));
							break;
					case 11: lines.set(i, Integer.toString(this.tamacoin));
							break;
					default: break;
				}
			}
			
			output.println(lines.get(i));
		}
		
		output.close();
	}
	
	public void addCurrentStamina(double amount) {
		this.currentStamina += amount;
	}
	
	public void subtractCurrentStamina(double amount) {
		this.currentStamina -= amount;
	}
		
	public void deductStaminaDueToTimeClosed() {
		long minutesPassed = ((gameTimer.getCurrentTime() - gameTimer.getProgramCloseTime())/1000)/60;
		
		double deduction = 0.5 * minutesPassed;
		
		this.currentStamina -= deduction;
		
		this.calculateMood();
		this.calculateCondition();
	}
	
	public int calculateStaminaPercentage() {
		double percentage = this.currentStamina * 100/this.totalStamina;
		
		if(percentage < 0) {
			this.currentStamina = 0;
		}
		
		if(percentage > 100) {
			this.currentStamina = 100;
		}
		
		return (int)(this.currentStamina * 100/this.totalStamina);
	}
	
	public String getMood() {
		return this.mood;
	}
	
	public String calculateMood() {
		double percentage = this.calculateStaminaPercentage();
		
		if(percentage <= 40) {
			this.mood = "Sad";
		} 
		else if (percentage <= 70) {
			this.mood = "Neutral";
		} 
		else {
			this.mood = "Happy";
		}
		
		return this.mood;
	}
	
	public void setCondition(String s) {
		this.condition = s;
	}

	public String getCondition() {
		return this.condition;
	}
	
	public String calculateCondition() {
		int number = (int)(Math.random() * 100) + 1;
		
		if(this.mood.equals("Neutral")) {
			if (number <= 15) {
				this.condition = "Sick";
			}
		} 
		else if(this.mood.equals("Sad")) {
			if (number <= 30) {
				this.condition = "Sick";
			} 
		}
		
		return this.condition;
	}

	public void setTamacoin(int i) {
		this.tamacoin = i;
	}
	
	public int getTamacoin() {
		return this.tamacoin;
	}
	
	public void addTamacoin(int i) {
		this.tamacoin += i;
	}
	
	public void subtractTamacoin(int i) {
		this.tamacoin -= i;
	}
	
	public int calculateRemainingTamacoin(int cost) {
		return this.tamacoin - cost;
	}
	
	public String getImage() {
		if(!this.condition.equals("Sick")) {
			if(this.mood.equals("Happy")) {
				return "images/happy.gif";
			} else if(this.mood.equals("Neutral")) {
				return "images/neutral.gif";
			} else {
				return "images/sad.gif";
			}
		} else {
			return "images/sick.gif";
		}
	}
	
	public static GameData getInstance() {
		return INSTANCE;
	}

}
