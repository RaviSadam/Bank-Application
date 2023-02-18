import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.Random;
import java.text.*;

public class Jdbc_Connection{
    public Connection con;
    private ResultSet rs;
    private Statement st;
    SimpleDateFormat formate;
    Jdbc_Connection(){
        try{
            //Creating the connection between the Application and MYSQL(BANK_TABLE table)
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK_TABLE", "root", "Ravi@9390");
            formate=new SimpleDateFormat("dd-MM-yyyy");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //New Registration (Inserting the new details and immage of user into userDetaila and userImage table)
    public String newRegistration(String name,String fname,String gender,String phone,String address,String pin,String password,String filePath){
        String accountNumber=null;
        PreparedStatement newUserData=null,newImage=null;
        Random rand;
        try {
            newUserData=con.prepareStatement("INSERT INTO userDetails VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");//For userDetails Table
            newImage=con.prepareStatement("INSERT INTO userImage VALUES(?,?)");//For userImage table
            rand=new Random();
            
            //Account number generation
            int number=rand.nextInt(0, 999999);
            accountNumber=String.format("%4.4s", name)+String.format("%06d", number);
            System.out.println(accountNumber);

            //Adding the date
            Date date=new Date();
            java.util.Date d=formate.parse(formate.format(date));
            System.out.println(d+" "+new java.sql.Date(d.getTime()));
            
            //Creating FIN for image file
            FileInputStream fileInputStream=new FileInputStream(filePath);
            System.out.println(fileInputStream);


            //Inserting the data to the UserDetails table

            newUserData.setString(1,accountNumber);
            newUserData.setString(2,name);
            newUserData.setString(3,fname);
            newUserData.setString(4,gender);
            newUserData.setString(5,phone);
            newUserData.setString(6,address);
            newUserData.setString(7,pin);
            newUserData.setDouble(8,10000.0);
            newUserData.setString(9,"MLRITM507203");
            newUserData.setString(10,"Gandi Mysama");
            newUserData.setDate(11,new java.sql.Date(d.getTime()));
            newUserData.setString(12,password);
            newUserData.setTimestamp(13, new java.sql.Timestamp(date.getTime()));
            newUserData.executeUpdate();

            //Inserting the image to image table
            newImage.setString(1,accountNumber);
            newImage.setBlob(2, fileInputStream);
            newImage.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            newUserData.close();
            newImage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountNumber;
    }

    //Checking the password and id of user
    public boolean validUser(String id,String passwordEntered) {
        PreparedStatement passwordCheck=null;
        try {
            passwordCheck=con.prepareStatement("SELECT COUNT(*) FROM userDetails WHERE idNum=? AND pass=?");
            passwordCheck.setString(1,id);
            passwordCheck.setString(2,passwordEntered);
            rs=passwordCheck.executeQuery();
            rs.next();
            if(rs.getInt(1)==1){
                //Taking the image from BDB
                try {
                    st=con.createStatement();
                    PreparedStatement imageRead=con.prepareStatement("SELECT image FROM userImage WHERE idNum=?");
                    imageRead.setString(1,id);
                    rs=imageRead.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try (FileOutputStream fos = new FileOutputStream("C:/Users/Lenovo/Desktop/Projects/Bank Application/userImage.png",false)) {
                    rs.next();
                    Blob blob = rs.getBlob(1);
                    int len = (int) blob.length();
                    byte[] buf = blob.getBytes(1, len);
                    fos.write(buf);
                    fos.flush();;
                    fos.close();;
                }
                catch(IOException | SQLException exception){
                    exception.printStackTrace();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            passwordCheck.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    //Gathering the user details from the Data Base
    public String[] getUserDetails(String idNum){
        String details[]=new String[11];
        try {
            rs=null;
            st=con.createStatement();
            rs=st.executeQuery("SELECT name,fname,gender,phone,address,pin,balance,ifsc_code,branch,lastvisite,lastTime FROM userDetails WHERE idNum=\'"+idNum+"\'");
            rs.next();
            details[0]=rs.getString(1);
            details[1]=rs.getString(2);
            details[2]=rs.getString(3);
            details[3]=rs.getString(4);
            details[4]=rs.getString(5);
            details[5]=rs.getString(6);
            details[6]=String.valueOf(rs.getDouble(7));
            details[7]=rs.getString(8);
            details[8]=rs.getString(9);
            java.sql.Date da=rs.getDate(10);
            
            details[9]=formate.format(da);
            details[10]=String.valueOf(rs.getTime(11));
            
        } catch (Exception e) {e.printStackTrace();;}
        try{
            rs.close();
            st.close();
        }
        catch(SQLException e){e.printStackTrace();}
        return details;
    }

    public String[] moneyTransfor(String senderId,String recevierId,Double money) {
        Double tracactionResult=null;
        ResultSet rs1=null,rs2=null;
        boolean b=false;
        String dateString="",senderName="",receiverName="";
        try{
            PreparedStatement ps1=con.prepareStatement("SELECT name,balance FROM userDetails WHERE idNum=?");
            PreparedStatement ps2=con.prepareStatement("SELECT name,balance FROM userDetails WHERE idNum=?");
            ps1.setString(1,senderId);
            ps2.setString(1,recevierId);
            rs1=ps1.executeQuery();
            rs2=ps2.executeQuery();
            if(rs1.next() && rs2.next()){
                b=true;
                double senderMoney=rs1.getDouble(2),receiverMoney=rs2.getDouble(2);
                senderName=rs1.getString(1);receiverName=rs2.getString(1);
                if(senderMoney>=money){
                    tracactionResult=senderMoney-money;
                    PreparedStatement senderUpdate=con.prepareStatement("UPDATE userDetails SET balance=? WHERE idNum=?");
                    senderUpdate.setDouble(1, senderMoney-money);
                    senderUpdate.setString(2, senderId);
                    senderUpdate.executeUpdate();
                    PreparedStatement receiverUpdate=con.prepareStatement("UPDATE userDetails SET balance=? WHERE idNum=?");
                    receiverUpdate.setDouble(1, receiverMoney+money);
                    receiverUpdate.setString(2,recevierId);
                    receiverUpdate.executeUpdate();
                    dateString=this.updateTranscationStatement(senderId,senderName, recevierId, receiverName,money);
                    try {
                        receiverUpdate.close();
                    } catch (Exception e) {e.printStackTrace();}
                    try {
                        senderUpdate.close();
                    } catch (Exception e) {e.printStackTrace();}
                }
            }
            try {
                ps1.close();
                ps2.close();
            } catch (Exception e) {e.printStackTrace();}
         }
        catch(Exception e){
            e.printStackTrace();
        }
        try {
            if(rs1!=null)
                rs1.close();
            if(rs2!=null)
                rs2.close();
        } catch (Exception e) {e.printStackTrace();}
        if(!b){
            return new String[]{"-2"};
        }
        if(!b && tracactionResult==null){
            return new String[]{"-3"};
        } 
        return tracactionResult==null ? new String[]{"-1"}:new String[]{String.valueOf(tracactionResult),receiverName,dateString};
    }
    
    //Adding the transaction details to table 
    public String updateTranscationStatement(String sender , String senderName,String receiver,String receiverName,double money){
        PreparedStatement ps1=null,ps2=null;
        Date date=new Date();
        java.util.Date d;
        try{
            d = formate.parse(formate.format(date));
            ps1=con.prepareStatement("INSERT INTO transcationstatement values(?,?,?,?,?)");
            ps1.setString(1, sender);
            ps1.setString(2,"Money send to "+receiverName);
            ps1.setDate(3,new java.sql.Date(d.getTime()));
            ps1.setTimestamp(4, new java.sql.Timestamp(date.getTime()));
            ps1.setDouble(5, money);
            ps1.executeUpdate();

            ps2=con.prepareStatement("INSERT INTO transcationstatement values(?,?,?,?,?)");
            ps2.setString(1,receiver);
            ps2.setString(2,"Money received from "+senderName);
            ps2.setDate(3,new java.sql.Date(d.getTime()));
            ps2.setTimestamp(4, new java.sql.Timestamp(date.getTime()));
            ps2.setDouble(5,money);
            ps2.executeUpdate();
        }
        catch(Exception e){e.printStackTrace();}
        try {
            ps1.close();
            ps2.close();
        } catch (SQLException e) {e.printStackTrace();}
        return String.valueOf(new java.sql.Timestamp(date.getTime()));
    }

    public void updateLastVisite(String idNum) {

        //Updating the lastVisite and Last Visited Time
        Date date=new Date();
        java.util.Date d;
        try {
            d = formate.parse(formate.format(date));
            PreparedStatement updateDate=con.prepareStatement("UPDATE userDetails SET lastvisite=? ,lastTime=? WHERE idNum=?");
            updateDate.setDate(1, new java.sql.Date(d.getTime()));
            updateDate.setTimestamp(2,new java.sql.Timestamp(date.getTime()));
            updateDate.setString(3,idNum);
            updateDate.executeUpdate();
            updateDate.close();
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
        return;
    }
    public String getStatements(String idNum){
        StringBuilder builder=new StringBuilder("");
        try{
            PreparedStatement pst1 = con.prepareStatement(" SELECT count(*) FROM transcationstatement WHERE idNum=?");
            pst1.setString(1,idNum);
            rs=pst1.executeQuery();
            rs.next();
            if(rs.getInt(1)!=0){
                PreparedStatement pst = con.prepareStatement(" SELECT * FROM transcationstatement WHERE idNum=?");
                pst.setString(1,idNum);
                rs=pst.executeQuery();
                while(rs.next()){
                    builder.append(rs.getString(1)+"-"+String.valueOf(rs.getTimestamp(4)).replaceAll("-", "/")+"-"+rs.getString(2)+"-"+rs.getDouble(5)+"\n");    
                }
            }
            pst1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return builder.toString().equals(null)==true?"":builder.toString();
    }

}