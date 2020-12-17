package application;

public class GameOver extends EndGame {

	public GameOver() {
		
		secondLabel.setText("You lose!");

		btn.setText("Restart ->");
		
		super.setTitle("GameOver");
	}

}
