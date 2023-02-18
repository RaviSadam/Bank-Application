import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class HomePage implements ActionListener,WindowListener,KeyListener{
    JLabel sendTo,money,title,userImage,uname,acno,fname,ifsc,branch,gender,balance,address,lastvisite,phone,village,mndl,dist;
    JButton profile,statement,logout,transfor,procede;
    JPanel panel1,panel2,panel3,panel4,panel5;
    JTextField sendToField,moneyField;
    JTextArea textArea;
    JFrame frame;
    String id;
    Login loginObj;

    boolean statementGetted=false;

    HomePage(Login obj){
        this.loginObj=obj;
        frame=new JFrame("Home Page");

        /*Panel 1*/
        panel1=new JPanel();
        panel1.setBackground(new Color(238,32,77));;
        panel1.setBounds(0,0,1095,150);
        frame.add(panel1);

        title=new JLabel("MLRITM BANK");
        title.setFont(loginObj.font5);
        title.setForeground(new Color(45,187,34));
        panel1.add(title);
        

        /*panel 2*/
        panel2=new JPanel();
        panel2.setBackground(new Color(29,172,214));
        panel2.setBounds(0,150,230,530);

        profile=new JButton(new ImageIcon("C:/Users/Lenovo/Desktop/Projects/Bank Application/ProfileIcon.png"));
        profile.setFont(loginObj.font4);
        profile.setText("Profile");
        profile.setCursor(loginObj.cursor);
        
        profile.setBorderPainted(false);
        profile.setContentAreaFilled(false);
        profile.setFocusPainted(false);
        profile.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));

        profile.setForeground(Color.white);
        profile.addActionListener(this);

        statement=new JButton(new ImageIcon("C:/Users/Lenovo/Desktop/Projects/Bank Application/StatementIcon.png"));
        statement.setIconTextGap(-8);
        statement.setFont(loginObj.font4);
        statement.setText("Statement");
        statement.setCursor(loginObj.cursor);

        statement.setBorderPainted(false);
        statement.setContentAreaFilled(false);
        statement.setFocusPainted(false);
        statement.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));

        statement.setForeground(Color.white);
        statement.addActionListener(this);

        transfor=new JButton(new ImageIcon("C:/Users/Lenovo/Desktop/Projects/Bank Application/MoneySend.png"));
        transfor.setFont(loginObj.font4);
        transfor.setText("SendMoney");
        transfor.setCursor(loginObj.cursor);

        transfor.setBorderPainted(false);
        transfor.setContentAreaFilled(false);
        transfor.setFocusPainted(false);
        transfor.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));

        transfor.setForeground(Color.white);
        transfor.addActionListener(this);

        logout=new JButton(new ImageIcon("C:/Users/Lenovo/Desktop/Projects/Bank Application/Logout.png"));
        logout.setFont(loginObj.font4);
        logout.setText("Logout");
        logout.setCursor(loginObj.cursor);

        logout.setBorderPainted(false);
        logout.setContentAreaFilled(false);
        logout.setFocusPainted(false);
        logout.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));

        logout.setForeground(Color.white);
        logout.addActionListener(this);


        panel2.add(profile);
        panel2.add(statement);
        panel2.add(transfor);
        panel2.add(logout);
        frame.add(panel2);
        
        
        /*Panel 3*/
        panel3=new JPanel();
        panel3.setBackground(new Color(28,211,162));
        panel3.setBounds(230,150,850,530); 
        panel3.setLayout(null);
        panel3.setVisible(false); 
        frame.add(panel3);


        uname=new JLabel();
        uname.setForeground(Color.white);
        uname.setFont(loginObj.font3);
        uname.setBounds(100,40,650,30);
        panel3.add(uname);

        userImage= new JLabel();
        userImage.setBounds(620,80,200,300);
        panel3.add(userImage);

        acno=new JLabel();
        acno.setFont(loginObj.font3);
        acno.setForeground(Color.white);
        acno.setBounds(100,80,650,30);
        panel3.add(acno);

        fname=new JLabel();
        fname.setFont(loginObj.font3);
        fname.setForeground(Color.white);
        fname.setBounds(100,120,650,30);
        panel3.add(fname);


        ifsc=new JLabel();
        ifsc.setFont(loginObj.font3);
        ifsc.setForeground(Color.white);
        ifsc.setBounds(100,160,650,30);
        panel3.add(ifsc);

        branch=new JLabel();
        branch.setFont(loginObj.font3);
        branch.setForeground(Color.white);
        branch.setBounds(100,200,650,30);
        panel3.add(branch);

        gender=new JLabel();
        gender.setFont(loginObj.font3);
        gender.setForeground(Color.white);
        gender.setBounds(100,240,650,30);
        panel3.add(gender);

        address=new JLabel();
        address.setFont(loginObj.font3);
        address.setForeground(Color.white);
        address.setBounds(100,280,650,30);
        panel3.add(address);

        village=new JLabel();
        village.setFont( loginObj.font2);
        village.setForeground(Color.white);
        village.setBounds(300,280,200,30);
        panel3.add(village);
 
        mndl=new JLabel();
        mndl.setFont(loginObj.font2);
        mndl.setForeground(Color.white);
        mndl.setBounds(300,310,200,30);
        panel3.add(mndl);

        dist=new JLabel();
        dist.setFont(loginObj.font2);
        dist.setForeground(Color.white);
        dist.setBounds(300,340,200,30);
        panel3.add(dist);


        phone=new JLabel();
        phone.setFont(loginObj.font3);
        phone.setForeground(Color.white);
        phone.setBounds(100,380,650,30);
        panel3.add(phone);

        lastvisite=new JLabel();
        lastvisite.setFont(loginObj.font3);
        lastvisite.setForeground(Color.white);
        lastvisite.setBounds(100,420,850,35);
        panel3.add(lastvisite);
        

        balance=new JLabel();
        balance.setFont(loginObj.font3);
        balance.setBounds(100,470,450,30);
        balance.setForeground(Color.white);
        panel3.setVisible(false); 
        panel3.add(balance);

        //Panel 4
        panel4=new JPanel();//satements
        panel4.setBounds(230,150,850,530);
        panel4.setLayout(new GridLayout(0,1));
        panel4.setVisible(false);
        frame.add(panel4);

        textArea=new JTextArea();
        textArea.setFont(loginObj.font1);
        textArea.setBackground(new Color(28,211,162));

        textArea.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        textArea.setEditable(false);
        panel4.add(textArea);


        panel5=new JPanel();//sendMoney
        panel5.setBackground(new Color(	255,255,153));
        panel5.setBounds(230,150,850,530);
        panel5.setLayout(null);
        panel5.setVisible(false);
        frame.add(panel5);

        procede=new JButton("Continue");
        procede.setFont(loginObj.font3);
        procede.setForeground(Color.RED);
        procede.setBounds(380,300,150,40);
        procede.setFocusPainted(false);
        procede.addActionListener(this);
        procede.setCursor(loginObj.cursor);
        panel5.add(procede);

        frame.setLayout(null);
        frame.setSize(1095,760);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);        
        frame.setVisible(false);
        frame.addWindowListener(obj);
    }
    
    //Setting the userDetails
    private void userDetails(String details[]){
        ImageIcon icon=new ImageIcon("C:/Users/Lenovo/Desktop/Projects/Bank Application/userImage.png");    
        Image image = icon.getImage().getScaledInstance(200,300, Image.SCALE_AREA_AVERAGING);                                
        icon.setImage(image); 

        uname.setText("Name :                  "+details[0]);
        userImage.setIcon(icon);
        acno.setText("Account Number: "+this.id);
        fname.setText("Father Name:        "+details[1]);
        ifsc.setText("IFSC Code :          "+details[7]);
        address.setText("Address:  ");
        branch.setText("Branch Name:      "+details[8]);
        String add=details[4].replace("\n", " ");

        if(add.length()>1)
            add=add+"Not Given";
        if(add.length()>2)
            add=add+" Not Given";
        if(add.length()>3)
            add=add+" Not Given";
        String temp[]=add.split(" ");
        village.setText("Vill : "+temp[0]);
        mndl.setText("Mndl : "+temp[1]);
        dist.setText("Dist : "+temp[2]);
        phone.setText("Phone Number: "+details[3]);
        lastvisite.setText("Last Visite On: "+details[9]+" "+details[10]);
        balance.setText("Balance : "+details[6]);
    }

    //Sending Money
    private void sendMoney(){
        sendTo=new JLabel("Enter recivers A/C number:");
        sendTo.setFont(loginObj.font3);
        sendTo.setForeground(new Color(255,29,206));
        sendTo.setBounds(300,80,400,30);
        panel5.add(sendTo);

        sendToField=new JTextField();
        sendToField.setFont(loginObj.font3);
        sendToField.setForeground(new Color(28,211,162));
        sendToField.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        sendToField.setBounds(300,130,300,30);
        panel5.add(sendToField);

        money=new JLabel("Enter amount:");
        money.setFont(loginObj.font3);
        money.setForeground(new Color(255,29,206));
        money.setBounds(300,190,300,30);
        panel5.add(money);

        moneyField=new JTextField();
        moneyField.setFont(loginObj.font3);
        moneyField.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        moneyField.setForeground(new Color(28,211,162));
        moneyField.setHorizontalAlignment(JTextField.CENTER);
        moneyField.setBounds(300,240,300,30);
        moneyField.addKeyListener(this);
        panel5.add(moneyField);
    }

    //Getting the statement Table
    public void statementTable(){
        textArea.append(String.format("%-17s", "A/C Number")+String.format("%-30s","Date & Time")+String.format("%-22s","Money")+"Description"+"\n");
        textArea.append("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        String statementsString[]=loginObj.jdbcObject.getStatements(this.id).split("\n");
        for(String s:statementsString){
            String res[]=s.split("-");
            String temp[]=res[1].split(" ");
            res[3]=String.format("%1$-15s",res[3]).replace(" ", "  ");
            textArea.append(String.format("%1$-17s", res[0])+String.format("%1$-25s",temp[0]+"-"+temp[1])+res[3]+res[2]+"\n");
        }
        return;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o=e.getSource();
        if(o==profile  && !panel3.isVisible()){
            String getDetails[]={".....",".....",".....",".....",".....",".....",".....",".....",".....",".....","......"};
            if(!this.id.equals("")){
                getDetails=loginObj.jdbcObject.getUserDetails(this.id);
            }
            panel3.setVisible(true);
            panel4.setVisible(false);
            panel5.setVisible(false);
            userDetails(getDetails);
        }else if(o==transfor){
            if( !panel5.isVisible()){
                panel4.setVisible(false);
                panel3.setVisible(false);
                panel5.setVisible(true);
            }
            this.sendMoney();
        }
        else if(o==statement && !panel4.isVisible()){
            if(!this.statementGetted)
                this.statementTable();
            this.statementGetted=true;
            panel4.setVisible(true);
            panel3.setVisible(false);
            panel5.setVisible(false);
        }
        else if(o==procede){
            //sending money from sender ro receiver(Updating in data base)
        
            String transactionResult[]={"-1"};
            if(!sendToField.equals("") && !moneyField.getText().equals(""))
                transactionResult=loginObj.jdbcObject.moneyTransfor(loginObj.pre,sendToField.getText(),Double.parseDouble(moneyField.getText()));
            if(transactionResult[0]=="-1"){
                JOptionPane.showMessageDialog(frame, "Transaction Unsuccessful\nDue to internal ERROR\nTry After some TIME", "trancaction details", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(transactionResult[0]=="-2"){
                JOptionPane.showMessageDialog(frame, "Details Not Found\nPlease check the entered details","Invalid Details",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(transactionResult[0]=="-3"){
                JOptionPane.showMessageDialog(frame,"Insufficent Balance", "Transcation Summry",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(frame, "Transaction Successful\nYour Account Balance is:"+transactionResult[0], "trancaction details", JOptionPane.INFORMATION_MESSAGE);
                if(statementGetted){
                    String temp[]=transactionResult[2].split(" ");
                    String temp1=String.format("%1$-15s",moneyField.getText()).replace(" ", "  ");
                    textArea.append(String.format("%1$-17s", this.id)+String.format("%1$-25s",temp[0]+"-"+temp[1])+temp1+"Money Sended to "+transactionResult[1]+"\n");        
                    balance.setText("Balance : "+transactionResult[0]);
                }
            }
            moneyField.setText("");
            sendToField.setText("");
        }
        else if(o==logout){
            int option=JOptionPane.showConfirmDialog(loginObj, "You Sure want to LOG-GOUT","Logout",JOptionPane.OK_CANCEL_OPTION);
            if(option==-1 || option==0){
                this.panel3.setVisible(false);
                this.frame.setVisible(false);
                panel5.setVisible(false);
                panel4.setVisible(false);
                this.statementGetted=false;
                uname.setText("");fname.setText("");acno.setText("");ifsc.setText("");branch.setText("");gender.setText("");lastvisite.setText("");phone.setText("");village.setText("");mndl.setText("");dist.setText("");
                loginObj.frame.setVisible(true);
            }
            textArea.setText("");
        }
    }




    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==32 || code==10 ||code==65535||code==8){}
        if(!(code>=48 && code<=57)){
            moneyField.setText("");
            JOptionPane.showMessageDialog(frame, "Invalid Number", "Invalid", JOptionPane.OK_OPTION);
        }
    }
    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }
    public void windowClosing(WindowEvent e) {
        loginObj.closingWork();
    }
}
