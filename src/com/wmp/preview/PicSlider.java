package com.wmp.preview;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PicSlider {

	public List getPicList(String picPath){
		List list = new ArrayList();
		File directory = new File(picPath);
		File[] txtFiles = directory.listFiles(new FileNameSelector("JPG"));
		for (File file : txtFiles) {
			list.add(file.getPath());
		}
		txtFiles = directory.listFiles(new FileNameSelector("jpg"));
		for (File file : txtFiles) {
			list.add(file.getPath());
		}
		return list;
		
	}

}
