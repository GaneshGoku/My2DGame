package Entity;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_White_Hair extends Entity {

	public NPC_White_Hair(GamePanel gp) {
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
		
		try {
			faceImage = ImageIO.read(getClass().getResourceAsStream("/npc/white_hair_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public void getImage() {
        
        up1=setup("/npc/white_hair_RE",gp.tilesize,gp.tilesize);
        up2=setup("/npc/white_hair_RE",gp.tilesize,gp.tilesize);
        down1=setup("/npc/white_hair_RE",gp.tilesize,gp.tilesize);
        down2=setup("/npc/white_hair_RE",gp.tilesize,gp.tilesize);
        left1=setup("/npc/white_hair_RE",gp.tilesize,gp.tilesize);
        left2=setup("/npc/white_hair_RE",gp.tilesize,gp.tilesize);
        right1=setup("/npc/white_hair_RE",gp.tilesize,gp.tilesize);
        right2=setup("/npc/white_hair_RE",gp.tilesize,gp.tilesize);
    }

     public void setDialogue() {
 	
	 dialogues[0][0]="Another One haa?";
	 dialogues[0][1]="you are 50th one this month";
	 dialogues[0][2]=" ";
     dialogues[0][3]="Be Carefull there are monster out there \n check u have all the weapons needed";
     dialogues[0][4]="i'll give u a tip";
     
     dialogues[1][0]="The treasure is some where around the\n dark forest";
     dialogues[1][1]="but make shore u become strong before\n going there";
     dialogues[1][2]="man this job suckssss";
     dialogues[1][3]="fak that old man!!!!!";
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
