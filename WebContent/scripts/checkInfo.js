var flag = false;

function checkUserName()
{
  var obj = document.getElementById("userName");
  if(obj.value == "")
  {
    document.getElementById("userNameInfo").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 为了您的账号安全，建议您填写真实姓名。";
    flag = false;
    return;
  }
  
  flag = true;
  document.getElementById("userNameInfo").innerHTML = "<img src='images/ok1.png' style='vertical-align:middle;'/>";
}

function checkTel()
{
  var obj = document.getElementById("tel");
  if(obj.value == "")
  {
    document.getElementById("telInfo").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 便于我们直接与您或您的公司进行联系，也有助于贵公司的业务推广。";
    flag = false;
    return;
  }
  
  if(!isNumber(obj.value))
  {
    document.getElementById("telInfo").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 请您输入正确的联系方式";
    flag = false;
    return;
  }
  
  flag = true;
  document.getElementById("telInfo").innerHTML = "<img src='images/ok1.png' style='vertical-align:middle;'/>";
}

function checkCompanyName()
{
  var obj = document.getElementById("companyName");
  if(obj.value == "")
  {
    document.getElementById("companyNameInfo").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 许愿墙上会显示您公司的名称哦，请输入真实的公司名称。";
    flag = false;
    return;
  }
  
  flag = true;
  document.getElementById("companyNameInfo").innerHTML = "<img src='images/ok1.png' style='vertical-align:middle;'/> ";
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

function goUpdate()
{
  checkUserName();
  if(!flag)
  {
    return;
  }
  
  checkTel();
  if(!flag)
  {
    return;
  }
  
  checkCompanyName();
  if(!flag)
  {
    return;
  }
  
  document.forms[0].submit();
}