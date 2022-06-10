import java.awt.*;
abstract class Shape { 
  /*■■■ フィールド変数部 ■■■*/
  int x,y;    // 位置
  int dx;
  int dy;  // 速度

  Shape(){
    x = y = 10;
    dx = dy = 10;
  }

  Shape(int x,int y,int dx,int dy){
    this.x = x;
    this.y = y;
    this.dx = dx;
    this.dy = dy;
  }

  void update (int width, int height){ // オブジェクトのパラメータの更新
    if (x >= width)  dx=dx*(-1);     // 右端に当たったときの処理
    if (x <= 0)      dx=dx*(-1);     // 左端に当たったときの処理
    if (y >= height) dy=dy*(-1);     // 下端に当たったときの処理
    if (y <= 0)      dy=dy*(-1);     // 上端に当たったときの処理
    x=x+dx;                          // x 座標の更新
    y=y+dy;                          // y 座標の更新
  }

  abstract void draw(Graphics g);



  
}

class Circle extends Shape{

  int radious;
  Color co;


  Circle(){
    radious = 10;
    co = Color.red;
  }
  Circle(int x,int y,int radious,Color co){
    this.radious = radious;
    this.co = co;
    this.x = x;
    this.y = y;
  }

    /*■■■ メソッド部 ■■■*/
    void draw(Graphics g){ // サブ画用紙 (buf) の GC (buf_gc) が引数
      g.setColor(co); // 色を設定
      g.fillOval(x-radious, y-radious, 2*radious, 2*radious); // ○の描画
    }
  


}