import java.awt.*;

public class EnemyB extends MovingObject{

  // コンストラクタ(初期値設定)
  EnemyB(int apWidth, int apHeight) {
    super(apWidth, apHeight); // スーパークラス(ObjBase)のコンストラクタの呼び出し
    w = 12;
    h = 12;
    hp = 0;  // 初期状態では全て死亡
  }
  // ○を描き更新するメソッド
  void move(Graphics buf, int apWidth, int apHeight) {
    buf.setColor(Color.red); // gc の色を黒に
    if (hp>0) { // もし生きていれば
      buf.fillOval(x - w, y - h, 5 * w, 5 * h); // gc を使って○を描く
      buf.drawString("強敵",x-w,y-h);
      x = x + dx; // 座標値の更新
      y = y + dy; // 座標値の更新
      if(x<0 || x>apWidth){//強敵は少ないため、跳ね返りを実装
        dx  = -1*dx;
      }
      if (y>apHeight+h)
 hp=0;
    }
  }
  void revive(int apWidth, int apHeight) { // 敵を新たに生成（再利用）
    x = (int)(Math.random()*(apWidth-2*w)+w);
    y = -h;
    dy = 1;
    if (x<apWidth/2)
      dx = (int)(Math.random()*10);
    else
      dx = -(int)(Math.random()*10);
    hp = 5;
  }

}