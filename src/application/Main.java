package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	

//	private int levelWidth;
//	int levelNumber = 0;

//	public void bonus() {
//		int random = (int) Math.floor(Math.random() * 100);
//		int x = (int) Math.floor(Math.random() * 568);
//		int y = (int) Math.floor(Math.random() * 568);
//		if (random == 5) {
//			Rectangle rect = new Rectangle(20, 20, Color.RED);
//			rect.setX(x);
//			rect.setY(y);
//			bonuses.add(rect);
//			root.getChildren().addAll(rect);
//		}
//	}
//
//	public void bullet(int one) {
//
//		one = one % 5;
//		if (one == 1) {
//			double x = player.getTranslateX() + 15;
//			double y = player.getTranslateY() + 15;
//			Ellipse elipse = new Ellipse(5, 5);
//			elipse.setCenterX(x);
//			elipse.setCenterY(y);
//			elipse.setFill(Color.RED);
//			root.getChildren().addAll(elipse);
//
//			if (player.animation.getOffsetY() == 64)
//				Bullet.bulletsR.add(elipse);
//			if (player.animation.getOffsetY() == 32)
//				Bullet.bulletsL.add(elipse);
//			if (player.animation.getOffsetY() == 0)
//				Bullet.bulletsU.add(elipse);
//			if (player.animation.getOffsetY() == 96)
//				Bullet.bulletsD.add(elipse);
//
//		}
//
//	}

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

	

	public void Delete() {
	}

	

	public static void main(String[] args) {
		launch(args);
	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {

		new Start(primaryStage);

	}

}
