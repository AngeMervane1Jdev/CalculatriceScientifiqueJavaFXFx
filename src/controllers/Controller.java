package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Main;

import javax.swing.*;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.Arrays.asList;
import static outils.Outils.*;

public class Controller implements Initializable {
    public static Stage second;
    public Button bDEG;
    public Button bC;
    public Button bOFF;
    public Button bD;
    public Button bP1;
    public Button bEXP10;
    public Button bsin;
    public Button b7;
    public Button b4;
    public Button b1;
    public Button b0;
    public Button bA;
    public Button bE;
    public Button bP2;
    public Button bEXP;
    public Button bcos;
    public Button b8;
    public Button b5;
    public Button bB;
    public Button b2;
    public Button bpoint;
    public Button bF;
    public Button bCHAP;
    public Button bFACT;
    public Button btan;
    public Button b9;
    public Button b6;
    public Button b3;
    public Button bmoins;
    public Button bc;
    public Button bEXP3;
    public Button bSqrt;
    public Button bLn;
    public Button bmod;
    public Button bplus;
    public Button brm;
    public Button bmoin;
    public Button b10E;
    public Button bDEL;
    public Button bdivX;
    public Button brac3;
    public Button bPi;
    public Button bac;
    public Button bxm;
    public Button bfois;
    public Button bdiv;
    public Button bequal;
    public TextField opperation;
    public static TextField opperationPassed;
    public Button bshift;
    public MenuBar menuBar;
    private String Memoire;
    private boolean is2ndf=false,isPlSs=false, isBin=false, isHex=false, isDec=true, isOct=false, isDeg=true, isOn=true;
    private int plusCount=0, sousCount=0, mulCount=0, divCount=0, powCount=0, modCount=0;
    private ArrayList<String>numbers=new ArrayList<>(), opperators=new ArrayList<>();
    private String previewText;
    public static int base=2;

    public static ArrayList<ArrayList<String>> results, historique;
    private List<Button> mes_Bouttons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        initHistorique();
        opperationPassed=opperation;
        mes_Bouttons=asList(bDEG,bD,bP1,bEXP10,bsin,b7,b4,b1,b0,bA,bE,bP2,bEXP,bcos,b8,b5,bB,b2,bpoint,bF,bCHAP,bFACT,btan,b9,b6,b3,bmoins,bc,bEXP3,bSqrt,bLn,bmod,bplus,brm,bmoin,b10E,bDEL,bdivX,brac3,bPi,bac,bxm,bfois,bdiv,bequal);
        System.out.println(mes_Bouttons.get(0).getText());
        changerClavier();
    }

    public void init(){
        numbers=new ArrayList<>();
        opperators=new ArrayList<>();
        plusCount=0;
        sousCount=0;
        divCount=0;
        mulCount=0;
        modCount=0;
        powCount=0;
        isPlSs=false;
        is2ndf = false;
    }

    public void click(ActionEvent actionEvent) {
       Button t= (Button) actionEvent.getSource();
       try {
           switch (t.getText()) {
               case "AC":
                   init();
                   opperation.setText("");
                   break;
               case "x^3":
                   opperation.setText(opperation.getText() + "^3");
                   break;
               case "tan":
               case "sin":
               case "cos":
                   if (is2ndf) {
                       opperation.setText(opperation.getText() + "a" + t.getText() + "(");
                       is2ndf = false;
                   } else
                       opperation.setText(opperation.getText() + t.getText() + "(");
                   break;
               case "XM":
                   Memoire = opperation.getText();
                   break;
               case "RM":
                   opperation.setText(opperation.getText() + Memoire);
                   break;
               case "Pi":
                   opperation.setText(opperation.getText() + "" + Math.PI);
                   break;

               case "Ln/Log":
                   if (is2ndf) {
                       opperation.setText(opperation.getText() + "log(");
                       is2ndf = false;
                   } else
                       opperation.setText(opperation.getText() + "ln(");

                   break;
               case "10^x":
               case "1/x":
                   opperation.setText(opperation.getText() + t.getText().substring(0, t.getText().length() - 1));
                   break;
               case "√x":
               case "e^x":
               case "∛x":
                   opperation.setText(opperation.getText() + t.getText().substring(0, t.getText().length() - 1) + "(");
                   break;
               case "+/-":
                   isPlSs=true;
                   opperation.setText(opperation.getText() + "-");
                   break;

               case "DEG":
                   t.setText("RAD");
                   isDeg = false;
                   break;
               case "RAD":
                   t.setText("DEG");
                   isDeg = true;
                   break;
               case "n!":
                   opperation.setText(opperation.getText() + "!");
                   break;
               case "DEL":
                   if (opperation.getText().length() > 0) {
                       opperation.setText(opperation.getText().substring(0, opperation.getText().length() - 1));
                   if(opperation.getText().length()==0){
                       init();

                    }
                   }
                   else
                       init();
                   break;
               case "=":
                   String p = "";
                   previewText = opperation.getText();
                   for (int i = 0; i < opperation.getText().length(); i++) {

                       char c = opperation.getText().charAt(i);
                       while (c != '+' && c != '-' && c != '/' && c != '*' && c != '^' && c != '%') {
                           //------------aSin et acos et atan----------------------
                           if (c == 'a') {
                               char d = opperation.getText().charAt(i + 1);
                               String n = "";
                               i += 5;
                               c = opperation.getText().charAt(i);
                               while (c != ')') {
                                   n += c;
                                   i++;
                                   c = opperation.getText().charAt(i);
                               }
                               if (isDeg) {
                                   if (d == 's')
                                       p += "" + Math.toDegrees(Math.asin(Double.parseDouble(n)));
                                   else if (d == 'c')
                                       p += "" + Math.toDegrees(Math.acos(Double.parseDouble(n)));
                                   else
                                       p += "" + Math.toDegrees(Math.atan(Double.parseDouble(n)));
                               } else {
                                   if (d == 's')

                                       p += "" + Math.asin(Double.parseDouble(n));
                                   else if (d == 'c')
                                       p += "" + Math.acos(Double.parseDouble(n));
                                   else
                                       p += "" + Math.atan(Double.parseDouble(n));
                               }

                           }
                           //-------------Sin et cos et tan--------------------------
                           else if (c == 's' || c == 'c' || c == 't') {
                               char d = c;
                               String n = "";
                               i += 4;
                               c = opperation.getText().charAt(i);
                               while (c != ')') {
                                   n += c;
                                   i++;
                                   c = opperation.getText().charAt(i);
                               }
                               if (isDeg) {
                                   if (d == 's')
                                       p += "" + Math.sin(Math.toRadians(Double.parseDouble(n)));
                                   else if (d == 'c')
                                       p += "" + Math.cos(Math.toRadians(Double.parseDouble(n)));
                                   else
                                       p += "" + Math.tan(Math.toRadians(Double.parseDouble(n)));
                               } else {
                                   if (d == 's')
                                       p += "" + Math.sin(Double.parseDouble(n));
                                   else if (d == 'c')
                                       p += "" + Math.cos(Double.parseDouble(n));
                                   else
                                       p += "" + Math.tan(Double.parseDouble(n));
                               }

                           }
                           //--------------Ln-----------------------------------------
                           else if (c == 'l' && opperation.getText().charAt(i + 1) == 'n') {
                               i += 3;
                               String n = "";
                               c = opperation.getText().charAt(i);
                               while (c != ')') {
                                   n += c;
                                   i++;
                                   c = opperation.getText().charAt(i);
                               }
                               p += "" + Math.log(Double.parseDouble(n));

                           }
                           //----------------log---------------------------------------
                           else if (c == 'l' && opperation.getText().charAt(i + 1) == 'o') {
                               i += 4;
                               String n = "";
                               c = opperation.getText().charAt(i);
                               while (c != ')') {
                                   n += c;
                                   i++;
                                   c = opperation.getText().charAt(i);
                               }
                               p += "" + Math.log10(Double.parseDouble(n));

                           }
                           //------------------------esponentielle---------------------
                           else if (c == 'e') {
                               i += 3;
                               String n = "";
                               c = opperation.getText().charAt(i);
                               while (c != ')') {
                                   n += c;
                                   i++;
                                   c = opperation.getText().charAt(i);
                               }
                               p += "" + Math.exp((Double.parseDouble(n)));
                               System.out.println(Math.exp((Double.parseDouble(n))));
                           }
                           //----------------------racine carrée-----------------------
                           else if (c == '√') {
                               i += 2;
                               String n = "";
                               c = opperation.getText().charAt(i);
                               while (c != ')') {
                                   n += c;
                                   i++;
                                   c = opperation.getText().charAt(i);
                               }
                               p += "" + Math.sqrt(Double.parseDouble(n));
                           }
                           //----------------------∛----------------------

                           else if (c == '∛') {
                               i += 2;
                               String n = "";
                               c = opperation.getText().charAt(i);
                               while (c != ')') {
                                   n += c;
                                   i++;
                                   c = opperation.getText().charAt(i);
                               }
                               p += "" + Math.pow(Double.parseDouble(n), 0.33333333333333333);
                           }


                           //----------------------------------------------------------
                           else {
                               p += c;
                               if(i+1<opperation.getText().length() &&(
                                       opperation.getText().charAt(i+1)=='t' ||
                                       opperation.getText().charAt(i+1)=='s'||
                                       opperation.getText().charAt(i+1)=='c'||
                                       opperation.getText().charAt(i+1)=='e'||
                                       opperation.getText().charAt(i+1)=='l'||
                                       opperation.getText().charAt(i+1)=='a'||
                                       opperation.getText().charAt(i+1)=='√'||
                                       opperation.getText().charAt(i+1)=='∛')){
                                       opperators.add("*");
                                       mulCount++;
                                       numbers.add(p);
                                       p="";
                               }

                           }
                           i++;
                           if (i < opperation.getText().length()) {
                               c = opperation.getText().charAt(i);
                           } else {
                               break;
                           }
                       }
                       if (!(c != '+' && c != '-' && c != '/' && c != '*' && c != '^' && c != '%')) {
                           opperators.add("" + c);
                           if (c == '+') plusCount++;
                           else if (c == '-') sousCount++;
                           else if (c == '/') divCount++;
                           else if (c == '*') mulCount++;
                           else if (c == '%') modCount++;
                           else powCount++;
                       }

                       if (p != "")
                           numbers.add(p);
                       p = "";
                   }
                   System.out.println(numbers + "-->" + opperators);
                   Moteur();
                   break;
               default:
                   opperation.setText(opperation.getText() + t.getText());
                   break;

           }
       }catch (Exception ex){
           JOptionPane.showMessageDialog(null,"Veuillez verifier votre syntaxe ou consultez le menu d'aide","Alert",JOptionPane.ERROR_MESSAGE);
           init();
           opperation.setText("");
       }
    }

    public void barAction(ActionEvent actionEvent) {
        Button t= (Button) actionEvent.getSource();
        switch (t.getText()){
            case "SHIFT":
                is2ndf =true;
                break;
            case "C":
                opperation.setText("");
                isPlSs=false;
                init();
                break;
            case "OFF":
                t.setText("ON");
                init();
                for (Button boutons:mes_Bouttons) {
                   boutons.setDisable(false);
                }
                menuBar.setDisable(false);
                changerClavier();
                Memoire="";
                isPlSs=false;
                isOn=false;
                break;
            case "ON":
                t.setText("OFF");
                for (Button boutons:mes_Bouttons) {
                    boutons.setDisable(true);
                }
                menuBar.setDisable(true);
                init();
                Memoire="";
                isOn=true;
                break;
        }
    }
    public void Moteur(){
        if(numbers.size()==opperators.size()){
            if((previewText.charAt(0)=='-'&& isPlSs) || (previewText.charAt(0)=='+')){
                numbers.add(0,"0");
            }
            else if(previewText.charAt(0)=='-'&& !isPlSs){
                throw new NumberFormatException();
            }
            else{
                for(int i=0;i<previewText.length();i++){
                    if(isNumber(previewText.charAt(i)) && isNumber(previewText.charAt(i+1))){
                         throw new NumberFormatException();
                    }
                }
            }
        }

        int k=0;
        //----------factorielle---------
        for (String h:numbers) {
            if(h.charAt(h.length()-1)=='!'){
                numbers.set(k,""+fact(h.substring(0,h.length()-1)));
            }
            k++;
        }
        //------------------------------
        String a,b;
        for(int i=0;i<opperators.size();i++){
            while(powCount>0){
                int t=searchOp("^");
                if(t!=-1){
                    numbers.add(t,""+Math.pow(Double.parseDouble(numbers.get(t)),Double.parseDouble(numbers.get(t+1))));
                    if(numbers.size()>t+1)
                        remove(t+1);
                }
                powCount--;
            }
            while(modCount>0){
                int t=searchOp("%");
                if(t!=-1){
                    BigInteger s=new BigInteger(numbers.get(t));
                    BigInteger r=new BigInteger(numbers.get(t+1));
                    numbers.add(t,""+s.mod(r));
                    if(numbers.size()>t+1)
                        remove(t+1);
                }
                modCount--;
            }
            while(divCount>0){
                int t=searchOp("/");
                if(t!=-1){
                    a=numbers.get(t);b=numbers.get(t+1);
                    if(isBin){
                        numbers.add(t,""+(fromDec(""+(toDec(a,2)/toDec(b,2)),2)));
                    }
                    else if(isHex){
                        numbers.add(t,""+(fromDec(""+(toDec(a,16)/toDec(b,16)),16)));
                    }
                    else  if(isOct){
                        numbers.add(t,""+(fromDec(""+(toDec(a,8)/toDec(b,8)),8)));
                    }
                    else numbers.add(t,""+(Double.parseDouble(a)/Double.parseDouble(b)));
                    if(numbers.size()>t+1)
                        remove(t+1);
                }
                divCount--;
            }
            while(mulCount>0){
                int t=searchOp("*");
                if(t!=-1){
                    a=numbers.get(t);b=numbers.get(t+1);
                    if(isBin){
                        numbers.add(t,""+(fromDec(""+(toDec(a,2)*toDec(b,2)),2)));
                    }
                    else if(isHex){
                        numbers.add(t,""+(fromDec(""+(toDec(a,16)*toDec(b,16)),16)));
                    }
                    else if(isOct){
                        numbers.add(t,""+(fromDec(""+(toDec(a,8)*toDec(b,8)),8)));
                    }
                    else numbers.add(t,""+(Double.parseDouble(a)*Double.parseDouble(b)));
                    if(numbers.size()>t+1)
                        remove(t+1);
                }
                mulCount--;
            }
            while(sousCount>0){
                int t=searchOp("-");
                if(t!=-1){
                    a=numbers.get(t);b=numbers.get(t+1);
                    if(isBin){
                        numbers.add(t,""+(fromDec(""+(toDec(a,2)-toDec(b,2)),2)));
                    }
                    else if(isHex){
                        numbers.add(t,""+(fromDec(""+(toDec(a,16)-toDec(b,16)),16)));
                    }
                    else if(isOct){
                        numbers.add(t,""+(fromDec(""+(toDec(a,8)-toDec(b,8)),8)));
                    }
                    else{ numbers.add(t,""+(Double.parseDouble(a)-Double.parseDouble(b)));}
                    if(numbers.size()>t+1)
                        remove(t+1);
                }
                sousCount--;
            }
            while(plusCount>0){
                int t=searchOp("+");
                if(t!=-1){
                    a=numbers.get(t);b=numbers.get(t+1);
                    if(isBin){
                        numbers.add(t,""+(fromDec(""+(toDec(a,2)+toDec(b,2)),2)));
                    }
                    else  if(isHex){
                        numbers.add(t,""+(fromDec(""+(toDec(a,16)+toDec(b,16)),16)));
                    }
                    else if(isOct){
                        numbers.add(t,""+(fromDec(""+(toDec(a,8)+toDec(b,8)),8)));
                    }
                    else numbers.add(t,""+(Double.parseDouble(a)+Double.parseDouble(b)));
                    if(numbers.size()>t+1)
                        remove(t+1);
                }
                plusCount--;
            }
        }
        if(numbers.size()>0 && !numbers.get(0).equals("NaN")&&!numbers.get(0).equals("Infinity")) {
            opperation.setText(numbers.get(0));
            System.out.println(results);
            results.get(base).add(numbers.get(0));
           historique.get(base).add(previewText);
            System.out.println(historique.get(0));
            previewText=numbers.get(0);
        }
        else if(numbers.get(0).equals("NaN")){

            opperation.setText("ERREUR::not a number::");

        }
        else if(numbers.get(0).equals("Infinity")||numbers.get(0).equals("-Infinity")){
            opperation.setText("ERREUR::division by 0::");

        }
        init();
    }

    //-------------------------------------------------------------------------
    public int searchOp(String c){
        for (int i = 0; i<opperators.size() ; i++) {
            if(opperators.get(i).equals(c)){
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }

    //-----------------------------------------------------------------------------

//-----------------------------------------------------------------------------
    public void remove(int t){
        numbers.remove(t);
        numbers.remove(t);
        opperators.remove(t-1);

    }
    //------------------------------------------------------------------------------


//---------------------------------------
    public static void initHistorique(){
        historique=new ArrayList<>();
        historique.add(new ArrayList<>());
        historique.add(new ArrayList<>());
        historique.add(new ArrayList<>());
        historique.add(new ArrayList<>());

        results=new ArrayList<>();
        results.add(new ArrayList<>());
        results.add(new ArrayList<>());
        results.add(new ArrayList<>());
        results.add(new ArrayList<>());

    }


    public void mOption(Event event) throws IOException {
        MenuItem mi= (MenuItem) event.getSource();
        switch (mi.getText()){
            case "Quitter":
                System.exit(0);
                break;
            case "Historique":
                if(results.get(base).size()>0) {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/vues/historique.fxml")));
                    second = new Stage();

                    second.setScene(scene);
                    second.initOwner(Main.getStage());
                    second.initModality(Modality.WINDOW_MODAL);
                    second.show();
                }else {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas d'historique", "Historique", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
        }
    }
    public void changerClavier(){
        previewText="";
        Memoire="";
        opperation.setText("");
        if(isBin){
            for (Button btn:mes_Bouttons) {
                btn.setDisable(!btn.getText().equals("0") && !btn.getText().equals("1") &&
                        !btn.getText().equals("-") && !btn.getText().equals("*") &&
                        !btn.getText().equals("/") && !btn.getText().equals("+") &&
                        !btn.getText().equals("+/-") && !btn.getText().equals("=")
                        && !btn.getText().equals("DEL") && !btn.getText().equals("XM")
                        && !btn.getText().equals("RM") && !btn.getText().equals("AC"));
            }
        }
        else if(isOct){
            for (Button btn:mes_Bouttons) {
                btn.setDisable(!btn.getText().equals("0") && !btn.getText().equals("1") && !btn.getText().equals("-") &&
                        !btn.getText().equals("*") && !btn.getText().equals("/") &&
                        !btn.getText().equals("+") && !btn.getText().equals("+/-") &&
                        !btn.getText().equals("=") &&
                        !btn.getText().equals("2") && !btn.getText().equals("3") &&
                        !btn.getText().equals("4") && !btn.getText().equals("5") &&
                        !btn.getText().equals("6") && !btn.getText().equals("7")
                        && !btn.getText().equals("DEL") && !btn.getText().equals("XM") &&
                        !btn.getText().equals("RM") && !btn.getText().equals("AC"));
            }
        }
        else if(isHex){

            for (Button btn:mes_Bouttons) {
                btn.setDisable(!btn.getText().equals("0") && !btn.getText().equals("1") && !btn.getText().equals("-") &&
                        !btn.getText().equals("*") && !btn.getText().equals("/") &&
                        !btn.getText().equals("2") && !btn.getText().equals("3") &&
                        !btn.getText().equals("4") && !btn.getText().equals("5") &&
                        !btn.getText().equals("6") && !btn.getText().equals("7") &&
                        !btn.getText().equals("8") && !btn.getText().equals("9") &&
                        !btn.getText().equals("A") && !btn.getText().equals("B") &&
                        !btn.getText().equals("C") && !btn.getText().equals("D") &&
                        !btn.getText().equals("E") && !btn.getText().equals("F") &&
                        !btn.getText().equals("+") && !btn.getText().equals("+/-") &&
                        !btn.getText().equals("=") && !btn.getText().equals("DED") &&
                        !btn.getText().equals("XM") && !btn.getText().equals("RM") &&
                        !btn.getText().equals("AC") &&!btn.getText().equals("DEL"));
            }

        }
        else if(isDec){
            for (Button btn:mes_Bouttons) {
                btn.setDisable(btn.getText().equals("A") || btn.getText().equals("B") ||
                        btn.getText().equals("C") || btn.getText().equals("D") ||
                        btn.getText().equals("E") || btn.getText().equals("F"));
            }
        }

    }
    public void mBase(Event event) {
        MenuItem mi= (MenuItem) event.getSource();
        System.out.println(mi.getText());
        switch (mi.getText()){
            case "Binaire":
                base= 0;
                isBin=true; isDec=false; isHex=false; isOct=false;
                changerClavier();
                break;
            case "Octale":
                base= 1;
                isBin=false; isDec=false; isHex=false; isOct=true;
                changerClavier();
                break;
            case "Décimale":
                base= 2;
                isBin=false; isDec=true; isHex=false; isOct=false;
                changerClavier();
                break;
            case "Hexadécimale":
                base= 3;
                isBin=false; isDec=false; isHex=true; isOct=false;
                changerClavier();
                break;
        }
    }

    public void mAide() {
    Helper h=new Helper();
    h.doer();
    }
}
