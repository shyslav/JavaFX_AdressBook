package com.shyslav.sample;

import com.shyslav.controller.EditController;
import com.shyslav.interfaces.impls.CollectionAdress;
import com.shyslav.model.blotter;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.io.ObjectInput;

public class Controller {
    private CollectionAdress ca = new CollectionAdress();
    @FXML
    private Button addButton;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private TableView tableFioPhone;
    @FXML
    //<blotter, в каком формате хранить столбец> с какого взять и в какой преобразовать
    private TableColumn <blotter,String> columnFIO;
    @FXML
    private TableColumn <blotter,String> columnPhone;
    @FXML
    private Label labAmount;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditController editController;
    private Stage editDialogStage;

    @FXML
    //Вызов инициализации после считывания fxml
    private void initialize()
    {
        //какие обьекты нам нужны из модели
        columnFIO.setCellValueFactory(new PropertyValueFactory<blotter,String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<blotter,String>("phone"));
        //заполнить лист
        ca.initialTable();
        //записать обзервебл лист в таблицу
        tableFioPhone.setItems(ca.getPesronsList());
        //изменить количество записей при изминении листа
        ca.getPesronsList().addListener(new ListChangeListener<blotter>() {
            @Override
            public void onChanged(Change<? extends blotter> c) {
                updateAmountData();
            }
        });
        updateAmountData();
        try {

            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateAmountData()
    {
        labAmount.setText("Количество записей: " + ca.getPesronsList().size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        //если текущей обьект не кнопка, выйти из метода
        if(!(source instanceof Button))
        {
            return;
        }
        Button clickedButton = (Button) source;

        //получить выделенную строку
        blotter selectedPerson = (blotter) tableFioPhone.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        if(selectedPerson==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Вы не выбрали ни одной записи");
            alert.setContentText("Для выбора записи из таблицы необходимо нажать на нее один раз!");
            alert.showAndWait();
            return;
        }
        editController.setPerson(selectedPerson);
        switch (clickedButton.getId())
        {
            case "btnSearch":
                System.out.println("btnSearch" + selectedPerson.getFio() + selectedPerson.getPhone());
                break;
            case "btnDelete":
                System.out.println("btnDelete" +  selectedPerson.getFio() + selectedPerson.getPhone());
                break;
            case "btnEdit":
                showDialog(parentWindow);
                System.out.println("btnEdit" +  selectedPerson.getFio() + selectedPerson.getPhone());
                break;
            case "addButton":
                System.out.println("addButton" +  selectedPerson.getFio() + selectedPerson.getPhone());
                break;
        }

    }
    //Функция добавления или правки табличных данных
    private void showDialog(Window parentWindow)
    {
        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(parentWindow);
        }
            editDialogStage.show();
//            try{
//            Stage stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("../fxml/edit.fxml"));
//            stage.setTitle(name);
//            stage.setMinHeight(100);
//            stage.setMinWidth(450);
//            //запретить растяжение
//            stage.setResizable(false);
//            stage.setScene(new Scene(root));
//            //сделать окно модальным
//            stage.initModality(Modality.WINDOW_MODAL);
//            //определить это окно дочерним к тому откуда вызвано данное окно
//            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
//            stage.show();
//        }catch (IOException ex)
//        {
//            ex.printStackTrace();
//        }
    }



}
