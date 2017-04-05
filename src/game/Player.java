package game;

public class Player {

	private String name ;
	private Integer gamesPlayed ; 
	private Integer gamesWon ; 

	public Player(String name) {
		this.name = name.trim() ; 
		gamesPlayed = 0 ; 
		gamesWon = 0 ; 
	}
	
	public String getName() {
		return name ; 
	}
	
	public double getWinPercentage() {
		
		double temp = gamesWon * 100 ; 
		temp /= gamesPlayed ; 
		return temp ; 
		
	}
	public Integer getGamesPlayed() {
		return gamesPlayed ; 
	}
	
	public Integer getGamesWon() {
		return gamesWon ; 
	}
	
	public void updateGamesPlayed() {
		gamesPlayed += 1 ; 
	}
	
	public void updateGamesWon() {
		gamesWon += 1 ; 
	}
	
}
