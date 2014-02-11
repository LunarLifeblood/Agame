package com.agame;

public class Physics {
	private float g;
	private float timeInt;

	public Physics(){
		g = Constants.g();
		timeInt = Constants.timeInt();
	}
	
	public float calcXVel(float u){
		return (u);
	}
	
	public float calcXPos(float x0, float u, Player player, TwoDMap map){
		float newPos = x0 + u*timeInt;
		if(map.getAtPos(newPos+player.getRadius(), player.getPosY()) == 1 |map.getAtPos(newPos-player.getRadius(), player.getPosY()) == 1){
			float edge = map.getClosestEdge(x0);
			if(edge>newPos){
				return edge-player.getRadius();
			}else{
				return edge+player.getRadius();
			}
		}
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
	
	public float calcYVel(float u){
		return (u + -g*timeInt);
	}
	public float calcYPos(float y0, float u, Player player, TwoDMap map){
		float newPos = y0 + u*timeInt + (1/2)*-g*timeInt;
		if(map.getAtPos(player.getPosX(), newPos+player.getRadius()) == 1 | map.getAtPos(player.getPosX(), newPos-player.getRadius()) == 1){

			float edge = map.getClosestEdge(y0);
			if(edge>newPos){
				player.setVelocityY(0.0f);
				return edge-player.getRadius();
			}else if(edge<newPos){
				//player.setVelocityY(0.0f);
				return edge+player.getRadius();
			}else{
				System.err.println("Uh oh!!!!");
			}
		}
		if(newPos == 0) newPos = 0.0000001f;
		if (Math.abs(newPos) > 1-player.getRadius()){
			if(newPos>0){
				return 1-player.getRadius();
			}else{
				player.setVelocityY(0.0f);
				return -1+player.getRadius();
			}
		}else{
			return newPos;
		}
	}
}
