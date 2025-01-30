package Dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Field;

@Getter
@Setter
@ToString
public class Employee {
    private int emp_no;
    private String birth_date;
    private String first_name;
    private String last_name;

    enum Gender {
        M,F
    }

    private Gender gender;
    private String hire_date;

}
