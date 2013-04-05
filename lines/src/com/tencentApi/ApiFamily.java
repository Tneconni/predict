package com.tencentApi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;   
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


/*必需引用java.io 与java.util相关类来读写文件*/  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  



import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;

import com.tencent.tauth.Tencent;

public class ApiFamily {
	private static HttpGet  httpRequestGet;
	private static HttpPost httpRequestPost;
	public static Tencent mTencent;
	
	public static boolean checkLogin(JSONObject response) throws JSONException{
		System.out.println("checklogin");
		System.out.println(response);
		System.out.println(response.getString("msg"));
		if( response.getString("msg") == "sucess" ){System.out.println("checklogin :success!");
			
		ApiFamily.mTencent.setOpenId(response.getString("openid"));
		ApiFamily.mTencent.setAccessToken(response.getString("access_token"), response.getString("expires_in"));
			System.out.println("the accessToken and the openId");
			System.out.println(ApiFamily.mTencent.getAccessToken());
			System.out.println(ApiFamily.mTencent.getOpenId());
			return true;
		}else{
			System.out.println("checklogin :failure!");
		}
		mTencent.setOpenId(response.getString("openid"));
		mTencent.setAccessToken(response.getString("access_token"), response.getString("expires_in"));

		return false;
		
	}
	
	public static String getFansList(Tencent mTencent){System.out.println(ApiFamily.mTencent.getOpenId());
		
		String uriAPI = "https://graph.qq.com/relation/get_fanslist?";
		uriAPI += "access_token="+ ApiFamily.mTencent.getAccessToken();
		uriAPI += "&oauth_consumer_key=100402110";
		uriAPI += "&openid="+ ApiFamily.mTencent.getOpenId();
        String result = requestGetSettings(uriAPI, "get");
        return result;
		
	}
	
	public static String add_t(String content) throws UnsupportedEncodingException{ System.out.println(ApiFamily.mTencent.getOpenId());
		
		String uriAPI = "https://graph.qq.com/t/add_t?";
		uriAPI += "access_token="+ ApiFamily.mTencent.getAccessToken();
		uriAPI += "&oauth_consumer_key=100402110";
		uriAPI += "&openid="+ ApiFamily.mTencent.getOpenId();
		uriAPI += "&content="+ content;
		HttpPost httpRequestPost = new HttpPost(uriAPI);

        List <NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("access_token",ApiFamily.mTencent.getAccessToken()));
        params.add(new BasicNameValuePair("oauth_consumer_key","100402110"));
        params.add(new BasicNameValuePair("openid",ApiFamily.mTencent.getOpenId()));
        
        params.add(new BasicNameValuePair("content", content));
        
        UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(params, "utf-8");
        
        httpRequestPost.setEntity(p_entity);
		
        String result = requestPostSettings(httpRequestPost);
        
        return result;
	}
	
	private static String requestGetSettings(String uriAPI, String requestType){

	    httpRequestGet = new HttpGet(uriAPI);	

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()
        .detectDiskWrites()
        .detectAll()   // or .detectAll() for all detectable problems
        .penaltyLog()
        .build());
     StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectLeakedClosableObjects()
        .penaltyLog()
        .penaltyDeath()
        .build());
     
        try   
        { 
        	
          DefaultHttpClient httpClient = new DefaultHttpClient();
          HttpResponse httpResponse = httpClient.execute(httpRequestGet);
          
          if(httpResponse.getStatusLine().getStatusCode() == 200)
          {

            String strResult = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(strResult);
            return strResult;
            
          }

        }
        catch (ClientProtocolException e)   
        {    

          e.printStackTrace();   
        }   
        catch (IOException e)   
        {    

          e.printStackTrace();   
        }   
        catch (Exception e)   
        {    

          e.printStackTrace();    
        }
		
        return "";

	}
	
	private static String requestPostSettings(HttpPost httpRequestPost){


        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()
        .detectDiskWrites()
        .detectAll()   // or .detectAll() for all detectable problems
        .penaltyLog()
        .build());
     StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectLeakedClosableObjects()
        .penaltyLog()
        .penaltyDeath()
        .build());
     
        try   
        { 
        	
          DefaultHttpClient httpClient = new DefaultHttpClient();

          HttpResponse httpResponse = httpClient.execute(httpRequestPost);			
          
          System.out.println("fans status Code::");
          System.out.println(httpResponse.getStatusLine()); 
          
          if(httpResponse.getStatusLine().getStatusCode() == 200)
          {

            String strResult = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(strResult);
            return strResult;
            
          }

        }
        catch (ClientProtocolException e)   
        {    

          e.printStackTrace();   
        }   
        catch (IOException e)   
        {    

          e.printStackTrace();   
        }   
        catch (Exception e)   
        {    

          e.printStackTrace();    
        }
		
        return "";
		
	}
	
	
}
