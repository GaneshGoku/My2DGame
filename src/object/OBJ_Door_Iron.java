package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Door_Iron extends Entity{

GamePanel gp;
	
	public static final String objName= "Door Iron";
	public OBJ_Door_Iron (GamePanel gp) {
		super(gp);
		this.gp=gp;
		
		type= type_obstacle;
		name =objName;
		down1=setup("/objects/door_iron",gp.tilesize,gp.tilesize);
		collision=true;
		
		solidArea.x=0;
		solidArea.y=16;
		solidArea.width=48;
		solidArea.height=32;
		SolidAreaDefaultX=solidArea.x;
		SolidAreaDefaultY=solidArea.y;
		
		setDialogue();
}
	public void setDialogue() {
		dialogues[0][0]="shit cant move";
	}
	public void interact() {
		
		startDialogue(this,0);
	}
}
