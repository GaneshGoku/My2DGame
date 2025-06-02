package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    private int keyCount; // This replaces hasKey for better encapsulation
    public boolean attackCanceled= false;
    public boolean lightupdated=false;

    public Player(GamePanel gp, KeyHandler keyH) {
    	super(gp);
        this.gp = gp;
        this.keyH = keyH;

        // Set player's position on the screen
        screenX = gp.screenwidth / 2 - (gp.tilesize / 2);
        screenY = gp.screenheight / 2 - (gp.tilesize / 2);

        // Initialize solid area (collision box)
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        SolidAreaDefaultX = solidArea.x;
        SolidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
//        
//        attackArea.width=36;
//        attackArea.height=36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        getGuardImage();
        setItems();

        // Initialize key count to 0
        keyCount = 0;
    }

    public void setDefaultValues() {
        // Starting position in the world
        worldX = gp.tilesize * 23;
        worldY = gp.tilesize * 21;
//        worldX = gp.tilesize * 12;
//        worldY = gp.tilesize * 12;
        defaultSpeed=4;
        speed= defaultSpeed;
        gp.currentMap=0;
        speed = 4;  // Movement speed
        direction = "down";  // Initial direction
        
        //Player Status
        level=1;
        maxLife=6;
        life=maxLife;
        maxMana = 4;
        mana=maxMana;
        strength=5;
        dexterity=1;
        exp=0;
        nextLevelExp=5;
        coin=500;
        currentWeapon= new OBJ_Sword_Normal(gp);
        currentShield= new OBJ_Shield_Wood(gp);
        projectile= new OBJ_Fireball(gp);
        currentLight=null;
        attack= getAttack();
        defense= getDefense();
        
        getPlayerImage();
        getPlayerAttackImage();
        getGuardImage();
        setItems();
        setDialogue();
    }
    
    public void setDefaultPositions() {
    	
    	gp.currentMap=0;
    	worldX = gp.tilesize * 23;
        worldY = gp.tilesize * 21;
        direction = "down"; 
    }
    
    public void setDialogue() {
    	
    	dialogues[0][0]="You are Level "+ level +"now\n";
    }
    
    public void restoreStatus() {
    	life= maxLife;
    	mana= maxMana;
    	speed= defaultSpeed;
    	invincible = false;
    	transparnt=false;
    	attacking=false;
    	guarding=false;
    	knockBack=false;
    	lightupdated=false;
    }
    
    public void setItems() {
    	
    	inventory.clear();
    	inventory.add(currentWeapon);
    	inventory.add(currentShield);
    	inventory.add(new OBJ_Key(gp));
    	inventory.add(new OBJ_Key(gp));
    	
    }
    
    public int getAttack() {
    	attackArea= currentWeapon.attackArea;
    	motion1_duration=currentWeapon.motion1_duration;
		motion2_duration=currentWeapon.motion2_duration;
    	return attack=strength * currentWeapon.attackValue;
    }
    public int getDefense() {
    	return defense = dexterity * currentShield.defenseValue;
    }
    
    public int getCurrentWeaponSlot() {
    	
    	int currentWeaponSlot=0;
    	for(int i=0;i<inventory.size();i++) {
    		if(inventory.get(i)==currentWeapon) {
    			currentWeaponSlot=i;
    		}
    	}
    	return currentWeaponSlot;
    }
public int getCurrentShieldSlot() {
    	
    	int currentShieldSlot=0;
    	for(int i=0;i<inventory.size();i++) {
    		if(inventory.get(i)==currentShield) {
    			currentShieldSlot=i;
    		}
    	}
    	return currentShieldSlot;
    }

    public void getPlayerImage() {
        
        up1=setup("/player/boy_up_1",gp.tilesize,gp.tilesize);
        up2=setup("/player/boy_up_2",gp.tilesize,gp.tilesize);
        down1=setup("/player/boy_down_1",gp.tilesize,gp.tilesize);
        down2=setup("/player/boy_down_2",gp.tilesize,gp.tilesize);
        left1=setup("/player/boy_left_1",gp.tilesize,gp.tilesize);
        left2=setup("/player/boy_left_2",gp.tilesize,gp.tilesize);
        right1=setup("/player/boy_right_1",gp.tilesize,gp.tilesize);
        right2=setup("/player/boy_right_2",gp.tilesize,gp.tilesize);

    }
    
    public void getSleepingImage(BufferedImage image) {
    	up1=image;
        up2=image;
        down1=image;
        down2=image;
        left1=image;
        left2=image;
        right1=image;
        right2=image;
    }
    
    public void getPlayerAttackImage() {
    	
    	if(currentWeapon.type== type_sword) {
    	   	attackUp1= setup("/player/boy_attack_up_1",gp.tilesize,gp.tilesize*2);
        	attackUp2= setup("/player/boy_attack_up_2",gp.tilesize,gp.tilesize*2);
        	attackDown1= setup("/player/boy_attack_down_1",gp.tilesize,gp.tilesize*2);
        	attackDown2= setup("/player/boy_attack_down_2",gp.tilesize,gp.tilesize*2);
        	attackLeft1= setup("/player/boy_attack_left_1",gp.tilesize*2,gp.tilesize);
        	attackLeft2= setup("/player/boy_attack_left_2",gp.tilesize*2,gp.tilesize);
        	attackRight1= setup("/player/boy_attack_right_1",gp.tilesize*2,gp.tilesize);
        	attackRight2= setup("/player/boy_attack_right_2",gp.tilesize*2,gp.tilesize);
    	}
    	if(currentWeapon.type== type_axe) {
    	   	attackUp1= setup("/player/boy_axe_up_1",gp.tilesize,gp.tilesize*2);
        	attackUp2= setup("/player/boy_axe_up_2",gp.tilesize,gp.tilesize*2);
        	attackDown1= setup("/player/boy_axe_down_1",gp.tilesize,gp.tilesize*2);
        	attackDown2= setup("/player/boy_axe_down_2",gp.tilesize,gp.tilesize*2);
        	attackLeft1= setup("/player/boy_axe_left_1",gp.tilesize*2,gp.tilesize);
        	attackLeft2= setup("/player/boy_axe_left_2",gp.tilesize*2,gp.tilesize);
        	attackRight1= setup("/player/boy_axe_right_1",gp.tilesize*2,gp.tilesize);
        	attackRight2= setup("/player/boy_axe_right_2",gp.tilesize*2,gp.tilesize);
    	}
    	if(currentWeapon.type== type_pickaxe) {
    	   	attackUp1= setup("/player/boy_pick_up_1",gp.tilesize,gp.tilesize*2);
        	attackUp2= setup("/player/boy_pick_up_2",gp.tilesize,gp.tilesize*2);
        	attackDown1= setup("/player/boy_pick_down_1",gp.tilesize,gp.tilesize*2);
        	attackDown2= setup("/player/boy_pick_down_2",gp.tilesize,gp.tilesize*2);
        	attackLeft1= setup("/player/boy_pick_left_1",gp.tilesize*2,gp.tilesize);
        	attackLeft2= setup("/player/boy_pick_left_2",gp.tilesize*2,gp.tilesize);
        	attackRight1= setup("/player/boy_pick_right_1",gp.tilesize*2,gp.tilesize);
        	attackRight2= setup("/player/boy_pick_right_2",gp.tilesize*2,gp.tilesize);
    	}
 
    }
    
    public void getGuardImage() {
    	guardUp=setup("/player/boy_guard_up",gp.tilesize,gp.tilesize);
    	guardDown=setup("/player/boy_guard_down",gp.tilesize,gp.tilesize);
    	guardLeft=setup("/player/boy_guard_left",gp.tilesize,gp.tilesize);
    	guardRight=setup("/player/boy_guard_right",gp.tilesize,gp.tilesize);
    }

    public void update() {
    	
    	if(knockBack==true) {
    		
            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, true);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkEntity(this, gp.monster);
            gp.cChecker.checkEntity(this, gp.iTile);
			
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
    	else if(keyH.spacePressed==true) {
    		guarding=true;
    		guardCounter++;
    	}
        // Check if the player is moving in any direction
    	else if (keyH.up || keyH.down || keyH.left || keyH.right || keyH.enter) {

            // Update direction based on key input
            if (keyH.up) {
                direction = "up";
            } else if (keyH.down) {
                direction = "down";
            } else if (keyH.left) {
                direction = "left";
            } else if (keyH.right) {
                direction = "right";
            }

            // Check for collision with tiles or objects
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check for object interaction (key, door, etc.)
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //Check NPC Collision
            int npcIndex= gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            //Check Monster Collision
            int monsterIndex= gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            //Check interactive colision
            int iTileIndex= gp.cChecker.checkEntity(this, gp.iTile);
            
            //Check Event
            gp.eHandler.checkEvent();
            
           	
            // Move the player if no collision is detected
            if (collisionOn==false && keyH.enter==false) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            
            if(keyH.enter==true && attackCanceled==false) {
            	attacking=true;
            	sprinteCounter=0;
            }
            attackCanceled=false;
            gp.keyH.enter=false;
            guarding =false;
            guardCounter=0;

            // Handle player walking animation
            sprinteCounter++;
            if (sprinteCounter > 12) {
                sprinteNum = sprinteNum == 1 ? 2 : 1;
                sprinteCounter = 0;
            }
        }
        else {
        	sprinteCounter=1;
        	guarding =false;
        	guardCounter=0;
        }
    	
    	if(gp.keyH.shotKey==true && projectile.alive==false && projectile.haveResource(this)==true) {
    		
    		projectile.set(worldX,worldY,direction,true,this);
    		
    		projectile.subtractResource(this);
    		
//    		gp.projectileList.add(projectile);
    		
    		for(int i=0;i<gp.projectile[1].length;i++) {
    			if(gp.projectile[gp.currentMap][i]==null) {
    				gp.projectile[gp.currentMap][i]=projectile;
    				break;
    			}
    		}
    		
    		gp.PlaySE(10);
    	}
    	
    	
        if(invincible==true) {
        	invincibleCounter++;
        	if(invincibleCounter > 60) {
        		invincible=false;
        		transparnt=false;
        		invincibleCounter=0;
        	}
        }
        if(life>maxLife) {
        	life=maxLife;
        }
        if(mana>maxMana) {
        	mana=maxMana;
        }
        if(keyH.godModeOn==false) {
        	if(life<= 0) {
            	gp.gameState= gp.gameOverState;
            	gp.stopMusic();
            	gp.ui.commandNum=-1;
            	gp.PlaySE(12);
            }
        }
    }


    public void pickUpObject(int i) {
        if (i != 999) {  // 999 is an index that represents no object
           
        	//PiclUp only items
        	if(gp.obj[gp.currentMap][i].type== type_pickupOnly) {
        		gp.obj[gp.currentMap][i].use(this);
        		gp.obj[gp.currentMap][i]=null;
        		
        	}
        	
        	else if(gp.obj[gp.currentMap][i].type==type_obstacle) {
        		if(keyH.enter==true) {
        			attackCanceled=true;
        			gp.obj[gp.currentMap][i].interact();
        		}
        	}
        	else {
        		String text;
                
                if(canObtainItem(gp.obj[gp.currentMap][i])==true) {
             	   gp.PlaySE(1);
             	   text="Got a "+ gp.obj[gp.currentMap][i].name+"!";
             	   
                }
                else {
             	   text="You can't carry this shit";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i]=null;
        	}
        	
        }
    }
    public void interactNPC(int i) {
    	
    		if (i != 999) {  // 999 is an index that represents no object
    			if(gp.keyH.enter==true){
    				attackCanceled= true;
            	    gp.npc[gp.currentMap][i].speak();
    			}
    			gp.npc[gp.currentMap][i].move(direction);
            }	
    	}

    // New method to collect a key
    public void collectKey() {
        keyCount++;
    }

    // New method to use a key (returns true if successful, false if no keys are available)
    public boolean useKey() {
        if (keyCount > 0) {
            keyCount--;
            return true;
        }
        return false;
    }

    // New getter for the key count
    public int getKeyCount() {
        return keyCount;
    }
    
    public void contactMonster(int i) {
    	
    	if(i!=999) {
    		if(invincible==false && gp.monster[gp.currentMap][i].dying==false) {
    			gp.PlaySE(6);
    			
    			int damage= gp.monster[gp.currentMap][i].attack - defense;
    			if(damage<1) {
    				damage=1;
    			}
    			life-= damage;
    			invincible =true;
    			transparnt=true;
    		}
    	}
    }
    
    public void damageMonster(int i,Entity attacker, int attack,int knockBackPower) {
    	
    	if(i!=999) {
    		
    		if(gp.monster[gp.currentMap][i].invincible==false) {
    			gp.PlaySE(5);
    			
    			if(knockBackPower>0) {
    				setKnockBack(gp.monster[gp.currentMap][i],attacker,knockBackPower);
    			}
    			
    			if(gp.monster[gp.currentMap][i].offBalance==true) {
    				attack*=5;
    			}
    			
    			setKnockBack(gp.monster[gp.currentMap][i],attacker,knockBackPower);
    			int damage= attack - gp.monster[gp.currentMap][i].defense;
    			if(damage<0) {
    				damage=0;
    			}
    			gp.monster[gp.currentMap][i].life-= damage;
    			gp.ui.addMessage(damage + "damage");
    			gp.monster[gp.currentMap][i].invincible=true;
    			gp.monster[gp.currentMap][i].damageReaction();
    			
    			if(gp.monster[gp.currentMap][i].life<=0) {
    				gp.monster[gp.currentMap][i].dying= true;
    				gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name +"!");
    				gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
    				exp+= gp.monster[gp.currentMap][i].exp;
    				checkLevelUp();
    			}
    		}
    	}
    }
 
    public void damageInteractiveTile(int i) {
    	
    	if(i!=999 && gp.iTile[gp.currentMap][i].destructible==true && gp.iTile[gp.currentMap][i].isCorrectItem(this)==true && gp.iTile[gp.currentMap][i].invincible==false) {
    		gp.iTile[gp.currentMap][i].playSE();
    		gp.iTile[gp.currentMap][i].life--;
    		gp.iTile[gp.currentMap][i].invincible=true;
    		
    		generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);
    		if(gp.iTile[gp.currentMap][i].life==0) {
    			gp.iTile[gp.currentMap][i]=gp.iTile[gp.currentMap][i].getDestroyedForm();	
    		}
    	}
    }
    
    public void damageProjectile(int i) {
    	
//    	if(i!=999) {
//    		Entity projectile= gp.projectile[gp.currentMap][i];
//    		projectile.alive=false;
//    		generateParticle(projectile,projectile);
//    	}
    }
    
    public void checkLevelUp() {
    	
    	if(exp>=nextLevelExp) {
    		
    		level++;
    		nextLevelExp=nextLevelExp*2;
    		maxLife+=2;
    		strength++;
    		dexterity++;
    		attack= getAttack();
    		defense= getDefense();
    		
    		gp.PlaySE(8);
    		gp.gameState= gp.dialogueState;
    		
    		setDialogue();
    		
    		startDialogue(this,0);
    	}
    }
    
    public void selectItem() {
    	
    	int itemIndex= gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
    	
    	if(itemIndex< inventory.size()) {
    		Entity selectedItem= inventory.get(itemIndex);
    		
    		if(selectedItem.type== type_sword || selectedItem.type== type_axe|| selectedItem.type==type_pickaxe) {
    			currentWeapon= selectedItem;
    			attack= getAttack();
    			getPlayerAttackImage();
    		}
    		if(selectedItem.type== type_shield) {
    			currentShield= selectedItem;
    			defense=getDefense();
    		}
    		if(selectedItem.type==type_light) {
    			
    			if(currentLight==selectedItem) {
    				currentLight=null;
    			}
    			else {
    				currentLight=selectedItem;
    			}
    			lightupdated=true;
    		}
    		if(selectedItem.type== type_consumable) {
                
    			if(selectedItem.use(this)==true){
    				if(selectedItem.amount>1) {
    					selectedItem.amount--;
    				}
    				else {
    					inventory.remove(itemIndex);
    				}
    			}
    		}
    	}
    }
    
    public int searchItemInInventory(String itemName) {
    	
    	int index=999;
    	
    	for(int i=0;i<inventory.size();i++) {
    		if(inventory.get(i).name.equals(itemName)) {
    			index=i;
    			break;
    		}
    	}
    	return index;
    }
    
    public boolean canObtainItem(Entity item) {
    	
    	boolean canObtain=false;
    	
    	Entity newItem= gp.eGenerator.getObject(item.name);
    	
    	if(newItem.stackable==true) {
    		int index=searchItemInInventory(newItem.name);
    		
    		if(index!=999) {
    			inventory.get(index).amount++;
    			canObtain=true;
    		}
    		else {
    			if(inventory.size()!=inventorySize) {
    				inventory.add(newItem);
    				canObtain=true;
    			}
    		}
    	}
    	else {
    		if(inventory.size()!=inventorySize) {
				inventory.add(newItem);
				canObtain=true;
			}
    	}
    	return canObtain;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX=screenX;
        int tempScreenY=screenY;

        // Choose the right image for the current direction and sprite number
        switch (direction) {
            case "up":
            	if(attacking==false) {
            		image = sprinteNum == 1 ? up1 : up2;
            	}
            	if(attacking==true) {
            		tempScreenY=screenY-gp.tilesize;
            		image = sprinteNum == 1 ? attackUp1 : attackUp2;
            	}
            	if(guarding==true) {
            		image=guardUp;
            	}
                break;
            case "down":
            	if(attacking==false) {
            		image = sprinteNum == 1 ? down1 : down2;
            	}
            	if(attacking==true) {
            		image = sprinteNum == 1 ? attackDown1 : attackDown2;
            	}
            	if(guarding==true) {
            		image=guardDown;
            	}
                break;
            case "left":
            	if(attacking==false) {
            		image = sprinteNum == 1 ? left1 : left2;
            	}
            	if(attacking==true) {
            		tempScreenX=screenX-gp.tilesize;
            		image = sprinteNum == 1 ? attackLeft1 : attackLeft2;
            	}
            	if(guarding==true) {
            		image=guardLeft;
            	}
                break;
            case "right":
            	if(attacking==false) {
            		image = sprinteNum == 1 ? right1 : right2;
            	}
            	if(attacking==true) {
            		image = sprinteNum == 1 ? attackRight1 : attackRight2;
            	}
            	if(guarding==true) {
            		image=guardRight;
            	}
                break;
        }
        
        if(transparnt==true) {
        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        // Draw the player on the screen (centered at screenX, screenY)
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
