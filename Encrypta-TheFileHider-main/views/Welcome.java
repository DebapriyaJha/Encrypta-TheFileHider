package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.sendOTPService;
import service.UserService;
import service.sendOTPService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen()
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Encrypta App | The File Hider");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to signup");
        System.out.println("Press 0 to exit");
        int choice=0;
        try{
            choice=Integer.parseInt(br.readLine());
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
        switch(choice)
        {
            case 1 ->login();
            case 2 ->signUp();
            case 0 ->System.exit(0);
        }
    }
    private void login() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your E-mail");
        String email=sc.nextLine();
        try{
            if(UserDAO.isExists(email)){
                String genOTP= GenerateOTP.getOTP();
                sendOTPService.sendOTP(email,genOTP);
                System.out.println("Enter the OTP ");
                String otp=sc.nextLine();
                if(otp.equals(genOTP))
                {
                    new UserView(email).home();
                }
                else{
                    System.out.println("Wrong OTP");
                }
            }
            else {
                System.out.println("User not found");
            }
        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    private void signUp() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your name");
        String name=sc.nextLine();
        System.out.println("Enter your E-mail");
        String email=sc.nextLine();
        String genOTP= GenerateOTP.getOTP();
        sendOTPService.sendOTP(email,genOTP);
        System.out.println("Enter the OTP ");
        String otp=sc.nextLine();
        if(otp.equals(genOTP))
        {
            User user=new User(name,email);
            int response= UserService.saveUser(user);
            switch (response){
                case 0:
                    System.out.println("User Registered");
                    break;
                case 1:
                    System.out.println("User Already exists");
                    break;
            }
        }
        else{
            System.out.println("Wrong OTP");
        }

    }
}
