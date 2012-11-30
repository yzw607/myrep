package com.wmp.selfService.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.Question;
import com.wmp.selfService.service.IQuestionService;
import com.wmp.sms.bean.MsgInBox;
import com.wmp.sms.bean.NamePoint;
import com.wmp.userManage.bean.User;

public class QuestionServiceImpl extends HibernateDaoSupport implements IQuestionService
{
    private final static String FIND_NAME = "FROM NamePoint o WHERE o.telNumber = ?"; 
    
    @SuppressWarnings("unchecked")
    public ActivityInfo getActivity(User user, String activityId) throws Exception
    {
        String hql = "FROM ActivityInfo o WHERE o.id = ? AND o.userCode = ?";
        List list = this.getHibernateTemplate().find(hql,
                new Object[] { new Integer(activityId), user.getUserCode() });
        if(null != list && list.size() > 0)
        {
            return (ActivityInfo)list.get(0);
        }
        else
        {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Question> getQuestions(User user, String activityId) throws Exception
    {
        String hql = "FROM Question o WHERE o.userCode = ? AND o.activityId = ?";
        List list = this.getHibernateTemplate().find(hql,
                new Object[] { user.getUserCode(), new Integer(activityId) });

        return list;
    }
    
    public void addQuestion(User user, Question question, String activityId) throws Exception
    {
        question.setUserCode(user.getUserCode());
        question.setActivityId(Integer.parseInt(activityId));
        
        this.getHibernateTemplate().save(question);
    }
    
    @SuppressWarnings("unchecked")
    public void delQuestion(User user, Question question) throws Exception
    {
        String hql = "FROM Question o WHERE o.userCode = ? and o.questionId = ?";
        List list = this.getHibernateTemplate().find(hql,
                new Object[] { user.getUserCode(), question.getQuestionId() });
        
        if(null != list && list.size() > 0)
        {
            Question q = (Question)list.get(0);
            this.getHibernateTemplate().delete(q);
        }
    }
    
    @SuppressWarnings("unchecked")
    public Question getQuestion(String questionId, String activityId,
            String userCode, String number) throws Exception
    {
        HibernateTemplate ht = this.getHibernateTemplate();
        if(null != questionId && !"".equals(questionId.trim()))
        {
            Question q = (Question)ht.get(Question.class, new Integer(questionId));
            
            if(!userCode.equals(q.getUserCode()))
            {
                return null;
            }
            
            q.setStatus(1);
            ht.update(q);
        }
        
        String sql = "update MsgInBox o set o.status = 1 where o.msgTitle like '" + number + "抢答%'";
        ht.getSessionFactory().getCurrentSession().createQuery(sql).executeUpdate();
        
        List<Question> list = null;
        StringBuffer sb = new StringBuffer();
        sb.append("FROM Question o WHERE o.userCode = '").append(userCode).append("' AND o.activityId = ");
        sb.append(activityId).append(" AND o.status = 0 ORDER BY o.questionId");
        
        final String hql = sb.toString();
        final int firstResult = 0;
        final int maxResults = 1;
        list = ht.executeFind(new HibernateCallback()
        {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                Query query = session.createQuery(hql);
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                List list = query.list();
                return list;
            }
        });
        
        if(null != list && list.size() > 0)
        {
            return list.get(0);
        }
        else
        {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public MsgInBox toGetTheLatestAnswer(String number) throws Exception
    {
        HibernateTemplate ht = this.getHibernateTemplate();
        MsgInBox box = null;
        NamePoint name = null;
        String msg = null;

        StringBuffer hql = new StringBuffer(256);
        hql.append("FROM MsgInBox o WHERE o.msgArrivedTime > curdate() AND (o.msgTitle LIKE '");
        hql.append(number).append("抢答%' OR o.msgTitle LIKE '").append(number);
        hql.append(" 抢答%' OR o.msgTitle LIKE '000%') AND o.msgType = 0 AND o.status IS NULL ORDER BY o.id");

        List<MsgInBox> list = ht.find(hql.toString());
        List<NamePoint> nList = null;
        String sender = null;
        String newName = null;
        
        if (null != list)
        {
            int size = list.size();
            for (int i = 0; i < size; i++)
            {
                box = list.get(i);

                msg = box.getMsgTitle();
                sender = box.getSender().trim();
                
                if (msg.startsWith("000"))
                {
                    box.setStatus("2");
                    ht.update(box);
                    
                    if(msg.length() > 18)
                    {
                        msg = msg.substring(0, 18);
                    }
                    newName = msg.substring(3, msg.length());
                    newName = newName.replaceAll("\\?", "？");
                    newName = newName.replaceAll("'", "‘");
                    newName = newName.replaceAll("%", "％");
                    
                    nList = ht.find(FIND_NAME, sender);
                    if(null != nList && nList.size() > 0)
                    {
                        name = nList.get(0);
                        name.setName(newName);
                        
                        ht.update(name);
                    }
                    else
                    {
                        name = new NamePoint();
                        name.setTelNumber(sender);
                        name.setName(msg.substring(3, msg.length()));
                        ht.save(name);
                    }
                    
                    box = null;

                    continue;
                }
                else
                {
                    box.setStatus("1");
                    this.getHibernateTemplate().update(box);

                    nList = ht.find(FIND_NAME, sender);
                    if(null != nList && nList.size() > 0)
                    {
                        name = nList.get(0);
                        MsgInBox newBox = new MsgInBox();
                        newBox.setSender(name.getName());
                        newBox.setMsgTitle(box.getMsgTitle());
                        newBox.setMsgType(1);
                        newBox.setMMSURL(box.getMMSURL());

                        return newBox;
                    }

                    break;
                }
            }
        }

        return box;
    }
}
