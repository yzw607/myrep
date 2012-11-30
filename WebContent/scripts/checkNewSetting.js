var userNameStatus = false;
var telStatus = false;
var emailStatus = false;
var companyNameStatus = false;
var flag = false;

function focusUserName(obj)
{
  if(obj.value == "")
  {
    document.getElementById("userNameInfo").innerHTML = "<img src='" + path + "/images/deng1.png' style='vertical-align:middle;'/> 请使用真实的姓名或企业名称，方便我们更好的为您服务。";
  }
}

function pageInit()
{
  document.getElementById("isAgree").checked = false;
}



function checkUserName(obj)
{
  if(obj.value == "")
  {
    document.getElementById("userNameInfo").innerHTML = "<img src='" + path + "/images/cha1.png' style='vertical-align:middle;'/> 请使用真实的姓名或企业名称，方便我们更好的为您服务。";
    userNameStatus = false;
    return;
  }
  
  document.getElementById("userNameInfo").innerHTML = "<img src='" + path + "/images/ok1.png' style='vertical-align:middle;'/>";
  userNameStatus = true;
}

function focusTel(obj)
{
  if(obj.value == "")
  {
    document.getElementById("telInfo").innerHTML = "<img src='" + path + "/images/deng1.png' style='vertical-align:middle;'/> 您在此处填写的联系方式，将会显示在婚礼现场大屏幕上，为贵公司做宣传，请慎重填写。";
  }
}

function checkTel(obj)
{
  if(obj.value == "")
  {
    document.getElementById("telInfo").innerHTML = "<img src='" + path + "/images/cha1.png' style='vertical-align:middle;'/> 您在此处填写的联系方式，将会显示在婚礼现场大屏幕上，为贵公司做宣传，请慎重填写。";
    telStatus = false;
    return;
  }
  
  if(!isNumber(obj.value))
  {
    document.getElementById("telInfo").innerHTML = "<img src='" + path + "/images/cha1.png' style='vertical-align:middle;'/> 请您输入正确的联系方式";
    telStatus = false;
    return;
  }
  
  document.getElementById("telInfo").innerHTML = "<img src='" + path + "/images/ok1.png' style='vertical-align:middle;'/>";
  telStatus = true;
}

function focusEmail(obj)
{
  if(obj.value == "")
  {
    document.getElementById("emailInfo").innerHTML = "<img src='" + path + "/images/deng1.png' style='vertical-align:middle;'/> 这是您找回密码的唯一方式。";
  }
}

function checkEmail(obj)
{
  if(obj.value == "")
  {
    document.getElementById("emailInfo").innerHTML = "<img src='" + path + "/images/cha1.png' style='vertical-align:middle;'/> 这是您找回密码的唯一方式。";
    emailStatus = false;
    return;
  }
  
  if(!isEmail(obj.value))
  {
    document.getElementById("emailInfo").innerHTML = "<img src='" + path + "/images/cha1.png' style='vertical-align:middle;'/> 请您输入正确的电子邮箱地址";
    emailStatus = false;
    return;
  }
  
  document.getElementById("emailInfo").innerHTML = "<img src='" + path + "/images/ok1.png' style='vertical-align:middle;'/> 找回密码的唯一方式，一旦注册成功将不能修改";
  emailStatus = true;
}

function focusCompanyName(obj)
{
  if(obj.value == "")
  {
    document.getElementById("companyNameInfo").innerHTML = "<img src='" + path + "/images/deng1.png' style='vertical-align:middle;'/> 许愿墙上会显示您公司的名称哦，请输入真实的公司名称，40个汉字以内。";
  }
}

function checkCompanyName(obj)
{
  if(obj.value == "")
  {
    document.getElementById("companyNameInfo").innerHTML = "<img src='" + path + "/images/cha1.png' style='vertical-align:middle;'/> 许愿墙上会显示您公司的名称哦，请输入真实的公司名称，40个汉字以内。";
    companyNameStatus = false;
    return;
  }
  
  document.getElementById("companyNameInfo").innerHTML = "<img src='" + path + "/images/ok1.png' style='vertical-align:middle;'/> ";
  companyNameStatus = true;
}

function focusCompanyAddress(obj)
{
  if(obj.value == "")
  {
    document.getElementById("companyAddressInfo").innerHTML = "<img src='" + path + "/images/deng1.png' style='vertical-align:middle;'/> 40个汉字以内。";
  }
}

function checkCompanyAddress(obj)
{
  if(obj.value != "")
  {
    document.getElementById("companyAddressInfo").innerHTML = "<img src='" + path + "/images/ok1.png' style='vertical-align:middle;'/> ";
  }
  else
  {
    document.getElementById("companyAddressInfo").innerHTML = ""; 
  }
}

function isNumber(str)
{ 
  if(""==str)
  { 
    return false; 
  } 
  var reg = /\D/; 
  return str.match(reg)==null; 
}

function isLetter(str)
{ 
  if(""==str)
  { 
    return false; 
  }
  
  for(var i=0; i < str.length; i++) 
  {
    var c = str.charAt(i);
    if((c<"a"||c>"z")&&(c<"A"||c>"Z"))
    { 
      return false; 
    } 
  } 
  return true; 
}

function isEmail(str)
{
  var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
  if(reg.test(str))
  {
     return true;
  }
  else
  {
    return false;
  }
}

function doRegister()
{
  if(flag)
  {
    document.forms[0].submit();
  }
}

function formCheck()
{
  checkUserName(document.getElementById("userName"));
  checkTel(document.getElementById("tel"));
  checkEmail(document.getElementById("email"));
  checkCompanyName(document.getElementById("companyName"));
  


  if(userNameStatus && telStatus && emailStatus && companyNameStatus)
  {
    document.forms[0].submit();
  }
}

function strLen(s) 
{
 var l = 0;
 var a = s.split("");
 for (var i=0;i<a.length;i++) {
  if (a[i].charCodeAt(0)<299) {
   l++;
  } else {
   l+=2;
  }
 }
 return l;
}