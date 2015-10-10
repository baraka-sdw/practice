package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DAOFactory;
import bean.Cart;
import bean.CartItem;
import dao.ComputerDAO;
import entity.Computer;

public class ShoppingServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),
				uri.lastIndexOf("."));
		if(action.equals("/list")){
			ComputerDAO dao = 
				(ComputerDAO) DAOFactory.getInstance(ComputerDAO.class);
			List<Computer> computers = 
				new ArrayList<Computer>();
			try {
				computers = 
					dao.findAll();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
			request.setAttribute("computers", computers);
			request.getRequestDispatcher("cart_list.jsp")
			.forward(request, response);
			return;
		}
		if(action.equals("/buy")){
			String id = request.getParameter("id");
			System.out.println("id:" + id);
			HttpSession session = 
				request.getSession();
			//step1 从session中取出购物车
			Cart cart = (Cart) session.getAttribute("cart");
			if(cart == null){
				cart = new Cart();
				session.setAttribute("cart", cart);
				
			}
			//step2 向购物车中添加商品
			//step2_1: 构造一个商品条目
			ComputerDAO dao = 
				(ComputerDAO) DAOFactory.getInstance(ComputerDAO.class);
			Computer c  = null;
			try {
				c = dao.findById(new Long(id));
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			} 
			System.out.println("computer:" + c);
			CartItem item = new CartItem();
			item.setQty(1);
			item.setComputer(c);
			//step2_2
			boolean flag = cart.add(item);
			//flag为false:表示添加失败
			System.out.println("cart:" + cart);
			System.out.println("cart's size:" + cart.list().size());
			if(!flag){
				request.setAttribute("buyMsg_" + c.getId(), "已经购买了该商品");
				request.getRequestDispatcher("list.do")
				.forward(request, response);
				return;
			}
			response.sendRedirect("list.do");
			return;
		}
		
	}
	

}
