package com.mdu.gridit;

import java.awt.Color;
import java.awt.Graphics2D;

class BoxElement extends Element {
  float width = P.GetFloatProperty("default-box-width", "50");
  float height = P.GetFloatProperty("default-box-height", "50");;

  BoxElement() {
  }

  void paint(Graphics2D g) {
    g.setColor(Color.black);
    g.draw(box);

  }

  void layout(Graphics2D g, Page p) {
    box = U.BoxLayout(this, p, width, height);
  }
}
