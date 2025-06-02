package main;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	
	GamePanel gp;
	
	public Config(GamePanel gp) {
		this.gp= gp;
	}
	
	public void saveConfig() {
		
		try {
			BufferedWriter bf= new BufferedWriter(new FileWriter("config.txt"));
			
			//Full screen
			if(gp.fullScreenOn==true) {
				bf.write("ON");
			}
			if(gp.fullScreenOn==false) {
				bf.write("Off");
			}
			bf.newLine();
			
			//Music
			bf.write(String.valueOf(gp.music.volumeScale));
			bf.newLine();
			//SE
			bf.write(String.valueOf(gp.sound.volumeScale));
		
			bf.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		
		try {
			BufferedReader br= new BufferedReader(new FileReader("config.txt"));
			
			String s= br.readLine();
			//Full screen
			if(s.equals("ON")) {
				gp.fullScreenOn=true;
			}
			if(s.equals("Off")) {
				gp.fullScreenOn=false;
			}
			//Music 
			s= br.readLine();
			gp.music.volumeScale= Integer.parseInt(s);
			//SE
			s= br.readLine();
			gp.sound.volumeScale= Integer.parseInt(s);
			
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
