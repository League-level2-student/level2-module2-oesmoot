package _08_LeagueSnake;

import java.awt.Color;
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
    Boolean isDead = false;
    boolean isLoading = false;
    boolean isLoadingToGame = false;
    boolean isLoadingToJuicer = false;
    Boolean inJuicer = false;
    int progress = 0;
    int score = 0;
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
    		fill(255,255,255);
    		background(0,200,0);
    		textSize(50);
    		text("snake",150,100);
    		rect(150,130,200,100);
    		rect(150,280,200,100);
    		fill(0,0,0);
    		text("play",175,200);
    		text("juicer",175,350);
    		if(mousePressed) {
    			if(mouseX>=150&&mouseX<=350&&mouseY>=130&&mouseY<=230) {
    				inMenu = false;
    				isLoadingToGame = true;
    			}
    			else if(mouseX>=150&&mouseX<=350&&mouseY>=280&&mouseY<=380) {
    				inMenu=false;
    				isLoadingToJuicer = true;
    			}
    		}
    	}
    	else if(isDead) {
    		fill(255,255,255);
    		background(200,0,0);
    		textSize(50);
    		text("you suck",150,100);
    		rect(150,130,200,100);
    		rect(150,280,200,100);
    		fill(0,0,0);
    		text("menu",175,200);
    		text("again",175,350);
    		if(mousePressed) {
    			if(mouseX>=150&&mouseX<=350&&mouseY>=130&&mouseY<=230) {
    				isLoading = true;
    				isDead = false;
    			}
    		else if(mouseX>=150&&mouseX<=350&&mouseY>=280&&mouseY<=380) {
    			isDead = false;
    			isLoadingToGame = true;
    		}
    		}
    	}
    	else if(isLoading) {
			background(0,0,200);
			fill(255,255,255);
			textSize(50);
			text("loading",150,100);
			progress++;
			if(progress>=10) {
				isLoading = false;
				inMenu = true;
				progress = 0;
			}
    	}
		else if(isLoadingToGame) {
			background(0,0,200);
			fill(255,255,255);
			textSize(50);
			text("loading",150,100);
			progress++;
			if(progress>=50) {
				isLoadingToGame = false;
				progress = 0;
		}
		}
		else if(isLoadingToJuicer) {
			background(0,0,200);
			fill(255,255,255);
			textSize(50);
			text("loading",150,100);
			progress++;
			if(progress>=10) {
				isLoadingToJuicer = false;
				inJuicer = true;
				progress = 0;
		}
		}
		else if(inJuicer) {
			background(0,200,0);
			fill(255,255,255);
			textSize(50);
			text("add difficulty",100,100);
			rect(150,130,200,100);
			rect(150,280,200,100);
			fill(0,0,0);
			text("faster",175,200);
			text("back",175,350);
		}
    	else {
    		background(0,0,0);
            move();
            drawFood();
            drawSnake();
            eat();
            textSize(20);
            text("score: " + String.valueOf(score),10,20);
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
    		isDead = true;
    		score = 0;
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
        	score++;
        }
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
