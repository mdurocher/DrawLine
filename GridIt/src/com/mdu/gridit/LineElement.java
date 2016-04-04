package com.mdu.gridit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class LineElement extends BoxElement {
  float percent;

  LineElement() {
  }

  void paint(Graphics2D g) {
    if (U.DEBUG) {
      g.setColor(Color.lightGray);
      g.draw(box);
    }
    g.setColor(Color.darkGray);
    Line2D.Float l = new Line2D.Float(box.x, box.y + box.height / 2f,
        box.x + box.width, box.y + box.height / 2f);
    g.draw(l);
  }

  void layout(Graphics2D g, Page p) {
    if (percent > 0f) {
      float w = p.width - p.insets.right - p.currentX;
      w = w * percent / 100f;
      Rectangle2D.Float r = U.BoxLayout(this, p, w, height);
      box = r;
    } else {
      super.layout(g, p);
    }
  }
}
