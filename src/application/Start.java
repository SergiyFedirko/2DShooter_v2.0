package application;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Start {
	
	private Stage stage;

//	private int hight;
//	private int width;

	private int SizeBlock = 40;

	public static ArrayList<Rectangle> bonuses;

	int a = 0;

//	Image image;
//	ImageView imageView;
	Hero player;

	static Pane root;
	Scene scene;

	Enemy enemy = null;
	int EnemyCount = 2;

	

	static Pane rootLvl;
	public static Maze maze;
	
	public Start(Stage primaryStage) {
		stage = primaryStage;

		root = new Pane();
		rootLvl = new Pane();
		root.setPrefSize(800, 600);
		scene = new Scene(root);
		bonuses = new ArrayList<>();

		playerProperty();

		maze = new Maze(Settings.hight, Settings.width, SizeBlock);
		rootLvl.setPrefSize(600, 600);
		rootLvl.getChildren().add(maze);
		root.getChildren().add(rootLvl);

		addMenu();

//		lbl.textProperty().addListener(listener -> {
//			victory();
//		});

//		scene = new Scene(root);
		scene.setOnKeyPressed(event -> Settings.keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> {
			Settings.keys.put(event.getCode(), false);
		});
		
		stage.setTitle("Game");
		stage.setScene(scene);
		stage.show();
	}
	
	public Start() {
		victory();
	}
	
	public void playerProperty() {

//		image = new Image(getClass().getResourceAsStream("hero.png"));
//		imageView = new ImageView(image);
		player = new Hero();

		rootLvl.getChildren().add(player);
	}

	private void addMenu() {
		

		root.getChildren().add(new Menu());
	}
	
	public void victory() {
		Label secondLabel = new Label("Viktory!!!");
		secondLabel.setFont(new Font(50));
		Pane secondaryLayout = new Pane();
		secondaryLayout.getChildren().add(secondLabel);

//		clear();
		Settings.keys.clear();

		Scene secondScene = new Scene(secondaryLayout, 200, 100);

		Button btn = new Button("Next Lvl");
		btn.setLayoutX(50);
		btn.setLayoutY(75);
		btn.setPrefSize(100, 20);
		secondaryLayout.getChildren().add(btn);

		Stage newWindow = new Stage();
		newWindow.setTitle("Viktory!!!");
		newWindow.setScene(secondScene);

		btn.setOnAction(e -> {
			newWindow.close();
			restart();
		});

		newWindow.initModality(Modality.WINDOW_MODAL);
		newWindow.setResizable(false);
		newWindow.setOnCloseRequest(e -> {
			restart();
		});

		newWindow.initOwner(stage);

//		newWindow.setX(stage.getX() + 200);
//		newWindow.setY(stage.getY() + 100);

		newWindow.show();
	}

	private void restart() {
		
		try {
			clear();
			Settings.width += 2;
//			hight += 2;
//			this(stage);
//			new Start(stage);
			maze = new Maze(Settings.hight, Settings.width, SizeBlock);
			player = new Hero();
			rootLvl.getChildren().addAll(player, maze);
			
			
//			timer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void clear() {

//		player = new Hero(imageView);
//		timer.stop();
		

//		maze.enemys.clear();
		rootLvl.setLayoutX(0);
//		rootLvl.getChildren().remove(maze);
		rootLvl.getChildren().clear();
		
		
//		player.relocate(42, 42);
//		root.getChildren().clear();

	}
	
	
	
}