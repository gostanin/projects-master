import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.*;
import javax.swing.border.*;


public class Cell extends JButton {
	private boolean is_it_bomb = false;
	private int adjacent_bomb = 0;
	private boolean openCell = false;
	private ImageIcon imgCell = new ImageIcon(getClass().getResource("cell.png"));
	private ImageIcon imgFlag = new ImageIcon(getClass().getResource("cellFLAG.png"));
    private boolean flag;
   
    public Cell() {
		this.flag = false;
		super.setIcon(this.imgCell);
		super.setEnabled(true);
		super.setMargin(new Insets(0, 0, 0, 0));
		super.setFocusPainted(false);
		super.setBorder(new LineBorder(new Color(90,90,90)));
		super.setForeground(Color.BLACK);
		super.setBackground(Color.GRAY);
    }
   
    public void setBomb() {
    	this.is_it_bomb = true;
    }
   
    public boolean check() {
    	return this.is_it_bomb;
    }
   
    public void addNumber() {
	   	this.adjacent_bomb++;
    }
   
    public int getNumber() {
    	switch(this.adjacent_bomb) {
    	case 1:
    		super.setForeground(Color.WHITE);
    		break;
    	case 2:
    		super.setForeground(Color.CYAN);
    		break;
    	case 3:
    		super.setForeground(Color.RED);
    		break;
    	case 4:
    		super.setForeground(Color.MAGENTA);
    		break;
    	case 5:
    		super.setForeground(Color.MAGENTA);
    		break;
    	}
      
    	return this.adjacent_bomb;
    }
   
    public void setOpen() {
    	this.openCell = true;
    	super.setIcon(null);
      
    	if(getNumber() != 0)
    		super.setText("" + getNumber());
    }
   
    public boolean checkOpen() {
    	return this.openCell;   
    }
   
    public void setFlag() {
    	if(!this.flag) { 
    		super.setIcon(this.imgFlag);
    		this.flag = true;
    	} 
    	else {
    		super.setIcon(this.imgCell);
    		this.flag = false;
    	}
    }
   
    public boolean flag() {
    	return this.flag;
    } 
}