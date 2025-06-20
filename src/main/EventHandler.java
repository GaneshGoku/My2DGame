package main;

import java.awt.Rectangle;

import Entity.Entity;

public class EventHandler {

	GamePanel  gp;
	EventRect eventRect[][][];
	Entity eventMaster;
	
	int previousEventX, previousEventY;
	boolean canTouchEvent= true;
	int tempMap, tempcol, temprow;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		eventMaster= new Entity(gp);
		
		int map=0;
		int col =0;
		int row=0;
		while( map<gp.maxMap &&  col<gp.maxWorldCol && row<gp.maxWorldRow) {
			eventRect[map][col][row]= new EventRect();
			eventRect[map][col][row].x=23;
			eventRect[map][col][row].y=23;
			eventRect[map][col][row].width=2;
			eventRect[map][col][row].height=2;
			eventRect[map][col][row].eventRectDefaultX= eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY= eventRect[map][col][row].y;
			
			col++;
			if(col==gp.maxWorldCol) {
				col=0;
				row++;
				
				if(row==gp.maxWorldRow) {
					row=0;
					map++;
				}
			}
		}
		setDialogue();
	}
	
	public void setDialogue() {
		eventMaster.dialogues[0][0]="watch your step ";
		
		eventMaster.dialogues[1][0]="youe Health and mana have been recoverd\n Game saved";;
	}
	
	public void checkEvent() {
		
		int xDistance= Math.abs(gp.player.worldX - previousEventX);
		int yDistance= Math.abs(gp.player.worldY - previousEventY);
        int distance= Math.max(xDistance, yDistance);
        if(distance>gp.tilesize) {
        	canTouchEvent=true;
        }
        if(canTouchEvent==true) {

    		if(hit(0,52,82,"any") == true){damagePit(gp.dialogueState);}
    		else if(hit(0,40,51,"any") == true){damagePit(gp.dialogueState);}
    		else if(hit(0,60,75,"up")==true) {healingPool(gp.dialogueState);}
    		else if(hit(0,75,37,"up")==true) {healingPool(gp.dialogueState);}
    		else if(hit(0,18,55,"up")==true) {healingPool(gp.dialogueState);}
    		else if(hit(0,23,89,"up")==true) {healingPool(gp.dialogueState);}
    		else if(hit(0,72,79,"any")==true) {teleport(1,20,14,gp.indoor);}
    		else if(hit(0,30,66,"any")==true) {teleport(1,12,13,gp.indoor);}
    		else if(hit(1,20,15,"any")==true) {teleport(0,73,79,gp.outside);}
    		else if(hit(1,12,9,"up")==true) { speak(gp.npc[1][0]);}
    		else if(hit(0,36,83,"any")==true) {teleport(2,9,41,gp.dungeon);}
    		else if(hit(2,9,41,"any")==true) {teleport(0,36,83,gp.outside);}
    		else if(hit(2,8,7,"any")==true) {teleport(3,26,41,gp.finalBoss);}
    		else if(hit(3,26,41,"any")==true) {teleport(2,8,7,gp.dungeon);}
        }
		
	}
	public boolean hit(int map,int col ,int row,String reqDirection) {
		
		boolean hit= false;
		
		if(map==gp.currentMap) {
			
			gp.player.solidArea.x +=gp.player.worldX;
			gp.player.solidArea.y +=gp.player.worldY;
	        eventRect[map][col][row].x +=col*gp.tilesize;
	        eventRect[map][col][row].y +=row*gp.tilesize;
	        
	        if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone==false) {
	        	if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
	        		hit = true;
	        		
	        		previousEventX=gp.player.worldX;
	        		previousEventY= gp.player.worldY;
	        	}
	        }
			gp.player.solidArea.x= gp.player.SolidAreaDefaultX;
			gp.player.solidArea.y= gp.player.SolidAreaDefaultY;
			eventRect[map][col][row].x=eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y=eventRect[map][col][row].eventRectDefaultY;
	
		}
		return hit;
	}
	
	public void damagePit(int gameState) {
		
		gp.gameState=gameState;
		eventMaster.startDialogue(eventMaster, 0);
		gp.player.life-=1;
//		eventRect[col][row].eventDone=true;
		canTouchEvent=false;
	}
	
	public void healingPool(int gameState) {
		
		if(gp.keyH.enter==true) {
			gp.gameState=gameState;
			gp.player.attackCanceled=true;
			gp.PlaySE(2);
			eventMaster.startDialogue(eventMaster, 1);
			gp.player.mana= gp.player.maxMana;
			gp.player.life= gp.player.maxLife;
			gp.aSetter.setMonster();
			gp.saveLoad.save();
		}
	}
	public void teleport(int map, int col, int row,int area) {
		
		gp.gameState= gp.transitionState;
		tempMap= map;
		tempcol=col;
		temprow= row;
		gp.nextArea=area;
		gp.currentMap= map;
		gp.player.worldX= gp.tilesize* col;
		gp.player.worldY= gp.tilesize* row;
		previousEventX= gp.player.worldX;
		previousEventY= gp.player.worldY;
		canTouchEvent= false;
		gp.PlaySE(13);
	}
	
	public void speak(Entity entity) {
		if(gp.keyH.enter==true) {
			gp.gameState=gp.dialogueState;
			gp.player.attackCanceled=true;
			entity.speak();
		}
	}
		
}
