package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class MON_SkeletonLord extends Entity {

	GamePanel gp;
	public static final String monName= "Skeleto Lord";

	public MON_SkeletonLord	(GamePanel gp) {
		super(gp);
        this.gp= gp;
        
		type=type_monster;
	    name =monName;
	    boss=true;
	    defaultSpeed=1;
	    speed =defaultSpeed;
	    maxLife =10;
	    life = maxLife;
	    attack =10;
	    defense=2;
	    exp= 50;
	    knockBackPower=5;
	    
	    int size= gp.tilesize*5;
	    solidArea.x = 4;
	    solidArea.y = 4;
	    solidArea.width= size-48*2;
	    solidArea.height= size-48;
	    SolidAreaDefaultX= solidArea.x;
	    SolidAreaDefaultY= solidArea.y;
	    attackArea.width=170;
	    attackArea.height=170;
	    motion1_duration=25;
	    motion2_duration=50;
	    
	    getImage();	 
	    getAttackImage();
	}
	
	public void getImage() {
		int i=3;
		
		if(inRage==false) {
			up1=setup("/monster/skeletonlord_up_1",gp.tilesize*i,gp.tilesize*i);
			up2=setup("/monster/skeletonlord_up_2",gp.tilesize*i,gp.tilesize*i);
			down1=setup("/monster/skeletonlord_down_1",gp.tilesize*i,gp.tilesize*i);
			down2=setup("/monster/skeletonlord_down_2",gp.tilesize*i,gp.tilesize*i);
			left1=setup("/monster/skeletonlord_left_1",gp.tilesize*i,gp.tilesize*i);
			left2=setup("/monster/skeletonlord_left_2",gp.tilesize*i,gp.tilesize*i);
		    right1=setup("/monster/skeletonlord_right_1",gp.tilesize*i,gp.tilesize*i);
			right2=setup("/monster/skeletonlord_right_2",gp.tilesize*i,gp.tilesize*i);
		}
		if(inRage==true) {
			up1=setup("/monster/skeletonlord_phase2_up_1",gp.tilesize*i,gp.tilesize*i);
			up2=setup("/monster/skeletonlord_phase2_up_2",gp.tilesize*i,gp.tilesize*i);
			down1=setup("/monster/skeletonlord_phase2_down_1",gp.tilesize*i,gp.tilesize*i);
			down2=setup("/monster/skeletonlord_phase2_down_2",gp.tilesize*i,gp.tilesize*i);
			left1=setup("/monster/skeletonlord_phase2_left_1",gp.tilesize*i,gp.tilesize*i);
			left2=setup("/monster/skeletonlord_phase2_left_2",gp.tilesize*i,gp.tilesize*i);
		    right1=setup("/monster/skeletonlord_phase2_right_1",gp.tilesize*i,gp.tilesize*i);
			right2=setup("/monster/skeletonlord_phase2_right_2",gp.tilesize*i,gp.tilesize*i);
		}
		
	}
	public void getAttackImage() {
		
		int i=3;
		if(inRage==false) {
			attackUp1= setup("/monster/skeletonlord_attack_up_1",gp.tilesize*i,gp.tilesize*i*2);
	    	attackUp2= setup("/monster/skeletonlord_attack_up_2",gp.tilesize*i,gp.tilesize*i*2);
	    	attackDown1= setup("/monster/skeletonlord_attack_down_1",gp.tilesize*i,gp.tilesize*i*2);
	    	attackDown2= setup("/monster/skeletonlord_attack_down_2",gp.tilesize*i,gp.tilesize*i*2);
	    	attackLeft1= setup("/monster/skeletonlord_attack_left_1",gp.tilesize*i*2,gp.tilesize*i);
	    	attackLeft2= setup("/monster/skeletonlord_attack_left_2",gp.tilesize*i*2,gp.tilesize*i);
	    	attackRight1= setup("/monster/skeletonlord_attack_right_1",gp.tilesize*i*2,gp.tilesize*i);
	    	attackRight2= setup("/monster/skeletonlord_attack_right_2",gp.tilesize*i*2,gp.tilesize*i);
		}
		if(inRage==true) {
			attackUp1= setup("/monster/skeletonlord_phase2_attack_up_1",gp.tilesize*i,gp.tilesize*i*2);
	    	attackUp2= setup("/monster/skeletonlord_phase2_attack_up_2",gp.tilesize*i,gp.tilesize*i*2);
	    	attackDown1= setup("/monster/skeletonlord_phase2_attack_down_1",gp.tilesize*i,gp.tilesize*i*2);
	    	attackDown2= setup("/monster/skeletonlord_phase2_attack_down_2",gp.tilesize*i,gp.tilesize*i*2);
	    	attackLeft1= setup("/monster/skeletonlord_phase2_attack_left_1",gp.tilesize*i*2,gp.tilesize*i);
	    	attackLeft2= setup("/monster/skeletonlord_phase2_attack_left_2",gp.tilesize*i*2,gp.tilesize*i);
	    	attackRight1= setup("/monster/skeletonlord_phase2_attack_right_1",gp.tilesize*i*2,gp.tilesize*i);
	    	attackRight2= setup("/monster/skeletonlord_phase2_attack_right_2",gp.tilesize*i*2,gp.tilesize*i);
		}
	
	}
	public void setAction() {	
		
		if(inRage==false && life<maxLife/2) {
			inRage=true;
			getImage();
			getAttackImage();
			speed++;
			
		}
		if(getTiledistance(gp.player)<10) {
			moveTowardsPlayer(60);
		}
		if(onPath==true) {}
		else {
			getRandomDistance(120);
		}
		if(attacking ==false) {
			checkAttackOrNot(60,gp.tilesize*6,gp.tilesize*5);
		}
	}

	
	public void damageReaction() {
		actionLockCounter=0;
//		direction = gp.player.direction;
//		onPath=true;
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
