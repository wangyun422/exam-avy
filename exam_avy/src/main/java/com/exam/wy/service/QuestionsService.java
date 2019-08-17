package com.exam.wy.service;

import java.util.List;

import com.exam.wy.bean.Questions;

public interface QuestionsService {
	
	 /**
     * 查询问题,查询选项,查询正确选项通过问题id
     */
	List<Questions> selectQuestionsByQid();
}
