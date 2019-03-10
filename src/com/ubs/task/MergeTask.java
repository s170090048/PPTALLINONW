package com.ubs.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.ubs.utils.PPTUtils;

public class MergeTask implements Callable<String> {

	public MergeTask(ArrayList<String> inputs, String output) {
		this.inputs = inputs;
		this.output = output;
	}

	private ArrayList<String> inputs;
	private String output;

	@Override
	public String call() {

		try {
			// ºÏ²¢PPT
			PPTUtils.MergePPT(inputs, output);
			return output;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}

}
