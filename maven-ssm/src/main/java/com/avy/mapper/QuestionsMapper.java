package com.avy.mapper;

import java.util.List;

import com.avy.bean.Questions;
import com.exam.wy.bean.Questions;

public interface QuestionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Questions record);

    int insertSelective(Questions record);

    Questions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Questions record);

    int updateByPrimaryKey(Questions record);
    /**
     * 查询问题,查询选项,查询正确选项通过问题id
     */
    List<Questions> selectQuestionsByQid();
}