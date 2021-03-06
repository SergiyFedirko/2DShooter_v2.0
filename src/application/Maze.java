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

	protected Coins coin;

	private Enemy enemy = null;
	public static ArrayList<Enemy> enemys = new ArrayList<>();

	public ArrayList<Rectangle> getWalls() {
		return walls;
	}

	private Stack<Point2D> stack = new Stack<>();

	private double courentY = 1;
	private double courentX = 1;
	private ArrayList<Point2D> neighbours = new ArrayList<>();
	private ArrayList<Point2D> visited = new ArrayList<>();

	public Maze() {

		this.hight = Settings.getHight();
		this.width = Settings.getWidth();
		this.SizeBlock = Settings.getSizeBlock();
		
		coin = new Coins(getRandomCoor(width, true) + 10, getRandomCoor(hight, true) + 10);
		
		
		getChildren().add(coin);

		startMaze();
		
		
	}

	private void enemyMove() {
		enemys.forEach(enemy -> {
			enemy.startMove();
		});
	}

	private void showMaze() {
		this.getChildren().addAll(walls);
	}
	
	public void restartMaze() {
		getChildren().removeAll(walls);
		getChildren().removeAll(enemys);
		enemys.clear();
		walls.clear();
		neighbours.clear();
		visited.clear();
		stack.clear();
//		width += 2;
		this.width = Settings.getWidth();
		startMaze();
	}

	private void startMaze() {
		generateMaze();
		showMaze();
		enemyMove();
	}

	private void generateMaze() {

		Image img = new Image("img/wall.png");
		for (int i = 0; i < hight; i++) {
			for (int j = 0; j < width; j++) {
				if ((i % 2 != 0 && j % 2 != 0) && (i < hight - 1 && j < width - 1)) {
				} else {
					Rectangle rect = new Rectangle(j * SizeBlock, i * SizeBlock, SizeBlock, SizeBlock);
					rect.setFill(new ImagePattern(img));
					walls.add(rect);

				}
			}
		}
		Point2D CourentPoint = new Point2D(courentX, courentY);
		stack.add(CourentPoint);
		visited.add(CourentPoint);

		addCash();

		////////////////////////////////////////////////////////////////////////////////////

		do {

			Neighbours neighbours = new Neighbours(CourentPoint, width, hight, visited);
			this.neighbours = neighbours.getNeighbours();

			if (this.neighbours.size() != 0) {

				int randNum = (int) (Math.random() * this.neighbours.size());

				enemyAndWall(CourentPoint, this.neighbours.get(randNum));

				CourentPoint = this.neighbours.get(randNum);
				visited.add(this.neighbours.get(randNum));

				stack.add(this.neighbours.get(randNum));
			} else if (stack.size() > 0) {
				stack.remove(stack.lastElement());
				CourentPoint = stack.lastElement();

			}

		} while (unvisitedCount() > 0);

	}

	private void addEnemy(int x, int y, double distance, boolean isX) {

		Image imageEnemy = new Image("img/enemy.png");
		ImageView imageViewEnemy = new ImageView(imageEnemy);
		enemy = new Enemy(imageViewEnemy, x, y, distance, isX);

//			enemy.moveX();
		enemys.add(enemy);
		this.getChildren().add(enemy);

//		}

	}

	public void addCash() {

//		if (this.getChildren().contains(coin)) {
//			this.getChildren().remove(coin);
//			Settings.addScore(1);
//			Menu.setScore();
//		} else {
//			coin.relocate(getRandomCoor(width, true) + 10, getRandomCoor(hight, true) + 10);
		coin.restart(getRandomCoor(width, true) + 10, getRandomCoor(hight, true) + 10);
			
//			this.getChildren().add(coin);

//		}
	}

	private double getRandomCoor(int length, boolean isCoin) {
		int x = isCoin ? (length / 2 + (int) (Math.random() * (length / 2)))
				: (3 + (int) (Math.random() * (length - 3)));
		if (x % 2 != 0)
			return x * SizeBlock;
		else
			return getRandomCoor(length, isCoin);
	}

	private int unvisitedCount() {
		return ((width - 1) / 2) * ((hight - 1) / 2) - visited.size();
	}

	int remoweWall;
	int procentRespawnEnemy = 5;

	private void enemyAndWall(Point2D p1, Point2D p2) {
		int x = (int) Math.abs((p1.getX() + p2.getX()) / 2);
		int y = (int) Math.abs((p1.getY() + p2.getY()) / 2);

		double distance = p1.getX() - p2.getX() == 0 ? p1.getY() - p2.getY() : p1.getX() - p2.getX();
		boolean isX = p1.getX() - p2.getX() == 0 ? false : true;

		respawnEnemy(x, y, distance, isX);

		remoweWall(x, y);
	}
//	}

	private void remoweWall(int x, int y) {
		walls.forEach((rect) -> {

			if (rect.getX() == x * SizeBlock && rect.getY() == y * SizeBlock) {
				remoweWall = walls.indexOf(rect);

			}
		});

		walls.remove(remoweWall);
	}

	private void respawnEnemy(int x, int y, double distance, boolean isX) {
		int rand = (int) (Math.random() * 100);

		if (enemys.size() < (int) (width / 3) && x > 3 && y > 3) {
			if (rand < procentRespawnEnemy)
				addEnemy(x, y, distance, isX);
			else
				procentRespawnEnemy++;
		}
	}
}
