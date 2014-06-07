package com.agame;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

	private float jumpSpeed = 0.3f;
	private float moveSpeed = 0.1f;
	private long timeCount = 0;
	private long lastTime = 0;

	private TwoDMap map;
	private int twoDMapSize = 20;
	private float sqrSize = (float)(2.0/twoDMapSize);
	private Player player = new Player(sqrSize/2f, -0.4f, 0f, 0f);
	private Physics physics = new Physics();

	private int currentLevel = 1;


	public AGameMain(){
		loadLevel(String.valueOf(currentLevel));
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
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void loadLevel(String level){
		String filename = level+".dat";
		File file = new File(filename);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("Could not load level, quitting.");
			System.exit(0);
		}
		try {
			twoDMapSize = Integer.parseInt(br.readLine());
		} catch (Exception ex) {
			System.err.println("Invalid file format\nCould not read map size.");
		}
		map = new TwoDMap(twoDMapSize, sqrSize);


		for(int i = 0; i< twoDMapSize; i++){
			for(int j = 0; j<twoDMapSize; j++){
				map.set(i, j, 1);
			}
		}


		String line;
		try {
			line = br.readLine();
			String[] linePart = line.split(",");
			player.setPosX(Float.parseFloat(linePart[0]));
			player.setPosY(Float.parseFloat(linePart[1]));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String[] coordValue = new String[3];
		try {
			while ((line = br.readLine()) != null) {
				coordValue = line.split(",");
				map.set(Integer.parseInt(coordValue[0]), Integer.parseInt(coordValue[1]), Integer.parseInt(coordValue[2]));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Level "+currentLevel);
		lastTime = System.currentTimeMillis();
	}

	public void levelUp(){
		currentLevel += 1;
		System.out.println("Level Up!");
		loadLevel(String.valueOf(currentLevel));
	}




	@Override
	public void keyPressed(KeyEvent event) {
		char key = event.getKeyChar();
		if (key == ' ') {
			if(Math.abs(player.getVelocityY()) == 0.0){
				player.setVelocityY(jumpSpeed);
			}
		}else if(key == 'w' | event.getKeyCode() == KeyEvent.VK_UP){
			if(Math.abs(player.getVelocityY()) == 0.0){
				player.setVelocityY(jumpSpeed);
			}
		}else if(key == 'a' | event.getKeyCode() == KeyEvent.VK_LEFT){
			player.setVelocityX(-moveSpeed);
		}else if(key == 's' | event.getKeyCode() == KeyEvent.VK_DOWN){
		}else if(key == 'd' | event.getKeyCode() == KeyEvent.VK_RIGHT){
			player.setVelocityX(moveSpeed);
		}else if(key == 'p' | event.getKeyCode() == KeyEvent.VK_P){
			System.out.println("X pos = "+player.getPosX()+"   Y Pos = "+player.getPosY());
			System.out.println("X vel = "+player.getVelocityX()+"   Y Vel = "+player.getVelocityY());
		}

	}

	@Override
	public void keyReleased(KeyEvent event) {
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

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear( GL.GL_COLOR_BUFFER_BIT );
		timeCount = timeCount + (System.currentTimeMillis() - lastTime);
		if(timeCount > Constants.timeInt()){
			timeCount = 0;
			update2DPositions();
			if (AtGoal()){
				levelUp();
			}

		}
		map.render2DMap(drawable);
		player.render2DPlayer(drawable);
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {

	}

	@Override
	public void init(GLAutoDrawable arg0) {

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
	}


	public boolean AtGoal(){
		float radius = player.getRadius();
		if(map.getAtPos(player.getPosX(), player.getPosY()) == 2){
			return true;
		}else if(map.getAtPos(player.getPosX()+radius, player.getPosY()) == 2){
			return true;
		}else if(map.getAtPos(player.getPosX()-radius, player.getPosY()) == 2){
			return true;
		}else if(map.getAtPos(player.getPosX(), player.getPosY()+radius) == 2){
			return true;
		}else if(map.getAtPos(player.getPosX(), player.getPosY()-radius) == 2){
			return true;
		}
		return false;

	}

	public void update2DPositions(){
		if (player.getVelocityY() == 0){
			if (map.getAtPos(player.getPosX() ,(float) (player.getPosY()-(0.05*sqrSize))) == 0){
				player.setVelocity(physics.calcNewVelocity(player));
			}

		}
		if (player.getVelocityY() != 0 || player.getVelocityX() != 0){
			player.setPosition(physics.calcNewPos(player, map));
			if(player.getVelocityY() != 0) {
				player.setVelocity(physics.calcNewVelocity(player));
			}
		}

	}
}
