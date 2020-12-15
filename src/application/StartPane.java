package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StartPane extends Pane{
	
	double hight, width;
	
	protected TextField namePlayer = new TextField();
	
	public Button start = new Button("Start");

	public StartPane(double width, double hight) {
		
		this.hight = hight;
		this.width = width;
		
		property();
		
		textFieldProperty();
	
		buttonProperty();
		
		getChildren().addAll(namePlayer, start);
	}
	
	public String getNamePlayer() {
		return namePlayer.getText();
	}

	protected void buttonProperty() {
		
		start.setPrefSize(100, 40);

		start.setTranslateX(width/2 - start.getPrefWidth()/2);
		
		start.setTranslateY(hight/2 - 50);
		
		
		
	}

	protected void textFieldProperty() {
		
		namePlayer.setPromptText("Write your name");
		
		namePlayer.setFocusTraversable(false);
		
		namePlayer.setPrefWidth(200);
		
		namePlayer.setTranslateX(width/2 - namePlayer.getPrefWidth()/2);
		
		namePlayer.setTranslateY(hight/2 - 100);
		
		namePlayer.setAlignment(Pos.CENTER);
	}

	protected void property() {
		
		setPrefSize(width, hight);
		
		setBackground(new Background(new BackgroundFill(Color.DARKCYAN, null, null)));
		
	}
}
