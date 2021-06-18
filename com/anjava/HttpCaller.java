package com.anjava;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;


//import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//import java.io.IOException;

public class HttpCaller {
	
	private String url = "https://anjava-api-server.herokuapp.com/";
	// sign = url+"users/sign/"
	// logIn = url+"users/"+{userId}
	// room = url+"room/"
	
	private String id = "";  // ������� id�� ����˴ϴ�.
	private String token = "";  // ������� ��ū(�ĺ���)�� ����˴ϴ�.
	private String name = null;  // ������� �̸��� ����˴ϴ�.
	private boolean isAdmin = false;  // ������� ������ ���ΰ� ����˴ϴ�.
	private int yjuNum = 0;  // ������� �й��� ����˴ϴ�.
	private String email = null;  // ������� �̸����� ����˴ϴ�.
	private JSONArray reservedRooms = null;  // ����ڰ� ������ �� ����� ����˴ϴ�.
	
	private boolean isSuccessful = false;  // ��û�� ����� �� ���� ���������� ������ �����Ͽ����� ����˴ϴ�.
	
	private String request(String type, String requestURL, String jsonMessage) {
		try{
			OkHttpClient client = new OkHttpClient();  // OkHttpClient ��ü�� �����մϴ�.
			
			Request request;  // ��û ��ü�� �����մϴ�.
			switch (type) {  // ���ڷ� ���� type ������ ���� ���� get, post, delete, patch�� ���ĺ��� ��û ��ü�� �����մϴ�.
			case "POST":
				request = new Request.Builder()
				.addHeader("Authorization", token)  // ����� �߰��մϴ�. (����̸�, ��)
				.url(requestURL)  // ��û url�� �߰��մϴ�.
				.post(RequestBody.create(jsonMessage, MediaType.parse("application/json; charset=utf-8"))) //POST�� ��û�մϴ�.
				.build();  // ��û�� �ۼ��մϴ�.
				break;
			case "DELETE":
				request = new Request.Builder()
				.addHeader("Authorization", token)
				.url(requestURL)
				.delete() //DELETE�� ��û�մϴ�.
				.build();
				break;
			case "PATCH":
				request = new Request.Builder()
				.addHeader("Authorization", token)
				.url(requestURL)
				.patch(RequestBody.create(jsonMessage, MediaType.parse("application/json; charset=utf-8"))) //PATCH�� ��û�մϴ�.
				.build();
				break;
			default: //GET�� ��û�մϴ�.
				request = new Request.Builder()
				.addHeader("Authorization", token)
				.url(requestURL)
				.build();
				break;
			}
                        //���� ó���� execute�Լ� ���
			Response response = client.newCall(request).execute();  // OkhttpClient ��ü�� �ۼ��� ��û��ü�� ��������� ������ ���� �� ���ƿ� ������ �����մϴ�.
			
			this.isSuccessful = response.isSuccessful();  // ������ ���������� ���ŵǾ������� �ʵ� ������ �����մϴ�.

			//���
			String message = response.body().string();  // json�������� ���ƿ� ������ string�������� �����մϴ�.
			
			return message;  // string �������� ������ ������ ��ȯ�մϴ�.

		} catch (Exception e) {
			System.err.println(e.toString());
			this.isSuccessful = false;
			return "API request and response failed";
		}
	}
	/**
	 * ���� ������� ������ ��û�մϴ�.<br>
	 * @return ���� ������� ������ ��� JSON ��ü�� ���� String �����͸� ��ȯ�մϴ�.
	 * <pre>
	 * {
  "message": "{userId}���� ���� �Դϴ�.",
  "data": {
    "isAdmin": boolean,
    "userId": "userId",
    "name": "userName",
    "email": "test@email.com",
    "yjuNum": 1234567,
    "reservedRooms": [
      {
        "sitNum": 1,
        "roomNum": 1,
        "reserveDate": "ISO 8601������ String�Դϴ�. Timezone�� UTC 0�� Z�Դϴ�."
      },
      {
        "sitNum": 99,
        "roomNum": 33,
        "reserveDate": "YYYY-MM-DDThh:mm:ss.sssZ"
      }
    ]
  }
}
	 * </pre>
	 */
	public String getUserDetail() {  // ���� ������� �������� ��û
		return this.request("GET", url+"users/"+this.id, null);
	}
	/**
	 * Ư�� ������� ������ ��ȸ�մϴ�.<br>
	 * �����ڸ� ��� �����մϴ�.
	 * @param userId �� ��ȸ�� ������� ID�Դϴ�.
	 * @return �Ű����� userID ��� ID�� ����� ������ ��� JSON ��ü�� ���� String �����͸� ��ȯ�մϴ�.
	 * <pre>
	 * {
  "message": "{userId}���� ���� �Դϴ�.",
  "data": {
    "isAdmin": boolean,
    "userId": "userId",
    "name": "userName",
    "email": "test@email.com",
    "yjuNum": 1234567,
    "reservedRooms": [
      {
        "sitNum": 1,
        "roomNum": 1,
        "reserveDate": "ISO 8601������ String�Դϴ�. Timezone�� UTC 0�� Z�Դϴ�."
      },
      {
        "sitNum": 99,
        "roomNum": 33,
        "reserveDate": "YYYY-MM-DDThh:mm:ss.sssZ"
      }
    ]
  }
}
	 * </pre>
	 */
	public String getUserDetail(String userId) {  // Ư�� ������� �������� ��û
		return this.request("GET", url+"users/"+userId, null);
	}
	/**
	 * Ư�� ���� ���� ������ ��ȸ�մϴ�.<br>
	 * @param roomNum �� ��ȸ�� ���� ȣ���Դϴ�.
	 * @return �Ű����� roomNum �̶�� ȣ���� �� ������ ��� JSON ��ü�� ���� String �����͸� ��ȯ�մϴ�.
	 * <pre>
	 * {
  "message": "{roomNum} ���� ����",
  "data": {
    "roomData": {
      "row": 10,
      "column": 8,
      "rowBlankLine": [
        3,
        6
      ],
      "columnBlankLine": [
        4,
        8
      ],
      "resetDate": "",
      "reservedData": { // sitNum ������������ �����մϴ�.
        "16": "aio",
        "19": "forbidden" // ������ ������ �¼��Դϴ�.
      }
    }
  }
}
	 * </pre>
	 */
	public String getOneRoom(int roomNum) {  // Ư�� ���� ������ ��û
		return this.request("GET", url+"room/"+String.valueOf(roomNum), null);
	}
	/**
	 * ��� �濡 ���� ������ ��ȸ�մϴ�.<br>
	 * @return ��� ���� �뷫���� ������ ��� JSON ��ü�� ���� String �����͸� ��ȯ�մϴ�.
	 * <pre>{
  "message": "��� �� ����Ʈ�Դϴ�.",
  "data": {
    "roomsData": [
      {
        "roomNum": 202,
        "maxSit": 80,
        "resetDate": "ISO 8601 // �������� �ʴٸ� ��û ����� ǥ����� �ʽ��ϴ�."
      },
      {
        "roomNum": 203,
        "maxSit": 80
      }
    ]
  }
}</pre>
	 */
	public String getAllRoom() {  // ��ü ���� ������ ��û
		return this.request("GET", url+"room/", null);
	}
	/**
	 * ���ο� ������ ����մϴ�.<br>
	 * @param id �� ��Ͻ� ����� ID�Դϴ�.
	 * @param pw �� ��Ͻ� ����� ��й�ȣ�Դϴ�.
	 * @param name �� ��Ͻ� ����� �̸��Դϴ�.
	 * @param yNum �� ��Ͻ� ����� �й��Դϴ�.
	 * @param email �� ��Ͻ� ����� �̸��� �ּ��Դϴ�.
	 * @return ȸ�� ���Խ� ������ �������� ��� JSON ��ü�� ���� String �����͸� ��ȯ�մϴ�. ���� �߻��� ���� �޽����� ��ȯ�մϴ�.
	 * <pre>{
    "userId":"user�� ���̵�" // uniuqe ,
    "password":"��й�ȣ" // min 8,
    "name":"�ѱ� ����" ,
    "yjuNum":"�л���ȣ" // unique 7�ڸ��� ��������,
    "email":"�̸���" // unique  email �����̿��� �մϴ�.
}</pre>
	 */
	public String postSign(String id, char[] pw, String name, int yNum, String email) {  // ȸ�������� ��û
		JSONObject jo = new JSONObject();
		String password = "";
		for (char a : pw) password += a;
		jo.put("userId", id);
		jo.put("password", password);
		jo.put("name", name);
		jo.put("yjuNum", yNum);
		jo.put("email", email);
		return this.request("POST", url+"users/sign/", jo.toString());
	}
	/**
	 * �α��� �մϴ�.<br>
	 * @param id �� �α����� ���̵��Դϴ�.
	 * @param pw �� �α����� ������ ���� ��й�ȣ�Դϴ�.
	 * @return ��û ��� �޽����� �Բ� ��ū�� ������ ���ΰ� ������ ��� JSON ��ü�� ���� String �����͸� ��ȯ�մϴ�.
	 * <pre>{
  "message": "userId�� �α��� ����",
  "data": {
    "token": "jwt string�Դϴ�.",
    "isAdmin": boolean
  }
}</pre>
	 */
	public String postLogIn(String id, char[] pw) {  // �α����� ��û (�α��� ������ ����� ������ �ʵ庯���� ����)
		String password = "";
		for (char a : pw) password += a;
		String result = this.request("POST", url+"users/", "{\"userId\":\""+id+"\",\"password\":\""+password+"\"}");
		if (isSuccessful) {
			JSONObject jo = new JSONObject(result).getJSONObject("data");
			this.id = id;
			this.token = jo.getString("token");
			result = getUserDetail();
			jo = new JSONObject(result).getJSONObject("data");
			this.name = jo.getString("name");
			this.isAdmin = jo.getBoolean("isAdmin");
			this.yjuNum = jo.getInt("yjuNum");
			this.email = jo.getString("email");
			this.reservedRooms = jo.getJSONArray("reservedRooms");
		}
		return result;
	}
	/**
	 * ���ο� Room�� ����ϴ�.<br>
	 * �����ڸ� ��� �����մϴ�.
	 * @param roomNum �� ���� ���� �� ȣ���Դϴ�.
	 * @param col �� �¼� ������ �Դϴ�.
	 * @param row �� �¼� �హ�� �Դϴ�.
	 * @param colBlank �� ��� ����ȣ�Դϴ�. ����� �������� null�� �Է��ϼ���.
	 * @param rowBlank �� ��� ���ȣ�Դϴ�. ����� �������� null�� �Է��ϼ���.
	 * @return <pre>{
    "roomNum": ���� ��ȣ int,
    "column": ������ int,
    "row": ������ int,
    "rowBlankLine": [
        1
    ],
    "columnBlankLine": [
        3,
        4,
    8 ...
    ]
}</pre>
	 */
	public String postCreateRoom(int roomNum, int col, int row, int[] colBlank, int[] rowBlank) {  // ���� �����ϴ� ��û
		JSONObject jo = new JSONObject();
		
		jo.put("roomNum", roomNum);
		jo.put("column", col);
		jo.put("row", row);
		if (rowBlank != null)
		jo.put("rowBlankLine", rowBlank);
		if (colBlank != null)
		jo.put("columnBlankLine", colBlank);
		System.out.println(jo.toString());
		return this.request("POST", url+"room/", jo.toString());
	}
	/**
	 * ���ο� Room�� ����ϴ�.<br>
	 * �����ڸ� ��� �����մϴ�.
	 * @param roomNum �� ���� ���� �� ȣ���Դϴ�.
	 * @param col �� �¼� ������ �Դϴ�.
	 * @param row �� �¼� �హ�� �Դϴ�.
	 * @return <pre>{
    "roomNum": ���� ��ȣ int,
    "column": ������ int,
    "row": ������ int,
    "rowBlankLine": [
        1
    ],
    "columnBlankLine": [
        3,
        4,
    8 ...
    ]
}</pre>
	 */
	public String postCreateRoom(int roomNum, int col, int row) {  // ���� �����ϴ� ��û
		JSONObject jo = new JSONObject();
		
		jo.put("roomNum", roomNum);
		jo.put("column", col);
		jo.put("row", row);
		return this.request("POST", url+"room/", jo.toString());
	}
	/**
	 * roomNum�� ���� Ư�� �¼��� �����մϴ�.<br>
	 * @param roomNum �� �¼��� ������ ���� ȣ���Դϴ�.
	 * @param sitNum �� ������ �¼� ��ȣ�Դϴ�.
	 * @return ��û ��� �޽����� ��ȯ�˴ϴ�.
	 */
	public String postReserveRoom(int roomNum, int sitNum) {  // �ڸ��� �����ϴ� ��û
		JSONObject jo = new JSONObject();
		
		jo.put("sitNum", sitNum);
		
		return this.request("POST", url+"room/"+roomNum+"/reserve", jo.toString());
	}
	/**
	 * Ư�� ����ڸ� roomNum�� ���� Ư�� �¼��� �����ŵ�ϴ�.<br>
	 * �����ڸ� ��� �����մϴ�.
	 * @param userId �� �����ų ����� ���̵��Դϴ�.
	 * @param roomNum �� �¼��� ������ ���� ȣ���Դϴ�.
	 * @param sitNum �� ������ �¼� ��ȣ�Դϴ�.
	 * @return ��û ��� �޽����� ��ȯ�˴ϴ�.
	 */
	public String postReserveRoom(String userId, int roomNum, int sitNum) {  // �ڸ��� �����ϴ� ��û
		JSONObject jo = new JSONObject();
		jo.put("userId", userId);
		jo.put("sitNum", sitNum);
		
		return this.request("POST", url+"room/"+roomNum+"/reserve", jo.toString());
	}
	/**
	 * roomNum�濡 resetDate�� ����ϰų� �����մϴ�.<br>
	 * resetDate���� ������ resetDate�� �����˴ϴ�.<br>
	 * �����ڸ� ��� �����մϴ�.
	 * @param roomNum �� ������ ��¥�� ������ �� ȣ���Դϴ�.
	 * @param resetDate ������ ��¥ ��ü�Դϴ�.
	 * @return ��û ��� �޽����� ��ȯ�˴ϴ�.
	 */
	public String patchResetDateRoom(int roomNum, Date resetDate) {  // Ư�� ���� �ڸ��� ���µǴ� �ð��� �����ϴ� ��û
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS:SZ");
		JSONObject jo = new JSONObject();
		
		jo.put("roomNum", roomNum);
		jo.put("resetDate", df.format(resetDate));
		
		return this.request("PATCH", url+"room/"+roomNum+"/reset", jo.toString());
	}
	/**
	 * ���� ������� roomNum���� Ư�� �¼��� ���� ������ ����մϴ�.
	 * @param roomNum �� ������ ����� �¼��� �ִ� �� ȣ���Դϴ�.
	 * @param sitNum �� ������ ����� �¼� ��ȣ�Դϴ�.
	 * @return ��û ��� �޽����� ��ȯ�˴ϴ�.
	 */
	public String deleteReserveRoom(int roomNum, int sitNum) {  // Ư�� �濡 ���ؼ� Ư�� �ڸ��� ������ �����ϴ� ��û
		JSONObject jo = new JSONObject();
		
		
		jo.put("sitNum", sitNum);
		
		return this.request("DELETE", url+"room/"+roomNum+"/reserve", jo.toString());
	}
	/**
	 * Ư�� ������ roomNum���� Ư�� �¼��� ���� ������ ����մϴ�.<br>
	 * �����ڸ� ��� �����մϴ�.
	 * @param userId �� ������ ����� ����� ���̵��Դϴ�.
	 * @param roomNum �� ������ ����� �¼��� �ִ� �� ȣ���Դϴ�.
	 * @param sitNum �� ������ ����� �¼� ��ȣ�Դϴ�.
	 * @return ��û ��� �޽����� ��ȯ�˴ϴ�.
	 */
	public String deleteReserveRoom(String userId, int roomNum, int sitNum) {  // Ư�� �濡 ���ؼ� Ư�� ������ ������ ����ϴ� ��û
		JSONObject jo = new JSONObject();
		
		jo.put("userId", userId);
		jo.put("sitNum", sitNum);
		
		return this.request("DELETE", url+"room/"+roomNum+"/reserve", jo.toString());
	}
	/**
	 * ���� ������� roomNum�濡 ���� ������ ����մϴ�.
	 * @param roomNum �� ������ ����� �¼��� �ִ� �� ȣ���Դϴ�.
	 * @param sitNum �� ������ ����� �¼� ��ȣ�Դϴ�.
	 * @return ��û ��� �޽����� ��ȯ�˴ϴ�.
	 */
	public String deleteReserveRoom(int roomNum) {  // Ư�� �濡 ���ؼ� ���� ������� ������ ����ϴ� ��û
		return this.request("DELETE", url+"room/"+roomNum+"/reserve", null);
	}
	/**
	 * ���� �����ϸ鼭, �� ��� ���õ� �������� ����鵵 ���� �˴ϴ�.<br>
	 * �����ڸ� ��� �����մϴ�.
	 * @param roomNum �� ������ �� ȣ���Դϴ�.
	 * @return ��û ��� �޽����� ��ȯ�˴ϴ�.
	 */
	public String deleteRoom(int roomNum) {
		
		return this.request("DELETE", url+"room/"+roomNum, null);
	}
	/**
	 * ���� �� ��ü���� �����ϰ� �ִ� ����� ������ ���� �����մϴ�.
	 */
	public void clearData() {  // ���� �����ϰ� �ִ� ����� ������ �����Ѵ�.
		this.id = "";
		this.token = "";
		this.email = null;
		this.name = null;
		this.isAdmin = false;
		this.reservedRooms = null;
		this.yjuNum = 0;
	}
	/**
	 * ���� �α��� ���θ� ��ȯ�մϴ�.
	 * @return �α��� �Ǿ� ���� �� true, �ƴ� �� false
	 */
	public boolean isLoggedIn() {
		if (token.isEmpty()) return false;
		return true;
	}
	/**
	 * ���� �α��� �Ǿ� �ִ� ������ ID�� ��ȯ�մϴ�.
	 * @return �α��� �Ǿ� ���� ������ ������ ��ȯ�մϴ�.
	 */
	public String getId() {
		return id;
	}
	/**
	 * ���� �α��� �Ǿ� �ִ� ������ �̸��� ��ȯ�մϴ�.
	 * @return �α��� �Ǿ� ���� ������ null�� ��ȯ�մϴ�.
	 */
	public String getName() {
		return name;
	}
	/**
	 * ���� �α��� �Ǿ� �ִ� ������ ������ ���θ� ��ȯ�մϴ�.
	 * @return �������� �� true, �ƴϰų� �α��� �Ǿ����� ���� �� false
	 */
	public boolean isAdmin() {
		return isAdmin;
	}
	/**
	 * ���� �α��� �Ǿ� �ִ� ������ �й��� ��ȯ�մϴ�.
	 * @return �α��� �Ǿ� ���� ������ 0�� ��ȯ�մϴ�.
	 */
	public int getYjuNum() {
		return yjuNum;
	}
	/**
	 * ���� �α��� �Ǿ� �ִ� ������ �̸����� ��ȯ�մϴ�.
	 * @return �α��� �Ǿ� ���� ������ null�� ��ȯ�մϴ�.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * ���� �α��� �Ǿ� �ִ� ������ ������ �� ����� ��ȯ�˴ϴ�.
	 * @return �α��� �Ǿ� ���� ������ null�� ��ȯ�մϴ�.
	 */
	public JSONArray getReservedRooms() {
		return reservedRooms;
	}
	
}