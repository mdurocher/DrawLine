package com.mdu.gridit;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class P {
  static boolean Debug = true;
  static Properties properties;

  static void LoadProperties() {
    properties = new Properties();
    File pf = new File("GridItProperties");
    try {
      InputStream is = new FileInputStream(pf);
      properties.load(is);
    } catch (Exception e) {
      Alert("Cannot load properties");
    }
  }

  static Color GetColorProperty(String p, String v) {
    String si = properties.getProperty(p, v);
    Color c = null;
    try {
      int i = Integer.decode(si);
      c = new Color(i);
    } catch (NumberFormatException e) {
      Error(e, si);
    }
    return c;
  }

  static int GetIntProperty(String p) {
    return GetIntProperty(p, null);
  }

  static int GetIntProperty(String p, String v) {
    String si = properties.getProperty(p, v);
    int i = 0;
    try {
      i = Integer.parseInt(si);
    } catch (NumberFormatException e) {
      Error(e, p);
      if (v != null)
        i = Integer.parseInt(v);
    }
    return i;
  }

  static float GetFloatProperty(String p) {
    return GetFloatProperty(p, null);
  }

  static float GetFloatProperty(String p, String d) {
    String pi = properties.getProperty(p, d);
    float v = 0;
    try {
      v = Float.parseFloat(pi);
    } catch (NumberFormatException e) {
      Error(e, p);
    }
    return v;
  }

  static HashMap<String, Font> FontProperties = new HashMap<>();

  static Font GetFontProperty(String p) {
    return GetFontProperty(p, null);
  }

  static Font GetFontProperty(String p, String v) {
    Font f = FontProperties.get(p);
    if (f != null)
      return f;
    String fo = properties.getProperty(p, v);
    URL url = GridIt.class.getResource(fo);
    Font font = null;
    try {
      font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(font);
    } catch (IOException e) {
      Error(e, fo);
    } catch (FontFormatException e) {
      Error(e, fo);
    }
    FontProperties.put(p, font);
    return font;
  }

  static void Error(Exception e, Object o) {
    System.err.println(e + " " + o);
  }

  static void Alert(String message) {
    System.err.println(message);
  }

}
