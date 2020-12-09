package application;

import java.util.ArrayList;
import java.util.Stack;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Maze extends Pane {

	private int hight;
	private int width;

	private int SizeBlock;

	private ArrayList<Rectangle> walls = new ArrayList<>();
	
//	public Rectangle cash;
	
	Image image;
	ImageView imageView;
	public Coins coin;
	
	Enemy enemy = null;
	public static ArrayList<Enemy> enemys = new ArrayList<>();

	public ArrayList<Rectangle> getWalls() {
		return walls;
	}

	private Stack<Point2D> stack = new Stack<>();

	private double courentY = 1;
	private double courentX = 1;
	private ArrayList<Point2D> neighbours = new ArrayList<>();
	private ArrayList<Point2D> visited = new ArrayList<>();

	public Maze(int hight, int width, int SizeBlock) {
		this.hight = hight;
		this.width=width;
		this.SizeBlock=SizeBlock;
//		rect = new Rectangle(40,40,Color.DARKGRAY);
//		rect.setX(100); rect.setY(100);
//		
//		this.getChildren().add(rect);

		generateMaze();
		showMaze();
	}

	private void showMaze() {
		this.getChildren().addAll(walls);
	}

	private void generateMaze() {
		Image img = new Image(getClass().getResourceAsStream("wall.png"));
		for (int i = 0; i < hight; i++) {
			for (int j = 0; j < width; j++) {
				if ((i % 2 != 0 && j % 2 != 0) && (i < hight - 1 && j < width - 1)) {
				} else {
					Rectangle rect = new Rectangle(j * SizeBlock, i * SizeBlock, SizeBlock, SizeBlock);
//					rect.setFill(Color.DARKGRAY);
					rect.setFill(new ImagePattern(img));
					walls.add(rect);
//					this.getChildren().add(rect);

				}
			}
		}
		Point2D CourentPoint = new Point2D(courentX, courentY);
		stack.add(CourentPoint);
		visited.add(CourentPoint);

		
		addCash();
		addEnemys((int)(width/3));
		////////////////////////////////////////////////////////////////////////////////////

		do {
//		for (int i = 0; i <10; i++) {
		
			Neighbours neighbours = new Neighbours(CourentPoint, width, hight, visited);
			this.neighbours = neighbours.getNeighbours();

			if (this.neighbours.size() != 0) {

				int randNum = (int) (Math.random() * this.neighbours.size());

				removeWall(CourentPoint, this.neighbours.get(randNum));

				CourentPoint = this.neighbours.get(randNum);
				visited.add(this.neighbours.get(randNum));

				stack.add(this.neighbours.get(randNum));
			} else if (stack.size() > 0) {
				stack.remove(stack.lastElement());
				CourentPoint = stack.lastElement();
				
			}
//			}
//			System.out.println(unvisitedCount());
		} while (unvisitedCount() > 0);
//		System.out.println(unvisitedCount());
	}

	private void addEnemys(int count) {

		for (int i = 0; i < count; i++) {
			Image imageEnemy = new Image(getClass().getResourceAsStream("enemy.png"));
			ImageView imageViewEnemy = new ImageView(imageEnemy);
			enemy = new Enemy(imageViewEnemy);
			enemy.setLayoutX(getRandomCoor(width,false));
			enemy.setLayoutY(getRandomCoor(hight,false));
			enemys.add(enemy);
			this.getChildren().add(enemy);
			enemy.animation.play();
		}
		
		
	}

	public void addCash() {
		
		image = new Image(getClass().getResourceAsStream("coins.png"));
		imageView = new ImageView(image);
		
		
		if(this.getChildren().contains(coin)) {
			this.getChildren().remove(coin);
			Bullet.addScore(1);}
		else {
			coin = new Coins(imageView);
			coin.setMaxSize(20, 20);
			coin.setTranslateX(getRandomCoor(width,true)+10);
			coin.setTranslateY(getRandomCoor(hight,true)+10);
//		cash = new Rectangle(getRandomCoor(height)+10,getRandomCoor(width)+10,20,20);
//		cash.setFill(Color.YELLOW);
			coin.animation.play();
			coin.animation.setRate(0.3);
			
		this.getChildren().add(coin);
		
		}
	}

	private double getRandomCoor(int length, boolean isCoin) {
		int x = isCoin?length/2 + (int)( Math.random() * (length/2)):(int)( Math.random() * (length));
//		int x = length/2 + (int)( Math.random() * (length/2));
//		int x =  (int)( Math.random() * (length));
		if(x%2 != 0)
			return x*SizeBlock;
		else
			return getRandomCoor ( length,isCoin);
	}

	private int unvisitedCount() {
		return ((width - 1) / 2) * ((hight - 1) / 2) - visited.size();
	}

	int remoweWall;

	private void removeWall(Point2D p1, Point2D p2) {
		int x = (int) Math.abs((p1.getX() + p2.getX()) / 2);
		int y = (int) Math.abs((p1.getY() + p2.getY()) / 2);

//		System.out.println("x="+x);
//		System.out.println("y="+y);
//		if(x!=1||x!=width-1||y!=1||y!=height-1) {
		walls.forEach((rect) -> {
			
			if (rect.getX()  == x * SizeBlock&& rect.getY()  == y*SizeBlock) {
//				System.out.println("123 = " + walls.indexOf(rect));
				remoweWall = walls.indexOf(rect);
//				walls.remove(rect);
			}
		});

		walls.remove(remoweWall);
	}
//	}
}
