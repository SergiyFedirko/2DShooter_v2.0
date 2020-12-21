package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Coins extends Pane{

	private Image image = new Image("img/coins.png");
	private ImageView imageView = new ImageView(image);
	int count = 10;
	int columns = 10;
	int offsetX = 0;
	int offsetY = 0;
	int width = 20;
	int hight = 20;
	double x, y;

	SpriteAnimation animation;
	
	public Coins(double x, double y) {
		this.x = x;
		this.y = y;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, hight));
		animation = new SpriteAnimation(imageView, Duration.millis(1000), count, columns, offsetX, offsetY, width,
				hight);
		getChildren().addAll(imageView);
		
		setCoor();
	}

	private void setCoor() {
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}
