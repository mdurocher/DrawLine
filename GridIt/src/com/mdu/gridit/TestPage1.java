package com.mdu.gridit;

@SuppressWarnings("serial")
class TestPage1 extends GridItComponent {
  TestPage1() {
    super();
    content();
  }

  void content() {
    StringElement e;

    e = new StringElement();
    e.line_alignement = StringElement.LINE_CENTERED;
    e.text = StringElement.LINE_CENTERED;
    add(e);

    e = new StringElement();
    e.line_alignement = Element.LINE_BEGIN;
    e.text = Element.LINE_BEGIN;
    e.newline = true;
    add(e);

    e = new StringElement();
    e.line_alignement = StringElement.LINE_BEGIN;
    e.text = "G\u0429  \u044c \u00b9";
    e.newline = true;
    add(e);

    LineElement le;
    le = new LineElement();
    le.percent = 100;
    le.height = 10;
    le.newline = true;
    add(le);

    le = new LineElement();
    le.line_alignement = Element.LINE_CENTERED;
    le.percent = 50;
    le.height = 20;
    le.newline = true;
    add(le);

    e = new StringElement();
    e.line_alignement = Element.LINE_END;
    e.text = "1" + Element.LINE_END;
    e.newline = true;
    add(e);

    e = new StringElement();
    e.line_alignement = Element.LINE_FLOW;
    e.text = "One ";
    e.newline = false;
    e.fontSize = 20;
    add(e);

    e = new StringElement();
    e.line_alignement = StringElement.LINE_FLOW;
    e.text = "Two ";
    e.newline = false;
    add(e);

    e = new StringElement();
    e.line_alignement = StringElement.LINE_CENTERED;
    e.text = "Center";
    e.newline = false;
    add(e);

    e = new StringElement();
    e.line_alignement = StringElement.LINE_FLOW;
    e.text = "Three ";
    e.newline = false;
    add(e);

    BoxElement be = new BoxElement();
    be.line_alignement = StringElement.LINE_FLOW;
    be.newline = false;
    add(be);

    be = new BoxElement();
    be.line_alignement = StringElement.LINE_FLOW;
    be.newline = true;
    be.width = 10;
    be.height = 5;
    add(be);

    be = new BoxElement();
    be.line_alignement = StringElement.LINE_FLOW;
    be.newline = true;
    be.width = 50;
    be.height = 25;
    add(be);

//    ChordsElement ce = new ChordsElement();
//    add(ce);

    StringElement se = new StringElement();
    se.text = "\u0391"; //&#924;";
    add(se);

    se = new StringElement();
    se.text = "\u0392"; //&#924;";
    add(se);

    se = new StringElement();
    se.text = "\u0393"; //&#924;";
    add(se);

  }

}
