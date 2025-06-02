package Entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;


public class NPC_OldMan extends Entity{
	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		SolidAreaDefaultX = solidArea.x;
		SolidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
		direction="down";
		speed=1;
		
		getImage();
		setDialogue();
		dialogueSet=-1;
		
	}
    public void getImage() {
        
        up1=setup("/npc/oldman_up_1",gp.tilesize,gp.tilesize);
        up2=setup("/npc/oldman_up_2",gp.tilesize,gp.tilesize);
        down1=setup("/npc/oldman_down_1",gp.tilesize,gp.tilesize);
        down2=setup("/npc/oldman_down_2",gp.tilesize,gp.tilesize);
        left1=setup("/npc/oldman_left_1",gp.tilesize,gp.tilesize);
        left2=setup("/npc/oldman_left_2",gp.tilesize,gp.tilesize);
        right1=setup("/npc/oldman_right_1",gp.tilesize,gp.tilesize);
        right2=setup("/npc/oldman_right_2",gp.tilesize,gp.tilesize);

    }

     public void setDialogue() {
 	
	 dialogues[0][0]="Hello nigga";
	 dialogues[0][1]="so you come to this island \n to find the treasure";
	 dialogues[0][2]="i once also came for the treasure \n but i cant find it ,and i was traped ";
     dialogues[0][3]="Good luck jack assssss";
	 
	 dialogues[1][0]="are u tired?";
	 dialogues[1][1]="so by the water";
	 dialogues[1][2]="u well gain some energy there ";
	 dialogues[1][3]="but watch out for the monsters!!";
	 
	 dialogues[2][0]=" do come , if u need any help";
     }
     public void setAction() {
	
	if(onPath==true) {
//		int goalCol=12;
//		int goalRow=10;
		int goalCol=(gp.player.worldX + gp.player.solidArea.x)/gp.tilesize;
		int goalRow=(gp.player.worldY + gp.player.solidArea.y)/gp.tilesize;
		
		searchPath(goalCol, goalRow);
	}
	else {
		
		actionLockCounter++;
		if(actionLockCounter==120) {
			Random random= new Random();
			int i= random.nextInt(100)+1;
			
			if(i<25) {
				direction= "up";
			}
			if(i>25 && i<50) {
				direction="down";
			}
			if(i>50 && i<75) {
				direction= "left";
			}
			if(i>75 && i<100) {
				direction= "right";
		}
			actionLockCounter=0;
	}
	
	
	
   }
   }
   public void speak() {
	
	facePlayer();
	startDialogue(this,dialogueSet);
//	onPath=true;
	
	dialogueSet++;
	if(dialogues[dialogueSet][0]==null) {
		dialogueSet--;
	}
   }

    

}
