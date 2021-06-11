package com.harry.project;
import javax.swing.*;
import java.awt.*;

public class Chip8Graphics extends JPanel implements Runnable{
  Cpu cpu = new Cpu();
  private static final int PWIDTH = 480;
  private static final int PHEIGHT = 160;
  private Thread animator;
  private volatile boolean running = false;
  private volatile boolean gameOver = false;
  private Graphics dbg;
  private Image dbImage = null;

  private long period = 1000 / 60;
  private int NO_DELAY_PER_YIELD = 10;

  public Chip8Graphics() {
    setBackground(Color.black);
    setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
    //cpu.loadRom("/home/crypto/Programming/Java_projects/Chip-8-emulator/maze.rom");
    //cpu.loadRom("/home/crypto/Programming/Java_projects/Chip-8-emulator/15puzzle.ch8");
    cpu.loadRom("/home/crypto/Programming/Java_projects/Chip-8-emulator/Pong1player.ch8");
    //cpu.loadRom("/home/crypto/Programming/Java_projects/Chip-8-emulator/IBMLogo.ch8");
    // cpu.loadRom("/home/crypto/Programming/Java_projects/Chip-8-emulator/test_opcode.ch8");
   // cpu.loadRom("/home/crypto/Programming/Java_projects/Chip-8-emulator/Brick.ch8");


  }

  public void  addNotify(){
    super.addNotify();
    startGame();
  }

  public void startGame() {
    if (animator == null || !running) {
      animator = new Thread(this);
      animator.start();
    }
  }

  public void stopGame() {
    running = false;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    //cpu.loadFontstoVideo();
    cpu.draw(g2d);
    if (dbImage != null) {
    //  g.drawImage(dbImage, 0, 0, null);
    }


  }

  @Override
  public void run() {
    long beforeTime, timeDiff, sleepTime, afterTime;
    long overSleepTime = 0l;
    int noDelays = 0;
    beforeTime = System.currentTimeMillis();
    running = true;;
    while (running) {
      gameUpdate();
      gameRender();
      paintScreen();
      cpu.updateTimers();

      afterTime = System.currentTimeMillis();
      timeDiff = afterTime - beforeTime;
      sleepTime = period - timeDiff - overSleepTime;
      if (sleepTime > 0) {
        try {
          Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
        }
        overSleepTime = (System.currentTimeMillis() - afterTime) - sleepTime;
      }
      else {
        overSleepTime = 0;
        if (++noDelays >= NO_DELAY_PER_YIELD) {
          Thread.yield();
          noDelays = 0;
        }
      }
      beforeTime = System.currentTimeMillis();
    }
  }

  public void gameUpdate() {
      for (int a = 0; a < 9; a++) {
        cpu.cycle();
      }

  }

  public void gameRender() {
    if (dbImage == null) {
      dbImage = createImage(PWIDTH, PHEIGHT);
      if (dbImage == null) {
        System.out.println("dbImage is null");
        return;
      } else {
        dbg = dbImage.getGraphics();
      }

    }
    dbg.setColor(Color.black);
    dbg.fillRect(0, 0, PWIDTH, PHEIGHT);
    cpu.draw((Graphics2D) dbg);

  }
 
  public void paintScreen() {
    Graphics g;

    try{
      g = this.getGraphics();
      if((g != null) && (dbImage !=null)){
        g.drawImage(dbImage, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
      }
    }
    catch (Exception e) {
      System.out.println("Graphics context error: " + e);
    }
  }

}
