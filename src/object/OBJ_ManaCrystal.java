package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {
	
	GamePanel gp;
	
	public static final String objName= "Mana Cryastal";

	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		this.gp= gp;
		
		name=objName;
		image= setup("/objects/manacrystal_full",gp.tilesize,gp.tilesize);
		image2= setup("/objects/manacrystal_blank",gp.tilesize,gp.tilesize);
	}

}
