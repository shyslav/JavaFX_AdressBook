package com.shyslav.interfaces.impls;

import com.shyslav.interfaces.AdressBook;
import com.shyslav.model.blotter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 22.02.2016.
 */
public class CollectionAdress implements AdressBook {
    //private ArrayList<blotter> pesronsList = new ArrayList<>();
    private ObservableList<blotter> pesronsList = FXCollections.observableArrayList();

    @Override
    public void add(blotter Person) {
        pesronsList.add(Person);
    }

    @Override
    public void delete(blotter Person) {
        pesronsList.remove(Person);
    }

    @Override
    public void update(blotter Person) {

    }
    public void initialTable() {
        pesronsList.add(new blotter("Ivanov Ivan Ivanovich", "0913030595"));
        pesronsList.add(new blotter("Petrob Petrov Vasyleivch", "0935825956"));
    }

    public ObservableList<blotter> getPesronsList() {
        return pesronsList;
    }
}
