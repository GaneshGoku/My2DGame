package tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Map extends TileManager {
	
	GamePanel gp;
	BufferedImage worldMap[];
	public boolean miniMapOn=false;
	

	public Map(GamePanel gp) {
		super(gp);
		this.gp=gp;
		createWotldMap();
	}
	public void createWotldMap() {
		 
		worldMap=new BufferedImage[gp.maxMap];
		int worldMapWidth=gp.tilesize*gp.maxWorldCol;
		int worldMapHeight= gp.tilesize* gp.maxWorldRow;
		
		for(int i=0;i<gp.maxMap;i++) {
			worldMap[i]=new BufferedImage(worldMapWidth,worldMapHeight,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2= (Graphics2D)worldMap[i].createGraphics();
			
			int row=0;
			int col=0;
			
			while(col<gp.maxWorldCol && row<gp.maxWorldRow) {
				int tileNum=mapTileNum[i][col][row];
				int x= gp.tilesize* col;
				int y= gp.tilesize*row;
				g2.drawImage(tile[tileNum].image,x,y,null);
				
				col++;
				if(col==gp.maxWorldCol) {
					col=0;
					row++;
				}
			}
		}
	}
	public void drawFullMapScreen(Graphics2D g2) {
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);
		
		int width=500;
		int height=500;
		int x=gp.screenwidth/2 - width/2;
		int y=(gp.screenwidth/2- height/2)-200;
		g2.drawImage(worldMap[gp.currentMap], x, y,width,height,null);
		
		double scale= (double)(gp.tilesize* gp.maxWorldCol)/width;
		int playerx= (int)(x+ gp.player.worldX/scale);
		int playery=(int)(y+ gp.player.worldY/scale);
		int playerSize= (int)(gp.tilesize/scale);
		g2.drawImage(gp.player.down1, playerx, playery, playerSize, playerSize, null);
		
		g2.setFont(gp.ui.ink.deriveFont(23F));
		g2.setColor(Color.white);
		g2.drawString("Press M to Close", 750, 550);
	}

	public void drawMiniMap(Graphics2D g2) {
		
		if(miniMapOn==true) {
			
			int width=200;
			int height=200;
			
			int x= gp.screenwidth-width-50;
			int y=50;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
			g2.drawImage(worldMap[gp.currentMap], x, y,width,height,null);
			
			double scale= (double)(gp.tilesize* gp.maxWorldCol)/width;
			int playerx= (int)(x+ gp.player.worldX/scale);
			int playery=(int)(y+ gp.player.worldY/scale);
			int playerSize= (int)(gp.tilesize/3);
			g2.drawImage(gp.player.down1, playerx-5, playery-2, playerSize, playerSize, null);
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		}
	}
}
