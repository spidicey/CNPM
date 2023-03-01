/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author thinh nguyen
 */
public class TypeTrainning {
    private String idTypeTrainning;
    private String nameTypeTrainning;

    public TypeTrainning(String idTypeTrainning, String nameTypeTrainning) {
        this.idTypeTrainning = idTypeTrainning;
        this.nameTypeTrainning = nameTypeTrainning;
    }

    public TypeTrainning() {
    }

    public String getIdTypeTrainning() {
        return idTypeTrainning;
    }

    public void setIdTypeTrainning(String idTypeTrainning) {
        this.idTypeTrainning = idTypeTrainning;
    }

    public String getNameTypeTrainning() {
        return nameTypeTrainning;
    }

    public void setNameTypeTrainning(String nameTypeTrainning) {
        this.nameTypeTrainning = nameTypeTrainning;
    }

    @Override
    public String toString() {
        return "TypeTrainning{" + "idTypeTrainning=" + idTypeTrainning + ", nameTypeTrainning=" + nameTypeTrainning + '}';
    }
    
}
