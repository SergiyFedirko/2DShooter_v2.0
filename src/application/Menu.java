package application;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends Pane{
	
	private Label namePlayer = new Label();

	private static Label score = new Label("Score: 0");
	
	private int x = 20;
	
	private ImageView imageView = new ImageView(new Image("img/bullet.png"));
	
	private static Label countBullets = new Label("20");
	
	public Menu(String name) {
		
		setNamePlayer(name);
		
		setLayout();
		
		setSize();
		
		setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
		
		addLifeBar();
		
		addScore();
		
		addName();
		
		addCountBullets();
		
	}
	
	private void addLifeBar() {
//		HeartBar bar = new HeartBar();
		
		Start.heartBar.setTranslateX(x);
		
		Start.heartBar.setTranslateY(60);
		
		getChildren().add(Start.heartBar);
		
	}

	private void addCountBullets() {
		
		imageView.setTranslateX(x);		imageView.setTranslateY(150);
		
		imageView.setFitWidth(20);
		
		imageView.setFitHeight(20);
		
		countBullets.setTranslateX(x+40);		countBullets.setTranslateY(150);
		
		countBullets.setTextFill(Color.BLACK);
		
		countBullets.setFont(new Font(20));
		
		getChildren().addAll(imageView, countBullets);
		
	}
	
	public static int getCountBullets() {
		return Integer.parseInt(countBullets.getText());
	}
	
	public static void setCountBullets(int count) {
		countBullets.setText(Integer.toString(count));
	}

	private void addName() {
		namePlayer.setTranslateX(x); namePlayer.setTranslateY(20); 
		
		namePlayer.setTextFill(Color.BLACK);
		
		namePlayer.setFont(new Font(20));
		
		getChildren().add(namePlayer);
	}

	private void setNamePlayer(String name) {
		
//		name = name.toUpperCase();
		
		Label text = new Label(name);
		
		text.setStyle("-fx-font-weight: bold");
		text.setFont(new Font(20));
		
		text.setTextFill(Color.BLACK);
		
		namePlayer.setText("Name: ");
		
		text.setTranslateX(x+60);
		text.setTranslateY(20);
		
		getChildren().add(text);
		
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
		
		score.setTranslateX(x); score.setTranslateY(120); 
		
		score.setTextFill(Color.BLACK);
		
		score.setFont(new Font(20));
		
		this.getChildren().add(score);
		
	}
	
}
