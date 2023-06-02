package kr.or.ddit.controller.noticeboard.web;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Controller;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TelegramSendController {
	
	public void sendGet(String name, String title) throws IOException {
		String chatId = "-987525737";
		String urlName = "telegram api key값";
		String text = "";
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String timeStr = format1.format(time);
		
		text = name + "님께서 글작성을 완료!\n" +
					"[제목]:" + title + "\n" +
					"[작성일]:" + timeStr + "\n";
		
		URL url = new URL(urlName + "?chat_id=" + chatId + "&text=" + URLEncoder.encode(text, "UTF-8"));
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		int respCode = conn.getResponseCode();
		System.out.println("[상태코드 ] : " + respCode);
		
				
	}
	
}
