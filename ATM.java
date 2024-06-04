import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;

// used https://stackoverflow.com/questions/20473325/gridlayout-java-center-alignment
// used https://stackoverflow.com/questions/2545214/how-to-set-a-transparent-background-of-jpanel#comment98942748_2545642
public class ATM{
    public static Dimension d = new Dimension(1100,700);
    public static JFrame f = new JFrame("ATM");
    public static int remained = 5530000;
    public static JPanel mainPanel = new JPanel();
    public static JPanel temp = new JPanel();
    // define menus
    public static JPanel langMenu = LangMenu();
    public static JPanel farsiMainMenu = FarsiMainMenu();
    public static JPanel farsiEnterPass = FarsiEnterPass();
    public static JPanel farsiChangePass = FarsiChangePass();
    public static JPanel farsiRemain = FarsiRemain();
    public static JPanel farsiResult = FarsiResult();
    public static JPanel farsiWithDraw = FarsiWithDraw();
    public static JPanel farsiTransfer = FarsiTransfer();
    public static JButton _return(JPanel panel){
        JButton button;
        JPanel destination = new JPanel();
        button = new JButton("بازگشت");
        button.setBounds(d.width*13/14,d.height*13/14-38,d.width/14,d.height/14);
        // define destination
        if(panel == langMenu || panel == farsiEnterPass)button.setVisible(false);
        else{
            button.setVisible(true);
            if (panel == farsiMainMenu)destination = langMenu;
            if (panel == farsiRemain || panel == farsiTransfer
                    || panel == farsiWithDraw || panel == farsiChangePass)destination = farsiMainMenu;
            if (panel == farsiResult)destination = temp;
        }
        //add action
        JPanel finalDestination = destination;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMainPanel(finalDestination);
            }
        });
        return button;
    }
    public static JButton ret = _return(langMenu);
    public static void main(String[] args) {
        //create frame
        f.setSize(d);
        f.setLayout(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane().setBackground( Color.CYAN );

        //default mainpanel
        mainPanel = langMenu;
        //add
        f.add(mainPanel);
        f.add(ret);
        f.setVisible(true);//making the frame visible
    }
    public static JPanel LangMenu(){
        //panel create
        JPanel panel = new JPanel(new GridLayout(1,4,0,0));
        panel.setBounds(0,d.height*3/8,d.width,d.height/4);
        // button defining
        JButton englishB, farsiB;
        JLabel englishL, farsiL;
        englishB = new JButton("English");
        farsiB = new JButton("فارسی");
        //button action
        farsiB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMainPanel(farsiEnterPass);
            }
        });
        //label defining
        englishL = new JLabel("Choose Language");
        farsiL = new JLabel("زبان خود را انتخاب کنید.");
        farsiL.setHorizontalAlignment(JLabel.CENTER);
        englishL.setHorizontalAlignment(JLabel.CENTER);
        //adding
        panel.add(englishB);
        panel.add(englishL);
        panel.add(farsiL);
        panel.add(farsiB);
        panel.setBackground(Color.cyan);
        return panel;
    }
    public static JPanel FarsiMainMenu(){
        //panel create
        JPanel panel = new JPanel(new GridLayout(2,2,d.width/3,d.height/15));
        panel.setBounds(0,d.height*3/8,d.width,d.height/4);
        // button defining
        JButton withdraw, pass, transfer, remain;
        withdraw = new JButton("برداشت وجه");
        pass = new JButton("تغییر رمز");
        transfer = new JButton("کارت به کارت");
        remain = new JButton("اعلام موجودی");
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == withdraw)setMainPanel(farsiWithDraw);
                else if(e.getSource() == pass)setMainPanel(farsiChangePass);
                else if(e.getSource() == transfer)setMainPanel(farsiTransfer);
                else if(e.getSource() == remain)setMainPanel(farsiRemain);
                else if( ((JButton)(e.getSource())).getText() == "فارسی")setMainPanel(farsiMainMenu);
            }
        };
        //button action
        withdraw.addActionListener(al);
        pass.addActionListener(al);
        transfer.addActionListener(al);
        remain.addActionListener(al);
        //adding
        panel.add(withdraw);
        panel.add(pass);
        panel.add(transfer);
        panel.add(remain);
        panel.setOpaque(false);
        return panel;
    }
    public static JPanel FarsiEnterPass(){
        //panel create
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(d.width,d.height-38);// my panel didnt fit with given dimension so i added a -38 which was found with testing
        // button defining
        JButton enter;
        enter = new JButton("ثبت");
        enter.setBounds(panel.getWidth()*3/7,panel.getHeight()*6/7,panel.getWidth()/7,panel.getHeight()/7);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMainPanel(farsiMainMenu);
            }
        });
        //label defining
        JLabel label = new JLabel("رمز خود را وارد کنید.");
        label.setBounds(enter.getX(),enter.getY()-300,enter.getWidth(),enter.getHeight()/2);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        //input field defining
        JTextField field = new JTextField();
        field.setBounds(label.getX(),label.getY()+label.getHeight(),
                label.getWidth(),label.getHeight()/2);
        //adding
        panel.add(enter);
        panel.add(label);
        panel.add(field);
        panel.setOpaque(false);
        return panel;
    }
    public static JPanel FarsiChangePass(){
        //panel create
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(d.width,d.height-38);// my panel didnt fit with given dimension so i added a -38 which was found with testing
        // button defining
        JButton enter;
        enter = new JButton("تایید");
        enter.setBounds(panel.getWidth()*3/7,panel.getHeight()*6/7,panel.getWidth()/7,panel.getHeight()/14);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMainPanel(farsiResult);
            }
        });
        //label defining
        JLabel label = new JLabel("رمز جدید را وارد کنید.");
        label.setBounds(enter.getX(),enter.getY()-300,enter.getWidth(),enter.getHeight()/2);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        //input field defining
        JTextField field = new JTextField();
        field.setBounds(label.getX(),label.getY()+label.getHeight()+20,
                label.getWidth(),label.getHeight());
        //adding
        panel.add(enter);
        panel.add(label);
        panel.add(field);
        panel.setOpaque(false);
        return panel;
    }
    public static JPanel FarsiRemain(){
        //panel create
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(d.width,d.height-38);// my panel didnt fit with given dimension so i added a -38 which was found with testing
        //label defining
        String remainedMoney = NumberFormat.getCurrencyInstance().format(remained).replace("$","");
        JLabel label = new JLabel("موجودی شما: " + remainedMoney + " ریال است.");
        label.setBounds(panel.getWidth()/4,panel.getHeight()*5/7-300,panel.getWidth()/2,panel.getHeight()/14);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        //adding
        panel.add(label);
        panel.setOpaque(false);
        return panel;
    }
    public static JPanel FarsiResult(){
        // it is FarsiRemain with another text
        JPanel panel = FarsiRemain();
        ((JLabel) (panel.getComponents()[0])).setText("عملیات با موفقیت انجام شد!");
        return panel;
    }
    public static JPanel FarsiTransfer(){
        // it is FarsiRemain with another text
        JPanel panel = FarsiChangePass();
        Component c = panel.getComponents()[1];
        ((JLabel)c).setText("مبلغ مورد نظر را وارد کنید.");
        for (int i = 1; i < 3; i++) {
            c = panel.getComponents()[i];
            c.setBounds(c.getX(),c.getY()-120,c.getWidth(),c.getHeight());
        }
        //label defining
        JLabel label = new JLabel("شماره کارت مقصد را وارد کنید.");
        //label.setBounds(c.getX(),c.getY()-300,c.getWidth(),c.getHeight()/2);
        label.setBounds(c.getX(),c.getY()+100,c.getWidth(),c.getHeight());
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        //input field defining
        JTextField field = new JTextField();
        field.setBounds(label.getX(),label.getY()+label.getHeight()+20,
                label.getWidth(),label.getHeight());
        //adding
        panel.add(label);
        panel.add(field);
        panel.revalidate();
        panel.repaint();
        return panel;
    }
    public static JPanel FarsiWithDraw(){
        // it is FarsiMainMenu with another text
        JPanel panel = FarsiMainMenu();
        for (int i = 0; i < 4; i++){
            ((JButton)(panel.getComponents()[i])).removeActionListener(((JButton)(panel.getComponents()[i])).getActionListeners()[0]);
            ((JButton)(panel.getComponents()[i])).setText(Integer.toString(50000*(i+1)));
            ((JButton)(panel.getComponents()[i])).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setMainPanel(farsiResult);
                }
            });
        }
        return panel;
    }
    public static void setMainPanel(JPanel p){
        f.remove(mainPanel);
        f.remove(ret);
        if(p == farsiResult)temp = mainPanel;
        mainPanel = p;
        ret = _return(mainPanel);
        System.out.println(ret.isVisible());
        f.add(mainPanel);
        f.repaint();
        f.revalidate();
        f.add(ret);
    }
}
