package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_Slime extends Entity {
	
	GamePanel gp;

	public MON_Slime(GamePanel gp) {
		super(gp);
		
		this.gp= gp;
        
		type=type_monster;
	    name =" Green Slime ";
	    defaultSpeed=1;
	    boss=false;
	    speed =defaultSpeed;
	    maxLife =4;
	    life = maxLife;
	    attack =5;
	    defense=0;
	    exp= 2;
	    projectile= new OBJ_Rock(gp);
	    
	    solidArea.x = 3;
	    solidArea.y = 18;
	    solidArea.width= 42;
	    solidArea.height= 30;
	    SolidAreaDefaultX= solidArea.x;
	    SolidAreaDefaultY= solidArea.y;
	    
	    getImage();	    	}
	
	public void getImage() {
		
		up1=setup("/monster/greenslime_down_1",gp.tilesize,gp.tilesize);
		up2=setup("/monster/greenslime_down_2",gp.tilesize,gp.tilesize);
		down1=setup("/monster/greenslime_down_1",gp.tilesize,gp.tilesize);
		down2=setup("/monster/greenslime_down_2",gp.tilesize,gp.tilesize);
		left1=setup("/monster/greenslime_down_1",gp.tilesize,gp.tilesize);
		left2=setup("/monster/greenslime_down_2",gp.tilesize,gp.tilesize);
	    right1=setup("/monster/greenslime_down_1",gp.tilesize,gp.tilesize);
		right2=setup("/monster/greenslime_down_2",gp.tilesize,gp.tilesize);
	}
	
	public void setAction() {	
		if(onPath==true) {
			
			CheckStopChasingOrNot(gp.player,15,100);
			
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
			checkShootOrNot(200,30);
		}
		else {
			CheckStartChasingOrNot(gp.player,5,100);
			
			getRandomDistance(120);
		}
	}

	
	public void damageReaction() {
		actionLockCounter=0;
//		direction = gp.player.direction;
		onPath=true;
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
