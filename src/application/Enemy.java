package application;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class Enemy extends Characters {

	ImageView imageViewEnemy;
	int width = 36;
	int height = 36;
	int score = 0;

	int x, y, way;
	boolean isX;

	public Enemy(ImageView imageViewEnemy, int x, int y, double way, boolean isX) {
		
		super(imageViewEnemy, 36, 36);
		
		this.x = x * 40 + 2;
		this.y = y * 40 + 2;
		this.way = way > 0 ? 1 : -1;
		this.isX = isX;

//		addListener();
		setCoor();
	}
	
	private void setCoor() {
		setTranslateX(x);
		setTranslateY(y);
		
	}

	public void startMove() {
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				collisionWithWall();
				move();

			}
		};
		super.animation.play();
		timer.start();
	}


	private void collisionWithWall() {
		Main.maze.getWalls().forEach(wall -> {
			if (this.getBoundsInParent().intersects(wall.getBoundsInParent())) {
				way *= -1;
			}
		});
	}

	public void move(){    	
    	
    	if(isX) {
    		if (way > 0)
    			super.animation.setOffsetY(72);
    		else
    			super.animation.setOffsetY(36);
    	
    		setTranslateX(this.getTranslateX() + way);
    		}
    	else {
    		if (way > 0)
    			super.animation.setOffsetY(0);
    		else
    			super.animation.setOffsetY(108);
    		setTranslateY(this.getTranslateY() + way);
    	}
            }

	public void isEnemyDestroy() {

	}
}