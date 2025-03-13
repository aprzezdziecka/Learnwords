package pl.edu.pw.mini.ui;

import pl.edu.pw.mini.ui.loginUI.*;
import pl.edu.pw.mini.ui.loginUI.addSet.AddSetCSVPanel;
import pl.edu.pw.mini.ui.loginUI.addSet.AddSetPanelinApp;
import pl.edu.pw.mini.ui.loginUI.addSet.AddSetTXTPanel;
import pl.edu.pw.mini.ui.logoutUI.LoginPanel;
import pl.edu.pw.mini.ui.logoutUI.RegisterPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PanelManager {
    private static PanelManager instance;
    private final Container container;
    private final HashMap<String, JPanel> panels = new HashMap<>();
    protected Runnable menuUpdate;

    //chyba private powinno byc
    private PanelManager(Container container, Runnable menuUpdate) {
        this.container = container;
        this.menuUpdate = menuUpdate;
        initializePanels();

    }
    public static PanelManager getInstance() {
        return instance;
    }
    public static void initialize(Container container, Runnable menuUpdate) {
        if (instance == null) {
            instance = new PanelManager(container, menuUpdate);
        }
    }

    private void initializePanels(){
        panels.put("StartPanel", new StartPanel());
        panels.put("LoginPanel", new LoginPanel(menuUpdate));
        panels.put("RegisterPanel", new RegisterPanel());
        panels.put("AccountPanel", new AccountPanel());
        panels.put("AddSetPanelApp", new AddSetPanelinApp());
        panels.put("AddSetCSVPanel", new AddSetCSVPanel());
        panels.put("AddSetTXTPanel", new AddSetTXTPanel());
        panels.put("RemindersPanel", new RemindersPanel());
        panels.put("LearnPanel", new LearnPanel());
    }
    public void restart(){
        panels.clear();
        initializePanels();
    }

    public void showPanel(String panelName){
        container.removeAll();
        container.add(panels.get(panelName), BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }
    public void showPanel(JPanel panel){
        container.removeAll();
        container.add(panel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }




}
