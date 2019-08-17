package com.avy.bean;

import lombok.Data;

@Data
public class Questions {
    private Integer id;

    private String questions;

    private Integer correctAnswer;

    private Integer status;

    private Integer sortnum;

    private String prepare;
    
    //一对多关联(需要传的Json串里,答案是以数组的形式)
    private String[] answers;

   
}