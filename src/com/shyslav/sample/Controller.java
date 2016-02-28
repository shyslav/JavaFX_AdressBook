package com.shyslav.sample;

import com.shyslav.controller.EditController;
import com.shyslav.interfaces.impls.CollectionAdress;
import com.shyslav.model.blotter;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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
    private TableColumn<blotter, String> columnFIO;
    @FXML
    private TableColumn<blotter, String> columnPhone;
    @FXML
    private Label labAmount;
    @FXML
    private TextField txtSearch;

    private Stage mainStage;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditController editController;
    private Stage editDialogStage;
    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        //какие обьекты нам нужны из модели
        columnFIO.setCellValueFactory(new PropertyValueFactory<blotter, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<blotter, String>("phone"));
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
            fxmlLoader.setResources(ResourceBundle.getBundle("com.shyslav.Locale.locale"));
            fxmlEdit = fxmlLoader.load();
            editController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateAmountData() {
        labAmount.setText(resourceBundle.getString("amountData")+": " + ca.getPesronsList().size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        //если текущей обьект не кнопка, выйти из метода
        if (!(source instanceof Button)) {
            return;
        }
        Button clickedButton = (Button) source;

        //получить выделенную строку
        blotter selectedPerson = (blotter) tableFioPhone.getSelectionModel().getSelectedItem();
        //Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        switch (clickedButton.getId()) {
            case "btnSearch":
                SearchAction(txtSearch.getText());
                System.out.println(txtSearch.getText());
//                System.out.println("btnSearch" + selectedPerson.getFio() + selectedPerson.getPhone());
                break;
            case "btnDelete":
                DeleteAction(selectedPerson);
                System.out.println("btnDelete" + selectedPerson.getFio() + selectedPerson.getPhone());
                break;
            case "btnEdit":
                if (selectedPerson == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Вы не выбрали ни одной записи");
                    alert.setContentText("Для выбора записи из таблицы необходимо нажать на нее один раз!");
                    alert.showAndWait();
                    return;
                }
                editController.setPerson(selectedPerson);
                editController.setChecked(clickedButton.getId());
                showDialog();
                System.out.println("btnEdit" + selectedPerson.getFio() + selectedPerson.getPhone());
                break;
            case "addButton":
                editController.setCa(ca);
                editController.setChecked(clickedButton.getId());
                showDialog();
                break;
        }

    }

    //Функция добавления или правки табличных данных
    private void showDialog() {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle(resourceBundle.getString("Edit"));
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }
        editDialogStage.show();
    }

    private void DeleteAction(blotter Person) {
        ca.delete(Person);
    }

    private void SearchAction(String searchText) {
        ObservableList<blotter> list = FXCollections.observableArrayList();
        for (int i = 0; i < ca.getPesronsList().size(); i++) {
            if (ca.getPesronsList().get(i).getPhone().contains(searchText) || ca.getPesronsList().get(i).getFio().contains(searchText)) {
                //записать обзервебл лист в таблицу
                list.add(ca.getPesronsList().get(i));
                System.out.println(list.size());
                tableFioPhone.setItems(list);
                return;
            }
        }
        tableFioPhone.setItems(ca.getPesronsList());
        return;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void changeLanguage(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (!(source instanceof Button)) {
            return;
        }
        Button clickedButton = (Button) source;
        switch (clickedButton.getId()) {
            case "ChangeEN":
                Main.setDEFAULT_LOCALE(new Locale("en")); // change to english
                break;
            case "ChangeRU":
                Main.setDEFAULT_LOCALE(new Locale("ru")); // change to rus
                break;
        }
        mainStage.close();
        Main reload = new Main();
        try {
            reload.start(mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
