package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {
	public static final String objName= "Normal Sword";

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		
		type= type_sword;		
		 name=objName;
		 down1= setup("/objects/sword_normal",gp.tilesize,gp.tilesize);
		 attackValue=1;
		 attackArea.width=40;
		 attackArea.height=30;
		 description="[" + name + "]\nAn old sword ";
		 price=120;
		 knockBackPower=5;
		 motion1_duration=5;
		 motion2_duration=20;
	}
     
}
