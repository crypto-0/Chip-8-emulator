package com.harry.project;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;




public class MyFrame extends JFrame {
  Chip8Graphics chip8Graphics = new Chip8Graphics();
  public MyFrame(){
    //this.setSize(64, 32);
    this.setSize(480, 160);
    this.setTitle("Chip-8");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(chip8Graphics);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    this.setVisible(true);

    
  }

}
