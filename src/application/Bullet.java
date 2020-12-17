package application;

import java.lang.reflect.Array;
//import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends Pane {

//	public static ArrayList<Bullet> bulletsR = new ArrayList<>();
//	public static ArrayList<Bullet> bulletsL = new ArrayList<>();
//	public static ArrayList<Bullet> bulletsU = new ArrayList<>();
//	public static ArrayList<Bullet> bulletsD = new ArrayList<>();

	enum Side {
		RIGHT, LEFT, UP, DOWN
	};

	private static HashMap<Side, List<Bullet>> bullets = new HashMap<>();

	private static Bullet remove;

	private static int speed = 5;
	
	AnimationTimer timer;

	public Bullet() {
		for (Side side : Side.values()) {
			bullets.put(side, new ArrayList<>());
		}
		
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				fire();
				
			}

		
		};
		
		timer.start();
		
	}
	
//	double x,y;
	Bullet removeBullet;
	Side key;

private void fire() {
//	bullets.values().
	
	for (Side side : Side.values()) {
		bullets.get(side).forEach(bullet->{
			for (int i = 0; i < speed; i++) {
				
			switch (side) {
			case RIGHT:
				bullet.setTranslateX(bullet.getTranslateX() + 1);
				break;

			case LEFT:
				bullet.setTranslateX(bullet.getTranslateX() - 1);
				break;
				
			case UP:
				bullet.setTranslateY(bullet.getTranslateY() - 1);
				break;
				
			case DOWN:
				bullet.setTranslateY(bullet.getTranslateY() + 1);
				break;
				
			default:
				
				break;
			}
			
			}
			System.out.println(bullets);
			isWall(bullet, side);
//			System.out.println(x+" "+y);
			
		});
		
	}
	
//	bullets.get(key).remove(removeBullet);
}

	private void isWall(Bullet bullet, Side side) {
	Start.maze.getWalls().forEach(wall->{
		if(bullet.getBoundsInParent().intersects(wall.getBoundsInParent())) {
			bullets.get(side).remove(bullet);
//			removeBullet = bullet;
			key = side;
		}
	});
	
}

	public Bullet(double x, double y, int way) {
		Image image = new Image("img/bullet.png");
		ImageView imageView = new ImageView(image);
		
		

		if (way == 64) {
			imageView.setRotate(90);
			bullets.get(Side.RIGHT).add(this);

		}
		if (way == 32) {
			imageView.setRotate(270);
			bullets.get(Side.LEFT).add(this);
//			bulletsL.add(this);
		}
		if (way == 0) {
			imageView.setRotate(180);
//			bulletsD.add(this);
			bullets.get(Side.DOWN).add(this);
		}

		if (way == 96)
//			bulletsU.add(this);
			bullets.get(Side.UP).add(this);

		getChildren().add(imageView);
		setTranslateX(x);
		setTranslateY(y);

		setPrefSize(10, 10);

		Start.rootLvl.getChildren().addAll(this);

	}

	public static void BulletRemove() {
//		bulletsRemove(bulletsR, speed, 0);
//		bulletsRemove(bulletsL, -speed, 0);
//		bulletsRemove(bulletsU, 0, speed);
//		bulletsRemove(bulletsD, 0, -speed);
	}

	public static void bulletsRemove(ArrayList<Bullet> bullets, int speedX, int speedY) {
//		for (int i = 0; i < bullets.toArray().length; i++) {					
//			bullets.get(i).setCenterX(bullets.get(i).getCenterX() + speedX);
//			bullets.get(i).setCenterY(bullets.get(i).getCenterY() + speedY);
		bullets.forEach(elipse -> {
			elipse.setTranslateX(elipse.getTranslateX() + speedX);
			elipse.setTranslateY(elipse.getTranslateY() + speedY);
			Start.maze.getWalls().forEach((rect) -> {

				if (elipse.getBoundsInParent().intersects(rect.getBoundsInParent())) {
					remove = elipse;
//						ScoreMain++;
//						System.out.println(ScoreMain);
				}
			});
//			if(elipse.getCenterX()>=600 || elipse.getCenterY()>=600) 
//			removeElipse = elipse;

		});
//		}
//			Main.bonuses.remove(removeRect);
//	        Main.root.getChildren().remove(removeRect);

		bullets.remove(remove);
		Start.rootLvl.getChildren().remove(remove);
	}

}
