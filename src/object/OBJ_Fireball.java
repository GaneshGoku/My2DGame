package object;

import java.awt.Color;

import Entity.Entity;
import Entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{
	GamePanel gp;
	
	public static final String objName= "FireBall";

	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp= gp;
		
		name=objName;
		speed=5;
		maxLife=80;
		life= maxLife;
		attack=2;
		useCost=1;
		knockBackPower=0;
		alive= false;
		getImage();
		
	}
	
	public void getImage() {
		up1= setup("/projectile/fireball_up_1",gp.tilesize,gp.tilesize);
		up2= setup("/projectile/fireball_up_2",gp.tilesize,gp.tilesize);
		down1= setup("/projectile/fireball_down_1",gp.tilesize,gp.tilesize);
		down2= setup("/projectile/fireball_down_2",gp.tilesize,gp.tilesize);
		left1= setup("/projectile/fireball_left_1",gp.tilesize,gp.tilesize);
		left2= setup("/projectile/fireball_left_2",gp.tilesize,gp.tilesize);
		right1= setup("/projectile/fireball_right_1",gp.tilesize,gp.tilesize);
		right2= setup("/projectile/fireball_right_2",gp.tilesize,gp.tilesize);
	}
	public boolean haveResource(Entity user) {
		 
		boolean haveResource= false;
		if(user.mana>-useCost) {
			haveResource=true;
		}
		return haveResource;
	}
	public void subtractResource(Entity user) {
		user.mana-=useCost;
	}
	public Color getParticleColor() {
		Color color= new Color(240,50,0);
		return color;
	}
	public int getParticleSize() {
		int size=6; // 6 pixels
		return size;
	}
	public int getParticleSpeed() {
		int speed= 1;
		return speed;
	}
	public int  getParticleMaxLife() {
		int maxLife=20;
		return maxLife;
	}

}
