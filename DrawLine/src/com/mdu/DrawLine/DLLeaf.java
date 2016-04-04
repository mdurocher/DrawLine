package com.mdu.DrawLine;

import static com.mdu.DrawLine.DLParams.SAMPLE_PRECISION;
import static com.mdu.DrawLine.DLUtil.RangeRandom;
import static java.lang.Math.PI;

class DLLeaf extends DLCurve {
  float a = 0.01f;
  float scale = 10;

  DLLeaf(DLLeaf l) {
    super(l);
    a = l.a;
  }

  public DLLeaf(float x, float y) {
    super(x, y);
  }

  DLLeaf copy() {
    return new DLLeaf(this);
  }

  DLPath path() {
    DLPath p = null;
    for (float t = 0; t < 2 * PI; t += SAMPLE_PRECISION / 10) {
       float sint = DLUtil.Sin(t);
       float cost = DLUtil.Cos(t);

       float r = (1f + 0.9f * DLUtil.Cos(8 * t)) * (1f + 0.01f * DLUtil.Cos(24 * t)) * (0.9f + a * DLUtil.Cos(200 * t)) * (1f + sint);
       float x = scale * r * cost;
       float y = scale * r * sint;

      p = DLUtil.AddPoint(x, y, p);
    }
    p.closePath();
    transform(p);
    return p;
  }

  @Override
  public void randomize() {
    super.randomize();
    scale = RangeRandom(15, 30);
    a = RangeRandom(0.01f, 0.6f);
  }

}
