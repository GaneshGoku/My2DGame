package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity{

	public static final String objName= "Pickaxe Axe";

	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		
		type= type_pickaxe;
		name=objName;
		down1=setup("/objects/pickaxe",gp.tilesize,gp.tilesize);
		attackValue= 2;
		attackArea.width=30;
		attackArea.height=30;
		description="[PickAxe Axe] \n used to dig ahole";
		price=75;
		knockBackPower=20;
		motion1_duration=10;
		motion2_duration=30;
	}
}
