package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Start {
	
	private Stage stage;

//	public static ArrayList<Rectangle> bonuses;

	int a = 0;
	
	Hero player;

	private static Pane root;
	
	private Scene scene;
	

	static Pane rootLvl;
	public static Maze maze;
	
	public Start(Stage primaryStage) {
		stage = primaryStage;
		
		StartPane startPane = new StartPane(800, 600);
		scene = new Scene(startPane, 800, 600);
		
		startPane.start.setOnAction(e->{
			init(startPane.getNamePlayer());
		});
		
		stage.setTitle("Game");
		stage.setScene(scene);
		stage.show();
	}

	protected void init(String namePlayer) {
		
		root = new Pane();
		rootLvl = new Pane();
		root.setPrefSize(800, 600);
		scene = new Scene(root);
		stage.setScene(scene);
//		bonuses = new ArrayList<>();

		playerProperty();

		maze = new Maze(Settings.getHight(), Settings.getWidth(), Settings.getSizeBlock());
		rootLvl.setPrefSize(600, 600);
		rootLvl.getChildren().add(maze);
		root.getChildren().add(rootLvl);

		addMenu(namePlayer);

		scene.setOnKeyPressed(event -> Settings.putKeys(event.getCode(), true));
		scene.setOnKeyReleased(event -> Settings.putKeys(event.getCode(), false));
	}
	
	public Start() {
		victory();
	}
	
	public void playerProperty() {

		player = new Hero();

		rootLvl.getChildren().add(player);
	}

	private void addMenu(String namePlayer) {
		
		root.getChildren().add(new Menu(namePlayer));
		
	}
	
	public void victory() {
		Label secondLabel = new Label("Viktory!!!");
		secondLabel.setFont(new Font(50));
		Pane secondaryLayout = new Pane();
		secondaryLayout.getChildren().add(secondLabel);

//		clear();
		Settings.clearKeys();

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
//			rootLvl = new Pane();
			Settings.setWidth(Settings.getWidth() + 2);
			maze = new Maze(Settings.getHight(), Settings.getWidth(), Settings.getSizeBlock());
			player = new Hero();
			rootLvl.getChildren().addAll(player, maze);
//			root.getChildren().add(rootLvl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void clear() {
//		root.getChildren().remove(rootLvl);
		rootLvl.setLayoutX(0);
		rootLvl.getChildren().clear();
		
	}
	
	
	
}