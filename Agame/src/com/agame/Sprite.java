package com.agame;

public class Sprite {
	private float posY = 0;
	private float radius = 0;
	private float posX = 0;
	private float posZ = 0;
	private float velX = 0;
	private float velY = 0;
	private float velZ = 0;
	
	public Sprite(float startRadius, float startPosX, float startPosZ, float startPosY){
		radius = startRadius;
		posX = startPosX;
		posZ = startPosZ;
		posY = startPosY;
		
		
	}
	
	public void setPosY(float newPosY){
		posY = newPosY;
	}
	
	public void increasePosY(float amount){
		posY+=amount;
	}
	
	public void decreasePosY(float amount){
		posY-=amount;
	}
	
	public void setPosX(float newPos){
		posX = newPos;
	}
	
	public void increasePosX(float amount){
		posX+=amount;
	}
	
	public void decreasePosX(float amount){
		posX-=amount;
	}

	public void setPosZ(float newPos){
		posZ = newPos;
	}
	
	public void increasePosZ(float amount){
		posZ+=amount;
	}
	
	public void decreasePosZ(float amount){
		posZ-=amount;
	}
	
	public void setVelocityX(float newVelocity){
		velX = newVelocity;
	}
	public void increaseVelocityX(float amount){
		velX+=amount;
	}
	
	public void decreaseVelocityX(float amount){
		velX-=amount;
	}
	
	public void setVelocityY(float newVelocity){
		velY = newVelocity;
	}
	
	public void increaseVelocityY(float amount){
		velY+=amount;
	}
	
	public void decreaseVelocityY(float amount){
		velY-=amount;
	}
	
	public void setVelocityZ(float newVelocity){
		velZ = newVelocity;
	}

	public void increaseVelocityZ(float amount){
		velZ+=amount;
	}
	
	public void decreaseVelocityZ(float amount){
		velZ-=amount;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public float getPosY(){
		return posY;
	}
	
	public float getPosX(){
		return posX;
	}
	
	public float getPosZ(){
		return posZ;
	}
	
	public float getVelocityY(){
		return velY;
	}
	
	public float getVelocityX(){
		return velX;
	}
	
	public float getVelocityZ(){
		return velZ;
	}
}
