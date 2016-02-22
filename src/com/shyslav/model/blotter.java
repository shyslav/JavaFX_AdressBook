package com.shyslav.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Shyshkin Vladyslav on 22.02.2016.
 */
public class blotter {
    //private String fio;
    private SimpleStringProperty fio = new SimpleStringProperty("");
    //private String phone;
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public blotter() {
    }

    public blotter(String fio, String phone) {
        this.fio = new SimpleStringProperty(fio);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getFio() {
        return fio.get();
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public SimpleStringProperty fioProperty() {
        return fio;
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }
}
