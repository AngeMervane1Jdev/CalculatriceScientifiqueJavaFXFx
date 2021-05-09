package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Helper implements Initializable {
    public Label helpText;
    String text;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       text="Bienvenue sur notre page d'aide.\n\n" +
                "Voici ci-dessous quelques:\n1-Guides d'utilisation\n" +
                "En haut de la calculatrice vous disposez de trois options\n" +
                "=> Option où vous pouvez visiter votre historique de calcul si\n" +
                "vous en avez. \n" +
                "=> Base où vous pouvez changer de base de calcul vous y trouverez\n"+
                "les bases décimale,Octale,Binaire et Hexadécimale)\n" +
                "=> Aide pour vous aider à faire une bonne expérience d'utilisateur\n\n" +
                "2-Conseils\n" +
                "Tout calcul requiert l'utilisation d'un oppérateur quand c'est nécessaire\n" +
                "Evitez les parenthèses au profit du signe *.\n" +
                "Exemple : 2*3 en lieu et place de (2)(3)\n" +
                "Evitez l'exces d'oppérateurs (2**2 et 2//2 et 2---2 et 2+++*-2) \n" +
                "Vous pouvez utiliser\"shift\" pour arccos, arcsin arctan (shift+sin) par excemple\n" +
                "Passez en Degré ou en radian avec le boutton \"DEG\"\n" +
                "Lorsque vous changer de base, les oppérations sont réduites mais nous\n" +
               "Utilisez le bouton +/- lorsque vous voulez faire par exemple (-5+5)\n" +
               "N'utilisez en aucun cas les parenthèses sauf si on vous en propose ou quand\n" +
               "vous les effacez par inadvertance et qu'il faudra les remettre.\n" +
                "vous assurons que le strict nécésaire y est\n\n" +
                "Merci d'avoir lu et faites en un bon usage.";
                 helpText.setText(text);

    }
    public void doer() {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/vues/helper.fxml"));
            Main.getStage().setScene(new Scene(root,600,700));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exitHelper(ActionEvent actionEvent) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/vues/principalView.fxml"));
            Main.getStage().setScene(new Scene(root,600,700));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}