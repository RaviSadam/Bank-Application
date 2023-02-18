import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Registration implements ActionListener,KeyListener,WindowListener{
    JLabel icon,name,father,address,pass,image,pin,phone,gender,file;
    JTextField Ename,Efather,Epass,Ephone,Epin;
    JRadioButton male,female;
    JButton submit,Efile;
    JTextArea Eaddress;
    ButtonGroup bg;
    JPanel panel;
    JFrame frame;

    String filePath;
    Login loginObj;
    
    Registration(Login obj){
        this.loginObj=obj;
        frame=new JFrame();


        panel=new JPanel();
        panel.setBackground(new Color(123,143,116));
        panel.setLayout(null);
        frame.add(panel);


        icon=new JLabel(new ImageIcon("registrationIcon1.png"));
        icon.setBounds(180,10,200,200);
        panel.add(icon);


        name=new JLabel("Name:");
        name.setFont(loginObj.font2);
        name.setBounds(100,200 ,100 , 30 );
        panel.add(name);

        Ename=new JTextField();
        Ename.setFont(loginObj.font2);
        Ename.setHorizontalAlignment(JTextField.CENTER);
        Ename.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(213,136,217)));
        Ename.setBounds(260,200,200,30);
        Ename.addKeyListener(this);
        panel.add(Ename);

        father=new JLabel("Father name:");
        father.setFont(loginObj.font2);
        father.setBounds(100,260 ,200 , 30 );
        panel.add(father);

        Efather=new JTextField();
        Efather.setFont(loginObj.font2);
        Efather.setHorizontalAlignment(JTextField.CENTER);
        Efather.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(213,136,217)));
        Efather.setBounds(260,260,200,30);
        Efather.addKeyListener(this);
        panel.add(Efather);

        gender=new JLabel("Gender");
        gender.setFont(loginObj.font2);
        gender.setBounds(100,320,200,30);
        panel.add(gender);

        male=new JRadioButton("Male");
        male.setFont(loginObj.font2);
        male.setCursor(loginObj.cursor);
        male.setBounds(260,320,70,30);
        male.setBorderPainted(false);
        male.setContentAreaFilled(false);
        male.setFocusPainted(false);
        panel.add(male);


        female=new JRadioButton("Female");
        female.setFont(loginObj.font2);
        female.setCursor(loginObj.cursor);
        female.setBounds(340,320,100,30);
        female.setBorderPainted(false);
        female.setContentAreaFilled(false);
        female.setFocusPainted(false);
        panel.add(female);
        
        bg=new ButtonGroup();
        bg.add(male);
        bg.add(female);

        phone=new JLabel("Phone number:");
        phone.setFont(loginObj.font2);
        phone.setBounds(100,380 ,200 , 30 );
        panel.add(phone);

        Ephone=new JTextField();
        Ephone.setFont(loginObj.font2);
        Ephone.setHorizontalAlignment(JTextField.CENTER);
        Ephone.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(213,136,217)));
        Ephone.setBounds(260,380,200,30);
        Ephone.addKeyListener(this);
        panel.add(Ephone);

        address=new JLabel("Address:");
        address.setFont(loginObj.font2);
        address.setBounds(100,430,200,30);
        panel.add(address);

        Eaddress=new JTextArea(4,6);
        Eaddress.setFont(loginObj.font2);
        Eaddress.setBounds(260,430,200,80);
        panel.add(Eaddress);

        pin=new JLabel("Pin number:");
        pin.setFont(loginObj.font2);
        pin.setBounds(100,520 ,200 , 30 );
        panel.add(pin);

        Epin=new JTextField();
        Epin.setFont(loginObj.font2);
        Epin.setHorizontalAlignment(JTextField.CENTER);
        Epin.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(213,136,217)));
        Epin.setBounds(260,520,200,30);
        Epin.addKeyListener(this);
        panel.add(Epin);


        pass=new JLabel("Set password:");
        pass.setFont(loginObj.font2);
        pass.setBounds(100,570 ,200 , 30 );
        panel.add(pass);

        Epass=new JTextField();
        Epass.setFont(loginObj.font2);
        Epass.setHorizontalAlignment(JTextField.CENTER);
        Epass.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(213,136,217)));
        Epass.setBounds(260,570,200,30);
        panel.add(Epass);

        file=new JLabel("Choose Photo:");
        file.setFont(loginObj.font2);
        file.setBounds(100,620,200,30);
        panel.add(file);

        Efile=new JButton("Choose photo file");
        Efile.setCursor(loginObj.cursor);
        Efile.setBounds(260, 620, 200, 30);
        Efile.setBorderPainted(false);
        Efile.setFocusPainted(false);
        Efile.addActionListener(this);
        panel.add(Efile);

        submit=new JButton("submit");
        submit.setFont(loginObj.font2);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.setCursor(loginObj.cursor);
        submit.setBounds(250, 660, 100, 30);
        submit.setBorderPainted(false);
        submit.setFocusPainted(false);
        submit.addActionListener(this);
        panel.add(submit);
        
        ImageIcon frameIcon=new ImageIcon("registrationIcon-removebg-preview.png");
        frame.setIconImage(frameIcon.getImage());
        frame.setTitle("Registration");
        frame.setBackground(new Color(123,145,206));
        frame.setSize(600,900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
        frame.addWindowListener(obj);
    }
    @Override
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Efile){
            JFileChooser file_Chooser=new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", new String[] {"jpg", "jpeg","png"});
            file_Chooser.setFileFilter(filter);
            file_Chooser.addChoosableFileFilter(filter);

            if(file_Chooser.showOpenDialog(null)==0){
                Efile.setBackground(new Color(123,200,67));
            }
            this.filePath=file_Chooser.getSelectedFile().getAbsolutePath();
            this.filePath=(this.filePath).replace("\\","/");
            Efile.setText(filePath.substring(filePath.lastIndexOf("/")+1));
            System.out.println(this.filePath);
        }
        else{
            Efile.setText("Choose File");
            String userInput[]=new String[7];

            //Collecting the information from feilds
            userInput[0]=Ename.getText();userInput[1]=Efather.getText();userInput[3]=Ephone.getText();userInput[4]=Eaddress.getText();userInput[5]=Epin.getText();userInput[6]=Epass.getText();
            if(male.isSelected())
                userInput[2]="Male";
            else if(female.isSelected())
                userInput[2]="Female";


            //Checking all feilds are FILLED or NOT
            if(userInput[0].equals(null) ||userInput[1].equals(null)||userInput[2].equals(null)||userInput[3].equals(null)||userInput[4].equals(null)||userInput[5].equals(null)||userInput[6].equals(null) ){
                    int res=JOptionPane.showConfirmDialog(frame, "Not filled all fileds","Requried",JOptionPane.ERROR_MESSAGE);
                    if(res==-1){
                        loginObj.closingWork();
                        loginObj.frame.dispose();
                    }
            }
            else{
                //Address Spliting
                userInput[2]=userInput[2].replace("\n", " ");
                if(userInput[2].length()==1)
                    userInput[2]=userInput[2]+" Not Given";
                else if(userInput[2].length()==2)
                    userInput[2]=userInput[2]+" Not Given Not Given";

                /*JDBC CALL*/
                String accounNumber=loginObj.jdbcObject.newRegistration(userInput[0],userInput[1],userInput[2],userInput[3],userInput[4],userInput[5],userInput[6],this.filePath);

                //Displying the USER DETAILS 
                if(!accounNumber.equals(null)){
                    JOptionPane.showConfirmDialog(frame,"YourName : "+userInput[0]+"\n"+"Account ID / Number : "+accounNumber+"\n"+"Password : "+userInput[6]+"\n"+"Save the A/C number\nand PASSWORD for future use","Account Details",JOptionPane.OK_CANCEL_OPTION);
                    frame.setVisible(false);
                    loginObj.frame.setVisible(true);
                }

                //Resetting the Input Details;
                if(userInput[2].equals("Male"))
                    male.setSelected(false);
                else
                    female.setSelected(false);
                    
                Ename.setText("");Efather.setText("");Epass.setText("");Ephone.setText("");Epin.setText("");Eaddress.setText("");
                this.filePath="";
            }
        }
    }

    //Checking wether the user giving the Valid input ot not in every filed
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyChar();
        Object o=e.getSource();
        if(code==32 || code==10 ||code==65535||code==8){}
        else if(o==Ename){
            if(!(code>=97 && code<=122 || code>=65 && code<=90)){
                Ename.setText("");
                JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", JOptionPane.OK_OPTION);
            }
        }
        else if(o==Efather){
            if(!(code>=97 && code<=122 || code>=65 && code<=90)){
                Efather.setText("");
                JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", JOptionPane.OK_OPTION);
            }
        }
        else if(o==Epin){
            if(!(code>=48 && code<=57)){
                Epin.setText("");
                JOptionPane.showMessageDialog(frame, "Invalid Number", "Invalid", JOptionPane.OK_OPTION);
            }
        }
        else if(o==Ephone){
            if(!(code>=48 && code<=57)){
                Ephone.setText("");
                JOptionPane.showMessageDialog(frame, "Invalid Number", "Invalid", JOptionPane.OK_OPTION);
            }
        }   
    }
    

    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }
    
    public void windowClosing(WindowEvent e) {
        loginObj.closingWork();
    }
}