import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.TrayIcon.MessageType;
public class App extends JFrame{
    JFrame frame;
    JPanel p[] = new JPanel[3];
    JLabel l[] = new JLabel[2];
    JButton b[] = new JButton[2];
    JTextField t;
    JTextField tl[] = new JTextField[5];
    String cb[] = {
        "Choose Option","Auto Shutdown","Auto Restart","Only Notification","Study timer"
    };
    JComboBox c = new JComboBox(cb);
    boolean check;
    Thread t1;
    public App(String title){
        setComponent(title);
        setEvent();
    }
    public void setComponent(String title){
        frame = new JFrame(title);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400,200,600,300);
        frame.setLayout(new GridLayout(1,1));
        for(int i = 0;i < 3;i++){
            p[i] = new JPanel();
        }
        p[0].setLayout(new BoxLayout(p[0],BoxLayout.X_AXIS));
        p[1].setPreferredSize(new Dimension(400,0));
        p[2].setPreferredSize(new Dimension(200,0));
        t = new JTextField(20);
        t.setEnabled(false);
        l[0] = new JLabel("Options:");
        l[1] = new JLabel("Notification:");
        tl[0] = new JTextField(2);
        tl[0].setText("00");
        tl[0].setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        tl[1] = new JTextField(1);
        tl[1].setText(":");
        tl[1].setFocusable(false);
        tl[1].setBorder(new MatteBorder(0,0,0,0,Color.WHITE));
        tl[2] = new JTextField(2);
        tl[2].setText("00");
        tl[2].setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        tl[3] = new JTextField(1);
        tl[3].setText(":");
        tl[3].setFocusable(false);
        tl[3].setBorder(new MatteBorder(0,0,0,0,Color.WHITE));
        tl[4] = new JTextField(2);
        tl[4].setText("00");
        tl[4].setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        b[0] = new JButton("Start");
        b[1] = new JButton("Cancel");
        b[1].setEnabled(false);
        b[0].setFont(new Font("Arial",Font.BOLD,15));
        b[1].setFont(new Font("Arial",Font.BOLD,15));
        b[0].setBackground(new Color(129, 137, 178));
        b[0].setForeground(Color.WHITE);
        b[1].setBackground(new Color(129, 137, 178));
        b[1].setForeground(Color.WHITE);
        b[0].setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
        b[0].setFocusPainted(false);
        b[1].setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
        b[1].setFocusPainted(false);
        l[0].setFont(new Font("Arial",Font.BOLD,15));
        l[1].setFont(new Font("Arial",Font.BOLD,15));
        t.setFont(new Font("Arial",Font.BOLD,15));
        t.setBorder(null);
        p[1].setLayout(new FlowLayout(FlowLayout.CENTER,0,70));
        p[2].setLayout(new GridBagLayout());
        p[2].setBackground(new Color(129, 137, 178));
        l[0].setForeground(Color.WHITE);
        l[1].setForeground(Color.WHITE);
        c.setBackground(new Color(129, 100, 178));
        c.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12,12,12,12);
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        p[2].add(l[0], gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.ipady = 10;
        p[2].add(c, gbc);
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        p[2].add(l[1],gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.ipady = 10;
        p[2].add(t,gbc);
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        p[2].add(b[0], gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        p[2].add(b[1], gbc);
        p[1].setBackground(new Color(124, 92, 159));
        for(int i = 0;i < 5;i++){
            tl[i].setFont(new Font("Arial",Font.BOLD,50));
            tl[i].setForeground(Color.WHITE);
            tl[i].setBackground(new Color(124, 92, 159));
            tl[i].setHorizontalAlignment(JTextField.CENTER);
            p[1].add(tl[i]);
        }
        p[0].add(p[1]);
        p[0].add(p[2]);
        frame.add(p[0]);
        frame.setVisible(true);
    }
    public void setEvent(){
        b[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                boolean checktrue = checktrue();
                if(checktrue == true && (c.getSelectedIndex() == 1 || c.getSelectedIndex() == 2 || c.getSelectedIndex() == 3)){
                    b[0].setEnabled(false);
                    b[1].setEnabled(true);
                    c.setEnabled(false);
                    t.setEnabled(false);
                    for(int i = 0;i < 5;i++){
                        tl[i].setFocusable(false);
                    }
                    t1 = new Thread(){
                        @Override
                        public void run(){
                            try{
                                String pattern = "[0-9]";
                                int h = Integer.parseInt(tl[0].getText());
                                int m = Integer.parseInt(tl[2].getText());
                                int s = Integer.parseInt(tl[4].getText());
                                while(true){
                                    if(h == 0 && m == 0 && s == 0){
                                        finish();
                                        thuchienevent();
                                        break;
                                    }
                                    if(s == 0 && m != 0){
                                        s = 59;
                                        m = m - 1;
                                    }
                                    if(s == 0 && m == 0 && h != 0){
                                        s = 59;
                                        m = 59;
                                        h = h - 1;
                                    }
                                    s = s - 1;
                                    if(String.valueOf(h).matches(pattern)){
                                        tl[0].setText("0"+String.valueOf(h));
                                    }else{
                                        tl[0].setText(String.valueOf(h));
                                    }
                                    if(String.valueOf(m).matches(pattern)){
                                        tl[2].setText("0"+String.valueOf(m));
                                    }else{
                                        tl[2].setText(String.valueOf(m));
                                    }
                                    if(String.valueOf(s).matches(pattern)){
                                        tl[4].setText("0"+String.valueOf(s));
                                    }else{
                                        tl[4].setText(String.valueOf(s));
                                    }
                                    Thread.sleep(1000);
                                }
                            }catch(Exception ex){
                                tl[0].setText("00");
                                tl[2].setText("00");
                                tl[4].setText("00");
                                ex.printStackTrace();
                            }
                        }
                    };
                    t1.start();
                }else if(checktrue == true && c.getSelectedIndex() == 4){
                    String tbreak = JOptionPane.showInputDialog(frame, "Th???i gian b???n mu???n ngh??? m???i chu k??? h???c l??(ph??t): ");
                    try{
                        int time = Integer.parseInt(tbreak);
                        int ch = Integer.parseInt(tl[0].getText());
                        int cm = Integer.parseInt(tl[2].getText());
                        int cs = Integer.parseInt(tl[4].getText());
                        boolean th1 = (ch == 0 && cm == 0 && cs >= 30);
                        boolean th2 = (ch > 0 || cm > 0);
                        if(time <= 0 || th1 == false && th2 == false){
                            JOptionPane.showMessageDialog(frame, "Th???i gian h???c ph???i l???n h??n 30s, th???i gian ngh??? ph???i l???n h??n 1p");
                        }else{
                            b[0].setEnabled(false);
                            b[1].setEnabled(true);
                            c.setEnabled(false);
                            t.setEnabled(false);
                            for(int i = 0;i < 5;i++){
                                tl[i].setFocusable(false);
                            }
                            t1 = new Thread(){
                                @Override
                                public void run(){
                                    boolean done = true;
                                    firsthocevent();
                                    int hour = Integer.parseInt(tl[0].getText());
                                    int minute = Integer.parseInt(tl[2].getText());
                                    int second = Integer.parseInt(tl[4].getText());
                                    try{
                                        String pattern = "[0-9]";
                                        int h = Integer.parseInt(tl[0].getText());
                                        int m = Integer.parseInt(tl[2].getText());
                                        int s = Integer.parseInt(tl[4].getText());
                                        while(true){
                                            if(h == 0 && m == 0 && s == 0){
                                                if(done == true){
                                                    nghievent(time);
                                                    m = time;
                                                    s = 1;
                                                    done = false;
                                                }else{
                                                    hocevent();
                                                    h = hour;
                                                    m = minute;
                                                    s = second;
                                                    done = true;
                                                }
                                            }
                                            if(s == 0 && m != 0){
                                                s = 59;
                                                m = m - 1;
                                            }
                                            if(s == 0 && m == 0 && h != 0){
                                                s = 59;
                                                m = 59;
                                                h = h - 1;
                                            }
                                            s = s - 1;
                                            if(String.valueOf(h).matches(pattern)){
                                                tl[0].setText("0"+String.valueOf(h));
                                            }else{
                                                tl[0].setText(String.valueOf(h));
                                            }
                                            if(String.valueOf(m).matches(pattern)){
                                                tl[2].setText("0"+String.valueOf(m));
                                            }else{
                                                tl[2].setText(String.valueOf(m));
                                            }
                                            if(String.valueOf(s).matches(pattern)){
                                                tl[4].setText("0"+String.valueOf(s));
                                            }else{
                                                tl[4].setText(String.valueOf(s));
                                            }
                                            Thread.sleep(1000);
                                        }
                                    }catch(Exception ex){
                                        JOptionPane.showMessageDialog(frame,"D??? li???u kh??ng h???p l???"); 
                                        ex.printStackTrace();
                                    }
                                }
                            };
                            t1.start();
                        }
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(frame,"D??? li???u kh??ng h???p l???");
                        tl[0].setText("00");
                        tl[2].setText("00");
                        tl[4].setText("00"); 
                    }
                }else{
                    tl[0].setText("00");
                    tl[0].setFocusable(true);
                    tl[2].setText("00");
                    tl[2].setFocusable(true);
                    tl[4].setText("00");
                    tl[4].setFocusable(true);
                    b[0].setEnabled(true);
                    b[1].setEnabled(false);
                    c.setEnabled(true);
                    t.setEnabled(true);   
                }
            }
        });
        b[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                stopdh();
            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(c.getSelectedIndex() == 0 || c.getSelectedIndex() == 1 || c.getSelectedIndex() == 2 || c.getSelectedIndex() == 4){
                    t.setEnabled(false);
                    t.setText("");
                }else{
                    t.setEnabled(true);
                    t.setText("");
                }
            }
        });
    }
    public void stopdh(){
        t1.stop();
        tl[0].setText("00");
        tl[0].setFocusable(true);
        tl[2].setText("00");
        tl[2].setFocusable(true);
        tl[4].setText("00");
        tl[4].setFocusable(true);
        b[0].setEnabled(true);
        b[1].setEnabled(false);
        c.setEnabled(true);
        t.setEnabled(true);
    }
    public void finish(){
        tl[0].setText("00");
        tl[0].setFocusable(true);
        tl[2].setText("00");
        tl[2].setFocusable(true);
        tl[4].setText("00");
        tl[4].setFocusable(true);
        b[0].setEnabled(true);
        b[1].setEnabled(false);
        c.setEnabled(true);
        t.setEnabled(true);
    }
    public void nghievent(int time){
        try{
            String thongbao = "???? h???t gi??? h???c,...tranh th??? ngh??? "+time+"p tr?????c khi v??o l???i bu???i h???c nh??!!";
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("Untitled-2.png");
            TrayIcon trayIcon = new TrayIcon(image, "?????m");
            tray.add(trayIcon);
            Thread temp = new Thread(){
                @Override
                public void run(){
                    try{
                        trayIcon.displayMessage(thongbao, "Ch??c m???t ng??y t???t l??nh", MessageType.NONE);
                        Thread.sleep(10000);
                        tray.remove(trayIcon);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            };  
            temp.start();  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void hocevent(){
        try{
            String thongbao = "???? h???t gi??? ngh???,...h???c th??i!!";
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("Untitled-2.png");
            TrayIcon trayIcon = new TrayIcon(image, "?????m");
            tray.add(trayIcon);
            Thread temp = new Thread(){
                @Override
                public void run(){
                    try{
                        trayIcon.displayMessage(thongbao, "Ch??c m???t ng??y t???t l??nh", MessageType.NONE);
                        Thread.sleep(10000);
                        tray.remove(trayIcon);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            };  
            temp.start();  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void firsthocevent(){
        try{
            String thongbao = "Ch??c c??c b???n c?? m???t gi??? h???c t???p hi???u qu??? nh??";
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("Untitled-2.png");
            TrayIcon trayIcon = new TrayIcon(image, "?????m");
            tray.add(trayIcon);
            Thread temp = new Thread(){
                @Override
                public void run(){
                    try{
                        trayIcon.displayMessage(thongbao, "Ch??c m???t ng??y t???t l??nh", MessageType.NONE);
                        Thread.sleep(10000);
                        tray.remove(trayIcon);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            };  
            temp.start();  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void thuchienevent(){
        if(c.getSelectedIndex() == 1){
            try{
                Runtime run = Runtime.getRuntime();
                Process proc = run.exec("shutdown -s -t 1");
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(c.getSelectedIndex() == 2){
            try{
                Runtime run = Runtime.getRuntime();
                Process proc = run.exec("shutdown -r -t 1");
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            try{
                String thongbao = t.getText();
                SystemTray tray = SystemTray.getSystemTray();
                Image image = Toolkit.getDefaultToolkit().createImage("Untitled-2.png");
                TrayIcon trayIcon = new TrayIcon(image, "?????m");
                tray.add(trayIcon);
                Thread temp = new Thread(){
                    @Override
                    public void run(){
                        try{
                            trayIcon.displayMessage(thongbao, "Ch??c m???t ng??y t???t l??nh", MessageType.NONE);
                            Thread.sleep(10000);
                            tray.remove(trayIcon);
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                };  
                temp.start();                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public boolean checktrue(){
        String hour = tl[0].getText();
        String minute = tl[2].getText();
        String second = tl[4].getText();
        try{
            if(c.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(this, "B???n ch??a ch???n Options");
                return false;
            }
            if(hour.equals("") || minute.equals("") || second.equals("")){
                JOptionPane.showMessageDialog(this, "D??? li???u ?????ng h??? kh??ng ???????c ????? tr???ng");
                return false;
            }
            if(Integer.parseInt(hour) > 24 || Integer.parseInt(hour) < 0){
                JOptionPane.showMessageDialog(this, "D??? li???u gi??? kh??ng ???????c h??n 24 v?? l?? s??? d????ng");
                return false;
            }
            if(Integer.parseInt(minute) > 60 || Integer.parseInt(minute) < 0){
                JOptionPane.showMessageDialog(this, "D??? li???u ph??t kh??ng ???????c h??n 60 v?? l?? s??? d????ng");
                return false;
            }
            if(Integer.parseInt(second) > 60 || Integer.parseInt(second) < 0){
                JOptionPane.showMessageDialog(this, "D??? li???u gi??y kh??ng ???????c h??n 60 v?? l?? s??? d????ng");
                return false;
            }
            if(Integer.parseInt(hour) == 0 && Integer.parseInt(minute) == 0 && Integer.parseInt(second) == 0){
                int yn = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(this, "S??? gi???, s??? ph??t v?? s??? gi??y hi???n t???i ?????u l?? 00, Ch???c n??ng s??? th???c hi???n ngay l???p t???c\n B???n c?? mu???n ti???p t???c", "X??c nh???n", yn);
                if(result == JOptionPane.NO_OPTION || result == -1){
                    return false;
                }
            }
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "D??? li???u ?????ng h??? kh??ng h???p l???");
            return false;
        }
    }
    public static void main(String[] args) throws Exception {
        App f = new App("Count-down app");
    }
}
