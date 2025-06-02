package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Aex extends Entity {
	
	public static final String objName= "Woodcutter Axe";

	public OBJ_Aex(GamePanel gp) {
		super(gp);
		
		type= type_axe;
		name=objName;
		down1=setup("/objects/axe",gp.tilesize,gp.tilesize);
		attackValue= 2;
		attackArea.width=30;
		attackArea.height=30;
		description="[ Wooden Axe ] \n Bit rusty";
		price=75;
		knockBackPower=20;
		motion1_duration=20;
		motion2_duration=40;
	}

}
