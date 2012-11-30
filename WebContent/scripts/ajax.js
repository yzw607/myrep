var http_request = false;

function send_request(url)
{
    http_request = false;
    if(window.XMLHttpRequest)
    {
        http_request = new XMLHttpRequest();
        if (http_request.overrideMimeType)
        {
            http_request.overrideMimeType('text/xml');
        }
    }
    else if (window.ActiveXObject)
    {
        try
        {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch(e)
        {
            try
            {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch(e) {}
        }
    }

    if(!http_request)
    {
        alert("系统异常，不能创建XMLHttpRequest对象实例");
        return false;
    }

    http_request.onreadystatechange = processRequest;
    http_request.open("GET", url, true);
    http_request.send(null);
}

function send_request_post(url, property)
{
    http_request = false;
    if(window.XMLHttpRequest)
    {
        http_request = new XMLHttpRequest();
        if (http_request.overrideMimeType)
        {
            http_request.overrideMimeType('text/xml');
        }
    }
    else if (window.ActiveXObject)
    {
        try
        {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch(e)
        {
            try
            {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch(e) {}
        }
    }

    if(!http_request)
    {
        alert("系统异常，不能创建XMLHttpRequest对象实例");
        return false;
    }

    http_request.onreadystatechange = processRequest;
    http_request.open("post", url, true);
    http_request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
    http_request.send(property);
}