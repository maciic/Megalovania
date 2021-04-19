/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.time.LocalDate;

/**
 *
 * @author student
 */
public class Model {
    private Student student;

    public Student getStudent() {
        return student;
    }

    public Model() {
        student = new Student("Robert Smith", 55, LocalDate.parse("1991-12-03"));
    }   
}
