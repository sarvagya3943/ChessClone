package game;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import game.Player;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L ; 
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
	/*
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
	*/
	public static ArrayList<Player> getPlayers()         //Function to fetch the list of the players
	{
		Player tempplayer;
		ObjectInputStream input = null;
		ArrayList<Player> players = new ArrayList<Player>();
		try
		{
			File infile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
			input = new ObjectInputStream(new FileInputStream(infile));
			try
			{
				while(true)
				{
					tempplayer = (Player) input.readObject();
					players.add(tempplayer);
				}
			}
			catch(EOFException e)
			{
				input.close();
			}
		}
		catch (FileNotFoundException e)
		{
			players.clear();
			return players;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			try {input.close();} catch (IOException e1) {}
			JOptionPane.showMessageDialog(null, "Unable to read the required Game files !!");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return players;
	}
	
	public void UpdatePlayer()            //Function to update the statistics of a player
	{
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		Player temp_player;
		File inputfile=null;
		File outputfile=null;
		try
		{
			inputfile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
			outputfile = new File(System.getProperty("user.dir")+ File.separator + "tempfile.dat");
		} catch (SecurityException e)
		{
			JOptionPane.showMessageDialog(null, "Read-Write Permission Denied !! Program Cannot Start");
			System.exit(0);
		} 
		boolean playerdonotexist;
		try
		{
			if(outputfile.exists()==false)
				outputfile.createNewFile();
			if(inputfile.exists()==false)
			{
					output = new ObjectOutputStream(new java.io.FileOutputStream(outputfile,true));
					output.writeObject(this);
			}
			else
			{
				input = new ObjectInputStream(new FileInputStream(inputfile));
				output = new ObjectOutputStream(new FileOutputStream(outputfile));
				playerdonotexist=true;
				try
				{
				while(true)
				{
					temp_player = (Player)input.readObject();
					if (temp_player.getName().equals(getName()))
					{
						output.writeObject(this);
						playerdonotexist = false;
					}
					else
						output.writeObject(temp_player);
				}
				}
				catch(EOFException e){
					input.close();
				}
				if(playerdonotexist)
					output.writeObject(this);
			}
			inputfile.delete();
			output.close();
			File newf = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
			if(outputfile.renameTo(newf)==false)
				System.out.println("File Renameing Unsuccessful");
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
