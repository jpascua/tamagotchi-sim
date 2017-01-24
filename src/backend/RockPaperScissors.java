package backend;

public class RockPaperScissors {
	
	/*
	 * rock = 0
	 * paper = 1
	 * scissors = 2
	 */
	
	private int prizeTamacoin;
	private int winningPrize = 100;
	private int losingPrize = -25;
	private int tiePrize = 0;
	
	public RockPaperScissors() { }
	
	public int getPrizeTamacoin() {
		return this.prizeTamacoin;
	}
	
	public String getResult(int user) {
		int computer = (int)(Math.random() * 3);
		
		if (user == 0) {
			if (computer == 1) {
				this.prizeTamacoin = this.losingPrize;
				return "Computer chooses paper. You lost " + Math.abs(this.losingPrize) + " Tamacoin.";
			}  else if (computer == 2) {
				this.prizeTamacoin = this.winningPrize;
				return "Computer chooses scissors. You win " + this.winningPrize + " Tamacoin.";
			} else {
				this.prizeTamacoin = this.tiePrize;
				return "Computer chooses rock. Tie. You earned  " + this.tiePrize + " Tamacoin.";
			}
		}
		
		if (user == 1) {
			if (computer == 0) {
				this.prizeTamacoin = this.winningPrize;
				return "Computer chooses rock. You win " + this.winningPrize + " Tamacoin.";
			} else if (computer == 2) {
				this.prizeTamacoin = this.losingPrize;
				return "Computer chooses scissor. You lost " + Math.abs(this.losingPrize) + " Tamacoin.";
			} else {
				this.prizeTamacoin = this.tiePrize;
				return "Computer chooses paper. Tie. You earned " +  this.tiePrize + " Tamacoin.";
			}
		}
		
		if (user == 2) {
			if (computer == 0) {
				this.prizeTamacoin = this.losingPrize;
				return "Computer chooses rock. You lost  " + Math.abs(this.losingPrize) + " Tamacoin.";
			} else if (computer == 1) {
				this.prizeTamacoin = this.winningPrize;
				return "Computer chooses paper. You win " + this.winningPrize + " Tamacoin.";
			} else {
				this.prizeTamacoin = this.tiePrize;
				return "Computer chooses scissors. Tie. You earned " + this.tiePrize + " Tamacoin.";
			}
		}
		
		return "";
	}

}
