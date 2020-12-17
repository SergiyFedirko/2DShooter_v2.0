package application;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Start {
	
	protected static Stage stage;

//	public static ArrayList<Rectangle> bonuses;

	int a = 0;
	
	private static Hero player;

	private static Pane root;
	
	private Scene scene;
	
//	private static Menu menu;

	protected static Pane rootLvl;
	
	protected static Maze maze;
	
	public Start(Stage primaryStage) {
		
		stage = primaryStage;
		
		StartPane startPane = new StartPane(800, 600);
		
		scene = new Scene(startPane, 800, 600);
		
		startPane.start.setOnAction(e->{
			
			init(startPane.getNamePlayer());
			
		});
		
		stage.setResizable(false);
		
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

		addPlayer();

		maze = new Maze(Settings.getHight(), Settings.getWidth(), Settings.getSizeBlock());
		
		rootLvl.setPrefSize(600, 600);
		
		rootLvl.getChildren().add(maze);
		
		root.getChildren().add(rootLvl);

		addMenu(namePlayer);

		scene.setOnKeyPressed(event -> {Settings.putKeys(event.getCode(), true);
										player.fire(event.getCode());});
		scene.setOnKeyReleased(event -> Settings.putKeys(event.getCode(), false));
		
		new Bullet();
	}

	public Start() {
		
		victory();
		
	}
	
	private void addPlayer() {

		player = new Hero();

		rootLvl.getChildren().add(player);
	}

	private void addMenu(String namePlayer) {
		
		root.getChildren().add(new Menu(namePlayer));
		
	}
	
	public void victory() {
		
		Settings.clearKeys();
		
		new WinGame();

//		clear();
		

		
	}

	public static void restart() {
		
		try {
			clear();
			
			Settings.setWidth(Settings.getWidth() + 2);
			
			maze = new Maze(Settings.getHight(), Settings.getWidth(), Settings.getSizeBlock());
			
			rootLvl.getChildren().add(maze);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void clear() {
		
		player.respawn();
		
		rootLvl.getChildren().remove(maze);
		
		rootLvl.setLayoutX(0);
		
	}
	
	
	
}