package com.management.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OtherProjectExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    public OtherProjectExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
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
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
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

        public Criteria andOpIdIsNull() {
            addCriterion("op_id is null");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNotNull() {
            addCriterion("op_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpIdEqualTo(Integer value) {
            addCriterion("op_id =", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotEqualTo(Integer value) {
            addCriterion("op_id <>", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThan(Integer value) {
            addCriterion("op_id >", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("op_id >=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThan(Integer value) {
            addCriterion("op_id <", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThanOrEqualTo(Integer value) {
            addCriterion("op_id <=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdIn(List<Integer> values) {
            addCriterion("op_id in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotIn(List<Integer> values) {
            addCriterion("op_id not in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdBetween(Integer value1, Integer value2) {
            addCriterion("op_id between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("op_id not between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectDateIsNull() {
            addCriterion("project_date is null");
            return (Criteria) this;
        }

        public Criteria andProjectDateIsNotNull() {
            addCriterion("project_date is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDateEqualTo(Date value) {
            addCriterion("project_date =", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateNotEqualTo(Date value) {
            addCriterion("project_date <>", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateGreaterThan(Date value) {
            addCriterion("project_date >", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateGreaterThanOrEqualTo(Date value) {
            addCriterion("project_date >=", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateLessThan(Date value) {
            addCriterion("project_date <", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateLessThanOrEqualTo(Date value) {
            addCriterion("project_date <=", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateIn(List<Date> values) {
            addCriterion("project_date in", values, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateNotIn(List<Date> values) {
            addCriterion("project_date not in", values, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateBetween(Date value1, Date value2) {
            addCriterion("project_date between", value1, value2, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateNotBetween(Date value1, Date value2) {
            addCriterion("project_date not between", value1, value2, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectAmountIsNull() {
            addCriterion("project_amount is null");
            return (Criteria) this;
        }

        public Criteria andProjectAmountIsNotNull() {
            addCriterion("project_amount is not null");
            return (Criteria) this;
        }

        public Criteria andProjectAmountEqualTo(Float value) {
            addCriterion("project_amount =", value, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andProjectAmountNotEqualTo(Float value) {
            addCriterion("project_amount <>", value, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andProjectAmountGreaterThan(Float value) {
            addCriterion("project_amount >", value, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andProjectAmountGreaterThanOrEqualTo(Float value) {
            addCriterion("project_amount >=", value, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andProjectAmountLessThan(Float value) {
            addCriterion("project_amount <", value, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andProjectAmountLessThanOrEqualTo(Float value) {
            addCriterion("project_amount <=", value, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andProjectAmountIn(List<Float> values) {
            addCriterion("project_amount in", values, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andProjectAmountNotIn(List<Float> values) {
            addCriterion("project_amount not in", values, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andProjectAmountBetween(Float value1, Float value2) {
            addCriterion("project_amount between", value1, value2, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andProjectAmountNotBetween(Float value1, Float value2) {
            addCriterion("project_amount not between", value1, value2, "projectAmount");
            return (Criteria) this;
        }

        public Criteria andIsExistIsNull() {
            addCriterion("is_exist is null");
            return (Criteria) this;
        }

        public Criteria andIsExistIsNotNull() {
            addCriterion("is_exist is not null");
            return (Criteria) this;
        }

        public Criteria andIsExistEqualTo(String value) {
            addCriterion("is_exist =", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistNotEqualTo(String value) {
            addCriterion("is_exist <>", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistGreaterThan(String value) {
            addCriterion("is_exist >", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistGreaterThanOrEqualTo(String value) {
            addCriterion("is_exist >=", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistLessThan(String value) {
            addCriterion("is_exist <", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistLessThanOrEqualTo(String value) {
            addCriterion("is_exist <=", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistLike(String value) {
            addCriterion("is_exist like", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistNotLike(String value) {
            addCriterion("is_exist not like", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistIn(List<String> values) {
            addCriterion("is_exist in", values, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistNotIn(List<String> values) {
            addCriterion("is_exist not in", values, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistBetween(String value1, String value2) {
            addCriterion("is_exist between", value1, value2, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistNotBetween(String value1, String value2) {
            addCriterion("is_exist not between", value1, value2, "isExist");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table other_project
     *
     * @mbg.generated do_not_delete_during_merge Fri Sep 07 11:06:15 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table other_project
     *
     * @mbg.generated Fri Sep 07 11:06:15 CST 2018
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