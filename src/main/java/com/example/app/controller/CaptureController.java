package com.example.app.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CaptureController {
	
	//private final HttpSession session;
	private static final String UPLOAD_DIRECTORY =	"C:/Users/moand/gallery";
	
	
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> uploadImage(@RequestBody Map<String, String> payload) {
        try {
            String imageData = payload.get("image");
            // データURIスキーム「data:image/png;base64,」を削除
            String base64Image = imageData.split(",")[1];

            // Base64からバイト配列にデコード
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            // パスとファイル名をここで指定すればいいのね！ここではダメ！！
            //String filePath = "";//"captured_image.png";
            String filePath = UPLOAD_DIRECTORY + "/temp.jpg";
//            System.out.println(base64Image);
            //session.setAttribute("imgData", imageData);
            // 画像をファイルに保存
         
            
            
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(imageBytes);
            }
            	
            return ResponseEntity.ok("画像の保存に成功しました: " + filePath);
        }catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("画像の保存に失敗しました。");
            
            }
            }
}
