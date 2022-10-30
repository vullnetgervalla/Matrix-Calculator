/**
 * File: App.java
 * Project: Matrix Calculator
 * Date: October 2022
 * @author Neti-G
 */

/**
 * The class which executes the program.
 */
public class App {
    /**
     * The main method of the application which creates an instance of the
     * class GuiInterface which in turn makes an instance of the class
     * MatrixLogic.
     */
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiInterface();
             }
        });
    }
}
