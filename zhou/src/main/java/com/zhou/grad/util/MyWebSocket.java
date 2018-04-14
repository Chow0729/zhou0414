package com.zhou.grad.util;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
/**
 * 声明websocket的名字
 */
@ServerEndpoint(value = "/websocket")  
public class MyWebSocket {
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * 
     * @param session
     *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @throws Exception
     */
    @OnOpen
    public void onOpen(Session session) throws Exception {
        System.out.println("连接建立  queryString:   " + session.getQueryString());
        this.session = session;
        WebSocketMapUtil.put(session.getQueryString(), this);
    }

    /**
     * 连接关闭调用的方法
     * 
     * @throws Exception
     */
    @OnClose
    public void onClose() throws Exception {
        // 从map中删除
        WebSocketMapUtil.remove(session.getQueryString());
    }

    /**
     * 收到客户端消息后调用的方法
     * 
     * @param message
     *            客户端发送过来的消息
     * @param session
     *            可选的参数
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("客户端消息：  " + message);
        try {
            MyWebSocket myWebSocket = ((MyWebSocket) WebSocketMapUtil
                    .get(session.getQueryString().replace("service", "client")));
            if (myWebSocket != null) {
                myWebSocket.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     * 
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 发送消息方法。
     * 
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发消息方法。
     * 
     * @param message
     * @throws IOException
     */
    public void sendMessageAll(String message) throws IOException {
        for (MyWebSocket myWebSocket : WebSocketMapUtil.getValues()) {
            myWebSocket.sendMessage(message);
        }
    }
}
