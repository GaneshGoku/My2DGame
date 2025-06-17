package main;

import java.awt.BasicStroke;
import Entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import object.OBJ_Key;
import object.OBJ_ManaCrystal;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    public Font ink;
    BufferedImage heart_full,heart_half,heart_blank, crystal_full, crystal_blank,coin;
    BufferedImage slotImage; // Image for the inventory slot background
    BufferedImage selectedSlotImage; // Image for the selected slot (highlighted)
    BufferedImage backgroundImage; //bg image for title screen
    public boolean messageOn= false;
//    public String message = "";
    
    ArrayList<String> message= new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>(); 
    int slotSize;
    int cols = 9;  // 9 slots in the hotbar
    int selectedSlot = 0; // Currently selected slot
    int spacing = 10;  // Spacing between slots
//    int messageCounter=0;
    public boolean gameFinished= false; 
    public String currentDialogue= "";
    public int commandNum=0;
    public int playerSlotCol=0;
    public int playerSlotRow=0;
    public  int npcSlotCol=0;
    public int npcSlotRow=0;
    public int subState=0;
    public int counter=0;
    public Entity npc;
    int charIndex=0;
    String combinedText=" ";
    

    // A class to represent an inventory item
    class InventoryItem {
        BufferedImage image;
        int count;

        InventoryItem(BufferedImage image, int count) {
            this.image = image;
            this.count = count;
        }
    }
    // List to store inventory items
    List<InventoryItem> inventoryItems;
    // Constructor accepting the slot image and selected slot image
    public UI(GamePanel gp, BufferedImage slotImage, BufferedImage selectedSlotImage) {
        this.gp = gp;
        loadBackground();
        
        try {
        	InputStream is= getClass().getResourceAsStream("/font/Mantinia_Regular.TTF");
			ink=Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        //Heart Object
        Entity heart= new OBJ_Heart(gp);
        heart_full=heart.image;
        heart_half=heart.image2;
        heart_blank=heart.image3;
        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full= crystal.image;
        crystal_blank= crystal.image2;
        Entity bronzeCoin= new OBJ_Coin_Bronze(gp);
        coin = bronzeCoin.down1;
        
        // Initialize the slot and selected slot images
        this.slotImage = slotImage;
        this.selectedSlotImage = selectedSlotImage;

        // Set the inventory slot size (can be based on tile size)
        slotSize = gp.tilesize;

        // Initialize inventory with placeholder (items will be updated dynamically)
        inventoryItems = new ArrayList<>();

        // Example item: OBJ_Key (ensure this loads properly in your project)
        BufferedImage keyImage = new OBJ_Key(gp).image;  // Replace with actual loading method
        if (keyImage != null) {
            inventoryItems.add(new InventoryItem(keyImage, 0));  // Initialize with 0 keys
        }
        // Add more items as necessary
    }

    public void addMessage(String text) {
    	message.add(text);
    	messageCounter.add(0);
    }
    // Draws the inventory bar
    public void draw(Graphics2D g2) {
    	this.g2= g2;
    	 g2.setFont(ink);
    	 g2.setColor(Color.WHITE);
    	 //Title State
    	 if(gp.gameState==gp.titleState) {
    		 drawTitleScreen();
    	 }
    	 //play state
    	 if(gp.gameState==gp.playState) {
    		 drawPlayerLife();
    		 drawMonsterLife();
    		 drawMessage();
    	 }
    	 //pause state
    	 if(gp.gameState==gp.pauseState) {
    		 drawPlayerLife();
    		 drawPauseScreen();
    	 }
    	 //Dialogue state
    	 if(gp.gameState==gp.dialogueState) {
    		 drawPlayerLife();
    		 drawDialogueScreen();
    	 }
    	 //character State
    	 if(gp.gameState==gp.characterState) {
    		 drawCharaterScreen();
    		 drawInventry(gp.player,true);
    	 }
    	 if(gp.gameState==gp.optionsState) {
    		 drawOptionScreen();
    	 }
    	 //game Over State
    	 if(gp.gameState==gp.gameOverState) {
    		 drawGameOverScreen();
    	 }
    	 if(gp.gameState==gp.transitionState) {
    		 drawTransitionScreen();
    	 }
    	 if(gp.gameState==gp.tradeState) {
    		 drawTradeScreen();
    	 }
    	 if(gp.gameState==gp.sleepState) {
    		 drawSleepScreen();
    	 }
    }
    
    public void drawPlayerLife() {
//    	gp.player.life=5;
    	int x=gp.tilesize/2;
    	int y= gp.tilesize/2;
    	int i=0;
    	
    	while(i<gp.player.maxLife/2){
    		g2.drawImage(heart_blank, x, y, null);
    		i++;
    		x+=gp.tilesize;
    	}
    	 x=gp.tilesize/2;
    	 y=gp.tilesize/2;
    	 i=0;
    	while(i<gp.player.life) {
    		g2.drawImage(heart_half, x, y, null);
    		i++;
    		if(i<gp.player.life) {
    			g2.drawImage(heart_full, x, y, null);
    		}
    		i++;
    		x+=gp.tilesize;
    	}
    	//Draw max mana
    	x= (gp.tilesize/2)-5;
    	y= (int)(gp.tilesize*1.5);
    	i=0;
    	while(i<gp.player.maxMana) {
    		g2.drawImage(crystal_blank, x,y,null);
    		i++;
    		x+=35;
    	}
    	//Draw Mana
    	x= (gp.tilesize/2)-5;
    	y= (int)(gp.tilesize*1.5);
    	i=0;
    	while(i<gp.player.mana) {
    		g2.drawImage(crystal_full, x, y, null);
    		i++;
    		x+=35;
    	}
    }
    
    public void drawMonsterLife() {
    	
    	for(int i=0;i<gp.monster[1].length;i++) {
    		Entity monster= gp.monster[gp.currentMap][i];
    		if(monster!=null && monster.inCamera()==true ) {
    			if(monster.hpBarOn==true && monster.boss==false) {
    	        	
    	        	double oneScale = (double)gp.tilesize/monster.maxLife;
    	        	double hpBarValue= oneScale*monster.life;
    	        	
    	        	g2.setColor(new Color(35,35,35));
    	        	g2.fillRect(monster.getScreenX()-1, monster.getScreenY()-16, gp.tilesize+2, 12);
    	        	
    	        	g2.setColor(new Color(255,0,30));
    	        	g2.fillRect(monster.getScreenX(), monster.getScreenY()-15, (int)hpBarValue, 10);
    	        	
    	        	monster.hpBarCounter++;
    	        	
    	        	if(monster.hpBarCounter>600) {
    	        		monster.hpBarCounter=0;
    	        		monster.hpBarOn= false;
    	        	}
    	        }
    			else if(monster.boss==true) {
        			double oneScale = (double)gp.tilesize*8/monster.maxLife;
    	        	double hpBarValue= oneScale*monster.life;
    	        	
    	        	int x=gp.screenwidth/2- gp.tilesize*4;
    	        	int y=gp.tilesize*10;
    	        	
    	        	g2.setColor(new Color(35,35,35));
    	        	g2.fillRect(x-1, y-1, gp.tilesize*8+2, 22);
    	        	
    	        	g2.setColor(new Color(255,0,30));
    	        	g2.fillRect(x, y, (int)hpBarValue, 20);
    	        	
    	        	g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
    	        	g2.setColor(Color.white);
    	        	g2.drawString(monster.name, x+4, y-10);
        		}
    		}
    	}
    }
    
    public void drawMessage() {
    	int messageX= gp.tilesize;
    	int messageY= gp.tilesize*4;
    	
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
    	
    	for(int i=0;i<message.size();i++) {
    		
    		if(message.get(i)!= null) {
    			
    			g2.setColor(Color.black);
    			g2.drawString(message.get(i), messageX+2, messageY);
    			g2.setColor(Color.white);
    			g2.drawString(message.get(i), messageX, messageY);
    			
    			int counter= messageCounter.get(i) +1;
    			messageCounter.set(i, counter);
    			messageY+=50;
    			
    			if(messageCounter.get(i)>100) {
    				message.remove(i);
    				messageCounter.remove(i);
    			}
    			
    		}
    	}
    }
    
    public void loadBackground() {
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/maps/title_bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void drawTitleScreen() {
    	
    	if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, gp.screenwidth, gp.screenheight, null);
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);
        }
    	
    	//Title Name
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD,55F));
    	String text = "Boy's Bizarre Adventure";
    	int x=getXforCenteredText(text);
    	int y= gp.tilesize*3;
    	//Shadow
    	g2.setColor(Color.gray);
    	g2.drawString(text, x+5, y+5);
    	
    	g2.setColor(Color.BLACK);
    	g2.drawString(text, x, y);
    	
    	//Boy image
    	x=gp.screenwidth/2-(gp.tilesize*2)/2;
    	y+=gp.tilesize*2;
    	g2.drawImage(gp.player.down1, x, y,gp.tilesize*2,gp.tilesize*2,null);
    	
    	//Menu
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
    	
    	text="New Game";
    	x=getXforCenteredText(text);
    	y+=gp.tilesize*3.5;
    	g2.drawString(text, x, y);
    	if(commandNum==0) {
    		g2.drawString("-", x-gp.tilesize, y);
    	}
    	
    	text="Load Game";
    	x=getXforCenteredText(text);
    	y+=gp.tilesize;
    	g2.drawString(text, x, y);
    	if(commandNum==1) {
    		g2.drawString("-", x-gp.tilesize, y);
    	}
    	
    	text="Quit";
    	x=getXforCenteredText(text);
    	y+=gp.tilesize;
    	g2.drawString(text, x, y);
    	if(commandNum==2) {
    		g2.drawString("-", x-gp.tilesize, y);
    	}
    }
    
    public void drawPauseScreen() {
    	
    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN,55F));
    	String text= "PAUSED";
    	int x= getXforCenteredText(text);
    	int y= gp.screenheight/2;
    	
    	g2.drawString(text,x,y);
    }
    
    public void drawDialogueScreen() {
    	
    	int x=gp.tilesize*3;
    	int y=gp.tilesize/2;
    	int width=gp.screenwidth - (gp.tilesize*6);
    	int height=gp.tilesize*4;
    	drawSubWindow(x,y,width,height);
    	
    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
    	x+=gp.tilesize;
    	y+=gp.tilesize;
    	
    	if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex]!=null) {
//    		currentDialogue= npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
    		
    		char characters[]=npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
    		
    		if(charIndex<characters.length) {
    			gp.PlaySE(17);
    			String s= String.valueOf(characters[charIndex]);
    			combinedText= combinedText + s;
    			currentDialogue= combinedText;
    			charIndex++;
    		}
    		if(gp.keyH.enter==true) {
    			
    			charIndex=0;
    			combinedText="";
    			if(gp.gameState==gp.dialogueState) {
        			npc.dialogueIndex++;
        			gp.keyH.enter=false;
        		}
    		}
    	}
    	else {
    		npc.dialogueIndex=0;
    		if(gp.gameState==gp.dialogueState) {
    			gp.gameState=gp.playState;
    		}
    	}
    	for(String line :currentDialogue.split("\n")) {
        	g2.drawString(line, x, y);
        	y += 40;
    	}
    }
    
    public void drawCharaterScreen() {
    	
    	final int frameX=gp.tilesize*2;
    	final int frameY= gp.tilesize;
    	final int frameWidth=gp.tilesize*5;
    	final int frameHeigth= gp.tilesize*10;
    	drawSubWindow(frameX,frameY,frameWidth,frameHeigth);
    	
    	g2.setColor(Color.white);
    	g2.setFont(g2.getFont().deriveFont(24F));
    	
    	int textX= frameX + 20;
    	int textY= frameY +gp.tilesize;
    	final int lineHeigth=35;
    	
    	g2.drawString("Level", textX, textY);
    	textY+=lineHeigth;
    	g2.drawString("Life", textX, textY);
    	textY+=lineHeigth;
    	g2.drawString("Mana", textX, textY);
    	textY+=lineHeigth;
    	g2.drawString("Strength", textX, textY);
    	textY+=lineHeigth;
    	g2.drawString("Dexterity", textX, textY);
    	textY+=lineHeigth;
    	g2.drawString("Attack", textX, textY);
    	textY+=lineHeigth;
    	g2.drawString("defense", textX, textY);
    	textY+=lineHeigth;
    	g2.drawString("Exp", textX, textY);
    	textY+=lineHeigth;
    	g2.drawString("Next Level", textX, textY);
    	textY+=lineHeigth;
    	g2.drawString("Coin", textX, textY);
    	textY+=lineHeigth+10;
    	g2.drawString("Weapon", textX, textY);
    	textY+=lineHeigth+15;
    	g2.drawString("Shield", textX, textY);
    	textY+=lineHeigth;
    	
    	//values
    	int tailX= (frameX +frameWidth)-30;
    	//reset texty
    	textY= frameY + gp.tilesize;
    	String value;
    	
    	value= String.valueOf(gp.player.level);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	value= String.valueOf(gp.player.life + "/"  + gp.player.maxLife);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	value= String.valueOf(gp.player.mana + "/"  + gp.player.maxMana);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	value= String.valueOf(gp.player.strength);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	value= String.valueOf(gp.player.dexterity);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	value= String.valueOf(gp.player.attack);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	value= String.valueOf(gp.player.defense);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	value= String.valueOf(gp.player.exp);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	value= String.valueOf(gp.player.nextLevelExp);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	value= String.valueOf(gp.player.coin);
    	textX=getXforAlignToRigthText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY+=lineHeigth;
    	
    	g2.drawImage(gp.player.currentWeapon.down1,tailX-gp.tilesize,textY-25,null);
    	textY+=gp.tilesize;
    	g2.drawImage(gp.player.currentShield.down1,tailX-gp.tilesize,textY-25,null);
    }
    
    public void drawInventry(Entity entity, boolean cursor) {
    	
    	int frameX=0;
    	int frameY= 0;
    	int frameWidth= 0;
    	int frameHeight= 0;
    	int slotCol=0;
    	int slotRow=0;
    	
    	if(entity==gp.player) {
    		
    		 frameX= gp.tilesize *12;
        	 frameY= gp.tilesize;
        	 frameWidth= gp.tilesize * 6;
        	 frameHeight= gp.tilesize *5;
        	 slotCol= playerSlotCol;
        	 slotRow= playerSlotRow;
    	}
    	else {
    		
    		 frameX= gp.tilesize *2;
        	 frameY= gp.tilesize;
        	 frameWidth= gp.tilesize * 6;
        	 frameHeight= gp.tilesize *5;
        	 slotCol= npcSlotCol; 
        	 slotRow= npcSlotRow;
    	}
    	
    	
    	drawSubWindow(frameX, frameY,frameWidth,frameHeight);
    	
    	final int slotXstart= frameX +20;
    	final int slotYstart= frameY + 20;
    	int slotX= slotXstart;
    	int slotY= slotYstart;
    	int slotsize= gp.tilesize+3;
    	
    	for(int i=0;i<entity.inventory.size();i++) {
    		
    		if(entity.inventory.get(i)== entity.currentWeapon || entity.inventory.get(i)== entity.currentShield || entity.inventory.get(i)==entity.currentLight ) {
    			g2.setColor(new Color(240,190,90));
    			g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
    		}
    		
    		g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
    		
    		//Display amount
    		if(entity==gp.player &&entity.inventory.get(i).amount>1) {
    			g2.setFont(g2.getFont().deriveFont(32f));
    			int amountX;
    			int amountY;
    			
    			String s=""+ entity.inventory.get(i).amount;
    			amountX=getXforAlignToRigthText(s,slotX+44);
    			amountY= slotY +gp.tilesize;
    			
    			g2.setColor(new Color(60,60,60));
    			g2.drawString(s, amountX, amountY);
    			
    			g2.setColor(Color.white);
    			g2.drawString(s, amountX-3, amountY-3);
    		}
    		
    		slotX+=slotsize;
    		
    		if(i==4 || i==9 || i==14) {
    			slotX=slotXstart;
    			slotY+= slotsize; 
    		}
    	}
    	
    	if(cursor==true) {
    		
    		int curserX= slotXstart +(slotsize * slotCol);
        	int curserY= slotYstart +(slotsize * slotRow);
        	int curserWidth= gp.tilesize;
        	int curserHeight= gp.tilesize;
        	
        	g2.setColor(Color.white);
        	g2.setStroke(new BasicStroke(3));
        	g2.drawRoundRect(curserX, curserY, curserWidth, curserHeight,10,10);
        	
        	//Description Box
        	int dFrameX= frameX;
        	int dFrameY= frameY+ frameHeight;
        	int dFrameWidth= frameWidth;
        	int dFrameHeigth= gp.tilesize*3;
        	
        	//Description Text
        	int textX=dFrameX+20;
        	int texty= dFrameY +gp.tilesize;
        	g2.setFont(g2.getFont().deriveFont(20F));
        	
        	int itemIndex= getItemIndexOnSlot(slotCol,slotRow);
        		if(itemIndex<entity.inventory.size()) {
        			
        			drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeigth);
        			
        			for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
        				g2.drawString(line, textX, texty);
        				texty+=32;
        		}
        	}
    	}
    }
    
    public void drawGameOverScreen() {
    	
    	g2.setColor(new Color(0,0,0,150));
    	g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);
    	
    	int x;
    	int y;
    	String text,text1;
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
    	
    	text= "Game Over";
    	//shadow
    	g2.setColor(Color.black);
    	x= getXforCenteredText(text);
    	y= gp.tilesize*4;
    	g2.drawString(text, x, y);
    	//main
    	g2.setColor(Color.white);
    	g2.drawString(text, x-4, y-4);
    	
    	text= "Skill Issue";
    	//shadow
    	g2.setColor(Color.black);
    	x= getXforCenteredText(text);
    	y= gp.tilesize*4;
    	g2.drawString(text, x, y+120);
    	//main
    	g2.setColor(Color.white);
    	g2.drawString(text, x-4, y+116);
    	
    	//Retry
    	g2.setFont(g2.getFont().deriveFont(50f));
    	text= "Retry";
    	x= getXforCenteredText(text);
    	y+=gp.tilesize*4;
    	g2.drawString(text, x, y);
    	if(commandNum==0) {
    		g2.drawString("-", x-40, y);
    	}
    	//Quit
    	text="Quit";
    	x= getXforCenteredText(text);
    	y+=50;
    	g2.drawString(text, x, y);
    	if(commandNum==1) {
    		g2.drawString("-", x-40, y);
    	}
    }
    
    public void drawOptionScreen() {
    	
    	g2.setColor(Color.white);
    	g2.setFont(g2.getFont().deriveFont(32F));
    	
    	int frameX= gp.tilesize*6;
    	int frameY= gp.tilesize;
    	int frameWidth= gp.tilesize*8;
    	int frameHeight= gp.tilesize*10;
    	
    	drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    	
    	switch(subState) {
    	case 0: options_top(frameX, frameY); break;
    	case 1: options_fullScreenNotification(frameX, frameY); break;
    	case 2: options_control(frameX, frameY); break;
    	case 3: options_endGameConfirmation(frameX, frameY);
    	}
    	
    	gp.keyH.enter=false;
    }
    
    public void options_top(int frameX, int frameY) {
    	int textX;
    	int textY;
    	
    	String text= "Options";
    	textX= getXforCenteredText(text);
    	textY= frameY + gp.tilesize;
    	g2.drawString(text, textX, textY);
    	
    	//Full screen
    	textX=frameX+ gp.tilesize;
    	textY+=gp.tilesize*2;
    	g2.drawString("Ful Screen", textX, textY);
    	if(commandNum==0) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enter==true) {
    			if(gp.fullScreenOn==false) {
    				gp.fullScreenOn=true;
    			}
    			else if(gp.fullScreenOn==true) {
    				gp.fullScreenOn=false;
    			}
    			subState=1;
    		}
    	}
    	
    	//Music
    	textX=frameX+ gp.tilesize;
    	textY+=gp.tilesize;
    	g2.drawString("Music", textX, textY);
    	if(commandNum==1) {
    		g2.drawString("-", textX-25, textY);
    	}
    	//SE
    	textX=frameX+ gp.tilesize;
    	textY+=gp.tilesize;
    	g2.drawString("SE", textX, textY);
    	if(commandNum==2) {
    		g2.drawString("-", textX-25, textY);
    	}
    	//Control
    	textX=frameX+ gp.tilesize;
    	textY+=gp.tilesize;
    	g2.drawString("Control", textX, textY);
    	if(commandNum==3) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enter==true) {
    			subState=2;
    			commandNum=0;
    		}
    	}
    	//End Game
    	textX=frameX+ gp.tilesize;
    	textY+=gp.tilesize;
    	g2.drawString("End Game", textX, textY);
    	if(commandNum==4) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enter==true) {
    			subState=3;
    			commandNum=0;
    		}
    	}
    	//BAck
    	textX=frameX+ gp.tilesize;
    	textY+=gp.tilesize*2;
    	g2.drawString("Back", textX, textY);
    	if(commandNum==5) {
    		g2.drawString("-", textX-25, textY);
    	}
    	//Full screen 
    	textX= frameX + (int)(gp.tilesize*4.5);
    	textY= frameY + gp.tilesize*2 + 24;
    	g2.setStroke(new BasicStroke(3));
    	g2.drawRect(textX, textY, 24, 24);
    	if(gp.fullScreenOn==true) {
    		g2.fillRect(textX, textY, 24, 24);
    	}
    	//Music
    	textY+=gp.tilesize;
    	g2.drawRect(textX, textY, 120, 24);
    	int volumeWidth= 24* gp.music.volumeScale;
    	g2.fillRect(textX, textY, volumeWidth, 24);
    	//SE
    	textY+=gp.tilesize;
    	g2.drawRect(textX, textY, 120, 24);
    	volumeWidth= 24* gp.sound.volumeScale;
    	g2.fillRect(textX, textY, volumeWidth, 24);
    	
    	gp.config.saveConfig();
    }
    
    public void options_fullScreenNotification(int frameX, int frameY) {
    	
    	int textX= frameX+ gp.tilesize;
    	int textY= frameY + gp.tilesize*3;
    	
    	currentDialogue= "The Change will be \nimplemented after \n restarting";
    	for(String line : currentDialogue.split("\n")) {
    		g2.drawString(line, textX, textY);
    		textY+=40;
    	}
    	//back
    	textY= frameY + gp.tilesize*9;
    	g2.drawString("Back", textX, textY);
    	if(commandNum==0) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enter==true) {
    			subState=0;
    		}
    	}
    }
    
    public void options_control(int frameX,int frameY) {
    	int textX;
    	int textY;
    	
    	String text = "Control";
    	textX= getXforCenteredText(text);
    	textY= frameY + gp.tilesize;
    	g2.drawString(text, textX, textY);
    	
    	textX=frameX+ gp.tilesize;
    	textY += gp.tilesize;
    	g2.drawString("Move", textX, textY); textY+=gp.tilesize;
    	g2.drawString("Confirm/Attack", textX, textY); textY+=gp.tilesize;
    	g2.drawString("Shoot/Cast", textX, textY); textY+=gp.tilesize;
    	g2.drawString("Character Screen", textX, textY); textY+=gp.tilesize;
    	g2.drawString("Pause", textX, textY); textY+=gp.tilesize;
    	g2.drawString("Options", textX, textY); textY+=gp.tilesize;
    	
    	textX=frameX + gp.tilesize*6;
    	textY = frameY + gp.tilesize*2;
    	g2.drawString("wasd", textX, textY); textY+=gp.tilesize;
    	g2.drawString("enter", textX, textY); textY+=gp.tilesize;
    	g2.drawString("f", textX, textY); textY+=gp.tilesize;
    	g2.drawString("c", textX, textY); textY+=gp.tilesize;
    	g2.drawString("p", textX, textY); textY+=gp.tilesize;
    	g2.drawString("ecs", textX, textY); textY+=gp.tilesize;
    	
    	textY= frameY + gp.tilesize*9;
    	g2.drawString("Back", textX, textY);
    	if(commandNum==0) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enter==true) {
    			subState=0;
    			commandNum=3;
    		}
    	}
    }
    
    public void options_endGameConfirmation(int frameX, int frameY) {
    	int textX=frameX +gp.tilesize;
    	int textY= frameY + gp.tilesize;
    	
    	currentDialogue="Quit the Game";
    	
    	for(String line: currentDialogue.split("\n")) {
    		g2.drawString(line, textX, textY);
    		textY+=40;
    	}
    	//Yes
    	String text= "YES";
    	textX= getXforCenteredText(text);
    	textY+=gp.tilesize*3;
    	g2.drawString(text, textX, textY);
    	if(commandNum==0) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enter==true) {
    			subState=0;
    			gp.gameState= gp.titleState;
    			gp.resetGame(true);
    		}
    	}
    	
    	text= "NO";
    	textX= getXforCenteredText(text);
    	textY+=gp.tilesize;
    	g2.drawString(text, textX, textY);
    	if(commandNum==1) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enter==true) {
    			subState=0;
    			commandNum=4;
    		}
    	}		
    }
    public void drawTransitionScreen() {
    	counter++;
    	g2.setColor(new Color(0,0,0,counter*5));
    	g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);
    	
    	if(counter==50) {
    		counter=0;
    		gp.gameState=gp.playState;
    		gp.currentMap=gp.eHandler.tempMap;
    		gp.player.worldX= gp.tilesize* gp.eHandler.tempcol;
    		gp.player.worldY= gp.tilesize* gp.eHandler.temprow;
    	    gp.eHandler.previousEventX= gp.player.worldX;
    	    gp.eHandler.previousEventY= gp.player.worldY;
    	    gp.checkArea();
    	}
    }
    
    public void drawTradeScreen() {
    	switch(subState) {
    	case 0: trade_select(); break;
    	case 1: trade_buy(); break;
    	case 2: trade_sell(); break;
    	}
    	gp.keyH.enter= false;
    	
    }
    public void trade_select() {
    	npc.dialogueSet=0;
    	drawDialogueScreen();
    	
    	int x= gp.tilesize*15;
    	int y= gp.tilesize*4;
    	int width= gp.tilesize*3;
    	int height= (int)(gp.tilesize* 3.5);
    	drawSubWindow(x,y,width,height);
    	
    	x+=gp.tilesize;
    	y+= gp.tilesize;
    	g2.drawString("Buy", x, y);
    	if(commandNum==0) {
    		g2.drawString("-", x-24, y);
    		if(gp.keyH.enter==true) {
    			subState=1;
    		}
    	}
    	y+=gp.tilesize;
    	g2.drawString("Sell", x, y);
    	if(commandNum==1) {
    		g2.drawString("-", x-24, y);
    		if(gp.keyH.enter==true) {
    			subState=2;
    		}
    	}
    	y+=gp.tilesize;
    	g2.drawString("Leave", x, y);
    	if(commandNum==2) {
    		g2.drawString("-", x-24, y);
    		if(gp.keyH.enter==true) {
    			commandNum=0;
    			npc.startDialogue(npc,1);
    		}
    	}
    }
    public void trade_buy() {
    	
    	drawInventry(gp.player,false);
    	
    	drawInventry(npc,true);
    	
    	int x= gp.tilesize*2;
    	int y= gp.tilesize*9;
    	int width= gp.tilesize* 6;
    	int height= gp.tilesize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString("[ESC] BACK", x+24, y+60);
    	
    	x= gp.tilesize*12;
    	y= gp.tilesize*9;
    	width= gp.tilesize* 6;
    	height= gp.tilesize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString("Your Coins: " + gp.player.coin, x+24, y+60);
    	
    	//price
    	int itemIndex= getItemIndexOnSlot(npcSlotCol, npcSlotRow);
    	if(itemIndex< npc.inventory.size()) {
    		x=(int)(gp.tilesize*5.5);
    		y=(int)(gp.tilesize*5.5);
    		width=(int)(gp.tilesize*2.5);
    		height= gp.tilesize;
    		drawSubWindow(x,y,width,height);
    		g2.drawImage(coin, x+10, y+8, 32,32 ,null);
    		
    		int price=npc.inventory.get(itemIndex).price;
    		String text= ""+price;
    		x= getXforAlignToRigthText(text,gp.tilesize*8-20);
    		g2.drawString(text, x, y+32);
    		
    		//buy item
    		if(gp.keyH.enter==true) {
    			if(npc.inventory.get(itemIndex).price> gp.player.coin) {
    				subState=0;
    				npc.startDialogue(npc,2);
    				drawDialogueScreen();
    			}
    			else {
    				if(gp.player.canObtainItem(npc.inventory.get(itemIndex))==true) {
    					gp.player.coin-=npc.inventory.get(itemIndex).price;
    				}
    				else {
        				subState=0;
        				npc.startDialogue(npc,3);
    			}
    				
    			}
    		}
    	}
    }
    public void trade_sell() {
    	
    	drawInventry(gp.player,true);
    	
    	int x;
    	int y;
    	int width;
    	int height;
    	
    	x= gp.tilesize*2;
    	y= gp.tilesize*9;
    	width= gp.tilesize* 6;
    	height= gp.tilesize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString("[ESC] BACK", x+24, y+60);
    	
    	x= gp.tilesize*12;
    	y= gp.tilesize*9;
    	width= gp.tilesize* 6;
    	height= gp.tilesize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString("Your Coins: " + gp.player.coin, x+24, y+60);
    	
    	//price
    	int itemIndex= getItemIndexOnSlot(playerSlotCol, playerSlotRow);
    	if(itemIndex< gp.player.inventory.size()) {
    		x=(int)(gp.tilesize*15.5);
    		y=(int)(gp.tilesize*5.5);
    		width=(int)(gp.tilesize*2.5);
    		height= gp.tilesize;
    		drawSubWindow(x,y,width,height);
    		g2.drawImage(coin, x+10, y+8, 32,32 ,null);
    		
    		int price=gp.player.inventory.get(itemIndex).price/2;
    		String text= ""+price;
    		x= getXforAlignToRigthText(text,gp.tilesize*18-20);
    		g2.drawString(text, x, y+32);
    		
    		//buy item
    		if(gp.keyH.enter==true) {
    			if(gp.player.inventory.get(itemIndex)== gp.player.currentWeapon || gp.player.inventory.get(itemIndex)==gp.player.currentShield) {
    				commandNum=0;
    				subState=0;
    				npc.startDialogue(npc,4);
    			}
    			else {
    				if(gp.player.inventory.get(itemIndex).amount>1) {
    					gp.player.inventory.get(itemIndex).amount--;
    				}
    				else {
    					gp.player.inventory.remove(itemIndex);
    				}
    				gp.player.coin+=price;
    			}
    		}
    	}
    }
    public void drawSleepScreen() {
    	
    	counter++;
    	
    	if(counter<120) {
    		gp.emanager.lighting.filterAlpha+=0.01f;
    		if(gp.emanager.lighting.filterAlpha>1f) {
    			gp.emanager.lighting.filterAlpha=1f;
    		}
    	}
    	if(counter>120) {
    		gp.emanager.lighting.filterAlpha-=0.01f;
    		if(gp.emanager.lighting.filterAlpha<=0f) {
    			gp.emanager.lighting.filterAlpha=0f;
    			counter=0;
    			gp.emanager.lighting.dayState=gp.emanager.lighting.day;
    			gp.emanager.lighting.dayCounter=0;
    			gp.gameState=gp.playState;
    			gp.player.getPlayerImage();
    		}
    	}
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow) {
    	int itemIndex=slotCol + (slotRow *5);
    	return itemIndex;
    }
    
    public void drawSubWindow(int x, int y, int width,int height) {
    	Color c= new Color(0,0,0,200);
    	g2.setColor(c);
    	g2.fillRoundRect(x, y, width, height, 35, 35);
    	
    	c=new Color (255,255,255) ;
    	g2.setColor(c);
    	g2.setStroke(new BasicStroke(5));
    	g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXforCenteredText(String text) {
    	int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    	int x=gp.screenwidth/2- length/2;
    	return x; 
    }
    
    public int getXforAlignToRigthText(String text,int tailX) {
    	int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    	int x=tailX- length;
    	return x; 
    }

    // Set selected slot (can be used for changing selection during gameplay)
    public void setSelectedSlot(int index) {
        selectedSlot = index % cols; // Wrap around to keep within 0-8 range
    }
}
