package main;

import Entity.NPC_BigRock;
import Entity.NPC_Mearchant;
import Entity.NPC_OldMan;
import Entity.NPC_Red_Hair;
import monster.MON_Bat;
import monster.MON_Orc;
import monster.MON_SkeletonLord;
import monster.MON_Slime;
import object.OBJ_Aex;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Tent;
import tile_interactive.IT_DistructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter( GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObject() {
		int mapNum=0;
		int i=0;
		gp.obj[mapNum][i]= new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*25;
		gp.obj[mapNum][i].worldY= gp.tilesize*19;
		i++;
		gp.obj[mapNum][i]= new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*21;
		gp.obj[mapNum][i].worldY= gp.tilesize*19;
		i++;
		gp.obj[mapNum][i]= new OBJ_Lantern(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*23;
		gp.obj[mapNum][i].worldY= gp.tilesize*19;
		i++;
		gp.obj[mapNum][i]= new OBJ_Tent(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*23;
		gp.obj[mapNum][i].worldY= gp.tilesize*20;
		i++;
		gp.obj[mapNum][i]= new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*26;
		gp.obj[mapNum][i].worldY= gp.tilesize*21;
		i++;
		gp.obj[mapNum][i]= new OBJ_Aex(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*33;
		gp.obj[mapNum][i].worldY= gp.tilesize*21;
		i++;
		gp.obj[mapNum][i]= new OBJ_Shield_Blue(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*36;
		gp.obj[mapNum][i].worldY= gp.tilesize*25;
		i++;
		gp.obj[mapNum][i]= new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*23;
		gp.obj[mapNum][i].worldY= gp.tilesize*27;
		i++;
		gp.obj[mapNum][i]= new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*14;
		gp.obj[mapNum][i].worldY= gp.tilesize*28;
//		i++;
//		gp.obj[mapNum][i]= new OBJ_Door(gp);
//		gp.obj[mapNum][i].worldX= gp.tilesize*12;
//		gp.obj[mapNum][i].worldY= gp.tilesize*12;
		i++;
		gp.obj[mapNum][i]= new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX= gp.tilesize*38;
		gp.obj[mapNum][i].worldY= gp.tilesize*8 ;
		i++;
		gp.obj[mapNum][i]= new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*24;
		gp.obj[mapNum][i].worldY= gp.tilesize*27;
		i++;
		gp.obj[mapNum][i]= new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX= gp.tilesize*24;
		gp.obj[mapNum][i].worldY= gp.tilesize*28;
		i++;
		
		mapNum=2;
		i=0;
		i++;
		gp.obj[mapNum][i]= new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.obj[mapNum][i].worldX= gp.tilesize*40;
		gp.obj[mapNum][i].worldY= gp.tilesize*41 ;
		i++;
//		gp.obj[mapNum][i]= new OBJ_Door_Iron(gp);
//		gp.obj[mapNum][i].worldX= gp.tilesize*18;
//		gp.obj[mapNum][i].worldY= gp.tilesize*23;
//		i++;
		
	}
	public void setNPC() {
		
		int mapNum=0;
		int i=0;
		gp.npc[mapNum][i]=new NPC_OldMan(gp);
		gp.npc[mapNum][i].worldX= gp.tilesize*21;
		gp.npc[mapNum][i].worldY= gp.tilesize*21;
		i++;
		gp.npc[mapNum][i]=new NPC_Red_Hair(gp);
		gp.npc[mapNum][i].worldX= gp.tilesize*27;
		gp.npc[mapNum][i].worldY= gp.tilesize*21;
		i++;
		
		mapNum=1;
		i=0;
		gp.npc[mapNum][i]=new NPC_Mearchant(gp);
		gp.npc[mapNum][i].worldX= gp.tilesize*12;
		gp.npc[mapNum][i].worldY= gp.tilesize*7;
		
		mapNum=2;
		i=0;
		gp.npc[mapNum][i]=new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX= gp.tilesize*20;
		gp.npc[mapNum][i].worldY= gp.tilesize*25;
		i++;
		gp.npc[mapNum][i]=new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX= gp.tilesize*11;
		gp.npc[mapNum][i].worldY= gp.tilesize*18;
		i++;
		gp.npc[mapNum][i]=new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX= gp.tilesize*23;
		gp.npc[mapNum][i].worldY= gp.tilesize*14;
		i++;
	}
	public void setMonster() {
		int i=0;
		int mapNum=0;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX= gp.tilesize*23;
		gp.monster[mapNum][i].worldY= gp.tilesize*36;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX= gp.tilesize*23;
		gp.monster[mapNum][i].worldY= gp.tilesize*37;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX= gp.tilesize*27;
		gp.monster[mapNum][i].worldY= gp.tilesize*39;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX= gp.tilesize*44;
		gp.monster[mapNum][i].worldY= gp.tilesize*30;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX= gp.tilesize*34;
		gp.monster[mapNum][i].worldY= gp.tilesize*40;
		i++;
		gp.monster[mapNum][i]= new MON_Orc(gp);
		gp.monster[mapNum][i].worldX= gp.tilesize*12;
		gp.monster[mapNum][i].worldY= gp.tilesize*33;
		i++;
		
		mapNum=2;
		gp.monster[mapNum][i]= new MON_Bat(gp);
		gp.monster[mapNum][i].worldX= gp.tilesize*39;
		gp.monster[mapNum][i].worldY= gp.tilesize*41;
		i++;
//		gp.monster[mapNum][i]= new MON_Bat(gp);
//		gp.monster[mapNum][i].worldX= gp.tilesize*12;
//		gp.monster[mapNum][i].worldY= gp.tilesize*33;
//		i++;
//		gp.monster[mapNum][i]= new MON_Bat(gp);
//		gp.monster[mapNum][i].worldX= gp.tilesize*12;
//		gp.monster[mapNum][i].worldY= gp.tilesize*33;
//		i++;
		mapNum=3;
		gp.monster[mapNum][i]= new MON_SkeletonLord(gp);
		gp.monster[mapNum][i].worldX= gp.tilesize*23;
		gp.monster[mapNum][i].worldY= gp.tilesize*16;
		i++;
	}
	
	public void setInteractiveTile() {
		int mapNum=0;
		int i=0;
		gp.iTile[mapNum][i]= new IT_DryTree(gp,27,12); i++;
		gp.iTile[mapNum][i]= new IT_DryTree(gp,28,12); i++;
		gp.iTile[mapNum][i]= new IT_DryTree(gp,29,12); i++;
		gp.iTile[mapNum][i]= new IT_DryTree(gp,30,12); i++;
		gp.iTile[mapNum][i]= new IT_DryTree(gp,31,12); i++;
		gp.iTile[mapNum][i]= new IT_DryTree(gp,32,12); i++;
		gp.iTile[mapNum][i]= new IT_DryTree(gp,33,12); i++;
		
		mapNum=2;
		i=0;
		gp.iTile[mapNum][i]= new IT_DistructibleWall(gp,18,30); i++;
		gp.iTile[mapNum][i]= new IT_DistructibleWall(gp,17,31); i++;
		gp.iTile[mapNum][i]= new IT_MetalPlate(gp,20,22); i++;
		gp.iTile[mapNum][i]= new IT_MetalPlate(gp,8,17); i++;
		gp.iTile[mapNum][i]= new IT_MetalPlate(gp,39,31); i++;
	}

}
