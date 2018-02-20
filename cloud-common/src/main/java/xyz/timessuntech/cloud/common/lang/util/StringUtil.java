package xyz.timessuntech.cloud.common.lang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * 字符串工具类
 * 
 * @author 李学政（lixuez@toone.com.cn）
 * @version 1.0
 */

public final class StringUtil {
	private StringUtil() {
	}

	/**
	 * 该方法从commons-2.0's ObjectUtils中取出
	 * <p>
	 * Appends the toString that would be produced by <code>Object</code> if a class
	 * did not override toString itself. <code>null</code> will return
	 * <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.appendIdentityToString(*, null)            = null
	 * ObjectUtils.appendIdentityToString(null, &quot;&quot;)           = &quot;java.lang.String@1e23&quot;
	 * ObjectUtils.appendIdentityToString(null, Boolean.TRUE) = &quot;java.lang.Boolean@7fa&quot;
	 * ObjectUtils.appendIdentityToString(buf, Boolean.TRUE)  = buf.append(&quot;java.lang.Boolean@7fa&quot;)
	 * </pre>
	 * 
	 * @param buffer
	 *            the buffer to append to, may be <code>null</code>
	 * @param object
	 *            the object to create a toString for, may be <code>null</code>
	 * @return the default toString text, or <code>null</code> if <code>null</code>
	 *         passed in
	 * @since 2.0
	 */

	public static StringBuffer appendIdentityToString(StringBuffer buffer, Object object) {
		if (object == null) {
			return null;
		}
		if (buffer == null) {
			buffer = new StringBuffer();
		}
		return buffer.append(object.getClass().getName()).append('@')
				.append(Integer.toHexString(System.identityHashCode(object)));
	}

	/**
	 * 创建主键ID
	 * 
	 * @return
	 */
	public static String createPK() {
		return (UUID.randomUUID()).toString();
	}

	/**
	 * 判断一个字符串是否为空或者是右空白符号组成
	 * 
	 * @param source
	 *            String
	 * @return boolean
	 */
	public static boolean empty(String source) {
		return (source == null || source.trim().length() == 0 || source.toLowerCase().trim().equals("null")) ? true
				: false;
	}

	/**
	 * 判断一个字符串是否不为空或者不是右空白符号组成
	 * 
	 * @param source
	 *            String
	 * @return boolean
	 */
	public static boolean notEmpty(String source) {
		return (source != null && source.trim().length() > 0 && !source.toLowerCase().equals("null")) ? true : false;
	}

	public static String[] toArray(String parseString) {
		return toArray(parseString, " \t\n\r\f", false);
	}

	public static String[] toArray(String parseString, String splitString) {
		return toArray(parseString, splitString, false);
	}

	/**
	 * 分隔一个字符串
	 * 
	 * @param parseString
	 *            String 原始字符串
	 * @param splitString
	 *            String 分隔串
	 * @param returnDelims
	 *            boolean 返回值是否包含分隔串
	 * @return String[] 分隔后的字符串
	 */
	public static String[] toArray(String parseString, String splitString, boolean returnDelims) {
		StringTokenizer tokens = new StringTokenizer(parseString, splitString, returnDelims);
		String[] values = new String[tokens.countTokens()];
		int i = 0;
		while (tokens.hasMoreTokens()) {
			values[i++] = tokens.nextToken();
		}
		return values;
	}

	public static char toUpperCase(char ch) {
		if (ch >= 'a' && ch <= 'z') {
			ch -= 32;
		}
		return ch;
	}

	/**
	 * 将给定字符串指定位置上的字母大写
	 * 
	 * @param source
	 *            String
	 * @param pos
	 *            int
	 * @return String
	 */
	public static String upperCharAt(final String source, int pos) {
		if (source == null) {
			return null;
		}
		if (pos < 0 || pos >= source.length()) {
			return "";
		}
		char[] chars = source.toCharArray();
		chars[pos] = toUpperCase(chars[pos]);
		return new String(chars);
	}

	/**
	 * 得到字符串的位长度，中文＝2,英文＝1
	 * 
	 * @param sourceString
	 *            待测的字符串
	 * @return int 字节长度
	 */
	public static int lengthInBit(String sourceString) {
		int index = 0;
		char[] sourceChrs = sourceString.toCharArray();
		int sourceLength = sourceChrs.length;
		for (int i = 0; i < sourceLength; i++) {
			if (sourceChrs[i] <= 202 && sourceChrs[i] >= 8) {
				index++;
			} else {
				index += 2;
			}
		}
		sourceChrs = null;
		return index;
	}

	/**
	 * 截取字符，主要用于显示区域长度固定的字符串的显示（中文＝2,英文＝1）
	 * 
	 * @param sourceString
	 *            待处理的字符串
	 * @param viewLength
	 *            截取的长度
	 * @return 返回截取后的字符串
	 */
	public static String subInBit(final String sourceString, int viewLength) {
		char[] sourceChrs = sourceString.toCharArray();
		int sLen = sourceChrs.length;
		int i = 0;
		for (; i < viewLength; i++) {
			if (i >= sLen) {
				return sourceString;
			}
			// 非英文字符
			if (sourceChrs[i] < 8 || sourceChrs[i] > 202) { 
				viewLength--;
			}
		}
		sourceChrs = null;
		return sourceString.substring(0, i);
	}

	/**
	 * 截取字符串,若该字符串被截断，则添加指定字符串（append）在末尾
	 * 
	 * @param sourceString
	 *            待处理的字符串
	 * @param viewLength
	 *            截取的长度,是字节长度,一个中文两个字节
	 * @param append
	 *            需要添加的字符串
	 * @return 返回截取后的字符串
	 */
	public static String subAppend(final String sourceString, int viewLength, final String append) {
		char[] sourceChrs = sourceString.toCharArray();
		int sLen = sourceChrs.length;
		int i = 0;
		for (; i < viewLength; i++) {
			if (i >= sLen) {
				return sourceString;
			}
			// 非英文字符
			if (sourceChrs[i] < 8 || sourceChrs[i] > 202) {
				viewLength--;
			}
		}
		sourceChrs = null;
		return sourceString.substring(0, i) + append;
	}

	public static String wrapToXNL(String content) {
		return "<![CDATA[" + content + "]]>";
	}

	public static String getSetter(String field) {
		return "set" + upperCharAt(field, 0);
	}

	public static String getGetter(String field) {
		return "get" + upperCharAt(field, 0);
	}

	public static boolean isNumber(Object value) {
		if (value == null) {
			return false;
		}
		String sValue = value.toString();
		try {
			new java.math.BigDecimal(sValue);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static double getDouble(Object o) {
		if (o == null) {
			return 0.0;
		}
		try {
			return Double.parseDouble(o.toString().trim());
		} catch (Exception ex) {
			return 0.0;
		}
	}

	public static int getInt(Object o) {
		if (o == null) {
			return 0;
		}
		try {
			return Integer.parseInt(o.toString().trim());
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 根据给定对象（使用Object.toString()方法）返回一个非空且不包含左右空格的字符串
	 * 
	 * @param param
	 *            Object 待处理的对象
	 * @return String 处理后的字符串
	 */
	public static String getNotNullAndTrim(Object param) {
		if (param == null) {
			return "";
		}
		String rtn = param.toString();
		return rtn == null ? "" : rtn.trim();
	}

	/**
	 * 填充字符串
	 * 
	 * @param src
	 * @param length
	 * @param ch
	 * @return
	 */
	public static String fillString(String src, int length, char ch) {
		if (src == null) {
			src = new String();
		}
		while (src.length() < length) {
			src = ch + src;
		}
		return src;
	}

	/**
	 * 第一个字符小写
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerCaseStart(String str) {
		if (isEmpty(str)){
			return new String();
		}
		String substring = str.substring(0, 1);
		String lowerCase = substring.toLowerCase();
		return lowerCase + str.substring(1);
	}

	/**
	 * 第一个字符大写
	 * 
	 * @param str
	 * @return
	 */
	public static String toUpperCaseStart(String str) {
		if (isEmpty(str)) {
			return new String();
		}
		String substring = str.substring(0, 1);
		String upperCase = substring.toUpperCase();
		return upperCase + str.substring(1);
	}

	/**
	 * Check if a string is empty
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * Get the file's extension name, such as doc, png, jpeg
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtention(String filename) {
		String ext = null;
		int index = filename.lastIndexOf(".");
		if (index > 0) {
			ext = filename.substring(index + 1);
		}
		return ext;
	}

	/**
	 * Encrypt string s with MD5.
	 * 
	 * @param s
	 * @return
	 */
	public static String encodeMD5(String s) {
		if (isEmpty(s)) {
			return null;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			// ignore ex
			return null;
		}
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		md.update(s.getBytes());
		byte[] datas = md.digest();
		int len = datas.length;
		char str[] = new char[len * 2];
		int k = 0;
		for (int i = 0; i < len; i++) {
			byte byte0 = datas[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}

}
