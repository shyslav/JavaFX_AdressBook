package com.shyslav.controller;

import com.shyslav.interfaces.impls.CollectionAdress;
import com.shyslav.model.blotter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Created by Shyshkin Vladyslav on 22.02.2016.
 */
public class EditController {
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField textFIO;
    @FXML
    private TextField textPhone;
    @FXML
    private Label lebFIO;
    @FXML
    private Label lebPhone;

    private blotter Person;
    private String Checked;
    private CollectionAdress ca;


    public void setPerson(blotter person) {
        Person = person;
        textPhone.setText(person.getPhone());
        textFIO.setText(person.getFio());
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void actionSave(ActionEvent actionEvent) {
        if(Checked.equals("btnEdit")) {
            System.out.println("editButton"+Checked);
            Person.setFio(textFIO.getText());
            Person.setPhone(textPhone.getText());
        }else if(Checked.equals("addButton"))
        {
            ca.add(new blotter(textFIO.getText(),textPhone.getText()));
        }
            actionClose(actionEvent);
    }

    public void setCa(CollectionAdress ca) {
        this.ca = ca;
    }

    public void setChecked(String checked) {
        Checked = checked;
    }
}
