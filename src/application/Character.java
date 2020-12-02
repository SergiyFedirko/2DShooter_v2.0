package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Character extends Pane {

	ImageView imageView;
	int count = 3;
	int columns = 3;
	int offsetX = 0;
	int offsetY = 0;
	int width = 32;
	int height = 32;
//    int score = 0;

	Rectangle removeRect = null;
	SpriteAnimation animation;

	public Character(ImageView imageView) {
		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width,
				height);
		getChildren().addAll(imageView);
	}

	public double getX() {
		return this.getTranslateX();
	}

	public double getY() {
		return this.getTranslateY();
	}

	public void moveX(int x) {
		boolean right = x > 0 ? true : false;

		for (int i = 0; i < Math.abs(x); i++) {
			if (right && this.getTranslateX() < 400 - 32) {
				this.setTranslateX(this.getTranslateX() + 1);
			} else if (this.getTranslateX() > 0) {

				this.setTranslateX(this.getTranslateX() - 1);
			}
//            isBonuseEat();
		}
	}

	public void moveY(int y) {
		boolean down = y > 0 ? true : false;

		for (int i = 0; i < Math.abs(y); i++) {
			if (down && this.getTranslateY() < 400 - 35)
				this.setTranslateY(this.getTranslateY() + 1);
			else if (this.getTranslateY() > 0)
				this.setTranslateY(this.getTranslateY() - 1);
//            isBonuseEat();
		}
	}

//	private void isWall(boolean down) {
//		Main.gd.getWalls().forEach(e->{
//    		if (this.getBoundsInParent().intersects(e.getBoundsInParent())) {
////    			isWallY = true;
////    			this.setTranslateX(this.getTranslateX() - 1);
//    			
//    			if(this.getTranslateX() == e.getX()-31)
//    				this.setTranslateX(e.getX()-32);
//    		if(this.getTranslateX() == e.getX()+39) 
//    			this.setTranslateX(e.getX()+40);
//    			
////    			if(this.getTranslateX() != e.getX()-33 || this.getTranslateX() != e.getX()+41) {
//    			if(down && this.getTranslateY() == e.getY()-31)
//    				this.setTranslateY(e.getY()-32);
//    			else
//    				if(this.getTranslateY() == e.getY()+39)
//    				this.setTranslateY(e.getY()+40);
////    		}
//    		}
//    	});
//	}
//    
//    public void isFiring(int f) {
//    }

//  public int getScore() {
//	  return score;
//  }

    public void isBonuseEat(){
    	
    }
//    	
//    	Main.gd.)
//      Main.bonuses.forEach((rect) -> {
//          if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
//              removeRect = rect;
//              score++;
//              System.out.println(score);
//          }
//
//      });
//      Main.bonuses.remove(removeRect);
//      Main.root.getChildren().remove(removeRect);
//  }

//    public void isBonuseEat(){
//        Main.bonuses.forEach((rect) -> {
//            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
//                removeRect = rect;
//                score++;
//                System.out.println(score);
//            }
//
//        });
//        Main.bonuses.remove(removeRect);
//        Main.root.getChildren().remove(removeRect);
//    }
}