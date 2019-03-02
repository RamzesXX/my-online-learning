package com.khanchych.udemy.javaindepth.exceptions;

public class MissingGradeException extends Exception {
    private int studentId;


    public MissingGradeException(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }
}
