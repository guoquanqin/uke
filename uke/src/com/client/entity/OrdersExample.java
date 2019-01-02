package com.client.entity;

import java.util.ArrayList;
import java.util.List;

public class OrdersExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    public OrdersExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
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
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
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

        public Criteria andOrderUserIdIsNull() {
            addCriterion("order_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdIsNotNull() {
            addCriterion("order_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdEqualTo(Integer value) {
            addCriterion("order_user_id =", value, "orderUserId");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdNotEqualTo(Integer value) {
            addCriterion("order_user_id <>", value, "orderUserId");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdGreaterThan(Integer value) {
            addCriterion("order_user_id >", value, "orderUserId");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_user_id >=", value, "orderUserId");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdLessThan(Integer value) {
            addCriterion("order_user_id <", value, "orderUserId");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_user_id <=", value, "orderUserId");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdIn(List<Integer> values) {
            addCriterion("order_user_id in", values, "orderUserId");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdNotIn(List<Integer> values) {
            addCriterion("order_user_id not in", values, "orderUserId");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdBetween(Integer value1, Integer value2) {
            addCriterion("order_user_id between", value1, value2, "orderUserId");
            return (Criteria) this;
        }

        public Criteria andOrderUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_user_id not between", value1, value2, "orderUserId");
            return (Criteria) this;
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table orders
     *
     * @mbg.generated do_not_delete_during_merge Sat Aug 04 07:40:16 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table orders
     *
     * @mbg.generated Sat Aug 04 07:40:16 CST 2018
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