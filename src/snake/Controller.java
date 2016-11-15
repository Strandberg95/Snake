package snake;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;

public class Controller {
	
	private JFrame frame = new JFrame("Snake");
	
	private final int WIDTH;
	private final int HEIGHT;
	private final int BLOCK_AMOUNT;
	private final float PAUSE_TIME;
	
	private boolean[][] grid1;
	private boolean[][] grid2;
	
	private MyPanel panel;
	
	private Random rdm;
	
	private Stack<Position> positions;
	
	private int startX;
	private int startY;
	
	private int currentX;
	private int currentY;
	
//	private int maxValue1 = 4;
//	private int minValue1 = 0;
//	
//	private int maxValue2 = 5;
//	private int minValue2 = 9;
	
	private Graphics g;
	
	public Controller() throws IOException{
		String[] subStrings = null;
			BufferedReader in = new BufferedReader(new FileReader("/Users/Strandberg95/Desktop/file.txt"));
			String line = in.readLine();
			System.out.println(line);
			subStrings = line.split(",");
			for(int i = 0; i < subStrings.length; i++){
				System.out.println(subStrings[i]);
			}
			
			WIDTH = Integer.parseInt(subStrings[0]);
			HEIGHT = Integer.parseInt(subStrings[1]);
			BLOCK_AMOUNT = Integer.parseInt(subStrings[2]);
			PAUSE_TIME = 1000000f;
			
			grid1 = new boolean[WIDTH][HEIGHT];
			grid2 = new boolean[WIDTH][HEIGHT];
			
//			while(in.ready()){
//				String tempLine = in.readLine();
//				addBlock(Integer.parseInt(tempLine.charAt(0) + ""),Integer.parseInt(tempLine.charAt(3)+""));
//			}
//		
		currentX = startX;
		currentY = startY;
		

		panel = new MyPanel(grid1,grid2);
		rdm = new Random();
		positions = new Stack<Position>();
//		startX = rdm.nextInt(WIDTH);
//		startY = rdm.nextInt(HEIGHT);
		
		startX = 0;
		startY = 0;
		positions.add(new Position(startX,startY));
	
	}
	
	private void addBlock(int x,int y){
		System.out.println(x + " " + y);
		grid2[x][y] = true;
	}
	public void newGame(){
		
		frame.setSize(1000, 1000);
		frame.setLayout(new BorderLayout());
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		frame.addMouseListener(new MouseListen());
		
		frame.setVisible(true);
		
		g = frame.getGraphics();
		
		setStart();

//		while(grid1[0][HEIGHT-1] != true){
		pause();
		while(true){
			move();
			pause();
		}
//		System.out.println("Done");
		
//			frame.repaint();
//		}
	}
	
	public void setStart(){
		grid1[0][0] = true;
		for(int i = 0; i < BLOCK_AMOUNT; i++){

			int rdmWidth = rdm.nextInt(WIDTH - 1);
			int rdmHeight = rdm.nextInt(HEIGHT - 1);
			
			try{	
				if((rdmWidth != 0 && rdmHeight != 0) && (rdmWidth != 0 && rdmHeight != (HEIGHT - 1)))
				grid2[rdmHeight][rdm.nextInt(rdmWidth)] = true;
				else
					i--;
			}catch(IllegalArgumentException e){

				e.printStackTrace();
			}

		}
		panel.repaint();
	}
	
	public void move(){
//		int tempX = currentX;
//		int tempY = currentY;
		
		if(canGoUp(currentX,currentY)){
			System.out.println("moved up");
			positions.add(new Position(currentX,currentY));
			grid1[currentX][(currentY -= 1)] = true;
			panel.repaint();
//			pause();
//			move(tempX,tempY);
		}else if(canGoRight(currentX,currentY)){
			System.out.println("moved right");
			positions.add(new Position(currentX,currentY));
			grid1[(currentX += 1)][currentY] = true;
			
			panel.repaint();
//			pause();
//			move(tempX,tempY);
		}else if(canGoDown(currentX,currentY)){
			System.out.println("moved down");
			positions.add(new Position(currentX,currentY));
			grid1[currentX][(currentY += 1)] = true;
			
			panel.repaint();
//			pause();
//			move(tempX,tempY);
		}else if(canGoLeft(currentX,currentY)){
			System.out.println("moved left");
			positions.add(new Position(currentX,currentY));
			grid1[(currentX -= 1)][currentY] = true;
			panel.repaint();
//			pause();
//			move(tempX,tempY);
		}else{
			Position tempPos = positions.pop();
			System.out.println("Backtrack to: X: " + tempPos.getX() + "Y: " + tempPos.getY());
			currentX = tempPos.getX();
			currentY = tempPos.getY();
//			move(tempPos.getX(),tempPos.getY());
		}	
	}
	
	private boolean canGoUp(int x, int y){
		
//		System.out.println(x);
//		System.out.println(y);
		if((y-1) < 0){
			return false;
		}
		if(grid2[x][y-1] != true && grid1[x][y-1] != true){
			return true;
		}
		return false;
	}
	
	private boolean canGoRight(int x, int y){
		if((x+1) > (WIDTH - 1)){
			return false;
		}
		if(grid2[x + 1][y] != true && grid1[x + 1][y] != true){
			return true;
		}
		return false;
	}
	
	private boolean canGoDown(int x, int y){
		if((y+1) > (HEIGHT - 1)){
			return false;
		}
		if(grid2[x][y + 1] != true && grid1[x][y + 1] != true){
			return true;
		}
		return false;
	}
	
	private boolean canGoLeft(int x, int y){
		if((x-1) < 0){
			return false;
		}
		if(grid2[x - 1][y] != true && grid1[x - 1][y] != true){
			return true;
		}
		return false;
	}
	
	//**BACKTRACK**
	private boolean canBacktrackUp(int x, int y){
		if((y-1) < 0){
			return false;
		}
		if(grid2[x][y - 1] != true){
			return true;
		}
		return false;
		
	}
	private boolean canBacktrackRight(int x, int y){
		if((x+1) > (WIDTH - 1)){
			return false;
		}
		if(grid2[x+1][y] != true){
			return true;
		}
		return false;
		
	}
	private boolean canBacktrackDown(int x, int y){
		if((y+1) > (HEIGHT-1)){
			return false;
		}
		if(grid2[x][y+1] != true){
			return true;
		}
		return false;
	}
	private boolean canBacktrackLeft(int x, int y){
		if((x-1) < 0){
			return false;
		}
		if(grid2[x-1][y] != true){
			return true;
		}
		return false;
	}
	
	public void pause(){
//		Random rdm = new Random();
		double timer = 0;
		while(timer < PAUSE_TIME)
			timer += 1;
	}
	
	public static void main(String[] args){
		
		Controller ctrl;
		try {
			ctrl = new Controller();
			ctrl.newGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


