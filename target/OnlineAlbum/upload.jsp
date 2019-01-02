<%@ page import="java.util.List" %>
<%@ page import="model.Album" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- <script type="text/javascript" src="js/david.js"></script> -->
	<link rel="stylesheet" type="text/css" href="CSS/STYLE.CSS">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>上传照片</title>
</head>
<body>

	<div id="main" class="main">
		<div class="mainAdmin">
			<form onsubmit="return checkFormByName('uploadForm');" name="uploadForm" action="../upload" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>
							上传照片：
						</td>
						<td align="left">
							<input type="file" name="photoUrl" title="上传照片" size="40"><span style="color: red; ">图片不大于2MB</span>
						</td>
					</tr>
					<tr>
						<td>
							照片名字：
						</td>
						<td align="left">
							<input type="text" name="photoTitle" value="未命名" title="照片名字"
								size="40">
						</td>
					</tr>
					<tr>
						<td>
							所属相册：
						</td>
						<td align="left">
							<select name="albumId" title="所属相册">
								<option value="">
									--所属相册--
								</option>
								<%
									try {
										List<Album> list = (List<Album>) request.getAttribute("list");
										for(Album album : list) {
											out.println("<option value=\"" + album.getId() + "\">");
											out.println(album.getName());
											out.println("</option>");
										}
									} catch (ClassCastException e) {
										e.printStackTrace();
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							照片描述：
						</td>
						<td align="left">
							<textarea cols="48" rows="5" name="photoDescription" value="" title="照片描述"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input id="submit" type="submit" value="上传" />
							&nbsp;&nbsp;
							<input id="reset" type="reset" value="全部清空" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>