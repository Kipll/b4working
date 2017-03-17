package game;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Spritesheet{
	public BufferedImage img;
	//offset of the spritesheet from the top left corner of the image
	public int offsetW;
	public int offsetH;
	//the size of one frame
	public int spriteW;
	public int spriteH;
	//number of columns of sprites in spritesheet
	public int spriteCol;
	//number of rows of sprites in spritesheet
	public int spriteRow;
	public int[] colsInRow;
	
	public Spritesheet(BufferedImage img, int offsetW, int offsetH, int spriteW, int spriteH, int spriteCol, int spriteRow, int[] colsInRow){
		this.img = img;
		this.offsetW = offsetW;
		this.offsetH = offsetH;
		this.spriteW = spriteW;
		this.spriteH = spriteH;
		this.spriteCol = spriteCol;
		this.spriteRow = spriteRow;
		this.colsInRow = colsInRow;
	}
	
	public Spritesheet(BufferedImage img, int spriteCol, int spriteRow, int[] colsInRow){
		this.img = img;
		this.offsetW = 0;
		this.offsetH = 0;
		this.spriteW = img.getWidth()/spriteCol;
		this.spriteH = img.getHeight()/spriteRow;
		this.spriteCol = spriteCol;
		this.spriteRow = spriteRow;
		this.colsInRow = colsInRow;
	}
}
