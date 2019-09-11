package com.daselearn.common.util;

/**
 * Description:
 *
 * @author daniel
 * @date 2019/7/25
 */
public class Student {
    String name = null ;
    int number = -1 ;
    String sex = null ;
    int age = -1 ;
    String school = null ;

    public Student(){

    }

    public Student(StudentBuilder builder) {
        this.age = builder.age;
        this.name = builder.name;
        this.number = builder.number;
        this.school = builder.school ;
        this.sex = builder.sex ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (number != student.number) return false;
        if (age != student.age) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (sex != null ? !sex.equals(student.sex) : student.sex != null) return false;
        return school != null ? school.equals(student.school) : student.school == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + number;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (school != null ? school.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                '}';
    }
}
