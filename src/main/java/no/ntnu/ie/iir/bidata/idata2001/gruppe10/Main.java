package no.ntnu.ie.iir.bidata.idata2001.gruppe10;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes.MainMenuApp;

/**
 * Represents the starting point of the application.
 * The only function of this class is to keep the class
 * method <b>main()</b>.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class Main {
  /**
    * The main starting point of the application. The operating system
    * of the computer expects to find a publicly available method it can
    * call without having to create objects first.
    *
    * @param args an fixed size array of Strings holding arguments provided
    *             from the command line during the startup of the application.
    */
  public static void main(String[] args) {
    MainMenuApp.appLauncher(args);
  }
}