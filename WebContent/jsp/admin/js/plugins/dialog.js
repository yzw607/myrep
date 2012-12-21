function jqueryAlert(message){  
    if (jQuery("#dialogalert").length == 0) {  
        jQuery("body").append('<div id="dialogalert"></div>');  
        jQuery("#dialogalert").dialog({  
            autoOpen: false,  
            modal: true,
            position: "center",
            resizable:false,  
            overlay: {  
                opacity: 0.5,  
                background: "black"  
            },  
            buttons: {     
            	"确定": function(){  
                    jQuery(this).dialog("close");  
                }  
            }  
        });  
    }  
    jQuery("#dialogalert" ).dialog({ title: "提示信息"});
    jQuery("#dialogalert").html(message);  
    jQuery("#dialogalert").dialog("open");  
}  

function jqueryConfirm(title,message, callback1,callback2){  
    if (jQuery("#dialogconfirm").length == 0) {  
        jQuery("body").append('<div id="dialogconfirm"></div>');  
        jQuery("#dialogconfirm").dialog({  
            autoOpen: false,  
            modal: true,  
            resizable:false,  
            position: "center",
            overlay: {  
                opacity: 0.5,  
                background: "black"  
            },  
            buttons: {  
            	"确定": function(){
            		jQuery(this).dialog("close"); 
            		if(callback1){
            			callback1();  
            		}
                },  
                "取消": function(){  
                	if(callback2){
            			callback2();  
            		}
                    jQuery(this).dialog("close");  
                }  
            }  
        });  
    }  
    jQuery("#dialogconfirm" ).dialog({ title: "提示信息"});
    jQuery("#dialogconfirm").html(message);  
    jQuery("#dialogconfirm").dialog("open");      
}  


     