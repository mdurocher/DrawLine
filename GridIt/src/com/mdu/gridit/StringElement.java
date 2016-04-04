package com.mdu.gridit;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class StringElement extends Element {
  String text = "Empty text";
  float fontSize = P.GetFloatProperty("default-font-size", "10");
  private FontMetrics fontMetrics;
  float width = 0f;
  float height = 0f;
  private Rectangle2D.Float stringBounds;

  void paint(Graphics2D g) {
    Font f = P.GetFontProperty("default-font");
    f = f.deriveFont(fontSize);
    g.setFont(f);
    if (width > 0f) {
      float x = box.x;
      float y = box.y;
      switch (content_alignement) {
      case CONTENT_BEGIN:
        x = box.x;
        break;
      case CONTENT_CENTER:
        x = box.x + (width - stringBounds.width) / 2;
        break;
      case CONTENT_END:
        x = box.x + width - stringBounds.width;
        break;
      }
      g.setColor(Color.black);
      g.drawString(text, x, y + fontMetrics.getAscent());
    } else {
      g.setColor(Color.black);
      g.drawString(text, box.x, box.y + fontMetrics.getAscent());
    }
    if (U.DEBUG) {
      g.setColor(Color.gray);
      g.draw(box);
    }
  }

  void layout(Graphics2D g, Page p) {
    float w;
    float h;

    Font f = P.GetFontProperty("default-font");
    f = f.deriveFont(fontSize);
    fontMetrics = g.getFontMetrics(f);
    Rectangle2D.Float b = (Rectangle2D.Float) fontMetrics.getStringBounds(text, g);
    if (width > 0f)
      w = width;
    else
      w = b.width;

    if (height > 0f)
      h = height;
    else
      h = b.height;

    Rectangle2D.Float r = U.BoxLayout(this, p, w, h);
    box = r;
    stringBounds = b;
  }
}
