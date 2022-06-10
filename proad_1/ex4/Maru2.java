import java.awt.*;
class Maru2 { 
  /*■■■ フィールド変数部 ■■■*/
  int x1, y1,x2,y2;    // 位置
  int dx;
  int dy;  // 速度
  int color;   // 色
  int r;       // 半径
  int width;
  int height;

  /*■■■ コンストラクタ部 ■■■*/
  Maru2 () { // 引数が無い場合のデフォルト値
    r = 8;
    x1 = y1 = 200;
    x2 = y2 = 100;
    dx = 2;
    dy = 10; //重力加速度9.8を10にして考慮(int型なため)
    width = 50;
    height = 100;
  }
  Maru2 (int r, int x1, int y1,int x2, int y2, int dx, int dy,int height,int width){  // 引数がある場合（メソッドオーバーロード）
    this.r = r;
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.dx = dx;
    this.dy = dy;
    this.width = width;
    this.height = height;
  }

  /*■■■ メソッド部 ■■■*/
  void draw(Graphics g){ // サブ画用紙 (buf) の GC (buf_gc) が引数
    g.setColor(Color.blue); // 色を設定
    g.drawOval(x1-r, y1-r, 2*r, 2*r); // ○の描画
  }

  void shikaku_draw(Graphics g){//□の描画
    g.setColor(Color.orange);//カラーをブルーに設定
    g.fillRect(x2,y2,width,height);
  }

  void update (int width, int height){ // オブジェクトのパラメータの更新
    if (x1 >= width)  dx=dx*(-1);     // 右端に当たったときの処理
    if (x1 <= 0)      dx=dx*(-1);     // 左端に当たったときの処理
    if (y1 >= height) dy=dy*(-1);     // 下端に当たったときの処理
    if (y1 <= 0)      dy=dy*(-1);     // 上端に当たったときの処理
    if (x2 >= width)  dx=dx*(-1);     // 右端に当たったときの処理
    if (x2 <= 0)      dx=dx*(-1);     // 左端に当たったときの処理
    if (y2 >= height) dy=dy*(-1);     // 下端に当たったときの処理
    if (y2 <= 0)      dy=dy*(-1);     // 上端に当たったときの処理
    x1=x1+dx;                          // x 座標の更新
    y1=y1+dy;                          // y 座標の更新
    x2=x2-dx;                          // x 座標の更新
    y2=y2-dy;                          // y 座標の更新
  }

}