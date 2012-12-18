<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>

<script type="text/javascript">
var flag = false;
function userAgent()
{
  var ua = navigator.userAgent;
  ua = ua.toLowerCase();
  var match = /(webkit)[ \/]([\w.]+)/.exec(ua) || 
  /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua) || 
  /(msie) ([\w.]+)/.exec(ua) || 
  !/compatible/.test(ua) && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) || [];   

  //如果需要获取浏览器版本号：match[2]   
  switch(match[1])
  {
    case "msie":      //ie   
    {
      if (parseInt(match[2]) === 6)    //ie6
      {
        //alert("ie6");   
        flag = false;
      }
      else if (parseInt(match[2]) === 7)    //ie7   
      {
        //alert("ie7"); 
        flag = false;
      }
      else if (parseInt(match[2]) === 8)    //ie8 
      {
        //alert("ie8"); 
        flag = true;
      }
      else if (parseInt(match[2]) === 9)    //ie8 
      {
        //alert("ie8"); 
        flag = true;
      }
      else if (parseInt(match[2]) === 10)    //ie8 
      {
        //alert("ie8"); 
        flag = true;
      }  
      break;
    } 
    case "webkit":     //safari or chrome   
    {
      //alert("safari or chrome");   
      flag = true;
      break;   
    }
    case "opera":      //opera   
    {
      //alert("opera");   
      flag = true;
      break;   
    }
    case "mozilla":    //Firefox   
    {
      //alert("Firefox");  
      flag = true; 
      break;   
    }
    default:       
      break;   
    }   
  }   

  userAgent();   
  //if(!flag)
   if(0 > 1)
  {
    window.location.href="<%=path%>/prompt.jsp";
  }

</script>
