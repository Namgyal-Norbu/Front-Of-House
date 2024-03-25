import java.io.IOException;

import FOH.Home;

public class Main {
  public static void main(String[] args) {
    System.out.println("booting up system...");
    try {
      Home app = new Home();
      app.start();
    
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("failed to boot up system...");
    }
  }
}
