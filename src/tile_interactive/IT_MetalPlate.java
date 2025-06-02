package tile_interactive;

import main.GamePanel;

public class IT_MetalPlate extends InteractiveTile {
	
	GamePanel gp;
	public static final String itName="Metal plate";

	public IT_MetalPlate(GamePanel gp, int col, int row) {
		super(gp, col, row);
		
        this.gp = gp;
	    
	    this.worldX= gp.tilesize*col;
	    this.worldY= gp.tilesize* row;
	    
	    name=itName;
	    down1=setup("/tiles_interactive/metalplate",gp.tilesize, gp.tilesize);
	
	    solidArea.x=0;
	    solidArea.y=0;
	    solidArea.width=0;
	    solidArea.height=0;
	    SolidAreaDefaultX= solidArea.x;
	    SolidAreaDefaultY= solidArea.y;
	}
}
