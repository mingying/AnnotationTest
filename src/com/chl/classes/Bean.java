package com.chl.classes;

import com.chl.anno.Column;
import com.chl.anno.Table;
//使用注解的时候 ， 属性名＝“值”
@Table(name = "BeanTable")
public class Bean {

	@Column(name = "field")
	int field;
	
	@Column(name = "description")
	String description;
}
