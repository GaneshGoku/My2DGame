package Entity;

import main.GamePanel;
import object.OBJ_Aex;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class NPC_Mearchant extends Entity {

		
		public NPC_Mearchant(GamePanel gp) {
		super(gp);
		
		direction="down";
		speed=1;
		
		getImage();
		setDialogue();
		setItems();
	}
        public void getImage() {
        
        up1=setup("/npc/merchant_down_1",gp.tilesize,gp.tilesize);
        up2=setup("/npc/merchant_down_2",gp.tilesize,gp.tilesize);
        down1=setup("/npc/merchant_down_1",gp.tilesize,gp.tilesize);
        down2=setup("/npc/merchant_down_2",gp.tilesize,gp.tilesize);
        left1=setup("/npc/merchant_down_1",gp.tilesize,gp.tilesize);
        left2=setup("/npc/merchant_down_2",gp.tilesize,gp.tilesize);
        right1=setup("/npc/merchant_down_1",gp.tilesize,gp.tilesize);
        right2=setup("/npc/merchant_down_2",gp.tilesize,gp.tilesize);

    }

       public void setDialogue() {
	
	 dialogues[0][0]="yoo nigga \n welcome to the hood \n take watcha want!";
	 dialogues[1][0]="Cum again ,ahhhh!!!";
	 dialogues[2][0]="You broke nigga";
	 dialogues[3][0]="You cant carry more";
	 dialogues[4][0]="are u gonna fight monster's bare hand?";

}
       public void setItems() {
    	   inventory.add(new OBJ_Potion_Red(gp));
    	   inventory.add(new OBJ_Key(gp));
    	   inventory.add(new OBJ_Sword_Normal(gp));
    	   inventory.add(new OBJ_Aex(gp));
    	   inventory.add(new OBJ_Shield_Wood(gp));
    	   inventory.add(new OBJ_Shield_Blue(gp));
       }
       
       public void speak() {
    	   super.speak();
    	   gp.gameState= gp.tradeState;
    	   gp.ui.npc= this;
       }
}
			
	