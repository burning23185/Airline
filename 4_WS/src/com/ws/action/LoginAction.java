package com.ws.action;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import com.ws.dao.MemberDAO;
import com.ws.vo.MemberVO;

import util.SHA256;

public class LoginAction implements Action {
	private MemberDAO dao;
	private static String RSA_INSTANCE = "RSA"; // rsa transformation
	private static PrivateKey privateKey; // privateKey ���������� ����
	private static PublicKey publicKey;
	private static RSAPublicKeySpec publicSpec;

	public LoginAction(MemberDAO dao) {
		this.dao = dao;
		KeyPairGenerator generator;
		try {
			generator = KeyPairGenerator.getInstance(LoginAction.RSA_INSTANCE);
			SecureRandom random = new SecureRandom();
			generator.initialize(1024, random);

			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance(LoginAction.RSA_INSTANCE);
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String email = null;
		String pw = null;
		HttpSession session = request.getSession();
		if (request.getParameter("email") == null) {
			// ���ǿ� ����Ű�� ���ڿ��� Ű���Ͽ� ����Ű�� �����Ѵ�.
			session.setAttribute("__rsaPrivateKey__", privateKey);
			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

			request.setAttribute("RSAModulus", new Gson().toJson(publicKeyModulus)); // rsa modulus �� request �� �߰�
			request.setAttribute("RSAExponent", new Gson().toJson(publicKeyExponent));
			request.setAttribute("nickname", new Gson().toJson("null"));
			return "getLogin.jsp";
		} else {	
			PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
			session.removeAttribute("__rsaPrivateKey__"); // Ű�� ������ ���´�. �׻� ���ο� Ű�� �޵��� ����.
			if (privateKey == null) {
				throw new RuntimeException("��ȣȭ ���Ű ������ ã�� �� �����ϴ�.");
			}
			try {
				email = decryptRsa(privateKey, request.getParameter("email"));
				pw = decryptRsa(privateKey, request.getParameter("pw"));
				email = Base64.base64Decode(email);
				pw = new SHA256().encryptSHA256(Base64.base64Decode(pw));

			} catch (Exception ex) {
				throw new ServletException(ex.getMessage(), ex);
			}
		}
		
		String nickname = dao.login(new MemberVO(email, pw));
		request.setAttribute("RSAModulus", new Gson().toJson("null"));
		request.setAttribute("RSAExponent", new Gson().toJson("null"));
		request.setAttribute("nickname", new Gson().toJson(nickname));
		
		return "getLogin.jsp";
	}

	private String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {

		Cipher cipher = Cipher.getInstance("RSA");
		byte[] encryptedBytes = hexToByteArray(securedValue);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		String decryptedValue = new String(decryptedBytes, "utf-8"); // ���� ���ڵ� ����.
		return decryptedValue;
	}

	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() % 2 != 0) {
			return new byte[] {};
		}

		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}

}