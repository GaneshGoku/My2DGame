package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener  {
	
	GamePanel gp;
	public boolean up, down, left, right,enter,shotKey,spacePressed; 
	//debug
	boolean showDebugText= false;
	public boolean godModeOn=false;
	
	public KeyHandler(GamePanel gp) {
		this.gp=gp;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
	      
		int code= e.getKeyCode();
		
		//title State
        if(gp.gameState==gp.titleState) {
        	titleState(code);
		}
		//Play state
        else if(gp.gameState==gp.playState) {
        	playState(code);
		}
		//Pause State
		else if(gp.gameState==gp.pauseState) {
			pauseState(code);
		}
		//Dialogue State
		else if(gp.gameState==gp.dialogueState) {
			dialogueState(code);
		}
        //Character state
		else if(gp.gameState==gp.characterState) {
			characterState(code);
		}
        //Option  state
      	else if(gp.gameState==gp.optionsState) {
      		optionsState(code);
      	}
      	else if(gp.gameState==gp.gameOverState) {
      		gameOverState(code);
      	}
      	else if(gp.gameState==gp.tradeState) {
      		tradeState(code);
      	}
      	else if(gp.gameState==gp.mapState) {
      		mapState(code);
      	}
	}
	
	public void titleState(int code) {
		//move up on title
		if(code==KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum<0) {
				gp.ui.commandNum=2;
			}
		}
		//move down in title
		if(code==KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum>2) {
				gp.ui.commandNum=0;
			}
		}
		//chose
		if(code==KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum==0) {
				gp.gameState=gp.playState;
			}
			if(gp.ui.commandNum==1) {
				gp.saveLoad.load();
				gp.gameState=gp.playState;
			}
			if(gp.ui.commandNum==2) {
				System.exit(0);
			}
		}
	}
	public void playState(int code) {
		
		if(code==KeyEvent.VK_W) {
			up=true;
		}
		if(code==KeyEvent.VK_A) {
			left=true;
		}
		if(code==KeyEvent.VK_S) {
			down=true;
		}
		if(code==KeyEvent.VK_D) {
			right=true;
		}
		if(code==KeyEvent.VK_P) {
			gp.gameState=gp.pauseState;
		}
		if(code==KeyEvent.VK_C) {
			gp.gameState=gp.characterState;
		}
		if(code==KeyEvent.VK_ENTER) {
			enter=true;
		}
		if(code==KeyEvent.VK_F) {
			shotKey=true;
		}
		//options
		if(code==KeyEvent.VK_ESCAPE) {
			gp.gameState=gp.optionsState;
		}
		//show map
		if(code==KeyEvent.VK_M) {
			gp.gameState=gp.mapState;
		}
		//show mini Map
		if(code==KeyEvent.VK_X) {
			if(gp.map.miniMapOn==false) {
				gp.map.miniMapOn=true;
			}
			else {
				gp.map.miniMapOn=false;
			}
		}
		if(code==KeyEvent.VK_SPACE) {
			spacePressed=true;
		}
		//debug state
		if(code==KeyEvent.VK_T) {
			if(showDebugText==false) {
				showDebugText=true;
			}
			else if(showDebugText==true) {
				showDebugText=false;
			}
		}
		//godMode
		if(code==KeyEvent.VK_G) {
			if(godModeOn==false) {
				godModeOn=true;
			}
			else if(godModeOn==true) {
				godModeOn=false;
			}
		}
		
	}
	public void pauseState(int code) {
		if(code==KeyEvent.VK_P) {
			gp.gameState=gp.playState;
		}
	}
	public void dialogueState(int code) {
		if(code==KeyEvent.VK_ENTER) {
			enter=true;
		}
	}
	public void characterState(int code) {
		if(code==KeyEvent.VK_C) {
			gp.gameState= gp.playState;
		}
		
		if(code==KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
		
		playerInventory(code);
	}
	public void optionsState(int code) {
		
		if(code==KeyEvent.VK_ESCAPE) {
			gp.gameState=gp.playState;
		}
		if(code==KeyEvent.VK_ENTER) {
			enter=true;
		}
		
		int maxCommandNum=0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum=5; break;
		case 3: maxCommandNum=1; break;
		}
		if(code==KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.PlaySE(9);
			if(gp.ui.commandNum<0) {
				gp.ui.commandNum= maxCommandNum;
			}
		}
		if(code==KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.PlaySE(9);
			if(gp.ui.commandNum>maxCommandNum) {
				gp.ui.commandNum= 0;
			}
		}
		if(code==KeyEvent.VK_A) {
			if(gp.ui.subState==0){
				if(gp.ui.commandNum==1 && gp.music.volumeScale>0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.PlaySE(9);
				}
				if(gp.ui.commandNum==2 && gp.sound.volumeScale>0) {
					gp.sound.volumeScale--;
					gp.PlaySE(9);
				}
			}
		}
		if(code==KeyEvent.VK_D) {
			if(gp.ui.subState==0){
				if(gp.ui.commandNum==1 && gp.music.volumeScale<5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.PlaySE(9);
				}
				if(gp.ui.commandNum==2 && gp.sound.volumeScale<5) {
					gp.sound.volumeScale++;
					gp.PlaySE(9);
				}
			}
		}
	}
	
	public void gameOverState(int code) {
		if(code==KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum<0) {
				gp.ui.commandNum=1;
			}
			gp.PlaySE(9);
		}
		if(code==KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum>1) {
				gp.ui.commandNum=0;
			}
		}
		gp.PlaySE(9);
		
		if(code==KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum==0) {
				gp.gameState=gp.playState;
				gp.resetGame(false);
				gp.playMusic(0);
			}
			else if(gp.ui.commandNum==1) {
				gp.gameState= gp.titleState;
				gp.playMusic(0);
				gp.resetGame(true);
			}
		}
	}
	
	public void mapState(int code) {
		
		if(code==KeyEvent.VK_M) {
			gp.gameState=gp.playState;
		}
	}
	
	public void tradeState(int code) {
		if(code==KeyEvent.VK_ENTER) {
			enter=true;
		}
		if(gp.ui.subState==0) {
			if(code==KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum<0) {
					gp.ui.commandNum=2;
				}
				gp.PlaySE(9);
			}
			if(code==KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum>2) {
					gp.ui.commandNum=0;
				}
				gp.PlaySE(9);
			}
		}
		if(gp.ui.subState==1) {
			npcInventory(code);
			if(code==KeyEvent.VK_ESCAPE) {
				gp.ui.subState=0;
			}
		}
		if(gp.ui.subState==2) {
			playerInventory(code);
			if(code==KeyEvent.VK_ESCAPE) {
				gp.ui.subState=0;
			}
		}
	}
	
	public void playerInventory(int code) {
		
		if(code==KeyEvent.VK_W) {
			if(gp.ui.playerSlotRow != 0) {
				gp.ui.playerSlotRow--;
				gp.PlaySE(9);
			}
		}
		if(code==KeyEvent.VK_A) {
			if(gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
				gp.PlaySE(9);
			}
		}
		if(code==KeyEvent.VK_S) {
			if(gp.ui.playerSlotRow != 3) {
				gp.ui.playerSlotRow++;
				gp.PlaySE(9);	
			}
		}
		if(code==KeyEvent.VK_D) {
			if(gp.ui.playerSlotCol != 4) {
				gp.ui.playerSlotCol++;
				gp.PlaySE(9);	
			}
		}
	}
	
public void npcInventory(int code) {
		
		if(code==KeyEvent.VK_W) {
			if(gp.ui.npcSlotRow != 0) {
				gp.ui.npcSlotRow--;
				gp.PlaySE(9);
			}
		}
		if(code==KeyEvent.VK_A) {
			if(gp.ui.npcSlotCol != 0) {
				gp.ui.npcSlotCol--;
				gp.PlaySE(9);
			}
		}
		if(code==KeyEvent.VK_S) {
			if(gp.ui.npcSlotRow != 3) {
				gp.ui.npcSlotRow++;
				gp.PlaySE(9);	
			}
		}
		if(code==KeyEvent.VK_D) {
			if(gp.ui.npcSlotCol != 4) {
				gp.ui.npcSlotCol++;
				gp.PlaySE(9);	
			}
		}
	}


	public void keyReleased(KeyEvent e) {
		
        int code= e.getKeyCode();
		
		if(code==KeyEvent.VK_W) {
			up=false;
		}
		if(code==KeyEvent.VK_A) {
			left=false;
		}
		if(code==KeyEvent.VK_S) {
			down=false;
		}
		if(code==KeyEvent.VK_D) {
			right=false;
		}
		if(code==KeyEvent.VK_F) {
			shotKey=false;
		}
		if(code==KeyEvent.VK_ENTER) {
			enter=false;
		}  
		if(code==KeyEvent.VK_SPACE) {
			spacePressed=false;
		}
	}

}
