package com.agame;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class Player extends Sprite {
	int health = 3;
	boolean isAlive = true;

	public Player(float startRadius, float startPosX, float startPosY, float startZ){
		super(startRadius, startPosX, startPosY, startZ);
	}
	
	public Player(float startRadius, float startPosX, float startPosY){
		super(startRadius, startPosX, startPosY);
	}
	
	public void decreaseHealth(int hpLoss){
		health-=hpLoss;
	}
	
	public void increaseHealth(int hpLoss){
		health+=hpLoss;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void render2DPlayer(){
		
	}
}
