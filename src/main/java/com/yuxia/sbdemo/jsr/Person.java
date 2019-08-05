package com.yuxia.sbdemo.jsr;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class Person {
    @NotBlank(message = "姓名不能为空")
    private String name;
    @Max(message = "年龄最大150",value = 150)
    private Integer age;
    @IsEmail()
    private String email;

    private List<@NotBlank(message = "书名不能为空") String> bookList;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getBookList() {
        return bookList;
    }

    public void setBookList(List<String> bookList) {
        this.bookList = bookList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
