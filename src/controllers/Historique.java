package controllers;

import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Historique implements Initializable {

    public TextArea HistArea;
    //--------------------------------------------------
    public void afficherHistorique(){
        String newHist="";
        int index=0;
        System.out.println(Controller.base);
        for (String t:Controller.historique.get(Controller.base)) {
            newHist+=(index+1)+"°) "+t+"="+Controller.results.get(Controller.base).get(index)+"\n";
            index++;
        }
        if(newHist.equals("")){
            newHist="Votre historique est vide";
        }
        HistArea.setText(newHist);
    }
    public void click(ActionEvent actionEvent) {
        Button t= (Button) actionEvent.getSource();
        if(t.getText().equals("VIDER")){
            if (Controller.results.get(Controller.base).size()>0){
                HistArea.setText("");
            }

            Controller.initHistorique();

        }

        else if(t.getText().equals("SUPPRIMER"))
        {
            if(Controller.historique.get(Controller.base).size()==1){
                Controller.historique.get(Controller.base).remove(0);
                Controller.results.get(Controller.base).remove(0);
                afficherHistorique();

            }
            else {
                try {
                    String rep = JOptionPane.showInputDialog("Numéros de la ligner à supprimer");
                    if (rep != null) {
                        Controller.historique.get(Controller.base).remove(Integer.parseInt(rep) - 1);
                        Controller.results.get(Controller.base).remove(Integer.parseInt(rep) - 1);
                        afficherHistorique();

                    }


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrez un entier");
                } catch (IndexOutOfBoundsException ex) {
                    if (Controller.historique.get(Controller.base).size() > 0)
                        JOptionPane.showMessageDialog(null, "Veuillez choisir un numéro comprit entre 1 et " + Controller.historique.get(Controller.base).size());
                    else
                        JOptionPane.showMessageDialog(null, "Votre hstorique est vide");
                }
            }
        }
        else {
            if (Controller.historique.get(Controller.base).size() == 1) {
                Controller.opperationPassed.setText(Controller.historique.get(Controller.base).get(0));
                Controller.second.close();
            } else {
                try {
                    String rep = JOptionPane.showInputDialog("Quelle numéro de ligne ?");
                    System.out.println(rep);
                    if (rep != null) {
                        Controller.opperationPassed.setText(Controller.historique.get(Controller.base).get(Integer.parseInt(rep) - 1));
                        Controller.second.close();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrez un entier");
                } catch (IndexOutOfBoundsException ex) {
                    if (Controller.historique.get(Controller.base).size() > 0)
                        JOptionPane.showMessageDialog(null, "Veuillez choisir un numéro comprit entre 1 et " + Controller.historique.get(Controller.base).size());
                    else
                        JOptionPane.showMessageDialog(null, "Votre hstorique est vide");
                }
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficherHistorique();
    }
}
