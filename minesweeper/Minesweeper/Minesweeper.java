/*
MinerSweeper game 
Author: Grigory Ostanin: 2/12/17
*/

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

public class Minesweeper extends JFrame {
	private static ImageIcon icon = new ImageIcon(Minesweeper.class.getResource("bombIcon.png")); 

	public static void main(String[] args) {
		Board board = new Board();
		JFrame application = new JFrame("Minesweeper");

		board.newGame();
      
		JLabel mines = new JLabel(board.bombsLeft());
        mines.setPreferredSize(new Dimension(0, 50));
      
        JButton newGame = new JButton("New Game");
        newGame.setPreferredSize(new Dimension(0, 40)); 
        newGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		board.removeAll();
        		board.revalidate();
        		board.newGame();
            }
         });    
   
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(board);
        application.add(newGame, BorderLayout.NORTH);
        application.add(mines, BorderLayout.SOUTH);
        application.setSize(410,510);
        application.setVisible(true);
        application.setResizable(false);
        application.setIconImage(icon.getImage());
        application.setLocationRelativeTo(null);
        
        while(true) {
        	mines.setText(board.bombsLeft());
        }
	}
}