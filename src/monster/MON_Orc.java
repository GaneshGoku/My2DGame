package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class MON_Orc extends Entity {
	
	GamePanel gp;

	public MON_Orc(GamePanel gp) {
		super(gp);
        this.gp= gp;
        
		type=type_monster;
	    name ="Orc";
	    boss=false;
	    defaultSpeed=1;
	    speed =defaultSpeed;
	    maxLife =10;
	    life = maxLife;
	    attack =8;
	    defense=2;
	    exp= 20;
	    knockBackPower=5;
	    
	    solidArea.x = 4;
	    solidArea.y = 18;
	    solidArea.width= 40;
	    solidArea.height= 44;
	    SolidAreaDefaultX= solidArea.x;
	    SolidAreaDefaultY= solidArea.y;
	    attackArea.width=48;
	    attackArea.height=48;
	    motion1_duration=40;
	    motion2_duration=85;
	    
	    getImage();	 
	    getAttackImage();
	}
	
	public void getImage() {
		
		up1=setup("/monster/orc_up_1",gp.tilesize,gp.tilesize);
		up2=setup("/monster/orc_up_2",gp.tilesize,gp.tilesize);
		down1=setup("/monster/orc_down_1",gp.tilesize,gp.tilesize);
		down2=setup("/monster/orc_down_2",gp.tilesize,gp.tilesize);
		left1=setup("/monster/orc_left_1",gp.tilesize,gp.tilesize);
		left2=setup("/monster/orc_left_2",gp.tilesize,gp.tilesize);
	    right1=setup("/monster/orc_right_1",gp.tilesize,gp.tilesize);
		right2=setup("/monster/orc_right_2",gp.tilesize,gp.tilesize);
	}
	public void getAttackImage() {
		attackUp1= setup("/monster/orc_attack_up_1",gp.tilesize,gp.tilesize*2);
    	attackUp2= setup("/monster/orc_attack_up_2",gp.tilesize,gp.tilesize*2);
    	attackDown1= setup("/monster/orc_attack_down_1",gp.tilesize,gp.tilesize*2);
    	attackDown2= setup("/monster/orc_attack_down_2",gp.tilesize,gp.tilesize*2);
    	attackLeft1= setup("/monster/orc_attack_left_1",gp.tilesize*2,gp.tilesize);
    	attackLeft2= setup("/monster/orc_attack_left_2",gp.tilesize*2,gp.tilesize);
    	attackRight1= setup("/monster/orc_attack_right_1",gp.tilesize*2,gp.tilesize);
    	attackRight2= setup("/monster/orc_attack_right_2",gp.tilesize*2,gp.tilesize);
	}
	public void setAction() {	
		if(onPath==true) {
			
			CheckStopChasingOrNot(gp.player,15,100);
			
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
		}
		else {
			CheckStartChasingOrNot(gp.player,5,100);
			
			getRandomDistance(120);
		}
		if(attacking ==false) {
			checkAttackOrNot(30,gp.tilesize*4,gp.tilesize);
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
