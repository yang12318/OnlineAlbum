//－－－－－－－－－－－－－－－－－－控制ajax的javascript－－－－－－－－－－－－－




//－－－－－－－－－－－－－－－创建XMLHttpRequest对象－－－－－－－－－－－－－－－－－
var xmlHttp;
	try{
		xmlHttp=new XMLHttpRequest();
	}catch(e){
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
//-------------------------创建显示返回内容的Div－－－－－－－－－－－－－－－－
function createDiv(content){
	//创建显示返回内容的Div
	var respDiv=document.createElement("div");
	respDiv.className="respDiv";
	//document.body.appendChild(respDiv);
	var main=document.getElementById("mainAdmin");
	main.appendChild(respDiv);
	respDiv.innerHTML=content;
}
///--------------------向服务器发送数据，并接受返回数据库，路径为url，参数为args----------------------
function sendMessageToServer(url,args){
	//alert(url);
	//获取XMLHttpRequest对象
	xmlHttp.onreadystatechange=function(){
		if(xmlHttp.readyState==4){
			//数据已经接收成功
			if(xmlHttp.status==200){
				//请求成功
				var respText=xmlHttp.responseText;
				alert(respText);
			}else{
				alert(respText);
			}
		}
	};
	xmlHttp.open("post",url,args);
	xmlHttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xmlHttp.send(args);
}
////////-------------------------发送表单数据库到servlet-------------------------------------
//////---------------------------先进行表单验证，验证成功后按需要发送----------------------------------------------
function sendFormToServer(formName,servletName){
	if(checkFormByName(formName)){
		sendMessageToServer(servletName,changeFormToQueryString(formName));
	}
}
////----------------------------------将表单数据构造成为一个查询字符串----------------------
function changeFormToQueryString(formName){
	var form=document.forms[formName];
	var args="";
	for(var i=0;i<form.elements.length;i++){
		//将表单中的数据构造成为查询字符串
		if(form.elements[i].type!="button"){
			if(i==0){
				args=form.elements[i].name+"="+form.elements[i].value;
			}else{
				args=args+"&&"+form.elements[i].name+"="+form.elements[i].value;
			}
		}
	}
	return args;
}
//--------------------------获取表单的字段名的值，构造成查询字符串-----------------------
function changeFieldToQueryString(formName,fieldName){
	var args="";
	var form=document.forms[formName];
	args=form.elements[fieldName].name+"="+form.elements[fieldName].value;
	return args;
}
//-------------------------将表单上某个字段的值发送到服务器端，并返回结果------------------
function sendFieldToServer(formName,fieldName,servletName){
	sendMessageToServer(servletName,changeFieldToQueryString(formName,fieldName));
}