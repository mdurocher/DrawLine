package com.mdu.DrawLine;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

@SuppressWarnings("serial")
public class DLPath extends Path2D.Float {
  DLPath() {
  }
  DLPath(int i) {
    super(i);
  }
  DLPath(GeneralPath p) {
    super(p);
  }
}
