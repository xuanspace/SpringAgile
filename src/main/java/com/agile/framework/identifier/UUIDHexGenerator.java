package com.agile.framework.identifier;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

/**
 * 根据IP地址，JVM虚拟机，时间，同步序列号的情况下产生的相对唯一ID，可用于多线程，多服务器。
 * 32位 系统的 UUID 生成为36位
 */
public class UUIDHexGenerator extends AbstractUUIDGenerator {
	public static final UUIDHexGenerator DEFAULT = new UUIDHexGenerator();
	private String sep = "";
 
	protected String format(final int intval) {
		final String formatted = Integer.toHexString(intval);
		final StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}
 
	protected String format(final short shortval) {
		final String formatted = Integer.toHexString(shortval);
		final StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}
 
	public Serializable generate(final Object obj) {
		return new StringBuffer(36).append(format(getIP())).append(sep)
				.append(format(getJVM())).append(sep)
				.append(format(getHiTime())).append(sep)
				.append(format(getLoTime())).append(sep)
				.append(format(getCount())).toString();
	}
 
	public void configure(final Properties params) {
		sep = params.getProperty("separator", "");
	}
 
	public static final String generator() {
		return String.valueOf(UUIDHexGenerator.DEFAULT.generate(null));
	}
 
	public static final String generator(final Object obj) {
		return String.valueOf(UUIDHexGenerator.DEFAULT.generate(obj));
	}
 
	public static void main(final String[] args) {
		String s=UUIDHexGenerator.generator();
		System.out.println(s.length());
		System.out.println(UUIDHexGenerator.generator());
		System.out.println(UUIDHexGenerator.generator());
		String s2=UUID.randomUUID().toString();
		System.out.println(s2);
		System.out.println(s2.length());
	}
}