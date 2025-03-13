package pl.edu.pw.mini;

import pl.edu.pw.mini.base.manager.FileManager;
import pl.edu.pw.mini.ui.MyMenuBar;
import pl.edu.pw.mini.ui.PanelManager;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainApp {
    public static void main(String[] args) {
        if (!FileManager.isWorkingFileExists()) {
            FileManager.copyFromResources();
        }
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        UIManager.put("Panel.background", new Color(249, 249, 249));

        SwingUtilities.invokeLater(()-> {
            File iconFile = new File("C:\\Users\\alapr\\Desktop\\java_zaawansowane_24_25\\projekt2\\Learnwords\\src\\main\\resources\\icon\\icons8-learning-flashcards-78.png");
            ImageIcon icon = new ImageIcon(iconFile.getAbsolutePath());
            JFrame frame = new JFrame("Learn your words :)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setIconImage(icon.getImage());

            MyMenuBar menuBar = new MyMenuBar(frame);
            frame.setJMenuBar(menuBar);

            PanelManager.initialize(frame.getContentPane(), menuBar::updateMenu);
            PanelManager panelManager = PanelManager.getInstance();
            panelManager.showPanel("StartPanel");
            frame.setVisible(true);
        });
    }
}
