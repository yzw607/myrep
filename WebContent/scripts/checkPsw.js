var flag = false;

function checkOldPsw()
{
  var obj = document.getElementById("oldPsw");
  if(obj.value == "")
  {
    document.getElementById("oldPswInfo").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 请输入旧密码";
    flag = false;
    return;
  }
  
  document.getElementById("oldPswInfo").innerHTML = "<img src='images/ok1.png' style='vertical-align:middle;'/>";
  flag = true;
}

function checkNewPsw()
{
  var obj = document.getElementById("newPsw");
  
  if(isNumber(obj.value))
  {
    document.getElementById("newPswInfo").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 不能全为数字";
    flag = false;
    return;
  }
  
  if(isLetter(obj.value))
  {
    document.getElementById("newPswInfo").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 不能全为字母";
    flag = false;
    return;  
  }
  
  var len = obj.value.length;
  if(len < 6 || len > 16)
  {
    document.getElementById("newPswInfo").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 长度应为6-16个字符";
    flag = false;
    return;  
  }
  
  flag = true;
  document.getElementById("newPswInfo").innerHTML = "<img src='images/ok1.png' style='vertical-align:middle;'/>";
}

function checkNewPsw2()
{
  var obj = document.getElementById("newPsw2");
  if(obj.value == "")
  {
    document.getElementById("newPsw2Info").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 再输一次密码";
    flag = false;
    return;
  }
  
  if(obj.value != document.getElementById("newPsw").value)
  {
    document.getElementById("newPsw2Info").innerHTML = "<img src='images/cha1.png' style='vertical-align:middle;'/> 两次密码输入不一致";
    flag = false;
    return;  
  }
  
  document.getElementById("newPsw2Info").innerHTML = "<img src='images/ok1.png' style='vertical-align:middle;'/>";
  flag = true;
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
  checkOldPsw();
  if(!flag)
  {
    return;
  }
  
  checkNewPsw();
  if(!flag)
  {
    return;
  }
  
  checkNewPsw2();
  if(!flag)
  {
    return;
  }
  
  document.forms[0].submit();
}