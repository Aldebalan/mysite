package com.douzone.web.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	public static void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp").forward(request, response);
	}

	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		response.sendRedirect(url);
	}

}
