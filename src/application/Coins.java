package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Coins extends Pane{

	private Image image = new Image("img/coins.png");
	private ImageView imageView = new ImageView(image);
	int count = 10;
	int columns = 10;
	int offsetX = 0;
	int offsetY = 0;
	int width = 20;
	int height = 20;
	double x, y;
//    int score = 0;

	Rectangle removeRect = null;
	SpriteAnimation animation;
	
	public Coins(double x, double y) {
		this.x = x;
		this.y = y;
//		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width,
				height);
		getChildren().addAll(imageView);
		
		setCoor();
//		this.setPrefSize(20, 20);
	}

	private void setCoor() {
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}
