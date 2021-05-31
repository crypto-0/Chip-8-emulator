package com.harry.project;
import javax.swing.JFrame;


public class MyFrame extends JFrame {
  Chip8Graphics chip8Graphics = new Chip8Graphics();
  public MyFrame(){
    //this.setSize(64, 32);
    this.setSize(500, 500);
    this.setTitle("Chip-8");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(chip8Graphics);
    this.setVisible(true);
    
  }

}
