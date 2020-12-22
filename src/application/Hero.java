package application;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Hero extends Characters {
	
	private static int width = 32;
	private static int hight = 32;
	
	
	private static Image image = new Image("img/hero.png");
	private static ImageView imageView = new ImageView(image);
	
	AnimationTimer timer;
	
	AnimationTimer startTime = new AnimationTimer() {
		
		int startTime;
		
		@Override
		public void handle(long now) {
			if(startTime< 100)
				startTime++;
			else {
				startTime = 0;
				Hero.this.startTime.stop();
			}
				
	
	if((startTime/10)%2>0)
	setOpacity(0.2);
	else
	setOpacity(1);
	
//	System.out.println(startTime);
//	System.out.println((startTime/100)%2>0);
			
		}
	};
	
//	int startTime = 0;
	
	public Hero() {
		
		super(imageView, hight, width);
		
		startTime.start();

		setCoor();
		
		addListener();
		
		
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				move();
//				blik();
				
				
				
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
			
			playerGetCash();
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
			playerGetCash();
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
		
		
		collisionWithEnemy();
		
//		Bullet.BulletRemove();
		
//		enemyMove();
	}
	
int i = 0;
	
	private void playerGetCash() {
		if(getBoundsInParent().intersects(Start.maze.coin.getBoundsInParent())) {
//			score++;
			Settings.addScore(1);
			Menu.setScore();
			i++;
			Start.maze.addCash();
//			Bullet.addScore(1);
			if(i==1) {
			timer.stop();
			new Start();
			
			i=0;
			}
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

		
			double x = this.getTranslateX() + 10;
			double y = this.getTranslateY() + 10;
			
			new Bullet(x,y, super.animation.getOffsetY());
			
//			Start.rootLvl.getChildren().addAll(bullet);


	}

	public void isBonuseEat() {

	}

	public void respawn() {
		timer.start();
		startTime.start();
		
		setTranslateX(42);
		setTranslateY(42);
		
	}



	public void collisionWithEnemy() {
		Start.maze.enemys.forEach(enemy->{
			if(getBoundsInParent().intersects(enemy.getBoundsInParent())) {
				startTime.start();
				Start.rootLvl.setLayoutX(0);
		
		setTranslateX(42);
		setTranslateY(42);
		
		Settings.setLife(Settings.getLife()-1);
		
		Start.heartBar.changeLife(Settings.getLife());
			}
		});
	   	
    		
		
		
	}
}
