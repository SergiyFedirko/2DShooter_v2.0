package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends ImageView {

	enum Side {
		RIGHT, LEFT, UP, DOWN
	};

	private static HashMap<Side, List<Bullet>> bullets = new HashMap<>();

	private int speed = 5;
	
	AnimationTimer timer = new AnimationTimer() {
		
		@Override
		public void handle(long now) {
			fire();
			
		}

	
	};

	public Bullet() {
		for (Side side : Side.values()) {
			bullets.put(side, new ArrayList<>());
		}		
		
	}
	
//	double x,y;
//	Bullet removeBullet;
	Side side;

private void fire() {
//	bullets.values().
	
//	for (Side side : Side.values()) {
//		bullets.get(side).forEach(bullet->{
			for (int i = 0; i < speed; i++) {
				
			switch (side) {
			case RIGHT:
				setTranslateX(getTranslateX() + 1);
				break;

			case LEFT:
				setTranslateX(getTranslateX() - 1);
				break;
				
			case UP:
				setTranslateY(getTranslateY() - 1);
				break;
				
			case DOWN:
				setTranslateY(getTranslateY() + 1);
				break;
				
			default:
				
				break;
			}
			
			}
			isWall();
			
//			for (Side side : Side.values()) 
			if(bullets.get(Side.RIGHT).size() == 0 &&
					bullets.get(Side.LEFT).size() == 0 &&
					bullets.get(Side.UP).size() == 0 &&
					bullets.get(Side.DOWN).size() == 0)
				timer.stop();
			
//			System.out.println(bullets);
			
//		});
//		
//	}
	
//	bullets.get(key).remove(removeBullet);
}

	private void isWall() {
	Start.maze.getWalls().forEach(wall->{
		if(getBoundsInParent().intersects(wall.getBoundsInParent())) {
			bullets.get(side).remove(this);
			Start.rootLvl.getChildren().remove(this);
			
//			removeBullet = bullet;
//			key = side;
		}
	});
	
}

	public Bullet(double x, double y, int way) {
		Image image = new Image("img/bullet.png");
//		ImageView imageView = new ImageView(image);
		setImage(image);
		

		if (way == 64) {
			setRotate(90);
			side = Side.RIGHT;
			

		}
		if (way == 32) {
			setRotate(270);
			side = Side.LEFT;
		}
		if (way == 0) {
			setRotate(180);
			side = Side.DOWN;
		}

		if (way == 96)
			side = Side.UP;
		
			bullets.get(side).add(this);

//		getChildren().add(imageView);
		setTranslateX(x);
		setTranslateY(y);

		setFitWidth(10);
		setFitHeight(10);
		
//		setPrefSize(10, 10);

		Start.rootLvl.getChildren().addAll(this);
		

		timer.start();
	}

}
