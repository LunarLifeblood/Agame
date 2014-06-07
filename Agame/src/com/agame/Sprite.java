package com.agame;

import javax.media.opengl.GLAutoDrawable;

public class Sprite {
	private Coordinate position = new Coordinate(0, 0);
	private float radius = 0;
	private Coordinate velocity = new Coordinate(0, 0);
	GLAutoDrawable drawable = null;
	
	public Sprite(float startRadius, float startPosX, float startPosY, float startPosZ){
		radius = startRadius;
		this.getPosition().setX(startPosX);
		this.getPosition().setY(startPosY);
		this.getPosition().setZ(startPosZ);
	}
	
	public Sprite(float startRadius, float startPosX, float startPosY){
		radius = startRadius;
		this.getPosition().setX(startPosX);
		this.getPosition().setY(startPosY);
	}
		
	public void setPosX(float newPosX){
		this.getPosition().setX(newPosX);
	}
	
	public void setDrawable(GLAutoDrawable draw){
		drawable = draw;
	}
	
	public void setPosY(float newPosY){
		this.getPosition().setY(newPosY);
	}
	
	public void setPosZ(float newPosZ){
		this.getPosition().setZ(newPosZ);
	}
	
	public void setVelocityX(float newVelocity){
		this.velocity.setX(newVelocity);
	}
	
	public void setVelocityY(float newVelocity){
		this.velocity.setY(newVelocity);
	}
	
	public void setVelocityZ(float newVelocity){
		this.velocity.setZ(newVelocity);
	}

	
	public float getRadius(){
		return radius;
	}
	
	public float getPosX(){
		return this.getPosition().getX();
	}
	
	public float getPosY(){
		return this.getPosition().getY();
	}
	
	public float getPosZ(){
		return this.getPosition().getZ();
	}
	
	public float getVelocityX(){
		return this.velocity.getX();
	}
	
	public float getVelocityY(){
		return this.velocity.getY();
	}
	
	public float getVelocityZ(){
		return this.velocity.getZ();
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	public Coordinate getVelocity() {
		return velocity;
	}

	public void setVelocity(Coordinate velocity) {
		this.velocity = velocity;
	}
}
