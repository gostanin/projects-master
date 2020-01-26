import java.security.SecureRandom;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements MouseListener, ActionListener {
	private int row = 20;
	private int column = 20;
	private int items = row * column;
	private int bombs = 0;
	private int numberBombs = 40;
	private ImageIcon bomb = new ImageIcon(getClass().getResource("bomb2.png"));
	private ImageIcon bombWrong = new ImageIcon(getClass().getResource("bombWrong.png"));
	private Cell[][] cells_on = new Cell[row][column];
   
	public void newGame() {
		items = row*column;
		bombs = 0;
		setBoard();
	}
   
	public void setBoard() {
		setLayout(new GridLayout(row,column));

		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {  
				cells_on[i][j] = new Cell();
				add(cells_on[i][j]);
				cells_on[i][j].addActionListener(this);
				cells_on[i][j].addMouseListener(this);
			}
		}
		initilizeBombs();
		initilizeNumbers(); 
	}
   
	public String bombsLeft() {
		return "      Bombs: "+bombs;
	}
   
	private void initilizeBombs() {
		SecureRandom random = new SecureRandom();
           
		do {
			int i = random.nextInt(row);
			int j = random.nextInt(column);
         
			if(!cells_on[i][j].check()) {
				cells_on[i][j].setBomb();
				bombs++;
			}
		}
		while(bombs<numberBombs); 
	}
   
	private void initilizeNumbers() {
		for(int i=0; i<row; i++) {
         	for(int j=0; j<column; j++) {
         		if(cells_on[i][j].check())
         			increaseNumbers(i,j);
         	}
		}
	}
   
	public void openCell(int y_cord, int x_cord) {
		if(!cells_on[y_cord][x_cord].checkOpen()) {
			cells_on[y_cord][x_cord].setOpen(); 
			items--;
		}
		
		System.out.println(items);
		System.out.println(bombs);
      
		if(!cells_on[y_cord][x_cord].check() && cells_on[y_cord][x_cord].getNumber() == 0) {   
			revealsZeros(y_cord, x_cord); 
		}
		else if(cells_on[y_cord][x_cord].check()) {  
			revealBoard(0);
			cells_on[y_cord][x_cord].setIcon(bomb);
			cells_on[y_cord][x_cord].setBackground(new Color(168,20,20));
		}
		
		if(items <= numberBombs || bombs <= 0) {
			System.out.println("win");
			revealBoard(1);
		}
	}
   
	public void revealBoard(int status) {
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				cells_on[i][j].removeActionListener(this);
				cells_on[i][j].removeMouseListener(this);
				if(status == 0) {
					if(cells_on[i][j].check() && !cells_on[i][j].flag()) {
						cells_on[i][j].setIcon(bomb);
						cells_on[i][j].setBackground(Color.GRAY);  
					}
					else if(!cells_on[i][j].check() && cells_on[i][j].flag()) {  
						cells_on[i][j].setIcon(bombWrong);
						cells_on[i][j].setBackground(Color.GRAY);
					}
				} 
				else {
					if(cells_on[i][j].check()) {
						cells_on[i][j].setFlag(); 
					}
				}           
			}
		}
	}
    
	private void increaseNumbers(int row_number, int column_number) {    
		for(int i = row_number-1; i <= row_number+1; i++) {
	        if(i >= 0 && i < row) { 
	            for(int j = column_number-1; j <= column_number+1; j++) {
	            	if(j >= 0 && j < column) {
	            		if(!cells_on [i][j].check())
	            			cells_on[i][j].addNumber();
	            	}
	            }
	        }
         }        
	}
   
	private void revealsZeros(int row_zero, int column_zero) {     
		for(int i = row_zero-1; i <= row_zero+1; i++) {
			if(i >= 0 && i < row) {
				for(int j = column_zero-1; j <= column_zero+1; j++) {
					if(j >= 0 && j < column) {
						if(!cells_on[i][j].checkOpen() && !cells_on[i][j].check()) {
							cells_on[i][j].setOpen();
							items--;
							if(cells_on[i][j].getNumber() == 0)
								revealsZeros(i,j);
						}
					}
				}
			}
		} 
    }
   
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < cells_on[1].length; i++) {
		    for(int j = 0; j < cells_on.length; j++) {
                if(e.getSource() == cells_on[i][j]) {
                     openCell(i,j);
                 }
            }
		}
	}
   
	public void mouseClicked(MouseEvent ex) {
		if (ex.getButton() == MouseEvent.BUTTON3) {
			for(int i = 0; i < cells_on[1].length; i++) {
            	for(int j = 0; j < cells_on.length; j++) {                              
                	if(ex.getSource() == cells_on[i][j]) {
                    	cells_on[i][j].setFlag();
                    	if(cells_on[i][j].flag()) {
                    		cells_on[i][j].removeActionListener(this);
                    		bombs--;
                    	}
                        else {
                        	cells_on[i][j].addActionListener(this);
                        	bombs++;
                        }
                    }
                }
            }    
		}
	}

	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent eventt){}
	public void mouseExited(MouseEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
}