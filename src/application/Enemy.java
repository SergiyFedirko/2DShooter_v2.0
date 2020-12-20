package application;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class Enemy extends Characters {

//	private static Image image = new Image("img/enemy.png");
//	private static ImageView imageViewEnemy = new ImageView(image);
	
	private static int width = 36;
	private static int hight = 36;

	private int x, y, way;
	private boolean isX;

	public Enemy(ImageView imageViewEnemy, int x, int y, double way, boolean isX) {
		
		super(imageViewEnemy, hight, width);
		
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
		Start.maze.getWalls().forEach(wall -> {
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
		Start.maze.getChildren().remove(this);
		Start.maze.enemys.remove(this);

	}
}