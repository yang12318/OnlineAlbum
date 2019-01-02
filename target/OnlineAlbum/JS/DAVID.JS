function openLink(link){
	window.open(link,"","width=500,height=300,toolbar=0,menubar=0,scrollbars=0");
}
function jumping(pageForm){
	pageForm.submit();
	return;
}
function gotoPage(pageNum,pageForm){
	document.forms(pageForm).jumpPage.value=pageNum;
	document.forms(pageForm).submit();
	return;
}
function setButtonDisable(){
	var submit=document.getElementById("submit");
	var reset=document.getElementById("reset");
	submit.disable=true;
	reset.disable=true;
	//button.disable=true;
}


function isDelete(){
	return confirm("确定要删除吗？");
}

function   lTrim(instr){  
	 var str=instr+"";  
	 if (str.length==0){  
	 	 return str;  
	 }  
	 var i=0;  
	 while((i<str.length)&&(str.substring(i,i+1)==" ")&&(i<2000)){
	  	i++; 
	 }

	 return str.substring(i,str.length);  
}  

function rTrim(str){  
	  var instr=str+"";  
	  var last_space;  
	  var ret;  
	  last_space=instr.length;  
	  var loop=0;  
	  while((instr.charAt(last_space-1)==" ")&&(last_space>0)&&(loop<2000)){
	      	loop++;  
	     	last_space--;  
	  }  
	  if(last_space==0){  
	  		return ""  
	  }else{  
	  		return instr.substring(0,last_space);  
	  }  
}  
//--------------------去掉左右空格函数----------------------------  
function allTrim(str){  
  	return rTrim(lTrim(str));  
}
////////////////////////////////////////////////
//////通用表单验证，输入域是否为空，接收表单名字，输入控件的title属性为错误提示信息标志
////////////////////////////////////////////////
function checkFormByName(formName){
	var form = document.forms[formName];
	for(var i=0;i<form.elements.length;i++){
		var input=form.elements[i];		
		if(allTrim(input.value).length==0){
			alert("dsfsdf"+input.title+"sdfsdfsd");
			input.focus();
			return false;
		}
		if(input.type=="password"){
			//如果是密码框
			if(allTrim(input.value).length<6){
				alert("fdgdfgdfg");
				input.focus();
				return false;
			}
		}
		if(input.name=="rePassword"){
			//如果有确认密码
			if(allTrim(form.elements["password"].value)!=allTrim(form.elements["rePassword"].value)){
				alert("djhk");
				input.focus();
				return false;
			}
		}
	}	
	return true;
}

function disabledForm(formName){
	var form=document.forms[formName];
	for(var i=0;i<form.elements.length;i++){
		form.elements[i].disabled=true;
	}
}

//控制图片大小css的函数
	function changeBig(v){
		//var d=document.getElementById("imgDiv");
		//d.className="divBig";
		var p=document.getElementById(v.id);
		p.className="imgBig";
	}
	function changeSmall(v){
		var p=document.getElementById(v.id);
		p.className="imgSmall";
	}