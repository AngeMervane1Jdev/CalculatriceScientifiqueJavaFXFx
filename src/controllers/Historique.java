package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class Historique implements Initializable {
   public Alert al=new Alert(Alert.AlertType.ERROR);
    public TextArea HistArea;
    public Button allHist;
    public boolean onClick=true;
    public VBox actionsContainer;

    //--------------------------------------------------
    public void afficherHistorique(){
        String newHist="";
        int index=0;
        for (String t:Controller.historique.get(Controller.base)) {
            newHist+=(index+1)+"°) "+t+"="+Controller.results.get(Controller.base).get(index)+"\n";
            index++;
        }
        actionsContainer.setVisible(true);
        HistArea.setText(newHist);
    }
    //--------------------------------Afficher tout l'historique--------------------------------------
    public void afficheAllHist(){
        actionsContainer.setVisible(false);
        String newHist="";
        int index=0,base=0;
        for (ArrayList<String>t:Controller.historique) {
            index=0;
            //----------------------------------------------------
            if(base==0){
                newHist+="\n-----------Base Binaire---------------\n";
            }
            if(base==1){
                newHist+="\n-----------Base Octale----------------\n";
            }
            if(base==2){
                newHist+="\n-----------Base Décimale--------------\n";
            }
            if(base==3){
                newHist+="\n-----------Base Hexadécimale----------\n";
            }
            //----------------------------------------------------
            for (String a:t) {
                newHist+=(index+1)+"°) "+a+"="+t.get(index)+"\n";
                index++;
            }

            base++;
        }
        HistArea.setText(newHist);
    }
    //---------------------------------------------------------------------
    public void click(ActionEvent actionEvent) {
        if(Controller.results.get(Controller.base).size()!=0){
            Button t= (Button) actionEvent.getSource();
            if(t.getText().equals("VIDER")){
                if (Controller.results.get(Controller.base).size()>0){
                    HistArea.setText("");
                }
                Controller.initHistorique();

            }
            //------------------------
            else if(t==allHist){
                if(onClick){
                    afficheAllHist();
                    allHist.setText("Historique de la base actuelle");
                }
                else{
                    allHist.setText("Retrouvez tout votre historique ici");
                    afficherHistorique();
                }
                onClick=!onClick;
            }
            //----------------------
            else if(t.getText().equals("SUPPRIMER"))
            {
                if(Controller.historique.get(Controller.base).size()==1){
                    Controller.historique.get(Controller.base).remove(0);
                    Controller.results.get(Controller.base).remove(0);
                    afficherHistorique();

                }
                else {
                    try {
                        TextInputDialog dialog=new TextInputDialog();
                        dialog.setContentText("Numéros de la ligner à supprimer");
                        dialog.setTitle("Suppression");
                        Optional<String> result=dialog.showAndWait();
                        if(result.isPresent()){
                            String rep=result.get();
                            Controller.historique.get(Controller.base).remove(Integer.parseInt(rep) - 1);
                            Controller.results.get(Controller.base).remove(Integer.parseInt(rep) - 1);
                            afficherHistorique();

                        }

                    } catch (NumberFormatException ex) {
                        al.setContentText("Veuillez entrez un entier");
                        al.show();
                    } catch (IndexOutOfBoundsException ex) {
                        if (Controller.historique.get(Controller.base).size() > 0)
                            al.setContentText("Veuillez choisir un numéro comprit entre 1 et " + Controller.historique.get(Controller.base).size());
                        al.show();
                    }
                }
            }
            else {
                if (Controller.historique.get(Controller.base).size() == 1) {
                    Controller.opperationPassed.setText(Controller.historique.get(Controller.base).get(0));
                    Controller.second.close();
                } else {
                    try {
                        TextInputDialog dialog=new TextInputDialog();
                        dialog.setContentText("Quelle numéro de ligne ?");
                        dialog.setTitle("Rapide use");
                        Optional<String> result=dialog.showAndWait();
                        if (result.isPresent()) {
                            String rep=result.get();
                            Controller.opperationPassed.setText(Controller.historique.get(Controller.base).get(Integer.parseInt(rep) - 1));
                            Controller.second.close();
                        }
                    } catch (NumberFormatException ex) {
                        al.setContentText("Veuillez entrez un entier");
                        al.show();
                    } catch (IndexOutOfBoundsException ex) {
                        if (Controller.historique.get(Controller.base).size() > 0)
                            al.setContentText("Veuillez choisir un numéro comprit entre 1 et " + Controller.historique.get(Controller.base).size());
                        else
                            al.setContentText("Votre hstorique est vide");
                        al.show();
                    }
                }
            }
        }
        else{
            al.setAlertType(Alert.AlertType.INFORMATION);
            al.setContentText("Votre historique est vide");
            al.showAndWait();
            al.setAlertType(Alert.AlertType.ERROR);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficherHistorique();

    }
}
