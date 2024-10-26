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
    int wallXa;
    int wallYa;
    int wallXb;
    int wallYb;
    int wallXc;
    int wallYc;
    ArrayList<Wall> walls = new ArrayList<Wall>();
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
    boolean isBorders = false;
    String borderText = "borders(off)";
    String sizeText = "bigger(off)";
    String speedText = "faster(off)";
    String wallText = "walls(off)";
    boolean isBig = false;
    boolean isFast = false;
    boolean isWalls = false;
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
        head = new Segment(240,240);
        frameRate(20);
        dropFood();
        makeWalls();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(50)*10);
    	foodY = ((int)random(50)*10);
    	
    }
    void makeWalls() {
    	walls.add(new Wall());
    	walls.add(new Wall());
    	walls.add(new Wall());
    	walls.add(new Wall());
    	walls.add(new Wall());
    	walls.add(new Wall());
    	walls.add(new Wall());
    	walls.add(new Wall());
    	walls.add(new Wall());
    	walls.add(new Wall());
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
    		head.x = 250;
    		head.y = 250;
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
			text("add difficulty",100,50);
			rect(150,60,200,100);
			rect(150,165,200,100);
			rect(150,270,200,100);
			rect(150,375,200,100);
			rect(10,10,65,50);
			fill(0,0,0);
			text("<-",7,50);
			textSize(30);
			text(borderText,170,120);
			text(sizeText,175,225);
			text(speedText,175,330);
			text(wallText,175,435);
			if(mousePressed) {
				if(mouseX>=150&&mouseX<=350&&mouseY>=60&&mouseY<=160) {
					inJuicer = false;
					isLoadingToJuicer = true;
					if(!isBorders) {
						isBorders = true;
						borderText = "borders(on)";
					}
					else if(isBorders) {
						isBorders = false;
						borderText = "borders(off)";
					}
				}
				else if(mouseX>=150&&mouseX<=350&&mouseY>=165&&mouseY<=265) {
					inJuicer = false;
					isLoadingToJuicer = true;
					if(!isBig) {
						isBig = true;
						sizeText = "bigger(on)";
					}
					else if(isBig) {
						isBig = false;
						sizeText = "bigger(off)";
					}
				}
				else if(mouseX>=150&&mouseX<=350&&mouseY>=270&&mouseY<=370) {
					inJuicer = false;
					isLoadingToJuicer = true;
					if(!isFast) {
						isFast = true;
						speedText = "faster(on)";
					}
					else if(isFast) {
						isFast = false;
						speedText = "faster(off)";
					}
				}
				else if(mouseX>=150&&mouseX<=350&&mouseY>=375&&mouseY<=475) {
					inJuicer = false;
					isLoadingToJuicer = true;
					if(!isWalls) {
						isWalls = true;
						wallText = "walls(on)";
					}
					else if(isWalls) {
						isWalls = false;
						wallText = "walls(off)";
					}
				}
				else if(mouseX>=10&&mouseX<=75&&mouseY>=10&&mouseY<=60) {
					inJuicer = false;
					isLoading = true;
				}
				
			}
		}
    	else {
    		background(0,0,0);
            move();
            drawFood();
            drawSnake();
            eat();
            textSize(20);
            text("score: " + String.valueOf(score),10,20);
            if(!isBig) {
            	if(!isFast) {
            		frameRate(20);
            	}
            	else if(isFast) {
            		frameRate(40);
            	}
            }
            else if(isBig) {
            	if(!isFast) {
            		frameRate(10);
            	}
            	else if(isFast) {
            		frameRate(20);
            	}
            }
            if(isWalls) {
            	drawWalls();
            	
            	checkWallCollision();
            }
    	}
    }

    void drawFood() {
        // Draw the food
    	fill(255,0,0);
        rect(foodX,foodY,10,10);
        
    }
    void drawWalls() {
    	fill(0,0,255);
    	for(Wall w:walls) {
    		rect(w.x,w.y,10,10);
    	}
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0,255,0);
    	if(isBig) {
    		rect(head.x,head.y,20,20);
    	}
    	else {
    		rect(head.x,head.y,10,10);
    	}
    	manageTail();
    }

    void drawTail() {
        // Draw each segment of the tail
        for(int i = 0; i<tailPeices.size(); i++) {
        	if(isBig) {
        		rect(tailPeices.get(i).x,tailPeices.get(i).y,20,20);
        	}
        	else {
        		rect(tailPeices.get(i).x,tailPeices.get(i).y,10,10);
        	}
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
        	if(isBig) {
        		head.y-=20;
        	}
        	else {
        		head.y-=10;
        	}
        } else if (direction == DOWN) {
        	if(isBig) {
        		head.y+=20;
        	}
        	else {
        		head.y+=10;
        	}
        } else if (direction == LEFT) {
        	if(isBig) {
        		head.x-=20;
        	}
        	else {
        		head.x-=10;
        	}
        } else if (direction == RIGHT) {
        	if(isBig) {
        		head.x+=20;
        	}
        	else {
        		head.x+=10;
        	}
        }
        checkBoundaries();
    }
    void death() {
    	foodEaten = 1;
		tailPeices.removeAll(tailPeices);
		Segment newSeg = new Segment(head.x,head.y);
		tailPeices.add(newSeg);
		isDead = true;
		score = 0;
    }
    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(head.x>500) {
        	if(isBorders) {
        		death();
        	}
        	else {
        		head.x = 0;
        	}
        }
        else if(head.x<0) {
        	if(isBorders) {
        		death();
        	}
        	else {
        		if(isBig) {
        			head.x = 480;
        		}
        		else {
        			head.x= 490;
        		}
        	}
        }
        else if(head.y>500) {
        	if(isBorders) {
        		death();
        	}
        	else {
        		head.y = 0;
        	}
        }
        else if(head.y<0) {
        	if(isBorders) {
        		death();
        	}
        	else {
        		if(isBig) {
        			head.y=480;
        		}
        		else {
        			head.y = 490;
        		}
        	}
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
    	if(isBig) {
    		if(head.x== foodX&&head.y==foodY
    				||head.x == (foodX-10)&&head.y == foodY
    				||head.x == (foodX-10)&&head.y == (foodY-10)
    				||head.x == foodX&&head.y==(foodY-10)) {
            	foodEaten++;
            	dropFood();
            	Segment seg = new Segment(head.x,head.y);
            	tailPeices.add(seg);
            	score++;
            }
    	
    	}
    	else {
    		if(head.x== foodX&&head.y==foodY) {
            	foodEaten++;
            	dropFood();
            	Segment seg = new Segment(head.x,head.y);
            	tailPeices.add(seg);
            	score++;
            }
    	}
    }
    void checkWallCollision() {
    	for(Wall w: walls) {
    		if(isBig) {
        		if(head.x== w.x&&head.y==w.y
        				||head.x == (w.x-10)&&head.y == w.y
        				||head.x == (w.x-10)&&head.y == (w.y-10)
        				||head.x == w.x&&head.y==(w.y-10)) {
                		death();
                }
        	
        	}
    		else {
        		if(head.x== w.x&&head.y==w.y) {
                	death();
                }
        	}

    	}
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
