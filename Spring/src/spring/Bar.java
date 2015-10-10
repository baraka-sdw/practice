package spring;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Bar {
    public List list;
    public Set set;
    public Properties props;
    public Map map;
	public List getList() {
		
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
}
