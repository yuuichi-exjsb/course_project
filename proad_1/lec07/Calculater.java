
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calculater extends Frame implements ActionListener {
  // ■ フィールド変数
  Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btClr,btPlus,btMinus,btMul,btDiv,btEql;
  TextField txt1;
  String ss = "";//テキストボックスに表示する計算式を格納する変数
  String s_num= "";//マウスクリックで入力した文字を計算する関数に渡す変数
  
  public static void main(String [] args) {
    Calculater si = new Calculater(); 
  }

  // ■ コンストラクタ
  Calculater() {
    super("Calculater");
    setSize(300, 200); 

    //============================================================
    // まず、BorderLayout を設定し、NORTH,CENTER,SOUTHだけを使って
    // ウィンドウをおおまかに３つに分ける
    //============================================================
    setLayout(new BorderLayout()); // 先頭に this. が省略されている

    // NORTHにテキストフィールドを設定する
    txt1 = new TextField();
    add(txt1, BorderLayout.NORTH);

    //====================================================================
    // パネルを一枚作成し、CENTER にパネルをはめこむ内部をGridLayoutにする
    // パネルを GridLayout にし、パネル内部にボタンを９コ配置する
    //====================================================================
    Panel p_center = new Panel();
    p_center.setLayout(new GridLayout(4, 4));

    bt1 = new Button("1");  p_center.add(bt1);  // ボタン設定
    bt2 = new Button("2");  p_center.add(bt2);
    bt3 = new Button("3");  p_center.add(bt3);
    btPlus = new Button("+"); p_center.add(btPlus);
    bt4 = new Button("4");  p_center.add(bt4);
    bt5 = new Button("5");  p_center.add(bt5);
    bt6 = new Button("6");  p_center.add(bt6);
    btMinus = new Button("-"); p_center.add(btMinus);
    bt7 = new Button("7");  p_center.add(bt7);
    bt8 = new Button("8");  p_center.add(bt8);
    bt9 = new Button("9");  p_center.add(bt9);
    btMul = new Button("×"); p_center.add(btMul);
    bt0 = new Button("0"); p_center.add(bt0);
    btClr = new Button("Clear"); p_center.add(btClr);
    btDiv = new Button("÷"); p_center.add(btDiv);
    btEql = new Button("="); p_center.add(btEql);

    bt1.addActionListener(this);                // リスナ登録
    bt2.addActionListener(this);
    bt3.addActionListener(this);
    bt4.addActionListener(this);
    bt5.addActionListener(this);
    bt6.addActionListener(this);
    bt7.addActionListener(this);
    bt8.addActionListener(this);
    bt9.addActionListener(this);
    btPlus.addActionListener(this);
    btMinus.addActionListener(this);
    btMul.addActionListener(this);
    bt0.addActionListener(this);
    btClr.addActionListener(this);
    btDiv.addActionListener(this);
    btEql.addActionListener(this);

    add(p_center, BorderLayout.CENTER);         // パネルを CENTER に配置


    setVisible(true);
  }
  public void actionPerformed(ActionEvent e) {    // ボタンクリック処理
    Button btn = (Button)e.getSource();
    if (btn == btClr){
       ss = "";
       s_num = "";
      update(this.getGraphics());
      //clearボタンが入力されたときにテキストボックスを初期化と画面の更新
    }
    else if(btn == btEql){
      ss = calc(s_num);
      update(this.getGraphics());
      //イコールが押された場合にs_numを計算関数に投げて戻り値をssに代入
    }
    else if(btn == btPlus || btn == btMinus || btn == btMul || btn == btDiv ){
      ss = ss+ btn.getLabel();
      s_num = s_num+" "+btn.getLabel()+" ";
      update(this.getGraphics());
      /*下にあるcalc関数はs_numの文字列をスペースで分割するため、
      +,-などの算術演算子をクリックした場合は最初と最後にスペースを挿入する */
    }

    else{
      ss = ss + btn.getLabel();
        s_num = s_num+btn.getLabel();
        update(this.getGraphics());
        /*数字の場合はそのまま文字列に挿入する*/
    }

    System.out.println(s_num);
    //今の文字列をCUI上で確認する
  }

  public void paint(Graphics g) {
    txt1.setText(ss);
  }

  public String calc(String str){
    /*なのこの関数はteratailで質問があった「文字列から計算する方法」を参考に書き換えた
    URL:https://teratail.com/questions/246600
    なお、ここで自分で理解した関数の動作を記述する
    ・まずstrでは"1 + 23 - 4"などの文字列が入っている
    ・これを "1", "+","23"などのスペースで区切りで分割 
    ・この分割された文字列に対して->
    ・数字であればresultに代入
    ・算術演算子であれば、計算こ行う。
    ・最後にresultをstringにキャストして戻り値にする*/

    String ope = "";
    String sv1 = "";
    int result = 0;

    // スペース(" ")を区切りとしてトークン分割
      String[] tokens = str.split(" ", -1);
      //for (String token: tokens) {
      for (int i = 0; i < tokens.length; i++) {
        String token = tokens[i];

          if (sv1.isEmpty()) {
                // 1つ目の数文字列であれば、まだ計算しない
                sv1 = token;
            } else if (ope.isEmpty()) {
                // 演算子であれば、1つ目の数文字列は存在することになるが、まだ計算しない
                ope = token;
            } else {
                // 1つ目の数文字列、演算子は確定しているので、計算できる。
                int value1 = Integer.parseInt(sv1);
                int value2 = Integer.parseInt(token);
                if (ope.equals("+")) {
                    result = value1 + value2;
                } else if (ope.equals("-")) {
                    result = value1 - value2;
                } else if (ope.equals("×")) {
                    result = value1 * value2;
                } else if (ope.equals("÷")) {
                    result = value1 / value2;
                }

                ope = "";
                // 現在の答えを次の1つ目の数文字列として扱うことで、連続した計算を可能にする
                sv1 = String.valueOf(result);
            }
        }

        /*String s = String.format("calc2(): %s = %d", str, result);  
        System.out.println(s);*/
        Integer i = Integer.valueOf(result);
        String str_x = i.toString();
        return str_x;
  } 
}