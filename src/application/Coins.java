package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Coins extends Pane{

	ImageView imageView;
	int count = 10;
	int columns = 10;
	int offsetX = 0;
	int offsetY = 0;
	int width = 20;
	int height = 20;
//    int score = 0;

	Rectangle removeRect = null;
	SpriteAnimation animation;
	
	public Coins(ImageView imageView) {
		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width,
				height);
		getChildren().addAll(imageView);
	}
}
