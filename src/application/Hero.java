package application;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Hero extends Characters {
	
	private static int width = 32;
	private static int hight = 32;
	
	
	private static Image image = new Image("img/hero.png");
	private static ImageView imageView = new ImageView(image);
	
	AnimationTimer timer;
	
	public Hero() {
		
		super(imageView, hight, width);

		setCoor();
		
		addListener();
		
		
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				move();
			}
		};
		timer.start();
	}

	public void setCoor() {
		setTranslateX(42);
		setTranslateY(42);
	}

	private void addListener() {
		translateXProperty().addListener((obs, old, newValue) -> {
			Start.maze.getWalls().forEach(e -> {
				if (getBoundsInParent().intersects(e.getBoundsInParent())) {
					if (newValue.doubleValue() == (e.getX() - 32))
						setTranslateX(old.doubleValue());
					if (newValue.doubleValue() == (e.getX()) + 40)
						setTranslateX(old.doubleValue());

				}
			});

			if (newValue.intValue() > 300 && newValue.intValue() < Settings.getWidth() * 40 - 300) {
				Start.rootLvl.setLayoutX(-(newValue.intValue() - 300));}
		});

		translateYProperty().addListener((obs, old, newValue) -> {
			Start.maze.getWalls().forEach(e -> {
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

		if (Settings.isPressed(KeyCode.UP)) {
			super.animation.play();
			super.animation.setOffsetY(96);
			moveY(-2);
		} else if (Settings.isPressed(KeyCode.DOWN)) {
			super.animation.play();
			super.animation.setOffsetY(0);
			moveY(2);
		} else if (Settings.isPressed(KeyCode.RIGHT)) {
			super.animation.play();
			super.animation.setOffsetY(64);
			moveX(2);
		} else if (Settings.isPressed(KeyCode.LEFT)) {
			super.animation.play();
			super.animation.setOffsetY(32);
			moveX(-2);
		} else {
			
			super.animation.stop();
		}
		
		playerGetCash();
		
		Bullet.BulletRemove();
		
//		enemyMove();
	}
	
int i = 0;
	
	private void playerGetCash() {
		if(getBoundsInParent().intersects(Start.maze.coin.getBoundsInParent())) {
//			score++;
			i++;
			Start.maze.addCash();
//			Bullet.addScore(1);
			if(i==1)
			new Start();
			timer.stop();
			i=0;
		}
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
	
	public void fire(KeyCode key) {
		if(key.getName().equals(KeyCode.CONTROL.getName()))
			bullet();
//			System.out.println(key.getName().equals(KeyCode.CONTROL.getName()));
	}
	
	public void bullet() {

		
		
//		one = one % 5;
//		if (one == 1) {
			double x = this.getTranslateX() + 15;
			double y = this.getTranslateY() + 15;
			Ellipse elipse = new Ellipse(5, 5);
			elipse.setCenterX(x);
			elipse.setCenterY(y);
			elipse.setFill(Color.RED);
			Start.rootLvl.getChildren().addAll(elipse);

			if (super.animation.getOffsetY() == 64)
				Bullet.bulletsR.add(elipse);
			if (super.animation.getOffsetY() == 32)
				Bullet.bulletsL.add(elipse);
			if (super.animation.getOffsetY() == 0)
				Bullet.bulletsU.add(elipse);
			if (super.animation.getOffsetY() == 96)
				Bullet.bulletsD.add(elipse);

//		}

	}

	public void isBonuseEat() {

	}

	public void respawn() {
		timer.start();
		
		setTranslateX(42);
		setTranslateY(42);
		
	}
}
