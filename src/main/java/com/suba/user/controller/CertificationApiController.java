package com.suba.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.user.dao.CertifiDao;
import com.suba.user.service.CertificationKeyGenerator;

@Controller
@RequestMapping(value="/api/certification")
public class CertificationApiController {

	@Autowired
	private CertifiDao certifiDao;
	@Autowired
	private CertificationKeyGenerator certificationKeyGenerator;

	@RequestMapping(value="/keyGeneration")
	@ResponseBody
	public String certificationKeyGeneration(String phone) throws Exception {
		try{
			//인증키 삭제&생성 루틴 실행
			certificationKeyGenerator.tempKeyGenerator(phone);
			return "success";
		}catch(Exception e){
			return "fail";
		}
	}


	@RequestMapping(value="/certification")
	@ResponseBody
	public String certification(String phone , String inputKey) throws Exception {

		try{
			if(certificationKeyGenerator.isCorrectCertifiKey( phone, inputKey)){
				return "success";
			}else{
				return "fail";
			}
		}catch(Exception e){
			return "fail";
		}
	}
}
