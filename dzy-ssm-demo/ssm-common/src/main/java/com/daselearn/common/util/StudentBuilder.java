package com.daselearn.common.util;

/**
 * Description:
 *
 * @author daniel
 * @date 2019/7/25
 */
public class StudentBuilder {
    String name = null ;
    int number = -1 ;
    String sex = null ;
    int age = -1 ;
    String school = null ;
    public StudentBuilder setName(String name) {
        this.name = name;
        return  this ;
    }

    public StudentBuilder setNumber(int number) {
        this.number = number;
        return  this ;
    }

    public StudentBuilder setSex(String sex) {
        this.sex = sex;
        return  this ;
    }

    public StudentBuilder setAge(int age) {
        this.age = age;
        return  this ;
    }

    public StudentBuilder setSchool(String school) {
        this.school = school;
        return  this ;
    }
    public Student build() {
        return new Student(this);//调用student构造函数
    }

}
