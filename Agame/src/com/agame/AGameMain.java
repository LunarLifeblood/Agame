package com.agame;

import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.GL2GL3.GL_QUADS;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class AGameMain implements GLEventListener, KeyListener {
	Constants constant = new Constants();
	
	
	
	private float jumpSpeed = 0.2f;
	private float moveSpeed = 0.1f;
	private long timeCount = 0;
	private long lastTime = 0;

	private TwoDMap map;
	private int twoDMapSize = 20;
	private float sqrSize = (float)(2.0/twoDMapSize);
	private Player player = new Player(sqrSize/2f, -0.9f, 0f, 0f);
	private Physics physics = new Physics();
		
		
	public AGameMain(){
			map = new TwoDMap(twoDMapSize, sqrSize);
			for(int i = 0; i< twoDMapSize; i++){
				for(int j = 0; j<twoDMapSize; j++){
					map.set(i, j, 1);
				}
			}
			for(int i = 0; i<twoDMapSize; i++){
				map.set(i, 9, 0);
				map.set(i, 10, 0);
				map.set(i, 11, 0);
				map.set(i, 12, 0);
			}
			map.set(10, 9, 1);
			map.set(4, 9, 1);
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("one.dat"));
				bw.write("woo");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		lastTime = System.currentTimeMillis();
	}
	
	public void loadLevel(String level){
		map = new TwoDMap(twoDMapSize, sqrSize);
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame("~A Game~");
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);
		frame.setSize(700,700);
		frame.add(canvas);
		frame.setVisible(true);  
		AGameMain aGame = new AGameMain();
		canvas.addGLEventListener(aGame);
		canvas.addKeyListener(aGame);
		FPSAnimator animator = new FPSAnimator(canvas, 60);
		animator.start();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new AGameMain();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
        char key = event.getKeyChar();
        if (key == ' ') {
        	if(Math.abs(player.getVelocityY()) == 0.0){
        		player.increaseVelocityY(jumpSpeed);
        	}
        }else if(key == 'w' | event.getKeyCode() == KeyEvent.VK_UP){  ////Something dodgy with arrow keys...they don't act like wasd exactly when held
        	if(Math.abs(player.getVelocityY()) == 0.0){
        		player.increaseVelocityY(jumpSpeed);
        	}
        }else if(key == 'a' | event.getKeyCode() == KeyEvent.VK_LEFT){
        	player.setVelocityX(-moveSpeed);
        }else if(key == 's' | event.getKeyCode() == KeyEvent.VK_DOWN){
        }else if(key == 'd' | event.getKeyCode() == KeyEvent.VK_RIGHT){
        	player.setVelocityX(moveSpeed);
        }
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
        char key = event.getKeyChar();
        if (key == ' ') {
        }else if(key == 'w' | event.getKeyCode() == KeyEvent.VK_UP){
        }else if(key == 'a' | event.getKeyCode() == KeyEvent.VK_LEFT){
        	player.setVelocityX(0);
        }else if(key == 's' | event.getKeyCode() == KeyEvent.VK_DOWN){
        }else if(key == 'd' | event.getKeyCode() == KeyEvent.VK_RIGHT){
        	player.setVelocityX(0);
        }
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
			// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
        gl.glClear( GL.GL_COLOR_BUFFER_BIT );
        timeCount = timeCount + (System.currentTimeMillis() - lastTime);
        if(timeCount > Constants.timeInt()){
        	timeCount = 0;
        	update2DPositions();
        }
		map.render2DMap(drawable);
		player.render2DPlayer(drawable);
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}
	

	public void update2DPositions(){
		if (player.getVelocityX() != 0){
			player.setPosX(physics.calcXPos(player.getPosX(), player.getVelocityX(), player, map));
		}
		if (player.getVelocityY() != 0){
			player.setPosY(physics.calcYPos(player.getPosY(), player.getVelocityY(), player, map));
			if(player.getVelocityY() != 0) player.setVelocityY(physics.calcYVel(player.getVelocityY()));
		}
	}
}
