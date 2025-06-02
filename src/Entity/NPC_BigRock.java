package Entity;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;
import object.OBJ_Door_Iron;
import tile_interactive.IT_MetalPlate;
import tile_interactive.InteractiveTile;

public class NPC_BigRock extends Entity{

	public static final String npcName= "Big Rock";
	public NPC_BigRock(GamePanel gp) {
		super(gp);
		
		name=npcName;
		solidArea = new Rectangle();
		solidArea.x = 2;
		solidArea.y = 6;
		SolidAreaDefaultX = solidArea.x;
		SolidAreaDefaultY = solidArea.y;
		solidArea.width = 44;
		solidArea.height = 40;
		
		direction="down";
		speed=4;
		
		getImage();
		setDialogue();
		dialogueSet=-1;
		
	}
    public void getImage() {
        
        up1=setup("/npc/bigrock",gp.tilesize,gp.tilesize);
        up2=setup("/npc/bigrock",gp.tilesize,gp.tilesize);
        down1=setup("/npc/bigrock",gp.tilesize,gp.tilesize);
        down2=setup("/npc/bigrock",gp.tilesize,gp.tilesize);
        left1=setup("/npc/bigrock",gp.tilesize,gp.tilesize);
        left2=setup("/npc/bigrock",gp.tilesize,gp.tilesize);
        right1=setup("/npc/bigrock",gp.tilesize,gp.tilesize);
        right2=setup("/npc/bigrock",gp.tilesize,gp.tilesize);

    }

     public void setDialogue() {
 	
	 dialogues[0][0]=" Big Rock ";
	 
     }
     public void setAction() {}
     public void update() {}
     public void speak() {
	
	facePlayer();
	startDialogue(this,dialogueSet);
//	onPath=true;
	
	dialogueSet++;
	if(dialogues[dialogueSet][0]==null) {
		dialogueSet--;
	}
  }
     public void move(String d) {
    	 this.direction=d;
    	 
    	 checkCollision();
    	 
    	 if(collisionOn==false) {
    		 switch(direction) {
    		 case "up":worldY-=speed;break;
    		 case "down":worldY+=speed;break;
    		 case "left":worldX-=speed;break;
    		 case "right":worldX+=speed;break;
    		 }
    	 }
    	 detectPlate();
     }
     
     public void detectPlate() {
    	 
    	 ArrayList<InteractiveTile> plateList= new ArrayList<>();
    	 ArrayList<Entity> rockList = new ArrayList<>();
    	 
    	 //plate list
    	 for(int i=0;i<gp.iTile[1].length;i++) {
    		 if(gp.iTile[gp.currentMap][i]!=null &&gp.iTile[gp.currentMap][i].name!=null&& gp.iTile[gp.currentMap][i].name.equals(IT_MetalPlate.itName)) {
    			 plateList.add(gp.iTile[gp.currentMap][i]);
    		 }
    	 }
    	 //rock list
    	 for(int i=0;i<gp.npc[1].length;i++) {
    		 if(gp.npc[gp.currentMap][i]!=null && gp.npc[gp.currentMap][i].name.equals(NPC_BigRock.npcName)) {
    			 rockList.add(gp.npc[gp.currentMap][i]);
    		 }
    	 }
    	 int counter=0;
    	 
    	 //scan plate list
    	 
    	 for(int i=0;i<plateList.size();i++) {
    		 int xDistance=Math.abs(worldX-plateList.get(i).worldX);
    		 int yDistance=Math.abs(worldY-plateList.get(i).worldY);
    		 int distance=Math.max(xDistance, yDistance);
    		 
    		 if(distance<8) {
    			 if(linkedEntity==null) {
    				 linkedEntity=plateList.get(i);
    				 gp.PlaySE(3);
    			 }
    		 }
    		 else {
    			 if(linkedEntity==plateList.get(i)) {
    				 linkedEntity=null;    			
    				 }
    		 }
    	 }
    	 //scan rock list
    	 
    	 for(int i=0;i<rockList.size();i++) {
    		 if(rockList.get(i).linkedEntity!=null) {
    			 counter++;
    		 }
    	 }
    	 //check all plates
    	 
    	 if(counter==rockList.size()) {
    		 for(int i=0;i<gp.obj[1].length;i++) {
    			 if(gp.obj[gp.currentMap][i]!=null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
    				 gp.obj[gp.currentMap][i]=null;
    				 gp.PlaySE(21);
    			 }
    		 }
    	 }
     }
  
}
