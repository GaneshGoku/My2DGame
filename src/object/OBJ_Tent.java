package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{
	
	GamePanel gp;
	
	public static final String objName= "Tent";

	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp=gp;
		
		type=type_consumable;
		name=objName;
		down1=setup("/objects/tent",gp.tilesize,gp.tilesize);
		description="Tent\n use to skip night";
		price=300;
		stackable=true;
		
	}
	public boolean use(Entity entity) {
		gp.gameState=gp.sleepState;
		gp.PlaySE(14);
		gp.player.getSleepingImage(down1);
		gp.player.life=gp.player.maxLife;
		gp.player.mana=gp.player.maxMana;
		return true;
	}

}
