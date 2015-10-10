package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ValidateCode extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedImage image=new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		Random r=new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.fill3DRect(0, 0, 100, 30,true);
		String number=getNumber(5);
		HttpSession session=request.getSession();
		session.setAttribute("code",number);
		g.setColor(new Color(0,0,0));
		g.setFont(new Font(null, Font.ITALIC, 24));
		g.drawString(number,5,25);
		for (int i = 0; i < 30; i++) {
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(100), r.nextInt(30), r.nextInt(100), r.nextInt(30));
		}
		response.setContentType("image/jpeg");
		OutputStream os=response.getOutputStream();
		ImageIO.write(image, "gif", os);
		os.close();
	} 
	  public String getNumber(int size){
		  Random r=new Random();
		  String number="";
		  String str="ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		  for (int i = 0; i < size; i++) {
			  number+=str.charAt(r.nextInt(str.length()));
		}
		return number;
	  }
}
