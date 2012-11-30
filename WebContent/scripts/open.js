var smsArr = new Array();

function processRequest()
{
  if (http_request.readyState == 4)
  {
    if (http_request.status == 200)
    {
      var returnStr = http_request.responseText;

      if(returnStr != "")
      {
        var json = eval("(" + returnStr + ")");
	    var tmp = json.msgTitle.substring(3, json.msgTitle.length);
	    var tmpLen = tmp.length;

        if(tmpLen > 55)
        {
          var len = parseInt(tmpLen / 55);
          if(tmpLen % 55 > 0)
          {
            len++; 
          }
          var start = 0;
          var end = 0;
          var str = "";
          for(var i = 0; i < len; i++)
          {
            start = i * 55;
            end = (i + 1) * 55;
            str = "[" + (i + 1) + "/" + len + "]"; 
          
            smsArr.push(new Array(json.id + "_" + i, json.sender, tmp.substring(start, end) + str));
          }
        }
        else
        {
          smsArr.push(new Array(json.id, json.sender, tmp));
        }
      }
    }
    else
    {
      //alert(http_request.status);
      return false;
    }
  }
}

function AttachEvent(target, eventName, handler, argsObject)
{
    var eventHandler = handler;
    if(argsObject)
    {
        eventHander = function(e)
        {
            handler.call(argsObject, e);
        }
    }
    if(window.attachEvent)//IE
        target.attachEvent("on" + eventName, eventHander );
    else//FF
        target.addEventListener(eventName, eventHander, false);
}

function hover2(e)
{
  var oDiv = document.getElementById("aa" + this.liid); 
  if (this.cType == 'show') { oDiv.style.display = 'block';} 
  if (this.cType == 'hide') { oDiv.style.display = 'none';} 
}

function delLi(id)
{
  var uu = document.getElementById("smsLi");
  for(var i = 0; i < uu.childNodes.length; i++)
  {
    if(uu.childNodes[i].id == id)
    {
      uu.removeChild(uu.childNodes[i]);
    }
  }
}

function bigMsg(id)
{
  document.getElementById("shBig").innerHTML = document.getElementById("h" + id).innerHTML;
  
  var mmsg = document.getElementById("p" + id).innerHTML;
  document.getElementById("phBig").innerHTML = mmsg;
  var mainDList = document.getElementById("mainDList");
  var len = strLen(mmsg);
  
  if(len <= 18)
  {
    mainDList.className = "mainlist threeline";
  }
  else if(len <= 50)
  {
    mainDList.className = "mainlist threeline";
  }
  else
  {
    mainDList.className = "mainlist threeline";
  }

  $("#TB_overlayBG").css({
    display:"block",height:$(document).height()
  });

  $(".box").css({
    left:($("body").width()-$(".box").width())/2,
    top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
    display:"block"
  });
  clearInterval(timer);
}

function closeBig()
{
  document.getElementById("shBig").innerHTML = "";
  document.getElementById("phBig").innerHTML = "";
  
  $("#TB_overlayBG").css("display","none");
  $(".box ").css("display","none");
  showSMS();
  timer = setInterval(showSMS, 6000);
}

function showSMS()
{
  var id = "";
  var sender = "";
  var sms = "";
  if(smsArr.length > 0)
  {
    var tmpArr = smsArr.shift(); 
    id = tmpArr[0];
    sender = tmpArr[1];
    sms = tmpArr[2];
  }
  else
  {
    return;
  }

  var uu = document.getElementById("smsLi");
  var t = uu.childNodes.length;
  if(t >= 6)
  {
    uu.removeChild(uu.childNodes[5]);
  }
  
  var li= document.createElement("li");
  li.id = id;
  
  var obj1 = new Object();
  obj1.liid = id;
  obj1.cType = 'show';
  
  var obj2 = new Object();
  obj2.liid = id;
  obj2.cType = 'hide';
  
  AttachEvent(li, "mouseover",  hover2, obj1);
  AttachEvent(li, "mouseout",  hover2, obj2);
  
  li.style.display = "none";

  var len = strLen(sms);
  if(len <= 36)
  {
    li.className = "threeline nopic";
  }
  else if(len <= 100)
  {
    li.className = "threeline nopic";
  }
  else
  {
    li.className = "threeline nopic";
  }
  
  var bb = "<h3 id='h" + id +"'>" + sender + "：</h3><p id='p" + id + "'>" + sms;
  bb = bb + "</p><div class='ycc' id='aa";
  bb = bb + id + "'> <span><a href='javascript:bigMsg(\"" + id + "\");' class='showbox'>放大</a> &nbsp;&nbsp; <a href='javascript:delLi(\"" + id;
  bb = bb + "\")'>删除</a></span></div>";
  li.innerHTML = bb; 
  uu.appendChild(li);
        
  var $ul = $("#con ul");
  var liHeight = $ul.find("li:last").height();

  $ul.animate({marginTop : liHeight+8 +"px"},1000,function(){
    $ul.find("li:last").prependTo($ul)
    $ul.find("li:first").hide();
    $ul.css({marginTop:0});
    $ul.find("li:first").fadeIn(1000);
  });
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
