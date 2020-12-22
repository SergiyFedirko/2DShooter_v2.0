package application;

import java.util.HashMap;
import javafx.scene.input.KeyCode;

public class Settings {
	
	private static int hight = 15;
	private static int width = 19;
	
	private static int SizeBlock = 40;

	private static HashMap<KeyCode, Boolean> keys = new HashMap<>();
	
	private  static int score;
	
	private static int life = 6;
	
	public static int getLife() {
		return life;
	}
	
	public static void setLife(int life) {
		Settings.life = life;
	}

	public static int getHight() {
		return hight;
	}

	public static void setHight(int hight) {
		Settings.hight = hight;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Settings.width = width;
	}

	public static int getSizeBlock() {
		return SizeBlock;
	}

	public static void setSizeBlock(int sizeBlock) {
		SizeBlock = sizeBlock;
	}

	public static HashMap<KeyCode, Boolean> getKeys() {
		return keys;
	}

	public static void putKeys(KeyCode event, Boolean bool) {
		Settings.keys.put(event, bool);
	}
	
	public static void clearKeys() {
		Settings.keys.clear();
	}
	
	public static boolean isPressed(KeyCode key) {
		return Settings.keys.getOrDefault(key, false);
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Settings.score = score;
	}

	public static void addScore(int score) {
		Settings.score+=score;
	}

	
	
	
}
