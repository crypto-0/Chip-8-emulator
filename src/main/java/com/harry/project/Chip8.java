package com.harry.project;

public class Chip8 {
  public static void main(String args[]) {
    MyFrame myFrame = new MyFrame();
    //myFrame.chip8Graphics.cpu.loadRom("/home/crypto/Desktop/java_programming/chip-8/test_opcode.ch8");
    //myFrame.chip8Graphics.cpu.loadRom("/home/crypto/Desktop/java_programming/chip-8/chip8-test-rom.ch8");
    myFrame.chip8Graphics.cpu.loadRom("/home/crypto/Desktop/java_programming/chip-8/maze.rom");
    //myFrame.chip8Graphics.cpu.printmemory();
    //myFrame.chip8Graphics.cpu.disassemble();
    
    
    while (true) {
      for (int a = 0; a < 9; a++) {
        myFrame.chip8Graphics.cpu.cycle();
      }
      myFrame.chip8Graphics.repaint();
    }
    
  }
}

