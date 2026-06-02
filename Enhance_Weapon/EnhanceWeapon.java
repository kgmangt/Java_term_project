import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

 
public class EnhanceWeapon extends JFrame {
    // 저장할 데이터
    String[] WeaponName = {" level 0 : 야구빠따", " level 1 : 나무 검","level 2 : 철 검","level 3 : 낡고 오래된 엽총",
    "level 4 : 기관단총","level 5 : 기관단총 V2 ","level 6 : 81mm km29a1","level 7 : 시즈 탱크", "level 8 : TANK V2","level 9 : 중거리 지대공 미사일"
    ,"level 10 : 이순신 함","level 11 : 히페리온 ","level 12 : 캐리어", "<html>축하드립니다!! 클리어 !!<font color = 'blue'>길동이"

    };
    String userName = "";
    int userAge = 0;
    int money = 10000; 
    int level = 0;
    int[] EnhancePrice = { 100, 100, 100, 300, 500,
                                   1000, 1500, 10000, 20000, 50000,
                                   70000, 1000000,999999999,041206
                                  };
            int[] SellingPrice = { 0, 50, 150, 400, 800,
                                   2200, 5000,35000,100000, 250000 ,
                                   490000, 10000000, 999999999,8282
                                 };  
            int[] rates = {100, 98, 95, 90, 80,
                            70, 60, 50, 40, 30,
                            15, 5 , 25, 0
                          }; // 각 단계별 확률 / 100= 100% 98 = 98% 12단계에서는 확률 업을 아무리 해도 25퍼센트 고정으로 해놨습니다.
 
    // 상단 정보 레이블
    JLabel lblInfo;
 
    public EnhanceWeapon() {
        setTitle("무기강화 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        showInputDialog(); // 실행 시 바로 입력창이 따로 띄우겠금, ai를 활용했습니다.
 
        // 메인 게임 레이아웃 구성
        setLayout(null);
        setTitle("무기강화 게임 - 유저 = " + userName + "님 방갑습니다!");
 
        add(new JLabel("안녕하세요, " + userName + "님!"));
 
        JButton btnStart = new JButton("게임 시작");
        btnStart.setBounds(650, 400, 200, 60);
        add(btnStart);
 
        JTextArea txtArea = new JTextArea(
            "안녕하세요! 기말 프로젝트로 이 게임을 만들어봤습니다.\n" +
            " 간략하게 게임을 설명하자면은 무기를 강화해 비싸게 팔고\n" +
            " 판 돈으로 또 강화를 시행하시면 됩니다! \n\n\n" +
            " 12단계 까지 있으며 준비가 되셨으면 '게임시작' 버튼을 누르면 됩니다");
        txtArea.setBounds(500, 500, 500, 150);
        txtArea.setEditable(false);
        add(txtArea);
 
        JLabel welcomeText = new JLabel(userName + "님 어서오세요!");
        welcomeText.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 40));
        welcomeText.setForeground(Color.BLUE);
        welcomeText.setBounds(500, 300, 600, 50);
        add(welcomeText);
 
        setSize(1540, 1094);
 
        btnStart.addActionListener(e -> {
            getContentPane().removeAll(); // ai 도움을 받아 getContentPane()써야한다는 것을 알게 되었습니다.
            setupGameUI();
            getContentPane().revalidate();
            getContentPane().repaint();
        });
 
        setVisible(true);
    }
 
    private void setupGameUI() {
      
        //  상단: 유저 정보 / 돈 / 레벨  
        lblInfo = new JLabel("유저: " + userName + "   |   골드: " + money + "   |   강화단계: +" + level);
        lblInfo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblInfo.setBounds(20, 10, 900, 40);
        add(lblInfo);
 
        //중앙 무기 이미지 자리
        JLabel imgLabel = new JLabel("[ 무기 이미지 ]", SwingConstants.CENTER);
        imgLabel.setBounds(200, 60, 900, 700);
        imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        Image img = new ImageIcon("image/level"+level+".png").getImage();
        Image resized = img.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(resized)); // 사진 삽입부분을 ai 도움 받았습니다.
        add(imgLabel);
        JLabel lblWeaponName = new JLabel(WeaponName[level], SwingConstants.CENTER);
        lblWeaponName.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        lblWeaponName.setBounds(200, 760, 900, 40); // imgLabel 바로 아래
        add(lblWeaponName);

        //  오른쪽: 현재 강화 레벨 표시 
        JLabel lblLevel = new JLabel("현재 단계: +" + level);
        lblLevel.setFont(new Font("Serif", Font.BOLD, 30));
        lblLevel.setBounds(1150, 300, 350, 50);
        add(lblLevel);
        JLabel PercentLevel = new JLabel("현재 강화 확률: "+rates[level]+"%");
        PercentLevel.setFont(new Font("Serif", Font.BOLD, 30));
        PercentLevel.setBounds(1150, 200, 350, 50);
        add(PercentLevel);

        //  오른쪽: 밑에 뜨는 문구
        JLabel lblResult = new JLabel(" 강화 비용 : "+EnhancePrice[level]+" 판매 시 : "+SellingPrice[level]);
        lblResult.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        lblResult.setBounds(1150, 370, 350, 40);
        add(lblResult);
        JButton Upgrade_Percent = new JButton("확률 업(500,000원)");
        Upgrade_Percent.setBounds(650, 850, 200, 60);
        add(Upgrade_Percent);
        Upgrade_Percent.addActionListener(e -> { //확률업 버튼 누를시
          if(level == 13) {
            Upgrade_Percent.setEnabled(false); // 13레벨에 도달하면 확률업 버튼을 회색으로 변하게 하여 못 누르게 바꾸었습니다.
           lblResult.setText("게임 클리어!!");
            return;
          }
        if(money < 500000) {
          lblResult.setText("<html><font color = 'red'>돈이 부족합니다.</font></html>");
          return ;
        }
        else{
          money -= 500000;
          for(int i = 0 ; i < rates.length -2 ; i++){
            rates[i] += 2;
          }
            lblLevel.setText("현재 강화 레벨: +" + level);
            lblInfo.setText(("유저: " + userName + "   |   골드: " + money + "   |   현재 단계: +" + level));
            PercentLevel.setText(("현재 강화 확률: "+rates[level]+"%"));
            Image newImg = new ImageIcon("image/level" + level + ".png").getImage();
          newImg = newImg.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
          imgLabel.setIcon(new ImageIcon(newImg));
            lblResult.setText("<html> 강화 비용 : " + EnhancePrice[level] + 
                  "  판매 시 : <font color='red'>" + SellingPrice[level] + "</font></html>");
        }
        });




        // 오른쪽: 강화 버튼 
        JButton btnEnhance = new JButton("강화 시도!");
        btnEnhance.setBounds(1150, 430, 200, 60);
        add(btnEnhance);
        JButton btnSelling = new JButton("판매 하기!");
        btnSelling.setBounds(1150, 530, 200, 60);
        add(btnSelling);
        btnSelling.addActionListener(e -> { //판매 버튼 클릭 시 
           if(level == 13) {
           btnSelling.setEnabled(false); // 13레벨에 도달하면 판매 버튼을 회색으로 변하게 하여 못 누르게 바꾸었습니다.
           lblResult.setText("게임 클리어!!");
           return;
          }
          money += SellingPrice[level];
          level = 0;
           lblLevel.setText("현재 강화 레벨: +" + level);
           PercentLevel.setText(("현재 강화 확률: "+rates[level]+"%"));
           Image newImg = new ImageIcon("image/level" + level + ".png").getImage();
           newImg = newImg.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
           lblInfo.setText(("유저: " + userName + "   |   골드: " + money + "   |   현재 단계: +" + level));
          imgLabel.setIcon(new ImageIcon(newImg));
            lblResult.setText("<html> 강화 비용 : " + EnhancePrice[level] + 
                  "  판매 시 : <font color='red'>" + SellingPrice[level] + "</font></html>");
                  //ai도움을 받아 label에는 html이 적용이 된다는 것을 알게되었고 특정부분만 색을 입힐 수 있게 만들어 보았습니다.
        });
        btnEnhance.addActionListener(e -> { // 강화하기 버튼 클릭 시
           if(level == 13) {
           btnEnhance.setEnabled(false); // 13레벨에 도달하면 강화 버튼을 회색으로 변하게 하여 못 누르게 바꾸었습니다.
           lblResult.setText("게임 클리어!!");
           return;
          }
                                if(money<EnhancePrice[level]){
                                   lblLevel.setText("돈이 부족합니다!");
                                  return;
                                }   
                                money -= EnhancePrice [level]; 
            int random = (int)(Math.random() * 100) + 1;//확률 계산법을 인터넷도움을 받아서 작성하였습니다.
            if(random < rates[level]) level++;
            else{ level = 0;}
            lblLevel.setText("현재 강화 레벨: +" + level);
            lblInfo.setText(("유저: " + userName + "   |   골드: " + money + "   |   강화단계: +" + level));
            PercentLevel.setText(("현재 강화 확률: "+rates[level]+"%"));
            lblWeaponName.setText(WeaponName[level]);
          Image newImg = new ImageIcon("image/level" + level + ".png").getImage();
          newImg = newImg.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
          imgLabel.setIcon(new ImageIcon(newImg));
        lblResult.setText("<html> 강화 비용 : " + EnhancePrice[level] + 
                  "  판매 시 : <font color='red'>" + SellingPrice[level] + "</font></html>");
          });//ai도움을 받아 label에는 html이 적용이 된다는 것을 알게되었고 특정부분만 색을 입힐 수 있게 만들어 보았습니다.
    }
 
    private void showInputDialog() { // 시작전 사용자의 정보를 얻기위해 만든 창입니다.
        JDialog UserInfo = new JDialog(this, "사용자 정보 입력", true);
        UserInfo.setLayout(new GridLayout(3, 2, 5, 5));
 
        JTextField tfName = new JTextField("홍길동", 20);
        JTextField tfAge = new JTextField("숫자로만 입력", 20);
        tfAge.addFocusListener(new FocusAdapter() { // 클릭시 "숫자로만 입력"이 사라지게끔 하기위해서 인터넷도움을 받았습니다.
            @Override
            public void focusGained(FocusEvent e) {
                if (tfAge.getText().equals("숫자로만 입력")) {
                    tfAge.setText("");
                }
            }
        });
        
        UserInfo.add(new JLabel("이름 "));
        UserInfo.add(tfName);
        UserInfo.add(new JLabel("나이 "));
        UserInfo.add(tfAge);
 
        JButton btnSave = new JButton("저장");
        btnSave.addActionListener(e -> {
            userName = tfName.getText();
            try {
                userAge = Integer.parseInt(tfAge.getText());
                UserInfo.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(UserInfo, "나이를 숫자로 입력해주세요."); // 나이칸에 숫자가 아닌 다른 것이 들어갈 경우를 대비해서 ai를 활용해서 수정했습니다.
            }
        });
 
        UserInfo.add(btnSave);
        UserInfo.setSize(250, 150);
        UserInfo.setLocationRelativeTo(null);
        UserInfo.setVisible(true);
    }
 
    public static void main(String[] args) {
        new EnhanceWeapon();
        
    }
}
