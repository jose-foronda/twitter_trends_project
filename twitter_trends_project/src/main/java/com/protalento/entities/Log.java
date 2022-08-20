package com.protalento.entities;

import com.octaviorobleto.sql.annotations.Column;
import com.octaviorobleto.sql.annotations.Table;

@Table(name = "logs")
public class Log {

	private Long woeid;
	@Column(name = "results")
	private String result;

	public Log() {
		super();
	}

	public Log(Long woeid, String result) {
		super();
		this.woeid = woeid;
		this.result = result;
	}

	public Long getWoeid() {
		return woeid;
	}

	public void setWoeid(Long woeid) {
		this.woeid = woeid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Log [woeid=" + woeid + ", result=" + result + "]";
	}

}
