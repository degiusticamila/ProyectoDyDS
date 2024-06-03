package utils;

import javax.swing.*;

public class ScoreItem {
    private String title;
    private ImageIcon icon;

    public ScoreItem(String title, String iconPath) {
        System.out.println("iconPath: "+iconPath);
        this.title = title;
        // Cargar la imagen y crear el ImageIcon
        this.icon = new ImageIcon(iconPath);
        //this.icon = new ImageIcon(getClass().getResource(iconPath));
    }

    public String getTitle() {
        return title;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return title;
    }
}
