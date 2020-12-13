package application;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

public class Main extends Application {
	
	private Stage stage;
	
//	private int hight;
//	private int width;
	
	private int SizeBlock = 40;

	
	public static ArrayList<Rectangle> bonuses;
	
	AnimationTimer timer;
	int a = 0;
	
	Image image;
	ImageView imageView;
	Hero player;

	static Pane root;
	Scene scene;
	
	Enemy enemy = null;
	int EnemyCount = 2;

	static Label lbl = new Label();

	
	static Pane rootLvl;
	public static Maze maze;
	
//	private int levelWidth;
//	int levelNumber = 0;


	public void bonus() {
		int random = (int) Math.floor(Math.random() * 100);
		int x = (int) Math.floor(Math.random() * 568);
		int y = (int) Math.floor(Math.random() * 568);
		if (random == 5) {
			Rectangle rect = new Rectangle(20, 20, Color.RED);
			rect.setX(x);
			rect.setY(y);
			bonuses.add(rect);
			root.getChildren().addAll(rect);
		}
	}

	public void bullet(int one) {

		one = one % 5;
		if (one == 1) {
			double x = player.getTranslateX() + 15;
			double y = player.getTranslateY() + 15;
			Ellipse elipse = new Ellipse(5, 5);
			elipse.setCenterX(x);
			elipse.setCenterY(y);
			elipse.setFill(Color.RED);
			root.getChildren().addAll(elipse);

			if (player.animation.getOffsetY() == 64)
				Bullet.bulletsR.add(elipse);
			if (player.animation.getOffsetY() == 32)
				Bullet.bulletsL.add(elipse);
			if (player.animation.getOffsetY() == 0)
				Bullet.bulletsU.add(elipse);
			if (player.animation.getOffsetY() == 96)
				Bullet.bulletsD.add(elipse);

		}

	}

//	public void enemyAdd() {
//
////		for (int i = 0; i < count; i++) {
//			Image imageEnemy = new Image(getClass().getResourceAsStream("enemy.png"));
//			ImageView imageViewEnemy = new ImageView(imageEnemy);
//			enemy = new Enemy(imageViewEnemy);
//			enemy.setLayoutX(122);
//			enemy.setLayoutY(122);
//			enemys.add(enemy);
//			rootLvl.getChildren().add(enemy);
//			enemy.animation.play();
////		}
//		
//	}

//	public void enemyMove() {
////		System.out.println(EnemyCount);
//		for (int i = 0; i < EnemyCount; i++)
//			enemys.get(i).moveX(i + 1);
//	}

	public void update(Stage primaryStage) {
//		
//		if (isPressed(KeyCode.UP)) {
//			player.animation.play();
//			player.animation.setOffsetY(96);
//			player.moveY(-2);
//		} else if (isPressed(KeyCode.DOWN)) {
//			player.animation.play();
//			player.animation.setOffsetY(0);
//			player.moveY(2);
//		} else if (isPressed(KeyCode.RIGHT)) {
//			player.animation.play();
//			player.animation.setOffsetY(64);
//			player.moveX(2);
//		} else if (isPressed(KeyCode.LEFT)) {
//			player.animation.play();
//			player.animation.setOffsetY(32);
//			player.moveX(-2);
//		} else {
//			
//			player.animation.stop();
//		}
//		
//		if (isPressed(KeyCode.CONTROL)) {
//			a++;
//			bullet(a);
//		} else 
//			a = 0;
//		playerGetCash(primaryStage);
//		Bullet.BulletRemove();
//		lbl.setText("Score: " + Bullet.getScore());
//		
////		enemyMove();
	}

	

	public void victory() {
		Label secondLabel = new Label("Viktory!!!");
		secondLabel.setFont(new Font(50));
		Pane secondaryLayout = new Pane();
		secondaryLayout.getChildren().add(secondLabel);
		
//		clear();
		Settings.keys.clear();

		Scene secondScene = new Scene(secondaryLayout, 200, 100);

		Button btn = new Button("Next Lvl");
		btn.setLayoutX(50);btn.setLayoutY(75);
		btn.setPrefSize(100, 20);
		secondaryLayout.getChildren().add(btn);
		
		
		Stage newWindow = new Stage();
		newWindow.setTitle("Viktory!!!");
		newWindow.setScene(secondScene);

		btn.setOnAction(e->{
			newWindow.close();						
			restart();
		});
		
		
		newWindow.initModality(Modality.WINDOW_MODAL);
		newWindow.setResizable(false);
		newWindow.setOnCloseRequest(e->{
			restart();
		});
		
		newWindow.initOwner(stage);

		newWindow.setX(stage.getX() + 200);
		newWindow.setY(stage.getY() + 100);

		newWindow.show();
	}
		
	private void restart() {
		try {
			clear();
			Settings.width += 2;
//			hight += 2;
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	private void clear() {
		
		
		player = new Hero(imageView);
		timer.stop();
		
		maze.enemys.clear();
		
		rootLvl.getChildren().clear();
		rootLvl.setLayoutX(0);
		root.getChildren().clear();
		
	}



	public void Delete() {
	}

		
	public void playerProperty() {
		
		image = new Image(getClass().getResourceAsStream("hero.png"));
		imageView = new ImageView(image);
		player = new Hero(imageView);
	        
	        rootLvl.getChildren().add(player);
	}
	
	private void addMenu() {
		Rectangle menuArea = new Rectangle(601, 0, 200, 600);
		menuArea.setFill(Color.CADETBLUE);
		menuArea.setStroke(Color.BLACK);
		root.getChildren().add(menuArea);
		
		lbl.setText("Score: " + Bullet.getScore());
		lbl.setLayoutX(650);
		lbl.setTextFill(Color.BLACK);
		lbl.setFont(new Font(20));
		
		root.getChildren().add(lbl);
	}

	public static void main(String[] args) {
		launch(args);
	}
	


	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		
		root = new Pane();
		rootLvl = new Pane();
		root.setPrefSize(800, 600);
		scene = new Scene(root);
		bonuses = new ArrayList<>();
		
		

		playerProperty();
		
		
				
		maze = new Maze(Settings.hight,Settings.width,SizeBlock);
		rootLvl.setPrefSize(600, 600);
		rootLvl.getChildren().add(maze);
		root.getChildren().add(rootLvl);
		
		addMenu();
		
		
		lbl.textProperty().addListener(listener->{
			victory();
		});
		
		
//		scene = new Scene(root);
		scene.setOnKeyPressed(event -> 
			Settings.keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> {
			Settings.keys.put(event.getCode(), false);
		});
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				player.move();
//				update(stage);
//				bonus();
			}
		};
		timer.start();
		stage.setTitle("Game");
		stage.setScene(scene);
		stage.show();

	}

	

}
