package application;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu extends Pane{

	private static Label lbl = new Label();
	
	public Menu() {
		
		setLayoutX(600);
		setLayoutY(0);
		
		setPrefSize(200, 600);
		
		setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
		
//		addArea();
		addLabel();
	}
	
	public static void setScore(int score) {
		lbl.setText("Score: " + score);
	}

	protected void addLabel() {
		lbl.setText("Score: 0");
		lbl.setLayoutX(50);
		lbl.setLayoutY(50);
		lbl.setTextFill(Color.BLACK);
		lbl.setFont(new Font(20));
		this.getChildren().add(lbl);
	}
	
}
