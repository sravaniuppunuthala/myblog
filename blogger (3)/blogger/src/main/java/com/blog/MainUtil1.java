package com.blog;



import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainUtil1 {
    public static  void main(String[] args) {
        //List<Integer> numbers= Arrays.asList(100,500,5,4000,5000,10000,2000);//finding maximum number
      // Integer maxVal= numbers.stream().max(Integer::compareTo).get();//:: it is method reference to call in Integer class present  in methods
       //Integer minVal= numbers.stream().min(Integer::compareTo).get();
       // System.out.println(maxVal);//here we not used list becuase we found maximum number only one value /single so
       // System.out.println(minVal);
        //List<Employee> names=Arrays.asList(new Employee(2,"xyz"),new Employee(1,"abc") );
       // Employee employee=names.stream().max(Employee::compareTo).get();
        //Employee employee=names.stream().min(Employee::compareTo).get();
       // System.out.println(employee.getId());
        //System.out.println((employee.getName()));
       List<Employee1> data=Arrays.asList(
               new Employee1("mike",5000 ),
               new Employee1("srav",4000),
               new Employee1("prem",8000)
       );
        Map<Double,List<Employee1>>  groups=data.stream().collect(Collectors.groupingBy(Employee1::getSalary));//if we want to getName instead of getSalary
        System.out.println(groups);
       for(Map.Entry<Double,List<Employee1>> entry:groups.entrySet()){//set stores key,value pair
           double salary=entry.getKey();
           List<Employee1> employeeList=entry.getValue();
    System.out.println("Employees with salary"+salary+":");
    for(Employee1 employee1:employeeList){
        System.out.println("\t"+employee1.getName());
    }


       }



    }
}
