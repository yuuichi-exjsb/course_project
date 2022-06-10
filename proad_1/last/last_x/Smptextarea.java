// ウィジェットの例
// === テキストエリア (TextArea) ===
// ボタンの動作を監視するため、ActionListener を実装する
// 
import java.awt.*;
import java.awt.event.*;

public class Smptextarea extends Frame implements ActionListener {
  // ■ フィールド変数
  TextArea txtar1;
  Button btn1, btn2, btn3;

  public static void main(String [] args) {
    Smptextarea sta = new Smptextarea(); 
  }

  // ■ コンストラクタ
  Smptextarea() {
    super("Widget Test");
    this.setSize(300, 200); 

    this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

    txtar1 = new TextArea("", 5, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
    add(txtar1);                   // テキストエリアの設定

    btn1 = new Button("置換する"); // ボタン1の設定
    btn1.addActionListener(this);
    add(btn1);
    btn2 = new Button("挿入する"); // ボタン2の設定
    btn2.addActionListener(this);
    add(btn2);
    btn3 = new Button("追加する"); // ボタン3の設定
    btn3.addActionListener(this);
    add(btn3);

    this.setVisible(true);
  }
  public void actionPerformed (ActionEvent e) {
    int stt = txtar1.getSelectionStart();
    int end = txtar1.getSelectionEnd();
    Button btn = (Button)e.getSource();

    if (btn == btn1) {             // 反転選択部分置換
      if (stt != end)
	txtar1.replaceRange("[置換]", stt, end);
    }
    else if (btn == btn2) {        // カーソル位置に挿入
      txtar1.insert("[挿入]", txtar1.getSelectionStart());
    }
    else if (btn == btn3) {        // 末尾に追加する
      txtar1.append("[追加]\n[改行して追加]");
    }
    txtar1.requestFocusInWindow(); // テキストエリアにフォーカスを与える
  }
}