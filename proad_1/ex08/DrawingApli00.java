/**
 * Simple Drawing Application
 * 簡単なお絵かきソフト
 * ・フリーハンド，直線，四角，楕円の描画機能
 * ・四角と楕円は左下方向のみ
 * ・色などの変更機能は無し
 *
 * @author fukai
 */
import java.awt.*;
import java.awt.event.*;

public class DrawingApli00 extends Frame implements ActionListener {
  // ■ フィールド変数
  Button bt1, bt2, bt3, bt4,bt5,bt6,bt7,btRed,btGre,btBlk,btBlu; // フレームに配置するボタンの宣言
  Panel  pnl;              // ボタン配置用パネルの宣言
  MyCanvas mc;               // 別途作成した MyCanvas クラス型の変数の宣言
  Label lb1, lb2, lb3;

  // ■ main メソッド（スタート地点）
  public static void main(String [] args) {
    DrawingApli00 da = new DrawingApli00(); 
  }

  // ■ コンストラクタ
  DrawingApli00() {
    super("Drawing Appli");
    this.setSize(480, 350); 

    pnl = new Panel();       // Panel のオブジェクト（実体）を作成
    mc = new MyCanvas(this); // mc のオブジェクト（実体）を作成

    this.setLayout(new BorderLayout(10, 10)); // レイアウト方法の指定
    this.add(pnl, BorderLayout.EAST);         // 右側に パネルを配置
    this.add(mc,  BorderLayout.CENTER);       // 左側に mc （キャンバス）を配置
                                         // BorerLayout の場合，West と East の幅は
                                         // 部品の大きさで決まる，Center は West と East の残り幅
    pnl.setLayout(new GridLayout(9,2));  // ボタンを配置するため，９行１列のグリッドをパネル上にさらに作成
    bt1 = new Button("Free Hand"); bt1.addActionListener(this); pnl.add(bt1);// ボタンを順に配置
    btGre = new Button("Green"); btGre.addActionListener(this); pnl.add(btGre);
    bt2 = new Button("Line");      bt2.addActionListener(this); pnl.add(bt2);
    btRed = new Button("Red"); btRed.addActionListener(this); pnl.add(btRed);
    bt3 = new Button("Rectangle"); bt3.addActionListener(this); pnl.add(bt3);
    btBlu = new Button("Blue"); btBlu.addActionListener(this); pnl.add(btBlu);
    bt4 = new Button("Oval"); bt4.addActionListener(this); pnl.add(bt4);
    btBlk =new Button("Black"); btBlk.addActionListener(this); pnl.add(btBlk);
    bt5 = new Button("FillRect");      bt5.addActionListener(this); pnl.add(bt5);
    bt6 = new Button("FillOval");      bt6.addActionListener(this); pnl.add(bt6);
    bt7 = new Button("Clear");      bt7.addActionListener(this); pnl.add(bt7);


    this.setVisible(true); //可視化
  }

  // ■ メソッド
  // ActionListener を実装しているため、例え内容が空でも必ず記述しなければならない
  public void actionPerformed(ActionEvent e){ // フレーム上で生じたイベントを e で取得
    if (e.getSource() == bt1)      // もしイベントが bt1 で生じたなら
      mc.mode_shape=1;                   // モードを１に
    else if (e.getSource() == bt2) // もしイベントが bt2 で生じたなら
      mc.mode_shape=2;                   // モードを２に
    else if (e.getSource() == bt3) // もしイベントが bt3 で生じたなら
      mc.mode_shape=3;                   // モードを３に
    else if (e.getSource() == bt4) // もしイベントが bt4 で生じたなら
      mc.mode_shape=4;                   // モードを４に
    else if (e.getSource() == bt5)
      mc.mode_shape = 5;
    else if (e.getSource() == bt6)
      mc.mode_shape = 6;
    else if (e.getSource() == bt7)
      mc.mode_shape = 7;

    else if(e.getSource() == btGre)mc.mode_color = 1;
    else if(e.getSource() == btRed)mc.mode_color = 2;
    else if(e.getSource() == btBlu)mc.mode_color = 3;
    else if(e.getSource() == btBlk)mc.mode_color = 4;
    
  }
}

/**
 * Extended Canvas class for DrawingApli
 * [各モードにおける処理内容]
 * 1: free hand 
 *      pressed -> set x, y,  dragged  -> drawline & call repaint()
 * 2: draw line 
 *      pressed -> set x, y,  released -> drawline & call repaint()
 * 3: rect
 *      pressed -> set x, y,  released -> calc w, h & call repaint()
 * 4: circle
 *      pressed -> set x, y,  released -> calc w, h & call repaint()
 * 
 * @author fukai
 */
class MyCanvas extends Canvas implements MouseListener, MouseMotionListener {
  // ■ フィールド変数
  int x, y;   // mouse pointer position
  int px, py; // preliminary position
  int ow, oh; // width and height of the object
  int tmpX,tmpY;
  int mode_shape,mode_color;   // drawing mode associated as below
  Image img = null;   // 仮の画用紙
  Graphics gc = null; // 仮の画用紙用のペン
  Dimension d; // キャンバスの大きさ取得用

  // ■ コンストラクタ
  MyCanvas(DrawingApli00 obj){
    mode_shape=0;                       // initial value 
    mode_color=1;
    this.setSize(500,400);        // キャンバスのサイズを指定
    addMouseListener(this);       // マウスのボタンクリックなどを監視するよう指定
    addMouseMotionListener(this); // マウスの動きを監視するよう指定
  }

  // ■ メソッド（オーバーライド）
  // フレームに何らかの更新が行われた時の処理
  public void update(Graphics g) {
    paint(g); // 下記の paint を呼び出す
  }

  // ■ メソッド（オーバーライド）
  public void paint(Graphics g) {
    d = getSize();   // キャンバスのサイズを取得
    if (img == null) // もし仮の画用紙の実体がまだ存在しなければ
      img = createImage(d.width, d.height); // 作成
    if (gc == null)  // もし仮の画用紙用のペン (GC) がまだ存在しなければ
      gc = img.getGraphics(); // 作成

    switch (mode_shape){
    case 1: // モードが１の場合
    colorChage();
      gc.drawLine(px, py, x, y); // 仮の画用紙に描画
      break;
    case 2: // モードが２の場合
    colorChage();
      gc.drawLine(px, py, x, y); // 仮の画用紙に描画
      break;
    case 3: // モードが３の場合
    colorChage();
      gc.drawRect(px, py, ow, oh); // 仮の画用紙に描画
      break;
    case 4: // モードが４の場合
      colorChage();
      gc.drawOval(px, py, ow, oh); // 仮の画用紙に描画
      break;
    case 5:
      colorChage();
      gc.fillRect(px, py, ow, oh);
      break;
    case 6:
      colorChage();
      gc.fillOval(px, py, ow, oh);
      break;
    case 7:
      gc.setColor(Color.white);
      gc.fillRect(0,0,d.width,d.height);
      break;
    }

    
    g.drawImage(img, 0, 0, this); // 仮の画用紙の内容を MyCanvas に描画
  }

  // ■ メソッド
  // 下記のマウス関連のメソッドは，MouseListener をインターフェースとして実装しているため
  // 例え使わなくても必ず実装しなければならない
  public void mouseClicked(MouseEvent e){}// 今回は使わないが、無いとコンパイルエラー
  public void mouseEntered(MouseEvent e){}// 今回は使わないが、無いとコンパイルエラー
  public void mouseExited(MouseEvent e){} // 今回は使わないが、無いとコンパイルエラー
  public void mousePressed(MouseEvent e){ // マウスボタンが押された時の処理
    switch (mode_shape){
    case 1: // mode が１の場合，次の内容を実行する
      x = e.getX();
      y = e.getY();
      break;
    case 2: // mode が２もしくは
    case 3: // ３もしくは
    case 4: // ４の場合，次の内容を実行する
    case 5:
    case 6:
    case 7:
      px = e.getX();
      py = e.getY();
    }
  }
  public void mouseReleased(MouseEvent e){ // マウスボタンが離された時の処理
    switch (mode_shape){
    case 2: // mode が２もしくは
    case 3: // ３もしくは
    case 4: // ４の場合，次の内容を実行する
    case 5:
    case 6:
    case 7:
      x = e.getX();
      y = e.getY();
      ow = x-px;
      oh = y-py;
      if(oh<0){
        oh = -oh;
        tmpY = py;
        py = tmpY-oh; 
      }
      repaint(); // 再描画
    }
  }

  // ■ メソッド
  // 下記のマウス関連のメソッドは，MouseMotionListener をインターフェースとして実装しているため
  // 例え使わなくても必ず実装しなければならない
  public void mouseDragged(MouseEvent e){ // マウスがドラッグされた時の処理
    switch (mode_shape){
    case 1: // mode が１の場合，次の内容を実行する
      px = x;
      py = y;
      x = e.getX();
      y = e.getY();
      repaint(); // 再描画
    }
  }
  public void mouseMoved(MouseEvent e){} // 今回は使わないが、無いとコンパイルエラー

  public void colorChage(){
      switch(mode_color){
              case 1: // モードが１の場合
            gc.setColor(Color.green); // 仮の画用紙に描画
            break;
          case 2: // モードが２の場合
            gc.setColor(Color.red); // 仮の画用紙に描画
            break;
          case 3: // モードが３の場合
            gc.setColor(Color.blue); // 仮の画用紙に描画
            break;
          case 4: // モードが４の場合
            gc.setColor(Color.black); // 仮の画用紙に描画
            break;
          
          }
  }
}