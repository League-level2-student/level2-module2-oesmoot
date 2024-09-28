package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    Segment head;
    int foodX;
    int foodY;
    int direction = UP;
    int foodEaten = 1;
    int unpressableKey =DOWN;
    ArrayList<Segment> tailPeices = new ArrayList<Segment>();
    Boolean inMenu = true;
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
        setSize(500,500);
    }

    @Override
    public void setup() {
        head = new Segment(250,250);
        frameRate(20);
        dropFood();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(50)*10);
    	foodY = ((int)random(50)*10);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
    	if(inMenu) {
    		background(0,200,0);
    		textSize(50);
    		text("snake",150,100);
    		rect(150,130,200,100);
    	}
    	else {
    		background(0,0,0);
            move();
            drawFood();
            drawSnake();
            eat();
    	}
    }

    void drawFood() {
        // Draw the food
    	fill(255,0,0);
        rect(foodX,foodY,10,10);
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0,255,0);
    	rect(head.x,head.y,10,10);
    	manageTail();
    }

    void drawTail() {
        // Draw each segment of the tail
        for(int i = 0; i<tailPeices.size(); i++) {
        	rect(tailPeices.get(i).x,tailPeices.get(i).y,10,10);
        }
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
    	checkTailCollision();
    	drawTail();
    	Segment newTailSeg = new Segment(head.x,head.y);
    	tailPeices.add(newTailSeg);
    	tailPeices.remove(0);
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.

    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
    	for(int i=0;i<tailPeices.size();i++) {
    	if(head.x == tailPeices.get(i).x&&head.y == tailPeices.get(i).y) {
    		foodEaten = 1;
    		tailPeices.removeAll(tailPeices);
    		Segment newSeg = new Segment(head.x,head.y);
    		tailPeices.add(newSeg);
    	}
    	}
        	
        
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
    	if(key == CODED) {
    		if(keyCode == UP) {
    			if(unpressableKey == UP) {
    				
    			}
    			else {
    				direction = UP;
    				unpressableKey = DOWN;
    			}
            	
            	
            }
    		else if(keyCode == DOWN) {
    			if(unpressableKey == DOWN) {
    				
    			}
    			else {
    				direction = DOWN;
    				unpressableKey = UP;
    			}
    		}
    		else if(keyCode == LEFT) {
    			if(unpressableKey == LEFT) {
    				
    			}
    			else {
    				direction = LEFT;
    				unpressableKey = RIGHT;
    			}
    			
    		}
    		else if(keyCode == RIGHT) {
    			if(unpressableKey == RIGHT) {
    				
    			}
    			else {
    				direction = RIGHT;
    				unpressableKey = LEFT;
    			}
    			
    		}
    	}
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.

        
        if (direction == UP) {
            head.y-=10;
        } else if (direction == DOWN) {
            head.y+=10;
        } else if (direction == LEFT) {
            head.x-=10;
        } else if (direction == RIGHT) {
            head.x+=10;
        }
        checkBoundaries();
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(head.x>500) {
        	head.x = 0;
        }
        else if(head.x<0) {
        	head.x= 490;
        }
        else if(head.y>500) {
        	head.y = 0;
        }
        else if(head.y<0) {
        	head.y = 490;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
        if(head.x== foodX&&head.y==foodY) {
        	foodEaten++;
        	dropFood();
        	Segment seg = new Segment(head.x,head.y);
        	tailPeices.add(seg);
        }
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
