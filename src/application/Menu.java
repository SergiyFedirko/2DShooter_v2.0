package application;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu extends Pane{
	
	private Label namePlayer = new Label();

	private static Label score = new Label("Score: 0");
	
	private int x = 20;
	
	public Menu(String name) {
		
		setNamePlayer(name);
		
		setLayout();
		
		setSize();
		
		setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
		
		addScore();
		
		addName();
	}
	
	private void addName() {
		namePlayer.setTranslateX(x); namePlayer.setTranslateY(20); 
		
		namePlayer.setTextFill(Color.BLACK);
		
		namePlayer.setFont(new Font(20));
		
		this.getChildren().add(namePlayer);
	}

	private void setNamePlayer(String name) {
		
		namePlayer.setText("Name: " + name);
		
	}

	protected void setSize() {
		
		setPrefSize(200, 600);
		
	}

	protected void setLayout() {
		
		setLayoutX(600);
		
		setLayoutY(0);
	}
	
	public static void setScore() {
		
		score.setText("Score: " + Settings.getScore());
		
	}

	protected void addScore() {
		
		score.setTranslateX(x); score.setTranslateY(70); 
		
		score.setTextFill(Color.BLACK);
		
		score.setFont(new Font(20));
		
		this.getChildren().add(score);
		
	}
	
}
