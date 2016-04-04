package com.mdu.gridit;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.SwingConstants;

public class ChordsElement extends BoxElement {

  static final String ONE_CHORD = "one-chord";
  static final String TWO_CHORDS = "two-chords";
  static final String THREE_CHORDS_A = "three-chords-a";
  static final String THREE_CHORDS_B = "three-chords-b";
  static final String FOUR_CHORDS = "four-chords";

  String chordLayout = ONE_CHORD;

  //  static final String LINE_NO_LINE = "no-line";
  //  static final String LINE_DIAGONAL = "diagonal";
  //  static final String LINE_DIAGONAL_AND_HALF = "diagonal-and-half";
  //  static final String LINE_TWO_DIAGONALS = "two-diagonals";
  //  static final String LINE_HALF_AND_DIAGONAL = "half-and-diagonal";

  ChordsElement() {
  }

  String chords[] = new String[4];
  FontMetrics fontMetrics;
  float fontSize = P.GetFloatProperty("default-font-size", "10");
  float scale = 1;

  static Point2D.Float DrawBox(Graphics2D g, Font f, String s, float fontSize, float width, float height) {
    FloatDimension d = new FloatDimension(width, height);
    float sc = U.GetScale(g, f, s, d) * 0.8f;
    f = f.deriveFont(sc * fontSize);
    g.setFont(f);
    FontMetrics fm = g.getFontMetrics(f);
    float as = fm.getAscent();
    FloatDimension ss = U.StringSize(g, f, s);
    float x = (width - ss.width) / 2f;
    float y = (height - ss.height) / 2f + as;
    return new Point2D.Float(x, y);
  }

  void paintChord(Graphics2D g, String s, int direction) {
    if(s == null)
      return;
    Font f = P.GetFontProperty("default-font");
    f = f.deriveFont(/*scale **/fontSize);
    g.setFont(f);
    float x = 0;
    float y = 0;
    float adjust = 5;

    switch (direction) {
    case SwingConstants.CENTER: {
      float w = /* scale * */box.width;
      float h = /* scale * */box.height;
      Point2D.Float p = DrawBox(g, f, s, fontSize, w, h);
      x = box.x + p.x;
      y = box.y + p.y;
    }
      break;
    case SwingConstants.WEST: {
      float w2 = scale * box.width / 2f;
      float h2 = scale * box.height / 2f;
      Point2D.Float p = DrawBox(g, f, s, fontSize, w2, h2);
      x = box.x + p.x - adjust;
      y = box.y + p.y + h2 / 2;
    }
      break;
    case SwingConstants.NORTH: {
      float w2 = scale * box.width / 2f;
      float h2 = scale * box.height / 2f;
      Point2D.Float p = DrawBox(g, f, s, fontSize, w2, h2);
      x = box.x + p.x + w2 / 2;
      y = box.y + p.y - adjust;
    }
      break;
    case SwingConstants.EAST: {
      float w2 = scale * box.width / 2f;
      float h2 = scale * box.height / 2f;
      Point2D.Float p = DrawBox(g, f, s, fontSize, w2, h2);
      x = box.x + p.x + w2 + adjust;
      y = box.y + p.y + h2 / 2;
    }
      break;
    case SwingConstants.SOUTH: {
      float w2 = scale * box.width / 2f;
      float h2 = scale * box.height / 2f;
      Point2D.Float p = DrawBox(g, f, s, fontSize, w2, h2);
      x = box.x + p.x + w2 / 2;
      y = box.y + p.y + h2 + adjust;
    }
      break;
    case SwingConstants.NORTH_WEST: {
      float w2 = /* scale * */box.width / 2f;
      float h2 = /* scale * */box.height / 2f;
      Point2D.Float p = DrawBox(g, f, s, fontSize, w2, h2);
      x = box.x + p.x;
      y = box.y + p.y;
    }
      break;
    case SwingConstants.NORTH_EAST: {
      float w2 = /* scale * */box.width / 2f;
      float h2 = /* scale * */box.height / 2f;
      Point2D.Float p = DrawBox(g, f, s, fontSize, w2, h2);
      x = box.x + box.width + p.x - w2;
      y = box.y + p.y;
    }
      break;
    case SwingConstants.SOUTH_EAST: {
      float w2 = /* scale * */box.width / 2f;
      float h2 = /* scale * */box.height / 2f;
      Point2D.Float p = DrawBox(g, f, s, fontSize, w2, h2);
      x = box.x + box.width + p.x - w2;
      y = box.y + box.height + p.y - w2;
    }
      break;
    case SwingConstants.SOUTH_WEST: {
      float w2 = /* scale * */box.width / 2f;
      float h2 = /* scale * */box.height / 2f;
      Point2D.Float p = DrawBox(g, f, s, fontSize, w2, h2);
      x = box.x + p.x;
      y = box.y + box.height + p.y - w2;
    }
      break;
    }
    //    if (x - box.x < 2)
    //      x = box.x + 2;
    g.drawString(s, x, y);
  }

  void paint(Graphics2D g) {
    g.setColor(Color.darkGray);
    g.draw(box);
    Path2D.Float p = null;

    switch (chordLayout) {
    case ONE_CHORD:
      p = null;
      break;
    case TWO_CHORDS:
      p = new Path2D.Float();
      p.moveTo(box.x + box.width, box.y);
      p.lineTo(box.x, box.y + box.height);
      break;
    case THREE_CHORDS_A:
      p = new Path2D.Float();
      p.moveTo(box.x + box.width, box.y);
      p.lineTo(box.x, box.y + box.height);
      p.moveTo(box.x + box.width / 2f, box.y + box.height / 2f);
      p.lineTo(box.x + box.width, box.y + box.height);
      break;
    case THREE_CHORDS_B:
      p = new Path2D.Float();
      p.moveTo(box.x + box.width, box.y);
      p.lineTo(box.x, box.y + box.height);
      p.moveTo(box.x + box.width / 2f, box.y + box.height / 2f);
      p.lineTo(box.x, box.y);
      break;
    case FOUR_CHORDS:
      p = new Path2D.Float();
      p.moveTo(box.x + box.width, box.y);
      p.lineTo(box.x, box.y + box.height);
      p.moveTo(box.x, box.y);
      p.lineTo(box.x + box.width, box.y + box.height);
      break;
    default:
      break;
    }
    g.setColor(Color.black);
    if (p != null)
      g.draw(p);

    switch (chordLayout) {
    case ONE_CHORD:
      paintChord(g, chords[0], SwingConstants.CENTER);
      break;
    case TWO_CHORDS:
      paintChord(g, chords[0], SwingConstants.NORTH_WEST);
      paintChord(g, chords[1], SwingConstants.SOUTH_EAST);
      break;
    case THREE_CHORDS_A:
      paintChord(g, chords[0], SwingConstants.WEST);
      paintChord(g, chords[1], SwingConstants.EAST);
      paintChord(g, chords[2], SwingConstants.SOUTH);
      break;
    case THREE_CHORDS_B:
      paintChord(g, chords[0], SwingConstants.EAST);
      paintChord(g, chords[1], SwingConstants.NORTH);
      paintChord(g, chords[2], SwingConstants.SOUTH_WEST);
      break;
    case FOUR_CHORDS:
      paintChord(g, chords[0], SwingConstants.WEST);
      paintChord(g, chords[1], SwingConstants.NORTH);
      paintChord(g, chords[2], SwingConstants.EAST);
      paintChord(g, chords[3], SwingConstants.SOUTH);
      break;
    default:
      break;
    }
  }

  void layout(Graphics2D g, Page p) {
    super.layout(g, p);
  }
}
