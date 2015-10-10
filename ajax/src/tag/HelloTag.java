package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloTag extends SimpleTagSupport {
	/**
	 * 属性名与标签中的属性名相同
	 */
    private String str;
    private int count;
	@Override
	public void doTag() throws JspException, IOException {
		//实现按照count指定的次数输出str的内容
		PageContext ctx=(PageContext) getJspContext();
		JspWriter out=ctx.getOut();
		for(int i =0;i<count;i++){
			out.println(str);
		}
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
 
}
