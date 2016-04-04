package com.mdu.gridit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class GridIt extends JFrame {

  GridIt() {
    super();
    P.LoadProperties();

    JTabbedPane pane = getTabbedPane();

    GridItComponent page = new TestPage1();
    page.defaultFont = loadDefaultFont();
    pane.addTab("TestPage", page);

    page = new TestPage2();
    page.defaultFont = loadDefaultFont();
    pane.addTab("TestPage2", page);

    page = new TestPage3();
    page.defaultFont = loadDefaultFont();
    pane.addTab("TestPage3", page);
    
    page = new TestPage4();
    page.defaultFont = loadDefaultFont();
    pane.addTab("TestPage4", page);

    getContentPane().add(pane, BorderLayout.CENTER);

    setSize(P.GetIntProperty("frame-width", "800"), P.GetIntProperty("frame-height", "600"));

    getContentPane().setBackground(P.GetColorProperty("frame-background", "0xc0c0c0"));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int x = (screenSize.width - getWidth()) / 2;
    int y = (screenSize.height - getHeight()) / 2;
    setLocation(x, y);
    setVisible(true);
  }

  JTabbedPane getTabbedPane() {
    Insets oldInsets = UIManager.getInsets("TabbedPane.contentBorderInsets");
    // bottom insets is 1 because the tabs are bottom aligned 
    UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 1, 0));
    JTabbedPane tabPane = new JTabbedPane(JTabbedPane.BOTTOM);
    UIManager.put("TabbedPane.contentBorderInsets", oldInsets);
    return tabPane;
  }

  Font loadDefaultFont() {
    Font f = P.GetFontProperty("default-font", "");
    return f;
  }

  public static void main(String[] a) {
    /*GridIt instance = */new GridIt();
  }
}
