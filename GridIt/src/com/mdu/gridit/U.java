package com.mdu.gridit;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_RENDER_QUALITY;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class U {
  static boolean DEBUG = false;

  static void SetHints(Graphics2D g) {
    g.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
    g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
    g.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
  }

  static NumberFormat format = new DecimalFormat("0000");

  static Rectangle2D.Float BoxLayout(Element e, Page page, float width, float height) {
    Rectangle2D.Float b = new Rectangle2D.Float();
    switch (e.line_alignement) {
    case Element.LINE_CENTERED:
      b.x = (float) (page.width - width) / 2f;
      break;
    case Element.LINE_BEGIN:
      b.x = page.insets.left;
      break;
    case Element.LINE_END:
      b.x = page.width - width - page.insets.right;
      break;
    case Element.LINE_FLOW:
      b.x = page.currentX;
      break;
    }
    b.y = page.currentY;
    b.width = width;
    b.height = height;

    if (b.y + b.height > page.maxY)
      page.maxY = b.y + b.height;

    if (e.newline) {
      page.currentX = page.insets.left;
      page.currentY = page.maxY;
    } else {
      page.currentX = b.x + b.width;
    }

    return b;
  }

  static String HTMLToUnicode(String s) {
    StringBuffer ret = new StringBuffer();
    boolean currentUnicode = false;
    String currentNumber = null;
    int cursor = 0;

    sw: for (char c : s.toCharArray()) {
      cursor++;
      switch (c) {
      case '#':
        if (currentUnicode && currentNumber == null) {
          currentNumber = "";
          continue sw;
        }
        break;
      case '&':
        if (!currentUnicode) {
          currentUnicode = true;
          continue sw;
        } else {
          throw new IllegalArgumentException(s + " at char " + cursor);
        }
        // cannot break;
      case ';':
        if (currentUnicode && currentNumber != null) {
          try {
            int n = Integer.parseInt(currentNumber);
            char[] hs = Character.toChars(n);
            ret.append(hs);
          } catch (NumberFormatException e) {
            ret.append(currentNumber);
          }
          currentUnicode = false;
          currentNumber = null;
          continue sw;
        }
        break;
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        if (currentNumber != null) {
          currentNumber = currentNumber + new String(new char[] { c });
          continue sw;
        }
        break;
      default:
        break;
      }
      ret.append(c);
    }
    return ret.toString();
  }

  static FloatDimension StringSize(Graphics2D g, Font f, String s) {
    if (s == null)
      return new FloatDimension();
    Rectangle2D r = f.getStringBounds(s, g.getFontRenderContext());
    return new FloatDimension(r.getWidth(), r.getHeight());
  }

  static float GetScale(Graphics2D g, Font f, String s, FloatDimension d) {
    FloatDimension ss = StringSize(g, f, s);
    float sx = d.width / ss.width;
    float sy = d.height / ss.height;
    float sc = sx > sy ? sy : sx;
    return sc;
  }

}

class FloatDimension {
  float width;
  float height;

  FloatDimension(float w, float h) {
    width = w;
    height = h;
  }

  FloatDimension() {
  }

  FloatDimension(double w, double h) {
    width = (float) w;
    height = (float) h;
  }
}