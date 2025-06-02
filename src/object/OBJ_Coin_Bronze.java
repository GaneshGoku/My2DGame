package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {
	
	public static final String objName= "Bronxe coin";

	GamePanel gp;
	public OBJ_Coin_Bronze(GamePanel gp) {
		super(gp);
		this.gp=gp;
		
		type= type_pickupOnly;
		name=objName;
		value=1;
		down1=setup("/objects/coin_bronze",gp.tilesize, gp.tilesize);
	
	}

	public boolean use(Entity entity) {
    	gp.PlaySE(1);
    	gp.ui.addMessage("Coin" + value);
    	gp.player.coin+=value;
        return true;
    }
}
