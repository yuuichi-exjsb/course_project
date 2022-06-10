/*@author Nishida */

import java.awt.*;
import java.awt.event.*;

public class GameMaster extends Frane implements KeyListener,ActionListener {
  Button bt0,bt1, bt2, bt3, bt4,bt5,bt6,bt7,bt8,bt9,btlft,btent,brht; // フレームに配置するボタンの宣言
  Panel  pnl,txt_pnl;              // ボタン配置用パネルの宣言
  Label lb1, lb2, lb3;
  String ss_turn,ss_num,ss_eatbite,ss_;  
  TextArea txt_turn,txt_num,txt_eatbite,txt_;  

  // ■ main メソッド（スタート地点）
  public static void main(String [] args) {
    GameMaster da = new GameMaster(); 
  }

  GameMaster(){
    super("NUMERON");
    this.setSize(480, 350); 

    txt_pnl = new Panel();       // Panel のオブジェクト（実体）を作成
    //mc = new MyCanvas(this); // mc のオブジェクト（実体）を作成
    txt_pnl.setLayout(new CardLayout());

    txt_turn = new TextArea(ss_turn);
    txt_pnl.add(txt_turn, "mycard1");
    txt_num = new TextArea(ss_num);
    txt_pnl.add(txt_num, "mycard2");
    txt_eatbite = new TextArea(ss_eatbite);
    txt_pnl.add(txt_eatbite, "mycard3");
    txt_ = new TextArea(ss_);
    txt_pnl.add(txt_, "mycard4");

    pnl = new Panel();
    pnl.setLayout(new GridLayout(3,4));  // ボタンを配置するため，９行１列のグリッドをパネル上にさらに作成
    btlft = new Button("←"); 
    btlft.addActionListener(this); 
    pnl.add(btlft);// ボタンを順に配置
    btent = new Button("Enter");      btent.addActionListener(this); pnl.add(btent);
    btrht = new Button("→"); btrht.addActionListener(this); pnl.add(btrht);
    bt0 = new Button("0"); bt0.addActionListener(this); pnl.add(bt0);// ボタンを順に配置
    bt1 = new Button("1");      bt1.addActionListener(this); pnl.add(bt1);
    bt2 = new Button("2"); bt2.addActionListener(this); pnl.add(bt2);
    bt3 = new Button("3"); bt3.addActionListener(this); pnl.add(bt3);
    bt4 = new Button("4");      bt4.addActionListener(this); pnl.add(bt4);
    bt5 = new Button("5");      bt5.addActionListener(this); pnl.add(bt5);
    bt6 = new Button("6");      bt6.addActionListener(this); pnl.add(bt6);
    bt7 = new Button("7");      bt7.addActionListener(this); pnl.add(bt7);
    bt8 = new Button("8");      bt8.addActionListener(this); pnl.add(bt8);
    bt9 = new Button("9");      bt9.addActionListener(this); pnl.add(bt9);

    setLayout(new BorderLayout());
    add(txt_pnl,BorderLayout.WEST);
    add(pnl,BorderLayout.EAST);

    this.setVisible(true); //可視化
  }
}