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
	
	public void render2DPlayer(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3d(1, 0, 0);
        double angle, q, r = 0;
        for(int i =0; i <= 300; i++){
            angle = 2 * Math.PI * i / 300;
            q = this.getRadius()*Math.cos(angle);
            r = this.getRadius()*Math.sin(angle);
            gl.glVertex2d(q+this.getPosition().getX(),r+this.getPosition().getY());
        }
        gl.glEnd();
	}
}
