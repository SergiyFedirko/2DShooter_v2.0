package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heart extends ImageView{
	
	private Image image = new Image("img/3heart.png");
	
	int width = 50;
	int hight = 50;
	
public Heart() {
	setImage(image);
	setFitHeight(hight); setFitWidth(width);
}

public void setHalfHeart() {
	setViewport(new Rectangle2D(420, 0, 210, 210));
}

public void setFullHeart() {
	setViewport(new Rectangle2D(0, 0, 210, 210));
}

public void setNoHeart() {
	setViewport(new Rectangle2D(210, 0, 210, 210));
}

}
