package com.mdu.gridit;

@SuppressWarnings("serial")
class TestPage2 extends GridItComponent {
  TestPage2() {
    super();
    content();
  }

  void title() {

    StringElement e;

    e = new StringElement();
    e.line_alignement = StringElement.LINE_CENTERED;
    e.text = "Minor Swing";
    e.newline = true;
    e.fontSize = 30;
    add(e);

    e = new StringElement();
    e.line_alignement = Element.LINE_CENTERED;
    e.text = "Django Reinhardt & Stephane Grappelli - 1937";
    e.newline = true;
    e.fontSize = 20;
    add(e);

  }

  void style() {
    StringElement e;
    e = new StringElement();
    e.line_alignement = Element.LINE_BEGIN;
    e.text = S.QuatreQuatre + " " + S.ClefDo + " " + S.Noire + "=160" + " Style Manouche";
//    e.newline = true;
    add(e);
    
    e = new StringElement();
    e.line_alignement = Element.LINE_END;
    e.text = "Tonalite " + Chord.C;
    e.newline = true;
    add(e);

  }

  void intro() {
    StringElement e;

    e = new StringElement();
    e.line_alignement = Element.LINE_BEGIN;
    e.text = "Intro";
    e.newline = true;
    add(e);

  }

  void grilleIntro() {
    GroupElement g;
    ChordsElement c;

    g = new GroupElement();
    g.line_alignement = Element.LINE_CENTERED;
    g.newline = true;

    c = new ChordsElement();
    c.chords[0] = Chord.A + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.D + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.A + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.D + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.A + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.D + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.E + S.Seventh;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = "(" + Chord.E + S.Seventh + ")";
    g.add(c);
    c.newline = true;
    add(g);
  }

  void chorus() {
    StringElement e;

    e = new StringElement();
    e.line_alignement = Element.LINE_BEGIN;
    e.text = "Chorus";
    e.newline = true;
    add(e);
  }

  void grilleChorus() {

    GroupElement g;
    ChordsElement c;

    g = new GroupElement();
    g.line_alignement = Element.LINE_CENTERED;
    g.newline = true;

    c = new ChordsElement();
    c.chords[0] = Chord.A + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = "%";
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.D + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = "%";
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.E + S.Seventh;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = "%";
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.A + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = "%";
    g.add(c);
    c.newline = true;

    c = new ChordsElement();
    c.chords[0] = Chord.D + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = "%";
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.A + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = "%";
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.B + S.Bemol + S.PetitSept;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.E + S.Seventh;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.A + S.Minor;
    g.add(c);

    c = new ChordsElement();
    c.chords[0] = Chord.E + S.Seventh;
    g.add(c);
    add(g);
  }

  void content() {

    title();
    style();
    intro();
    grilleIntro();
    chorus();
    grilleChorus();

  }

}
