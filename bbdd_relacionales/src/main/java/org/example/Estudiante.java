package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class Estudiante {

    private int nia;
    private String nombre;
    private LocalDate fecha_nacimiento;

}
