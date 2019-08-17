package com.exam.wy.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.wy.bean.Questions;
import com.exam.wy.mapper.QuestionsMapper;
import com.exam.wy.service.QuestionsService;
@Service
public class QuestionsServiceImpl implements QuestionsService{
	@Autowired
	private QuestionsMapper questionsMapper;
	@Override
	public List<Questions> selectQuestionsByQid() {
		// TODO Auto-generated method stub
		return questionsMapper.selectQuestionsByQid();
	}

}
