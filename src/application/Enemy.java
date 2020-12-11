package application;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class Enemy extends Pane {

	ImageView imageViewEnemy;
	int count = 3;
	int columns = 3;
	int offsetX = 0;
	int offsetY = 0;
	int width = 36;
	int height = 36;
	int score = 0;

	int x, y, way;
	boolean isX;

	Ellipse removeRect = null;
	SpriteAnimation animation;

	public Enemy(ImageView imageViewEnemy, int x, int y, double way, boolean isX) {
		this.x = x * 40 + 2;
		this.y = y * 40 + 2;
		this.way = way > 0 ? 1 : -1;
		this.isX = isX;

		this.imageViewEnemy = imageViewEnemy;
		this.imageViewEnemy.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageViewEnemy, Duration.millis(200), count, columns, offsetX, offsetY, width,
				height);
		getChildren().addAll(imageViewEnemy);
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
		animation.play();
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
    			animation.setOffsetY(72);
    		else
    			animation.setOffsetY(36);
    	
    		setTranslateX(this.getTranslateX() + way);
    		}
    	else {
    		if (way > 0)
    			animation.setOffsetY(0);
    		else
    			animation.setOffsetY(108);
    		setTranslateY(this.getTranslateY() + way);
    	}
            }

	public void isEnemyDestroy() {

	}
}