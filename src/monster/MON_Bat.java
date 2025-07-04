package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_Bat extends Entity{

	GamePanel gp;

	public MON_Bat(GamePanel gp) {
		super(gp);
		
		this.gp= gp;
        
		type=type_monster;
	    name =" Bat ";
	    boss=false;
	    defaultSpeed=3;
	    speed =defaultSpeed;
	    maxLife =4;
	    life = maxLife;
	    attack =5;
	    defense=0;
	    exp= 5;
	    
	    solidArea.x = 3;
	    solidArea.y = 15;
	    solidArea.width= 42;
	    solidArea.height= 21;
	    SolidAreaDefaultX= solidArea.x;
	    SolidAreaDefaultY= solidArea.y;
	    
	    getImage();	    	}
	
	public void getImage() {
		
		up1=setup("/monster/bat_down_1",gp.tilesize,gp.tilesize);
		up2=setup("/monster/bat_down_2",gp.tilesize,gp.tilesize);
		down1=setup("/monster/bat_down_1",gp.tilesize,gp.tilesize);
		down2=setup("/monster/bat_down_2",gp.tilesize,gp.tilesize);
		left1=setup("/monster/bat_down_1",gp.tilesize,gp.tilesize);
		left2=setup("/monster/bat_down_2",gp.tilesize,gp.tilesize);
	    right1=setup("/monster/bat_down_1",gp.tilesize,gp.tilesize);
		right2=setup("/monster/bat_down_2",gp.tilesize,gp.tilesize);
	}
	
	public void setAction() {	
		if(onPath==true) {
		}
		else {
			getRandomDistance(30);
		}
	}
	
	public void damageReaction() {
		actionLockCounter=0;
	}
	
	public void checkDrop() {
		
		int i= new Random().nextInt(100)+1;
		
		if(i<50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if(i>=50 && i<75) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i>=75 && i<100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
		
	}

}
