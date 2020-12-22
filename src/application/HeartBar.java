package application;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class HeartBar extends Pane {
	
	ArrayList<Heart> bar = new ArrayList<>();

	public HeartBar() {
		for (int i = 0; i<3; i++) {
			Heart heart = new Heart();
			heart.setTranslateX(50*i + 5);
			heart.setFullHeart();
			bar.add(heart);
		}
		getChildren().addAll(bar);
	}
	
	public void changeLife(int life) {
		
		int heart = life/2;
		// heart = 3 or 2 or 1; s
		int halfHeart = life%2;
		
//		bar.forEach(e-> {
//			e.setNoHeart();			
//		});
		
		for(int i = 2; i>heart-1; i--)
			bar.get(i).setNoHeart();
		if(halfHeart>0)
			bar.get(heart).setHalfHeart();
	}

}
