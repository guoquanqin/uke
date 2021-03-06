package com.client.dao;

import com.client.entity.IntegralFeedbackComment;
import com.client.entity.IntegralFeedbackCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralFeedbackCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    long countByExample(IntegralFeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    int deleteByExample(IntegralFeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    int deleteByPrimaryKey(Integer ifcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    int insert(IntegralFeedbackComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    int insertSelective(IntegralFeedbackComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    List<IntegralFeedbackComment> selectByExample(IntegralFeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    IntegralFeedbackComment selectByPrimaryKey(Integer ifcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    int updateByExampleSelective(@Param("record") IntegralFeedbackComment record, @Param("example") IntegralFeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    int updateByExample(@Param("record") IntegralFeedbackComment record, @Param("example") IntegralFeedbackCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    int updateByPrimaryKeySelective(IntegralFeedbackComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_feedback_comment
     *
     * @mbg.generated Sun Aug 26 07:59:05 CST 2018
     */
    int updateByPrimaryKey(IntegralFeedbackComment record);
}