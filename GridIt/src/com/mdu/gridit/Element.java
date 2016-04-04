package com.mdu.gridit;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

abstract class Element {
  static final String LINE_CENTERED = "line-centered";
  static final String LINE_BEGIN = "line-begin";
  static final String LINE_END = "line-end";
  static final String LINE_FLOW = "line-flow";

  String line_alignement = LINE_FLOW;
  boolean newline;

  static final String CONTENT_BEGIN = "content-begin";
  static final String CONTENT_CENTER = "content-center";
  static final String CONTENT_END = "content-end";

  String content_alignement = CONTENT_CENTER;

  Rectangle2D.Float box = new Rectangle2D.Float();
  float left_margin = 20f;
  float right_margin = 20f;

  void translate(float dx, float dy) {
    box.x += dx;
    box.y += dy;
  }

  abstract void paint(Graphics2D g);

  abstract void layout(Graphics2D g, Page p);
}
