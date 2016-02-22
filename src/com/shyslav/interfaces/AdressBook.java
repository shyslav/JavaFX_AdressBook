package com.shyslav.interfaces;

import com.shyslav.model.blotter;

/**
 * Created by Shyshkin Vladyslav on 22.02.2016.
 */
public interface AdressBook {
    //добавление
    void add(blotter Person);
    //удаление
    void delete(blotter Person);
    //обновление
    void update(blotter Person);
}
