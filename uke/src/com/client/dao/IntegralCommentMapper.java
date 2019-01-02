package com.client.dao;

import com.client.entity.IntegralComment;
import com.client.entity.IntegralCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    long countByExample(IntegralCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    int deleteByExample(IntegralCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    int deleteByPrimaryKey(Integer integralCommentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    int insert(IntegralComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    int insertSelective(IntegralComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    List<IntegralComment> selectByExample(IntegralCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    IntegralComment selectByPrimaryKey(Integer integralCommentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    int updateByExampleSelective(@Param("record") IntegralComment record, @Param("example") IntegralCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    int updateByExample(@Param("record") IntegralComment record, @Param("example") IntegralCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    int updateByPrimaryKeySelective(IntegralComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_comment
     *
     * @mbg.generated Sun Sep 09 20:30:26 CST 2018
     */
    int updateByPrimaryKey(IntegralComment record);
}