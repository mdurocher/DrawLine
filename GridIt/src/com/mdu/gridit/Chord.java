package com.mdu.gridit;

public class Chord {
  static final String A = "A";
  static final String B = "B";
  static final String C = "C";
  static final String D = "D";
  static final String E = "E";
  static final String F = "F";
  static final String G = "G";
  
  String root = A;
  String major_minor = "";
  String alteration = "";
  String dieze_bemol = "";
  String bass = "";

  public String toString() {
    return root + dieze_bemol + major_minor + alteration + ("".equals(bass) ? "" : "/" + bass);
  }

}
