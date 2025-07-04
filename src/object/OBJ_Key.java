package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
	
	GamePanel gp;
	
	public static final String objName= "Key";
	
	public OBJ_Key (GamePanel gp) {
		super(gp);
		this.gp=gp;
		
		type= type_consumable;
		name =objName;
		down1=setup("/objects/key",gp.tilesize,gp.tilesize);
		description="[" + name + "]\nAn used to open door's ";
		price=40;
		stackable=true;
		setDialogue();
}
	public void setDialogue() {
		dialogues[0][0]="you use the"+ name+"and opened the door";
		dialogues[1][0]="what u doing nigga?";
	}
	public boolean use(Entity entity) {
		
		int objIndex= getDetected(entity,gp.obj,"Door");
		
		if(objIndex!=999) {
			startDialogue(this,0);
			gp.PlaySE(3);
			gp.obj[gp.currentMap][objIndex]=null;
			return true;
			
		}
		else {
			startDialogue(this,1);
			return false;
		}
	}
}
