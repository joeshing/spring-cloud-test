package xyz.timessuntech.cloud.common.lang.util;

import java.io.Serializable;

/**
 * @author joeshing
 *
 * @param <K>
 * @param <V>
 */
public class KeyValue<K, V> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8232629712734455563L;
	private K key;
	private V value;

	public KeyValue() {
		super();
	}
	public KeyValue(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	
}
