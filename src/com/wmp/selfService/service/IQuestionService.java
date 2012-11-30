package com.wmp.selfService.service;

import java.util.List;

import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.Question;
import com.wmp.sms.bean.MsgInBox;
import com.wmp.userManage.bean.User;

public interface IQuestionService
{
    public ActivityInfo getActivity(User user, String activityId)
            throws Exception;

    public List<Question> getQuestions(User user, String activityId)
            throws Exception;

    public void addQuestion(User user, Question question, String activityId)
            throws Exception;

    public void delQuestion(User user, Question question) throws Exception;

    public Question getQuestion(String questionId, String activityId,
            String userCode, String number) throws Exception;

    public MsgInBox toGetTheLatestAnswer(String number) throws Exception;
}
