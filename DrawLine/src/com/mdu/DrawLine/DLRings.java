package com.mdu.DrawLine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;

public class DLRings extends DLImage {
  int threadSleep = 50;
  int frameCount = 1;
  float arcLength = 180f;
  long start_time = 0;

  public DLRings() {
    super();
  }

  DLRings(DLRings src) {
    this();
  }

  public DLRings(float x, float y) {
    super(x, y);
  }

  DLRings copy() {
    return new DLRings(this);
  }

  public void f(Graphics2D g, DLThread t) {
    while (frameCount++ > 0) {

      if (t != null && t.isStopped())
        break;

      clearImage();
      step(g);

      if (parent != null)
        parent.paint(this);

      int s = getThreadSleep();
      if (s > 0) {
        try {
          Thread.sleep(s);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      }
    }
  }

  BufferedImage image() {
    final BufferedImage img = new BufferedImage(iwidth, iheight, BufferedImage.TYPE_INT_ARGB);
    final Graphics2D g = img.createGraphics();
    DLUtil.SetHints(g);

    if (threaded)
      runThreaded(g);

    return img;
  }

  void sleep(int s) {
    try {
      Thread.sleep(s);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
  }
  
  void step(Graphics2D g) {
    if(start_time == 0)
      start_time = DLUtil.millis();
    
    g.setPaint(new Color(0x05225C));
    float tx = iwidth / 2f;
    float ty = iheight / 2f;
    for (int r = 10; r < 280; r = r + 20) {
      long m = System.nanoTime() - start_time;
      float theta = m / 100f;
      AffineTransform tr = AffineTransform.getRotateInstance(theta, tx, ty);
      g.setTransform(tr);
      float width = r / 30f;
      Stroke s = new BasicStroke(width);
      g.setStroke(s);
      Arc2D.Float arc = new Arc2D.Float(r, r, iwidth - 2 * r, iheight - 2 * r, 0, arcLength, Arc2D.OPEN);
      g.draw(arc);
//      g.setTransform(new AffineTransform());

//      System.err.println(System.nanoTime() - f);
    }
    start_time = 0;
  }

  public void randomize() {
    iwidth = DLUtil.RangeRandom(300, 500);
    iheight = iwidth;
  }

  public int getThreadSleep() {
    return threadSleep;
  }

  public void setThreadSleep(int threadSleep) {
    this.threadSleep = threadSleep;
  }

  public int[] rangeThreadSleep() {
    return new int[] { 0, 1000 };
  }

}
