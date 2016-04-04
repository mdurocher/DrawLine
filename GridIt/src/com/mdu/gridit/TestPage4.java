package com.mdu.gridit;

import java.lang.reflect.Field;

@SuppressWarnings("serial")
class TestPage4 extends GridItComponent {

  TestPage4() {
    super();
    content();
  }

  void content() {
    int count = 0;
    for (Field f : S.class.getDeclaredFields()) {
      StringElement e = new StringElement();
      e.fontSize = 20;
      e.newline = (++count % 8) == 0;
      try {
        e.text = f.getName() + " " + f.get(null) + " ";
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      add(e);
    }

    ChordsElement ce;
    ce = new ChordsElement();
    ce.width = 50;
    ce.height = 50;
    ce.fontSize = 30;
    ce.chords[0] = Chord.C + S.SemiDiminue;
    ce.chordLayout = ChordsElement.ONE_CHORD;
    add(ce);

    ce = new ChordsElement();
    ce.width = 50;
    ce.height = 50;
    ce.fontSize = 30;
    ce.chordLayout = ChordsElement.TWO_CHORDS;
    ce.chords[0] = Chord.C + S.SemiDiminue;
    ce.chords[1] = Chord.C + S.Minor;
    add(ce);
    
    ce = new ChordsElement();
    ce.width = 100;
    ce.height = 100;
    ce.fontSize = 30;
    ce.chordLayout = ChordsElement.FOUR_CHORDS;
    ce.chords[0] = Chord.C + S.SemiDiminue;
    ce.chords[1] = Chord.C + S.Minor;
    ce.chords[2] = Chord.G + S.Minor + S.PetitSept;
    ce.chords[3] = Chord.E + S.Minor + S.PetitSept;
    add(ce);
/*
    ce = new ChordsElement();
    ce.scale = s;
    ce.width = 50;
    ce.height = 50;
    ce.fontSize = 30;
    ce.chordLayout = ChordsElement.THREE_CHORDS_A;
    ce.chords[0] = Chord.C + S.SemiDiminue;
    ce.chords[1] = Chord.C + S.Minor;
    ce.chords[2] = Chord.G + S.Minor + S.PetitSept;
    add(ce);

    ce = new ChordsElement();
    ce.scale = s;
    ce.width = 50;
    ce.height = 50;
    ce.fontSize = 30;
    ce.chordLayout = ChordsElement.THREE_CHORDS_B;
    ce.chords[0] = Chord.C + S.SemiDiminue;
    ce.chords[1] = Chord.C + S.Minor;
    ce.chords[2] = Chord.G + S.Minor + S.PetitSept;
    add(ce);
    
    ce = new ChordsElement();
    ce.scale = s;
    ce.width = 50;
    ce.height = 50;
    ce.fontSize = 30;
    ce.chordLayout = ChordsElement.FOUR_CHORDS;
    ce.chords[0] = Chord.C + S.SemiDiminue;
    ce.chords[1] = Chord.C + S.Minor;
    ce.chords[2] = Chord.G + S.Minor + S.PetitSept;
    ce.chords[3] = Chord.E + S.Minor + S.PetitSept;
    add(ce);
*/
  }

}
