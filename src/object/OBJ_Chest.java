package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {
	
	GamePanel gp;
	public static final String objName= "Chest";

	
	public OBJ_Chest (GamePanel gp) {
		super(gp);
		this.gp=gp;
		
		type= type_obstacle;
		name =objName;
		image=setup("/objects/chest",gp.tilesize,gp.tilesize);
		image2=setup("/objects/chest_opened",gp.tilesize,gp.tilesize);
		down1=image;
		collision=true;
		
		solidArea.x=4;
		solidArea.y=16;
		solidArea.width=40;
		solidArea.height=32;
		SolidAreaDefaultX=solidArea.x;
		SolidAreaDefaultY= solidArea.y;
}
	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
	}
	
	public void setDialogue() {
		dialogues[0][0]="you opened a chest and find a" + loot.name+"!";
		dialogues[1][0]="\n..... u can't carry more";
		dialogues[2][0]="\n you obtained the "+ loot.name;
		dialogues[3][0]="Its empty";
	}
	public void interact() {
		if(opened==false) {
			gp.PlaySE(3);
		    startDialogue(this,0);
				
			if(gp.player.canObtainItem(loot)==false) {
				startDialogue(this,1);
			}
			else {
				startDialogue(this,2);
				down1=image2;
				opened=true;
			}
		}
		else {
			startDialogue(this,3);
		}
	}
}
