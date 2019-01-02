function date(){
	var date=new Date();
	var month=date.getMonth();
	var year=date.getYear();
}
var calUtil = {
  //当前日历显示的年份
  showYear:date.year,
  //当前日历显示的月份
  showMonth:date.month,
  //当前日历显示的天数
  showDays:1,
  eventName:"load",
  //初始化日历
  init:function(signList,s=''){

    if (typeof(s) == 'undefined'){
    }else{
      signList.splice('','',s);
    }
    calUtil.draw(signList);
  },
  draw:function(signList){
	  var date=new Date();
	  var year=date.getFullYear(); 
	  var month=date.getMonth()+1;
    //绑定日历
    if(signList.length > 20){
      $("#sign_note").empty();
      $("#sign_note").html('<button class="sign_contener" type="button" >' +
          '<i disabled="disabled" class="fa fa-calendar-check-o" aria-hidden="true">' +
          '</i>&nbsp;已达标，获得积分50</button>');
    }
    var str = calUtil.drawCal(year,month,signList);
    $("#calendar").html(str);
    //绑定日历表头
    var calendarName=year+"年"+month+"月";
    $(".calendar_month_span").html(calendarName);  
  },

  getDaysInmonth : function(iMonth, iYear){
   var dPrevDate = new Date(iYear, iMonth, 0);
   return dPrevDate.getDate();
  },
  bulidCal : function(iYear, iMonth) {
   var aMonth = new Array();
   aMonth[0] = new Array(7);
   aMonth[1] = new Array(7);
   aMonth[2] = new Array(7);
   aMonth[3] = new Array(7);
   aMonth[4] = new Array(7);
   aMonth[5] = new Array(7);
   aMonth[6] = new Array(7);
   var dCalDate = new Date(iYear, iMonth - 1, 1);
   var iDayOfFirst = dCalDate.getDay();
   var iDaysInMonth = calUtil.getDaysInmonth(iMonth, iYear);
   var iVarDate = 1;
   var d, w;
   aMonth[0][0] = "日";
   aMonth[0][1] = "一";
   aMonth[0][2] = "二";
   aMonth[0][3] = "三";
   aMonth[0][4] = "四";
   aMonth[0][5] = "五";
   aMonth[0][6] = "六";
   for (d = iDayOfFirst; d < 7; d++) {
    aMonth[1][d] = iVarDate;
    iVarDate++;
   }
   for (w = 2; w < 7; w++) {
    for (d = 0; d < 7; d++) {
     if (iVarDate <= iDaysInMonth) {
      aMonth[w][d] = iVarDate;
      iVarDate++;
     }
    }
   }
   return aMonth;
  },
  ifHasSigned : function(signList,day){
   var signed = false;
   $.each(signList,function(index,item){
    if(item.signDay == day) {
     signed = true;
     return false;
    }
   });
   return signed ;
  },
  drawCal : function(iYear, iMonth ,signList) {
   var myMonth = calUtil.bulidCal(iYear, iMonth);
   var htmls = new Array();
   htmls.push("<div class='sign_main' id='sign_layer'>");
   htmls.push("<div class='sign_succ_calendar_title'>");
   htmls.push("<div class='calendar_month_span'></div>");
   htmls.push("</div>");
   htmls.push("<div class='sign_equal' id='sign_cal'>");
   htmls.push("<div class='sign_row'>");
   htmls.push("<div class='th_1 bold'>" + myMonth[0][0] + "</div>");
   htmls.push("<div class='th_2 bold'>" + myMonth[0][1] + "</div>");
   htmls.push("<div class='th_3 bold'>" + myMonth[0][2] + "</div>");
   htmls.push("<div class='th_4 bold'>" + myMonth[0][3] + "</div>");
   htmls.push("<div class='th_5 bold'>" + myMonth[0][4] + "</div>");
   htmls.push("<div class='th_6 bold'>" + myMonth[0][5] + "</div>");
   htmls.push("<div class='th_7 bold'>" + myMonth[0][6] + "</div>");
   htmls.push("</div>");
   var d, w;
   for (w = 1; w < 6; w++) {
    htmls.push("<div class='sign_row'>");
    for (d = 0; d < 7; d++) {

     var ifHasSigned = calUtil.ifHasSigned(signList,myMonth[w][d]);

     if(ifHasSigned && typeof(myMonth[w][d]) != 'undefined'){
      htmls.push("<div class='td_"+d+" on'>" + (!isNaN(myMonth[w][d]) ? myMonth[w][d] : " ") + "</div>");
     } else {
      htmls.push("<div class='td_"+d+" calendar_record'>" + (!isNaN(myMonth[w][d]) ? myMonth[w][d] : " ") + "</div>");

     }
    }
    htmls.push("</div>");
   }
   htmls.push("</div>");
   htmls.push("</div>");
   htmls.push("</div>");
   return htmls.join('');
  }
};