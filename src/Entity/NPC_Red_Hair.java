package Entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Red_Hair extends Entity{
	
	public NPC_Red_Hair(GamePanel gp) {
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
        
        up1=setup("/npc/npc_Red_Hair1",gp.tilesize,gp.tilesize);
        up2=setup("/npc/npc_Red_Hair1",gp.tilesize,gp.tilesize);
        down1=setup("/npc/npc_Red_Hair1",gp.tilesize,gp.tilesize);
        down2=setup("/npc/npc_Red_Hair1",gp.tilesize,gp.tilesize);
        left1=setup("/npc/npc_Red_Hair1",gp.tilesize,gp.tilesize);
        left2=setup("/npc/npc_Red_Hair1",gp.tilesize,gp.tilesize);
        right1=setup("/npc/npc_Red_Hair1",gp.tilesize,gp.tilesize);
        right2=setup("/npc/npc_Red_Hair1",gp.tilesize,gp.tilesize);
    }

     public void setDialogue() {
 	
	 dialogues[0][0]="Hello Hero";
	 dialogues[0][1]="You look new to this Island ";
	 dialogues[0][2]="Are u also finding the treasure like me ??";
     dialogues[0][3]="Good luck then";
     }
     public void speak() {
	
//	facePlayer();
	startDialogue(this,dialogueSet);
//	onPath=true;
	
	dialogueSet++;
	if(dialogues[dialogueSet][0]==null) {
		dialogueSet--;
	}
   }
}
