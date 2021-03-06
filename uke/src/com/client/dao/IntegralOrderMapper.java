package com.client.dao;

import com.client.entity.IntegralOrder;
import com.client.entity.IntegralOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    long countByExample(IntegralOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    int deleteByExample(IntegralOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    int deleteByPrimaryKey(Integer integralOrderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    int insert(IntegralOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    int insertSelective(IntegralOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    List<IntegralOrder> selectByExample(IntegralOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    IntegralOrder selectByPrimaryKey(Integer integralOrderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    int updateByExampleSelective(@Param("record") IntegralOrder record, @Param("example") IntegralOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    int updateByExample(@Param("record") IntegralOrder record, @Param("example") IntegralOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    int updateByPrimaryKeySelective(IntegralOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_order
     *
     * @mbg.generated Wed Aug 29 16:38:53 CST 2018
     */
    int updateByPrimaryKey(IntegralOrder record);
}