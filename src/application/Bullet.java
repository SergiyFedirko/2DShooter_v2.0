package application;

//import java.awt.*;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends Pane {
	
	public static ArrayList<Bullet> bulletsR = new ArrayList<>();
	public static ArrayList<Bullet> bulletsL = new ArrayList<>();
	public static ArrayList<Bullet> bulletsU = new ArrayList<>();
	public static ArrayList<Bullet> bulletsD = new ArrayList<>();
	
	private static Bullet remove;

	private static int speed = 5;
	
	
	
	public Bullet(double x, double y, int way) {
		Image image = new Image("img/bullet.png");
	ImageView imageView = new ImageView(image);
	
	if(way == 64) {
		imageView.setRotate(90);
	bulletsR.add(this);
	}
	if(way==32) {
		imageView.setRotate(270);
		bulletsL.add(this);
	}
	if(way == 96) {
		imageView.setRotate(180);
	bulletsD.add(this);}
	
	if(way==0)
		bulletsU.add(this);
		
	getChildren().add(imageView);
	setTranslateX(x);
	setTranslateY(y);
	
	setPrefSize(10, 10);
		
	}
	
	public static void BulletRemove() {
		bulletsRemove(bulletsR, speed, 0);
		bulletsRemove(bulletsL, -speed, 0);
		bulletsRemove(bulletsU, 0, speed);
		bulletsRemove(bulletsD, 0, -speed);
	}
	
	public static void bulletsRemove (ArrayList<Bullet> bullets, int speedX, int speedY) {
//		for (int i = 0; i < bullets.toArray().length; i++) {					
//			bullets.get(i).setCenterX(bullets.get(i).getCenterX() + speedX);
//			bullets.get(i).setCenterY(bullets.get(i).getCenterY() + speedY);
			bullets.forEach(elipse ->{
				elipse.setTranslateX(elipse.getTranslateX() + speedX);
				elipse.setTranslateY(elipse.getTranslateY() + speedY);
			Start.maze.getWalls().forEach((rect) ->{
				
					if(elipse.getBoundsInParent().intersects(rect.getBoundsInParent())) {
						remove	= elipse;
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
