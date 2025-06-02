package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {
	
	public static final String objName= "Lantern";

	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		
		name=objName;
		type= type_light;
		down1= setup("/objects/lantern",gp.tilesize,gp.tilesize);
		description="lights the \n surrounding";
		price=200;
		lightRadius=250;
	}

}
