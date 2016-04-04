package com.mdu.gridit;

@SuppressWarnings("serial")
class TestPage3 extends GridItComponent {
  
  TestPage3() {
    super();
    content();
  }

  void content() {
    StringElement e;
    GroupElement g;

    g = new GroupElement();
    g.newline = true;

    e = new StringElement();
    e.text = "Minor Swing";
    e.fontSize = 30;
    g.add(e);

    e = new StringElement();
    e.text = "Django Reinhardt & Stephane Grappelli - 1937";
    e.fontSize = 20;
    g.add(e);

    add(g);

    g = new GroupElement();
    g.newline = true;
    g.line_alignement = Element.LINE_END;
    
    e = new StringElement();
    e.text = "2 - Minor Swing";
    e.fontSize = 30;
    g.add(e);

    e = new StringElement();
    e.text = "2 - Django Reinhardt & Stephane Grappelli - 1937";
    e.fontSize = 20;
    g.add(e);

    add(g);
  }

}
