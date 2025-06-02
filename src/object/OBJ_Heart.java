package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
	
	GamePanel gp;
	
	public static final String objName= "Heart";
	
	public OBJ_Heart (GamePanel gp) {
		super(gp);
		this.gp= gp;
		
		type= type_pickupOnly;
		name= objName;
		value=2;
		down1=setup("/objects/heart_full",gp.tilesize,gp.tilesize);
		image=setup("/objects/heart_full",gp.tilesize,gp.tilesize);
		image2=setup("/objects/heart_half",gp.tilesize,gp.tilesize);
		image3=setup("/objects/heart_blank",gp.tilesize,gp.tilesize);
}
	public boolean  use(Entity entity) {
		
		gp.PlaySE(2);
		gp.ui.addMessage("Life" + value);
		entity.life+= value;
		return true;
	}
}
