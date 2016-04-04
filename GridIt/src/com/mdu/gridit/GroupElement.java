package com.mdu.gridit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

class GroupElement extends Element {

  Page page = new Page(new Insets(0, 0, 0, 0));
  ArrayList<Element> elements = new ArrayList<Element>();

  GroupElement() {
  }

  void translate(float dx, float dy) {
    super.translate(dx, dy);

    for (Element e : elements) {
      e.translate(dx, dy);
    }

  }

  void paint(Graphics2D g) {
    for (Element e : elements)
      e.paint(g);
    if (U.DEBUG) {
      g.setColor(Color.blue);
      g.draw(box);
    }
  }

  void add(Element e) {
    elements.add(e);
  }

  void layout(Graphics2D g, Page p) {

    Rectangle2D.Float r = null;
    page.reset();

    for (Element e : elements) {
      e.layout(g, page);
      Rectangle2D.Float be = e.box;
      if (r == null)
        r = new Rectangle2D.Float(be.x, be.y, be.width, be.height);
      else
        Rectangle2D.Float.union(r, be, r);
    }

    box = U.BoxLayout(this, p, r.width, r.height);

    for (Element e : elements) {
      e.translate(box.x - page.insets.left, box.y - page.insets.top);
    }

  }

}
