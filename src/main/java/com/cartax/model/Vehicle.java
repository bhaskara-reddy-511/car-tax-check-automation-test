package com.cartax.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Vehicle {

    @CsvBindByName(column = "REGISTRATION")
    String registration;
    @CsvBindByName(column = "MAKE")
    String make;
    @CsvBindByName(column = "MODEL")
    String model;
    @CsvBindByName(column = "COLOR")
    String color;
    @CsvBindByName(column = "YEAR")
    String year;

}
