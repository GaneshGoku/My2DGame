package object;

import java.awt.Color;

import Entity.Projectile;
import main.GamePanel;

public class OBJ_Rock extends Projectile {

	GamePanel gp;

	public static final String objName= "Rock";
	public OBJ_Rock(GamePanel gp) {
		super(gp);
		this.gp= gp;
		
		name=objName;
		speed=8;
		maxLife=80;
		life= maxLife;
		attack=2;
		useCost=1;
		alive= false;
		getImage();
		
	}
	
	public void getImage() {
		up1= setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
		up2= setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
		down1= setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
		down2= setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
		left1= setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
		left2= setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
		right1= setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
		right2= setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
	}
	public Color getParticleColor() {
		Color color= new Color(45,50,0);
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
