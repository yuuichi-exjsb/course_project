
/**
 * ゲームの進行自体を取り仕切るクラス
 * ・キーボード入力
 * ・ゲーム内の各オブジェクトの管理
 * ・ゲーム画面の描画
 * 
 * @author fukai
 */
import java.awt.*;
import java.awt.event.*;

public class Title extends Canvas implements KeyListener {
  // ■ フィールド変数
  Image        buf;   // 仮の画面としての buffer に使うオブジェクト(Image クラス)
  Graphics     buf_gc;// buffer のグラフィックスコンテキスト (gc) 用オブジェクト
  Dimension    d;     // アプレットの大きさを管理するオブジェクト
  private int  imgW, imgH; // キャンバスの大きさ

  private int mode      = -1; // -1: タイトル画面，-2: ゲームオーバー，1〜 ゲームステージ
  private int i, j,k;
  // ■ コンストラクタ
  /**
   * ゲームの初期設定
   */
  GameMaster(int imgW, int imgH) { // コンストラクタ （アプレット本体が引数． ゲームの初期化を行う）
    this.imgW = imgW; // 引数として取得した描画領域のサイズをローカルなプライベート変数に代入
    this.imgH = imgH; // 引数として取得した描画領域のサイズをローカルなプライベート変数に代入
    setSize(imgW, imgH); // 描画領域のサイズを設定

    addKeyListener(this);
  }

  // ■ メソッド
  // コンストラクタ内で createImage を行うと peer の関連で 
  // nullpointer exception が返ってくる問題を回避するために必要
  public void addNotify(){
    super.addNotify();
    buf = createImage(imgW, imgH); // buffer を画面と同サイズで作成
    buf_gc = buf.getGraphics();
  }

  // ■ メソッド (Canvas)
  public void paint(Graphics g) {
    buf_gc.setColor(Color.white);      // gc の色を白に
    buf_gc.fillRect(0, 0, imgW, imgH); // gc を使って白の四角を描く（背景の初期化）
    switch (mode) {
    case -2: // ゲームオーバー画面（スペースキーを押されたらタイトル画面）
      buf_gc.setColor(Color.black); // ゲームオーバー画面を描く
      buf_gc.drawString("      == Game over ==      ", imgW/2-80, imgH/2-20);
      buf_gc.drawString("       Hit SPACE key       ", imgW/2-80, imgH/2+20);
      
      break;
    case -1: // タイトル画面（スペースキーを押されたらゲーム開始）
      buf_gc.setColor(Color.black); // タイトル画面を描く
      //buf_gc.drawString(" == Shooting Game Title == ", imgW/2-80, imgH/2-20);
      buf_gc.drawString("Hit SPACE bar to start game", imgW/2-80, imgH/2+100);
      buf_gc.drawImage(titleImg,imgW/2-80-50, imgH/2-20-25,138*2,50*2,this);
      break;
    default: 
      buf_gc.drawImage(backScreen,0,0,this);
    // ゲーム中
      // *** ランダムに敵を生成 *** 
    g.drawImage(buf, 0, 0, this); // 表の画用紙に裏の画用紙 (buffer) の内容を貼り付ける
  }

  // ■ メソッド (Canvas)
  public void update(Graphics gc) { // repaint() に呼ばれる
    paint(gc);
  }

  // ■ メソッド (KeyListener)
  public void keyTyped(KeyEvent ke) {
  } // 今回は使わないが実装は必要

  public void keyPressed(KeyEvent ke) {
    int cd = ke.getKeyCode();
    switch (cd) {
    case KeyEvent.VK_SPACE: // スペースキーが押されたら
      ftr.sflag = true; // フラグを立てる
      if (this.mode != 1){
 this.mode++;
      }
      break;
    }
  }

  // ■ メソッド (KeyListener)
  public void keyReleased(KeyEvent ke) {
    int cd = ke.getKeyCode();
    switch (cd) {
    case KeyEvent.VK_SPACE: // スペースキーが離されたら
      break;
    }
  }
}