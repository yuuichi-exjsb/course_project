// ウィジェットの例
// === スクロールバー (Scrollbar) ===
// スクロールバーの動作を監視するため、AdjustmentListener を実装する
// 
import java.awt.*;
import java.awt.event.*;

public class SmpScrollbar extends Frame implements AdjustmentListener, {
  // ■ フィールド変数
  Label lb1, lb2, lb3, lb4;
  Scrollbar sbar1, sbar2, sbar3, sbar4;
  String s1 = "", s2 = "";
  int red = 0, green = 0, blue = 0;
  Panel p_north = new Panel();

  // ■ main メソッド（プログラムのスタート地点）
  public static void main(String [] args) {
    SmpScrollbar stf = new SmpScrollbar(); 
  }

  // ■ コンストラクタ
  SmpScrollbar() {
    super("Widget Test");
    this.setSize(400, 400); 

    // 上部にスクロールバーを配置するためにボーダーレイアウトを利用
    this.setLayout(new BorderLayout());
    // 上部の領域 (NORTH) 内に８行のグリッドレイアウトをはめ込む
    p_north.setLayout(new GridLayout(8,1));

    lb1 = new Label("値", Label.CENTER); 
    p_north.add(lb1); // ラベルを配置

    sbar1 = new Scrollbar(Scrollbar.HORIZONTAL, 50, 10, 0, 200);
    sbar1.addAdjustmentListener(this); 
    p_north.add(sbar1); // スクロールバーを配置

    lb2 = new Label("赤", Label.CENTER); 
    p_north.add(lb2); // ラベルを配置

    sbar2 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
    sbar2.addAdjustmentListener(this); 
    p_north.add(sbar2); // スクロールバーを配置

    lb3 = new Label("緑", Label.CENTER); 
    p_north.add(lb3); // ラベルを配置

    sbar3 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
    sbar3.addAdjustmentListener(this); 
    p_north.add(sbar3); // スクロールバーを配置

    lb4 = new Label("青", Label.CENTER); 
    p_north.add(lb4); // ラベルを配置

    sbar4 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
    sbar4.addAdjustmentListener(this); 
    p_north.add(sbar4); // スクロールバーを配置

    this.add(p_north, BorderLayout.NORTH);
    
    this.setVisible(true);
  }

  // ■ メソッド
  // スクロールバーのいずれかが動かされたら呼び出される
  public void adjustmentValueChanged(AdjustmentEvent e) {
    Scrollbar sbar = (Scrollbar)e.getAdjustable();
    if (sbar == sbar1) s1 = "スクロールバー1の値は:" + sbar1.getValue();
    if (sbar == sbar2 || sbar == sbar3 ||sbar == sbar4) {
      red   = sbar2.getValue();
      green = sbar3.getValue();
      blue  = sbar4.getValue();
      s2 = "Red:" + red + " Green:" + green + " Blue:" + blue;
    }
    repaint();
  }

  // このメソッドは，上位の Frame クラスのメソッドをオーバーライド（上書き）
  public void paint(Graphics g) {
    g.setColor(new Color(red, green, blue)); // ペンの色設定
    // 色つきの四角を描く
    g.fillRect(10, 100, getSize().width-20, getSize().height-160);

    g.setColor(Color.BLACK);       // ペンの色設定
    g.drawString(s1, 10, getSize().height-20); // 文字列表示1
    g.drawString(s2, 10, getSize().height-35); // 文字列表示2
  }
}