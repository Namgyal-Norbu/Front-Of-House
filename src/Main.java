import java.io.IOException;

import FOH.GUI;

public class Main {
  public static void main(String[] args) {
    System.out.println("booting up system...");
    try {
      GUI.app();
    
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("failed to boot up system...");
    }
  }
}
