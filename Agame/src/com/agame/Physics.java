package com.agame;

public class Physics {
	private float g;
	private float timeInt;

	public Physics(){
		g = Constants.g();
		timeInt = Constants.timeInt();
	}
	
	public Coordinate calcNewPos(Player player, TwoDMap map){
		Coordinate newPos = new Coordinate((player.getPosX() + player.getVelocityX()*timeInt),
				(player.getPosY() + player.getVelocityY()*timeInt + (1/2)*-g*timeInt));
		
		newPos = makePossible(newPos, player, map);
		
		return newPos;
	}
	
	public Coordinate calcNewVelocity(Player player){
		Coordinate newVel = new Coordinate(player.getVelocityX(), player.getVelocityY() + -g*timeInt);
		return newVel;
	}
	
	public Coordinate makePossible(Coordinate newPos, Player player, TwoDMap map){
		int numSurfacePoints = 2880;
		float x, y;
		if(newPos.getX() > 1- 1.01*player.getRadius()){
			newPos.setX((float) (1-1.01*player.getRadius()));
		}
		if(newPos.getX() < -1+1.01*player.getRadius()){
			newPos.setX((float) (-1+1.01*player.getRadius()));
		}
		if(newPos.getY() > 1-1.01*player.getRadius()){
			newPos.setY((float) (1-1.01*player.getRadius()));
		}
		if(newPos.getY() < -1+1.01*player.getRadius()){
			newPos.setY((float) (-1+1.01*player.getRadius()));
		}
		
		
		
		
		for (int i = 0; i < numSurfacePoints; i++){
			x = (float) (player.getRadius()*Math.cos((i*2*Math.PI)/(numSurfacePoints))) + newPos.getX();
			y = (float) (player.getRadius()*Math.sin((i*2*Math.PI)/(numSurfacePoints))) + newPos.getY();
			
			if(map.getAtPos(x, player.getPosY()) == 1){
				float edge = map.getClosestEdge(newPos.getX());
				if(edge>newPos.getX()){
					newPos.setX(edge-player.getRadius());
					player.setVelocityY((float) (0.5*player.getVelocityY()));
				}else{
					newPos.setX(edge+player.getRadius());
					player.setVelocityY((float) (0.5*player.getVelocityY()));
				}	
				
			}
			if(map.getAtPos(player.getPosX(), y) == 1){
				float edge = map.getClosestEdge(newPos.getY());
				if(edge>y){
					player.setVelocityY(0.0f);
					newPos.setY(player.getPosY());
				}else if(edge < y){
					player.setVelocityY(0);
					newPos.setY(edge - player.getRadius());
				}
			}
						
		}
		
		
		
		return newPos;
	}
	

	/*
	public float calcZVel(float u){
		return u;
	}
	public float calcZPos(float z0, float u, Player player){
		float newPos = z0 + u*timeInt;
		if (Math.abs(newPos) > 1-player.getRadius()){
			if(newPos>0){
				return 1-player.getRadius();
			}else{
				return -1+player.getRadius();
			}
		}else{
			return newPos;
		}
	}
	*/


}