package com.agame;

public class Coordinate {
	float x = 0;
	float y = 0;
	float z = 0;
	
	public Coordinate(float xInit, float yInit){
		x = xInit;
		y = yInit;
	}
	
	public Coordinate(float xInit, float yInit, float zInit){
		x = xInit;
		y = yInit;
		z = zInit;
	}
	
	public void setX(float xNew){
		x = xNew;
	}
	
	public void setY(float yNew){
		y = yNew;
	}
	
	public void setZ(float zNew){
		z = zNew;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getZ(){
		return z;
	}

}
