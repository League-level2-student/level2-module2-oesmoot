package _08_LeagueSnake;

import java.util.Random;

public class Wall {
int x;
int y;
	public Wall(){
		Random ran = new Random();
		this.x = ((int)ran.nextInt(50)*10);
		this.y = ((int)ran.nextInt(50)*10);
	}
}
