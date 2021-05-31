package com.harry.project;
import javax.swing.*;
import java.awt.*;

public class Chip8Graphics extends JPanel {
  Cpu cpu = new Cpu();
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(Color.BLACK);
    Graphics2D g2d = (Graphics2D) g;
    //cpu.loadFontstoVideo();
    cpu.draw(g2d);


  }

}
