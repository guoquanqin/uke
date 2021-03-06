package com.client.entity;

import java.util.ArrayList;
import java.util.List;

public class GoodsExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public GoodsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Integer value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Integer value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Integer value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Integer value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Integer> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Integer> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeIsNull() {
            addCriterion("goods_sales_volume is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeIsNotNull() {
            addCriterion("goods_sales_volume is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeEqualTo(Integer value) {
            addCriterion("goods_sales_volume =", value, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeNotEqualTo(Integer value) {
            addCriterion("goods_sales_volume <>", value, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeGreaterThan(Integer value) {
            addCriterion("goods_sales_volume >", value, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_sales_volume >=", value, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeLessThan(Integer value) {
            addCriterion("goods_sales_volume <", value, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("goods_sales_volume <=", value, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeIn(List<Integer> values) {
            addCriterion("goods_sales_volume in", values, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeNotIn(List<Integer> values) {
            addCriterion("goods_sales_volume not in", values, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeBetween(Integer value1, Integer value2) {
            addCriterion("goods_sales_volume between", value1, value2, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsSalesVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_sales_volume not between", value1, value2, "goodsSalesVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryIsNull() {
            addCriterion("goods_inventory is null");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryIsNotNull() {
            addCriterion("goods_inventory is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryEqualTo(Integer value) {
            addCriterion("goods_inventory =", value, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryNotEqualTo(Integer value) {
            addCriterion("goods_inventory <>", value, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryGreaterThan(Integer value) {
            addCriterion("goods_inventory >", value, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_inventory >=", value, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryLessThan(Integer value) {
            addCriterion("goods_inventory <", value, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryLessThanOrEqualTo(Integer value) {
            addCriterion("goods_inventory <=", value, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryIn(List<Integer> values) {
            addCriterion("goods_inventory in", values, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryNotIn(List<Integer> values) {
            addCriterion("goods_inventory not in", values, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryBetween(Integer value1, Integer value2) {
            addCriterion("goods_inventory between", value1, value2, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsInventoryNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_inventory not between", value1, value2, "goodsInventory");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNull() {
            addCriterion("goods_price is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNotNull() {
            addCriterion("goods_price is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceEqualTo(Float value) {
            addCriterion("goods_price =", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotEqualTo(Float value) {
            addCriterion("goods_price <>", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThan(Float value) {
            addCriterion("goods_price >", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("goods_price >=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThan(Float value) {
            addCriterion("goods_price <", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThanOrEqualTo(Float value) {
            addCriterion("goods_price <=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIn(List<Float> values) {
            addCriterion("goods_price in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotIn(List<Float> values) {
            addCriterion("goods_price not in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceBetween(Float value1, Float value2) {
            addCriterion("goods_price between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotBetween(Float value1, Float value2) {
            addCriterion("goods_price not between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceIsNull() {
            addCriterion("goods_middle_price is null");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceIsNotNull() {
            addCriterion("goods_middle_price is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceEqualTo(Float value) {
            addCriterion("goods_middle_price =", value, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceNotEqualTo(Float value) {
            addCriterion("goods_middle_price <>", value, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceGreaterThan(Float value) {
            addCriterion("goods_middle_price >", value, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceGreaterThanOrEqualTo(Float value) {
            addCriterion("goods_middle_price >=", value, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceLessThan(Float value) {
            addCriterion("goods_middle_price <", value, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceLessThanOrEqualTo(Float value) {
            addCriterion("goods_middle_price <=", value, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceIn(List<Float> values) {
            addCriterion("goods_middle_price in", values, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceNotIn(List<Float> values) {
            addCriterion("goods_middle_price not in", values, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceBetween(Float value1, Float value2) {
            addCriterion("goods_middle_price between", value1, value2, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsMiddlePriceNotBetween(Float value1, Float value2) {
            addCriterion("goods_middle_price not between", value1, value2, "goodsMiddlePrice");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeIsNull() {
            addCriterion("goods_describe is null");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeIsNotNull() {
            addCriterion("goods_describe is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeEqualTo(String value) {
            addCriterion("goods_describe =", value, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeNotEqualTo(String value) {
            addCriterion("goods_describe <>", value, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeGreaterThan(String value) {
            addCriterion("goods_describe >", value, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("goods_describe >=", value, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeLessThan(String value) {
            addCriterion("goods_describe <", value, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeLessThanOrEqualTo(String value) {
            addCriterion("goods_describe <=", value, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeLike(String value) {
            addCriterion("goods_describe like", value, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeNotLike(String value) {
            addCriterion("goods_describe not like", value, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeIn(List<String> values) {
            addCriterion("goods_describe in", values, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeNotIn(List<String> values) {
            addCriterion("goods_describe not in", values, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeBetween(String value1, String value2) {
            addCriterion("goods_describe between", value1, value2, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsDescribeNotBetween(String value1, String value2) {
            addCriterion("goods_describe not between", value1, value2, "goodsDescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureIsNull() {
            addCriterion("goods_picture is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureIsNotNull() {
            addCriterion("goods_picture is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureEqualTo(String value) {
            addCriterion("goods_picture =", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureNotEqualTo(String value) {
            addCriterion("goods_picture <>", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureGreaterThan(String value) {
            addCriterion("goods_picture >", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureGreaterThanOrEqualTo(String value) {
            addCriterion("goods_picture >=", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureLessThan(String value) {
            addCriterion("goods_picture <", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureLessThanOrEqualTo(String value) {
            addCriterion("goods_picture <=", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureLike(String value) {
            addCriterion("goods_picture like", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureNotLike(String value) {
            addCriterion("goods_picture not like", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureIn(List<String> values) {
            addCriterion("goods_picture in", values, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureNotIn(List<String> values) {
            addCriterion("goods_picture not in", values, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureBetween(String value1, String value2) {
            addCriterion("goods_picture between", value1, value2, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureNotBetween(String value1, String value2) {
            addCriterion("goods_picture not between", value1, value2, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleIsNull() {
            addCriterion("goods_is_sale is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleIsNotNull() {
            addCriterion("goods_is_sale is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleEqualTo(String value) {
            addCriterion("goods_is_sale =", value, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleNotEqualTo(String value) {
            addCriterion("goods_is_sale <>", value, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleGreaterThan(String value) {
            addCriterion("goods_is_sale >", value, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleGreaterThanOrEqualTo(String value) {
            addCriterion("goods_is_sale >=", value, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleLessThan(String value) {
            addCriterion("goods_is_sale <", value, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleLessThanOrEqualTo(String value) {
            addCriterion("goods_is_sale <=", value, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleLike(String value) {
            addCriterion("goods_is_sale like", value, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleNotLike(String value) {
            addCriterion("goods_is_sale not like", value, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleIn(List<String> values) {
            addCriterion("goods_is_sale in", values, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleNotIn(List<String> values) {
            addCriterion("goods_is_sale not in", values, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleBetween(String value1, String value2) {
            addCriterion("goods_is_sale between", value1, value2, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsIsSaleNotBetween(String value1, String value2) {
            addCriterion("goods_is_sale not between", value1, value2, "goodsIsSale");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNull() {
            addCriterion("goods_type is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNotNull() {
            addCriterion("goods_type is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeEqualTo(Integer value) {
            addCriterion("goods_type =", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotEqualTo(Integer value) {
            addCriterion("goods_type <>", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThan(Integer value) {
            addCriterion("goods_type >", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_type >=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThan(Integer value) {
            addCriterion("goods_type <", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("goods_type <=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIn(List<Integer> values) {
            addCriterion("goods_type in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotIn(List<Integer> values) {
            addCriterion("goods_type not in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeBetween(Integer value1, Integer value2) {
            addCriterion("goods_type between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_type not between", value1, value2, "goodsType");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table goods
     *
     * @mbg.generated do_not_delete_during_merge Sat Jul 21 11:48:28 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table goods
     *
     * @mbg.generated Sat Jul 21 11:48:28 CST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}