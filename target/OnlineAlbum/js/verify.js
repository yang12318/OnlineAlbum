//验证姓名
function verifyName() {
	var name = document.getElementById("Name").value;//获得名字
	var domName = document.getElementById("name");//获取父节点
	var domFlag = document.getElementById("tipsName");
    //如果之前有提示框出现
    console.log(name);
	if(domFlag){
		domName.removeChild(document.getElementById("tipsName"));
	}
	//用户名为空的情况
	if(name == ""){
		//添加提示节点
		var tipsNull = document.createElement("span");
		tipsNull.setAttribute("id", "tipsName");
		var tipscontent = document.createTextNode("用户名不能为空");
		tipsNull.appendChild(tipscontent);
		tipsNull.style.color = "red";//修改颜色
		domName.appendChild(tipsNull);
		return false;
	}
	else{
		return true;
    }
}

function verify() {
	var flagName = verifyName();
    var flagPassword = verifyPassword();
	//正确通过
	if(flagName && flagPassword){
		alert('成功');
	}
	else {
		return false;
	}
}

//验证密码为空
function verifyPassword() {
	var name = document.getElementById("Password").value;//获得名字
	var domName = document.getElementById("password");//获取父节点
	var domFlag = document.getElementById("tipsPassword");
    //如果之前有提示框出现
    console.log(name);
	if(domFlag){
		domName.removeChild(document.getElementById("tipsPassword"));
	}
	//密码为空的情况
	if(name == ""){
		//添加提示节点
		var tipsNull = document.createElement("span");
		tipsNull.setAttribute("id", "tipsPassword");
		var tipscontent = document.createTextNode("密码不能为空");
		tipsNull.appendChild(tipscontent);
		tipsNull.style.color = "red";//修改颜色
		domName.appendChild(tipsNull);
		return false;
	}
	else{
		return true;
    }
}