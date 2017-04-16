package com.suba.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.FileDao;
import com.suba.user.menuController.MenuController;
import com.suba.util.PropertieManager;
import com.suba.vo.FileVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.PhotoVo;

@Controller
@RequestMapping(value="/admin/api/file")
public class AdminFileApiController extends MenuController{

	@Autowired
	private FileDao fileDao;

	private PropertieManager manager = new PropertieManager();

	private String root = null;
	private String uploadPath = null;
	private String photoUploadPath = null;

	@PostConstruct
	public void init() {
		uploadPath = manager.get("upload_path");
		photoUploadPath = manager.get("photo_upload_path");
		root = manager.get("root");
	}

	@RequestMapping(value="/upload")
	@ResponseBody
	public Result upload( FileVO fileVO,  @RequestParam("file")MultipartFile uploadfile, HttpSession session, HttpServletResponse response ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        if (uploadfile != null) {
            String fileName = uploadfile.getOriginalFilename();

            try {
                // 1. FileOutputStream 사용
            	/*
                 byte[] fileData = uploadfile.getBytes();
                 FileOutputStream output = new FileOutputStream("C:/images1/" + fileName);
                 output.write(fileData);
                 output.close();
                 */

            	// TODO 기존것을 고체해서 쓸모 없어진 이미지는 어케 구분하지?


                 // 파일을 일딴 따야함
            	 String ext = fileName.substring(fileName.lastIndexOf("."));
                 fileVO.setMemberNo(memberVO.getMemberNo());
                 fileDao.insert(fileVO);
                 // fileNo 는 라스트 아이디로 따짐

                 File directory = new File(uploadPath+fileVO.getBoardType()+File.separator);
                 if( !directory.exists() ) directory.mkdirs();

                 String path = uploadPath+fileVO.getBoardType()+File.separator+ fileVO.getFileNo()+ext;
                 uploadfile.transferTo(new File(path));


                 // 파일명 단순화 필요
                 fileVO.setPath(path);
                 fileVO.setSize((int)uploadfile.getSize());

                 fileDao.modify(fileVO);

                 // 각각의 게시물에 업데이트 필요함
                 // 복합키 처리가 어렵다
                 if( FileVO.BOARD_TYPE_TENDER.equals(fileVO.getBoardType()) ) {
                	 fileVO.getWhere().putNumber("tenderNo", fileVO.getBoardNo());
                	 fileDao.modifyTender(fileVO);
                 }
                 else if( FileVO.BOARD_TYPE_TENDER_REPLY.equals(fileVO.getBoardType()) ) {
                	 fileVO.getWhere().putNumber("tenderNo", fileVO.getBoardNo());
                	 fileVO.getWhere().putNumber("tenderSeq", fileVO.getBoardNo2());
                	 fileDao.modifyTenderReply(fileVO);
                 }
                 else if( FileVO.BOARD_TYPE_NOTICE.equals(fileVO.getBoardType()) ) {
                	 fileVO.getWhere().putNumber("boardNo", fileVO.getBoardNo());
                	 fileDao.modifyNotice(fileVO);
                 }
                 else if( FileVO.BOARD_TYPE_FAQ.equals(fileVO.getBoardType()) ) {
                	 fileVO.getWhere().putNumber("boardNo", fileVO.getBoardNo());
                	 fileDao.modifyFaq(fileVO);
                 }
                 else if( FileVO.BOARD_TYPE_QNA.equals(fileVO.getBoardType()) ) {
                	 fileVO.getWhere().putNumber("boardNo", fileVO.getBoardNo());
                	 fileDao.modifyQna(fileVO);
                 }
                 else if( FileVO.BOARD_TYPE_DEALER.equals(fileVO.getBoardType()) ) {
                	 fileVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
                	 fileDao.modifyDealer(fileVO);

             		// 수정이후 딜러의 이미지를 변경한다.
                	 memberVO.setFileNo(fileVO.getFileNo());
                 }

            } catch (IOException e) {
                e.printStackTrace();
            } // try - catch
        } // if

		Result rs = new Result("ok", fileVO);
		return rs;
	}


	@RequestMapping(value="/download/{fileNo}")
	public void download( @PathVariable("fileNo") int fileNo, HttpServletResponse response ) throws IOException {

		FileVO qfileVO = new FileVO();
		qfileVO.setFileNo(fileNo);

		FileVO dbFileVO = fileDao.viewFile(qfileVO);
		if( dbFileVO == null || dbFileVO.getPath() == null ) return;

		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;

		try {

		    fis = new FileInputStream(new File(dbFileVO.getPath()));
		    FileCopyUtils.copy(fis, out);

		} catch(Exception e){

		    e.printStackTrace();

		}finally{

		    if(fis != null){
		        try{
		            fis.close();
		        }catch(Exception e){}
		    }

		}// try end;
		out.flush();
	}

	@RequestMapping(value="/download")
	public void download( FileVO fileVO, HttpServletResponse response ) throws IOException {

		FileVO dbFileVO = fileDao.viewFile(fileVO);
		if( dbFileVO == null || dbFileVO.getPath() == null ) return;

		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;

		try {

		    fis = new FileInputStream(new File(dbFileVO.getPath()));
		    FileCopyUtils.copy(fis, out);

		} catch(Exception e){

		    e.printStackTrace();

		}finally{

		    if(fis != null){
		        try{
		            fis.close();
		        }catch(Exception e){}
		    }

		}// try end;
		out.flush();
	}

	// 스마트 에디터 용
	@RequestMapping(value="/uploadPhoto")
	@ResponseBody
	public String uploadPhoto( HttpServletRequest request, PhotoVo vo ){

		String callback = vo.getCallback();
		String callback_func = vo.getCallback_func();
		String file_result = "";
		try {
			if(vo.getFiledata() != null && vo.getFiledata().getOriginalFilename() != null && !vo.getFiledata().getOriginalFilename().equals("")){
				//파일이 존재하면
				String original_name = vo.getFiledata().getOriginalFilename();
				String ext = original_name.substring(original_name.lastIndexOf(".")+1);
				//파일 기본경로
				//파일 기본경로 _ 상세경로
				String path = photoUploadPath + File.separator + "photo_upload" + File.separator;
				File file = new File(path);
				System.out.println("path:"+path);
				//디렉토리 존재하지 않을경우 디렉토리 생성
				if(!file.exists()) {
					file.mkdirs();
				}
				//서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
				String realname = UUID.randomUUID().toString() + "." + ext;
				///////////////// 서버에 파일쓰기 /////////////////
				vo.getFiledata().transferTo(new File(path+realname));
				file_result += "&bNewLine=true&sFileName="+original_name+"&sFileURL=/resource/photo_upload/"+realname;
			} else {
				file_result += "&errstr=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + callback + "?callback_func="+callback_func+file_result;

	}

	//다중파일업로드
	@RequestMapping("/multiplePhotoUpload")
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response){
	    try {
	         //파일정보
	         String sFileInfo = "";
	         //파일명을 받는다 - 일반 원본파일명
	         String filename = request.getHeader("file-name");
	         //파일 확장자
	         String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
	         //확장자를소문자로 변경
	         filename_ext = filename_ext.toLowerCase();
	         //파일 기본경로
	         //파일 기본경로 _ 상세경로
	         String filePath = photoUploadPath + "photo_upload" + File.separator;

	         File file = new File(filePath);
	         if(!file.exists()) {
	            file.mkdirs();
	         }
	         String realFileNm = "";
	         SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	         String today= formatter.format(new java.util.Date());
	         realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
	         String rlFileNm = filePath + realFileNm;

	         // DB에 파일 정보를 남김
	         FileVO fileVO = new FileVO();
	         fileVO.setPath(rlFileNm);
	         fileVO.setBoardType(FileVO.BOARD_TYPE_BOARD);
             fileDao.insert(fileVO);
	         ///////////////// 서버에 파일쓰기 /////////////////
	         InputStream is = request.getInputStream();
	         OutputStream os=new FileOutputStream(rlFileNm);
	         int numRead;
	         byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
	         while((numRead = is.read(b,0,b.length)) != -1){
	            os.write(b,0,numRead);
	         }
	         if(is != null) {
	            is.close();
	         }
	         os.flush();
	         os.close();
	         ///////////////// 서버에 파일쓰기 /////////////////
	         // 정보 출력
	         sFileInfo += "&bNewLine=true";
	         // img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
	         sFileInfo += "&sFileName="+ filename;;
	         sFileInfo += "&sFileURL="+root+"/api/file/download/"+fileVO.getFileNo();
	         PrintWriter print = response.getWriter();
	         print.print(sFileInfo);
	         print.flush();
	         print.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@RequestMapping(value="/fileUpload")
	@ResponseBody
	@Deprecated
	public Result fileUpload( FileVO fileVO) {
		/*
		MultipartFile uploadfile = fileVO.getUploadfile();
        if (uploadfile != null) {
            String fileName = uploadfile.getOriginalFilename();

            try {
                // 1. FileOutputStream 사용
                 byte[] fileData = uploadfile.getBytes();
                 FileOutputStream output = new FileOutputStream("C:/images1/" + fileName);
                 output.write(fileData);

                // 2. File 사용
                 File file = new File("d:/images/" + fileName);
                uploadfile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            } // try - catch
        } // if
        // 데이터 베이스 처리를 현재 위치에서 처리

		System.out.println("ㅇㅋ");
		Result rs = new Result();
		return rs;
		*/
		return null;
	}
	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<FileVO> selectFileList( PageVO pageVO ) {

		List<FileVO> FileVOList = fileDao.list(pageVO);
		int total = fileDao.total(pageVO);
		ListVo<FileVO> list = new ListVo<FileVO>( total, FileVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addFile( FileVO fileVO ) {

		int row = fileDao.insert(fileVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateFile( FileVO fileVO ) {

		int row = fileDao.modify(fileVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteFile( FileVO fileVO ) {

		int row = fileDao.delete(fileVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
