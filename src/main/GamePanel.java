package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Data.SaveLoad;
import Entity.Entity;
import Entity.Player;
import ai.PathFinder;
import environment.EnvironmentManager;
import tile.Map;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable {
    //screen
	final int originalTileSize=16; //16x16 tile
	final int scale=3;
	
	 public final int tilesize=originalTileSize * scale; //48x48 tile
	
	public final int maxscreenrow=12;
	public final int maxscreencol=20;
	public final int screenwidth=tilesize * maxscreencol;
	public final int screenheight=tilesize * maxscreenrow;
	
	//world setting 
	public int maxWorldCol;
	public int maxWorldRow;
	public final int maxMap=10;
	public int currentMap=0;
	public final int worldWidth= tilesize * maxWorldCol;
	public final int worldHeight= tilesize * maxWorldRow;
	
	//Full screen
	int screenWidth2= screenwidth;
	int screenHeight2= screenheight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn= false;
	Config config= new Config(this);
	
	int FPS=60;
	
	public TileManager tileM= new TileManager(this);
	public KeyHandler keyH=new KeyHandler(this);
	Sound music = new Sound();
	Sound sound = new Sound();
	public CollisionChecker cChecker= new CollisionChecker(this);
	public AssetSetter aSetter= new AssetSetter(this);
	public UI ui = new UI(this, null, null);
	public EventHandler eHandler = new EventHandler(this);
	public PathFinder pFinder= new PathFinder(this);
	EnvironmentManager emanager= new EnvironmentManager(this);
	public EntityGenerator eGenerator= new EntityGenerator(this);
	Map map= new Map(this);
	SaveLoad saveLoad= new SaveLoad(this);
	Thread gameThread;
	
	//player and npc
	public Player player= new Player(this,keyH);
	public Entity obj[][]= new Entity[maxMap][100];
	public Entity npc[][]= new Entity[maxMap][10];
	public Entity monster[][]= new Entity[maxMap][30];
	
	//activity elements
	public InteractiveTile[][] iTile = new InteractiveTile[maxMap][50];
	public Entity projectile[][]=new Entity[maxMap][20];
//	public ArrayList<Entity> projectileList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> particleList= new ArrayList<>();
	
	//game State
	public int gameState;
	public final int titleState=0;
	public final int playState=1;
	public final int pauseState=2;
	public final int dialogueState=3;
	public final int characterState=4;
	public final int optionsState=5;
	public final int gameOverState=6;
	public final int transitionState=7;
	public final int tradeState=8;
	public final int sleepState=9;
	public final int mapState=10;
	
	//Map things
	public int currentArea;
	public int nextArea; 
	public final int indoor=50;
	public final int outside=51;
	public final int dungeon=52;
	public final int finalBoss=53;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenwidth, screenheight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);  
        this.setFocusable(true);
		
	}
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		currentArea=outside;
		aSetter.setInteractiveTile();
		emanager.setup();
		playMusic(0);
		gameState= titleState;
		
		tempScreen= new BufferedImage(screenwidth,screenheight, BufferedImage.TYPE_INT_ARGB);
		g2=(Graphics2D)tempScreen.getGraphics();
		
		if(fullScreenOn==true) {
			setFullScreen();
		}
	}
	
	public void resetGame(boolean restart) {
		
		currentArea= outside;
		player.setDefaultPositions();
		player.restoreStatus();
		player.resetCounter();
		aSetter.setNPC();
		aSetter.setMonster();
		
		if(restart==true) {
			player.setDefaultValues();
			player.setItems();
			aSetter.setObject();		
			aSetter.setInteractiveTile();
			emanager.lighting.resetDay();
		}
	}
	
	public void setFullScreen() {
		
		GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd= ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		screenWidth2=Main.window.getWidth();
		screenHeight2= Main.window.getHeight();
	}
	
     public void startGameThread() {
    	 gameThread= new Thread(this);
    	 gameThread.start();
     }
     
	public void run() {
		
		double drawInterval=1000000000/FPS;
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
		long timer=0;
		long drawCount=0;
		
		while(gameThread!=null) {
			currentTime=System.nanoTime();
			
			delta+=(currentTime- lastTime)/drawInterval;
			
			timer+=(currentTime-lastTime);
			
			lastTime=currentTime;
			
			if(delta>=1) {
				
				update();
				drawToTempScreen();
				drawTempScreen();
				delta--;
				drawCount++;
			}
			if(timer>=1000000000) {
				drawCount=0;
				timer=0;
			}		
		}
	}
	public void drawTempScreen() {
		Graphics g= getGraphics();
		g.drawImage(tempScreen, 0,0,screenWidth2,screenHeight2,null);
		g.dispose();
	}
	
	public void update() {
		
		if(gameState==playState) {
			//player
			player.update();
			//npc
			for(int i=0; i<npc[1].length;i++) {
				if(npc[currentMap][i]!=null) {
					npc[currentMap][i].update();
				}
			}
			for(int i=0; i<monster[1].length;i++) {
				if(monster[currentMap][i]!=null) {
					if(monster[currentMap][i].alive==true && monster[currentMap][i].dying==false) {
						monster[currentMap][i].update();
					}
					if(monster[currentMap][i].alive==false) {
						monster[currentMap][i].checkDrop();
						monster[currentMap][i]=null;
					}
				}
			}
			
			for(int i=0; i<projectile[1].length;i++) {
				if(projectile[currentMap][i]!=null) {
					if(projectile[currentMap][i].alive==true  ) {
						projectile[currentMap][i].update();
					}
					if(projectile[currentMap][i].alive==false) {
						projectile[currentMap][i]=null;
					}
				}
			}
			for(int i=0; i<particleList.size();i++) {
				if(particleList.get(i)!=null) {
					if(particleList.get(i).alive==true  ) {
						particleList.get(i).update();
					}
					if(particleList.get(i).alive==false) {
						particleList.remove(i);
					}
				}
			}
			for(int i=0;i<iTile[1].length;i++) {
				if(iTile[currentMap][i]!=null) {
					iTile[currentMap][i].update();
				}
			}
			emanager.update(); 
		}
		if(gameState==pauseState) {
			//nothing for now
		}
	}
	public void drawToTempScreen() {
		
		//debug
				long drawStart=0;
				if(keyH.showDebugText==true) {
					drawStart=System.nanoTime();
				}
				
				//tital
				if(gameState==titleState) {
					ui.draw(g2);
				}
				//map
				else if(gameState== mapState) {
					map.drawFullMapScreen(g2);
				}
				//Other
				else {
					//Tile
					tileM.draw(g2);
					
					//Interactive tile
					
					for(int i=0;i<iTile[1].length;i++) {
						if(iTile[currentMap][i]!=null) {
							iTile[currentMap][i].draw(g2);
						}
					}
					
					//add entity list
					entityList.add(player);
					for(int i=0;i<npc[1].length;i++) {
						if(npc[currentMap][i]!=null) {
							entityList.add(npc[currentMap][i]);
						}
					}
					for(int i=0;i<obj[1].length;i++) {
						if(obj[currentMap][i]!=null) {
							entityList.add(obj[currentMap][i]);
						}
					}
					for(int i=0;i<monster[1].length;i++) {
						if(monster[currentMap][i]!=null) {
							entityList.add(monster[currentMap][i]);	 
						}
					}
					
					for(int i=0;i<projectile[1].length;i++) {
						if(projectile[currentMap][i]!=null) {
							entityList.add(projectile[currentMap][i ]);	 
						}
					}
					for(int i=0;i<particleList.size();i++) {
						if(particleList.get(i)!=null) {
							entityList.add(particleList.get(i));	 
						}
					}
					//sort
					Collections.sort(entityList, new Comparator<Entity>() {

						@Override
						public int compare(Entity e1, Entity e2) {
							int result=Integer.compare(e1.worldY, e2.worldY);
							
							return result;
						}
						
					});
					//Draw Entity
					for(int i=0;i<entityList.size();i++) {
						entityList.get(i).draw(g2);
					}  
					//Empty entity list
					entityList.clear();
					
					emanager.draw(g2);
					
					//minimap
					map.drawMiniMap(g2);
					
					//UI
					ui.draw(g2);
				}
				//debug
				if(keyH.showDebugText==true) {
					long drawEnd=System.nanoTime();
					long passed= drawEnd - drawStart;
					
					g2.setFont(new Font("Arial" ,Font.PLAIN,20));
					g2.setColor(Color.white);
					int x=10;
					int y=400;
					int LineHeight=20;
					
					g2.drawString("World X :" + player.worldX, x, y); y+=LineHeight;
					g2.drawString("World Y :" + player.worldY, x, y); y+=LineHeight;
					g2.drawString("Col :" +( player.worldX + player.solidArea.x) /tilesize, x, y); y+=LineHeight;
					g2.drawString("Row :" +( player.worldY + player.solidArea.y) /tilesize, x, y); y+=LineHeight;
					g2.drawString("Draw Time" + passed, x, y);y+=LineHeight;
					g2.drawString("GodModeOn"+ keyH.godModeOn, x, y);
					
				}	
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.end();
	}
	public void PlaySE(int i) {
		
		sound.setFile(i);
		sound.play();
	}
	public void checkArea() {
		if(nextArea!=currentArea) {
			stopMusic();
			
			if(nextArea==outside) {
				playMusic(0);
			}
			if(nextArea==indoor) {
				playMusic(18);
			}
			if(nextArea==dungeon) {
				playMusic(20);
			}
			if(nextArea==finalBoss) {
				playMusic(19);
			}
		}
		currentArea=nextArea;
		aSetter.setMonster();
	}
}
	

