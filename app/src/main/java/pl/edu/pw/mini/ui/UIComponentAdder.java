package pl.edu.pw.mini.ui;

import javax.swing.*;
import java.awt.*;

public interface UIComponentAdder {
    default void addComponent(JPanel panel, Component comp, int x, int y, int gridWidth, int gridHeight, int anchor, int fill, int top, int left, int bottom, int right) {
        GridBagConstraints gbc = createGridBagConstraints(x, y, gridWidth, gridHeight, anchor, fill, top, left, bottom, right);
        panel.add(comp, gbc);
    }

    default void addComponent(JPanel panel, Component comp, int x, int y, int gridWidth, int gridHeight, int anchor, int fill) {
        GridBagConstraints gbc = createGridBagConstraints(x, y, gridWidth, gridHeight, anchor, fill, 5, 5, 5, 5);
        panel.add(comp, gbc);
    }

    default void addComponent2(JPanel panel, Component comp, int x, int y, int gridWidth, int weightx, int fill, int anchor, int top, int left, int bottom, int right) {
        GridBagConstraints gbc = createGridBagConstraints(x, y, gridWidth, 1, anchor, fill, top, left, bottom, right);
        gbc.weightx = weightx;
        panel.add(comp, gbc);
    }

    private GridBagConstraints createGridBagConstraints(int x, int y, int gridWidth, int gridHeight, int anchor, int fill, int top, int left, int bottom, int right) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.insets = new Insets(top, left, bottom, right);
        return gbc;
    }
}
