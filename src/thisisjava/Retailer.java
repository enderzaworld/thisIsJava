/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisisjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author Imba Gamer
 */
public class Retailer {
    private static final String directory=System.getProperty("user.dir");
    private static final String sample=directory+File.separator+"sendMsg.txt";
    private static final String trigger=directory+File.separator+"sendMsgTrigger.txt";
    private static final NumberValidation nvdia = new NumberValidation();

    public static void createFile(String code,String number){
        File file=new File(sample);
        try(FileWriter fw=new FileWriter(file))
        {
            fw.write(number+"_-_"+code);
            fw.flush();
            fw.close();
            File fileTrigger=new File(trigger);
            try(FileWriter fwt=new FileWriter(fileTrigger))
            {
                fwt.write("1");
                fwt.flush();
                fwt.close();
            }catch(IOException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                ex.printStackTrace();
            }
        }catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void safeCreateFile(String code,String number,int cost){//wait for queue
        FileReader fr = null;
        String flag = "1";
        int counter = 0;
        boolean fail = false;
        do{
        try {
            fr = new FileReader(trigger);
            BufferedReader textReader = new BufferedReader(fr);
            flag = textReader.readLine();
            System.out.println(flag);
        } catch (FileNotFoundException ex) {
            createTriggerFile();
        } catch (IOException ex) {
            Logger.getLogger(NoMorePorn.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                continue;//Logger.getLogger(NoMorePorn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            continue;//Logger.getLogger(NoMorePorn.class.getName()).log(Level.SEVERE, null, ex);
        }
        counter++;
        if(counter>10){fail=true;break;}
        }while("1".equals(flag));
            NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
        if(!fail){
            createFile(code,number);
            NoMorePorn.reduceToken(cost);
            JOptionPane.showMessageDialog(null, "Sent.");
        }else{
            createTriggerFile();
            JOptionPane.showMessageDialog(null, "Sending Failed. Try again.");
        }
        
    }
    
    public static void createTriggerFile(){
        File fileTrigger=new File(trigger);
        try(FileWriter fwt=new FileWriter(fileTrigger))
        {
            fwt.write("0");
            fwt.flush();
            fwt.close();
        }catch(IOException exc)
        {
            JOptionPane.showMessageDialog(null, exc.getMessage());
            exc.printStackTrace();
        }
    }
    
    public boolean retailSun(String number,String promo){
        String[] stringsPromo = {"Call and Text Combo 10", "Call and Text Combo 20", "Call and Text Combo 30", "Call and Text Combo 50", "Unli Call and Text 25", "Unli Call and Text 30", "Unli Call and Text 100", "Unli Call and Text 150", "Unli Call and Text 450", "Unli Text to all network 20", "Unli Text 20", "Unli Text 50", "Unli Text 150", "Winner Text 10", "Trio 20", "IDD Combo 30", "IDD Combo 60", "TODO IDD 30" };
        String[] stringsCode =  {"CTC10", "CTC20", "CTC30", "CTC50", "CTU25", "CTU30", "CTU100", "CTU150", "CTU450", "UTA20", "TU20", "TU50", "TU150", "WT10", "TRIO20", "IDD30", "IDD60", "IDD300" };
        int[] promoCost =       {     10,      20,      30,      50,      25,      30,      100,      150,      450,      20,     20,     50,     150,     10,       20,      30,      60,       30 };
        if(promo.contains("Regular")){
            int cost = Integer.parseInt(promo.replace("Regular ", ""));
            System.out.println(NoMorePorn.token+">="+cost);
            if(JOptionPane.showConfirmDialog(null, "The cost is:"+cost+".\nDo you want to proceed?","Confirm",YES_NO_OPTION)<1){
                if(NoMorePorn.token>=cost){
                    safeCreateFile(number+" "+promo.replace("Regular ", ""),nvdia.getGateway("sun"),cost);
                }else{
                    NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
                    JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Not Enough pepe");
                }
                return true;
            }else{
                return false;
            }
        }else{
            if(stringsPromo.length==stringsCode.length){
                for (int i = 0; i < stringsPromo.length && i< stringsCode.length; i++) {
                    String strPromo = stringsPromo[i];
                    if(promo.equals(strPromo)){
                        String strCode = stringsCode[i];
                        int cost = promoCost[i];

                        System.out.println(NoMorePorn.token+">="+cost);
                        if(JOptionPane.showConfirmDialog(null, "The cost is:"+cost+".\nDo you want to proceed?","Confirm",YES_NO_OPTION)<1){
                            if(NoMorePorn.token>=cost){
                                if(strPromo == null ? promo == null : strPromo.equals(promo)){
                                    safeCreateFile(number+" "+strCode,nvdia.getGateway("sun"),cost);
                                    return true;
                                }
                            }else{
                                NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
                                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Not Enough pepe");
                            }
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "no code match found");
            }else{
                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "length mismatch at retailSun");
            }
            return false;
        }
    }
    
    public boolean retailGlobe(String number,String promo){
        if(promo.contains("Regular")){
            int cost = Integer.parseInt(promo.replace("Regular ", ""));
            System.out.println(NoMorePorn.token+">="+cost);
            if(JOptionPane.showConfirmDialog(null, "The cost is:"+cost+".\nDo you want to proceed?","Confirm",YES_NO_OPTION)<1){
                if(NoMorePorn.token>=cost){
                    safeCreateFile(number+" "+promo.replace("Regular ", ""),nvdia.getGateway("globe"),cost);
                }else{
                    NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
                    JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Not Enough pepe");
                }
                return true;
            }else{
                return false;
            }
        }else{//must not happen at all cost?
            JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Something went wrong");
        }
        return false;
    }
    
    public boolean retailSmart(String number,String promo){
        String[] stringsPromo = {"Alltext 10","Alltext 20","Alltext 30","Lahat text 20","Lahat text 30","Smart text 30","Smart text 60","Unli text to SMART, TNT, and SUN 50","Unli text to SMART, TNT, and SUN 100","200 mins. call to SMART and TNT 100","All In 25","Big Bytes 30","Big Bytes 70"};
        String[] stringsCode =  {"AT10","AT20","AT30","L20","L30","ST30","ST60","BT50","BT100","BC100","AI25","BIG30","BIG70"};
        int[] promoCost =       {     10,   20,    30,   20,   25,    30,    60,    50,   100,     100,    25,     30,     70};
        if(promo.contains("Regular")){
            int cost = Integer.parseInt(promo.replace("Regular ", ""));
            System.out.println(NoMorePorn.token+">="+cost);
            if(JOptionPane.showConfirmDialog(null, "The cost is:"+cost+".\nDo you want to proceed?","Confirm",YES_NO_OPTION)<1){
                if(NoMorePorn.token>=cost){
                    safeCreateFile(number+" "+promo.replace("Regular ", ""),nvdia.getGateway("smart"),cost);
                }else{
                    NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
                    JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Not Enough pepe");
                }
                return true;
            }else{
                return false;
            }
        }else{
            if(stringsPromo.length==stringsCode.length){
                for (int i = 0; i < stringsPromo.length && i< stringsCode.length; i++) {
                    String strPromo = stringsPromo[i];
                    if(promo.equals(strPromo)){
                        String strCode = stringsCode[i];
                        int cost = promoCost[i];

                        System.out.println(NoMorePorn.token+">="+cost);
                        if(JOptionPane.showConfirmDialog(null, "The cost is:"+cost+".\nDo you want to proceed?","Confirm",YES_NO_OPTION)<1){
                            if(NoMorePorn.token>=cost){
                                if(strPromo == null ? promo == null : strPromo.equals(promo)){
                                    safeCreateFile(number+" "+strCode,nvdia.getGateway("smart"),cost);
                                    return true;
                                }
                            }else{
                                NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
                                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Not Enough pepe");
                            }
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "no code match found");
            }else{
                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "length mismatch at retailSmart");
            }
            return false;
        }
    }
    
    public boolean retailSmartBro(String number,String promo){
        String[] stringsPromo= {"Unlimited Surfing 50","Unlimited Surfing 85","Unlimited Surfing 200","Unlimited Surfing 250","Unlimited Surfing 500","All SurfMax (3G,4G,LTE capable) 50","All SurfMax (3G,4G,LTE capable) 85","All SurfMax (3G,4G,LTE capable) 200","All SurfMax (3G,4G,LTE capable) 250","All SurfMax (3G,4G,LTE capable) 500","All SurfMax (3G,4G,LTE capable) 995","Flexi 20","Flexi 30","Flexi 50","Flexi 100","Flexi 200","Flexi 400"};
        String[] stringsCode = {"USURF50","USURF85","USURF200","USURF250","USURF500","SMAX50","SMAX85","SMAX200","SMAX250","SMAX500","SMAX995","FLEXI20","FLEXI30","FLEXI50","FLEXI100","FLEXI200","FLEXI400"};
        int[] promoCost      = {       50,       85,       200,       250,       500,      50,      85,      200,      250,      500,      995,       20,       30,       50,       100,       200,       400};
        if(promo.contains("Regular")){
            String regStr = promo.substring((promo.indexOf("Regular ")+"Regular ".length()));
            int cost = Integer.parseInt(regStr);
            System.out.println(NoMorePorn.token+">="+cost);
            if(JOptionPane.showConfirmDialog(null, "The cost is:"+cost+".\nDo you want to proceed?","Confirm",YES_NO_OPTION)<1){
                if(NoMorePorn.token>=cost){
                    safeCreateFile(number+" "+regStr,nvdia.getGateway("smartbro"),cost);
                }else{
                    NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
                    JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Not Enough pepe");
                }
                return true;
            }else{
                return false;
            }
        }else{
            if(stringsPromo.length==stringsCode.length){
                for (int i = 0; i < stringsPromo.length && i< stringsCode.length; i++) {
                    String strPromo = stringsPromo[i];
                    if(promo.equals(strPromo)){
                        String strCode = stringsCode[i];
                        int cost = promoCost[i];

                        System.out.println(NoMorePorn.token+">="+cost);
                        if(JOptionPane.showConfirmDialog(null, "The cost is:"+cost+".\nDo you want to proceed?","Confirm",YES_NO_OPTION)<1){
                            if(NoMorePorn.token>=cost){
                                if(strPromo == null ? promo == null : strPromo.equals(promo)){
                                    safeCreateFile(number+" "+strCode,nvdia.getGateway("smartbro"),cost);
                                    return true;
                                }
                            }else{
                                NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
                                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Not Enough pepe");
                            }
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "no code match found");
            }else{
                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "length mismatch at retailSmartBro");
            }
            return false;
        }
    }
    
    public boolean retailTNT(String number,String promo){
        String[] stringsPromo= {"Gaan text 10", "Gaan text 20", "Patok o Text 10", "Patok o Text 20", "Patok o Text 30", "Unli text to all 20", "UTP 100", "UTP 200", "Unli text to all networks 30", "Unli text and 10 mins. call to SMART/TNT, 50 text to SMART/TNT 10", "Unli call and text to SMART/TNT 20", "Gaan Unlitext 10", "Gaan Unlitext Plus 15", "Gaan Unlitext 30", "Unlitext to SMART,TNT,SUN 150", "Unlitext to SMART,TNT,SUN 10", "Unlitext Extra 30", "40 Quick call(30 secs. each) 15", "PIDD 20", "PIDD 50" };
        String[] stringsCode = {"GT10","GT20","POT10","POT20","POT30","UA20","UTP100","UTP200","UT30","TP10","T20","GU10","GU15","GU30","U150","U10","U30","TOK15","PIDD20","PIDD50"};
        int[] promoCost      = {    10,    20,     10,     20,     30,    20,     100,     200,    30,    10,   20,    10,    15,    30,   150,   10,   30,     15,      20,      50};
        if(promo.contains("Regular")){
            String regStr = promo.substring((promo.indexOf("Regular ")+"Regular ".length()));
            int cost = Integer.parseInt(regStr);
            System.out.println(NoMorePorn.token+">="+cost);
            if(JOptionPane.showConfirmDialog(null, "The cost is:"+cost+".\nDo you want to proceed?","Confirm",YES_NO_OPTION)<1){
                if(NoMorePorn.token>=cost){
                    safeCreateFile(number+" "+regStr,nvdia.getGateway("tnt"),cost);
                }else{
                    NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
                    JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Not Enough pepe");
                }
                return true;
            }else{
                return false;
            }
        }else{
            if(stringsPromo.length==stringsCode.length){
                for (int i = 0; i < stringsPromo.length && i< stringsCode.length; i++) {
                    String strPromo = stringsPromo[i];
                    if(promo.equals(strPromo)){
                        String strCode = stringsCode[i];
                        int cost = promoCost[i];

                        System.out.println(NoMorePorn.token+">="+cost);
                        if(JOptionPane.showConfirmDialog(null, "The cost is:"+cost+".\nDo you want to proceed?","Confirm",YES_NO_OPTION)<1){
                            if(NoMorePorn.token>=cost){
                                if(strPromo == null ? promo == null : strPromo.equals(promo)){
                                    safeCreateFile(number+" "+strCode,nvdia.getGateway("tnt"),cost);
                                    return true;
                                }
                            }else{
                                NoMorePorn.MainTabPane.setSelectedComponent(NoMorePorn.MainPorn);
                                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "Not Enough pepe");
                            }
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "no code match found");
            }else{
                JOptionPane.showMessageDialog(NoMorePorn.MainTabPane, "length mismatch at retailTNT");
            }
            return false;
        }
    }
    
}
