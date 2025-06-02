package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
	
	GamePanel gp;
	
	public static final String objName= "Red Potion";

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		
		this.gp= gp;
		
		type= type_consumable;
		name=objName;
		value =5;
		down1=setup("/objects/potion_red",gp.tilesize,gp.tilesize);
		description="[Red Potion] \n Heals your life by" + value+".";
		price=100;
		stackable=true;
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0]="You drink the" + name+"!\n";
	}
    public boolean use(Entity entity) {
    	startDialogue(this,0);
    	entity.life+= value;
    	if(gp.player.life> gp.player.maxLife) {
    		gp.player.life= gp.player.maxLife;
    	}
    	gp.PlaySE(2);
    	return true;
    }
}
