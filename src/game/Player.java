package game;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import game.Player;

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
	
	public static ArrayList<Player> getPlayers() 
	{
		ArrayList<Player> arr = new ArrayList<Player>() ; 
		Player tempPlayer ; 
		ObjectInputStream input = null ; 
		try {
			
			File input_file = new File(System.getProperty("user.dir") + File.separator + "chess_game_data.dat") ;
			input = new ObjectInputStream(new FileInputStream(input_file)) ; 
			
			try {
				
				while(true) {
					tempPlayer = (Player)input.readObject() ; 
					arr.add(tempPlayer) ; 
				}
				
			} catch (EOFException e) {
				
				input.close() ; 
				
			}
			
		} catch (FileNotFoundException e) {

			arr.clear() ; 
			return arr ; 
			
		} catch (IOException e) {
			
			e.printStackTrace() ; 
			try {
				
				input.close() ; 
				
			} catch (Exception e2) {}
			JOptionPane.showMessageDialog(null, "Unable to read game files !!") ; 
		
		} catch(ClassNotFoundException e) {
			
			e.printStackTrace() ; 
			JOptionPane.showMessageDialog(null, "Game Data File corrupted , click OK to build new files") ; 
			
		} catch (Exception e) {
			
			e.printStackTrace() ; 
		
		}
		
		return arr ; 
	}
	
	public void UpdatePlayer() {
		
		ObjectInputStream input = null ; 
		ObjectOutputStream output = null ; 
		Player tempPlayer ;
		File input_file = null ; 
		File output_file = null ; 
		
		try {
		
			input_file = new File(System.getProperty("user.dir") + File.separator + "chess_game_data.dat") ;
			output_file = new File(System.getProperty("user.dir")+ File.separator + "tempfile.dat");
			
		} catch (SecurityException e) {

			JOptionPane.showMessageDialog(null, "Read-Write Permission Denied !! Program Cannot Start") ;
			System.exit(0) ; 
	
		}
		
		boolean Notexists = false ; 
		
		try {
			
			if(!output_file.exists())
				output_file.createNewFile();
			if(!input_file.exists()) {
				
				output = new ObjectOutputStream(new java.io.FileOutputStream(output_file,true)) ;
				output.writeObject(this) ; 
			
			}
			else {
			
				input = new ObjectInputStream(new FileInputStream(input_file));
				output = new ObjectOutputStream(new FileOutputStream(output_file));
				Notexists = true ;
				try
				{
					while(true)
					{
						tempPlayer = (Player)input.readObject();
						if (tempPlayer.getName().equals(getName()))
						{
							output.writeObject(this);
							Notexists = false;
						}
						else
							output.writeObject(tempPlayer);
					}
				}
				catch(EOFException e){
					input.close();
				}
				if(Notexists)
					output.writeObject(this);
				
			}
			
			input_file.delete();
			output.close();
			File newf = new File(System.getProperty("user.dir")+ File.separator + "chess_game_data.dat");
			
			if(output_file.renameTo(newf) == false)
				System.out.println("File Renaming Unsuccessful");
	
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to read/write the required Game files !! Press ok to continue");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
		}
		catch (Exception e)
		{
			
		}
	}
}
