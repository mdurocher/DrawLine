package com.mdu.DrawLine;

import java.util.ArrayList;

import shapeFile.files.shp.shapeTypes.ShpPolygon;
import shapeFile.shapeFile.ShapeFile;

public class DLMap extends DLCurve {

  public DLMap() {
    super();
  }

  DLMap(DLMap src) {
    this();
  }

  public DLMap(float x, float y) {
    super(x, y);
  }

  DLMap copy() {
    return new DLMap(this);
  }

  float scale = 10f;

  DLPath path() {
    String resources = "resources";
    String basename = "ne_110m_admin_0_countries";
    ShapeFile s = null;
    
    DLPath path = new DLPath();
    
    try {
      s = new ShapeFile(resources, basename);
      s.read();
      ArrayList<ShpPolygon> shapes = s.getSHP_shape();

      for (ShpPolygon sp : shapes) {
        double[][] pts = sp.getPoints();
        for (int i = 0; i < sp.getNumberOfPoints(); i++) {
          double x = pts[i][0] * scale;
          double y = pts[i][1] * scale;
          if(i == 0)
            path.moveTo(x, y);
          else
            path.lineTo(x, y);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return path;
  }
}
