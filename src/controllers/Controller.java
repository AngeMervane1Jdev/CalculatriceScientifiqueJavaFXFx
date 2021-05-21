package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Main;
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
    public Button bDEG,bC,bOFF,bD,bP1,bEXP10,bsin,b7,b3;
    public Button b4, b1,b0,bA, bE,bP2,bEXP,b8,bcos, b6;
    public Button bB,b2,bpoint,bF, bCHAP,bFACT, btan,b9;
    public Button bEXP3,b5,bc,bmoins,bSqrt, bdivX,bLn,bmod;
    public Button brm,bplus,bmoin,b10E,bDEL,brac3,bPi,bac;
    public Button bxm,bfois,bdiv,bequal;
    public TextField opperation;
    public static TextField opperationPassed;
    public Button bshift;
    public MenuBar menuBar;
    public Button Mylistener;
    private String Memoire;
    private boolean is2ndf=false,isBin=false, error=false,isHex=false,isDec=true,isOct=false,isDeg=true;
    private ArrayList<String>numbers=new ArrayList<>(), opperators=new ArrayList<>();
    private String previewText;
    public static int base=2;
    public Alert al=new Alert(null,null);

    public static ArrayList<ArrayList<String>> results, historique;
    private List<Button> mes_Bouttons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        initHistorique();
        opperationPassed=opperation;
        mes_Bouttons=asList(bDEG,bD,bP1,bEXP10,bsin,b7,b4,b1,b0,bA,bE,bP2,bEXP,bcos,b8,b5,bB,b2,bpoint,bF,bCHAP,bFACT,btan,b9,b6,b3,bmoins,bc,bEXP3,bSqrt,bLn,bmod,bplus,brm,bmoin,b10E,bDEL,bdivX,brac3,bPi,bac,bxm,bfois,bdiv,bequal);
        changerClavier();
    }

    public void init(){
        numbers=new ArrayList<>();
        opperators=new ArrayList<>();
        is2ndf = false;
    }

    public void click(ActionEvent actionEvent) {
        bshift.setOpacity(1);
        if(error) {
            opperation.setText("");
            error=false;
        }
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


                           else {
                               p += c;
                               //----------------------on transforme les calculs de la forme 2sin(30) en 2*sin(30)------------------------------------

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
                                       numbers.add(p);
                                       p="";
                               }
                               //---------------------------------------------------------------

                           }
                           i++;
                           if (i < opperation.getText().length()) {
                               c = opperation.getText().charAt(i);
                           } else {
                               break;
                           }
                       }
                       if (c == '+'|| c == '-'|| c == '/' || c == '*' || c == '^' || c == '%') {
                           opperators.add("" + c);
                       }

                       if (p != "")
                           numbers.add(p);
                       p = "";
                   }

                   calculator();
                   break;
               default:
                   opperation.setText(opperation.getText() + t.getText());
                   break;

           }
       }catch (Exception ex){
           al.setAlertType(Alert.AlertType.ERROR);
           al.setContentText("Veuillez verifier votre syntaxe ou consultez le menu d'aide");
           al.setTitle("Operation error!");
           al.show();
           init();
           opperation.setText("");
       }
    }

    public void barAction(ActionEvent actionEvent) {
        bshift.setOpacity(1);
        if(error) {
            opperation.setText("");
            error=false;
        }
        Button t= (Button) actionEvent.getSource();
        switch (t.getText()){
            case "SHIFT":
                is2ndf =true;
                t.setOpacity(0.7);
                break;
            case "C":
                opperation.setText("");

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
                opperation.setText("");
                Memoire="";

                boolean isOn = false;
                break;
            case "ON":
                opperation.setText("");
                t.setText("OFF");
                for (Button boutons:mes_Bouttons) {
                    boutons.setDisable(true);
                }
                menuBar.setDisable(true);
                init();
                Memoire="";
                isOn =true;
                break;
        }
    }
    //--------------------------------------Calculator-------------------------------------------
    public void calculator(){
        //-----------Lorsque l'utilisateur utilise un nombre négatif au début-----------
        if(numbers.size()==opperators.size()){
            if(previewText.charAt(0)=='-' || previewText.charAt(0)=='+'){
                numbers.add(0,"0");
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
        //---------------------Gestion des factorielle---------------------------
        for (String h:numbers) {
            if(h.charAt(h.length()-1)=='!'){
                numbers.set(k,""+fact(h.substring(0,h.length()-1)));
            }
            k++;
        }
//-----------------------------Gestion des autres opérateurs----------------------------------
        String a,b;
        int t;
        for(int i=0;i<opperators.size();i++){
            //------------^-------------------
           do{
                t= searchOpPosition("^");
                if(t>=0){
                    numbers.add(t,""+Math.pow(Double.parseDouble(numbers.get(t)),Double.parseDouble(numbers.get(t+1))));
                    if(numbers.size()>t+1)
                        remove(t+1);
                }
            } while(t>=0);
            //------------%-------------------
            do{
                t= searchOpPosition("%");
                if(t>=0){
                    BigInteger s=new BigInteger(numbers.get(t));
                    BigInteger r=new BigInteger(numbers.get(t+1));
                    numbers.add(t,""+s.mod(r));
                    if(numbers.size()>t+1)
                        remove(t+1);

                }
            }while(t>=0);
            //------------/-------------------
           do{
                t= searchOpPosition("/");
                if(t>=0){
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
            } while(t>=0);
            //------------*-------------------
           do{
                t= searchOpPosition("*");
                if(t>=0){
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
            } while(t>=0);
            //------------'-'-------------------
           do{
                t= searchOpPosition("-");
                if(t>=0){
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
            }while(t>=0);
            //------------'+'------------------
            do{
                t= searchOpPosition("+");
                if(t>=0){
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

            }while(t>=0);
        }

      afficheResult();
    }
//-------------------------------Affiche résultats-------------------------------
    private void afficheResult() {
        if(!isDec && numbers.get(0).charAt(0)=='-'){
            al.setAlertType(Alert.AlertType.INFORMATION);
            al.setContentText("Désolé nous ne gérons pas les nombres négatifs");
            al.show();
        }
        else if(numbers.size()>0 && !numbers.get(0).equals("NaN")&&!numbers.get(0).equals("Infinity")) {
            opperation.setText(numbers.get(0));
            results.get(base).add(numbers.get(0));
            historique.get(base).add(previewText);
            previewText=numbers.get(0);
        }
        else if(numbers.get(0).equals("NaN")){

            opperation.setText("ERREUR::not a number::");
            error=true;

        }
        else if(numbers.get(0).equals("Infinity")||numbers.get(0).equals("-Infinity")){
            opperation.setText("ERREUR::division by 0::");
           error=true;
        }
        init();
    }

    //-----------------------------searchOpPosition--------------------------------------------
    public int searchOpPosition(String c){
        for (int i = 0; i<opperators.size() ; i++) {
            if(opperators.get(i).equals(c)){
                return i;
            }
        }
        return -1;
    }

    //-----------------------------------------------------------------------------

//--------------------------------remove---------------------------------------------
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
                    al.setAlertType(Alert.AlertType.INFORMATION);
                    al.setContentText("Vous n'avez pas d'historique");
                    al.setTitle("Empty historique");
                    al.show();
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
    h.changeView();
    }
}