package com.mdu.gridit;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class GridItComponent extends JPanel implements ComponentListener {
  Font defaultFont;
  Page page = new Page(new Insets(20, 20, 20, 20));

  GridItComponent() {
    super();
    addComponentListener(this);
  }

  void pageLayout(Graphics2D g) {
    if (g == null) {
      BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
      g = image.createGraphics();
    }
    page.reset();
    page.layout(g);
    repaint();
  }

  void add(Element e) {
    page.elements.add(e);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    U.SetHints(g2);
    page.paintElements(g2);

    if (U.DEBUG) {
      page.paintInsets(g2);
      page.paintMaxY(g2);
      page.paintMaxYs(g2);
      page.paintCurrentPoints(g2);
    }
  }

  public void componentResized(ComponentEvent e) {
    page.resize(getWidth(), getHeight());
    pageLayout((Graphics2D) getGraphics());
  }

  public void componentMoved(ComponentEvent e) {
  }

  public void componentShown(ComponentEvent e) {
    pageLayout((Graphics2D) getGraphics());
  }

  public void componentHidden(ComponentEvent e) {
  }
}
