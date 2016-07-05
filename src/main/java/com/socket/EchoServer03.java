package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class EchoServer03 {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private int PORT = 3015;
	
	private ServerSocket serverSocket ; 
	
	private ThreadPool threadPool ; //线程池
	
	private final int POOL_SIZE = 4 ; //单个CPU时线程池的工作线程个数
	
	public EchoServer03() throws IOException{
		//请求队列最大长度为5
		this.serverSocket = new ServerSocket(PORT);  //服务队列的大小
		//创建线程池
		//Runtime的availableProcessors()方法返回当前系统的CPU格式
		//系统的CPU越多，线程池的工作线程数目也越多
		threadPool = new ThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);  
		logger.info("服务端启动... 端口号：" + PORT);
	}
	
	public void service(){
		while(true){
			Socket socket = null ;
			try {
				socket = this.serverSocket.accept();
				threadPool.execute(new Server02Handler(socket));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new EchoServer03().service(); //启动服务时，根据CPU数量创建线程池，线程池中的线程处于等待任务状态，休眠了

	}
	
	public class Server02Handler implements Runnable{
		private Socket socket ;
		
		public Server02Handler(Socket socket){
			this.socket = socket ;
		}
		public void run() {
			logger.info("一个新的请求达到并创建："+socket.getInetAddress()
					+":" +socket.getPort());
			try {
				InputStream socketIn = socket.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(socketIn));
				
				OutputStream socketOut = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(socketOut, true);
				
				String msg = null ; 
				while((msg = br.readLine()) != null){
					logger.info("服务器接收到的信息为："+ msg);
					pw.println(new Date());
					if(msg.equals("bye"))
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(socket != null){
						socket.close();
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	
}

class ThreadPool extends ThreadGroup{
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean isClosed = false ; //线程池是否关闭
	//将任务放在LinkedList中，LinkedList不支持同步，
	//所以在添加任务和获取任务的声明方法中必须使用synchronized关键字
	private LinkedList<Runnable> workQueue ; //表示工作队列
	private static int threadPoolID ; //表示线程池ID
	private int threadID ; //表示工作线程ID
	//构建一个线程组
	public ThreadPool(int poolSize) { //poolSize是指线程池中工作线程的数目
		super("ThreadPool-" + (threadPoolID++)) ;//线程组名
		setDaemon(true);
		workQueue = new LinkedList<Runnable>(); //创建工作队列
		for (int i = 0; i < poolSize; i++) { //设定范围创建工作线程数
			new WorkThread().start(); //创建并启动工作线程（如果工作队列为空，则所有工作线程处于阻塞状态）
		}
	}
	//向工作队列中添加一个任务，由工作线程去执行该任务
	public synchronized void execute(Runnable task){
		if(isClosed){ //线程池关闭则抛出IllegaStateException异常
			throw new IllegalStateException();
		}
		if(task != null){
			workQueue.add(task); //将指定元素添加到此列表的结尾。
			notify();  //唤醒正在getTask()方法中等待任务的工作线程
		}
	}
	//从工作队列中取出一个任务---工作线程会调用此方法
	protected synchronized Runnable getTask() throws InterruptedException{
		while(workQueue.size() == 0){
			if(isClosed)
				return null ;
			wait();  //如果工作队列没有任务，就等待任务
		}
		return workQueue.removeFirst(); //移除并返回此列表的第一个元素。
	}
	//关闭线程池
	public synchronized void close(){
		if(!isClosed){
			isClosed = true ; 
			workQueue.clear(); //清空工作队列
			interrupt(); //中断所有工作线程，该方法继承自ThreadGroup
		}
	}
	//等待工作线程把所有任务执行完
	public void join(){
		synchronized (this) {
			isClosed = true ;
			notify(); //唤醒还在getTask方法中等待任务的工作线程
		}
		//activeCount()方法是ThreadGroup类的，获得线程组中当前所有活着的工作线程数目
		Thread[] threads = new Thread[activeCount()] ;
		//enumerate方法继承自ThreadGroup类，获得线程组中当前所有活着的工作线程
		int count = enumerate(threads);
		for (int i = 0; i < count; i++) {
			try {
				threads[i].join();//等待工作线程运行结束
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error("工作线程出错...", e);
			} 
		}
	}
	
	
	//内部类，工作线程
	private class WorkThread extends Thread{
		public WorkThread(){
			//加入当前的ThreadPool线程组中
			//Thread(ThreadGroup group , String name)
			super(ThreadPool.this,"WorkThread-"+(threadID++));
		}
		public void run(){
			//isInterrupted()方法继承自ThreadGroup类，判断线程是否中断
			while(!isInterrupted()){
				Runnable task = null ;
				try {
					task = getTask();
				} catch (InterruptedException e) {
					e.printStackTrace();
					logger.error("获得任务异常...",e);
				}
				if(task == null){
					return ;
				}
				task.run(); // 直接调用task的run方法
			}
		}
	}
	
}


