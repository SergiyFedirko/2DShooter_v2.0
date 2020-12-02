package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

	private HashMap<KeyCode, Boolean> keys = new HashMap<>();
	public static ArrayList<Rectangle> bonuses = new ArrayList<>();
//	public static ArrayList<Enemy> enemys = new ArrayList<>();
//	public static ArrayList<Block> platforms = new ArrayList<>();
	
	int a = 0;
	
	static int BLOCK_SIZE = 50;
	
	Image backgroundImg = new Image(getClass().getResourceAsStream("backgroundImg.jpg"));

	Image image = new Image(getClass().getResourceAsStream("hero.png"));
	ImageView imageView = new ImageView(image);
	Character player = new Character(imageView);

//	Enemy enemy = null;
	int EnemyCount = 2;

	Label lbl = new Label();

	static Pane root = new Pane();
	static GridPane rootLvl = new GridPane();
	
//	private int levelWidth;
	int levelNumber = 0;


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
			double x = player.getX() + 15;
			double y = player.getY() + 15;
			Ellipse elipse = new Ellipse(5, 5);
			elipse.setCenterX(x);
			elipse.setCenterY(y);
			elipse.setFill(Color.RED);
			rootLvl.getChildren().addAll(elipse);

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

//	public void enemyAdd(int count) {
//
//		for (int i = 0; i < count; i++) {
//			Image imageEnemy = new Image(getClass().getResourceAsStream("enemy.png"));
//			ImageView imageViewEnemy = new ImageView(imageEnemy);
//			enemy = new Enemy(imageViewEnemy);
//			enemys.add(enemy);
//			rootLvl.getChildren().add(enemy);
//			enemy.animation.play();
//		}
//		
//	}

//	public void enemyMove() {
////		System.out.println(EnemyCount);
//		for (int i = 0; i < EnemyCount; i++)
//			enemys.get(i).moveX(i + 1);
//	}

	public void update() {

		
		
		if (isPressed(KeyCode.UP)) {
			player.animation.play();
			player.animation.setOffsetY(96);
			player.moveY(-2);
		} else if (isPressed(KeyCode.DOWN)) {
			player.animation.play();
			player.animation.setOffsetY(0);
			player.moveY(2);
		} else if (isPressed(KeyCode.RIGHT)) {
			player.animation.play();
			player.animation.setOffsetY(64);
			player.moveX(2);
		} else if (isPressed(KeyCode.LEFT)) {
			player.animation.play();
			player.animation.setOffsetY(32);
			player.moveX(-2);
		} else if (isPressed(KeyCode.CONTROL)) {
			a++;
			bullet(a);
		} else {
			a = 0;
			player.animation.stop();
		}
		playerGetCash();
		Bullet.BulletRemove();
		lbl.setText("Score: " + Bullet.getScore());
		
//		enemyMove();
	}

	private void playerGetCash() {
		if(player.getBoundsInParent().intersects(gd.cash.getBoundsInParent())) {
//			score++;
			
			gd.addCash();
//			Bullet.addScore(1);
		}
	}

	public boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	public void Delete() {
	}

	private void initContent() {

		root.setPrefSize(600, 600);
//		root.getChildren().addAll(player);

		Rectangle area = new Rectangle(0, 0, 401, 401);
		area.setFill(Color.TRANSPARENT);
		area.setStroke(Color.BLACK);
		root.getChildren().addAll(area);

		lbl.setText("Score: " + Bullet.getScore());
		lbl.setLayoutX(450);
		lbl.setTextFill(Color.BLACK);
		lbl.setFont(new Font(20));
		root.getChildren().add(lbl);
		
		
//		enemyAdd(EnemyCount);
		
		
		initial();

	}
	
	
	
	public void initial() {
		
			
		
		
		
		
		
		
//		 ImageView backgroundIV = new ImageView(backgroundImg);
//	        backgroundIV.setFitHeight(450);
//	        backgroundIV.setFitWidth(600);

//	        LvlData.initLvl(levelNumber, BLOCK_SIZE);
//	        levelWidth = LvlData.levels[levelNumber].length*BLOCK_SIZE;
//	        player =new Character();
	        player.setTranslateX(42);
	        player.setTranslateY(42);
	        player.translateXProperty().addListener((obs,old,newValue)->{
	        	
	        	  gd.getWalls().forEach(e->{
	        		  if (player.getBoundsInParent().intersects(e.getBoundsInParent())) {
//	        		  System.out.println(obs.getValue());
//	        		  System.out.println(old.doubleValue());
//	        		  System.out.println(newValue.doubleValue());
//	        		  System.out.println(e.getX());
//	        		  System.out.println(obs.getValue().doubleValue() == (e.getX()-32));
	        		 if(newValue.doubleValue() == (e.getX()-32)) 
	        			 player.setTranslateX(old.doubleValue());
	        		 if(newValue.doubleValue() == (e.getX())+40) 
	        			 player.setTranslateX(old.doubleValue());
	        		 
//	        			 newValue = e.getX()-32;
	        		  }
//		    			if(obs.getValue().equals(e.getX())) {
//		    			if(player.getTranslateY()== e.getY()-31)
//		    				player.setTranslateY(e.getY()-32);
//		    		if(player.getTranslateY() == e.getY()+39) 
//		    			player.setTranslateY(e.getY()+40);
//		    		
//		    				
//		    			if( player.getTranslateX() == e.getX()-31)
//		    				player.setTranslateX(e.getX()-32);
//		    			else
//		    				if(player.getTranslateX() == e.getX()+39)
//		    					player.setTranslateX(e.getX()+40);
//		    			}
		    		});
	        });
	        player.translateYProperty().addListener((obs,old,newValue)->{
//	            int offset = newValue.intValue();
//	            player.isBonuseEat();
	            gd.getWalls().forEach(e->{
	    			if(player.getBoundsInParent().intersects(e.getBoundsInParent())) {
	    				 if(newValue.doubleValue() == (e.getY()-32)) 
		        			 player.setTranslateY(old.doubleValue());
	    				 if(newValue.doubleValue() == (e.getY())+40) 
		        			 player.setTranslateY(old.doubleValue());
	    			}
	    		});
	            
//	            if(offset>0 && offset<levelWidth){
//	            	
//	                rootLvl.setLayoutY(-(offset-250));
////	                System.out.println(offset);
////	                System.out.println(levelWidth);
//	                backgroundIV.setLayoutY(-(offset-240));
//	            }
	        });
	        root.getChildren().add(player);
//	        root.getChildren().addAll(backgroundIV,rootLvl);
//	        root.getChildren().addAll(rootLvl);
	}
	
	public static Maze gd = new Maze();

	@Override
	public void start(Stage primaryStage) throws Exception {

		initContent();

		
		
//		gd.setPrefSize(40*5, 40*5);
		
//		System.out.println(gd.);
		
		root.getChildren().add(gd);
		
		
		Scene scene = new Scene(root);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> {
			keys.put(event.getCode(), false);
		});
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
//				bonus();
			}
		};
		timer.start();
		primaryStage.setTitle("Game");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
