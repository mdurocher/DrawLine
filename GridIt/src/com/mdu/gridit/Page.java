package com.mdu.gridit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Page {
  float width;
  float height;
  Insets insets; // = new Insets(10,  10,  10, 10);
  float currentX;
  float currentY;
  float maxY;
  ArrayList<Element> elements = new ArrayList<Element>();
  ArrayList<Point2D.Float> currentPoints = new ArrayList<Point2D.Float>();
  ArrayList<Float> maxYs = new ArrayList<Float>();

  Page() {
    this(new Insets(0, 0, 0, 0));
  }

  Page(Insets in) {
    insets = in;
    reset();
  }

  void reset() {
    currentX = insets.left;
    currentY = insets.top;
    maxY = currentY;
    currentPoints.clear();
    currentPoints.add(new Point2D.Float(currentX, currentY));
    maxYs.add(new Float(maxY));
  }

  void layout(Graphics2D g) {
    for (Element e : elements) {
      e.layout(g, this);
      currentPoints.add(new Point2D.Float(currentX, currentY));
      maxYs.add(new Float(maxY));
    }
  }

  void resize(float w, float h) {
    width = w;
    height = h;
  }

  void paintElements(Graphics2D g) {
    for (Element e : elements) {
      e.paint(g);
    }
  }

  void paintCurrentPoints(Graphics2D g) {
    float sz = 5;
    for (Point2D.Float p : currentPoints) {
      Rectangle2D.Float c = new Rectangle2D.Float(p.x - sz / 2f, p.y - sz / 2f, sz, sz);
      g.setColor(Color.green);
      g.draw(c);
    }
  }

  void paintInsets(Graphics2D g) {
    g.setColor(Color.gray);
    Line2D.Float l = new Line2D.Float(0, insets.top, width, insets.top);
    g.draw(l);
    l = new Line2D.Float(insets.left, 0, insets.left, height);
    g.draw(l);
    l = new Line2D.Float(0, height - insets.bottom, width, height - insets.bottom);
    g.draw(l);
    l = new Line2D.Float(width - insets.right, 0, width - insets.right, width);
    g.draw(l);
  }

  void paintMaxY(Graphics2D g) {
    Line2D.Float l = new Line2D.Float(0, maxY, width, maxY);
    g.setColor(new Color(0xff, 0, 0, 0xff / 2));
    g.draw(l);
  }

  void paintMaxYs(Graphics2D g) {
    for(Float y : maxYs) {
      Line2D.Float l = new Line2D.Float(0, y, width, y);
      g.setColor(new Color(0xff, 0, 0, 0xff / 16));
      g.draw(l);
    }
  }
}
