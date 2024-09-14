package _04_animals_inheritance;

public class Animal {
String name;
String furColor;
boolean isGirl;

Animal(String name, String furColor, boolean isGirl){
	this.name = name;
	this.furColor = furColor;
	this.isGirl = isGirl;
}
public void printName() {
	System.out.println("my name is " + name);
}
public void eat() {
	System.out.println("eating...");
}
public void sleep() {
	System.out.println("sleeping...");
}
public void play() {
	System.out.println("playing...");
}
}
