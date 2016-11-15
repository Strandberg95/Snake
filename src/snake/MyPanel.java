package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.*;

public class MyPanel extends JPanel {
	
	private boolean[][] grid1;
	private boolean[][] grid2;
	
	private Color grid1Color;
	private Color grid2Color;
	
	private Random rdm;
	
	public MyPanel(boolean[][] grid1,boolean[][] grid2){
		this.grid1 = grid1;
		this.grid2 = grid2;
		
		rdm = new Random();
		
		grid1Color = new Color(rdm.nextInt(256),rdm.nextInt(256),rdm.nextInt(256));
		grid2Color = new Color(rdm.nextInt(256),rdm.nextInt(256),rdm.nextInt(256));
	}
	
	public void paintComponent(Graphics g){
//		System.out.println(rdm.nextInt());
		super.paintComponent(g);
		paintLines(grid1,g);
		paintGrid(grid1,g,grid1Color);
		paintGrid(grid2,g,grid2Color);
	}
	
	public void paintLines(boolean[][] grid,Graphics g){
		
		g.setColor(Color.gray);
		
		int boxWidth = this.getWidth() / grid.length;
		int boxHeight = this.getHeight() / grid.length;
		
		for(int i = 0; i < grid.length ; i++){
			g.drawLine(i * boxWidth , 0, i * boxWidth, this.getHeight());
			g.drawLine(0 ,boxHeight * i,this.getWidth(), boxHeight * i);
//			g.drawLine(x1,     y1,             x2,             y2);
		}
		
	}
	
	public void paintGrid(boolean[][] grid,Graphics g, Color color){
		
		int boxWidth = this.getWidth() / grid.length;
		int boxHeight = this.getHeight() / grid.length;
		
		for(int row = 0; row < grid.length; row++){
			for(int column = 0; column < grid.length; column++ ){
				if(grid[row][column] == true){
					g.setColor(color);
					g.fillRect(row * boxWidth, column * boxHeight, boxWidth, boxHeight);
				}
//				if(grid[row][column] == false){
//					g.setColor(color);
//					g.fillRect(row * boxWidth, column * boxHeight, boxWidth, boxHeight);
//				}
			}
		}
	}
	

}
