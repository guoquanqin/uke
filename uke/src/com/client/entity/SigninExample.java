package com.client.entity;

import java.util.ArrayList;
import java.util.List;

public class SigninExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    public SigninExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
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
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
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

        public Criteria andSigninIdIsNull() {
            addCriterion("signin_id is null");
            return (Criteria) this;
        }

        public Criteria andSigninIdIsNotNull() {
            addCriterion("signin_id is not null");
            return (Criteria) this;
        }

        public Criteria andSigninIdEqualTo(Integer value) {
            addCriterion("signin_id =", value, "signinId");
            return (Criteria) this;
        }

        public Criteria andSigninIdNotEqualTo(Integer value) {
            addCriterion("signin_id <>", value, "signinId");
            return (Criteria) this;
        }

        public Criteria andSigninIdGreaterThan(Integer value) {
            addCriterion("signin_id >", value, "signinId");
            return (Criteria) this;
        }

        public Criteria andSigninIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("signin_id >=", value, "signinId");
            return (Criteria) this;
        }

        public Criteria andSigninIdLessThan(Integer value) {
            addCriterion("signin_id <", value, "signinId");
            return (Criteria) this;
        }

        public Criteria andSigninIdLessThanOrEqualTo(Integer value) {
            addCriterion("signin_id <=", value, "signinId");
            return (Criteria) this;
        }

        public Criteria andSigninIdIn(List<Integer> values) {
            addCriterion("signin_id in", values, "signinId");
            return (Criteria) this;
        }

        public Criteria andSigninIdNotIn(List<Integer> values) {
            addCriterion("signin_id not in", values, "signinId");
            return (Criteria) this;
        }

        public Criteria andSigninIdBetween(Integer value1, Integer value2) {
            addCriterion("signin_id between", value1, value2, "signinId");
            return (Criteria) this;
        }

        public Criteria andSigninIdNotBetween(Integer value1, Integer value2) {
            addCriterion("signin_id not between", value1, value2, "signinId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsIsNull() {
            addCriterion("sign_day_nums is null");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsIsNotNull() {
            addCriterion("sign_day_nums is not null");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsEqualTo(Integer value) {
            addCriterion("sign_day_nums =", value, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsNotEqualTo(Integer value) {
            addCriterion("sign_day_nums <>", value, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsGreaterThan(Integer value) {
            addCriterion("sign_day_nums >", value, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_day_nums >=", value, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsLessThan(Integer value) {
            addCriterion("sign_day_nums <", value, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsLessThanOrEqualTo(Integer value) {
            addCriterion("sign_day_nums <=", value, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsIn(List<Integer> values) {
            addCriterion("sign_day_nums in", values, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsNotIn(List<Integer> values) {
            addCriterion("sign_day_nums not in", values, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsBetween(Integer value1, Integer value2) {
            addCriterion("sign_day_nums between", value1, value2, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDayNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_day_nums not between", value1, value2, "signDayNums");
            return (Criteria) this;
        }

        public Criteria andSignDateIsNull() {
            addCriterion("sign_date is null");
            return (Criteria) this;
        }

        public Criteria andSignDateIsNotNull() {
            addCriterion("sign_date is not null");
            return (Criteria) this;
        }

        public Criteria andSignDateEqualTo(String value) {
            addCriterion("sign_date =", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotEqualTo(String value) {
            addCriterion("sign_date <>", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateGreaterThan(String value) {
            addCriterion("sign_date >", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateGreaterThanOrEqualTo(String value) {
            addCriterion("sign_date >=", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLessThan(String value) {
            addCriterion("sign_date <", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLessThanOrEqualTo(String value) {
            addCriterion("sign_date <=", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLike(String value) {
            addCriterion("sign_date like", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotLike(String value) {
            addCriterion("sign_date not like", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateIn(List<String> values) {
            addCriterion("sign_date in", values, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotIn(List<String> values) {
            addCriterion("sign_date not in", values, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateBetween(String value1, String value2) {
            addCriterion("sign_date between", value1, value2, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotBetween(String value1, String value2) {
            addCriterion("sign_date not between", value1, value2, "signDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table signin
     *
     * @mbg.generated do_not_delete_during_merge Wed Aug 01 22:10:16 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table signin
     *
     * @mbg.generated Wed Aug 01 22:10:16 CST 2018
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