package controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import entity.Users;
import orm.utils.ReflectionUtils;
import service.VoteOptionService;

@RestController
public class UploadController {
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private VoteOptionService voteOptionService;
	
	private final int MIN_CODE = 1000000;
	private final int MAX_CODE = 9999999;
	@RequestMapping(value="upload.htm",method=RequestMethod.POST)
	public String uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("voteOptionId") String voteOptionId) throws IllegalStateException, IOException, IllegalArgumentException, IllegalAccessException, URISyntaxException, SQLException{
		System.out.println(voteOptionId);
		if (!image.isEmpty()) {
			String fileName = image.getOriginalFilename();
			String newFileName = enCryptFileName(fileName);
			String path = request.getServletContext().getRealPath("/images/") + newFileName;
			System.out.println(path);
			image.transferTo(new File(path));
			voteOptionService.insertImage(newFileName, Long.parseLong(voteOptionId));
		}
		return null;
	}
	private String enCryptFileName(String fileName) {
		Random random = new Random();
		int number = random.nextInt(MAX_CODE-MIN_CODE+1) + MIN_CODE;
		Date dateNow = new Date();
		int indexFileType = fileName.lastIndexOf(".");
		String name = fileName.substring(0,indexFileType); 
		String type = fileName.substring(indexFileType);
		return name+String.valueOf(number)+dateNow.toString().replace(" ", "").replace(":", "")+type;
	}
}
