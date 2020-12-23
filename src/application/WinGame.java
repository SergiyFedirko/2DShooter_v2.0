package application;

public class WinGame extends EndGame{

	public WinGame() {
		
		secondLabel.setText("You Win!");
		
		buttonAction();
		
		btn.setText("Next ->");
		
		super.setTitle("Victory");
		
	}
	
private void buttonAction() {
		
		btn.setOnAction(e -> {
			
			close();
			
			Start.restart();
			
		});
		
	}
}
