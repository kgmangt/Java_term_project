import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EnhanceWeapon extends JFrame {
    // 저장할 데이터
     String userName = "";
     int userAge = 0;
     int money = 0;
     int level = 0;

    public EnhanceWeapon() {
        setTitle("무기강화 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        showInputDialog();//실행 시 바로 입력창이 따로 띄우겠금, ai를 활용했습니다.

        // 메인 게임 레이아웃 구성
        setLayout(new FlowLayout());
        setLayout(null);
        setTitle("무기강화 게임 - 유저 = "+userName+ "님 방갑습니다!");
        add(new JLabel("안녕하세요, " + userName + "님!"));
        JButton btnStart = new JButton("게임 시작");
        btnStart.setBounds(650, 400, 200, 60); // x, y, 가로, 세로
        add(btnStart);
        

JTextArea txtArea = new JTextArea("안녕하세요! 기말 프로젝트로 이 게임을 만들어봤습니다.\n 간략하게 게임을 설명하자면은 무기를 강화해 비싸게 팔고\n 판 돈으로 또 강화를 시행하시면 됩니다! \n\n\n 30단계 까지 있으며 준비가 되셨으면 '게임시작' 버튼을 누르면 됩니다");//게임 설명창
txtArea.setBounds(500, 500, 500, 150);
txtArea.setEditable(false); // 수정 불가 설정
add(txtArea);
JLabel welcomeText = new JLabel(userName + "님 어서오세요!");
welcomeText.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 40));    
welcomeText.setForeground(Color.BLUE);
        welcomeText.setBounds(500, 800, 600, 50); // 하단에 배치
         add(welcomeText);
        setSize(1540, 1094);    
        setVisible(true);
           btnStart.addActionListener(e -> {
   getContentPane().removeAll(); //그냥 removeAll()을 쓰니깐 작동이 멈춰서 ai 도움을 받아 getContentPane()써야한다는 것을 알게 되었습니다.
    setupGameUI();               // 새 UI 추가
    getContentPane().revalidate();
    getContentPane().repaint();
});
    }
    private void setupGameUI() {
   JLabel lblLevel = new JLabel("현재 강화 레벨: " + level);
    lblLevel.setFont(new Font("Serif", Font.BOLD, 30));
    lblLevel.setBounds(650, 300, 300, 50); 
    
    JButton btnEnhance = new JButton("강화 시도!");
    btnEnhance.setBounds(650, 400, 200, 60); 
    
    // 강화 로직 예시
    btnEnhance.addActionListener(e -> {
        // 여기에 강화 로직 (성공/실패 확률 계산) 작성
        // 성공 시: level++; lblLevel.setText("현재 강화 레벨: " + level);
    });
    
    add(lblLevel);
    add(btnEnhance);
}

    private void showInputDialog() {//시작전 사용자의 정보를 얻기위해 만든 창입니다.
        JDialog UserInfo = new JDialog(this, "사용자 정보 입력", true);
        UserInfo.setLayout(new GridLayout(3, 2, 5, 5));

       JTextField tfName = new JTextField("홍길동", 20);
       JTextField tfAge = new JTextField("숫자로만 입력", 20);
       tfAge.addFocusListener(new FocusAdapter() {//클릭시 "숫자로만 입력"이 사라지게끔 하기위해서 인터넷도움을 받았습니다.
    @Override
    public void focusGained(FocusEvent e) {
        if (tfAge.getText().equals("숫자로만 입력")) {
            tfAge.setText(""); // 클릭하면 내용 초기화
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
                UserInfo.dispose(); // 입력 후 창 닫기
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(UserInfo, "나이를 숫자로 입력해주세요.");//나이칸에 숫자가 아닌 다른 것이 들어갈 경우를 대비해서 ai를 활용해서 수정했습니다.
            }
        });
        UserInfo.add(btnSave);
        UserInfo.setSize(250, 150);
        UserInfo.setLocationRelativeTo(null); // 화면 중앙에 띄우기
        UserInfo.setVisible(true); // 여기서 실행이 멈추고 입력이 완료될 때까지 대기
    }

    public static void main(String[] args) {
        new EnhanceWeapon();
    }
}