var userCodeStatus = false;
var pswStatus = false;
var pswStatus2 = false;
var userNameStatus = false;
var telStatus = false;
var emailStatus = false;
var companyNameStatus = false;
var fileStatus = false;
var flag = false;

function bbb(obj)
{
  formCheck();
}

function ccc()
{
  checkUserCode(document.getElementById("userCode"));
  checkPsw(document.getElementById("passWord"));
  checkPsw2(document.getElementById("passWord2"));
  checkUserName(document.getElementById("userName"));
  checkTel(document.getElementById("tel"));
  checkEmail(document.getElementById("email"));
  checkCompanyName(document.getElementById("companyName"));
  checkFile(document.getElementById("file"));
}

function pageInit()
{
  document.getElementById("isAgree").checked = false;
  document.getElementById("userType").value = "enterprise";
}

function focusUserCode(obj)
{
  if(obj.value == "")
  {
    document.getElementById("userCodeInfo").innerHTML = "<img src='../../images/deng1.png' style='vertical-align:bottom;'/> 4-20个字符，欢迎您使用中文名字注册。";
  }
}

function checkUserCode(obj)
{
  var len = strLen(obj.value);
  if(len < 4 || len > 20)
  {
    document.getElementById("userCodeInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 4-20个字符";
    formCheck();
    return;
  }

  document.getElementById("userCodeInfo").innerHTML = "<img src='../../images/load.gif' style='vertical-align:bottom;'/>";
  checkNameIsOk(obj.value);
}

function focusPsw(obj)
{
  if(obj.value == "")
  {
    document.getElementById("pswInfo").innerHTML = "<img src='../../images/deng1.png' style='vertical-align:bottom;'/> 6-16个字符，请使用字母、数字组合密码，不能单独使用字母或数字。";
  }
}

function checkPsw(obj)
{
  if(isNumber(obj.value))
  {
    document.getElementById("pswInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 不能全为数字";
    pswStatus = false;
    formCheck();
    return;
  }
  
  if(isLetter(obj.value))
  {
    document.getElementById("pswInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 不能全为字母";
    pswStatus = false;
    formCheck();
    return;  
  }
  
  var len = obj.value.length;
  if(len < 6 || len > 16)
  {
    document.getElementById("pswInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 长度应为6-16个字符";
    pswStatus = false;
    formCheck();
    return;  
  }
  
  document.getElementById("pswInfo").innerHTML = "<img src='../../images/ok1.png' style='vertical-align:bottom;'/>";
  pswStatus = true;
  formCheck();
}

function focusPsw2(obj)
{
  if(obj.value == "")
  {
    document.getElementById("pswInfo2").innerHTML = "<img src='../../images/deng1.png' style='vertical-align:bottom;'/> 再输一次密码";
  }
}

function checkPsw2(obj)
{
  if(obj.value == "")
  {
    document.getElementById("pswInfo2").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 再输一次密码";
    pswStatus2 = false;
    formCheck();
    return;
  }
  
  if(obj.value != document.getElementById("passWord").value)
  {
    document.getElementById("pswInfo2").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 两次密码输入不一致";
    pswStatus2 = false;
    formCheck();
    return;  
  }
  
  document.getElementById("pswInfo2").innerHTML = "<img src='../../images/ok1.png' style='vertical-align:bottom;'/>";
  pswStatus2 = true;
  formCheck();
}

function focusUserName(obj)
{
  if(obj.value == "")
  {
    document.getElementById("userNameInfo").innerHTML = "<img src='../../images/deng1.png' style='vertical-align:bottom;'/> 请使用真实的姓名或企业名称，方便我们更好的为您服务。";
  }
}

function checkUserName(obj)
{
  if(obj.value == "")
  {
    document.getElementById("userNameInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 请使用真实的姓名或企业名称，方便我们更好的为您服务。";
    userNameStatus = false;
    formCheck();
    return;
  }
  
  document.getElementById("userNameInfo").innerHTML = "<img src='../../images/ok1.png' style='vertical-align:bottom;'/>";
  userNameStatus = true;
  formCheck();
}

function focusTel(obj)
{
  if(obj.value == "")
  {
    document.getElementById("telInfo").innerHTML = "<img src='../../images/deng1.png' style='vertical-align:bottom;'/> 您在此处填写的联系方式，将会显示在婚礼现场大屏幕上，为贵公司做宣传，请慎重填写。";
  }
}

function checkTel(obj)
{
  if(obj.value == "")
  {
    document.getElementById("telInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 您在此处填写的联系方式，将会显示在婚礼现场大屏幕上，为贵公司做宣传，请慎重填写。";
    telStatus = false;
    formCheck();
    return;
  }
  
  if(!isNumber(obj.value))
  {
    document.getElementById("telInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 请您输入正确的联系方式";
    telStatus = false;
    formCheck();
    return;
  }
  
  document.getElementById("telInfo").innerHTML = "<img src='../../images/ok1.png' style='vertical-align:bottom;'/>";
  telStatus = true;
  formCheck();
}

function focusEmail(obj)
{
  if(obj.value == "")
  {
    document.getElementById("emailInfo").innerHTML = "<img src='../../images/deng1.png' style='vertical-align:bottom;'/> 这是您找回密码的唯一方式。";
  }
}

function checkEmail(obj)
{
  if(obj.value == "")
  {
    document.getElementById("emailInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 这是您找回密码的唯一方式。";
    emailStatus = false;
    formCheck();
    return;
  }
  
  if(!isEmail(obj.value))
  {
    document.getElementById("emailInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 请您输入正确的电子邮箱地址";
    emailStatus = false;
    formCheck();
    return;
  }
  
  document.getElementById("emailInfo").innerHTML = "<img src='../../images/ok1.png' style='vertical-align:bottom;'/> 找回密码的唯一方式，一旦注册成功将不能修改";
  emailStatus = true;
  formCheck();
}

function focusCompanyName(obj)
{
  if(obj.value == "")
  {
    document.getElementById("companyNameInfo").innerHTML = "<img src='../../images/deng1.png' style='vertical-align:bottom;'/> 许愿墙上会显示您公司的名称哦，请输入真实的公司名称，40个汉字以内。";
  }
}

function checkCompanyName(obj)
{
  if(obj.value == "")
  {
    document.getElementById("companyNameInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 许愿墙上会显示您公司的名称哦，请输入真实的公司名称，40个汉字以内。";
    companyNameStatus = false;
    formCheck();
    return;
  }
  
  document.getElementById("companyNameInfo").innerHTML = "<img src='../../images/ok1.png' style='vertical-align:bottom;'/> ";
  companyNameStatus = true;
  formCheck();
}

function focusCompanyAddress(obj)
{
  if(obj.value == "")
  {
    document.getElementById("companyAddressInfo").innerHTML = "<img src='../../images/deng1.png' style='vertical-align:middle;'/> 40个汉字以内。";
  }
}

function checkCompanyAddress(obj)
{
  if(obj.value != "")
  {
    document.getElementById("companyAddressInfo").innerHTML = "<img src='../../images/ok1.png' style='vertical-align:middle;'/> ";
  }
  else
  {
    document.getElementById("companyAddressInfo").innerHTML = ""; 
  }
}

function checkFile(obj)
{
  var userType = document.getElementById("userType").value;
  if(userType == "personal")
  {
    fileStatus = true;
    obj.value = "";
    formCheck();
    return;
  }
  
  if(obj.value == "")
  {
    document.getElementById("fileInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 请上传您的营业执照，文件控制在2M以内。";
    fileStatus = false;
    formCheck();
    return;
  }
  
  t = /^.+(\.jpg|\.JPG)$/; 
  var fName = obj.value;
  if(!t.test(fName)) 
  {
    document.getElementById("fileInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 营业执照请上传jpg文件";
    fileStatus = false;
    formCheck();
    return;
  }
  
  document.getElementById("fileInfo").innerHTML = "<img src='../../images/ok1.png' style='vertical-align:bottom;'/> ";
  fileStatus = true;
  formCheck();
}

function processRequest()
{
  if (http_request.readyState == 4)
  {
    if (http_request.status == 200)
    {
      var returnStr = http_request.responseText;

      if(returnStr == "error")
      {
        document.getElementById("userCodeInfo").innerHTML = "<img src='../../images/cha1.png' style='vertical-align:bottom;'/> 该会员名已被使用。您可以:1、重新输入其他会员名。2、使用该会员名登录";
        userCodeStatus = false;
      }
      else
      {
        document.getElementById("userCodeInfo").innerHTML = "<img src='../../images/ok1.png' style='vertical-align:bottom;'/> 一旦注册成功不能修改";
        userCodeStatus = true;
      }
      
      formCheck();
    }
    else
    {
      //alert(http_request.status);
      return false;
    }
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
  ccc();
  if(flag)
  {
    document.forms[0].submit();
  }
}

function formCheck()
{
  if(document.getElementById("userType").value == "enterprise")
  {
    if(userCodeStatus && pswStatus && pswStatus2 && userNameStatus && telStatus && 
    emailStatus && companyNameStatus && fileStatus && document.getElementById("isAgree").checked)
    {
      document.getElementById("registerBtn").className = "stdbtn btn_yellow";
      flag = true;
    }
    else
    {
      document.getElementById("registerBtn").className = "stdbtn";
      flag = false;
    }
  }
  else
  {
    if(userCodeStatus && pswStatus && pswStatus2 && userNameStatus && telStatus && 
    emailStatus && document.getElementById("isAgree").checked)
    {
      document.getElementById("registerBtn").className = "stdbtn btn_yellow";
      flag = true;
    }
    else
    {
      document.getElementById("registerBtn").className = "stdbtn";
      flag = false;
    }
  }


}

function selUserType(obj)
{
  if(obj.value == "enterprise")
  {
    document.getElementById("cr1").style.display = "";
    document.getElementById("cr2").style.display = "";
    document.getElementById("cr3").style.display = "";
  }
  else
  {
    document.getElementById("cr1").style.display = "none";
    document.getElementById("cr2").style.display = "none";
    document.getElementById("cr3").style.display = "none";
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