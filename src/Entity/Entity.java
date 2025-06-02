package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	GamePanel gp;
	public int worldX,worldY;
	public int speed;

	public BufferedImage up1,up2, down1 ,down2, left1, left2, right1, right2;
	public BufferedImage attackUp1 ,attackUp2 ,attackDown1 ,attackDown2 ,attackLeft1 ,attackLeft2 
	,attackRight1 ,attackRight2,guardUp, guardDown,guardLeft,guardRight;
	public String direction="down";
	
	public int sprinteCounter=0;
	public int sprinteNum=1;
	public int shotAvailableCounter=0;
	
	public Rectangle solidArea= new Rectangle(0,0,48,48);
	public Rectangle attackArea= new Rectangle(0,0,0,0);
	public Entity attacker;
	public Entity linkedEntity;
	
	public int SolidAreaDefaultX, SolidAreaDefaultY;
	public boolean collisionOn= false;
	public int actionLockCounter=0;
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	public boolean onPath= false;
	public boolean knockBack=false;
	public String knockBackDirection;
	public boolean stackable=false;
	public int amount=1;
	public boolean guarding=false;
	public boolean transparnt=false;
	public boolean offBalance=false;
	public Entity loot;
	public boolean opened= false;
	public boolean inRage=false;
	public boolean boss;
	
	int dyingCounter=0;
	public int hpBarCounter=0;
	int knockBackCounter=0;
	public int guardCounter=0;
	int offBalanceCounter=0;
	
    public ArrayList<Entity> inventory= new ArrayList<>();
    public final int inventorySize= 20;
	
	public int invincibleCounter=0;
	public String dialogues[][]=new String[20][20];
	public int dialogueSet= 0;
	public int dialogueIndex=0;
	public BufferedImage image, image2,image3;
	public String name;
	public boolean collision =false;
	
	//Character Status
	public int defaultSpeed;
	public int maxLife;
	public int life;
	public int maxMana;
	public int mana;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int motion1_duration;
	public int motion2_duration;
	public Entity currentWeapon;
	public Entity currentShield;
	public Entity currentLight;
	public Projectile projectile;
	
	//Type
	public int type;
	public final int type_player=0;
	public final int type_npc=1;
	public final int type_monster=2;
	public final int type_sword=3;
	public final int type_axe=4;
	public final int type_shield=5;
	public final int type_consumable=6;
	public final int type_pickupOnly=7;
	public final int type_obstacle=8;
	public final int type_light=9;
	public final int type_pickaxe=10;
	
	//item attribute
	public int attackValue;
	public int defenseValue;
	public int value;
	public String description="";
	public int useCost;
	public int price;
	public int knockBackPower=0;
	public int lightRadius;
	
	public Entity(GamePanel gp) {
		this.gp= gp;
	}
	
	public int getScreenX() {
		int screenX= worldX- gp.player.worldX + gp.player.screenX;
		return screenX;
	}
	public int getScreenY() {
		int screenY= worldY - gp.player.worldY + gp.player.screenY;
		return screenY;
	}
	public int getLeftX() {
		return worldX+solidArea.x;
	}
	public int getRightX() {
		return worldX+solidArea.x+ solidArea.width;
	}
	public int getTopY() {
		return worldY+solidArea.y;
	}
	public int getBottomY() {
		return worldY+solidArea.y + solidArea.height;
	}
	public int getCol() {
		return (worldX+solidArea.x)/gp.tilesize;
	}
	public int getRow() {
		return (worldY+solidArea.y)/gp.tilesize;
	}
	
	public int getCenterX() {
		int centerX=worldX+left1.getWidth()/2;
		return centerX;
	}
	public int getCenterY() {
		int centerY= worldY+up1.getHeight()/2;
		return centerY;
	}
	public int getXdistance(Entity target) {
		int xDistance= Math.abs(getCenterX()-target.getCenterX());
		return xDistance;
	}
	public int getYdistance(Entity target) {
		int yDistance= Math.abs(getCenterY()- target.getCenterY());
		return yDistance;
	}
	public int getTiledistance(Entity target) {
		int tileDistance=(getXdistance(target)+ getYdistance(target))/gp.tilesize;
		return tileDistance;
	}
	public int getGoalCol(Entity target) {
		int goalCol=(target.worldX +target.solidArea.x)/gp.tilesize;
		return goalCol;
	}
	public int getGoalRow(Entity target) {
		int goalRow=(target.worldY + target.solidArea.y)/gp.tilesize;
		return goalRow; 
	}
	public void resetCounter() {
		
		dyingCounter=0;
		hpBarCounter=0;
		knockBackCounter=0;
		guardCounter=0;
		offBalanceCounter=0;
	}
	public void setLoot(Entity loot) {}
	public void setAction() {}
	public void move(String direction) {} 
	public void damageReaction() {}
	public void speak() {}
	
	public void facePlayer() {
		switch(gp.player.direction) {
		case "up":
			direction="down";
			break;
		case "down":
			direction="up";
			break;
		case "left":
			direction="right";
			break;
		case "right":
			direction="left";
			break;	
		}
	}
	public void startDialogue(Entity entity, int setNum) {
		gp.gameState= gp.dialogueState;
		gp.ui.npc= entity;
		dialogueSet= setNum;
	}
	
	public void interact() {
		
	}
	
	public boolean use(Entity entity) {return false;}
	
	public void checkDrop() {}
    public void dropItem(Entity droppedItem) {
    	for(int i=0;i<gp.obj[1].length;i++) {
    		if(gp.obj[gp.currentMap][i]==null) {
    			gp.obj[gp.currentMap][i]=droppedItem;
    			gp.obj[gp.currentMap][i].worldX= worldX;
    			gp.obj[gp.currentMap][i].worldY= worldY;
    			break;
    		}
    	}
    }	
    
    public Color getParticleColor() {
		Color color= null;
		return color;
	}
	public int getParticleSize() {
		int size=0; // 6 pixels
		return size;
	}
	public int getParticleSpeed() {
		int speed= 0;
		return speed;
	}
	public int  getParticleMaxLife() {
		int maxLife=0;
		return maxLife;
	}
	public void generateParticle (Entity generator, Entity target) {
	    
		Color color = generator.getParticleColor();
		int size= generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife= generator.getParticleMaxLife();
		
		Particle p1= new Particle(gp,target,color,size,speed,maxLife,-2,-1);
		Particle p2= new Particle(gp,target,color,size,speed,maxLife,2,-1);
		Particle p3= new Particle(gp,target,color,size,speed,maxLife,-2,1);
		Particle p4= new Particle(gp,target,color,size,speed,maxLife,2,1);
		gp.particleList.add(p1);
		gp.particleList.add(p2);
		gp.particleList.add(p3);
		gp.particleList.add(p4);
	}
	
	public void checkCollision() {
		
		collisionOn= false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer =gp.cChecker.checkPlayer(this);
		
		if(this.type==type_monster && contactPlayer==true) {
			
			damagePlayer(attack);
		}
	}
	
	public void update() {
		
		if(knockBack==true) {
			checkCollision();
			
			if(collisionOn==true) {
				knockBackCounter=0;
				knockBack=false;
				speed= defaultSpeed;
			}
			else if(collisionOn==false) {
				switch(knockBackDirection) {
				case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
				}
			}
			knockBackCounter++;
			if(knockBackCounter==10) {
				knockBackCounter=0;
				knockBack=false;
				speed= defaultSpeed;
			}
			
		}
		else if(attacking ==true) {
			attacking();
		}
		else {
			setAction();
			checkCollision();
			
			if (!collisionOn) {
	            switch (direction) {
	                case "up": worldY -= speed; break;
	                case "down": worldY += speed; break;
	                case "left": worldX -= speed; break;
	                case "right": worldX += speed; break;
	            }
	        }
			// Handle player walking animation
	        sprinteCounter++;
	        if (sprinteCounter > 24) {
	            if(sprinteCounter==1) {
	            	sprinteCounter=2;
	            }
	            else {
	            	sprinteCounter=1;
	            }
	            sprinteCounter=0;
	        }
		}
        if(invincible==true) {
        	invincibleCounter++;
        	if(invincibleCounter > 40) {
        		invincible=false;
        		invincibleCounter=0;
        	}
        }
        if(shotAvailableCounter<30) {
        	shotAvailableCounter++;
        }
        if(offBalance==true) {
        	offBalanceCounter++;
        	if(offBalanceCounter>60) {
        		offBalance=false;
        		offBalanceCounter=0;
        	}
        }
        }
	public void checkAttackOrNot(int rate, int straight,int horizontal) {
		
		boolean targetInRange=false;
		int xDis= getXdistance(gp.player);
		int yDis= getYdistance(gp.player);
		
		switch(direction) {
		case "up":
			if(gp.player.getCenterY()<getCenterY() && yDis<straight && xDis<horizontal) {
			   targetInRange=true;	
			}
			break;
		case "down":
			if(gp.player.getCenterY()>getCenterY() && yDis<straight && xDis<horizontal) {
				targetInRange=true;
			}
			break;	
		case "left":
			if(gp.player.getCenterX()<getCenterX() && yDis<straight && xDis<horizontal) {
				targetInRange=true;
			}
			break;	
		case "right":
			if(gp.player.getCenterX()>getCenterX() && yDis<straight && xDis<horizontal) {
				targetInRange=true;
			}
			break;	
		}
		if(targetInRange==true) {
			int i= new Random().nextInt(rate);
			if(i==0) {
				attacking =true;
				sprinteNum=1;
				sprinteCounter=0;
				shotAvailableCounter=0;
			}
		}
	}
	
	public void checkShootOrNot(int rate,int shotInterval) {
		int i= new Random().nextInt(rate)+1;
		if(i==0 && projectile.alive==false && shotAvailableCounter==shotInterval) {
			projectile.set(worldX, worldY, direction, true, this);
			
			for(int ii=0;ii<gp.projectile[1].length;ii++) {
				if(gp.projectile[gp.currentMap][ii]==null) {
					gp.projectile[gp.currentMap][ii]=projectile;
					break;
				}
			}
			shotAvailableCounter=0;
		}
	}
public void CheckStartChasingOrNot(Entity target, int distance,int rate) {
		
		if(getTiledistance(target)<distance ) {
			int i=new Random().nextInt(rate);
			if(i==0) {
				onPath=true;
			}
		}
	}
	public void CheckStopChasingOrNot(Entity target, int distance,int rate) {
		
		if(getTiledistance(target)>distance ) {
			int i=new Random().nextInt(rate);
			if(i==0) {
				onPath=false;
			}
		}
	}
	public void getRandomDistance(int interwal) {
		actionLockCounter++;
		if(actionLockCounter==interwal) {
			Random random= new Random();
			int i= random.nextInt(100)+1;
			
			if(i<25) {direction= "up";}
			if(i>25 && i<50) {direction="down";}
			if(i>50 && i<75) {direction= "left";}
			if(i>75 && i<100) {direction= "right";}
			actionLockCounter=0;
	}
	}
	
	public void moveTowardsPlayer(int interwal) {
		actionLockCounter++;
		
		if(actionLockCounter>interwal) {
			if(getXdistance(gp.player)>getYdistance(gp.player)) {
				if(gp.player.getCenterX()<getCenterX()) {
					direction="left";
				}
				else {
					direction="right";
				}
			}
			else if(getXdistance(gp.player)<getYdistance(gp.player)){
				if(gp.player.getCenterY()<getCenterY()) {
					direction="up";
				}
				else {
					direction="down";
				}
			}
			actionLockCounter=0;
		}
		
	}
	public String getOppositeDirection(String direction) {
		
		String oppositDirection="";
		
		switch(direction) {
		case"up":oppositDirection="down";break;
		case"down":oppositDirection="up";break;
		case"left":oppositDirection="right";break;
		case"right":oppositDirection="left";break;
		}
		return oppositDirection;
	}
    
    public void attacking() {
    	sprinteCounter++;
    	
    	if(sprinteCounter<=motion1_duration) {
    		sprinteNum=1;
    	}
    	if(sprinteCounter>motion1_duration && sprinteCounter<=motion2_duration) {
    		sprinteNum=2;
    		//save current world position
    		int currentWorldX=worldX;
    		int currentWorldY= worldY;
    		int solidAreaWidth= solidArea.width;
    		int solidAreaHeight= solidArea.height;
    		
    		switch(direction) {
    		case"up":worldY -= attackArea.height; break;
    		case"down":worldY += attackArea.height; break;
    		case"left":worldX -= attackArea.width; break;
    		case"right":worldX += attackArea.width; break;
    		}
    		solidArea.width=attackArea.width;
    		solidArea.height=attackArea.height;
    		

    		if(type==type_monster) {
    			if(gp.cChecker.checkPlayer(this)==true) {
    				damagePlayer(attack);
    			}
    		}
    		else {
    			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        		gp.player.damageMonster(monsterIndex,this,attack,currentWeapon.knockBackPower);
        		
        		int iTileIndex= gp.cChecker.checkEntity(this, gp.iTile);
        		gp.player.damageInteractiveTile(iTileIndex);
        		
        		int projectileIndex= gp.cChecker.checkEntity(this, gp.projectile);
        		gp.player.damageProjectile(projectileIndex);
    		}
    		
    		
    		
    		worldX=currentWorldX;
    		worldY= currentWorldY;
    		solidArea.width=solidAreaWidth;
    		solidArea.height= solidAreaHeight;
    		
    	}
    	if(sprinteCounter>motion2_duration) {
    		sprinteNum=1;
    		sprinteCounter=0;
    		attacking=false;
    	}
    }
	public void damagePlayer(int attack) {
		
		if(gp.player.invincible==false) {
			int damage= attack - gp.player.defense;

			String canGuardDirection=getOppositeDirection(direction); 
			
			if(gp.player.guarding==true && gp.player.direction.equals(canGuardDirection)) {
				if(gp.player.guardCounter<10) {
					damage=0;
					gp.PlaySE(16);
					setKnockBack(this,gp.player,knockBackPower);
					offBalance=true;
					sprinteCounter-=60;
				}
				else {
					damage/=3;
					gp.PlaySE(15);	
				}
			}
			else {
				gp.PlaySE(6);
					if(damage<0) {
					damage=0;
				}
			}
			if(damage!=0) {
				gp.player.transparnt=true;
				setKnockBack(gp.player,this, knockBackPower);
			}
			
			gp.player.life-=damage;
			gp.player.invincible=true;
		}
	}
	   
    public void setKnockBack(Entity target,Entity attacker,int knockBackPower) {
    	this.attacker=attacker;
    	target.speed+=knockBackPower;
    	target.knockBackDirection=attacker.direction;
    	target.knockBack=true;
    }
    public boolean inCamera() {
    	boolean inCamera=false;
    	if(worldX + gp.tilesize*5> gp.player.worldX - gp.player.screenX &&  
    			   worldX - gp.tilesize< gp.player.worldX + gp.player.screenX &&
    			   worldY + gp.tilesize*5> gp.player.worldY - gp.player.screenY && 
    			   worldY - gp.tilesize< gp.player.worldY + gp.player.screenY) {
    		inCamera=true;
    	}
    	return inCamera;
    }
    
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;		
		if(inCamera()==true) {
			

	        // Choose the right image for the current direction and sprite number
			int tempScreenX=getScreenX();
	        int tempScreenY=getScreenY();

	        // Choose the right image for the current direction and sprite number
	        switch (direction) {
	            case "up":
	            	if(attacking==false) {
	            		if(sprinteNum==1) { image=up1;}
	            		if(sprinteNum==2) { image=up2;}
	            	}
	            	if(attacking==true) {
	            		tempScreenY=getScreenY()-up1.getHeight();
	            		if(sprinteNum==1) { image=attackUp1;}
	            		if(sprinteNum==2) { image=attackUp2;}
	            	}
	                break;
	            case "down":
	            	if(attacking==false) {
	            		if(sprinteNum==1) { image=down1;}
	            		if(sprinteNum==2) { image=down2;}
	            	}
	            	if(attacking==true) {
	            		if(sprinteNum==1) { image=attackDown1;}
	            		if(sprinteNum==2) { image=attackDown2;}
	            	}
	                break;
	            case "left":
	            	if(attacking==false) {
	            		if(sprinteNum==1) { image=left1;}
	            		if(sprinteNum==2) { image=left2;}
	            	}
	            	if(attacking==true) {
	            		tempScreenX=getScreenX()-left1.getWidth();
	            		if(sprinteNum==1) { image=attackLeft1;}
	            		if(sprinteNum==2) { image=attackLeft2;}
	            	}
	                break;
	            case "right":
	            	if(attacking==false) {
	            		if(sprinteNum==1) { image=right1;}
	            		if(sprinteNum==2) { image=right2;}
	            	}
	            	if(attacking==true) {
	            		if(sprinteNum==1) { image=attackRight1;}
	            		if(sprinteNum==2) { image=attackRight2;}
	            	}
	                break;
	        }
	        //Monster HP bar
	        
	        if(invincible==true) {
	        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
	        	hpBarOn= true;
	        	hpBarCounter=0;
	        }
	        if(dying == true) {
	        	dyingAnimation(g2);
	        }
		
		    g2.drawImage(image,tempScreenX,tempScreenY,null);
		    
		    if(invincible==true) {
	        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	        }
	}
	
	}
	
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter++;
		
		int i=5;
		if(dyingCounter<=i) {
			changeAlpha(g2, 0f);}
		if(dyingCounter>i && dyingCounter<=i*2) {changeAlpha(g2, 1f);}
		if(dyingCounter>i*2 && dyingCounter<=i*3) {changeAlpha(g2, 0f);}
		if(dyingCounter>i*3 && dyingCounter<=i*4) {changeAlpha(g2, 1f);}
		if(dyingCounter>i*4 && dyingCounter<=i*5) {changeAlpha(g2, 0f);}
		if(dyingCounter>i*5 && dyingCounter<=i*6) {changeAlpha(g2, 1f);}
		if(dyingCounter>i*6 && dyingCounter<=i*7) {changeAlpha(g2, 0f);}
		if(dyingCounter>i*7 && dyingCounter<=i*8) {changeAlpha(g2, 1f);}
		if(dyingCounter> i*8) {
			alive = false;
		}
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
    public BufferedImage setup(String imagePath ,int width, int height) {
    	
    	UtilityTool uTool= new UtilityTool();
    	BufferedImage image=null;
    	
        try {
        	image= ImageIO.read(getClass().getResourceAsStream( imagePath + ".png"));
        	image= uTool.scaleImage(image, width, height);
        }
        catch(IOException e){
        	e.printStackTrace();
        	
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow) {
    	
    	int startCol=(worldX + solidArea.x )/gp.tilesize;
    	int startRow= (worldY + solidArea.y)/gp.tilesize;
    	
    	gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
    	
    	if(gp.pFinder.search()==true) {
    		
    		int nextX=gp.pFinder.pathList.get(0).col * gp.tilesize;
    		int nextY=gp.pFinder.pathList.get(0).row * gp.tilesize;
    		
    		int enLeftX=worldX + solidArea.x;
    		int enRightX= worldX + solidArea.x + solidArea.width;
    		int enTopY = worldY + solidArea.y;
    		int enBottomY= worldY + solidArea.y+ solidArea.height;
    		
    		if(enTopY>nextY && enLeftX>=nextX && enRightX< nextX + gp.tilesize+1) {
    			direction="up";
    		}
    		else if(enTopY< nextY && enLeftX>= nextX && enRightX<nextX + gp.tilesize+1) {
    			direction="down";
    		}
    		else if(enTopY>=nextY && enBottomY < nextY+ gp.tilesize ) {
    			if(enLeftX> nextX) {
    				direction="left";
    			}
    			if(enLeftX< nextX) {
    				direction="right";
    			}
    		}
    		else if(enTopY>nextY && enLeftX> nextX) {
    			direction="up";
    			checkCollision();
    			if(collision==true) {
    				System.out.println("Collision detected");
    				direction="left";
    			}
    		}
    		else if(enTopY> nextY && enLeftX< nextX) {
    			direction="up";
    			checkCollision();
    			if(collision==true) {
    				direction="right";
    			}
    		}
    		else if(enTopY< nextY && enLeftX> nextX) {
    			direction="down";
    			checkCollision();
    			if(collision==true) {
    				direction="left";
    			}
    		}
    		else if(enTopY< nextY && enLeftX< nextX) {
    			direction="down";
    			checkCollision();
    			if(collision==true) {
    				direction="right";
    			}
    		}
    		
//    		int nextCol= gp.pFinder.pathList.get(0).col;
//    		int nextRow= gp.pFinder.pathList.get(0).row;
//    		if(nextCol==goalCol && nextRow== goalRow) {
//    			onPath=false;
//    		}
    	}
    }
    public int getDetected(Entity user, Entity target[][],String targetName ) {
    	
    	int index=999;
    	
    	int nextWorldX=user.getLeftX();
    	int nextWorldY= user.getTopY();
    	
    	switch(user.direction) {
    	case "up": nextWorldX= user.getTopY()-gp.player.speed;break;
    	case "down": nextWorldY= user.getBottomY()+gp.player.speed;break;
    	case "left": nextWorldX= user.getLeftX()-gp.player.speed;break;
    	case "right": nextWorldX= user.getRightX()+gp.player.speed;break;
    	}
    	int col= nextWorldX/gp.tilesize;
    	int row= nextWorldY/gp.tilesize;
    	
    	for(int i=0;i<target[1].length;i++) {
    		if(target[gp.currentMap][i]!=null) {
    			if(target[gp.currentMap][i].getCol()==col && target[gp.currentMap][i].getRow()==row && target[gp.currentMap][i].name.equals(targetName)) {
    				index=i;
    				break;
    			}
    		}
    	}
    	
    	return index;
    	
    }
}
