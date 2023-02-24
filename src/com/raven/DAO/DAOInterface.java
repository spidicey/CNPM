/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.DAO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author thinh nguyen
 */
public interface DAOInterface<T> {

    List<T> getAll();
 
    Optional<T> get(int id);
 
    boolean save(T t);
 
    boolean update(T t);
 
    boolean delete(T t);
}
