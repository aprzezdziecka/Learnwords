package pl.edu.pw.mini.ui;

import pl.edu.pw.mini.base.session.UserSession;

import javax.swing.*;
import java.io.File;

public class MyMenuBar extends JMenuBar {
    private JMenuItem loginItem;
    private JMenuItem registerItem;
    private JMenuItem logoutItem;
    private JMenuItem accountItem;
    private JMenuItem addSetItem;
    private JMenuItem addSetItemCSV;
    private JMenuItem addSetItemTXT;
    private JMenuItem learningItem;
    private JMenuItem remindersItem;

    public MyMenuBar(JFrame frame) {
        JMenu menu = new JMenu("Menu");
        this.add(menu);

        JMenu subMenu = new JMenu("Dodawanie zestawów");
        menu.add(subMenu);

        loginItem = new JMenuItem("Zaloguj");
        ImageIcon iconlogin = new ImageIcon("src/main/resources/icon/icons8-test-account-40.png");
        loginItem.setIcon(iconlogin);
        loginItem.addActionListener(e-> {
            PanelManager.getInstance().showPanel("LoginPanel");
        });

        registerItem = new JMenuItem("Utwórz konto");
        ImageIcon iconsetaccount= new ImageIcon("src/main/resources/icon/icons8-add-user-female-40.png");
        registerItem.setIcon(iconsetaccount);
        registerItem.addActionListener(e-> {
            PanelManager.getInstance().showPanel("RegisterPanel");
        });

        logoutItem = new JMenuItem("Wyloguj");
        ImageIcon iconlogout = new ImageIcon("src/main/resources/icon/icons8-logout-40.png");
        logoutItem.setIcon(iconlogout);
        logoutItem.addActionListener(e-> {
            UserSession.getInstance().logout();
            updateMenu();
            PanelManager.getInstance().showPanel("StartPanel");
            PanelManager.getInstance().restart();
        });

        accountItem = new JMenuItem("Konto");
        ImageIcon iconaccount = new ImageIcon("src/main/resources/icon/icons8-contact-info-40.png");
        accountItem.setIcon(iconaccount);
        accountItem.addActionListener(e-> {
            PanelManager.getInstance().showPanel("AccountPanel");
        });

        addSetItem = new JMenuItem("Dodawnie zestawów w aplikacji");
        ImageIcon iconaddset = new ImageIcon("src/main/resources/icon/icons8-add-40.png");
        addSetItem.setIcon(iconaddset);
        addSetItem.addActionListener(e-> {
            PanelManager.getInstance().showPanel("AddSetPanelApp");
        });

        addSetItemCSV = new JMenuItem("Dodawanie zestawów z pliku CSV");
        ImageIcon iconadd = new ImageIcon("src/main/resources/icon/icons8-add-folder-40.png");
        addSetItemCSV.setIcon(iconadd);
        addSetItemCSV.addActionListener(e-> {
            PanelManager.getInstance().showPanel("AddSetCSVPanel");
        });

        addSetItemTXT = new JMenuItem("Dodawanie zestawów z pliku txt");
        addSetItemTXT.setIcon(iconadd);
        addSetItemTXT.addActionListener(e-> {
            PanelManager.getInstance().showPanel("AddSetTXTPanel");
        });
        learningItem = new JMenuItem("Nauka");
        ImageIcon iconlearning = new ImageIcon("src/main/resources/icon/icons8-translation-40.png");
        learningItem.setIcon(iconlearning);
        learningItem.addActionListener(e-> {
            PanelManager.getInstance().showPanel("LearnPanel");
        });

        remindersItem = new JMenuItem("Przypomnienia");
        ImageIcon iconreminders = new ImageIcon("src/main/resources/icon/icons8-calendar-plus-40.png");
        remindersItem.setIcon(iconreminders);
        remindersItem.addActionListener(e-> {
            PanelManager.getInstance().showPanel("RemindersPanel");
        });

        updateMenu();
    }

    public void updateMenu() {
        this.removeAll();
        JMenu menu = new JMenu("Menu");
        JMenu subMenu = new JMenu("Dodawanie zestawów");

        if(UserSession.getInstance().isLoggedIn()){
            menu.add(logoutItem);
            menu.add(accountItem);
            subMenu.add(addSetItemCSV);
            subMenu.add(addSetItem);
            subMenu.add(addSetItemTXT);
            menu.add(subMenu);
            menu.add(learningItem);
            menu.add(remindersItem);
        }else {
            menu.add(loginItem);
            menu.add(registerItem);
        }
        this.add(menu);
        this.revalidate();
        this.repaint();
    }

}
