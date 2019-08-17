package com.exam.wy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.wy.bean.Answers;
import com.exam.wy.bean.Questions;
import com.exam.wy.service.QuestionsService;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;
@Log4j
@Controller
@RequestMapping("questions")
public class QuestionsController {
	@Autowired
	private QuestionsService questionsService;
	
	@ResponseBody
	@RequestMapping("list")  //questions/list
	public JSONObject selectQuestionsByQid(){
		Map<String,Object> map= new HashMap<>();
		List<Questions> list=questionsService.selectQuestionsByQid();
		map.put("questions", list);
		//将map集合转json
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
}
