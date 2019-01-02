package com.client.dao;

import com.client.entity.FeedbackComment;
import com.client.entity.FeedbackCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedbackCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    long countByExample(FeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    int deleteByExample(FeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    int deleteByPrimaryKey(Integer fcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    int insert(FeedbackComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    int insertSelective(FeedbackComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    List<FeedbackComment> selectByExample(FeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    FeedbackComment selectByPrimaryKey(Integer fcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    int updateByExampleSelective(@Param("record") FeedbackComment record, @Param("example") FeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    int updateByExample(@Param("record") FeedbackComment record, @Param("example") FeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    int updateByPrimaryKeySelective(FeedbackComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback_comment
     *
     * @mbg.generated Fri Aug 10 16:10:27 CST 2018
     */
    int updateByPrimaryKey(FeedbackComment record);
}