package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerStuff {
	
	private JLabel label ; 
	Timer countDown ;
	int remainingTime ; 
	
	public TimerStuff(JLabel label) {
		this.label = label ; 
		countDown = new Timer(1000, new countDownListener()) ; 
		remainingTime = Main.TimeRemaining ; 
	}
	
	public void startTimer() {
		countDown.start() ; 
	}
	
	public void resetTimer() {
		remainingTime = Main.TimeRemaining ; 
	}
	
	class countDownListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int minutes , seconds ; 
			if(remainingTime > 0) {
				minutes = remainingTime/60 ; 
				seconds = remainingTime % 60 ;
				String minutes_ = String.valueOf(minutes) ; 
				String seconds_ = String.valueOf(seconds) ; 
				if(seconds < 10) {
					seconds_ = "0" + seconds ; 
				}
				label.setText(minutes_ + ":" + seconds_) ; 
				remainingTime -= 1 ; // since this is called every second 
			}
			else {
				label.setText("Time is Up chutiye") ; 
				resetTimer() ; 
				startTimer() ; 
				((Main) Main.gameBoard).changeTurns() ; 
			}
			
		}
		
	}
	
}
