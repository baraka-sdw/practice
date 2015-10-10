package web;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CheckCodeServlet extends HttpServlet {
	/**
	 * ����һ��ͼƬ�������ظ������ java.awt.*; PrintWriter out = response.getWriter();
	 * out.println();�����ˣ� OutputStream os = response.getOutputStream()
	 * 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("text/html;charset=utf-8");
		// �������������ǰ���������ص�������ͼƬ��
		response.setContentType("image/jpeg");
		// ͼƬ���ڴ�ӳ��
		BufferedImage image = new BufferedImage(60, 20,
				BufferedImage.TYPE_INT_RGB);
		Random r = new Random();
		// ��û��ʶ���
		Graphics g = image.getGraphics();
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.fillRect(0, 0, 60, 20);
		g.setColor(new Color(0, 0, 0));
		String number = String.valueOf(r.nextInt(99999));
		// �����ɵ���֤��ŵ�session������
		HttpSession session = request.getSession();
		session.setAttribute("number", number);
		g.drawString(number, 5, 15);

		// ѹ����jpeg��ʽ
		OutputStream os = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		// ��BufferedImage�����е�ͼ����Ϣ�����
		// �򴴽��ö���(encoder)ʱָ������������
		encoder.encode(image);
	}

}
