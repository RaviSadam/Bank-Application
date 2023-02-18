import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
public class Login extends JFrame implements ActionListener,WindowListener,MouseListener,KeyListener{
    Font font1,font2,font3,font4,font5;
    JLabel label,userId,userPass;
    JButton submit,newAcc,eye;
    JTextField id,pass;
    JFrame frame;
    JPanel panel;
    Cursor cursor;

    Jdbc_Connection jdbcObject;
    HomePage objHomePage;
    Registration registrationObj;

    String pre;
    StringBuilder password,text;
    int length;
    Login(){
        length=0;
        password=new StringBuilder();
        text=new StringBuilder();
        frame=new JFrame("Login");
        
        cursor=new Cursor(Cursor.HAND_CURSOR);
        
        font1=new FontUIResource("plane",Font.BOLD,15);
        font2=new FontUIResource("plane",Font.BOLD,20);
        font3=new FontUIResource("plane",Font.BOLD,25);
        font4=new FontUIResource("plane",Font.BOLD,30);
        font5=new FontUIResource("plane",Font.BOLD,120);

        label=new JLabel(new ImageIcon("login-removebg2.png"));
        label.setBounds(30,35,200,200);
        frame.add(label);

        userId=new JLabel("Enter your ID:");
        userId.setBounds(250,30,200,20);
        userId.setFont(font1);
        frame.add(userId);

        id=new JTextField();
        id.setColumns(30);
        id.setBounds(250, 60, 220, 30);
        id.setFont(font1);
        frame.add(id);

        userPass=new JLabel("Enter your PASSWORD:");
        userPass.setBounds(250,120,200,20);
        userPass.setFont(font1);
        frame.add(userPass);

        pass=new JTextField();
        pass.setColumns(15);
        pass.setBounds(250, 150, 220, 30);
        pass.setFont(font1);
        pass.addKeyListener(this);
        frame.add(pass);

        eye=new JButton();
        eye.setIcon(new ImageIcon("C:/Users/Lenovo/Desktop/Projects/Bank Application/look_see1.png"));
        eye.setBorderPainted(false);
        eye.setContentAreaFilled(false);
        eye.setFocusPainted(false);
        eye.setCursor(cursor);
        eye.addActionListener(this);
        eye.setBounds(470,150,35,30);
        eye.addMouseListener(this);
        frame.add(eye);

        submit=new JButton("submit");
        submit.setBounds(250, 210, 80, 30);
        submit.setCursor(cursor);
        submit.addActionListener(this);
        frame.add(submit);

        newAcc=new JButton("new Account");
        newAcc.setBounds(350,210,120,30);
        newAcc.setCursor(cursor);
        newAcc.addActionListener(this);
        frame.add(newAcc);

        ImageIcon frameIcon=new ImageIcon("OIP1.png");
        frame.setIconImage(frameIcon.getImage());
        frame.setTitle("Login");
        frame.setSize(600,300);
        frame.setBackground(Color.black);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(this);
        this.registrationObj=new Registration(this);
        this.jdbcObject=new Jdbc_Connection();
        this.objHomePage=new HomePage(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newAcc){
            frame.setVisible(false);
            registrationObj.frame.setVisible(true);
        } 
        else{
            //Checking the password and id of user
            if(jdbcObject.validUser(id.getText(),password.toString())){
                frame.setVisible(false);
                objHomePage.frame.setVisible(true);
                this.pre=id.getText();
                objHomePage.id=this.pre;
            }
            else{
                int value=JOptionPane.showConfirmDialog(frame, "Invalid PASSWORD or USER ID\nPlease try again","Invalid",JOptionPane.OK_CANCEL_OPTION);
                if(value==-1 || value==1){
                    this.closingWork();        
                }
            }
            password.delete(0, length);text.delete(0, length);this.length=0;
            this.id.setText("");
            this.pass.setText("");
        }
    }

    //Main Method
    public static void main(String args[]) {
        new Login();
    }

    public void closingWork(){
        jdbcObject.updateLastVisite(this.pre);
        try {
           jdbcObject.con.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        System.gc();     
        frame.dispose();
    }
    

    @Override
    
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}

    public void windowClosed(WindowEvent e) {
        if(jdbcObject!=null){
            try {
                if(!jdbcObject.con.isClosed()){
                    jdbcObject.con.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.exit(0);
    }
    
    public void windowClosing(WindowEvent e) {
        this.closingWork();
    }


    public void mouseEntered(MouseEvent e) {
        pass.setText(password.toString());
    }
    public void mouseExited(MouseEvent e) {
        pass.setText(text.toString());
    }
    
    
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==32||code==16){}
        else if(pass.getText().equals("")){
            password.delete(0, length);
            text.delete(0, length);
            this.length=0;
        }
        else if(e.getKeyCode()==8){
            if(length==1||length==0){
                password.delete(0, length);
                text.delete(1, length);
                this.length=0;
            }
            else{
                text.deleteCharAt(length-1);
                password.deleteCharAt(length-1);
                this.length--;
            }
        }
        else{
            char chr=e.getKeyChar();
            password.append(chr);
            text.append("*");
            pass.setText(text.toString());
            this.length++;
        }
    }
}
