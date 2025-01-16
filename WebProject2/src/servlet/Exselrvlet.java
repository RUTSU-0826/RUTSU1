package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
@WebServlet("/Exselrvlet")
public class Exselrvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		String path = application.getRealPath("upload"); // 파일이 저장될 폴더
		System.out.println("(참고) 절대경로 real path : "+ path);
		File filePath = new File(path);
		if(!filePath.exists()) {	 // upload폴더가 없으면
			filePath.mkdirs();
		}
				
		int fileLimit = 100 * 1024 * 1024;	// 2의 10승이 1024  100mb(메가바이트)를 뜻함. 
		MultipartRequest multi = new MultipartRequest(request , // 요청객체
														path, //파일 저장경로(절대경로) 
														fileLimit , // 파일최대크기
														"UTF-8" , // 파일명 한글깨짐 방지
														new DefaultFileRenamePolicy()); // 기본적인 'rename' 정책 MUltipartRequest객체가 생성될 때 ! 파일저장 완료
		// 이제 저장된 파일에 대한 정보(파일이름)를  MultipartRequest 객체로부터 뽑아냄
		
				int i = 1; 
				Enumeration<?> files = multi.getFileNames();
				while(files.hasMoreElements()) {
					String fileObject = (String)files.nextElement(); // 여기까지
					 String filename = multi.getFilesystemName(fileObject); //서버에 저장된 파일 이름.
					 if(i == 1) {
						 request.setAttribute("filename" + i , filename);
					 }
					 if(i == 2) {
						 request.setAttribute("filename" + i , filename);
					 }
					 System.out.println("filename" + i);
					 i++;
				}
				// fileNames 죠 ? 
				// 파일 여러개 업로드 대응
				// (참고) multi.getFile(fileObject).length(); - > 업로드한 파일 크기 (바이트 단위) 
				String description = multi.getParameter("desc");
				request.setAttribute("desc" , description);
				request.getRequestDispatcher("Exupload_Result.jsp").forward(request, response);
				//[주의]NOT "request.getParameter 안됨. 
//				filename = multi.getOriginalFileName(fileObject);// -> 웹바라우저에서 선택한 파일 이름
	}

}
