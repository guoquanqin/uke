package com.client.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderGoodsExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    public OrderGoodsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
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
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
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

        public Criteria andOrderGoodsIdIsNull() {
            addCriterion("order_goods_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdIsNotNull() {
            addCriterion("order_goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdEqualTo(Integer value) {
            addCriterion("order_goods_id =", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdNotEqualTo(Integer value) {
            addCriterion("order_goods_id <>", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdGreaterThan(Integer value) {
            addCriterion("order_goods_id >", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_goods_id >=", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdLessThan(Integer value) {
            addCriterion("order_goods_id <", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_goods_id <=", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdIn(List<Integer> values) {
            addCriterion("order_goods_id in", values, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdNotIn(List<Integer> values) {
            addCriterion("order_goods_id not in", values, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("order_goods_id between", value1, value2, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_goods_id not between", value1, value2, "orderGoodsId");
            return (Criteria) this;
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

        public Criteria andGoodsCountIsNull() {
            addCriterion("goods_count is null");
            return (Criteria) this;
        }

        public Criteria andGoodsCountIsNotNull() {
            addCriterion("goods_count is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsCountEqualTo(Short value) {
            addCriterion("goods_count =", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountNotEqualTo(Short value) {
            addCriterion("goods_count <>", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountGreaterThan(Short value) {
            addCriterion("goods_count >", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountGreaterThanOrEqualTo(Short value) {
            addCriterion("goods_count >=", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountLessThan(Short value) {
            addCriterion("goods_count <", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountLessThanOrEqualTo(Short value) {
            addCriterion("goods_count <=", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountIn(List<Short> values) {
            addCriterion("goods_count in", values, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountNotIn(List<Short> values) {
            addCriterion("goods_count not in", values, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountBetween(Short value1, Short value2) {
            addCriterion("goods_count between", value1, value2, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountNotBetween(Short value1, Short value2) {
            addCriterion("goods_count not between", value1, value2, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceIsNull() {
            addCriterion("goods_total_price is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceIsNotNull() {
            addCriterion("goods_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceEqualTo(Float value) {
            addCriterion("goods_total_price =", value, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceNotEqualTo(Float value) {
            addCriterion("goods_total_price <>", value, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceGreaterThan(Float value) {
            addCriterion("goods_total_price >", value, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("goods_total_price >=", value, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceLessThan(Float value) {
            addCriterion("goods_total_price <", value, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceLessThanOrEqualTo(Float value) {
            addCriterion("goods_total_price <=", value, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceIn(List<Float> values) {
            addCriterion("goods_total_price in", values, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceNotIn(List<Float> values) {
            addCriterion("goods_total_price not in", values, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceBetween(Float value1, Float value2) {
            addCriterion("goods_total_price between", value1, value2, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalPriceNotBetween(Float value1, Float value2) {
            addCriterion("goods_total_price not between", value1, value2, "goodsTotalPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeIsNull() {
            addCriterion("goods_size is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeIsNotNull() {
            addCriterion("goods_size is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeEqualTo(String value) {
            addCriterion("goods_size =", value, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeNotEqualTo(String value) {
            addCriterion("goods_size <>", value, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeGreaterThan(String value) {
            addCriterion("goods_size >", value, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeGreaterThanOrEqualTo(String value) {
            addCriterion("goods_size >=", value, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeLessThan(String value) {
            addCriterion("goods_size <", value, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeLessThanOrEqualTo(String value) {
            addCriterion("goods_size <=", value, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeLike(String value) {
            addCriterion("goods_size like", value, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeNotLike(String value) {
            addCriterion("goods_size not like", value, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeIn(List<String> values) {
            addCriterion("goods_size in", values, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeNotIn(List<String> values) {
            addCriterion("goods_size not in", values, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeBetween(String value1, String value2) {
            addCriterion("goods_size between", value1, value2, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsSizeNotBetween(String value1, String value2) {
            addCriterion("goods_size not between", value1, value2, "goodsSize");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksIsNull() {
            addCriterion("goods_remarks is null");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksIsNotNull() {
            addCriterion("goods_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksEqualTo(String value) {
            addCriterion("goods_remarks =", value, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksNotEqualTo(String value) {
            addCriterion("goods_remarks <>", value, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksGreaterThan(String value) {
            addCriterion("goods_remarks >", value, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("goods_remarks >=", value, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksLessThan(String value) {
            addCriterion("goods_remarks <", value, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksLessThanOrEqualTo(String value) {
            addCriterion("goods_remarks <=", value, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksLike(String value) {
            addCriterion("goods_remarks like", value, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksNotLike(String value) {
            addCriterion("goods_remarks not like", value, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksIn(List<String> values) {
            addCriterion("goods_remarks in", values, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksNotIn(List<String> values) {
            addCriterion("goods_remarks not in", values, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksBetween(String value1, String value2) {
            addCriterion("goods_remarks between", value1, value2, "goodsRemarks");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarksNotBetween(String value1, String value2) {
            addCriterion("goods_remarks not between", value1, value2, "goodsRemarks");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table order_goods
     *
     * @mbg.generated do_not_delete_during_merge Thu Aug 16 12:19:20 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table order_goods
     *
     * @mbg.generated Thu Aug 16 12:19:20 CST 2018
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