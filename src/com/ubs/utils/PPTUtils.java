package com.ubs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

public class PPTUtils {

	/**
	 * @Descreption Merge PPTs of all inputs to filepath and save as output
	 * @param inputs
	 *            :absolute path
	 * @param output
	 *            :absolute path " +
	 */
	public static boolean MergePPT(List<String> inputs, String output) throws IOException {
		boolean flag = false;

		// creating empty presentation
		XMLSlideShow ppt = new XMLSlideShow();

		for (String arg : inputs) {
			System.out.println(arg);

			FileInputStream inputstream = new FileInputStream(new File(arg));
			XMLSlideShow src = new XMLSlideShow(inputstream);

			for (XSLFSlide srcSlide : src.getSlides()) {

				// merging the contents
				ppt.createSlide().importContent(srcSlide);

			}
		}

		// creating the file object
		FileOutputStream out = new FileOutputStream(output);

		// saving the changes to a file
		ppt.write(out);
		System.out.println("Merging done successfully");
		out.close();

		flag = true;

		return flag;

	}

}
