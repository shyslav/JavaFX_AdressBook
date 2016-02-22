package com.shyslav.controller;

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
        Person.setFio(textFIO.getText());
        Person.setPhone(textPhone.getText());
        actionClose(actionEvent);
    }
}
