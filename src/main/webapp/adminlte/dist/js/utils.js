/*
 * Javascript 工具函数
 * 
 * @Author  linweixuan@gmail.com
 * @version 2.3.3
 * @license MIT <http://opensource.org/licenses/MIT>
 */
 
/**
 * 根据传入id获取js对象
 * @param id 入参id
 * @returns
 */
function obj$(id){
    return document.getElementById(id);
};
/**
 * 根据传入id获取js对象的值
 * @param id 入参id
 * @returns
 */
function val$(id){
    var obj= document.getElementById(id);
    if(obj!==null){
        return obj.value();
    }else{
        return null;
    }
};
/**
 * 去掉字符串前后的空格
 * @param str 入参:要去掉空格的字符串
 * @returns
 */
function trimAll(str){
     return str.replace(/(^\s*)|(\s*$)/g, ''); 
 
};
/**
 * 去掉字符串前的空格
 * @param str 入参:要去掉空格的字符串
 * @returns
 */
function trimLeft(str){
     return str.replace(/^\s*/g,'');
     
};
/**
 * 去掉字符串后的空格
 * @param str 入参:要去掉空格的字符串
 * @returns
 */
function trimRight(str){
    return str.replace(/\s*$/,'');
}
/**
 * 判断字符串是否为空
 * @param str 传入的字符串
 * @returns
 */
function isEmpty(str){
    if(str != null && str.length > 0)
    {
        return true;
    }
    return false;
}
/**
 * 判断两个字符串子否相同
 * @param str1
 * @param str2
 * @returns {Boolean}
 */
function isEquals(str1,str2){
    if(str1==str2){
        return true;
    }
    return false;
}
/**
 * 忽略大小写判断字符串是否相同
 * @param str1
 * @param str2
 * @returns {Boolean}
 */
function isEqualsIgnorecase(str1,str2){
    if(str1.toUpperCase() == str2.toUpperCase())
    {
        return true;
    }
    return false;
}
/**
 * 判断js对象的长度
 * @param obj
 * @param min
 * @param max
 * @returns {Boolean}
 */
function checkLength(obj,min,max){
    if(obj.length < min || obj.length > max) {
        return false;
    } else {
        return true;
    }
}
/**
 * 判断是否是数字
 * @param value
 * @returns {Boolean}
 */
function isNum(value){
    if( value != null && value.length>0 && isNaN(value) == false){
        return true;
    }
    else{
        return false;
    }
 
}
/**
 * 判断是否是中文
 * @param str
 * @returns {Boolean}
 */
function isChine(str){
    var reg = /^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/;
    if(reg.test(str)){
        return false;
    }
    return true;
}
/**
 * 获取年
 * @returns
 */
function getYear(){
    var year = null;
    var dateTime = this.getDateTime();                      
    if(dateTime != null){
        year = dateTime.getFullYear();
    }else{
        year = this.curDateTime.getFullYear();
    }       
    return year;
}
/**
 * 获取月
 * @returns
 */
function getMonth(){
    var month = null;
    var dateTime = this.getDateTime();
    if(dateTime != null){
        month = dateTime.getMonth() + 1;        
    }else{
        month = this.curDateTime.getMonth() + 1;    
    }
    return month;
}
/**
 * 获取天
 * @returns
 */
function getDay(){
    var day = null;
    var dateTime = this.getDateTime();
    if(dateTime != null){
        day = dateTime.getDate();
    }else{
        day = this.curDateTime.getDate();   
    }
    return day;
 
}
/**
 * 获取小时
 * @returns
 */
function getHour(){
    var hour = null;
    var dateTime = this.getDateTime();
    if(dateTime != null){
        hour = dateTime.getHours();
    }else{
        hour = this.curDateTime.getHours(); 
    }
    return hour;
}
/**
 * 获取分钟
 * @returns
 */
function getMinute(){
    var minute = null;
    var dateTime = this.getDateTime();
    if(dateTime != null){
        minute = dateTime.getMinutes();
    }else{
        minute = this.curDateTime.getMinutes();
    }
    return minute;  
}
/**
 * 获取秒
 * @returns
 */
function getSecond(){
    var second = null;
    var dateTime = this.getDateTime();
    if(dateTime != null){
        second = dateTime.getSeconds();
    }else{
        second = this.curDateTime.getSeconds();
    }
    return second;
}
/**
 * 是否是闰年
 * @returns {Boolean}
 */
function isLeapYear(){
    var flag = false;
    if((this.getYear() % 4 == 0 && this.getYear() % 100 !=0)
            || (this.getYear() % 400 == 0)){
        flag = true;
    }       
    return flag;
}
