package tile_interactive;

import main.GamePanel;

public class IT_Trunk extends InteractiveTile {

	public IT_Trunk(GamePanel gp, int col, int row) {
		super(gp, col, row);
        this.gp = gp;
	    
	    this.worldX= gp.tilesize*col;
	    this.worldY= gp.tilesize* row;
	    
	    down1=setup("/tiles_interactive/trunk",gp.tilesize, gp.tilesize);
	
	    solidArea.x=0;
	    solidArea.y=0;
	    solidArea.width=0;
	    solidArea.height=0;
	    SolidAreaDefaultX= solidArea.x;
	    SolidAreaDefaultY= solidArea.y;
	}
	

}
