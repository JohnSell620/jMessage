package io.johnsell620.jMessage.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
/**
 * 
 * @author johnny
 *
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Date date = new Date();
		System.out.println("Request filter");
		System.out.println(date + " Headers: " + requestContext.getHeaders());
		
//		File file = new File("requestlogs.txt");
//		if (!file.exists()) file.createNewFile();
//		
//		try(FileWriter fw = new FileWriter("./requestlogs.txt", true);
//			    BufferedWriter bw = new BufferedWriter(fw);
//			    PrintWriter out = new PrintWriter(bw))
//		{
//		    out.println("Headers: " + requestContext.getHeaders());
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		Date date = new Date();
		System.out.println("Response filter");
		System.out.println(date + " Headers: " + responseContext.getHeaders());
		
//		File file = new File("./responselogs.txt");
//		if (!file.exists()) file.createNewFile();
//		
//		try(FileWriter fw = new FileWriter("responselogs.txt", true);
//			    BufferedWriter bw = new BufferedWriter(fw);
//			    PrintWriter out = new PrintWriter(bw))
//		{
//		    out.println("Headers: " + requestContext.getHeaders());
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}		
	}

}
