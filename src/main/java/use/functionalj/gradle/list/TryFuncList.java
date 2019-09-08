package use.functionalj.gradle.list;

import static use.functionalj.gradle.list.Employee.theEmployee;

import java.time.LocalDate;

import functionalj.list.FuncList;
import functionalj.types.Struct;
import lombok.val;

public class TryFuncList {
    
    interface Models {
        
        @Struct
        void Employee(
                String    name, 
                LocalDate birthday, 
                int       compensation);
        
        @Struct
        void Order(
                String         id,
                LocalDate      date,
                FuncList<Item> items);
        
        @Struct
        void Item(
                String name, 
                int    quantity);
        
    }
    
    public static void main(String[] args) {
        val employees = FuncList.of(
                    new Employee("Kailyn Kerenza", LocalDate.of(1970,  8, 10), 59000),
                    new Employee("Randi Kathlyn",  LocalDate.of(1971, 10, 13), 43000),
                    new Employee("Layla Darrin",   LocalDate.of(1976,  2, 25), 63000),
                    new Employee("Bambi Vianne",   LocalDate.of(1998,  7, 20), 64000),
                    new Employee("Lila Rowina",    LocalDate.of(1999,  3, 22), 82000)
                );
        
        val Jan_1st_2019 = LocalDate.of(2019, 1, 1);
        double averageCompensationOfOldEmplyees = employees
                .filter  (theEmployee.birthday.until(Jan_1st_2019).getYears().thatGreaterThan(40))
                .mapToInt(theEmployee.compensation)
                .average()
                .orElse(0);
        
        System.out.println(averageCompensationOfOldEmplyees);
    }
    
}







