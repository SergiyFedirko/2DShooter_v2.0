package application;

public class GameOver extends EndGame {

	public GameOver() {
		
		secondLabel.setText("You lose!");
		
		buttonAction();

		btn.setText("Restart ->");
		
		super.setTitle("GameOver");
	}
	
private void buttonAction() {
		
		btn.setOnAction(e -> {
			
			close();
			
			Start.gameOver();
			
		});
		
	}

}
