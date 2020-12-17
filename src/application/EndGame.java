package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EndGame extends Stage {

	Label secondLabel = new Label();
	
	Button btn = new Button();;
	
	Scene secondScene;

	public EndGame() {
		Pane secondaryLayout = new Pane();
		
		secondScene = new Scene(secondaryLayout, 200, 100);

		addLabel();
		
		addButton();
		
		closeWindow();
		
		buttonAction();
		
		secondaryLayout.getChildren().addAll(secondLabel,btn);

//		setTitle("123");
		
		setScene(secondScene);

		initModality(Modality.WINDOW_MODAL);
		
		setResizable(false);
		
		initOwner(Start.stage);

		show();
	}

	private void closeWindow() {
		
		setOnCloseRequest(e -> {
			
			Start.stage.close();
			
		});
		
	}

	private void buttonAction() {
		
		btn.setOnAction(e -> {
			
			close();
			
			Start.restart();
			
		});
		
	}

	private void addButton() {
		
		btn.setLayoutX(50);
		
		btn.setLayoutY(75);
		
		btn.setPrefSize(100, 20);
		
	}

	private void addLabel() {
		
		secondLabel.setFont(new Font(30));
		
	}
}
