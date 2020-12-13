package application;

import java.util.HashMap;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Hero extends Characters {
	ImageView imageView;
	int count = 3;
	int columns = 3;
	int offsetX = 0;
	int offsetY = 0;
	int width = 32;
	int hight = 32;
	

	public Hero(ImageView imageView) {
		
		
		super(imageView, 32, 32);

		setTranslateX(42);
		setTranslateY(42);
		
		addListener();
	}

	private void addListener() {
		translateXProperty().addListener((obs, old, newValue) -> {
			Main.maze.getWalls().forEach(e -> {
				if (getBoundsInParent().intersects(e.getBoundsInParent())) {
					if (newValue.doubleValue() == (e.getX() - 32))
						setTranslateX(old.doubleValue());
					if (newValue.doubleValue() == (e.getX()) + 40)
						setTranslateX(old.doubleValue());

				}
			});

			if (newValue.intValue() > 300 && newValue.intValue() < Settings.width * 40 - 300)
				Main.rootLvl.setLayoutX(-(newValue.intValue() - 300));
		});

		translateYProperty().addListener((obs, old, newValue) -> {
			Main.maze.getWalls().forEach(e -> {
				if (getBoundsInParent().intersects(e.getBoundsInParent())) {
					if (newValue.doubleValue() == (e.getY() - 32))
						setTranslateY(old.doubleValue());
					if (newValue.doubleValue() == (e.getY()) + 40)
						setTranslateY(old.doubleValue());
				}
			});
		});

	}
	
	public void move() {

		if (isPressed(KeyCode.UP)) {
			super.animation.play();
			super.animation.setOffsetY(96);
			moveY(-2);
		} else if (isPressed(KeyCode.DOWN)) {
			super.animation.play();
			super.animation.setOffsetY(0);
			moveY(2);
		} else if (isPressed(KeyCode.RIGHT)) {
			super.animation.play();
			super.animation.setOffsetY(64);
			moveX(2);
		} else if (isPressed(KeyCode.LEFT)) {
			super.animation.play();
			super.animation.setOffsetY(32);
			moveX(-2);
		} else {
			
			super.animation.stop();
		}
		
		playerGetCash();
		Bullet.BulletRemove();
		Main.lbl.setText("Score: " + Bullet.getScore());
		
//		enemyMove();
	}
	
int i = 0;
	
	private void playerGetCash() {
		if(getBoundsInParent().intersects(Main.maze.coin.getBoundsInParent())) {
//			score++;
			i++;
			Main.maze.addCash();
//			Bullet.addScore(1);
//			if(i==1)
//			Main.victory();
		}
		}
	
	public boolean isPressed(KeyCode key) {
		return Settings.keys.getOrDefault(key, false);
	}

	public void moveX(int x) {
		boolean right = x > 0 ? true : false;

		for (int i = 0; i < Math.abs(x); i++) {
			if (right) {
				this.setTranslateX(this.getTranslateX() + 1);
			} else if (this.getTranslateX() > 0) {

				this.setTranslateX(this.getTranslateX() - 1);
			}
		}
	}

	public void moveY(int y) {
		boolean down = y > 0 ? true : false;

		for (int i = 0; i < Math.abs(y); i++) {
			if (down)
				this.setTranslateY(this.getTranslateY() + 1);
			else if (this.getTranslateY() > 0)
				this.setTranslateY(this.getTranslateY() - 1);
		}
	}

	public void isBonuseEat() {

	}
}
