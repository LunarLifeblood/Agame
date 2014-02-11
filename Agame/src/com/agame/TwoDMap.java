package com.agame;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class TwoDMap {

	private int gridSize;
	private int[][] map;
	private float sqrSize;
	
	public TwoDMap(int Size, float sqrSizeIn){
		gridSize = Size;
		map = new int[gridSize][gridSize];
		sqrSize = sqrSizeIn;
	}
	
	public int get(int x, int y){
		return map[x][y];
	}
	
	public int getAtPos(float x, float y){
		int xInt,yInt;
		xInt = (int) ((x+1)/sqrSize);
		yInt = (int) ((y+1)/sqrSize);
		return map[xInt][yInt];
	}
	
	public void set(int x, int y, int value){
		map[x][y] = value;
	}
	
	public float getClosestEdge(float pos){
		int Int;
		Int = Math.round(pos/sqrSize);
		return Int*sqrSize;
	}
	
	public void render2DMap(GLAutoDrawable drawable){
		float m = 0;
		float n = 0;
		GL2 gl = drawable.getGL().getGL2();
		gl.glColor3f(0.3f, 0.2f, 0.2f);
		for(int i = 0; i< gridSize; i++){
			for(int j = 0; j< gridSize; j++){
				if(get(i, j) == 1){
					m = i-gridSize/2f;
					n = j-gridSize/2f;
					gl.glBegin(GL2.GL_QUADS);
					gl.glVertex3f((m+1)*sqrSize,(n+1)*sqrSize, 0f);
					gl.glVertex3f(m*sqrSize, (n+1)*sqrSize, 0f);
					gl.glVertex3f(m*sqrSize, n*sqrSize, 0f);
					gl.glVertex3f((m+1)*sqrSize, n*sqrSize, 0f);
					gl.glEnd();
				}
			}
		}
	}
}
