package Model;

import java.util.Random;

public class Exit {

	Vertex position;
	
	public Exit() {
		position = new Vertex(0,0,0);
	}
	
	public Vertex getPosition() {
		return position;
	}
	
	public void setPosition(int x,int y) {
		this.position.setX(x);
		this.position.setY(y);
	}

	public void startPosition() {
		Random randomGenerator = new Random();
		int randomX = randomGenerator.nextInt(16);
		int randomY = randomGenerator.nextInt(16);
		Vertex v = new Vertex(randomX,randomY);
		this.setPosition(randomX, randomY);
	}

}
