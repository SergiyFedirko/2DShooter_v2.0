package application;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartPane extends Pane{
	
	double hight, width;
	
	protected TextField namePlayer = new TextField() {
		public void replaceText(int start, int end, String text) {
			super.replaceText(start, end, text.toUpperCase());
		};
	};
	
	public Button start = new Button("Start");
	
	private Label nameIsNull;
	
	AnimationTimer textTime = new AnimationTimer() {
		int time = 0;
		@Override
		public void handle(long now) {
//			if(nameIsNull.getOpacity()==1)
//				System.out.println(12);
			
			if(time< 100)
				time++;
			else {
				time = 0;
				textTime.stop();
			}
				
	
	if((time/10)%2>0)
	nameIsNull.setOpacity(0.2);
	else
	nameIsNull.setOpacity(1);
			
		}
	};

	public StartPane(double width, double hight) {
		
		this.hight = hight;
		this.width = width;
		
		property();
		
		textFieldProperty();
	
		buttonProperty();
		
		textlabelProperty();
		
		getChildren().addAll(namePlayer, start);
	}
	
	private void textlabelProperty() {
		
		nameIsNull = new Label("Please, write your name");
		
		nameIsNull.setOpacity(1);
		
		nameIsNull.setPrefWidth(200);
		
		nameIsNull.setAlignment(Pos.CENTER);
		
		nameIsNull.setTextFill(Color.DARKRED);
		
		nameIsNull.setStyle("-fx-font-weight: bold");
		
		nameIsNull.setFont(new Font(15));
		
		nameIsNull.setTranslateX(namePlayer.getTranslateX());
		
		nameIsNull.setTranslateY(namePlayer.getTranslateY() - 30);
		
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
		
//		namePlayer.setTextFormatter(new TextFormatter<>((change)-> {
//			change.setText(change.getText().toUpperCase());
//			return change;
//		}));
		
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

	public void addTextLabel() {
		
		if(getChildren().contains(nameIsNull))
			getChildren().remove(nameIsNull);
		
		getChildren().add(nameIsNull);
		
		textTime.start();
	}
}
