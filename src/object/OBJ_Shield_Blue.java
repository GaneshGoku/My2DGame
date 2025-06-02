package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends  Entity{

	public static final String objName= "Blue Shield";
	
	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp);
		
		type= type_shield;
		name= objName;
		down1= setup("/objects/shield_blue",gp.tilesize,gp.tilesize);
		defenseValue=2;
		description="[" + name + "]\n Just ablue shield ";
		price=250;
	}
	
}
