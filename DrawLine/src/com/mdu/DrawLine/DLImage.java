package com.mdu.DrawLine;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.util.ArrayList;

import com.jhlabs.image.BoxBlurFilter;
import com.jhlabs.image.EdgeFilter;

abstract class DLImage extends DLComponent implements Threaded, JPG {
  ArrayList<DLThread> threads = new ArrayList<DLThread>();
  boolean threaded = true;
  BufferedImage image = null;
  int iheight; // = 200;
  int iwidth; // = 200;
  boolean selectCheckTransparentPixel = false;
  Color backgroundColor;
  String filterName = "null";
  BufferedImageOp filter = getFilterFromString(filterName);
  int res = 4;

  void reportException(Throwable e) {
    System.err.println(e);
    e.printStackTrace();
  }

  DLImage() {
    super(0, 0);
  }

  DLImage(DLImage c) {
    super(c);
    iwidth = c.iwidth;
    iheight = c.iheight;
    threaded = c.threaded;
    reset();
  }

  DLImage(float x, float y) {
    super(x, y);
  }

  DLImage(float x, float y, int iw, int ih) {
    super(x, y);
    iwidth = iw;
    iheight = ih;
  }

  void clear() {
    clearImage();
    clearShadow();
  }

  void clearImage() {
    if (image == null) {
      image = image();
    } else {
      if (backgroundColor == null) {
        final Graphics2D g = image.createGraphics();
        Composite c = g.getComposite();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        final Rectangle rect = new Rectangle(0, 0, iwidth, iheight);
        g.fill(rect);
        g.setComposite(c);
      } else {
        Graphics2D g = image.createGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, iwidth, iheight);
      }
    }

    if (DLParams.DEBUG || true) {
      final Graphics2D g = image.createGraphics();
      g.setColor(Color.darkGray);
      g.drawRect(0, 0, iwidth - 1, iheight - 1);
    }
  }

  abstract DLImage copy();

  Rectangle getBounds() {
    return getBounds(true);
  }

  Rectangle getBounds(boolean deco) {
    if (image == null)
      image = image();
    Rectangle bounds = new Rectangle((int) (x - iwidth / 2), (int) (y - iheight / 2), iwidth, iheight);
    if (deco)
      bounds = addShadowBounds(bounds);

    return bounds;
  }

  @Override
  boolean hitTest(Point p) {
    if (!super.hitTest(p))
      return false;
    if (!selectCheckTransparentPixel)
      return true;
    if (image == null)
      image = image();
    final double tx = this.x - iwidth / 2.;
    final double ty = this.y - iheight / 2.;
    float px = (float) (p.x - tx + 0.5f);
    if (px < 0)
      px = 0;
    if (px >= iwidth)
      px = iwidth - 1;
    float py = (float) (p.y - ty + 0.5);
    if (py < 0)
      py = 0;
    if (py >= iheight)
      py = iheight - 1;
    final int pix = image.getRGB((int) px, (int) py);
    if ((pix & 0xff000000) == 0)
      return false;
    return true;
  }

  abstract BufferedImage image();

  boolean mouse(MouseEvent e) {
    return false;
  }

  @Override
  public void move(float dx, float dy) {
    final AffineTransform tr = AffineTransform.getTranslateInstance(dx, dy);
    transform(tr);
  }

  @Override
  public void paint(Graphics gr) {
    paint(gr, true);
  }

  @Override
  public void paint(Graphics gr, boolean deco) {
    final Graphics2D g = (Graphics2D) gr;

    if (image == null)
      image = image();

    if (deco)
      shadow(g);

    g.drawImage(image, (int) (x - iwidth / 2), (int) (y - iheight / 2), null);

    if (deco && DLParams.DEBUG) {
      final Rectangle b = getBounds();
      g.setColor(Color.darkGray);
      g.drawRect(b.x, b.y, b.width - 1, b.height - 1);
    }
  }

  @Override
  public void randomize() {
    super.randomize();
  };

  @Override
  void transform(AffineTransform tr) {
    final Point2D src = new Point2D.Float(x, y);
    final Point2D dst = tr.transform(src, null);
    x = (float) dst.getX();
    y = (float) dst.getY();
  }

  public boolean isThreaded() {
    return threaded;
  }

  public void setThreaded(boolean threaded) {
    this.threaded = threaded;
    stopAll();
    clear();
    run();
  }

  public Color getBackground() {
    return backgroundColor;
  }

  public void setBackground(Color c) {
    this.backgroundColor = c;
  }

  public void stopAll() {
    synchronized (threads) {
      DLThread[] tr = threads.toArray(new DLThread[threads.size()]);
      for (DLThread t : tr) {
        t.setStopped(true);
      }
    }
  }

  public void f() {
    if (image == null)
      image = image();
    f(image.createGraphics());
  }

  public void f(Graphics2D g) {
    f(g, null);
  }

  public abstract void f(Graphics2D g, DLThread t);

  public void run() {
    if (threaded)
      runThreaded();
  }

  public void runThreaded() {
    if (image == null)
      image = image();
    runThreaded(image.createGraphics());
  }

  public void runThreaded(final Graphics2D g) {
    DLRunnable run = new DLRunnable() {
      DLThread t;

      public void run() {
        f(g, t);
        synchronized (threads) {
          threads.remove(t);
        }
      }

      public DLThread getThread() {
        return t;
      }

      public void setThread(DLThread t) {
        this.t = t;
      }
    };
    DLThread t = new DLThread(run);
//    run.setThread(t);
    stopAll();
    synchronized (threads) {
      threads.add(t);
    }
    t.start();
  }

  public void save(File f) {
    DLUtil.Save(image, f);
  }

  void prepareForDisplay() {

  }

  public String getFilter() {
    return filterName;
  }

  public void setFilter(String f) {
    filterName = f;
    filter = getFilterFromString(f);
  }

  public String[] enumFilter() {
    return new String[] { "none", "edges", "blur" };
  }

  float filterStrength = 0f;

  public void setFilterStrength(float br) {
    filterStrength = br;
  }

  public float getFilterStrength() {
    return filterStrength;
  }

  public float[] rangeFilterStrength() {
    return new float[] { 0f, 1f };
  }

  private static BufferedImageOp getFilterFromString(String s) {
    switch (s) {

    case "null":
      return null;

    case "blur":
      BoxBlurFilter bf = new BoxBlurFilter();
      bf.setHRadius(5);
      bf.setVRadius(5);
      bf.setIterations(5);
      return bf;

    case "edge":
      EdgeFilter ef = new EdgeFilter();
      return ef;

    default:
      return null;
    }
  }

  void applyFilter() {
    if (filter == null)
      return;
    BufferedImage i = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    filter.filter(image, i);
    image = DLUtil.Merge(image, i, filterStrength, null);
  }

  void zoom() {
//    System.err.println("image " + image.getWidth() + " " + image.getHeight());
    BufferedImage i = new BufferedImage(iwidth, iheight, BufferedImage.TYPE_INT_ARGB);
    zoom(image, i);
    image = i;
  }

  BufferedImage zoom(BufferedImage src, BufferedImage dst) {
    if (src == null || dst == null)
      return null;

    AffineTransform tx = new AffineTransform();
    float sx = dst.getWidth() / src.getWidth();
    float sy = dst.getHeight() / src.getHeight();
    tx.scale(sx, sy);
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
    op.filter(src, dst);
    return dst;
  }

  public int getRes() {
    return res;
  }

  public void setRes(int res) {
    this.res = res;
    reset();
  }

  void reset() {
    image = new BufferedImage(iwidth, iheight, BufferedImage.TYPE_INT_ARGB);
  }
  
  public int[] rangeRes() {
    return new int[] { 1, 16 };
  }

}
