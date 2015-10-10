<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@page import="java.util.*,entity.*" %>
<html>
	<head>
		<link href="css/main/style.css"
			type="text/css" rel="stylesheet" />
	</head>
	<body topMargin="10">
		<div id="append_parent"></div>
		<table cellSpacing=6 cellPadding=2 width="100%" border="0">
			<tbody>
				<tr>
					<td>
						<table class="guide" cellSpacing="0" cellPadding="0" width="100%"
							border="0">
							<tbody>
								<tr>
									<td>
										<a href='#'>主页</a>&nbsp;/&nbsp;
										<a href='#'>笔记本订购(WEB007)</a>&nbsp;/&nbsp;商品列表
									</td>
								</tr>
							</tbody>
						</table>
						<br />

						<table class="tableborder" cellSpacing="0" cellPadding="0"
							width="100%" border="0">
							<tbody>
								<tr class="header">
									<td class="altbg1" width="15%">
										<b>型号</B>
									</td>
									<td class="altbg1" width="20%">
										<b>产品图片</b>
									</td>
									<td class="altbg1" width="30%">
										<b>产品说明</b>
									</td>

									<td class="altbg1" width="10%">
										<b>产品报价</b>
									</td>
									<td class="altbg1">&quot;
									</td>
								</tr>
							</tbody>
							<tbody>
							<%
								List<Computer> computers = 
									(List<Computer>)request.getAttribute("computers");
								for(int i=0;i<computers.size();i++){
									Computer c = computers.get(i);
								%>
								<tr>
									<td class="altbg2">
										<%=c.getName()%>
									</td>
									<td class="altbg2">
										<img src="img/d007/<%=c.getPic()%>" width="150"
											height="90" />
									</td>
									<td class="altbg2">
										<%=c.getDesc()%>
									</td>
								<td class="altbg2">
										￥<%=c.getPrice()%>
									</td>
									<td class="altbg2">
											<a href="buy.do?id=<%=c.getId()%>">购买</a>
											<%
												String info = (String)request.getAttribute("buyMsg_" + c.getId());
											 %>
											<span style="color:red;">
												<%=(info ==null ? "" : info) %>
											</span>
										</td>
								</tr>
								<%	
								}
							 %>
							</tbody>
						</table>
						<br />
						<center>
							<input class="button" type="button" value="查看购物车"
								name="settingsubmit" onclick="location = 'cart.jsp';">
						</center>
					</td>
				</tr>
			</tbody>
		</table>

	</body>
</html>