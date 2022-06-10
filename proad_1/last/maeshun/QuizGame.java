import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.awt.*;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




//フレーム作成
public class QuizGame extends JFrame {
    //Image img = getToolkit().getImage("Img/umi.jpg"); 
  public static void main(String[] args){
    new QuizGame("NOBUクイズ", 500, 500);
     //int mode = -1;

  }



/*public void paint(Graphics gc){
    gc.drawImage(img, 0, 0, this);
}*/




  static private class Quiz {
    String question; //問題文
    String[] answers; //選択肢
    String correct; //正解
    Quiz(String question, String[] answers) {
      this(question, answers, 0);
    }
    Quiz(String question, String[] answers, int correctNo) {
      this.question = question;
      this.answers = shuffle(answers);
      this.correct = answers[correctNo];
    }
    //シャッフル
    private static String[] shuffle(String[] array) {
      List<String> a = Arrays.asList(array.clone());
      Collections.shuffle(a);
      return a.toArray(new String[array.length]);
    }

    
  }
  //クイズの内容
  static private Quiz[] quizArray = {
      new Quiz("ドラえもんに登場するスネ夫の苗字は？",
          new String[]{"骨川", "馬川", "スネ山"}),
      new Quiz("ドラえもんの好物はどら焼き、では妹のドラミちゃんの好物は？",
          new String[]{"メロンパン", "チョコレートパン"}),
      new Quiz("硬式テニスで０点の事を２文字で何と言うでしょう？",
          new String[]{"ラブ", "レイブ"}),
      new Quiz("パソコンのコンはコンピュータの略。ではパソの略は？",
          new String[]{"パーソナル", "ターミナル"}),
      new Quiz("ばいきんまんが愛用しているロボットの名前は？",
          new String[]{"だだんだん", "どどんどん"}),
  };

  private Deque<Quiz> quizStack = new ArrayDeque<Quiz>();
  private Quiz quiz; //出題中のクイズ

  private JLabel questionLabel;
  private AnswerPanel answerPanel;//回答ボタン
  private SouthPanel southPanel;//下部ボタン
  

  public QuizGame(String title, int width, int height) {
    super(title);
    setVisible(true);
    setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    JLabel 

    JPanel headerPanel = new JPanel();
    headerPanel.setBackground(Color.BLACK);
    headerPanel.setPreferredSize(new Dimension(500,50));
    add(headerPanel, BorderLayout.NORTH);

    questionLabel = new JLabel("");
    questionLabel.setHorizontalAlignment(JLabel.CENTER);
    add(questionLabel, BorderLayout.CENTER);

    answerPanel = new AnswerPanel(answer->decide(answer));

    southPanel = new SouthPanel(answerPanel, e->drawQuiz(), e->end());
    add(southPanel, BorderLayout.SOUTH);
    
    

    //クイズをシャッフルして山にする
    List<Quiz> quizList = Arrays.asList(quizArray.clone());
    Collections.shuffle(quizList);
    quizStack.addAll(quizList);

    

    drawQuiz();
    
    
      
  
    
  }

  
  
  //回答ボタン群
  static private class AnswerPanel extends JPanel {
    interface AnswerListener {
      void decide(String answer);
    }
    private AnswerListener answerListener;
    private final ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String answer = ((JButton)e.getSource()).getText();
        answerListener.decide(answer);
      }
    };
    AnswerPanel(AnswerListener answerListener) {
      this.answerListener = answerListener;
    }
    //回答数に応じてボタン配置
    void setAnswers(String[] answers) {
      removeAll();
      for(int i=0; i<answers.length; i++) {
        JButton button = new JButton(answers[i]);
        button.addActionListener(actionListener);
        add(button);
      }
    }
  }

  

  //下部の表示を進行状況に応じて切り替える
  static private class SouthPanel extends JPanel {
    enum Card {
      ANSWER, //回答
      NEXT,   //次の問題へ
      END     //全問終了
    };

    private CardLayout southCard;

    SouthPanel(JComponent answer, ActionListener nextActionListener, ActionListener endActionListener) {
      super(null);
      southCard = new CardLayout();
      setLayout(southCard);

      add(answer, Card.ANSWER.name());

      JButton nextButton = new JButton("次へ");
      nextButton.addActionListener(nextActionListener);
      add(nextButton, Card.NEXT.name());

      JButton endButton = new JButton("終了");
      endButton.addActionListener(endActionListener);
      add(endButton, Card.END.name());
    }

    void show(Card card) {
      southCard.show(this, card.name());
    }
  }

  private void end() {
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }

  //山からクイズを引き, 表示する.
  private void drawQuiz() {
    quiz = quizStack.pollFirst();

    southPanel.show(SouthPanel.Card.ANSWER);

    questionLabel.setText(quiz.question);
    questionLabel.setForeground(Color.red);
    answerPanel.setAnswers(quiz.answers);
  }

  //判定　ここで分岐をつくる
  private void decide(String answer) {

    /*int mode = -1;
    switch (mode) {
      case -2:
        questionLabel.setText("不正解");//分岐を作りたい
        questionLabel.setForeground(Color.DARK_GRAY);
        break;
    }
    if(answer.equals(quiz.correct)){ //正解のとき
      questionLabel.setText("正解");
      questionLabel.setForeground(Color.PINK);
    }else{ 
      mode = -2;
       
    }*/

    if(answer.equals(quiz.correct)){ //正解のとき
      questionLabel.setText("正解");
      questionLabel.setForeground(Color.PINK);
    }else{ 
      questionLabel.setText("GAME　OVER");
      questionLabel.setForeground(Color.DARK_GRAY);
      //JButton endButton = new JButton("終了");
        
      

      //System.exit(1);
    
      
    }


    

    southPanel.show(quizStack.isEmpty() ? SouthPanel.Card.END : SouthPanel.Card.NEXT);
  }



  
  

}