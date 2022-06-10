/**
 * Canvas 内の動く物体を全て統括して制御するクラス
 *
 * @author fukai
 */
import java.awt.*;
import java.util.Arrays;
class GameMaster extends Canvas {
  // ■ フィールド変数
  Image buf;       // サブ画用紙（バッファ＝一時的に蓄えておく場所）
  Graphics buf_gc; // サブ画用紙の GC (graphics context)
  Dimension d;     // キャンバスの大きさ取得用
  int imgW, imgH;

  int ball_num = 15; 

  Shape[] shape = new Shape[ball_num];
  int x[] = new int [ball_num];
  int y[] = new int [ball_num];
  int rad[] = new int [ball_num];
  int dx[] = new int [ball_num];
  int dy[] = new int [ball_num];
  int r = 10;
  
  

  // ■ コンストラクタ
  GameMaster(int imgW, int imgH){ // Canvas を置く親 Frame の大きさを取得
    this.imgW=imgW; // キャンバスの幅を指定
    this.imgH=imgH; // キャンバスの高さを指定
    setSize(imgW, imgH); // キャンバスのサイズを設定
    System.out.println(this.imgW + " "+ this.imgH);

    for(int i=0;i<ball_num;i++){
      x[i] = (int) (Math.random()*(imgW-2*r)+r);  // 初期値を画面内でランダムに設定
      y[i] = (int) (Math.random()*(imgW-2*r)+r);  // 初期値を画面内でランダムに設定
      rad[i] = (int)Math.round(Math.random()*20);  //速度を1~10で設定
      dx[i] = (int) Math.round(Math.random()*20-10);// 初期値を-10～10 内でランダムに設定
      dy[i] = (int) Math.round(Math.random()*20-10);// 初期値を-10～10 内でランダムに設定

    }
    for(int i=0;i<ball_num;i++){
      shape[i] = new Circle(x[i],y[i],rad[i],Color.red);
    }

  }

  // ■ メソッド
  // コンストラクタ内で createImage を行うと peer の関連で 
  // nullpointer exception が返ってくる問題を回避するために必要
  public void addNotify(){
    super.addNotify();
    buf = createImage(imgW, imgH); // バッファ (Image クラス) を画面と同サイズで作成 (new)
    buf_gc = buf.getGraphics(); // buf の GC (Graphics クラス）を取得
  }

  // ■ メソッド
  public void paint(Graphics gc) { // 引数の gc はメイン画用紙の GC
    d = getSize(); // Frame のサイズを取得

    // 1. まずサブ画用紙 img に全て描画する
    buf_gc.setColor(Color.black);     // gc の色を白に
    buf_gc.fillRect(0,0,d.width,d.height); // gc を使って白の四角を全体に描いて初期化

    for(int i=0;i<ball_num;i++){
      shape[i].draw(buf_gc);
    }
    
    // 2. 描き終わった仮の画用紙 (buf) の内容をメイン画用紙 (this) に貼り付ける
    // 注意）主の画用紙は，Canvas クラスを継承している GameMaster クラスのオブジェクトが
    //       作成された時点でとして作成されている．this キーワードでアクセス
    gc.drawImage(buf, 0, 0, this);

    // 3. 最後に各パラメータの更新処理
    for(int i=0;i<ball_num;i++){
      shape[i].update(d.width, d.height);
    }
  }

  // ■ メソッド
  public void update(Graphics gc) { // repaint() に呼ばれる
    paint(gc);
  }
}